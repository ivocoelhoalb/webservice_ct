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
import br.com.graphvs.ntrack.model.domain.Atendimento;
import br.com.graphvs.ntrack.service.AtendimentoService;

@Path("/atendimento")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class AtendimentoResource implements IRestApi<Atendimento>{ 
	
	private AtendimentoService service = new AtendimentoService();

	@GET
	@Override
	public List<Atendimento> list() {
		// TODO Auto-generated method stub
		return service.list();
	}

	@POST
	@Override
	public Atendimento save(Atendimento entidade) {
		// TODO Auto-generated method stub
		return service.save(entidade);
	}

	@PUT
	@Override
	public Atendimento update(Atendimento entidade) {
		// TODO Auto-generated method stub
		return service.update(entidade);
	}

	@DELETE
	@Override
	public void delete(Atendimento entidade) {
		// TODO Auto-generated method stub
		 service.delete(entidade);
	}
	
	@GET
	@Path("/data/{data}/")
	public List<Atendimento> list(@PathParam("data") String data) {
		// TODO Auto-generated method stub
		return service.list(data);
	}
	
	
}