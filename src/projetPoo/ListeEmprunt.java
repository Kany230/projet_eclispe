package projetPoo;

import javax.swing.table.TableCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import LesBases.EmpruntClass;

public class ListeEmprunt extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;
	int id;
	String nom, prenom, nomMat, etat;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListeEmprunt window = new ListeEmprunt();
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
	public ListeEmprunt(String s) {
		
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
				Admin lgF = new Admin("");
				lgF.setVisible(true);
				lgF.setLocationRelativeTo(null);
			}
		});
		btnEtudiants.setForeground(Color.BLACK);
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
		
		JLabel lblNewLabel = new JLabel("LISTE DES EMPRUNTS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(166, 11, 293, 14);
        getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(139, 36, 370, 238);
		getContentPane().add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		model = new DefaultTableModel();
		table.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		table.setGridColor(Color.LIGHT_GRAY);
		table.setRowHeight(30);
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
	    table.getTableHeader().setBackground(new Color(30, 144, 255));
	    table.getTableHeader().setForeground(Color.WHITE);
	    table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setModel(model);
		table.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		Object [] column = {"ID","Nom ","Prenom ","Nom du materiel","Etat"};
		model.setColumnIdentifiers(column);
		Object [] row = new Object[100];
		EmpruntClass doemp = new EmpruntClass(0,0);
		ResultSet listEmp = doemp.PrintEtEmpMat();
		 try {
				while (listEmp.next()){
					row[0] = listEmp.getInt(1);
					row[1]= listEmp.getString(2);
					row[2]= listEmp.getString(3);
					row[3]= listEmp.getString(4);
					row[4]= listEmp.getString(5);
					//row[4]= resultat.getInt(5);
					model.addRow(row);
					
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		
		table.addMouseListener(new MouseAdapter() {
			

			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				id = Integer.parseInt(model.getValueAt(i,0).toString());
				prenom = (model.getValueAt(i, 0).toString());
				nom = (model.getValueAt(i, 1).toString());
				nomMat =(model.getValueAt(i, 2).toString());
				etat =(model.getValueAt(i, 3).toString());

			}
		});
		
		//  personnalisé la dernière colonne
        TableCellRenderer customRenderer = new DefaultTableCellRenderer() {
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		        // Appel du renderer par défaut
		        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		        
		        // Personnalisation de la dernière colonne (par exemple, la colonne d'index table.getColumnCount() - 1)
		        if (column == table.getColumnCount() - 1) {
		            c.setFont(new Font("Arial", Font.BOLD, 14)); // Changer la police
		            c.setForeground(Color.RED); // Changer la couleur du texte en rouge
		            c.setBackground(Color.YELLOW); // Changer la couleur de fond (optionnel)
		            
		            if (isSelected) {
		                c.setBackground(Color.ORANGE); // Couleur spéciale si la cellule est sélectionnée
		            }
		        } else {
		            // Rétablir le style par défaut pour les autres colonnes
		            c.setForeground(Color.BLACK);
		            c.setBackground(Color.WHITE);
		        }
		        return c;
		    }
		};

		// Appliquer ce renderer uniquement à la dernière colonne
		table.getColumnModel().getColumn(table.getColumnCount() - 1).setCellRenderer(customRenderer);

		
		JButton btnValider = new JButton("Valider");
		btnValider.setBackground(new Color(0, 128, 64));
		btnValider.setForeground(new Color(255, 255, 255));
		btnValider.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnValider.setBounds(166, 337, 129, 29);
		getContentPane().add(btnValider);
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				etat = "VALIDER";
				doemp.ValidateStatut(etat,id);
				JOptionPane.showMessageDialog(null, "Emprunt validé ", "Notification",JOptionPane.INFORMATION_MESSAGE);
				dispose();
				ListeEmprunt  listemp = new ListeEmprunt(s);
				listemp.setVisible(true);
				listemp.setLocationRelativeTo(null);
				
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
		
		JButton btnRefuser = new JButton("Refuser");
		btnRefuser.setForeground(Color.WHITE);
		btnRefuser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRefuser.setBackground(new Color(0, 128, 64));
		btnRefuser.setBounds(357, 337, 129, 29);
		getContentPane().add(btnRefuser);
		btnRefuser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				etat = "REFUSE";
				doemp.ValidateStatut(etat,id);
				JOptionPane.showMessageDialog(null, "Emprunt refusé ", "Notification",JOptionPane.INFORMATION_MESSAGE);
				dispose();
				ListeEmprunt  listemp = new ListeEmprunt(s);
				listemp.setVisible(true);
				listemp.setLocationRelativeTo(null);
				
			}
		});
			
	}
}
