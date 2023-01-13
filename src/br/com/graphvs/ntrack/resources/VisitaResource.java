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
import br.com.graphvs.ntrack.model.domain.Visita;
import br.com.graphvs.ntrack.service.VisitaService;

@Path("/visita")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class VisitaResource implements IRestApi<Visita>{ 
	
	private VisitaService service = new VisitaService();
	
	
	@GET
	@Override
	public List<Visita> list() {
		// TODO Auto-generated method stub
		return service.list();
		}

	@POST
	@Override
	public Visita save(Visita entidade) {
		// TODO Auto-generated method stub
		return service.save(entidade);
	}

	@PUT
	@Override
	public Visita update(Visita entidade) {
		// TODO Auto-generated method stub
		return service.update(entidade);
	}

	@DELETE
	@Override
	public void delete(Visita entidade) {
		// TODO Auto-generated method stub
		service.delete(entidade);
		
	}
	
	
	
	@GET
	@Path("/data/{data}/")
	public List<Visita> list(@PathParam("data") String data) {
		// TODO Auto-generated method stub
		return service.list(data);
		}
	
	

}