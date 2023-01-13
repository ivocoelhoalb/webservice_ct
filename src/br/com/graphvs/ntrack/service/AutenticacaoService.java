package br.com.graphvs.ntrack.service;

import br.com.graphvs.ntrack.model.dao.AutenticacaoDAO;
import br.com.graphvs.ntrack.model.domain.Autenticacao;

public class AutenticacaoService {
	private AutenticacaoDAO dao = new AutenticacaoDAO();

	public Autenticacao getAutenticacao(Autenticacao autenticacao) {
		return dao.get(autenticacao);
	}
	
	public Autenticacao updateAutenticacao(Autenticacao autenticacao) {
		return dao.update(autenticacao);
	}

}
