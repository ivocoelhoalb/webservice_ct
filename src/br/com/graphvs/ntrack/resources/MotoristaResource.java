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
import br.com.graphvs.ntrack.model.domain.Motorista;
import br.com.graphvs.ntrack.service.MotoristaService;

@Path("/motorista")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class MotoristaResource implements IRestApi<Motorista> {

	private MotoristaService service = new MotoristaService();

	@GET
	@Override
	public List<Motorista> list() {
		return service.list();
	}

	@POST
	@Override
	public Motorista save(Motorista motorista) {
		motorista = service.save(motorista);
//		return Response.status(Status.CREATED).entity(motorista).build();
		return motorista;
	}

	@PUT
	@Override
	public Motorista update( Motorista motorista) {
		return service.update(motorista);
	}

	@DELETE
	@Override
	public void delete(Motorista motorista) {
		service.delete(motorista);
	}

	@GET
	@Path("/data/{data}")
	public List<Motorista> listaMotoristas(@PathParam("data") String data) {
		return service.getMotoristaComRota(data);
	}

}