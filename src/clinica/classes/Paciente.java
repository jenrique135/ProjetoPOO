package clinica.classes;

public class Paciente {

	private Integer idpaciente;
	private String nome;
	private String numero;
	private String endereco;
	private String email;
	private String cpf;
	
	public Paciente() {
	}
	
	public Paciente(Integer idpaciente, String nome, String numero, String endereco, String email, String cpf) {
		this.idpaciente = idpaciente;
		this.nome = nome;
		this.numero = numero;
		this.endereco = endereco;
		this.email = email;
		this.cpf = cpf;
	}

	
	public Integer getIdpaciente() {
		return idpaciente;
	}

	public void setIdpaciente(Integer idpaciente) {
		this.idpaciente = idpaciente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
	
	
}
