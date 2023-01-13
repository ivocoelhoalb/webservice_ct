package br.com.graphvs.ntrack.model.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Motorista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private double latitude;
    private double longitude;
    private String data;
    private Long clientes;
    
    
    public Motorista() {
    }

    public Motorista(Long id, String nome, double latitude, double longitude, String data, Long clientes) {
		super();
		this.id = id;
		this.nome = nome;
		this.latitude = latitude;
		this.longitude = longitude;
		this.data = data;
		this.clientes = clientes;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
    public Long getClientes() {
        return clientes;
    }

    public void setClientes(Long clientes) {
        this.clientes = clientes;
    }

}
