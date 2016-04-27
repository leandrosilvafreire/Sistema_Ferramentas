package br.com.ferramentas.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.ferramentas.dao.FornecedorDao;
import br.com.ferramentas.dao.ProdutoDao;
import br.com.ferramentas.domain.Fornecedor;
import br.com.ferramentas.domain.Produto;

public class ProdutoDaoTest {

	@Test
	@Ignore
	public void salvar() {

		FornecedorDao dao = new FornecedorDao();
		Fornecedor fornecedor = dao.bucarPorCodigo(1L);

		Produto pro1 = new Produto();
		pro1.setNome("Alicate de corte");
		pro1.setQuantidade(25);
		pro1.setPreco(new BigDecimal(25.62D));
		pro1.setFornecedor(fornecedor);

		FornecedorDao dao1 = new FornecedorDao();
		Fornecedor fornecedor1 = dao1.bucarPorCodigo(2L);

		Produto pro2 = new Produto();
		pro2.setNome("Cadeado");
		pro2.setQuantidade(100);
		pro2.setPreco(new BigDecimal(20.02D));
		pro2.setFornecedor(fornecedor1);

		FornecedorDao dao3 = new FornecedorDao();
		Fornecedor fornecedor2 = dao3.bucarPorCodigo(7L);

		Produto pro3 = new Produto();
		pro3.setNome("Chave de Fenda");
		pro3.setQuantidade(80);
		pro3.setPreco(new BigDecimal(15.26D));
		pro3.setFornecedor(fornecedor2);

		ProdutoDao dao2 = new ProdutoDao();
		dao2.salvar(pro1);
		dao2.salvar(pro2);
		dao2.salvar(pro3);

	}

	@Test
	@Ignore
	public void listar() {

		ProdutoDao dao = new ProdutoDao();
		List<Produto> produtos = dao.listar();

		for (Produto produto : produtos) {
			System.out.println(produto);
		}

	}

	@Test
	@Ignore
	public void buscarPorCodigo() {

		ProdutoDao dao = new ProdutoDao();
		Produto produto = dao.bucarPorCodigo(10L);
		System.out.println(produto);

	}

	@Test
	@Ignore
	public void excluir() {

		ProdutoDao dao = new ProdutoDao();
		Produto produto = dao.bucarPorCodigo(3L);
		dao.excluir(produto);

	}

	@Test
	public void editar() {
		
		ProdutoDao dao =new ProdutoDao();
		Produto produto = dao.bucarPorCodigo(5L);
		
		//FornecedorDao dao2 = new FornecedorDao();
		//Fornecedor fornecedor = dao2.bucarPorCodigo(7L);
		//produto.setFornecedor(fornecedor);
		
		produto.setNome("Corrente para Cadeado");
		//produto.setPreco(new BigDecimal(31.00D));
		//produto.setQuantidade(37);
		
		dao.editar(produto);
	}

}
