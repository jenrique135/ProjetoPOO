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
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CadastroMedico extends JFrame {

	private JPanel contentPane;
	private JTextField txfNome;
	private JTextField txfEmail;
	private JTable table;
	private Medico medico;
	private MedicoDao medicoDao;
	private List<Medico> lista;
	private DefaultTableModel modelo;
	private JTextField txfID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroMedico frame = new CadastroMedico();
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
	public CadastroMedico() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 680, 747);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 60, 646, 306);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 10, 96, 37);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Email:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 57, 96, 37);
		panel.add(lblNewLabel_2);
		
		txfNome = new JTextField();
		txfNome.setBounds(115, 10, 341, 37);
		panel.add(txfNome);
		txfNome.setColumns(10);
		
		txfEmail = new JTextField();
		txfEmail.setBounds(116, 57, 340, 33);
		panel.add(txfEmail);
		txfEmail.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Especialidade");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(10, 104, 118, 37);
		panel.add(lblNewLabel_3);
		
		JComboBox cmbBoxEspecialidade = new JComboBox();
		cmbBoxEspecialidade.setModel(new DefaultComboBoxModel(new String[] {"CARDIOLOGISTA", "NEUROLOGISTA", "ORTOPEDISTA", "DERMATOLOGISTA"}));
		cmbBoxEspecialidade.setBounds(126, 106, 263, 37);
		panel.add(cmbBoxEspecialidade);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					medicoDao = new MedicoDao();
					medico = new Medico();
					
					medico.setNome(txfNome.getText());
					medico.setEmail(txfEmail.getText());
					medico.setEspecialidade(cmbBoxEspecialidade.getSelectedItem().toString());
					
					medicoDao.incluir(medico);
					limpar();
					popularTabela();
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro " + e1);
				}
				
			}
		});
		
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSalvar.setBounds(60, 220, 153, 47);
		panel.add(btnSalvar);
		
		JButton btnNewButton = new JButton("Editar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					medico = new Medico();
					medicoDao = new MedicoDao();
	
					
					medico.setNome(txfNome.getText());
					medico.setEmail(txfEmail.getText());
					medico.setEspecialidade(cmbBoxEspecialidade.getSelectedItem().toString());
					medico.setIdmedico(Integer.parseInt(txfID.getText()));

				    medicoDao.alterar(medico);
				    limpar();
				    popularTabela();
					
				
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Erro " + e2);

				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(232, 220, 153, 47);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Excluir");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					Integer resp = JOptionPane.showConfirmDialog(null, "Deseja excluir?");
					if (resp == 0) {
						medico = new Medico();
						medicoDao = new MedicoDao();
						
						medico.setIdmedico(Integer.parseInt(txfID.getText()));
						medicoDao.excluir(medico);
						limpar();
						popularTabela();

					}

				} catch (Exception e3) {
					JOptionPane.showMessageDialog(null, "Erro " + e3);
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(402, 220, 140, 47);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel_4 = new JLabel("ID");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(20, 151, 86, 37);
		panel.add(lblNewLabel_4);
		
		txfID = new JTextField();
		txfID.setEditable(false);
		txfID.setBounds(115, 151, 98, 37);
		panel.add(txfID);
		txfID.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("CADASTRO MEDICO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 10, 285, 40);
		contentPane.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(20, 376, 636, 324);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 616, 304);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {

				try {
					Integer itemSelecionado = table.getSelectionModel().getLeadSelectionIndex();

					medico = lista.get(itemSelecionado);
					txfID.setText(medico.getIdmedico().toString());
					txfNome.setText(medico.getNome());
					txfEmail.setText(medico.getEmail());
					cmbBoxEspecialidade.setSelectedItem(medico.getEspecialidade());
					

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
			medicoDao = new MedicoDao();
			lista = medicoDao.buscarTodos();
			modelo = new DefaultTableModel();

			modelo.addColumn("ID");
			modelo.addColumn("Nome");
			modelo.addColumn("Email");
			modelo.addColumn("Especialidade");

			for (Medico medico : lista) {
				modelo.addRow(new String[] { medico.getIdmedico().toString(), medico.getNome(), medico.getEmail(), medico.getEspecialidade() });
			}
			table.setModel(modelo);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro" + e);
		}
	}
	
	public void limpar() {
		txfNome.setText(null);
		txfEmail.setText(null);
		txfID.setText(null);
		
	}
}
