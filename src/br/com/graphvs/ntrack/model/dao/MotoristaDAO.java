package br.com.graphvs.ntrack.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.graphvs.ntrack.IRestApi;
import br.com.graphvs.ntrack.exceptions.DAOException;
import br.com.graphvs.ntrack.exceptions.ErrorCode;
import br.com.graphvs.ntrack.model.domain.Motorista;
import br.com.graphvs.ntrack.util.JPAUtil;

public class MotoristaDAO implements IRestApi<Motorista> {

	@Override
	public List<Motorista> list() {
		EntityManager em = JPAUtil.getEntityManager();
		List<Motorista> motoristas = null;

		try {
			motoristas = em.createQuery("select p from Motorista p", Motorista.class).getResultList();
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao recuperar todos os motoristas do banco: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}

		return motoristas;
	}

	@Override
	public Motorista save(Motorista motorista) {
		EntityManager em = JPAUtil.getEntityManager();

		if (!isValid(motorista)) {
			throw new DAOException("Motorista com dados incompletos.", ErrorCode.BAD_REQUEST.getStatusCode());
		}

		try {
			em.getTransaction().begin();
			em.persist(motorista);
			em.getTransaction().commit();
		} catch (RuntimeException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Erro ao salvar motorista no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}
		return motorista;
	}

	@Override
	public Motorista update(Motorista motorista) {
		EntityManager em = JPAUtil.getEntityManager();
		Motorista motoristaManaged = null;

		if (motorista.getId() <= 0) {
			throw new DAOException("O id precisa ser maior do que 0.", ErrorCode.BAD_REQUEST.getStatusCode());
		}
		if (!isValid(motorista)) {
			throw new DAOException("Motorista com dados incompletos.", ErrorCode.BAD_REQUEST.getStatusCode());
		}

		try {
			em.getTransaction().begin();
			motoristaManaged = em.find(Motorista.class, motorista.getId());
			motoristaManaged.setNome(motorista.getNome());
			motoristaManaged.setLatitude(motorista.getLatitude());
			motoristaManaged.setLongitude(motorista.getLongitude());
			motoristaManaged.setData(motorista.getData());
			motoristaManaged.setClientes(motorista.getClientes());
			em.getTransaction().commit();
		} catch (NullPointerException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Motorista informado para atualização não existe: " + ex.getMessage(),
					ErrorCode.NOT_FOUND.getStatusCode());
		} catch (RuntimeException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Erro ao atualizar motorista no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}
		return motoristaManaged;
	}

	@Override
	public void delete(Motorista motoristaEntidade) {
		Long id = motoristaEntidade.getId();
		EntityManager em = JPAUtil.getEntityManager();
		Motorista motorista = null;

		if (id <= 0) {
			throw new DAOException("O id precisa ser maior do que 0.", ErrorCode.BAD_REQUEST.getStatusCode());
		}

		try {
			em.getTransaction().begin();
			motorista = em.find(Motorista.class, id);
			em.remove(motorista);
			em.getTransaction().commit();
		} catch (IllegalArgumentException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Motorista informado para remoção não existe: " + ex.getMessage(),
					ErrorCode.NOT_FOUND.getStatusCode());
		} catch (RuntimeException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Erro ao remover motorista do banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}

	}

	public boolean isValid(Motorista motorista) {
		try {
			if ((motorista.getNome().isEmpty()) || (motorista.getLatitude() < 0) || (motorista.getLongitude() < 0)
					|| (motorista.getData().isEmpty()))
				return false;
		} catch (NullPointerException ex) {
			throw new DAOException("Motorista com dados incompletos.", ErrorCode.BAD_REQUEST.getStatusCode());
		}

		return true;
	}

	public Motorista getById(long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Motorista motorista = null;

		if (id <= 0) {
			throw new DAOException("O id precisa ser maior do que 0.", ErrorCode.BAD_REQUEST.getStatusCode());
		}

		try {
			motorista = em.find(Motorista.class, id);
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao buscar motorista por id no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}

		if (motorista == null) {
			throw new DAOException("Motorista de id " + id + " não existe.", ErrorCode.NOT_FOUND.getStatusCode());
		}

		return motorista;
	}

	public List<Motorista> getMotoristasComRota(String data) {
		EntityManager em = JPAUtil.getEntityManager();
		List<Motorista> motoristas = null;
		
		
		//Long id, String nome, double latitude, double longitude, String data, int clientes
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  new br.com.graphvs.ntrack.model.domain.Motorista(m.id, m.nome, m.latitude, m.longitude, m.data, count(r.data)) ");
		sql.append("FROM Motorista m ");
		sql.append("INNER JOIN Rota r ON r.idMotorista = m.id AND r.data = :data ");
		sql.append("group by m.id ");
		sql.append("");
		sql.append("");
		sql.append("");

	
		
		
		//String sql = "select m.*, count(r.data) as CLIENTES from Motorista m inner join rota r on r.idMotorista = m.id and r.data = :data group by m.id";
		try {
			
			TypedQuery<Motorista> query =  em.createQuery(sql.toString(), Motorista.class);
			query.setParameter("data", data);
			motoristas =  query.getResultList();
			
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao buscar motoristas por nome no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}

//		if (motoristas.isEmpty()) {
//			throw new DAOException("A consulta não encontrou motoristas.", ErrorCode.NOT_FOUND.getStatusCode());
//		}

		return motoristas;

	}

	public List<Motorista> getByPagination(int firstResult, int maxResults) {
		List<Motorista> motoristas;
		EntityManager em = JPAUtil.getEntityManager();

		try {
			motoristas = em.createQuery("select p from Motorista p", Motorista.class).setFirstResult(firstResult - 1)
					.setMaxResults(maxResults).getResultList();
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao buscar motoristas no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}

//		if (motoristas.isEmpty()) {
//			throw new DAOException("Página com motoristas vazia.", ErrorCode.NOT_FOUND.getStatusCode());
//		}

		return motoristas;
	}

	public List<Motorista> getByName(String name) {
		EntityManager em = JPAUtil.getEntityManager();
		List<Motorista> motoristas = null;

		try {
			motoristas = em.createQuery("select p from Motorista p where p.nome like :name", Motorista.class)
					.setParameter("name", "%" + name + "%").getResultList();
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao buscar motoristas por nome no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}

//		if (motoristas.isEmpty()) {
//			throw new DAOException("A consulta não encontrou motoristas.", ErrorCode.NOT_FOUND.getStatusCode());
//		}

		return motoristas;
	}
	
	public Motorista updateRastreamento(Motorista motorista) {
		EntityManager em = JPAUtil.getEntityManager();
		Motorista motoristaManaged = null;

		if (motorista.getId() <= 0) {
			throw new DAOException("O id precisa ser maior do que 0.", ErrorCode.BAD_REQUEST.getStatusCode());
		}
		

		try {
			em.getTransaction().begin();
			motoristaManaged = em.find(Motorista.class, motorista.getId());
			motoristaManaged.setLatitude(motorista.getLatitude());
			motoristaManaged.setLongitude(motorista.getLongitude());
			motoristaManaged.setData(motorista.getData());
			
			em.getTransaction().commit();
		} catch (NullPointerException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Motorista informado para atualização não existe: " + ex.getMessage(),
					ErrorCode.NOT_FOUND.getStatusCode());
		} catch (RuntimeException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Erro ao atualizar motorista no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}
		return motoristaManaged;
	}

}
