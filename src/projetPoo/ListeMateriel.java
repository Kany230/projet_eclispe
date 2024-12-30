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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import LesBases.MaterielClass;

public class ListeMateriel extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;
	private JTextField tfId;
	private JTextField tfQuantite;
	private JTextField tfNom;
	private JTextField tfMarque;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListeMateriel window = new ListeMateriel();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ListeMateriel(String s) {

		setBounds(100, 100, 530, 462);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 0, 128));
		panel.setBounds(0, 0, 129, 425);
		getContentPane().add(panel);
		
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
		
		JButton btnEtudiants = new JButton("ETUDIANTS");
		btnEtudiants.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
				Admin lgF = new Admin(s);
				lgF.setVisible(true);
				lgF.setLocationRelativeTo(null);
			}
		});		btnEtudiants.setForeground(Color.BLACK);
		btnEtudiants.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEtudiants.setBackground(Color.WHITE);
		btnEtudiants.setBounds(10, 159, 107, 23);
		panel.add(btnEtudiants);
		
		JButton btnMateriels = new JButton("MATERIELS");
		btnMateriels.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ListeMateriel lgF = new ListeMateriel(s);
				lgF.setVisible(true);
				lgF.setLocationRelativeTo(null);
			}
		});
		btnMateriels.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnMateriels.setBounds(10, 210, 107, 23);
		panel.add(btnMateriels);
		
		JButton btnEmprunts = new JButton("EMPRUNTS");
		btnEmprunts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ListeEmprunt lgF = new ListeEmprunt(s);
				lgF.setVisible(true);
				lgF.setLocationRelativeTo(null);
			}
		});
		btnEmprunts.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEmprunts.setBounds(10, 263, 107, 23);
		panel.add(btnEmprunts);
		
		JLabel lblListeDesMateriels = new JLabel("LISTE DES MATERIELS");
		lblListeDesMateriels.setHorizontalAlignment(SwingConstants.CENTER);
		lblListeDesMateriels.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblListeDesMateriels.setBounds(157, 11, 293, 14);
		getContentPane().add(lblListeDesMateriels);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(136, 36, 370, 238);
	    getContentPane().add(scrollPane);
	    table = new JTable();
		scrollPane.setViewportView(table);
		model = new DefaultTableModel();
		table.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				tfId.setText(model.getValueAt(i,0).toString());
				tfNom.setText(model.getValueAt(i,1).toString());
				tfMarque.setText(model.getValueAt(i,2).toString());
				tfQuantite.setText(model.getValueAt(i,3).toString());
			}
		});
		table.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		table.setGridColor(Color.LIGHT_GRAY);
		table.setRowHeight(30);
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
	    table.getTableHeader().setBackground(new Color(30, 144, 255));
	    table.getTableHeader().setForeground(Color.WHITE);
	    table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setModel(model);
		table.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		Object [] column = {"ID","NOM DU MATERIEL","MARQUE","QUANTITE"};
		model.setColumnIdentifiers(column);
		Object [] row = new Object[100];
		MaterielClass mat = new MaterielClass(" "," ",0);
		ResultSet listMat = mat.PrintMat();
		try {
			while (listMat.next()){
				row[0] = listMat.getInt(1);
				row[1]= listMat.getString(2);
				row[2]= listMat.getString(3);
				row[3]= listMat.getString(4);
				
				model.addRow(row);
				
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	    
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(136, 283, 370, 142);
		getContentPane().add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(20, 11, 96, 14);
		panel_1.add(lblNewLabel_1);
		
		tfId = new JTextField();
		tfId.setColumns(10);
		tfId.setBounds(20, 25, 120, 20);
		panel_1.add(tfId);
		
		JLabel lblNewLabel_1_1 = new JLabel("Quantite");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(20, 54, 96, 14);
		panel_1.add(lblNewLabel_1_1);
		
		tfQuantite = new JTextField();
		tfQuantite.setColumns(10);
		tfQuantite.setBounds(20, 68, 120, 20);
		panel_1.add(tfQuantite);
		
		JLabel lblNewLabel_1_2 = new JLabel("Nom materiel\r\n");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(214, 11, 96, 14);
		panel_1.add(lblNewLabel_1_2);
		
		tfNom = new JTextField();
		tfNom.setColumns(10);
		tfNom.setBounds(214, 25, 120, 20);
		panel_1.add(tfNom);
		
		JLabel lblNewLabel_1_3 = new JLabel("Marque");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_3.setBounds(214, 54, 96, 14);
		panel_1.add(lblNewLabel_1_3);
		
		tfMarque = new JTextField();
		tfMarque.setColumns(10);
		tfMarque.setBounds(214, 68, 120, 20);
		panel_1.add(tfMarque);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAjouter.setBackground(new Color(0, 128, 64));
		btnAjouter.setBounds(10, 108, 106, 23);
		panel_1.add(btnAjouter);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfNom.getText().isBlank()||tfMarque.getText().isBlank()||tfQuantite.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Entrer toutes les informations du matériel d'abord ", "Notification",JOptionPane.ERROR_MESSAGE);
				}
				else {
				mat.setNom(tfNom.getText());
				mat.setMarque(tfMarque.getText());
				mat.setStock(Integer.parseInt(tfQuantite.getText()));
				mat.AddMat(tfNom.getText(), tfMarque.getText(), Integer.parseInt(tfQuantite.getText()));
				Object [] row  = {mat.getId(),mat.getNom(),mat.getMarque(),mat.getStock()};
				model.addRow(row);
				}
			}
			
		});
		
		JButton btnModiffier = new JButton("Modifier");
		btnModiffier.setForeground(Color.WHITE);
		btnModiffier.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnModiffier.setBackground(new Color(0, 128, 64));
		btnModiffier.setBounds(126, 108, 106, 23);
		panel_1.add(btnModiffier);
		btnModiffier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				model.setValueAt(tfId.getText(), i, 0);
				model.setValueAt(tfNom.getText(), i, 1);
				model.setValueAt(tfMarque.getText(), i, 2);
				model.setValueAt(tfQuantite.getText(), i, 3);
				mat.UpdateMat(tfNom.getText(), tfMarque.getText(),Integer.parseInt(tfQuantite.getText()),Integer.parseInt(tfId.getText()));
				
			}
		});
		
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
		
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setForeground(Color.WHITE);
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSupprimer.setBackground(new Color(0, 128, 64));
		btnSupprimer.setBounds(242, 108, 106, 23);
		panel_1.add(btnSupprimer);
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mat.DeleteMat(Integer.parseInt(tfId.getText()));
				int rowSelected = table.getSelectedRow();
				model.removeRow(rowSelected);
				tfId.setText("");
				tfNom.setText("");
				tfMarque.setText("");
				tfQuantite.setText("");
			}

		});
	}
	
	
}
