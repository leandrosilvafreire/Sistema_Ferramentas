package br.com.ferramentas.bean;

import java.util.Map;

import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.ferramentas.domain.Funcionario;
import br.com.ferramentas.util.FacesUtil;

@SuppressWarnings("serial")
public class LoginPhaseListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent evento) {
		FacesContext facesContext = evento.getFacesContext();
		UIViewRoot uiViewRoot = facesContext.getViewRoot();
		String paginaAtual = uiViewRoot.getViewId();
		System.out.println(paginaAtual);

		boolean paginaLogin = paginaAtual.contains("login.xhtml");

		if (!paginaLogin) {
			ExternalContext externalContext = facesContext.getExternalContext();
			Map<String, Object> mapa = externalContext.getSessionMap();
			LoginBean loginBean = (LoginBean) mapa.get("loginBean");

			Funcionario funcionario = loginBean.getFuncionarioLogin();

			if (funcionario.getFuncao() == null) {
				FacesUtil.addMsgErro("Usuário não autenticado!");

				Application application = facesContext.getApplication();
				NavigationHandler navigationHandler = application.getNavigationHandler();
				navigationHandler.handleNavigation(facesContext, null, "/paginas/login.xhtml?faces-redirect=true");
			}

		}

	}

	@Override
	public void beforePhase(PhaseEvent evento) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
