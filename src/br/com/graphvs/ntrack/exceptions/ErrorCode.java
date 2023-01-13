package br.com.graphvs.ntrack.exceptions;

public enum ErrorCode {
	
	BAD_REQUEST(400),
	UNAUTHORIZED(403),
	FORBIDDEN(403),
	NOT_FOUND(404),
	SERVER_ERROR(500);

	private int status;
	
	ErrorCode(int status) {
		this.status = status;
	}
	
	public int getStatusCode() {
		return status;
	}
	
}
