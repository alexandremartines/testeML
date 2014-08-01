package br.com.restful.dao;

import java.util.ArrayList;

import br.com.restful.model.Usuario;

/**
 * Responsável por conter os metodos do CRUD
 * @author cbgoulart
 *
 */
public interface UsuarioDAO {

	/**
	 * Responsável por listar os usuários do banco
	 * @param limit
	 * @return
	 */
	public ArrayList<Usuario> listarTodos(int limit);
	
	/**
	 * Responsável por inserir o usuário no banco
	 * @param usuario
	 * @return
	 */
	public Usuario inserir(Usuario usuario);
	
	/**
	 * Responsável por excluir o usuário no banco
	 * @param usuario
	 * @return
	 */
	public String excluir(Usuario usuario);
	
	/**
	 * Responsável por listar o usuário passado pelo parametro
	 * @param facebookId
	 * @return
	 */
	public Usuario listarUsuario(String facebookId);
}
