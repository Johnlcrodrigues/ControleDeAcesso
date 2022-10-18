package br.controledeacesso.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.controledeacesso.dto.VisitaDTO;

public class VisitaDAOHibernate implements VisitaDAO {

	private Session session;
	private Transaction transaction;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(VisitaDTO visitaDTO) {
		try {
			this.transaction = this.session.beginTransaction();
			this.session.save(visitaDTO);
			this.transaction.commit();
			if (this.session.isOpen()) {
				this.session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void atualizar(VisitaDTO visitaDTO) {
		try {
			this.transaction = this.session.beginTransaction();
			this.session.update(visitaDTO);
			this.transaction.commit();
			if (this.session.isOpen()) {
				this.session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(VisitaDTO visitaDTO) {
		try {
			this.transaction = this.session.beginTransaction();
			this.session.delete(visitaDTO);
			this.transaction.commit();
			if (this.session.isOpen()) {
				this.session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public VisitaDTO carregar(Integer id) {
		return (VisitaDTO) this.session.get(VisitaDTO.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VisitaDTO> listar() {
		try {
			this.transaction = this.session.beginTransaction();
			return this.session.createCriteria(VisitaDTO.class).list();
		} finally {
			this.session.close();
		}
	}

	@Override
	public VisitaDTO buscarPorData(String data) {
		String hql = "select u from VisitaDTO u where u.data = :data";
		Query consulta = this.session.createQuery(hql);
		consulta.setString("data", data);
		return (VisitaDTO) consulta.uniqueResult();
	}
}
