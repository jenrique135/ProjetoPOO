package clinica.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import clinica.classes.Consulta;
import clinica.classes.Medico;
import clinica.classes.Paciente;
import clinica.dao.ConsultaDao;
import clinica.dao.MedicoDao;
import clinica.dao.PacienteDao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AgendarConsulta extends JFrame {

	private JPanel contentPane;
	private JTextField txfData;
	private JTextField txfHora;
	private Consulta consulta;
	private ConsultaDao consultaDao;
	private List<Consulta> lista;
	private DefaultTableModel modelo;
	private JTable table;
	private JTextField txfIdMedico;
	private JTextField txfIdPaciente;
	private JTextField txfIdConsulta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgendarConsulta frame = new AgendarConsulta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AgendarConsulta() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 704, 647);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 226, 670, 297);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 650, 277);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {

				try {
					Integer itemSelecionado = table.getSelectionModel().getLeadSelectionIndex();

					consulta = lista.get(itemSelecionado);
					txfIdConsulta.setText(consulta.getIdconsulta().toString());
					txfData.setText(consulta.getData());
					txfHora.setText(consulta.getHora());
					txfIdMedico.setText(consulta.getMedico().getIdmedico().toString());
					txfIdPaciente.setText(consulta.getPaciente().getIdpaciente().toString());
			

				} catch (Exception a) {
					JOptionPane.showMessageDialog(null, "Erro " + a);
				}

			}
		});
		
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);
		popularTabela();
		
		JLabel lblNewLabel = new JLabel("AGENDAR CONSULTA");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 10, 165, 37);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Data:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 53, 115, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Hora:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(20, 93, 100, 30);
		contentPane.add(lblNewLabel_2);
		
		txfData = new JTextField();
		txfData.setBounds(130, 57, 403, 27);
		contentPane.add(txfData);
		txfData.setColumns(10);
		
		txfHora = new JTextField();
		txfHora.setBounds(130, 97, 403, 27);
		contentPane.add(txfHora);
		txfHora.setColumns(10);
		
		JButton btnAgendar = new JButton("Agendar");
		btnAgendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				try {
					consulta = new Consulta();
					consultaDao = new ConsultaDao();
	
					MedicoDao medicoDao = new MedicoDao();
					Medico medico = medicoDao.buscarUm(Integer.parseInt(txfIdMedico.getText()));
	
					PacienteDao pacienteDao = new PacienteDao();
					Paciente paciente = pacienteDao.buscarUm(Integer.parseInt(txfIdPaciente.getText()));
					
					consulta.setData(txfData.getText());
					consulta.setHora(txfHora.getText());
					consulta.setMedico(medico);
					consulta.setPaciente(paciente);
	
					consultaDao.incluir(consulta);
					limpar();
					popularTabela();
					
				} catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Erro " + e1);
				}
				
				
			}
		});
		btnAgendar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAgendar.setBounds(91, 533, 115, 49);
		contentPane.add(btnAgendar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					consulta = new Consulta();
					consultaDao = new ConsultaDao();
	
					MedicoDao medicoDao = new MedicoDao();
					Medico medico = medicoDao.buscarUm(Integer.parseInt(txfIdMedico.getText()));
	
					PacienteDao pacienteDao = new PacienteDao();
					Paciente paciente = pacienteDao.buscarUm(Integer.parseInt(txfIdPaciente.getText()));
					
					consulta.setData(txfData.getText());
				    consulta.setHora(txfHora.getText());
				    consulta.setMedico(medico);
				    consulta.setPaciente(paciente);
				    consulta.setIdconsulta(Integer.parseInt(txfIdConsulta.getText()));

				    consultaDao.alterar(consulta);
				    limpar();
				    popularTabela();
					
				
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Erro " + e2);

				}
			}
		});
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEditar.setBounds(242, 533, 122, 49);
		contentPane.add(btnEditar);
		
		JButton btnCancelar = new JButton("Apagar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					Integer resp = JOptionPane.showConfirmDialog(null, "Deseja excluir?");
					if (resp == 0) {
						consulta = new Consulta();
						consultaDao = new ConsultaDao();
						
						consulta.setIdconsulta(Integer.parseInt(txfIdConsulta.getText()));
						consultaDao.excluir(consulta);
						limpar();
						popularTabela();

					}

				} catch (Exception e3) {
					JOptionPane.showMessageDialog(null, "Erro " + e3);
				}
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancelar.setBounds(398, 533, 115, 49);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel_3 = new JLabel("ID do Medico");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(20, 133, 122, 30);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("ID do Paciente");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(20, 173, 122, 30);
		contentPane.add(lblNewLabel_4);
		
		txfIdMedico = new JTextField();
		txfIdMedico.setBounds(150, 134, 107, 29);
		contentPane.add(txfIdMedico);
		txfIdMedico.setColumns(10);
		
		txfIdPaciente = new JTextField();
		txfIdPaciente.setBounds(150, 176, 107, 30);
		contentPane.add(txfIdPaciente);
		txfIdPaciente.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("ID da Consulta");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(309, 134, 115, 29);
		contentPane.add(lblNewLabel_5);
		
		txfIdConsulta = new JTextField();
		txfIdConsulta.setEditable(false);
		txfIdConsulta.setBounds(434, 134, 96, 29);
		contentPane.add(txfIdConsulta);
		txfIdConsulta.setColumns(10);
	}
		
		public void popularTabela() {
			try {
				consultaDao = new ConsultaDao();
				lista = consultaDao.buscarTodos();
				modelo = new DefaultTableModel();

				modelo.addColumn("ID");
				modelo.addColumn("Hora");
				modelo.addColumn("Data");
				modelo.addColumn("Medico");
				modelo.addColumn("Paciente");

				for (Consulta consulta : lista) {
					modelo.addRow(new String[] { consulta.getIdconsulta().toString(), consulta.getData(), consulta.getHora(), consulta.getMedico().getNome(),
							consulta.getPaciente().getNome() });
				}
				table.setModel(modelo);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro" + e);
			}
	}
		
		public void limpar() {
			txfIdConsulta.setText(null);
			txfData.setText(null);
			txfHora.setText(null);
			txfIdMedico.setText(null);
			txfIdPaciente.setText(null);
		}
}
