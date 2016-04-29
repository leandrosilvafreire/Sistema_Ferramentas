package br.com.ferramentas.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ferramentas.dao.FornecedorDao;
import br.com.ferramentas.dao.ProdutoDao;
import br.com.ferramentas.domain.Fornecedor;
import br.com.ferramentas.domain.Produto;
import br.com.ferramentas.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ProdutoBean {

	private Produto produtoCadastro;
	private List<Produto> produtoLista;
	private List<Produto> produtoListaConsulta;
	private String acao;
	private Long codigo;
	private List<Fornecedor> listaFornecedor;

	public Produto getProdutoCadastro() {

		if (produtoCadastro == null) {
			produtoCadastro = new Produto();
		}

		return produtoCadastro;
	}

	public void setProdutoCadastro(Produto produtoCadastro) {
		this.produtoCadastro = produtoCadastro;
	}

	public List<Produto> getProdutoLista() {
		return produtoLista;
	}

	public void setProdutoLista(List<Produto> produtoLista) {
		this.produtoLista = produtoLista;
	}

	public List<Produto> getProdutoListaConsulta() {
		return produtoListaConsulta;
	}

	public void setProdutoListaConsulta(List<Produto> produtoListaConsulta) {
		this.produtoListaConsulta = produtoListaConsulta;
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

	public List<Fornecedor> getListaFornecedor() {
		return listaFornecedor;
	}

	public void setListaFornecedor(List<Fornecedor> listaFornecedor) {
		this.listaFornecedor = listaFornecedor;
	}

	public void novo() {
		produtoCadastro = new Produto();

	}

	public void salvarPro() {

		try {
			ProdutoDao produtoDao = new ProdutoDao();
			produtoDao.salvar(produtoCadastro);
			produtoCadastro = new Produto();

			FacesUtil.addMsgInfo("Produto cadastrado com sucesso!");

		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar cadastrar o produto: " + ex.getMessage());

		}

	}

	public void pesquisar() {
		try {
			ProdutoDao produtoDao = new ProdutoDao();
			produtoLista = produtoDao.listar();

		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar listar os produtos: " + ex.getMessage());
		}

	}

	public void consultarProduto() {
		try {
			if (codigo != null) {
				ProdutoDao produtoDao = new ProdutoDao();
				produtoCadastro = produtoDao.bucarPorCodigo(codigo);
			} else {
				produtoCadastro = new Produto();
			}
			FornecedorDao fornecedorDao = new FornecedorDao();
			listaFornecedor = fornecedorDao.listar();
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar consultar o produto: " + ex.getMessage());
		}

	}

	public void excluirPro() {
		try {
			ProdutoDao produtoDao = new ProdutoDao();
			produtoDao.excluir(produtoCadastro);

			FacesUtil.addMsgInfo("Produto removido com sucesso!");

		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar remover o produto: " + ex.getMessage());

		}

	}

	public void editarPro() {
		try {
			ProdutoDao produtoDao = new ProdutoDao();
			produtoDao.editar(produtoCadastro);

			FacesUtil.addMsgInfo("Produto Atualizado com sucesso!");

		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar atualizar o produto: " + ex.getMessage());
		}

	}

}
