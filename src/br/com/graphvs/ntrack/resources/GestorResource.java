package br.com.graphvs.ntrack.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.graphvs.ntrack.IRestApi;
import br.com.graphvs.ntrack.model.domain.Gestor;
import br.com.graphvs.ntrack.service.GestorService;

@Path("/gestor")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class GestorResource implements IRestApi<Gestor> {

	private GestorService service = new GestorService();

	@GET
	@Override
	public List<Gestor> list() {
		return service.list();
	}

	@POST
	@Override
	public Gestor save(Gestor gestor) {
		gestor = service.save(gestor);
//		return Response.status(Status.CREATED).entity(gestor).build();
		return gestor;
	}

	@PUT
	@Override
	public Gestor update( Gestor gestor) {
		return service.update(gestor);
	}

	@DELETE
	@Override
	public void delete(Gestor gestor) {
		service.delete(gestor);
	}

	

}