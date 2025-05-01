package clinica.classes;

public class Consulta {

	private Integer idconsulta;
	private String hora;
	private String data;
	private Medico medico;
	private Paciente paciente;
	
	public Consulta() {
	}
	
	public Consulta(Integer idconsulta, String hora, String data, Medico medico, Paciente paciente) {
		this.idconsulta = idconsulta;
		this.hora = hora;
		this.data = data;
		this.medico = medico;
		this.paciente = paciente;
	}

	

	public Integer getIdconsulta() {
		return idconsulta;
	}

	public void setIdconsulta(Integer idconsulta) {
		this.idconsulta = idconsulta;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	
	
	
}
