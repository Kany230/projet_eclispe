package projetPoo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import LesBases.EmpruntClass;

public class MesInfos  extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MesInfos window = new MesInfos("","","","",);
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
	public MesInfos(String nom, String prenom, String email, int id) {
		
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
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(21, 96, 47, 19);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1 = new JLabel(nom);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(70, 94, 47, 23);
		panel.add(lblNewLabel_1);
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(136, 50, 370, 238);
		getContentPane().add(scrollPane);
		//Creation et configuration de la table JTable
		table = new JTable();
		scrollPane.setViewportView(table);
		model = new DefaultTableModel();
		table.setModel(model);
		table.setGridColor(Color.LIGHT_GRAY);
		table.setRowHeight(30);
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
	    table.getTableHeader().setBackground(new Color(30, 144, 255));
	    table.getTableHeader().setForeground(Color.WHITE);
	    table.setFont(new Font("Arial", Font.PLAIN, 14));
		//Definition des entete des colonnes
		Object [] column = {"ID","Nom du materiel","Marque","Etat"};
		model.setColumnIdentifiers(column);
		//Préparation d'un tableau pour les lignes
	    Object [] row = new Object[100];
//	   //Mettre ma liste d'emprunt
	    EmpruntClass doemp1 = new EmpruntClass(0,0);
	    ResultSet listEmp = doemp1.PrintEmpHistorique(id);
	    try {
			while (listEmp.next()){
				row[0] = listEmp.getInt(1);
				row[1]= listEmp.getString(2);
				row[2]= listEmp.getString(3);
				row[3]= listEmp.getString(4);
				
				model.addRow(row);
				
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
			e.printStackTrace();
		}
	    
	    
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

	    
		JLabel lblNewLabel = new JLabel("LISTE DES MATERIELS QUE J'AI EMPRUNTER");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(139, 11, 367, 28);
	    getContentPane().add(lblNewLabel);
	    
	    JLabel lblNewLabel_4 = new JLabel("Voudriez-vous changer de mot de passe ?");
	    lblNewLabel_4.setBounds(139, 402, 250, 14);
	    getContentPane().add(lblNewLabel_4);
	    
	    JButton btnChangerMDP = new JButton("Changer");
	    btnChangerMDP.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		dispose();
	    		ChangerMDP mdp = new ChangerMDP();
	    		mdp.setVisible(true);
	    		mdp.setLocationRelativeTo(null);
	    	}
	    });
	    btnChangerMDP.setForeground(Color.WHITE);
	    btnChangerMDP.setFont(new Font("Tahoma", Font.BOLD, 12));
	    btnChangerMDP.setBackground(new Color(0, 128, 64));
	    btnChangerMDP.setBounds(389, 397, 89, 23);
	    getContentPane().add(btnChangerMDP);
	}
}
