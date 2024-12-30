package projetPoo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import LesBases.EtudiantClass;

public class Admin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField tfId;
	private JTextField tfEmail;
	private JTextField tfNom;
	private JTextField tfPrenom;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin window = new Admin("");
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the application.
	 */
	public Admin(String s) {
	
	
		setBounds(100, 100, 530, 464);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 128));
		panel.setBounds(0, 0, 129, 427);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnEtudiants = new JButton("ETUDIANTS");
		btnEtudiants.setForeground(new Color(0, 0, 0));
		btnEtudiants.setBackground(new Color(255, 255, 255));
		btnEtudiants.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEtudiants.setBounds(10, 159, 107, 23);
		panel.add(btnEtudiants);
		btnEtudiants.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Admin lgF = new Admin(s);
				lgF.setVisible(true);
				lgF.setLocationRelativeTo(null);
			}
		});

		
		JButton btnMateriels = new JButton("MATERIELS");
		btnMateriels.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnMateriels.setBounds(10, 210, 107, 23);
		panel.add(btnMateriels);
		btnMateriels.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ListeMateriel lgF = new ListeMateriel(s);
				lgF.setVisible(true);
				lgF.setLocationRelativeTo(null);
			}
		});

		
		JButton btnEmprunts = new JButton("EMPRUNTS");
		btnEmprunts.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEmprunts.setBounds(10, 263, 107, 23);
		btnEmprunts.setBorder(null);
		panel.add(btnEmprunts);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Admin.class.getResource("/Images/icons8-male-user-64.png")));
		lblNewLabel_2.setBounds(35, 30, 64, 64);
		panel.add(lblNewLabel_2);
		
	        JLabel lblNewLabel_3 = new JLabel(s);
	        lblNewLabel_3.setForeground(new Color(255, 255, 255));
	        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_3.setBounds(45, 94, 47, 19);
			panel.add(lblNewLabel_3);
			
			JButton btnRetour = new JButton("");
			btnRetour.setBackground(new Color(0, 0, 128));
			btnRetour.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					Connexion retour = new Connexion();
					retour.setVisible(true);
					retour.setLocationRelativeTo(null);
				}
			});
			btnRetour.setBounds(0, 393, 33, 23);
			btnRetour.setBorder(null);
			btnRetour.setIcon(new ImageIcon(Admin.class.getResource("/Images/wm_1728838105989 (1).png")));
			panel.add(btnRetour);
	        
		
		
		btnEmprunts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ListeEmprunt lgF = new ListeEmprunt(s);
				lgF.setVisible(true);
				lgF.setLocationRelativeTo(null);
			}
		});
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(135, 31, 370, 238);
		getContentPane().add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		model = new DefaultTableModel();
		table.setGridColor(Color.LIGHT_GRAY);
		table.setRowHeight(30);
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
	    table.getTableHeader().setBackground(new Color(30, 144, 255));
	    table.getTableHeader().setForeground(Color.WHITE);
	    table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setModel(model);
		table.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		Object [] column = {"Id", "Nom", "Prenom", "Email"};
		model.setColumnIdentifiers(column);
		Object [] row = new Object[100];
		EtudiantClass ett = new EtudiantClass();
		ResultSet listEt = ett.PrintEt();
		try {
			while (listEt.next()){
				row[0] = listEt.getInt(1);
				row[1]= listEt.getString(2);
				row[2]= listEt.getString(3);
				row[3]= listEt.getString(4);
				
				model.addRow(row);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		table.addMouseListener(new MouseAdapter() {
	
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				tfId.setText(model.getValueAt(i,0).toString());
				tfNom.setText(model.getValueAt(i,1).toString());
				tfPrenom.setText(model.getValueAt(i,2).toString());
				tfEmail.setText(model.getValueAt(i,3).toString());
			}
		});
		
		JLabel lblNewLabel = new JLabel("LISTE DES ETUDIANTS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(159, 11, 293, 14);
		getContentPane().add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(135, 280, 370, 142);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(20, 11, 96, 14);
		panel_1.add(lblNewLabel_1);
		
		tfId = new JTextField();
		tfId.setBounds(20, 25, 120, 20);
		panel_1.add(tfId);
		tfId.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Email");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(20, 54, 96, 14);
		panel_1.add(lblNewLabel_1_1);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(20, 68, 120, 20);
		panel_1.add(tfEmail);
		
		JLabel lblNewLabel_1_2 = new JLabel("Nom");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(214, 11, 96, 14);
		panel_1.add(lblNewLabel_1_2);
		
		tfNom = new JTextField();
		tfNom.setColumns(10);
		tfNom.setBounds(214, 25, 120, 20);
		panel_1.add(tfNom);
		
		JLabel lblNewLabel_1_3 = new JLabel("Prenom");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_3.setBounds(214, 54, 96, 14);
		panel_1.add(lblNewLabel_1_3);
		
		tfPrenom = new JTextField();
		tfPrenom.setColumns(10);
		tfPrenom.setBounds(214, 68, 120, 20);
		panel_1.add(tfPrenom);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setForeground(new Color(255, 255, 255));
		btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAjouter.setBackground(new Color(0, 128, 64));
		btnAjouter.setBounds(10, 108, 106, 23);
		panel_1.add(btnAjouter);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EtudiantClass et = new EtudiantClass();
				et.setId(Integer.parseInt(tfId.getText()));
				et.setNom(tfNom.getText());
				et.setPrenom(tfPrenom.getText());
				et.setEmail(tfEmail.getText());
				et.RegisterByAdmin(et.getNom(),et.getPrenom(),et.getEmail());
				Object [] row = {et.getId(), et.getNom(), et.getPrenom(), et.getEmail()};
				model.addRow(row);
				tfId.setText("");
				tfNom.setText("");
				tfPrenom.setText("");
				tfEmail.setText("");
				dispose();
				Admin lgF = new Admin(s);
				lgF.setVisible(true);
				lgF.setLocationRelativeTo(null);
				
			}
			
			
		});
		
		JButton btnModiffier = new JButton("Modifier");
		btnModiffier.setForeground(Color.WHITE);
		btnModiffier.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnModiffier.setBackground(new Color(0, 128, 64));
		btnModiffier.setBounds(126, 108, 106, 23);
		EtudiantClass et = new EtudiantClass();
		panel_1.add(btnModiffier);
		btnModiffier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				model.setValueAt(Integer.parseInt(tfId.getText()),i,0);
				model.setValueAt(tfNom.getText(), i, 1);
				model.setValueAt(tfPrenom.getText(), i, 2);
				model.setValueAt(tfEmail.getText(), i, 3);
				et. UpdateEt(tfNom.getText(),tfPrenom.getText(),tfEmail.getText(),Integer.parseInt(tfId.getText()));
				dispose();
				Admin lgF = new Admin(s);
				lgF.setVisible(true);
				lgF.setLocationRelativeTo(null);
			}
		});
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setForeground(Color.WHITE);
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSupprimer.setBackground(new Color(0, 128, 64));
		btnSupprimer.setBounds(242, 108, 106, 23);
		panel_1.add(btnSupprimer);
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				et.DeleteEt(Integer.parseInt(tfId.getText()));
				int rowSelected = table.getSelectedRow();
				model.removeRow(rowSelected);
				tfId.setText("");
				tfNom.setText("");
				tfPrenom.setText("");
				tfEmail.setText("");
				dispose();
				Admin lgF = new Admin(s);
				lgF.setVisible(true);
				lgF.setLocationRelativeTo(null);
				
			}
		});
	}
}
