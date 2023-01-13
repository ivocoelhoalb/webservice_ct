package br.com.graphvs.ntrack.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.graphvs.ntrack.IRestApi;
import br.com.graphvs.ntrack.exceptions.DAOException;
import br.com.graphvs.ntrack.exceptions.ErrorCode;
import br.com.graphvs.ntrack.model.domain.Visita;
import br.com.graphvs.ntrack.util.JPAUtil;

public class VisitaDAO implements IRestApi<Visita> {

	@Override
	public List<Visita> list() {
		EntityManager em = JPAUtil.getEntityManager();
		List<Visita> clientesVisitas = null;

		try {
			clientesVisitas = em.createQuery("select v from Visita v", Visita.class).getResultList();
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao recuperar todos os Visita do banco: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}

		return clientesVisitas;
	}

	@Override
	public Visita save(Visita entidade) {

		entidade.setId(null);

		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(entidade);
			em.getTransaction().commit();
		} catch (RuntimeException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Erro ao salvar Visita no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}
		return entidade;
	}

	@Override
	public Visita update(Visita entidade) {
		// TODO Auto-generated method stub
		throw new DAOException("Não implementado.", ErrorCode.SERVER_ERROR.getStatusCode());
	}

	@Override
	public void delete(Visita entidade) {
		// TODO Auto-generated method stub
		throw new DAOException("Não implementado.", ErrorCode.SERVER_ERROR.getStatusCode());
	}

	public boolean isValid(Visita entidade) {
		// TODO Auto-generated method stub
		throw new DAOException("Não implementado.", ErrorCode.SERVER_ERROR.getStatusCode());
	}

	public List<Visita> list(String data) {
		EntityManager em = JPAUtil.getEntityManager();
		List<Visita> clientesVisitas = null;

		try {
			clientesVisitas = em.createQuery("select v from Visita v WHERE  date(data) = '" + data + "'", Visita.class)
					.getResultList();
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao recuperar todos os Visita do banco: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}

		return clientesVisitas;
	}

}
