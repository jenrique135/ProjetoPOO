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

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CadastroPaciente extends JFrame {

	private JPanel contentPane;
	private JTextField txfNome;
	private JTextField txfNumero;
	private JTextField txfEndereco;
	private JTextField txfEmail;
	private JTextField txfCPF;
	private Paciente paciente;
	private PacienteDao pacienteDao;
	private List<Paciente> lista;
	private DefaultTableModel modelo;
	private JTable table;
	private JTextField txfID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroPaciente frame = new CadastroPaciente();
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
	public CadastroPaciente() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 705, 756);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 52, 671, 346);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNome.setBounds(10, 10, 82, 26);
		panel.add(lblNome);
		
		txfNome = new JTextField();
		txfNome.setBounds(102, 10, 440, 30);
		panel.add(txfNome);
		txfNome.setColumns(10);
		
		JLabel lblNumero = new JLabel("Numero:");
		lblNumero.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumero.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNumero.setBounds(10, 65, 75, 26);
		panel.add(lblNumero);
		
		txfNumero = new JTextField();
		txfNumero.setBounds(102, 65, 253, 30);
		panel.add(txfNumero);
		txfNumero.setColumns(10);
		
		JLabel lblEndereco = new JLabel("Endereco:");
		lblEndereco.setHorizontalAlignment(SwingConstants.CENTER);
		lblEndereco.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEndereco.setBounds(10, 121, 75, 26);
		panel.add(lblEndereco);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setBounds(10, 171, 75, 26);
		panel.add(lblEmail);
		
		JLabel lblCPF = new JLabel("CPF:");
		lblCPF.setHorizontalAlignment(SwingConstants.CENTER);
		lblCPF.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCPF.setBounds(10, 231, 75, 26);
		panel.add(lblCPF);
		
		txfEndereco = new JTextField();
		txfEndereco.setBounds(102, 122, 440, 30);
		panel.add(txfEndereco);
		txfEndereco.setColumns(10);
		
		txfEmail = new JTextField();
		txfEmail.setBounds(102, 177, 440, 30);
		panel.add(txfEmail);
		txfEmail.setColumns(10);
		
		txfCPF = new JTextField();
		txfCPF.setBounds(102, 231, 270, 30);
		panel.add(txfCPF);
		txfCPF.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					pacienteDao = new PacienteDao();
					paciente = new Paciente();
					
					paciente.setNome(txfNome.getText());
					paciente.setNumero(txfNumero.getText());
					paciente.setEndereco(txfEndereco.getText());
					paciente.setEmail(txfEmail.getText());
					paciente.setCpf(txfCPF.getText());
					
					pacienteDao.incluir(paciente);
					limpar();
					popularTabela();
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
				
			}
		});
		
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSalvar.setBounds(119, 295, 120, 41);
		panel.add(btnSalvar);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(382, 231, 45, 26);
		panel.add(lblNewLabel);
		
		txfID = new JTextField();
		txfID.setEditable(false);
		txfID.setBounds(437, 231, 82, 30);
		panel.add(txfID);
		txfID.setColumns(10);
		
		JButton btnNewButton = new JButton("Editar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					paciente = new Paciente();
					pacienteDao = new PacienteDao();
					
					paciente.setNome(txfNome.getText());
					paciente.setNumero(txfNumero.getText());
					paciente.setEndereco(txfEndereco.getText());
					paciente.setEmail(txfEmail.getText());
					paciente.setCpf(txfCPF.getText());
					paciente.setIdpaciente(Integer.parseInt(txfID.getText()));

				    pacienteDao.alterar(paciente);
				    limpar();
				    popularTabela();
					
				
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Erro " + e2);

				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(249, 295, 120, 41);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Excluir");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					Integer resp = JOptionPane.showConfirmDialog(null, "Deseja excluir?");
					if (resp == 0) {
						paciente = new Paciente();
						pacienteDao = new PacienteDao();
						
						paciente.setIdpaciente(Integer.parseInt(txfID.getText()));
						pacienteDao.excluir(paciente);
						limpar();
						popularTabela();

					}

				} catch (Exception e3) {
					JOptionPane.showMessageDialog(null, "Erro " + e3);
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(382, 295, 120, 41);
		panel.add(btnNewButton_1);
		
		JLabel lblTitulo = new JLabel("CADASTRO PACIENTE");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitulo.setBounds(10, 10, 293, 44);
		contentPane.add(lblTitulo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 408, 671, 301);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 651, 281);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {

				try {
					Integer itemSelecionado = table.getSelectionModel().getLeadSelectionIndex();

					paciente = lista.get(itemSelecionado);
					txfID.setText(paciente.getIdpaciente().toString());
					txfNome.setText(paciente.getNome());
					txfNumero.setText(paciente.getNumero());
					txfEndereco.setText(paciente.getEndereco());
					txfEmail.setText(paciente.getEmail());
					txfCPF.setText(paciente.getCpf());

				} catch (Exception a) {
					JOptionPane.showMessageDialog(null, "Erro " + a);
				}

			}
		});
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table); // Ativa a tabela
		popularTabela(); // executa ao abrir
	}
	
	public void popularTabela() {
		try {
			pacienteDao = new PacienteDao();
			lista = pacienteDao.buscarTodos();
			modelo = new DefaultTableModel();

			modelo.addColumn("ID");
			modelo.addColumn("Nome");
			modelo.addColumn("Numero");
			modelo.addColumn("Endereco");
			modelo.addColumn("Email");
			modelo.addColumn("CPF");


			for (Paciente paciente : lista) {
				modelo.addRow(new String[] { paciente.getIdpaciente().toString(), paciente.getNome(), paciente.getNumero(), paciente.getEndereco(),
						paciente.getEmail(), paciente.getCpf()});
			}
			table.setModel(modelo);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro" + e);
		}
	}
	
	public void limpar() {
		txfNome.setText(null);
		txfNumero.setText(null);
		txfEndereco.setText(null);
		txfEmail.setText(null);
		txfCPF.setText(null);
		txfID.setText(null);
	}
	
	
}
