package br.controledeacesso.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;

import br.controledeacesso.dao.UsuarioRN;
import br.controledeacesso.dto.UsuarioDTO;

@ManagedBean(name = "usuarioBean")
@RequestScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 7125616678778892464L;

	private UsuarioDTO usuarioDTO = new UsuarioDTO();
	private String confirmarSenha;
	private List<UsuarioDTO> lista;
	private List<UsuarioDTO> listaFiltrada;
	private String destinoSalvar = "usuarioSucesso";
	private Session session;
	private Transaction transaction;

	// novo usuario: p�g 225
	public String novo() {
		this.destinoSalvar = "usuarioSucesso";
		this.usuarioDTO = new UsuarioDTO();
		this.usuarioDTO.setAtivo(true);
		return "/admin/usuario";
	}

	// editar usuario
	public String editar() {
		this.confirmarSenha = this.usuarioDTO.getSenha();
		return "/admin/usuario";
	}

	// salvar usuario
	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();

		String senha = this.usuarioDTO.getSenha();
		if (!senha.equals(this.confirmarSenha)) {
			FacesMessage facesMessage = new FacesMessage(
					FacesMessage.SEVERITY_WARN,
					"A senha n�o foi confirmada corretamente.", null);
			context.addMessage(null, facesMessage);
			return null;
		}
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(this.usuarioDTO);
		return this.destinoSalvar;
	}

	// excluir usuario
	public String excluir() {
		addMessage("Informa��o", "Usu�rio " + usuarioDTO.getNome()
				+ " foi exclu�do do banco de dados.");
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.excluir(this.usuarioDTO);
		this.lista = null;
		return null;
	}

	// M�todo que controla a exibi��o de FacesMessages dos m�todos excluir() e
	// ativar()
	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	// Mensagem de edi��o de c�lula no dataTable
	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"C�lula Atualizada", "Antes: " + oldValue + ", Depois: "
							+ newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	// status do usuario
	public String ativar() {
		try {
			if (this.usuarioDTO.getAtivo()) {
				this.usuarioDTO.setAtivo(false);
				addMessage("Informa��o", "Usu�rio " + usuarioDTO.getNome()
						+ " est� desativado.");
			} else {
				this.usuarioDTO.setAtivo(true);
				addMessage("Informa��o", "Usu�rio " + usuarioDTO.getNome()
						+ " est� ativado.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(this.usuarioDTO);
		return null;
	}

	//Adiciona ou remove permiss�o de administrador
	public String atribuirPermissao(UsuarioDTO usuarioDTO, String permissao) {
		this.usuarioDTO = usuarioDTO;
		java.util.Set<String> permissoes = this.usuarioDTO.getPermissao();
		if (permissoes.contains(permissao)) {
			permissoes.remove(permissao);
			addMessage("Informa��o", "Permiss�o de administrador removida.");
		} else {
			permissoes.add(permissao);
			addMessage("Informa��o", "Permiss�o de administrador adicionada.");
		}
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(this.usuarioDTO);
		return null;
	}

	// redefinir campos da p�gina usuario.xhtml
	public void resetTextFields() {
		RequestContext.getCurrentInstance().reset("form:panel");
	}

	// listagem dos usuarios
	public List<UsuarioDTO> getLista() {
		if (this.lista == null) {
			UsuarioRN usuarioRN = new UsuarioRN();
			this.lista = usuarioRN.listar();
		}
		return this.lista;
	}

	public List<UsuarioDTO> getListaFiltrada() {
		return listaFiltrada;
	}

	public void setListaFiltrada(List<UsuarioDTO> listaFiltrada) {
		this.listaFiltrada = listaFiltrada;
	}

	public void setLista(List<UsuarioDTO> lista) {
		this.lista = lista;
	}

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public UsuarioBean() {
	}

}
