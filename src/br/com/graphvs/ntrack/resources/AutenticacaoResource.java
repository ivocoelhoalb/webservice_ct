package br.com.graphvs.ntrack.resources;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.com.graphvs.ntrack.model.domain.Autenticacao;
import br.com.graphvs.ntrack.model.domain.Credentials;
import br.com.graphvs.ntrack.model.domain.Token;
import br.com.graphvs.ntrack.service.AutenticacaoService;

@Path("/autenticacao")
public class AutenticacaoResource {
	private final static int  SIZE_TOKEN = 150;

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response authenticateUser(Credentials credentials) {

		AutenticacaoService service = new AutenticacaoService();

		String username = credentials.getUsername();
		String password = credentials.getPassword();

		Autenticacao autenticacao = new Autenticacao();
		autenticacao.setLogin(username);
		autenticacao.setSenha(password);

		autenticacao = service.getAutenticacao(autenticacao);

		// Issue a token for the user
		Token token = new Token();
		token.setToken(generateRandomToken(SIZE_TOKEN));
		
		autenticacao.setToken(token.getToken());
		autenticacao.setValidade(new Date());
		
		service.updateAutenticacao(autenticacao);
		

		// Return the token on the response
		return Response.ok(autenticacao).build();

	}

	
	public static String generateRandomToken(int byteLength) {
	    SecureRandom secureRandom = new SecureRandom();
	    byte[] token = new byte[byteLength];
	    secureRandom.nextBytes(token);
	    return new BigInteger(1, token).toString(32); //hex encoding
	}
}
