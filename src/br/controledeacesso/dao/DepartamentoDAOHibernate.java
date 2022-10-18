package br.controledeacesso.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.controledeacesso.dto.DepartamentoDTO;

public class DepartamentoDAOHibernate implements DepartamentoDAO {

	private Session session;
	private Transaction transaction;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(DepartamentoDTO departamentoDTO) {
		try {
			this.transaction = this.session.beginTransaction();
			this.session.save(departamentoDTO);
			this.transaction.commit();
			if (this.session.isOpen()) {
				this.session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void atualizar(DepartamentoDTO departamentoDTO) {
		try {
			this.transaction = this.session.beginTransaction();
			this.session.update(departamentoDTO);
			this.transaction.commit();
			if (this.session.isOpen()) {
				this.session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void excluir(DepartamentoDTO departamentoDTO) {
		try {
			this.transaction = this.session.beginTransaction();
			this.session.delete(departamentoDTO);
			this.transaction.commit();
			if (this.session.isOpen()) {
				this.session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}

	}

	@Override
	public DepartamentoDTO carregar(Integer id) {
		return (DepartamentoDTO) this.session.get(DepartamentoDTO.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DepartamentoDTO> listar() {
		try {
			this.transaction = this.session.beginTransaction();
			return this.session.createCriteria(DepartamentoDTO.class).list();
		} finally {
			this.session.close();
		}
	}

	@Override
	public DepartamentoDTO buscarPorRamal(String ramal) {
		String hql = "select u from DepartamentoDTO u where u.ramal = :ramal";
		Query consulta = this.session.createQuery(hql);
		consulta.setString("ramal", ramal);
		return (DepartamentoDTO) consulta.uniqueResult();
	}

}
