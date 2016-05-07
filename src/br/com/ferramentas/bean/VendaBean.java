package br.com.ferramentas.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.ferramentas.dao.FuncionarioDao;
import br.com.ferramentas.dao.ItemDao;
import br.com.ferramentas.dao.ProdutoDao;
import br.com.ferramentas.dao.VendaDao;
import br.com.ferramentas.domain.Funcionario;
import br.com.ferramentas.domain.Item;
import br.com.ferramentas.domain.Produto;
import br.com.ferramentas.domain.Venda;
import br.com.ferramentas.filter.VendaFiltro;
import br.com.ferramentas.util.FacesUtil;

@ManagedBean
@ViewScoped
public class VendaBean {

	private List<Produto> produtoLista;
	private List<Produto> produtoListaConsulta;
	private Venda vendaCadastro;

	private List<Item> itensLista;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	private VendaFiltro consultaFiltro;
	private List<Venda> vendaListaConsulta;

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

	public List<Item> getItensLista() {
		if (itensLista == null) {
			itensLista = new ArrayList<>();
		}
		return itensLista;
	}

	public void setItensLista(List<Item> itensLista) {
		this.itensLista = itensLista;
	}

	public Venda getVendaCadastro() {
		if (vendaCadastro == null) {
			vendaCadastro = new Venda();
			vendaCadastro.setValorTotal(new BigDecimal(0.00D));
			vendaCadastro.setQuantidadeTotal(0);
		}
		return vendaCadastro;
	}

	public void setVendaCadastro(Venda vendaCadastro) {
		this.vendaCadastro = vendaCadastro;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public VendaFiltro getConsultaFiltro() {

		if (consultaFiltro == null) {
			consultaFiltro = new VendaFiltro();
		}

		return consultaFiltro;
	}

	public void setConsultaFiltro(VendaFiltro consultaFiltro) {
		this.consultaFiltro = consultaFiltro;
	}

	public List<Venda> getVendaListaConsulta() {
		return vendaListaConsulta;
	}

	public void setVendaListaConsulta(List<Venda> vendaListaConsulta) {
		this.vendaListaConsulta = vendaListaConsulta;
	}

	public void carregarPecas() {
		try {
			ProdutoDao produtoDao = new ProdutoDao();
			produtoLista = produtoDao.listar();

		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar listar os produtos: " + ex.getMessage());
		}

	}

	public void adicionarPeca(Produto produto) {

		int posicaoSelecionada = -1;

		for (int posicao = 0; posicao < itensLista.size() && posicaoSelecionada < 0; posicao++) {

			Item itemCorrente = itensLista.get(posicao);

			if (itemCorrente.getProduto().equals(produto)) {

				posicaoSelecionada = posicao;

			}

		}

		Item item = new Item();
		item.setProduto(produto);

		if (posicaoSelecionada < 0) {
			item.setQuantidade(1);
			item.setValorParcial(produto.getPreco());
			itensLista.add(item);
		} else {
			Item itemCorrente = itensLista.get(posicaoSelecionada);
			item.setQuantidade(itemCorrente.getQuantidade() + 1);
			item.setValorParcial(produto.getPreco().multiply(new BigDecimal(item.getQuantidade())));
			itensLista.set(posicaoSelecionada, item);
		}

		vendaCadastro.setValorTotal(vendaCadastro.getValorTotal().add(produto.getPreco()));
		vendaCadastro.setQuantidadeTotal(vendaCadastro.getQuantidadeTotal() + 1);

	}

	public void removerPeca(Item item) {
		int posicaoSelecionada = -1;

		for (int posicao = 0; posicao < itensLista.size() && posicaoSelecionada < 0; posicao++) {
			Item itemCorrente = itensLista.get(posicao);

			if (itemCorrente.getProduto().equals(item.getProduto())) {
				posicaoSelecionada = posicao;

			}

		}
		if (posicaoSelecionada > -1) {
			itensLista.remove(posicaoSelecionada);
			vendaCadastro.setValorTotal(vendaCadastro.getValorTotal().subtract(item.getValorParcial()));
			vendaCadastro.setQuantidadeTotal(vendaCadastro.getQuantidadeTotal() - item.getQuantidade());

		}

	}

	public void carregarVenda() {
		vendaCadastro.setHorario(new Date());

		FuncionarioDao funcionarioDao = new FuncionarioDao();
		Funcionario funcionario = funcionarioDao.bucarPorCodigo(loginBean.getFuncionarioLogin().getCodigo());
		vendaCadastro.setFuncionario(funcionario);

	}

	public void salvarVenda() {
		try {
			VendaDao vendaDao = new VendaDao();
			Long codigoVenda = vendaDao.salvar(vendaCadastro);
			Venda vendaFk = vendaDao.bucarPorCodigo(codigoVenda);

			for (Item item : itensLista) {
				item.setVenda(vendaFk);

				ItemDao itemDao = new ItemDao();
				itemDao.salvar(item);
			}

			vendaCadastro = new Venda();
			vendaCadastro.setValorTotal(new BigDecimal(0.00D));
			itensLista = new ArrayList<>();
			FacesUtil.addMsgInfo("Venda cadastrada com sucesso! ");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar cadastrar a venda! " + ex.getMessage());
		}
	}

	public void consultarVenda() {
		
	try {
		VendaDao vendaDao = new VendaDao();
		vendaListaConsulta = vendaDao.buscarData(consultaFiltro);
		
		for(Venda venda : vendaListaConsulta){
			System.out.println(venda);
		}
		
	} catch (RuntimeException ex) {
		FacesUtil.addMsgErro("Erro ao tentar consultar as vendas: " + ex.getMessage());
	}
		
	}

}
