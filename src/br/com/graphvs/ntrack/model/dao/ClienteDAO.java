package br.com.graphvs.ntrack.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.graphvs.ntrack.IRestApi;
import br.com.graphvs.ntrack.exceptions.DAOException;
import br.com.graphvs.ntrack.exceptions.ErrorCode;
import br.com.graphvs.ntrack.model.domain.Cliente;
import br.com.graphvs.ntrack.model.domain.ClienteRota;
import br.com.graphvs.ntrack.util.JPAUtil;

public class ClienteDAO implements IRestApi<Cliente> {

	@Override
	public List<Cliente> list() {
		EntityManager em = JPAUtil.getEntityManager();
		List<Cliente> clientes = null;

		try {
			clientes = em.createQuery("select c from Cliente c", Cliente.class).getResultList();
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao recuperar todos os clientes do banco: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}

		return clientes;
	}
	
	@Override
	public Cliente save(Cliente cliente) {
		EntityManager em = JPAUtil.getEntityManager();

		if (!isValid(cliente)) {
			throw new DAOException("Cliente com dados incompletos.", ErrorCode.BAD_REQUEST.getStatusCode());
		}
		
		cliente.setId(null);

		try {
			em.getTransaction().begin();
			em.persist(cliente);
			em.getTransaction().commit();
		} catch (RuntimeException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Erro ao salvar cliente no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}
		return cliente;
	}

