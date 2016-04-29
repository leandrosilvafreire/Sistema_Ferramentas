package br.com.ferramentas.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent var) {
		HibernateUtil.getSessionFactory().close();

	}

	@Override
	public void contextInitialized(ServletContextEvent var) {
		HibernateUtil.getSessionFactory().openSession();

	}

}