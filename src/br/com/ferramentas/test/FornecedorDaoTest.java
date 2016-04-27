package br.com.ferramentas.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.ferramentas.dao.FornecedorDao;
import br.com.ferramentas.domain.Fornecedor;

public class FornecedorDaoTest {

	@Ignore
	@Test
	public void salvar() {

		Fornecedor for1 = new Fornecedor();
		for1.setNome("Ferramentas Atacadao LTDA");
		for1.setCnpj("10.452.610/0001-51");

		Fornecedor for2 = new Fornecedor();
		for2.setNome("Revendas Parafuso SA");
		for2.setCnpj("27.987.498/0001-43");

		Fornecedor for3 = new Fornecedor();
		for3.setNome("Parafusos Atacado ME");
		for3.setCnpj("19.745.155/0001-13");

		
		FornecedorDao dao = new FornecedorDao();

		dao.salvar(for1);
		dao.salvar(for2);
		dao.salvar(for3);

	}

	@Ignore
	@Test
	public void listar() {

		FornecedorDao dao = new FornecedorDao();
		List<Fornecedor> fornecedors = dao.listar();

		for (Fornecedor fornecedor : fornecedors) {
			System.out.println(fornecedor);
		}

	}

	@Test
	@Ignore
	public void buscarPorCodigo() {

		FornecedorDao dao = new FornecedorDao();

		Fornecedor f1 = dao.bucarPorCodigo(5L);
		Fornecedor f2 = dao.bucarPorCodigo(7L);

		System.out.println(f1);
		System.out.println(f2);

	}

	@Test
	@Ignore
	public void excluir() {

		FornecedorDao dao = new FornecedorDao();
		Fornecedor for1 = dao.bucarPorCodigo(4L);
		Fornecedor for2 = dao.bucarPorCodigo(7L);

		dao.excluir(for1);

		if (for2 != null) {
			dao.excluir(for2);
		}

		// Fornecedor for1 = new Fornecedor();
		// for1.setCodigo(2L);
		// for1.setNome("Parafusos Atacado ME");
		//
		// FornecedorDao dao = new FornecedorDao();
		// dao.excluir(for1);

	}

	// @Test
	// @Ignore
	// public void excluirPorCodigo() {
	//
	// FornecedorDao dao = new FornecedorDao();
	// dao.excluir(10L);
	//
	// }

	@Test
	@Ignore
	public void editar() {
		
		FornecedorDao dao = new FornecedorDao();
		Fornecedor fornecedor = dao.bucarPorCodigo(7L);
		fornecedor.setNome("Aloisio Atacadao LTDA");
		dao.editar(fornecedor);

		// Fornecedor fornecedor = new Fornecedor();
		// fornecedor.setCodigo(1L);
		// fornecedor.setNome("Atacado Ferramentas BC LTDA");
		//
		// FornecedorDao dao = new FornecedorDao();
		// dao.editar(fornecedor);

	}

}
