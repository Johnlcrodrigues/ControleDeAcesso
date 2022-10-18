package br.controledeacesso.web;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import br.controledeacesso.dao.DepartamentoRN;
import br.controledeacesso.dao.VisitaRN;
import br.controledeacesso.dao.VisitanteRN;
import br.controledeacesso.dto.DepartamentoDTO;
import br.controledeacesso.dto.VisitaDTO;
import br.controledeacesso.dto.VisitanteDTO;

@ManagedBean(name = "visitaBean")
@RequestScoped
public class VisitaBean implements Serializable {

	private static final long serialVersionUID = -7984142518401124380L;

	private VisitanteDTO visitanteDTO = new VisitanteDTO();
	private DepartamentoDTO departamentoDTO = new DepartamentoDTO();
	private VisitaDTO visitaDTO = new VisitaDTO();
	private int confirmarVisita;
	private List<VisitaDTO> lista;
	private String destinoSalvar = "visitaSucesso";
	private Date data;
	private Date horaEntrada;
	private Date horaSaida;
	private Integer id;
	private Integer idDp;

	public Integer getIdDp() {
		return idDp;
	}

	public void setIdDp(Integer idDp) {
		this.idDp = idDp;
	}

	// nova visita
	public String novo() {
		this.destinoSalvar = "visitaSucesso";
		this.visitaDTO = new VisitaDTO();
		return "/restrito/visita";
	}

	// editar visita
	public String editar() {
		this.confirmarVisita = this.visitaDTO.getCodRegistro();
		return "/restrito/visita";
	}

	public VisitanteDTO buscarVisitante() {
		List<VisitanteDTO> lista = new ArrayList<VisitanteDTO>();
		VisitanteDTO visitante1 = new VisitanteDTO();
		VisitanteRN visitanteRN = new VisitanteRN();

		lista = visitanteRN.listar();

		for (VisitanteDTO visitanteDTO : lista) {
			if (visitanteDTO.getId() == id) {
				visitante1 = visitanteDTO;
			}
		}
		return visitante1;
	}

	public DepartamentoDTO buscarDepartamento() {
		List<DepartamentoDTO> listaDep = new ArrayList<DepartamentoDTO>();
		DepartamentoDTO departamento1 = new DepartamentoDTO();
		DepartamentoRN departamentoRN = new DepartamentoRN();

		listaDep = departamentoRN.listar();

		for (DepartamentoDTO departamento : listaDep) {
			if (departamento.getId() == idDp) {
				departamento1 = departamento;
			}
		}
		return departamento1;
	}

	// salvar visita
	public String salvar() {
		visitaDTO.setIdVisitante(buscarVisitante());
		visitaDTO.setIdDepartamento(buscarDepartamento());
		visitaDTO.setData(data);
		// visitaDTO.setHoraEntrada(horaEntrada);
		// visitaDTO.setHoraSaida(horaSaida);
		VisitaRN visitaRN = new VisitaRN();
		visitaRN.salvar(this.visitaDTO);
		return this.destinoSalvar;
	}

	// excluir visita
	public String excluir() {
		addMessage("Informação", "Visita do dia "
				+ visitaDTO.getData().toString()
				+ " foi excluída do banco de dados.");
		VisitaRN visitaRN = new VisitaRN();
		visitaRN.excluir(this.visitaDTO);
		this.lista = null;
		return null;
	}

	// listagem das visitas
	public List<VisitaDTO> getLista() {
		if (this.lista == null) {
			VisitaRN visitaRN = new VisitaRN();
			this.lista = visitaRN.listar();
		}
		return this.lista;
	}

	// Método que controla a exibição de FacesMessages dos métodos excluir()
	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	// redefinir campos da página visita.xhtml
	public void resetTextFields() {
		RequestContext.getCurrentInstance().reset("form:panelVisita");
	}

	public void onDateSelect(SelectEvent event) {
		// FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("EEE, dd/MM/yyyy, HH:mm");
		format.format(event.getObject());
		// facesContext.addMessage(null, new
		// FacesMessage(FacesMessage.SEVERITY_INFO,
		// "Data e Hora Selecionada:", format.format(event.getObject())));
	}
	
	//Mensagens de erro de validação
	public void errorVisitante() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
						"O visitante deve ser selecionado."));
	}
	
	public void errorDepartamento() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
						"O departamento deve ser selecionado."));
	}
	
	public void errorData() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
						"A data deve ser selecionada."));
	}

	public VisitanteDTO getVisitanteDTO() {
		return visitanteDTO;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setVisitanteDTO(VisitanteDTO visitanteDTO) {
		this.visitanteDTO = visitanteDTO;
	}

	public DepartamentoDTO getDepartamentoDTO() {
		return departamentoDTO;
	}

	public void setDepartamentoDTO(DepartamentoDTO departamentoDTO) {
		this.departamentoDTO = departamentoDTO;
	}

	public VisitaDTO getVisitaDTO() {
		return visitaDTO;
	}

	public void setVisitaDTO(VisitaDTO visitaDTO) {
		this.visitaDTO = visitaDTO;
	}

	public int getConfirmarVisita() {
		return confirmarVisita;
	}

	public void setConfirmarVisita(int confirmarVisita) {
		this.confirmarVisita = confirmarVisita;
	}

	public void setLista(List<VisitaDTO> lista) {
		this.lista = lista;
	}

	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(Date horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public Date getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(Date horaSaida) {
		this.horaSaida = horaSaida;
	}

	public VisitaBean() {
	}

}
