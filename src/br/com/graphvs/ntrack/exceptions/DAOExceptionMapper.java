package br.com.graphvs.ntrack.exceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.graphvs.ntrack.model.domain.ErrorMessage;


@Provider
public class DAOExceptionMapper implements ExceptionMapper<DAOException> {

	@Override
	public Response toResponse(DAOException exception) {
		ErrorMessage error = new ErrorMessage(exception.getMessage(), exception.getSatusCode());
		
		
		if (exception.getSatusCode() == ErrorCode.BAD_REQUEST.getStatusCode()) {
			return Response.status(Status.BAD_REQUEST)				
					.entity(error)
					.type(MediaType.APPLICATION_JSON)
					.build();
		} else if (exception.getSatusCode() == ErrorCode.NOT_FOUND.getStatusCode()) {
			return Response.status(Status.NOT_FOUND)				
					.entity(error)
					.type(MediaType.APPLICATION_JSON)
					.build();
		} else if (exception.getSatusCode() == ErrorCode.FORBIDDEN.getStatusCode()) {
			return Response.status(Status.FORBIDDEN)				
					.entity(error)
					.type(MediaType.APPLICATION_JSON)
					.build();
		} 
		else if (exception.getSatusCode() == ErrorCode.UNAUTHORIZED.getStatusCode()) {
			return Response.status(Status.UNAUTHORIZED)				
					.entity(error)
					.type(MediaType.APPLICATION_JSON)
					.build();
		} 
		else {
			return Response.status(Status.INTERNAL_SERVER_ERROR)				
					.entity(error)
					.type(MediaType.APPLICATION_JSON)
					.build();
		}
	}
}