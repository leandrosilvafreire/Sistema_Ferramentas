package br.com.ferramentas.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.ferramentas.domain.Venda;
import br.com.ferramentas.filter.VendaFiltro;
import br.com.ferramentas.util.HibernateUtil;

public class VendaDao {

	public Long salvar(Venda venda) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		Long codigo = null;

		try {
			transacao = sessao.beginTransaction();
			codigo = (Long) sessao.save(venda);
			transacao.commit();
		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}
		return codigo;

	}

	@SuppressWarnings("unchecked")
	public List<Venda> listar() {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Venda> vendas = null;

		try {
			Query consulta = sessao.getNamedQuery("Venda.listar");
			vendas = consulta.list();

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return vendas;
	}

	public Venda bucarPorCodigo(Long codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Venda venda = null;

		try {
			Query consulta = sessao.getNamedQuery("Venda.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			venda = (Venda) consulta.uniqueResult();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return venda;

	}

	public void excluir(Venda venda) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(venda);
			transacao.commit();
		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}

	}

	public void editar(Venda venda) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(venda);
			transacao.commit();
		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}

	}

	@SuppressWarnings("unchecked")
	public List<Venda> buscarData(VendaFiltro filtro) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		List<Venda> vendas = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select venda from Venda venda ");

		if (filtro.getDataInicio() != null && filtro.getDataFim() != null) {
			sql.append("where venda.horario between :dataInicio and :dataFim ");
		}

		sql.append("order by venda.horario ");

		try {
			Query consulta = sessao.createQuery(sql.toString());

			if (filtro.getDataInicio() != null && filtro.getDataFim() != null) {
				consulta.setDate("dataInicio", filtro.getDataInicio());
				consulta.setDate("dataFim", filtro.getDataFim());
			}

			vendas = consulta.list();

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}

		return vendas;
	}

}
