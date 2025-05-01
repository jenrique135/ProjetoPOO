package clinica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import clinica.classes.Consulta;
import clinica.classes.Medico;
import clinica.classes.Paciente;
import clinica.util.Conexao;

public class ConsultaDao implements ModeloCrud<Consulta>{
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public ConsultaDao() throws Exception {
		con = Conexao.conectar();
	}

	// Incluir //

	public void incluir(Consulta consulta) throws Exception {
		if (consulta == null) {
			throw new Exception("Objeto vazio");
		}
		try {

			String sql = "INSERT INTO consulta (hora, data, idmedico, idpaciente) VALUES (?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1,consulta.getHora());
			ps.setString(2,consulta.getData());
			ps.setInt(3,consulta.getMedico().getIdmedico());
			ps.setInt(4,consulta.getPaciente().getIdpaciente());

			ps.executeUpdate();
		} catch (Exception ex) {
			throw new Exception("Erro ao incluir dados" + ex);
		} finally {
			ps.close();
		}
	}

	// Excluir //

	public void excluir(Consulta consulta) throws Exception {
		if (consulta == null) {
			throw new Exception("Objeto vazio");
		}
		try {
			String sql = "delete from consulta where idconsulta = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, consulta.getIdconsulta());
			ps.executeUpdate();
		} catch (Exception ex) {
			throw new Exception("erro ao excluir dados" + ex);
		} finally {
			ps.close();
		}

	}

	// Alterar //

	public void alterar(Consulta consulta) throws Exception {
		if (consulta == null) {
			throw new Exception("Campos obrigatórios vazios");
		}
		try {

			String sql = "UPDATE consulta SET hora = ?, data = ?, idmedico = ?, idpaciente = ? where idconsulta = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, consulta.getHora());
			ps.setString(2, consulta.getData());
			ps.setInt(3, consulta.getMedico().getIdmedico());
			ps.setInt(4, consulta.getPaciente().getIdpaciente());
			ps.setInt(5, consulta.getIdconsulta());

			ps.executeUpdate();

		} finally {
			ps.close();
		}
	}

	// Buscar Um //

	public Consulta buscarUm(Integer id) throws Exception {
		if (id == null) {
			throw new Exception("ID Vazio");
		}

		try {
			String sql = "select * from consulta where idconsulta = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (!rs.next()) {
				throw new Exception("Nao foi encontrado registro");
			}
			String data = rs.getString("data");
			String hora = rs.getString("hora");
			Integer idmedico = rs.getInt("idmedico");
			Integer idpaciente = rs.getInt("idpaciente");
			

			MedicoDao md = new MedicoDao();
			Medico medico = md.buscarUm(idmedico);
			
			PacienteDao pd = new PacienteDao();
			Paciente paciente = pd.buscarUm(idpaciente);

			return new Consulta(id, data, hora, medico, paciente);
			
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			ps.close();
			rs.close();
		}
	}

	// Buscar Todos //

	public List<Consulta> buscarTodos() throws Exception {
		try {
			String sql = "select * from consulta";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			List<Consulta> lista = new ArrayList<Consulta>();

			while (rs.next()) {
				Integer id = rs.getInt("idconsulta");
				String data = rs.getString("data");
				String hora = rs.getString("hora");
				Integer idmedico = rs.getInt("idmedico");
				Integer idpaciente = rs.getInt("idpaciente");

				MedicoDao md = new MedicoDao();
				Medico medico = md.buscarUm(idmedico);
				
				PacienteDao pd = new PacienteDao();
				Paciente paciente = pd.buscarUm(idpaciente);

				lista.add(new Consulta(id, data, hora, medico, paciente));
			}
			return lista;

		} catch (Exception ex) {
			throw new Exception(ex);
		} finally {
			ps.close();
			rs.close();
		}
	}


}
