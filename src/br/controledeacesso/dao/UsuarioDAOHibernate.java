package br.controledeacesso.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.controledeacesso.dto.UsuarioDTO;

public class UsuarioDAOHibernate implements UsuarioDAO {

	private Session session;
	private Transaction transaction;

	public void setSession(Session session) {
		this.session = session;
	}

	// Complemento: página 149
	@Override
	public void salvar(UsuarioDTO usuarioDTO) {
		try {
			this.transaction = this.session.beginTransaction();
			this.session.save(usuarioDTO);
			this.transaction.commit();
			if (this.session.isOpen()) {
				this.session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void atualizar(UsuarioDTO usuarioDTO) {
		try {
			this.transaction = this.session.beginTransaction();
			if (usuarioDTO.getPermissao() == null
					|| usuarioDTO.getPermissao().size() == 0) {
				UsuarioDTO usuarioPermissao = this.carregar(usuarioDTO
						.getMatricula());
				usuarioDTO.setPermissao(usuarioPermissao.getPermissao());
				this.session.evict(usuarioPermissao);
			}
			this.session.update(usuarioDTO);
			this.transaction.commit();
			if (this.session.isOpen()) {
				this.session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(UsuarioDTO usuarioDTO) {
		try {
			this.transaction = this.session.beginTransaction();
			this.session.delete(usuarioDTO);
			this.transaction.commit();
			if (this.session.isOpen()) {
				this.session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public UsuarioDTO carregar(Integer id) {
		return (UsuarioDTO) this.session.get(UsuarioDTO.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioDTO> listar() {
		try {
			this.transaction = this.session.beginTransaction();
			return this.session.createCriteria(UsuarioDTO.class).list();
		} finally {
			this.session.close();
		}
	}

	@Override
	public UsuarioDTO buscarPorLogin(String login) {
		try {
			this.transaction = this.session.beginTransaction();
			String hql = "select u from UsuarioDTO u where u.login = :login";
			Query consulta = this.session.createQuery(hql);
			consulta.setString("login", login);
			return (UsuarioDTO) consulta.uniqueResult();
		} finally {
			this.session.close();
		}
	}

}
