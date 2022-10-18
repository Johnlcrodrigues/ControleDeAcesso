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

	// novo usuario: pág 225
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
					"A senha não foi confirmada corretamente.", null);
			context.addMessage(null, facesMessage);
			return null;
		}
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(this.usuarioDTO);
		return this.destinoSalvar;
	}

	// excluir usuario
	public String excluir() {
		addMessage("Informação", "Usuário " + usuarioDTO.getNome()
				+ " foi excluído do banco de dados.");
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.excluir(this.usuarioDTO);
		this.lista = null;
		return null;
	}

	// Método que controla a exibição de FacesMessages dos métodos excluir() e
	// ativar()
	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	// Mensagem de edição de célula no dataTable
	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Célula Atualizada", "Antes: " + oldValue + ", Depois: "
							+ newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	// status do usuario
	public String ativar() {
		try {
			if (this.usuarioDTO.getAtivo()) {
				this.usuarioDTO.setAtivo(false);
				addMessage("Informação", "Usuário " + usuarioDTO.getNome()
						+ " está desativado.");
			} else {
				this.usuarioDTO.setAtivo(true);
				addMessage("Informação", "Usuário " + usuarioDTO.getNome()
						+ " está ativado.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(this.usuarioDTO);
		return null;
	}

	//Adiciona ou remove permissão de administrador
	public String atribuirPermissao(UsuarioDTO usuarioDTO, String permissao) {
		this.usuarioDTO = usuarioDTO;
		java.util.Set<String> permissoes = this.usuarioDTO.getPermissao();
		if (permissoes.contains(permissao)) {
			permissoes.remove(permissao);
			addMessage("Informação", "Permissão de administrador removida.");
		} else {
			permissoes.add(permissao);
			addMessage("Informação", "Permissão de administrador adicionada.");
		}
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(this.usuarioDTO);
		return null;
	}

	// redefinir campos da página usuario.xhtml
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
