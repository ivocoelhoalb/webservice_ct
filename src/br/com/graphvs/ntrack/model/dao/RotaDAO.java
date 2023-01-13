package br.com.graphvs.ntrack.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.graphvs.ntrack.IRestApi;
import br.com.graphvs.ntrack.exceptions.DAOException;
import br.com.graphvs.ntrack.exceptions.ErrorCode;
import br.com.graphvs.ntrack.model.domain.Rota;
import br.com.graphvs.ntrack.util.JPAUtil;

public class RotaDAO implements IRestApi<Rota>{
	
	@Override
	public List<Rota> list() {
		EntityManager em = JPAUtil.getEntityManager();
		List<Rota> rotas = null;

		try {
			rotas = em.createQuery("select r from Rota r", Rota.class).getResultList();
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao recuperar todos os rotas do banco: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}

		return rotas;
	}

	@Override
	public Rota save(Rota entidade) {
		
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(entidade);
			em.getTransaction().commit();
		} catch (RuntimeException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Erro ao salvar Rota no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}
		return entidade;
	}

	@Override
	public Rota update(Rota entidade) {
		// TODO Auto-generated method stub
		throw new DAOException("Não implementado.", ErrorCode.SERVER_ERROR.getStatusCode());
	}

	@Override
	public void delete(Rota entidade) {
		// TODO Auto-generated method stub
		throw new DAOException("Não implementado.", ErrorCode.SERVER_ERROR.getStatusCode());
	}

	public boolean isValid(Rota entidade) {
		// TODO Auto-generated method stub
		throw new DAOException("Não implementado.", ErrorCode.SERVER_ERROR.getStatusCode());
	}
	
	public List<Rota> list(int idMotorista) {
		EntityManager em = JPAUtil.getEntityManager();
		List<Rota> rotas = null;

		try {
			rotas = em.createQuery("select r from Rota r where DATE(r.data) = curdate() and idMotorista = "+ idMotorista, Rota.class).getResultList();
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao recuperar todos os rotas do banco: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}

		return rotas;
	}
	
	
	
	public List<Rota> list(int idMotorista, String data) {
		EntityManager em = JPAUtil.getEntityManager();
		List<Rota> rotas = null;

		try {
			rotas = em.createQuery("select r from Rota r where DATE(r.data) = '"+data+"' and idMotorista = "+ idMotorista, Rota.class).getResultList();
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao recuperar todos os rotas do banco: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}

		return rotas;
	}

}
