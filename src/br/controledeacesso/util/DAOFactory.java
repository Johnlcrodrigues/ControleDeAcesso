package br.controledeacesso.util;

import br.controledeacesso.dao.DepartamentoDAO;
import br.controledeacesso.dao.DepartamentoDAOHibernate;
import br.controledeacesso.dao.UsuarioDAO;
import br.controledeacesso.dao.UsuarioDAOHibernate;
import br.controledeacesso.dao.VisitaDAO;
import br.controledeacesso.dao.VisitaDAOHibernate;
import br.controledeacesso.dao.VisitanteDAO;
import br.controledeacesso.dao.VisitanteDAOHibernate;

//página 192: 4.3.2.5. Criação da classe DAOFactory
public class DAOFactory {

	public static UsuarioDAO criarUsuarioDAO() {
		UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();
		usuarioDAO.setSession(HibernateUtil.createSessionFactory()
				.getCurrentSession());
		return usuarioDAO;
	}

	public static DepartamentoDAO criarDepartamentoDAO() {
		DepartamentoDAOHibernate departamentoDAO = new DepartamentoDAOHibernate();
		departamentoDAO.setSession(HibernateUtil.createSessionFactory()
				.getCurrentSession());
		return departamentoDAO;
	}

	public static VisitanteDAO criarVisitanteDAO() {
		VisitanteDAOHibernate visitanteDAO = new VisitanteDAOHibernate();
		visitanteDAO.setSession(HibernateUtil.createSessionFactory()
				.getCurrentSession());
		return visitanteDAO;
	}

	public static VisitaDAO criarVisitaDAO() {
		VisitaDAOHibernate visitaDAO = new VisitaDAOHibernate();
		visitaDAO.setSession(HibernateUtil.createSessionFactory()
				.getCurrentSession());
		return visitaDAO;
	}

}
