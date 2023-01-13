package br.com.graphvs.ntrack.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.graphvs.ntrack.IRestApi;
import br.com.graphvs.ntrack.exceptions.DAOException;
import br.com.graphvs.ntrack.exceptions.ErrorCode;
import br.com.graphvs.ntrack.model.domain.Rastreamento;
import br.com.graphvs.ntrack.util.JPAUtil;

public class RastreamentoDAO implements IRestApi<Rastreamento> {

	@Override
	public List<Rastreamento> list() {
		EntityManager em = JPAUtil.getEntityManager();
		List<Rastreamento> rastreamentos = null;

		try {
			rastreamentos = em.createQuery("select r from Rastreamento r", Rastreamento.class).getResultList();
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao recuperar todos os rastreamentos do banco: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}

		return rastreamentos;
	}

	public List<Rastreamento> list(int idMotorista, String data) {
		EntityManager em = JPAUtil.getEntityManager();
		List<Rastreamento> rastreamentos = null;

		try {
			rastreamentos = em.createQuery(
					"SELECT r FROM Rastreamento r	WHERE idMotorista = " + idMotorista + " and data LIKE '" + data + "%'",
					Rastreamento.class).getResultList();
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao recuperar todos os rastreamentos do banco: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}

		return rastreamentos;
	}

	// SELECT * FROM rastreamento WHERE idmotorista = ? and data LIKE ?

	@Override
	public Rastreamento save(Rastreamento entidade) {
		entidade.setId(null);
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(entidade);
			em.getTransaction().commit();
		} catch (RuntimeException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Erro ao salvar Rastreamento no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}
		return entidade;
	}

	@Override
	public Rastreamento update(Rastreamento entidade) {
		// TODO Auto-generated method stub
		throw new DAOException("Não implementado.", ErrorCode.SERVER_ERROR.getStatusCode());
	}

	@Override
	public void delete(Rastreamento entidade) {
		// TODO Auto-generated method stub
		throw new DAOException("Não implementado.", ErrorCode.SERVER_ERROR.getStatusCode());
	}

	public boolean isValid(Rastreamento entidade) {
		// TODO Auto-generated method stub
		throw new DAOException("Não implementado.", ErrorCode.SERVER_ERROR.getStatusCode());
	}

}
