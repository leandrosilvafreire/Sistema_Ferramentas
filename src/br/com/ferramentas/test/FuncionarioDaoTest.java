package br.com.ferramentas.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import br.com.ferramentas.dao.FuncionarioDao;
import br.com.ferramentas.domain.Funcionario;

public class FuncionarioDaoTest {

	@Ignore
	@Test
	public void salvar() {

		Funcionario fun1 = new Funcionario();

		fun1.setCpf("12345678912");
		fun1.setNome("Ricardo Augusto Cesar");
		fun1.setFuncao("Vendedor");
		fun1.setSenha("123qwe789");

		Funcionario fun2 = new Funcionario();

		fun2.setCpf("25678941526");
		fun2.setNome("Maria Aparecida Ribeiro");
		fun2.setFuncao("Gerente");
		fun2.setSenha("4189652");

		Funcionario fun3 = new Funcionario();

		fun3.setCpf("25674115210");
		fun3.setNome("Marcelo Costa Santos");
		fun3.setFuncao("Proprietário");
		fun3.setSenha("ertgfdvc");

		FuncionarioDao dao = new FuncionarioDao();

		dao.salvar(fun1);
		dao.salvar(fun2);
		dao.salvar(fun3);

	}

	@Ignore
	@Test
	public void listar() {

		FuncionarioDao dao = new FuncionarioDao();
		List<Funcionario> funcionarios = dao.listar();

		System.out.println(funcionarios);

	}

	@Test
	@Ignore
	public void buscarPorCodigo() {

		FuncionarioDao dao = new FuncionarioDao();
		Funcionario fun1 = dao.bucarPorCodigo(2L);
		Funcionario fun2 = dao.bucarPorCodigo(9L);

		System.out.println(fun1);
		System.out.println(fun2);
	}

	@Test
	@Ignore
	public void excluir() {

		FuncionarioDao dao = new FuncionarioDao();
		Funcionario fun1 = dao.bucarPorCodigo(2L);

		dao.excluir(fun1);

	}

	@Test
	@Ignore
	public void editar() {

		FuncionarioDao dao = new FuncionarioDao();
		Funcionario funcionario = dao.bucarPorCodigo(1L);
		funcionario.setNome("José Alexandre Pereira");
		funcionario.setCpf("45678912021");
		funcionario.setFuncao("Gerente");
		funcionario.setSenha("741hghg8120");
		dao.editar(funcionario);

	}

	@Test
	@Ignore
	public void autenticar() {
		FuncionarioDao funcionarioDao = new FuncionarioDao();
		Funcionario funcionario = funcionarioDao.logar("125.452.125-74", "789123");
		// System.out.println("Funcionario: " + funcionario);
		// Teste para ver se o funcionário é nulo
		Assert.assertNotNull(funcionario);
	}

}
