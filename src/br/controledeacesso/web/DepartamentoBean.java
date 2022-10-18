package br.controledeacesso.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.controledeacesso.dao.DepartamentoRN;
import br.controledeacesso.dto.DepartamentoDTO;

@ManagedBean(name = "departamentoBean")
@RequestScoped
public class DepartamentoBean implements Serializable {

	private static final long serialVersionUID = -7427388476267843699L;

	private DepartamentoDTO departamentoDTO = new DepartamentoDTO();
	private String confirmarDepartamento;
	private List<DepartamentoDTO> lista;
	private String destinoSalvar = "departamentoSucesso";

	// novo departamento
	public String novo() {
		this.destinoSalvar = "departamentoSucesso";
		this.departamentoDTO = new DepartamentoDTO();
		return "/admin/departamento";
	}

	// editar departamento
	public String editar() {
		this.confirmarDepartamento = this.departamentoDTO.getRamal();
		return "/admin/departamento";
	}

	// salvar departamento
	public String salvar() {
		DepartamentoRN departamentoRN = new DepartamentoRN();
		departamentoRN.salvar(this.departamentoDTO);
		return this.destinoSalvar;
	}

	// excluir usuario
	public String excluir() {
		addMessage("Informação", "Departamento " + departamentoDTO.getNome()
				+ " foi excluído do banco de dados.");
		DepartamentoRN departamentoRN = new DepartamentoRN();
		departamentoRN.excluir(this.departamentoDTO);
		this.lista = null;
		return null;
	}

	// Método que controla a exibição de FacesMessages
	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	// redefinir campos da página usuario.xhtml
	public void resetTextFields() {
		RequestContext.getCurrentInstance().reset("form:panel");
	}

	// listagem de departamentos
	public List<DepartamentoDTO> getLista() {
		if (this.lista == null) {
			DepartamentoRN departamentoRN = new DepartamentoRN();
			this.lista = departamentoRN.listar();
		}
		return this.lista;
	}

	public void setLista(List<DepartamentoDTO> lista) {
		this.lista = lista;
	}

	public DepartamentoDTO getDepartamentoDTO() {
		return departamentoDTO;
	}

	public void setDepartamentoDTO(DepartamentoDTO departamentoDTO) {
		this.departamentoDTO = departamentoDTO;
	}

	public String getConfirmarDepartamento() {
		return confirmarDepartamento;
	}

	public void setConfirmarDepartamento(String confirmarDepartamento) {
		this.confirmarDepartamento = confirmarDepartamento;
	}

	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}

	public DepartamentoBean() {

	}

}
