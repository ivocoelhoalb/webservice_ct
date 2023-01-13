package br.com.graphvs.ntrack.service;

import java.util.List;

import br.com.graphvs.ntrack.IRestApi;
import br.com.graphvs.ntrack.model.dao.VisitaDAO;
import br.com.graphvs.ntrack.model.domain.Visita;


public class VisitaService implements IRestApi<Visita>{
private VisitaDAO dao = new VisitaDAO();
	
	@Override
	public List<Visita> list() {
		// TODO Auto-generated method stub
		return dao.list();
	}

	@Override
	public Visita save(Visita entidade) {
		// TODO Auto-generated method stub
		return dao.save(entidade);
	}

	@Override
	public Visita update(Visita entidade) {
		// TODO Auto-generated method stub
		return dao.update(entidade);
	}

	@Override
	public void delete(Visita entidade) {
		// TODO Auto-generated method stub
		 dao.delete(entidade);
	}
	
	public List<Visita> list(String data) {
		return dao.list(data);
	}

}
