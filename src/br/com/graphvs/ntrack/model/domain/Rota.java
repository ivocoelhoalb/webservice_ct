package br.com.graphvs.ntrack.model.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rota {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long idMotorista;
	private Long idCliente;
	private int ordemCliente;
	private String data;

	public Long getIdMotorista() {
		return idMotorista;
	}

	public void setIdMotorista(Long idMotorista) {
		this.idMotorista = idMotorista;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}


	public int getOrdemCliente() {
		return ordemCliente;
	}

	public void setOrdemCliente(int ordemCliente) {
		this.ordemCliente = ordemCliente;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	

}
