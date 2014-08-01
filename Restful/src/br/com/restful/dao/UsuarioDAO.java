package br.com.restful.dao;

import java.util.ArrayList;

import br.com.restful.model.Usuario;

/**
 * Respons�vel por conter os metodos do CRUD
 * @author cbgoulart
 *
 */
public interface UsuarioDAO {

	/**
	 * Respons�vel por listar os usu�rios do banco
	 * @param limit
	 * @return
	 */
	public ArrayList<Usuario> listarTodos(int limit);
	
	/**
	 * Respons�vel por inserir o usu�rio no banco
	 * @param usuario
	 * @return
	 */
	public Usuario inserir(Usuario usuario);
	
	/**
	 * Respons�vel por excluir o usu�rio no banco
	 * @param usuario
	 * @return
	 */
	public String excluir(Usuario usuario);
	
	/**
	 * Respons�vel por listar o usu�rio passado pelo parametro
	 * @param facebookId
	 * @return
	 */
	public Usuario listarUsuario(String facebookId);
}
