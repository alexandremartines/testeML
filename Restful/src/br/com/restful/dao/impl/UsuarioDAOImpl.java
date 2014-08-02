package br.com.restful.dao.impl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import br.com.restful.dao.UsuarioDAO;
import br.com.restful.model.Usuario;

/**
 * Respons�vel por implementar os metodos da interface
 * @author cbgoulart
 *
 */
public class UsuarioDAOImpl implements UsuarioDAO {

	private EntityManager em;
	Logger logger = Logger.getLogger(UsuarioDAOImpl.class);

	public UsuarioDAOImpl(EntityManager em){
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Usuario> listarTodos(int limit) {
		Query q = em.createQuery("FROM Usuario");
		
		try{
			if (!"".equals(limit) && limit != 0){
				logger.info("Listando " + limit + " usu�rio(s)");
				return  (ArrayList<Usuario>) q.setMaxResults(limit).getResultList();
			}
			logger.info("Listando todos os usu�rios");
			return  (ArrayList<Usuario>) q.getResultList();
		}catch (Exception e){
			logger.info("Erro ao listar os usu�rios: " + e);
			return new ArrayList<Usuario>();
		}
	}

	@Override
	public Usuario inserir(Usuario usuario) {
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		logger.info("Usu�rio inserido com sucesso: facebookId = " + usuario.getFacebookId());
		return listarUsuario(usuario.getFacebookId());
	}

	@Override
	public String excluir(Usuario usuario) {
		em.getTransaction().begin();
		em.remove(usuario);
		em.getTransaction().commit();
		logger.info("Usu�rio excluido com sucesso: facebookId = " + usuario.getFacebookId());
		return "Usu�rio exclu�do.";
	}

	@Override
	public Usuario listarUsuario(String facebookId) {
		Query q = em.createQuery("FROM Usuario WHERE facebookId = :facebookId");
		q.setParameter("facebookId", facebookId);
		try{
			logger.info("Listando o usu�rio");
			return  (Usuario) q.getSingleResult();
		}catch (Exception e){
			logger.info("Erro ao listar o usuario:  "+ e);
			return null;
		}
	}

}
