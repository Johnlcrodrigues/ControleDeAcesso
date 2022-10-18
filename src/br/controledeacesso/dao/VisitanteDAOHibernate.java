package br.controledeacesso.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.controledeacesso.dto.VisitanteDTO;

public class VisitanteDAOHibernate implements VisitanteDAO {

	private Session session;
	private Transaction transaction;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(VisitanteDTO visitanteDTO) {
		try {
			this.transaction = this.session.beginTransaction();
			this.session.save(visitanteDTO);
			this.transaction.commit();
			if (this.session.isOpen()) {
				this.session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void atualizar(VisitanteDTO visitanteDTO) {
		try {
			this.transaction = this.session.beginTransaction();
			this.session.update(visitanteDTO);
			this.transaction.commit();
			if (this.session.isOpen()) {
				this.session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void excluir(VisitanteDTO visitanteDTO) {
		try {
			this.transaction = this.session.beginTransaction();
			this.session.delete(visitanteDTO);
			this.transaction.commit();
			if (this.session.isOpen()) {
				this.session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}

	}

	@Override
	public VisitanteDTO carregar(Integer id) {
		return (VisitanteDTO) this.session.get(VisitanteDTO.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VisitanteDTO> listar() {
		try {
			this.transaction = this.session.beginTransaction();
			return this.session.createCriteria(VisitanteDTO.class).list();
		} finally {
			this.session.close();
		}
	}

	@Override
	public VisitanteDTO buscaPorCpf(String cpf) {
		try {
			this.transaction = this.session.beginTransaction();
			String hql = "select u from VisitanteDTO u where u.cpf = :cpf";
			Query consulta = this.session.createQuery(hql);
			consulta.setString("cpf", cpf);
			return (VisitanteDTO) consulta.uniqueResult();
		} finally {
			this.session.close();
		}

		// String hql = "select u from VisitanteDTO u where u.cpf = :cpf";
		// Query consulta = this.session.createQuery(hql);
		// consulta.setString("cpf", cpf);
		// return (VisitanteDTO) consulta.uniqueResult();
	}

	@Override
	public VisitanteDTO buscaPorId(Integer id) {
		try {
			this.transaction = this.session.beginTransaction();
			String hql = "select u from VisitanteDTO u where u.id = :id";
			Query consulta = this.session.createQuery(hql);
			consulta.setInteger("id", id);
			return (VisitanteDTO) consulta.uniqueResult();
		} finally {
			this.session.close();
		}
	}

}
