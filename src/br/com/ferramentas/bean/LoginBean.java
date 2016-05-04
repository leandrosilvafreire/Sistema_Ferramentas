package br.com.ferramentas.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.ferramentas.dao.FuncionarioDao;
import br.com.ferramentas.domain.Funcionario;
import br.com.ferramentas.util.FacesUtil;

@ManagedBean
@SessionScoped
public class LoginBean {

	private Funcionario funcionarioLogin;

	public Funcionario getFuncionarioLogin() {
		if (funcionarioLogin == null) {
			funcionarioLogin = new Funcionario();
		}
		return funcionarioLogin;
	}

	public void setFuncionarioLogin(Funcionario funcionarioLogin) {
		this.funcionarioLogin = funcionarioLogin;
	}

	public String logar() {
		try {
			FuncionarioDao funcionarioDao = new FuncionarioDao();
			funcionarioLogin = funcionarioDao.logar(funcionarioLogin.getCpf(), funcionarioLogin.getSenha());

			if (funcionarioLogin == null) {
				FacesUtil.addMsgErro("CPF ou Senha inválidos!");
				return null;

			} else {
				FacesUtil.addMsgInfo("Funcionário autenticado no sistema!");
				return "/paginas/principal.xhtml?faces-redirect=true";
			}
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar entrar no sistema: " + ex.getMessage());
			return null;

		}

	}

	public String sair() {
		funcionarioLogin = null;
		return "/paginas/login.xhtml?faces-redirect=true";
	}

}
