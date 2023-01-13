package br.com.graphvs.ntrack.model.domain;

public class ErrorMessage { 

	private String message;
	private int status;
	
	public ErrorMessage() { }
		
	public ErrorMessage(String message, int status) {
		super();
		this.message = message;
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getStatusCode() {
		return status;
	}
	
	public void setStatusCode(int status) {
		this.status = status;
	}
	
}
