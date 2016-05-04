package br.com.ferramentas.bean;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

@SuppressWarnings("serial")
public class LoginPhaseListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent evento) {
		FacesContext facesContext = evento.getFacesContext();
		UIViewRoot uiViewRoot = facesContext.getViewRoot();
		String paginaAtual = uiViewRoot.getViewId();
		System.out.println(paginaAtual);
		
		boolean paginaLogin = paginaAtual.contains("login.xhtml");
		System.out.println("Valor = " + paginaLogin);
	}

	@Override
	public void beforePhase(PhaseEvent evento) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