	@Override
	public Cliente update(Cliente cliente) {
		EntityManager em = JPAUtil.getEntityManager();
		Cliente clienteManaged = null;

		if (cliente.getId() <= 0) {
			throw new DAOException("O id precisa ser maior do que 0.", ErrorCode.BAD_REQUEST.getStatusCode());
		}
		if (!isValid(cliente)) {
			throw new DAOException("Cliente com dados incompletos.", ErrorCode.BAD_REQUEST.getStatusCode());
		}

		try {
			em.getTransaction().begin();
			clienteManaged = em.find(Cliente.class, cliente.getId());
			clienteManaged.setNome(cliente.getNome());
			clienteManaged.setLatitude(cliente.getLatitude());
			clienteManaged.setLongitude(cliente.getLongitude());
			clienteManaged.setTelefone1(cliente.getTelefone1());
			clienteManaged.setTelefone2(cliente.getTelefone2());
			clienteManaged.setEndereco(cliente.getEndereco());
			em.getTransaction().commit();
		} catch (NullPointerException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Cliente informado para atualização não existe: " + ex.getMessage(),
					ErrorCode.NOT_FOUND.getStatusCode());
		} catch (RuntimeException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Erro ao atualizar cliente no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}
		return clienteManaged;
	}
	
	@Override
	public void delete(Cliente entidade) {
		
		Long id = entidade.getId();
		EntityManager em = JPAUtil.getEntityManager();
		Cliente cliente = null;

		if (id <= 0) {
			throw new DAOException("O id precisa ser maior do que 0.", ErrorCode.BAD_REQUEST.getStatusCode());
		}

		try {
			em.getTransaction().begin();
			cliente = em.find(Cliente.class, id);
			em.remove(cliente);
			em.getTransaction().commit();
		} catch (IllegalArgumentException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Cliente informado para remoção não existe: " + ex.getMessage(),
					ErrorCode.NOT_FOUND.getStatusCode());
		} catch (RuntimeException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Erro ao remover cliente do banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}

	}

	public boolean isValid(Cliente cliente) {
		try {
			if ((cliente.getNome().isEmpty()) || (cliente.getLatitude() == 0) || (cliente.getLongitude() == 0) || (cliente.getTelefone1().isEmpty()) || (cliente.getEndereco().isEmpty()))
				return false;
		} catch (NullPointerException ex) {
			throw new DAOException("Cliente com dados incompletos.", ErrorCode.BAD_REQUEST.getStatusCode());
		}

		return true;
	}
	
	public Cliente getById(long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Cliente cliente = null;

		if (id <= 0) {
			throw new DAOException("O id precisa ser maior do que 0.", ErrorCode.BAD_REQUEST.getStatusCode());
		}

		try {
			cliente = em.find(Cliente.class, id);
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao buscar cliente por id no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}

		if (cliente == null) {
			throw new DAOException("Cliente de id " + id + " não existe.", ErrorCode.NOT_FOUND.getStatusCode());
		}

		return cliente;
	}
	
	


	public List<Cliente> getByPagination(int firstResult, int maxResults) {
		List<Cliente> clientes;
		EntityManager em = JPAUtil.getEntityManager();

		try {
			clientes = em.createQuery("select p from Cliente p", Cliente.class).setFirstResult(firstResult - 1)
					.setMaxResults(maxResults).getResultList();
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao buscar clientes no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}

		if (clientes.isEmpty()) {
			throw new DAOException("Página com clientes vazia.", ErrorCode.NOT_FOUND.getStatusCode());
		}

		return clientes;
	}
	
	public List<Cliente> getByName(String name) {
		EntityManager em = JPAUtil.getEntityManager();
		List<Cliente> clientes = null;

		try {
			clientes = em.createQuery("select p from Cliente p where p.nome like :name", Cliente.class)
					.setParameter("name", "%" + name + "%").getResultList();
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao buscar clientes por nome no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}

		if (clientes.isEmpty()) {
			throw new DAOException("A consulta não encontrou clientes.", ErrorCode.NOT_FOUND.getStatusCode());
		}

		return clientes;
	}
	
	
	public List<ClienteRota> getByIdMotorista(long idMotorista) {
		EntityManager em = JPAUtil.getEntityManager();
		List<ClienteRota> clientesRotas = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT distinct new br.com.graphvs.ntrack.model.domain.ClienteRota(r.idMotorista,r.ordemCliente,");
		sql.append("CASE WHEN a.data is not null THEN true ELSE false END as atendido, ");
		sql.append("CASE WHEN v.data is not null THEN true ELSE false END as visitado, ");	
		sql.append("c.id, c.nome, c.latitude, c.longitude, c.telefone1, c.telefone2, c.endereco) ");
		sql.append("FROM Cliente c ");
		sql.append("INNER JOIN 	Rota        r ON c.id = r.idCliente ");
		sql.append("LEFT  JOIN  Atendimento a ON a.idMotorista = :idMotorista AND c.id = a.idCliente AND DATE(a.data) = curdate() ");
		sql.append("LEFT  JOIN  Visita      v on v.idMotorista = :idMotorista AND c.id = v.idCliente and DATE(v.data) = curdate() ");
		sql.append("WHERE                        r.idMotorista = :idMotorista AND r.data = curdate() ");

		try {
			TypedQuery<ClienteRota> query =  em.createQuery(sql.toString(), ClienteRota.class);
			query.setParameter("idMotorista", idMotorista);
			clientesRotas =  query.getResultList();
			
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao recuperar todos os clientes do banco: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}

		return clientesRotas;
	}
	
	public List<ClienteRota> getByIdMotoristaData(long idMotorista, String data) {
		EntityManager em = JPAUtil.getEntityManager();
		List<ClienteRota> clientesRotas = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT distinct new br.com.graphvs.ntrack.model.domain.ClienteRota(r.idMotorista, r.ordemCliente,");
		sql.append("CASE WHEN a.data is not null THEN true ELSE false END as atendido, ");
		sql.append("CASE WHEN v.data is not null THEN true ELSE false END as visitado, ");	
		sql.append("c.id, c.nome, c.latitude, c.longitude, c.telefone1, c.telefone2, c.endereco) ");
		sql.append("FROM Cliente c ");
		sql.append("INNER JOIN 	Rota        r ON c.id = r.idCliente ");
		sql.append("LEFT  JOIN  Atendimento a ON a.idMotorista = :idMotorista AND c.id = a.idCliente AND DATE(a.data) = '"+data+"' ");
		sql.append("LEFT  JOIN  Visita      v on v.idMotorista = :idMotorista AND c.id = v.idCliente and DATE(v.data) = '"+data+"' ");
		sql.append("WHERE                        r.idMotorista = :idMotorista AND r.data = '"+data+"' ");

		try {
			TypedQuery<ClienteRota> query =  em.createQuery(sql.toString(), ClienteRota.class);
			query.setParameter("idMotorista", idMotorista);
			clientesRotas =  query.getResultList();
			
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao recuperar todos os clientes do banco: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}

		return clientesRotas;
	}

	
	public List<ClienteRota> getByData( String data) {
		EntityManager em = JPAUtil.getEntityManager();
		List<ClienteRota> clientesRotas = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT distinct new br.com.graphvs.ntrack.model.domain.ClienteRota(r.idMotorista,r.ordemCliente,");
		sql.append("CASE WHEN a.data is not null THEN true ELSE false END as atendido, ");
		sql.append("CASE WHEN v.data is not null THEN true ELSE false END as visitado, ");	
		sql.append("c.id, c.nome, c.latitude, c.longitude, c.telefone1, c.telefone2, c.endereco) ");
		sql.append("FROM Cliente c ");
		sql.append("INNER JOIN 	Rota        r ON c.id = r.idCliente ");
		sql.append("LEFT  JOIN  Atendimento a ON a.idMotorista = r.idMotorista AND c.id = a.idCliente AND DATE(a.data) = '"+data+"' ");
		sql.append("LEFT  JOIN  Visita      v on v.idMotorista = r.idMotorista AND c.id = v.idCliente and DATE(v.data) = '"+data+"' ");
		sql.append("WHERE  r.data = '"+data+"' ");

		try {
			TypedQuery<ClienteRota> query =  em.createQuery(sql.toString(), ClienteRota.class);
			clientesRotas =  query.getResultList();
			
		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao recuperar todos os clientes do banco: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}

		return clientesRotas;
	}
	

}
