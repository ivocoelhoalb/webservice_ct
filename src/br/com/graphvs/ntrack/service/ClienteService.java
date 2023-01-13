package br.com.graphvs.ntrack.service;

import java.util.List;

import br.com.graphvs.ntrack.IRestApi;
import br.com.graphvs.ntrack.model.dao.ClienteDAO;
import br.com.graphvs.ntrack.model.domain.Cliente;
import br.com.graphvs.ntrack.model.domain.ClienteRota;


public class ClienteService implements IRestApi<Cliente>{
private ClienteDAO dao = new ClienteDAO();
	@Override
	public List<Cliente> list() {
		return dao.list();
	}
	@Override
	public Cliente save(Cliente entidade) {
		// TODO Auto-generated method stub
		return dao.save(entidade);
	}

	@Override
	public Cliente update(Cliente entidade) {
		// TODO Auto-generated method stub
		return dao.update(entidade);
	}

	@Override
	public void delete(Cliente entidade) {
		// TODO Auto-generated method stub
		 dao.update(entidade);
	}
	
	
	public List<ClienteRota> getClienteIdMotorista(Long id) {
		return dao.getByIdMotorista(id);
	}
	
	public List<ClienteRota> getClienteIdMotoristaData(Long id, String data) {
		return dao.getByIdMotoristaData(id,data);
	}
	
	public List<ClienteRota> getClienteData(String data) {
		return dao.getByData(data);
	}
	
	public List<Cliente> getClientesByPagination(int firstResult, int maxResults) {
		return dao.getByPagination(firstResult, maxResults);
	}
	
	public List<Cliente> getClienteByName(String name) {
		return dao.getByName(name);
	}

	


	

}
