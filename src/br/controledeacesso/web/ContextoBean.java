package br.controledeacesso.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.controledeacesso.dao.UsuarioRN;
import br.controledeacesso.dto.UsuarioDTO;

@ManagedBean(name = "contextoBean")
@SessionScoped
public class ContextoBean {

	private UsuarioDTO usuarioLogado = null;

	public UsuarioDTO getUsuarioLogado() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		String login = external.getRemoteUser();

		if (this.usuarioLogado == null
				|| !login.equals(this.usuarioLogado.getLogin())) {

			if (login != null) {
				UsuarioRN usuarioRN = new UsuarioRN();
				this.usuarioLogado = usuarioRN.buscarPorLogin(login);
			}
		}
		return usuarioLogado;
	}

	public void setUsuarioLogado(UsuarioDTO usuarioDTO) {
		this.usuarioLogado = usuarioDTO;
	}

	
}
