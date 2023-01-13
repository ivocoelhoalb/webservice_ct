package br.com.graphvs.ntrack.service;

import java.util.List;

import br.com.graphvs.ntrack.IRestApi;
import br.com.graphvs.ntrack.model.dao.MotoristaDAO;
import br.com.graphvs.ntrack.model.domain.Motorista;


public class MotoristaService implements IRestApi<Motorista>{
private MotoristaDAO dao = new MotoristaDAO();
	
	@Override
	public List<Motorista> list() {
		return dao.list();
	}
	
	public Motorista getMotorista(Long id) {
		return dao.getById(id);
	}
	
	@Override
	public Motorista save(Motorista motorista) {
		return dao.save(motorista);
	}
	
	@Override
	public Motorista update(Motorista mototista) {
		return dao.update(mototista);
	}
	
	@Override
	public void delete(Motorista motorista) {
		 dao.delete(motorista);
	}
	
	public List<Motorista> getMotoristasByPagination(int firstResult, int maxResults) {
		return dao.getByPagination(firstResult, maxResults);
	}
	
	public List<Motorista> getMotoristaByName(String name) {
		return dao.getByName(name);
	}
	
	public List<Motorista> getMotoristaComRota(String data) {
		return dao.getMotoristasComRota(data);
	}
	
	public Motorista updateRastreamento(Motorista mototista) {
		return dao.updateRastreamento(mototista);
	}

	

}
