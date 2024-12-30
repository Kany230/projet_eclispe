package projetPoo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import LesBases.EtudiantClass;
 
public class Inscription extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField tfnom;
	private JTextField tfprenom;
	private JTextField tfemail;
	private JPasswordField tfpassword;
	private JPasswordField tfpasswordC;

	/**
	 * Launch the application.
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inscription window = new Inscription();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} */

	/**
	 * Create the application.
	 */
	public Inscription() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setBounds(100, 100, 538, 460);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 153));
		panel.setBorder(new LineBorder(new Color(0, 0, 153)));
		panel.setBounds(0, 0, 162, 423);
		getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Inscrivez-vous");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(172, 11, 318, 38);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nom");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(182, 67, 251, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Prenom");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(182, 114, 251, 14);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Email");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(182, 161, 251, 14);
		getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Mot de passe");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_3.setBounds(182, 206, 251, 14);
	    getContentPane().add(lblNewLabel_1_3);
		
		tfnom = new JTextField();
		tfnom.setBounds(181, 83, 291, 20);
		getContentPane().add(tfnom);
		tfnom.setColumns(10);
		
		tfprenom = new JTextField();
		tfprenom.setColumns(10);
		tfprenom.setBounds(182, 130, 291, 20);
		getContentPane().add(tfprenom);
		
		tfemail = new JTextField();
		tfemail.setColumns(10);
		tfemail.setBounds(182, 175, 291, 20);
		getContentPane().add(tfemail);
		
		JButton btnInscrire = new JButton("S'inscrire");
		btnInscrire.setForeground(new Color(255, 255, 255));
		btnInscrire.setBackground(new Color(0, 128, 64));
		btnInscrire.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnInscrire.setBounds(292, 311, 89, 23);
		getContentPane().add(btnInscrire);
		btnInscrire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfnom.getText().isBlank()) {
					 JOptionPane.showMessageDialog(null, "Le prenom ne doit pas etre vide!", "Erreur", JOptionPane.ERROR_MESSAGE);
					
				}else if(tfprenom.getText().isBlank()) {
					 JOptionPane.showMessageDialog(null, "Le nom ne doit pas etre vide!", "Erreur", JOptionPane.ERROR_MESSAGE);
						
				}else if(tfemail.getText().isBlank()) {
					 JOptionPane.showMessageDialog(null, "L'adresse email ne doit pas etre vide!", "Erreur", JOptionPane.ERROR_MESSAGE);
						
				}
				else if(!tfemail.getText().endsWith("@univ-thies.sn")) {
					 JOptionPane.showMessageDialog(null, "L'adresse email universitaire est requis!", "Erreur", JOptionPane.ERROR_MESSAGE);
						
				}else if(tfpassword.getPassword().toString().length()<8){
					 JOptionPane.showMessageDialog(null, "Mot de passe trop court", "Erreur", JOptionPane.ERROR_MESSAGE);

						
				}
				else if(tfpassword.getPassword().toString().isBlank() || !java.util.Arrays.equals(tfpassword.getPassword(),tfpasswordC.getPassword())) {
					 JOptionPane.showMessageDialog(null, "Les mots de passe ne correspondent pas", "Erreur", JOptionPane.ERROR_MESSAGE);
						
				}
				else  {
				EtudiantClass et = new EtudiantClass(" "," "," "," ","");
				et.setPrenom(tfnom.getText());
				et.setNom(tfprenom.getText());
				et.setEmail(tfemail.getText());
				et.setPassword(new String(tfpassword.getPassword()));
				et.setPasswordConfirmation(new String(tfpasswordC.getPassword()));
				System.out.println(et.getPrenom()+","+et.getNom()+","+et.getEmail()+","+et.getPassword()+","+et.getPasswordConfirmation());
				et.RegisterDB(et.getPrenom(), et.getNom(), et.getEmail(), et.getPassword(),et.getPasswordConfirmation());
				dispose();
				Menu lf = new Menu();
				lf.setLocationRelativeTo(null);
				lf.setVisible(true); 
				
			}
			}
			
		});
		
		JLabel lblNewLabel_2 = new JLabel("J'ai déjà un compte");
		lblNewLabel_2.setBounds(172, 398, 112, 14);
		getContentPane().add(lblNewLabel_2);
		
		JButton btnConnecter = new JButton("Connecter");
		btnConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 // Action à exécuter lors du clic
		    	// Ouvrir la page d'inscription
		    	dispose();
		    	Connexion pageConnexion = new Connexion();
		    	pageConnexion.setTitle("Connexion");
		    	pageConnexion.setVisible(true);
		    	pageConnexion.setLocationRelativeTo(null);

			}
		});
		btnConnecter.setForeground(Color.WHITE);
		btnConnecter.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnConnecter.setBackground(new Color(0, 128, 64));
		btnConnecter.setBounds(292, 389, 99, 23);
		getContentPane().add(btnConnecter);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Confirmer votre mot de passe");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_3_1.setBounds(182, 252, 251, 14);
		getContentPane().add(lblNewLabel_1_3_1);
		
		tfpassword = new JPasswordField();
		tfpassword.setBounds(182, 221, 290, 20);
		getContentPane().add(tfpassword);
		
		tfpasswordC = new JPasswordField();
		tfpasswordC.setBounds(182, 266, 290, 20);
		getContentPane().add(tfpasswordC);
	}
}
