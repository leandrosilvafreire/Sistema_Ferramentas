package br.com.ferramentas.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ferramentas.dao.FornecedorDao;
import br.com.ferramentas.domain.Fornecedor;
import br.com.ferramentas.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FornecedorBean {

	private Fornecedor fornecedorCadastro;
	private List<Fornecedor> fornecedorLista;
	private List<Fornecedor> fornecedorListaConsulta;
	private String acao;
	private Long codigo;

	public Fornecedor getFornecedorCadastro() {
		// pode instanciar no get ou no consultarFornecedor

		if (fornecedorCadastro == null) {
			fornecedorCadastro = new Fornecedor();
		}

		return fornecedorCadastro;
	}

	public void setFornecedorCadastro(Fornecedor fornecedorCadastro) {
		this.fornecedorCadastro = fornecedorCadastro;
	}

	public List<Fornecedor> getFornecedorLista() {
		return fornecedorLista;
	}

	public void setFornecedorLista(List<Fornecedor> fornecedorLista) {
		this.fornecedorLista = fornecedorLista;
	}

	public List<Fornecedor> getFornecedorListaConsulta() {
		return fornecedorListaConsulta;
	}

	public void setFornecedorListaConsulta(List<Fornecedor> fornecedorListaConsulta) {
		this.fornecedorListaConsulta = fornecedorListaConsulta;
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
			FornecedorDao fornecedorDao = new FornecedorDao();
			fornecedorDao.salvar(fornecedorCadastro);

			fornecedorCadastro = new Fornecedor();

			// FacesUtil.addMsgInfo(fornecedorCadastro.toString());
			FacesUtil.addMsgInfo("Fornecedor cadastrado com sucesso!");
		} catch (RuntimeException ex) {
			// Rastreia erro
			ex.printStackTrace();
			FacesUtil.addMsgErro("Erro ao tentar cadastrar o fornecedor: " + ex.getMessage());
		}
	}

	public void novo() {

		fornecedorCadastro = new Fornecedor();

	}

	public void pesquisar() {

		try {
			FornecedorDao fornecedorDao = new FornecedorDao();
			fornecedorLista = fornecedorDao.listar();

		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar listar os fornecedores: " + ex.getMessage());
		}
	}

	public void consultarFornecedor() {

		try {
			
			//Sem a variavel codigo
			
			//acao = FacesUtil.getParam("foracao");

			//String valor = FacesUtil.getParam("fornecedorcod");

			//if (valor != null) {

				//Long codigo = Long.parseLong(valor);
			
			if(codigo != null){
				FornecedorDao fornecedorDao = new FornecedorDao();
				fornecedorCadastro = fornecedorDao.bucarPorCodigo(codigo);
			}

		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar consultar o fornecedor: " + ex.getMessage());
		}

	}

	public void excluir() {
		try {

			FornecedorDao fornecedorDao = new FornecedorDao();
			fornecedorDao.excluir(fornecedorCadastro);

			FacesUtil.addMsgInfo("Fornecedor removido com sucesso!");

		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar excluir o fornecedor: " + ex.getMessage());
		}

	}

	public void editar() {

		try {

			FornecedorDao fornecedorDao = new FornecedorDao();
			fornecedorDao.editar(fornecedorCadastro);

			FacesUtil.addMsgInfo("Fornecedor atualizado com sucesso!");

		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar atualizar o fornecedor: " + ex.getMessage());

		}

	}

}
