package br.com.ferramentas.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.ferramentas.domain.Fornecedor;
import br.com.ferramentas.util.HibernateUtil;

public class FornecedorDao {

	public void salvar(Fornecedor fornecedor) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		sessao.save(fornecedor);
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(fornecedor);
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
	public List<Fornecedor> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Fornecedor> fornecedors = null;

		try {
			Query consulta = sessao.getNamedQuery("Fornecedor.listar");
			fornecedors = consulta.list();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return fornecedors;

	}

	public Fornecedor bucarPorCodigo(Long codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Fornecedor fornecedor = null;

		try {
			Query consulta = sessao.getNamedQuery("Fornecedor.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			fornecedor = (Fornecedor) consulta.uniqueResult();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return fornecedor;

	}

	public void excluir(Fornecedor fornecedor) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(fornecedor);
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

	// public void excluir(Long codigo) {
	//
	// Session sessao = HibernateUtil.getSessionFactory().openSession();
	// Transaction transacao = null;
	//
	// try {
	// transacao = sessao.beginTransaction();
	// Fornecedor fornecedor = bucarPorCodigo(codigo);
	// sessao.delete(fornecedor);
	// transacao.commit();
	// } catch (RuntimeException ex) {
	// if (transacao != null) {
	// transacao.rollback();
	// }
	// throw ex;
	// } finally {
	// sessao.close();
	// }
	// }

	public void editar(Fornecedor fornecedor) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			// Fornecedor fornecedorEditar =
			// bucarPorCodigo(fornecedor.getCodigo());
			// fornecedorEditar.setNome(fornecedor.getNome());
			// sessao.update(fornecedorEditar);

			sessao.update(fornecedor);
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

}
