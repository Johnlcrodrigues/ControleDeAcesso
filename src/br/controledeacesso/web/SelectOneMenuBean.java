package br.controledeacesso.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ValueChangeEvent;

import org.hibernate.HibernateException;

import br.controledeacesso.dto.DepartamentoDTO;
import br.controledeacesso.dto.VisitanteDTO;

@ManagedBean(name = "oneMenuBean")
@RequestScoped
public class SelectOneMenuBean {

	private VisitanteDTO visitanteDTO = new VisitanteDTO();
	private DepartamentoDTO departamentoDTO = new DepartamentoDTO();
	private String idVisitante;
	private String idDepartamento;

	public void onSelectOneMenuChangeIdVisitante(ValueChangeEvent changeEvent) {
		try {
			this.idVisitante = String.valueOf(visitanteDTO.getId());
			this.idVisitante = changeEvent.getNewValue().toString();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	public void onSelectOneMenuChangeIdDepartamento(ValueChangeEvent changeEvent) {
		try {
			this.idDepartamento = String.valueOf(departamentoDTO.getId());
			this.idDepartamento = changeEvent.getNewValue().toString();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	public VisitanteDTO getVisitanteDTO() {
		return visitanteDTO;
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

	public String getIdVisitante() {
		return idVisitante;
	}

	public void setIdVisitante(String idVisitante) {
		this.idVisitante = idVisitante;
	}

	public String getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(String idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public SelectOneMenuBean() {
		this.idVisitante = "Nenhum visitante selecionado";
		this.idDepartamento = "Nenhum departamento selecionado";
	}
}
