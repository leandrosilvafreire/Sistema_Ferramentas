package br.com.ferramentas.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ferramentas.dao.ProdutoDao;
import br.com.ferramentas.domain.Item;
import br.com.ferramentas.domain.Produto;
import br.com.ferramentas.util.FacesUtil;

@ManagedBean
@ViewScoped
public class VendaBean {

	private List<Produto> produtoLista;
	private List<Produto> produtoListaConsulta;

	private List<Item> itensLista;

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

		}

	}

}
