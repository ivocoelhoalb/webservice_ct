package br.com.graphvs.ntrack.model.domain;

public class ClienteRota extends Cliente {

	private int Ordem;
	private boolean atendido;
	private boolean visitado;
	private Long id;
	private Long idMotorista;
	private String nome;
	private double latitude;
	private double longitude;
	private String telefone1;
	private String telefone2;
	private String endereco;

	public ClienteRota(long idMotorista, int ordem, boolean atendido, boolean visitado, Long id, String nome,
			double latitude, double longitude, String telefone1, String telefone2, String endereco) {
		super();
		this.idMotorista = idMotorista;
		this.Ordem = ordem;
		this.atendido = atendido;
		this.visitado = visitado;
		this.id = id;
		this.nome = nome;
		this.latitude = latitude;
		this.longitude = longitude;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
		this.endereco = endereco;
	}

	public Long getIdMotorista() {
		return idMotorista;
	}

	public void setIdMotorista(Long idMotorista) {
		this.idMotorista = idMotorista;
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

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getOrdem() {
		return Ordem;
	}

	public void setOrdem(int ordem) {
		Ordem = ordem;
	}

	public boolean isAtendido() {
		return atendido;
	}

	public void setAtendido(boolean atendido) {
		this.atendido = atendido;
	}

	public boolean isVisitado() {
		return visitado;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

}
