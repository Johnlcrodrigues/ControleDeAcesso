package br.controledeacesso.exception;

import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import com.sun.facelets.FaceletViewHandler;

public class PreventViewExpiredViewHandler extends FaceletViewHandler {

	private ViewHandler parent;

	public PreventViewExpiredViewHandler(ViewHandler parent) {
		super(parent);

		this.parent = parent;
	}

	@Override
	public UIViewRoot createView(FacesContext context, String viewId) {

		UIViewRoot viewRoot = this.parent.restoreView(context, viewId);

		if (viewRoot == null) {
			viewRoot = super.createView(context, viewId);
		}

		return viewRoot;
	}

}
