package clinica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import clinica.classes.Paciente;
import clinica.util.Conexao;

public class PacienteDao implements ModeloCrud<Paciente>{
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public PacienteDao() throws Exception {
		con = Conexao.conectar();
	}

	// INCLUIR //
	
	public void incluir(Paciente paciente) throws Exception {
		if (paciente == null) {
			throw new Exception("Objeto vazio");
		}
		
		try {
			String sql = " INSERT INTO pacientes (nome, numero, endereco, email, cpf) VALUES (?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, paciente.getNome());
			ps.setString(2, paciente.getNumero());
			ps.setString(3, paciente.getEndereco());
			ps.setString(4, paciente.getEmail());
			ps.setString(5, paciente.getCpf());

			ps.executeUpdate();
			
		} catch (Exception ex) {
			throw new Exception("Erro ao incluir dados" + ex);
		} finally {
			ps.close();
		}
	}
	
	// Excluir //
	
	public void excluir(Paciente paciente) throws Exception {
		if (paciente == null) {
			throw new Exception("Objeto vazio");
		}
		try {
			String sql = "delete from pacientes where idpaciente = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, paciente.getIdpaciente());
			ps.executeUpdate();
		} catch (Exception ex) {
			throw new Exception("erro ao excluir dados" + ex);
		} finally {
			ps.close();
		}

	}
	
	// Alterar //
	
	public void alterar(Paciente paciente) throws Exception {
		if (paciente == null) {
			throw new Exception("Campos obrigatórios vazios");
		}
		try {

			String sql = "UPDATE pacientes SET nome = ?, numero = ?, endereco = ?, email = ?, cpf = ? where idpaciente = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, paciente.getNome());
			ps.setString(2, paciente.getNumero());
			ps.setString(3, paciente.getEndereco());
			ps.setString(4, paciente.getEmail());
			ps.setString(5, paciente.getCpf());
			ps.setInt(6, paciente.getIdpaciente());

			ps.executeUpdate();

		} finally {
			ps.close();
		}
	}

	// BUSCAR UM //

	public Paciente buscarUm(Integer id) throws Exception {
		if (id == null) {
			throw new Exception("ID Vazio");
		}

		try {
			String sql = "select * from pacientes where idpaciente = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (!rs.next()) {
				throw new Exception("Nao foi encontrado registro");
			}
			String nome = rs.getString("nome");
			String numero = rs.getString("numero");
			String endereco = rs.getString("endereco");
			String email = rs.getString("email");
			String cpf = rs.getString("cpf");

			return new Paciente(id, nome, numero , endereco, email, cpf);

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			ps.close();
			rs.close();
		}
	}
	
	 // Buscar Todos //
	
	public List<Paciente> buscarTodos() throws Exception {
		try {
			String sql = "select * from pacientes";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			List<Paciente> lista = new ArrayList<Paciente>();

			while (rs.next()) {
				Integer id = rs.getInt("idpaciente");
				String nome = rs.getString("nome");
				String numero = rs.getString("numero");
				String endereco = rs.getString("endereco");
				String email = rs.getString("email");
				String cpf = rs.getString("cpf");


				lista.add(new Paciente(id, nome, numero, endereco, email, cpf));
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
