package br.com.restful.factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.restful.dao.UsuarioDAO;
import br.com.restful.dao.impl.UsuarioDAOImpl;

/**
 * Responsável pela conexão com o banco de dados.
 * @author cbgoulart
 *
 */
public class ConnectionFactory {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ws");
	static EntityManager em = emf.createEntityManager();
	
	public UsuarioDAO getUsuarioDAO(){
		return new UsuarioDAOImpl(em);
	}
}
