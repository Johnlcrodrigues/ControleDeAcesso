package br.controledeacesso.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.controledeacesso.dao.VisitanteRN;
import br.controledeacesso.dto.VisitanteDTO;

@ManagedBean(name = "visitanteBean")
@RequestScoped
public class VisitanteBean implements Serializable {

	private static final long serialVersionUID = 8517921001416640749L;

	private VisitanteDTO visitanteDTO = new VisitanteDTO();
	private String confirmarVisitante;
	private List<VisitanteDTO> lista;
	private String destinoSalvar = "visitanteSucesso";
	private StreamedContent imagem;
	private byte foto;

	// novo visitante
	public String novo() {
		this.destinoSalvar = "visitanteSucesso";
		this.visitanteDTO = new VisitanteDTO();
		return "/restrito/visitante";
	}

	// editar visitante
	public String editar() {
		this.confirmarVisitante = this.visitanteDTO.getCpf();
		return "/restrito/visitante";
	}

	// salvar visitante
	public String salvar() throws IOException {

		PhotoCamView photoCamView = new PhotoCamView();
		visitanteDTO.setFoto(photoCamView.fotoSave());
		VisitanteRN visitanteRN = new VisitanteRN();
		visitanteRN.salvar(this.visitanteDTO);
		return this.destinoSalvar;
	}

	// excluir visitante
	public String excluir() {
		addMessage("Informação", "Visitante " + visitanteDTO.getNome()
				+ " foi excluído do banco de dados.");
		VisitanteRN visitanteRN = new VisitanteRN();
		visitanteRN.excluir(this.visitanteDTO);
		this.lista = null;
		return null;
	}

	// Método que controla a exibição de FacesMessages dos métodos excluir()
	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	// redefinir campos da página usuario.xhtml
	public void resetTextFields() {
		RequestContext.getCurrentInstance().reset("form:panel");
	}

	// Força o panelWebCam a mostrar a foto capturada
	public void refreshPhotoCam() {
		RequestContext.getCurrentInstance().reset("form:panelWebCam");
	}

	// listagem dos visitantes
	public List<VisitanteDTO> getLista() {
		if (this.lista == null) {
			VisitanteRN visitanteRN = new VisitanteRN();
			this.lista = visitanteRN.listar();
		}
		return this.lista;
	}

	public StreamedContent getImagemDinamica() {
		VisitanteRN visitanteRN = new VisitanteRN();
		String strid = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("id_imagem");
		if (strid != null) {
			Integer id = Integer.parseInt(strid);
			VisitanteDTO visitanteDTO = visitanteRN.buscaPorId(id);
			return visitanteDTO.getImagem();

		}
		return new DefaultStreamedContent();
	}

	public VisitanteDTO getVisitanteDTO() {
		return visitanteDTO;
	}

	public void setVisitanteDTO(VisitanteDTO visitanteDTO) {
		this.visitanteDTO = visitanteDTO;
	}

	public String getConfirmarVisitante() {
		return confirmarVisitante;
	}

	public void setConfirmarVisitante(String confirmarVisitante) {
		this.confirmarVisitante = confirmarVisitante;
	}

	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}

	public void setLista(List<VisitanteDTO> lista) {
		this.lista = lista;
	}

	public StreamedContent getImagem() {
		return imagem;
	}

	public void setImagem(StreamedContent imagem) {
		this.imagem = imagem;
	}

	public byte getFoto() {
		return foto;
	}

	public void setFoto(byte foto) {
		this.foto = foto;
	}

	public VisitanteBean() {

	}

}
