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
import br.com.graphvs.ntrack.model.domain.Cliente;
import br.com.graphvs.ntrack.model.domain.ClienteRota;
import br.com.graphvs.ntrack.secutity.Secured;
import br.com.graphvs.ntrack.service.ClienteService;

@Path("/cliente")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class ClienteResource implements IRestApi<Cliente> { 
	
	private ClienteService service = new ClienteService();
	
	@GET
	@Override
	public List<Cliente> list() {
		return service.list();
	}
	
	@POST
	@Override
	public Cliente save(Cliente cliente) { 
		cliente = service.save(cliente);
		return cliente;
	}
	
	@PUT
	@Override
	public Cliente update(Cliente entidade) {
		// TODO Auto-generated method stub
		Cliente cliente = service.update(entidade);
		return cliente;
	}
	
	@DELETE
	@Override
	public void delete(Cliente cliente) {
		service.delete(cliente);	
	}
	
	
	@GET
	@Path("/rota/{idMotorista}")
	public List<ClienteRota> getCliente(@PathParam("idMotorista") long id) {
		return service.getClienteIdMotorista(id);
	}
	
	@GET
	@Path("/rota/{idMotorista}/{data}")
	public List<ClienteRota> getCliente(@PathParam("idMotorista") long id, @PathParam("data") String data) {
		return service.getClienteIdMotoristaData(id,data);
	}
	
	
	@GET
	@Path("/rota/data/{data}")
	public List<ClienteRota> getCliente( @PathParam("data") String data) {
		return service.getClienteData(data);
	}
	
	
	
	
//	@PUT
//	@Path("{clienteId}")
//	public void update(@PathParam("clienteId") long id, Cliente cliente) {
//		cliente.setId(id);
//		service.update(cliente);
//	}
	
	
	

	

}