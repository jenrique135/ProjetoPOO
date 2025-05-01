package clinica.classes;

public class Medico {

	private Integer idmedico;
	private String nome;
	private String email;
	private String especialidade;
	
	public Medico() {
	}
	
	public Medico(Integer idmedico, String nome, String email, String especialidade) {
		this.idmedico = idmedico;
		this.nome = nome;
		this.email = email;
		this.especialidade = especialidade;
	}

	

	public Integer getIdmedico() {
		return idmedico;
	}

	public void setIdmedico(Integer idmedico) {
		this.idmedico = idmedico;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	
	
}
