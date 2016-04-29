package br.com.ferramentas.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.ferramentas.dao.FuncionarioDao;
import br.com.ferramentas.domain.Funcionario;
import br.com.ferramentas.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FuncionarioBean {

	private Funcionario funcionarioCadastro;
	private List<Funcionario> funcionarioLista;
	private List<Funcionario> funcionarioListaConsulta;
	private String acao;
	private Long codigo;

	public Funcionario getFuncionarioCadastro() {
		if (funcionarioCadastro == null) {
			funcionarioCadastro = new Funcionario();
		}
		return funcionarioCadastro;
	}

	public void setFuncionarioCadastro(Funcionario funcionarioCadastro) {
		this.funcionarioCadastro = funcionarioCadastro;
	}

	public List<Funcionario> getFuncionarioLista() {
		return funcionarioLista;
	}

	public void setFuncionarioLista(List<Funcionario> funcionarioLista) {
		this.funcionarioLista = funcionarioLista;
	}

	public List<Funcionario> getFuncionarioListaConsulta() {
		return funcionarioListaConsulta;
	}

	public void setFuncionarioListaConsulta(List<Funcionario> funcionarioListaConsulta) {
		this.funcionarioListaConsulta = funcionarioListaConsulta;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public void salvar() {

		try {
			FuncionarioDao funcionarioDao = new FuncionarioDao();
			funcionarioDao.salvar(funcionarioCadastro);

			funcionarioCadastro = new Funcionario();

			FacesUtil.addMsgInfo("Funcionário cadastrado com sucesso!");

		} catch (RuntimeException ex) {

			FacesUtil.addMsgErro("Erro ao tentar cadastrar o funcionário: " + ex.getMessage());

		}

	}

	public void novo() {

		funcionarioCadastro = new Funcionario();

	}

	public void pesquisar() {

		try {
			FuncionarioDao funcionarioDao = new FuncionarioDao();
			funcionarioLista = funcionarioDao.listar();

		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar listar os funcionários: " + ex.getMessage());
		}

	}

	public void consultarFuncionario() {
		try {

			if (codigo != null) {
				FuncionarioDao funcionarioDao = new FuncionarioDao();
				funcionarioCadastro = funcionarioDao.bucarPorCodigo(codigo);

			} else {
				funcionarioCadastro = new Funcionario();
			}

		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar consultar o funcionário: " + ex.getMessage());

		}

	}

	public void excluir() {
		try {

			FuncionarioDao funcionarioDao = new FuncionarioDao();
			funcionarioDao.excluir(funcionarioCadastro);

			FacesUtil.addMsgInfo("Funcionário removido com sucesso!");

		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar excluir o funcionário: " + ex.getMessage());
		}

	}

	public void editar() {

		try {

			FuncionarioDao funcionarioDao = new FuncionarioDao();
			funcionarioDao.editar(funcionarioCadastro);

			FacesUtil.addMsgInfo("Funcionário atualizado com sucesso!");

		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar atualizar o funcionário: " + ex.getMessage());

		}

	}

}
