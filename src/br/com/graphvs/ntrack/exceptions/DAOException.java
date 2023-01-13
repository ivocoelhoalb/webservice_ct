package br.com.graphvs.ntrack.exceptions;

public class DAOException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3965087475900464946L;
	
	private int status;
	
	public DAOException(String message, int status) {
		super(message);
		this.status = status;
	}
	
	public int getSatusCode() {
		return status;
	}
	
}
