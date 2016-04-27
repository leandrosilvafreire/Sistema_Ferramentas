package br.com.ferramentas.test;

import java.math.BigDecimal;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import br.com.ferramentas.dao.ItemDao;
import br.com.ferramentas.dao.ProdutoDao;
import br.com.ferramentas.dao.VendaDao;
import br.com.ferramentas.domain.Item;
import br.com.ferramentas.domain.Produto;
import br.com.ferramentas.domain.Venda;

public class ItemDaoTest {

	@Test
	@Ignore
	public void salvar() {

		ProdutoDao dao = new ProdutoDao();
		Produto produto = dao.bucarPorCodigo(4L);

		VendaDao dao2 = new VendaDao();
		Venda venda = dao2.bucarPorCodigo(7L);

		Item item = new Item();

		item.setProduto(produto);
		item.setQuantidade(12);
		item.setValorParcial(new BigDecimal(35.62D));
		item.setVenda(venda);

		Produto produto1 = dao.bucarPorCodigo(8L);
		Venda venda1 = dao2.bucarPorCodigo(9L);

		Item item1 = new Item();

		item1.setProduto(produto1);
		item1.setQuantidade(2);
		item1.setValorParcial(new BigDecimal(23.52D));
		item1.setVenda(venda1);

		Produto produto2 = dao.bucarPorCodigo(1L);
		Venda venda2 = dao2.bucarPorCodigo(10L);

		Item item3 = new Item();

		item3.setProduto(produto2);
		item3.setQuantidade(10);
		item3.setValorParcial(new BigDecimal(174.51D));
		item3.setVenda(venda2);

		ItemDao dao3 = new ItemDao();
		dao3.salvar(item);
		dao3.salvar(item1);
		dao3.salvar(item3);

	}

	@Test
	@Ignore
	public void listar() {

		ItemDao dao = new ItemDao();
		List<Item> items = dao.listar();

		for (Item item : items) {
			System.out.println(item);
		}

	}

	@Test
	@Ignore
	public void buscarPorCodigo() {

		ItemDao dao = new ItemDao();
		Item item = dao.bucarPorCodigo(11L);
		System.out.println(item);

	}

	@Test
	@Ignore
	public void excluir() {

		ItemDao dao = new ItemDao();
		Item item = dao.bucarPorCodigo(2L);
		dao.excluir(item);
	}

	@Test
	@Ignore
	public void editar() {

		ItemDao dao2 = new ItemDao();
		Item item = dao2.bucarPorCodigo(1L);

		ProdutoDao dao = new ProdutoDao();
		Produto produto = dao.bucarPorCodigo(9L);

		VendaDao dao1 = new VendaDao();
		Venda venda = dao1.bucarPorCodigo(4L);

		item.setProduto(produto);
		item.setQuantidade(901);
		item.setValorParcial(new BigDecimal(25874.60D));
		item.setVenda(venda);

		dao2.editar(item);

	}

}
