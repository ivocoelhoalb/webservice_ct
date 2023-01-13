package br.com.graphvs.ntrack.service;

import java.util.List;

import br.com.graphvs.ntrack.IRestApi;
import br.com.graphvs.ntrack.model.dao.GestorDAO;
import br.com.graphvs.ntrack.model.domain.Gestor;


public class GestorService implements IRestApi<Gestor>{
private GestorDAO dao = new GestorDAO();
	
	@Override
	public List<Gestor> list() {
		return dao.list();
	}
	
	
	@Override
	public Gestor save(Gestor motorista) {
		return dao.save(motorista);
	}
	
	@Override
	public Gestor update(Gestor mototista) {
		return dao.update(mototista);
	}
	
	@Override
	public void delete(Gestor motorista) {
		 dao.delete(motorista);
	}
	
}
