package br.com.restful.dao.impl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.restful.dao.UsuarioDAO;
import br.com.restful.model.Usuario;

/**
 * Respons�vel por implementar os metodos da interface
 * @author cbgoulart
 *
 */
public class UsuarioDAOImpl implements UsuarioDAO {

	private EntityManager em;

	public UsuarioDAOImpl(EntityManager em){
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Usuario> listarTodos(int limit) {
		Query q = em.createQuery("FROM Usuario");
		
		try{
			if (!"".equals(limit) && limit != 0){
				return  (ArrayList<Usuario>) q.setMaxResults(limit).getResultList();
			}
			return  (ArrayList<Usuario>) q.getResultList();
		}catch (Exception e){
			System.out.println("Erro ao listar os usu�rios: " + e);
			e.printStackTrace();
			return new ArrayList<Usuario>();
		}
	}

	@Override
	public Usuario inserir(Usuario usuario) {
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		
		return listarUsuario(usuario.getFacebookId());
	}

	@Override
	public String excluir(Usuario usuario) {
		em.getTransaction().begin();
		em.remove(usuario);
		em.getTransaction().commit();
		
		return "Usu�rio exclu�do.";
	}

	@Override
	public Usuario listarUsuario(String facebookId) {
		Query q = em.createQuery("FROM Usuario WHERE facebookId = :facebookId");
		q.setParameter("facebookId", facebookId);
		try{
			return  (Usuario) q.getSingleResult();
		}catch (Exception e){
			System.out.println("Erro ao listar o usu�rio: " + e);
			e.printStackTrace();
			return null;
		}
	}

}
