package clinica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import clinica.classes.Medico;
import clinica.util.Conexao;

public class MedicoDao implements ModeloCrud<Medico>{
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public MedicoDao() throws Exception {
		con = Conexao.conectar();
	}

	// INCLUIR //
	
	public void incluir(Medico medico) throws Exception {
		if (medico == null) {
			throw new Exception("Objeto vazio");
		}
		
		try {
			String sql = " INSERT INTO medicos (nome, email, especialidade) VALUES (?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, medico.getNome());
			ps.setString(2, medico.getEmail());
			ps.setString(3, medico.getEspecialidade());

			ps.executeUpdate();
			
		} catch (Exception ex) {
			throw new Exception("Erro ao incluir dados" + ex);
		} finally {
			ps.close();
		}
	}
	
	// Excluir //
	
	public void excluir(Medico medico) throws Exception {
		if (medico == null) {
			throw new Exception("Objeto vazio");
		}
		try {
			String sql = "delete from medicos where idmedico = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, medico.getIdmedico());
			ps.executeUpdate();
		} catch (Exception ex) {
			throw new Exception("erro ao excluir dados" + ex);
		} finally {
			ps.close();
		}

	}
	
	// Alterar //
	
	public void alterar(Medico medico) throws Exception {
		if (medico == null) {
			throw new Exception("Campos obrigatórios vazios");
		}
		try {

			String sql = "UPDATE medicos SET nome = ?, email = ?, especialidade = ? where idmedico = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, medico.getNome());
			ps.setString(2, medico.getEmail());
			ps.setString(3, medico.getEspecialidade());
			ps.setInt(4, medico.getIdmedico());

			ps.executeUpdate();

		} finally {
			ps.close();
		}
	}
	

	// BUSCAR UM //

	public Medico buscarUm(Integer id) throws Exception {
		if (id == null) {
			throw new Exception("ID Vazio");
		}

		try {
			String sql = "select * from medicos where idmedico = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (!rs.next()) {
				throw new Exception("Nao foi encontrado registro");
			}
			String nome = rs.getString("nome");
			String email = rs.getString("email");
			String especialidade = rs.getString("especialidade");

			return new Medico(id, nome, email, especialidade);

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			ps.close();
			rs.close();
		}
		
	}
	
		// Buscar Todos //
	
		public List<Medico> buscarTodos() throws Exception {
			try {
				String sql = "select * from medicos";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				List<Medico> lista = new ArrayList<Medico>();

				while (rs.next()) {
					Integer id = rs.getInt("idmedico");
					String nome = rs.getString("nome");
					String email = rs.getString("email");
					String especialidade = rs.getString("especialidade");
					

					lista.add(new Medico(id, nome, email, especialidade));
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
