package br.com.graphvs.ntrack.service;

import java.util.List;

import br.com.graphvs.ntrack.IRestApi;
import br.com.graphvs.ntrack.model.dao.RotaDAO;
import br.com.graphvs.ntrack.model.domain.Rota;


public class RotaService implements IRestApi<Rota>{
private RotaDAO dao = new RotaDAO();
	
	@Override
	public List<Rota> list() {
		// TODO Auto-generated method stub
		List<Rota> rotas = dao.list();
		return rotas;
	}

	@Override
	public Rota save(Rota entidade) {
		// TODO Auto-generated method stub
		return dao.save(entidade);
	}

	@Override
	public Rota update(Rota entidade) {
		// TODO Auto-generated method stub
		return dao.update(entidade);
	}

	@Override
	public void delete(Rota entidade) {
		// TODO Auto-generated method stub
		 dao.delete(entidade);
	}
	
	public List<Rota> list(int idMotorista) {
		// TODO Auto-generated method stub
		return dao.list(idMotorista);
	}
	
	public List<Rota> list(int idMotorista, String data) {
		// TODO Auto-generated method stub
		return dao.list(idMotorista, data);
	}


}
