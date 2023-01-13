package br.com.graphvs.ntrack.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.graphvs.ntrack.IRestApi;
import br.com.graphvs.ntrack.exceptions.DAOException;
import br.com.graphvs.ntrack.exceptions.ErrorCode;
import br.com.graphvs.ntrack.model.domain.Atendimento;
import br.com.graphvs.ntrack.util.JPAUtil;

public class AtendimentoDAO implements IRestApi<Atendimento>{
	
	@Override
	public List<Atendimento> list() {
		EntityManager em = JPAUtil.getEntityManager();
		List<Atendimento> atendimento = null;

		try {
			atendimento = em.createQuery("select a from Atendimento a", Atendimento.class).getResultList();
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao recuperar todos os atendimento do banco: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}

		return atendimento;
	}

	@Override
	public Atendimento save(Atendimento entidade) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(entidade);
			em.getTransaction().commit();
		} catch (RuntimeException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Erro ao salvar Atendimento no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}
		return entidade;
	}

	@Override
	public Atendimento update(Atendimento entidade) {
		// TODO Auto-generated method stub
		throw new DAOException("Não implementado.", ErrorCode.SERVER_ERROR.getStatusCode());
	}

	@Override
	public void delete(Atendimento entidade) {
		// TODO Auto-generated method stub
		throw new DAOException("Não implementado.", ErrorCode.SERVER_ERROR.getStatusCode());
	}

	public boolean isValid(Atendimento entidade) {
		// TODO Auto-generated method stub
		throw new DAOException("Não implementado.", ErrorCode.SERVER_ERROR.getStatusCode());
	}
	
	public List<Atendimento> list(String data) {
		EntityManager em = JPAUtil.getEntityManager();
		List<Atendimento> atendimento = null;

		try {
			atendimento = em.createQuery("select a from Atendimento a", Atendimento.class).getResultList();
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao recuperar todos os atendimento do banco: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}

		return atendimento;
	}

	

}
