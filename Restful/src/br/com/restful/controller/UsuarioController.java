package br.com.restful.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import br.com.restful.factory.ConnectionFactory;
import br.com.restful.model.Usuario;

/**
 * Responsável por conter os metodos REST de acesso ao webservice
 * @author cbgoulart
 *
 */
@Path("/usuario")
public class UsuarioController {

	ConnectionFactory conn = new ConnectionFactory();
	
	/**
	 * Responsável por listar todos os usuários
	 * @param limit
	 * @return
	 */
	@GET
	@Path("/listarTodos/{limit}")
	@Produces("application/json")
	public ArrayList<Usuario> listarTodos(@PathParam("limit") String limit){
		return conn.getUsuarioDAO().listarTodos(Integer.valueOf(limit));
	}
	
	/**
	 * Responsável por inserir o usuário
	 * @param facebookId
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@GET
	@Path("/inserir/{facebookId}")
	@Produces("application/json")
	public Usuario inserir(@PathParam("facebookId") String facebookId) throws IOException, ParseException{
		
		JSONObject jsonObjeto;
		JSONParser parser = new JSONParser();
		
		URL url = new URL("https://graph.facebook.com/" + facebookId);
		Reader reader = new InputStreamReader( url.openStream() );
		
		jsonObjeto = (JSONObject) parser.parse(reader);
		
		Usuario usuario = new Usuario();
		usuario.setFacebookId(jsonObjeto.get("id").toString());
		usuario.setNome(jsonObjeto.get("name").toString());
		usuario.setUsername(jsonObjeto.get("username").toString());
		usuario.setGenero(jsonObjeto.get("gender").toString());
		
		return conn.getUsuarioDAO().inserir(usuario);
	}
	
	/**
	 * Responsável por excluir o usuário
	 * @param facebookId
	 * @return
	 */
	@GET
	@Path("/excluir/{facebookId}")
	public String excluir(@PathParam("facebookId") String facebookId) {
		
		Usuario usuario = conn.getUsuarioDAO().listarUsuario(facebookId);
		
		return conn.getUsuarioDAO().excluir(usuario);
	}
}
