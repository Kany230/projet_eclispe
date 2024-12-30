package projetPoo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

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

import LesBases.EmpruntClass;
import LesBases.MaterielClass;

public class Emprunts  extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField tfId;
	private JTextField tfQuantite;
	private JTextField tfNom;
	private JTextField tfMarque;
	private JTable table;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Emprunts window = new Emprunts();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Emprunts(String nom, String prenom, String email, int id) {
		
		setBounds(100, 100, 530, 464);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 0, 128));
		panel.setBounds(0, 0, 129, 427);
		getContentPane().add(panel);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Admin.class.getResource("/Images/icons8-male-user-64.png")));
		lblNewLabel_2.setBounds(35, 30, 64, 64);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(prenom);
        lblNewLabel_3.setForeground(new Color(255, 255, 255));
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(35, 96, 47, 19);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel(nom);
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(70, 94, 47, 23);
		panel.add(lblNewLabel_4);
		
		JButton btnMesInfos = new JButton("MES INFOS\r\n");
		btnMesInfos.setForeground(Color.BLACK);
		btnMesInfos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnMesInfos.setBackground(Color.WHITE);
		btnMesInfos.setBounds(10, 159, 107, 23);
		panel.add(btnMesInfos);
		btnMesInfos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				MesInfos retour = new MesInfos(nom,prenom,email,id);
				retour.setVisible(true);
				retour.setLocationRelativeTo(null);
			}
		});
		
		JButton btnMesEmprunts = new JButton("EMPRUNTER");
		btnMesEmprunts.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnMesEmprunts.setBounds(10, 210, 107, 23);
		panel.add(btnMesEmprunts);
		btnMesEmprunts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Emprunts frame = new Emprunts(nom,prenom,email,id);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		
		JButton btnMesRetours = new JButton("RETOURNER");
		btnMesRetours.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnMesRetours.setBounds(10, 263, 107, 23);
		panel.add(btnMesRetours);
		btnMesRetours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				MaterielRetourner retour = new MaterielRetourner(nom,prenom,email,id);
				retour.setVisible(true);
				retour.setLocationRelativeTo(null);
			}
		});
		
		
		JLabel lblListeDesMateriels = new JLabel("MATERIELS A EMPRUNTER");
		lblListeDesMateriels.setHorizontalAlignment(SwingConstants.CENTER);
		lblListeDesMateriels.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblListeDesMateriels.setBounds(139, 11, 367, 28);
		getContentPane().add(lblListeDesMateriels);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(136, 50, 370, 180);
		getContentPane().add(scrollPane);
		model = new DefaultTableModel();
		table = new JTable();
		table.setGridColor(Color.LIGHT_GRAY);
		table.setRowHeight(30);
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
	    table.getTableHeader().setBackground(new Color(30, 144, 255));
	    table.getTableHeader().setForeground(Color.WHITE);
	    table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				tfId.setText(model.getValueAt(i, 0).toString());
				tfNom.setText(model.getValueAt(i, 1).toString());
				tfMarque.setText(model.getValueAt(i, 2).toString());
				tfQuantite.setText(model.getValueAt(i, 3).toString());
			}
		});
		scrollPane.setViewportView(table);
		model = new DefaultTableModel();
		table.setModel(model);
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
		panel_1.setBounds(136, 241, 370, 142);
		getContentPane().add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(20, 11, 96, 14);
		panel_1.add(lblNewLabel_1);
		
		tfId = new JTextField();
		tfId.setColumns(10);
		tfId.setBounds(20, 25, 120, 20);
		panel_1.add(tfId);
		
		JLabel Quantite = new JLabel("Quantite");
		Quantite.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Quantite.setBounds(20, 54, 96, 14);
		panel_1.add(Quantite);
		
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
		
		JLabel Marque = new JLabel("Marque");
		Marque.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Marque.setBounds(214, 54, 96, 14);
		panel_1.add(Marque);
		
		tfMarque = new JTextField();
		tfMarque.setColumns(10);
		tfMarque.setBounds(214, 68, 120, 20);
		panel_1.add(tfMarque);
		
		
		JButton btnEmprunter = new JButton("Emprunter");
		btnEmprunter.setBounds(124, 108, 108, 23);
		panel_1.add(btnEmprunter);
		btnEmprunter.setForeground(Color.WHITE);
		btnEmprunter.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEmprunter.setBackground(new Color(0, 128, 64));
		
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

		btnEmprunter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MaterielClass mat = new MaterielClass (" "," ",0);
				int currentStock = Integer.parseInt(tfQuantite.getText().toString());

		        // Vérifier si le stock est suffisant
		        if (currentStock <= 0) {
		            JOptionPane.showMessageDialog(null, "L'emprunt ne peut pas être exécuté, le stock est épuisé.", "Alerte", JOptionPane.WARNING_MESSAGE);
		            return; // Arrêter l'exécution si le stock est insuffisant
		        }

		        // Si le stock est suffisant, continuer avec l'emprunt
		        int newStock = currentStock - 1;
				int idMat = Integer.parseInt(tfId.getText().toString());
				LocalDate dateEmp = LocalDate.now();
				EmpruntClass doemp = new EmpruntClass(0,0);
				doemp.doEmprunt(id, idMat,dateEmp);
				mat.UpdateMatAfterEmprunt(newStock, idMat);
				dispose();
				MesInfos etf = new MesInfos(nom, prenom, email, id);
				etf.setVisible(true);
				etf.setLocationRelativeTo(null);
			}
		});
	}
}
