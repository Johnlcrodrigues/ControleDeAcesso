package br.controledeacesso.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@SessionScoped
public class ControleDePagina implements Serializable {

	private static final long serialVersionUID = 3939330723933907716L;

	private String mensagem;

	public ControleDePagina() {
	}

	public String principalDepartamento() {
		this.setMensagem("você navegou para principalDepartamento");
		return "principalDepartamento";
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
