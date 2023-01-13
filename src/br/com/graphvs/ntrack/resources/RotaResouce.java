package br.com.graphvs.ntrack.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.graphvs.ntrack.IRestApi;
import br.com.graphvs.ntrack.model.domain.Rota;
import br.com.graphvs.ntrack.service.RotaService;




@Path("/rota")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class RotaResouce  implements IRestApi<Rota> {
	
	private RotaService service = new RotaService();


	@GET
	@Override
	public List<Rota> list() {
		// TODO Auto-generated method stub
		List<Rota> rotas = service.list();
		return rotas;
	}

	@POST
	@Override
	public Rota save(Rota entidade) {
		// TODO Auto-generated method stub
		return service.save(entidade);
	}

	@PUT
	@Override
	public Rota update(Rota entidade) {
		// TODO Auto-generated method stub
		return service.update(entidade);
	}

	@DELETE
	@Override
	public void delete(Rota entidade) {
		// TODO Auto-generated method stub
		service.delete(entidade);
		
	}
	
	
	@GET
	@Path("/motorista/{id}/")
	public List<Rota> buscarPorId(@PathParam("id") int idMotorista) {
		return service.list(idMotorista);
	}
	
	@GET
	@Path("/cliente/{id}/{data}")
	public List<Rota> buscarPorIdData(@PathParam("id") int idMotorista, @PathParam("data") String data) {
		return service.list(idMotorista, data);
	}


}
