package br.com.graphvs.ntrack.service;

import java.util.List;

import br.com.graphvs.ntrack.IRestApi;
import br.com.graphvs.ntrack.model.dao.AtendimentoDAO;
import br.com.graphvs.ntrack.model.domain.Atendimento;


public class AtendimentoService implements IRestApi<Atendimento>{
private AtendimentoDAO dao = new AtendimentoDAO();

	@Override
	public List<Atendimento> list() {
		// TODO Auto-generated method stub
		return dao.list();
	}

	@Override
	public Atendimento save(Atendimento entidade) {
		// TODO Auto-generated method stub
		return dao.save(entidade);
	}

	@Override
	public Atendimento update(Atendimento entidade) {
		// TODO Auto-generated method stub
		return dao.update(entidade);
	}

	@Override
	public void delete(Atendimento entidade) {
		// TODO Auto-generated method stub
		 dao.delete(entidade);
	}
	
	public List<Atendimento> list(String data) {
		// TODO Auto-generated method stub
		return dao.list(data);
	}

}
