package br.com.ferramentas.test;

import org.junit.Test;

import br.com.ferramentas.util.HibernateUtil;

public class GerarTabelasTeste {

	@Test
	public void gerar() {
		HibernateUtil.getSessionFactory();
		HibernateUtil.getSessionFactory().close();
	}
}
