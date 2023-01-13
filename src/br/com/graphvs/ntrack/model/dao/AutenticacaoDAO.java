package br.com.graphvs.ntrack.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.graphvs.ntrack.exceptions.DAOException;
import br.com.graphvs.ntrack.exceptions.ErrorCode;
import br.com.graphvs.ntrack.model.domain.Autenticacao;
import br.com.graphvs.ntrack.util.JPAUtil;

public class AutenticacaoDAO {

	public Autenticacao get(Autenticacao autenticacao) {
		EntityManager em = JPAUtil.getEntityManager();
		List<Autenticacao> autenticacoes = null;
		if (!autenticacaoIsValid(autenticacao)) {
			throw new DAOException("Autenticacao com dados incompletos.", ErrorCode.BAD_REQUEST.getStatusCode());
		}

		try {
			autenticacoes = em
					.createQuery("select p from Autenticacao p where p.login like :name and p.senha like :senha",
							Autenticacao.class)
					.setParameter("name", autenticacao.getLogin()).setParameter("senha", autenticacao.getSenha())
					.getResultList();

		} catch (RuntimeException ex) {
			throw new DAOException("Erro ao buscar clientes por nome no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}

		if (autenticacoes.isEmpty()) {
			throw new DAOException("Acesso negado.", ErrorCode.UNAUTHORIZED.getStatusCode());
		}
		return autenticacoes.get(0);
	}

	public Autenticacao update(Autenticacao autenticacao) {
		EntityManager em = JPAUtil.getEntityManager();
		Autenticacao autenticacaoManaged = null;

		if (autenticacao.getId() <= 0) {
			throw new DAOException("O id precisa ser maior do que 0.", ErrorCode.BAD_REQUEST.getStatusCode());
		}

		if (!autenticacaoIsValid(autenticacao)) {
			throw new DAOException("Motorista com dados incompletos.", ErrorCode.BAD_REQUEST.getStatusCode());
		}

		try {
			em.getTransaction().begin();
			autenticacaoManaged = em.find(Autenticacao.class, autenticacao.getId());
			autenticacaoManaged.setLogin(autenticacao.getLogin());
			autenticacaoManaged.setSenha(autenticacao.getSenha());
			autenticacaoManaged.setToken(autenticacao.getToken());
			autenticacaoManaged.setValidade(autenticacao.getValidade());
			autenticacaoManaged.setIdGestor(autenticacao.getIdGestor());
			autenticacaoManaged.setIdMotorista(autenticacao.getIdMotorista());
			em.getTransaction().commit();
		} catch (NullPointerException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Autenticação informada não existe: " + ex.getMessage(),
					ErrorCode.NOT_FOUND.getStatusCode());
		} catch (RuntimeException ex) {
			em.getTransaction().rollback();
			throw new DAOException("Erro ao atualizar autenticação no banco de dados: " + ex.getMessage(),
					ErrorCode.SERVER_ERROR.getStatusCode());
		} finally {
			em.close();
		}
		
		return autenticacao;
	}

	private boolean autenticacaoIsValid(Autenticacao autenticacao) {
		try {
			if ((autenticacao.getLogin().isEmpty()) || (autenticacao.getSenha().isEmpty()))
				return false;
		} catch (NullPointerException ex) {
			throw new DAOException("Autenticacao com dados incompletos.", ErrorCode.BAD_REQUEST.getStatusCode());
		}

		return true;
	}

}
