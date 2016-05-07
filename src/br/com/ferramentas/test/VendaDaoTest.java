package br.com.ferramentas.test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import br.com.ferramentas.dao.FuncionarioDao;
import br.com.ferramentas.dao.VendaDao;
import br.com.ferramentas.domain.Funcionario;
import br.com.ferramentas.domain.Venda;
import br.com.ferramentas.filter.VendaFiltro;

public class VendaDaoTest {

	@Test
	@Ignore
	public void salvar() {

		Venda venda = new Venda();

		FuncionarioDao dao = new FuncionarioDao();
		Funcionario funcionario = dao.bucarPorCodigo(3L);

		venda.setFuncionario(funcionario);
		venda.setHorario(new Date());
		venda.setValorTotal(new BigDecimal(256.21D));

		Venda venda1 = new Venda();

		Funcionario funcionario1 = dao.bucarPorCodigo(1L);

		venda1.setFuncionario(funcionario1);
		venda1.setHorario(new Date());
		venda1.setValorTotal(new BigDecimal(527.84D));

		Venda venda2 = new Venda();

		Funcionario funcionario2 = dao.bucarPorCodigo(3L);

		venda2.setFuncionario(funcionario2);
		venda2.setHorario(new Date());
		venda2.setValorTotal(new BigDecimal(1542.65D));

		VendaDao dao2 = new VendaDao();
		dao2.salvar(venda);
		dao2.salvar(venda2);
		dao2.salvar(venda1);

	}

	@Test
	@Ignore
	public void listar() {

		VendaDao dao = new VendaDao();
		List<Venda> vendas = dao.listar();

		for (Venda venda : vendas) {

			System.out.println(venda);

		}
	}

	@Test
	@Ignore
	public void buscarPorCodigo() {

		VendaDao dao = new VendaDao();
		Venda venda = dao.bucarPorCodigo(8L);
		System.out.println(venda);
	}

	@Test
	@Ignore
	public void excluir() {

		VendaDao dao = new VendaDao();
		Venda venda = dao.bucarPorCodigo(5L);
		dao.excluir(venda);

	}

	@Test
	@Ignore
	public void editar() {
		
		VendaDao dao = new VendaDao();
		Venda venda = dao.bucarPorCodigo(6L);
		
		FuncionarioDao dao1 =  new FuncionarioDao();
		Funcionario funcionario = dao1.bucarPorCodigo(1L);
		
		venda.setFuncionario(funcionario);
		venda.setHorario(Calendar.getInstance().getTime());
		//venda.setHorario(new Date());
		venda.setValorTotal(new BigDecimal(784.10D));
		
		dao.editar(venda);
		
		

	}
	
	@Test
	@Ignore
	public void buscarDate() throws java.text.ParseException{
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		VendaFiltro filtro = new VendaFiltro();
		filtro.setDataInicio(formato.parse("01/05/2016"));
		filtro.setDataFim(formato.parse("06/05/2016"));
		
		VendaDao vendaDao = new VendaDao();
		List<Venda> vendas = vendaDao.buscarData(filtro);
		
		for(Venda venda : vendas){
			System.out.println(venda);
		}
		
		
	}

}
