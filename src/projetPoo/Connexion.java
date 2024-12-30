package projetPoo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import LesBases.EtudiantClass;

public class Connexion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField email;
	private JPasswordField password;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connexion window = new Connexion();
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
	public Connexion () {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setBounds(100, 100, 538, 460);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Connectez-vous");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(10, 38, 504, 57);
	    getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 122, 402, 22);
		getContentPane().add(lblNewLabel_1);
		
		email = new JTextField();
		email.setBounds(10, 155, 504, 28);
		getContentPane().add(email);
		email.setColumns(10);
		
		JLabel lblNewLabel_1_3 = new JLabel("Mot de passe");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_3.setBounds(10, 204, 251, 14);
		getContentPane().add(lblNewLabel_1_3);
		
		JButton btnConnecter = new JButton("Connecter");
		btnConnecter.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnConnecter.setForeground(new Color(255, 255, 255));
		btnConnecter.setBackground(new Color(0, 128, 64));
		btnConnecter.setBounds(176, 289, 110, 28);
		getContentPane().add(btnConnecter);
		btnConnecter.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Créer un objet EtudiantClass
		        EtudiantClass et = new EtudiantClass("", "", "", "", "");

		        // On remplace l'email par la valeur saisie par l'utilisateur
		        et.setEmail(email.getText());

		        // Récupérer le mot de passe saisi
		        char[] enteredPassword = password.getPassword();

		        // Vérification si l'email ou le mot de passe est vide
		        if (email.getText().isBlank() || enteredPassword.length == 0) {
		            JOptionPane.showMessageDialog(null, "email ou mot de passe ne doivent pas être vides!", "Erreur", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        // Récupérer le mot de passe de la base de données via FindEtByEmail
		        ResultSet resultSet = et.FindEtByEmail(et.getEmail());
		        String passwordDB = "";
		        try {
		            if (resultSet != null && resultSet.next()) {
		                passwordDB = resultSet.getString("password");  // Le mot de passe dans la base
		            } else {
		                JOptionPane.showMessageDialog(null, "Email non trouvé", "Erreur", JOptionPane.ERROR_MESSAGE);
		                return;
		            }
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		        } finally {
		            try {
		                if (resultSet != null) resultSet.close();  // Fermer le ResultSet après utilisation
		            } catch (SQLException e1) {
		                e1.printStackTrace();
		            }
		        }

		        // Comparer le mot de passe saisi avec celui de la base
		        if (passwordDB.equals(new String(enteredPassword))) {
		            // Mot de passe correct, on peut vérifier le rôle de l'utilisateur
		            int loginResult = et.FindAdmin(et.getEmail(), passwordDB);  // Utilise le mot de passe récupéré depuis la base
		            
		            if (loginResult == -1) {
		                // Utilisateur étudiant
		                ResultSet donneeEt = et.FindEtByEmail(et.getEmail());
		                String prenom = "";
		                String nom = "";
		                String email = "";
		                int id = 0;
		                try {
		                    if (donneeEt != null && donneeEt.next()) {
		                        prenom = donneeEt.getString("prenom");
		                        nom = donneeEt.getString("nom");
		                        email = donneeEt.getString("email");
		                        id = donneeEt.getInt("id");
		                    }
		                    if (donneeEt != null) donneeEt.close();
		                } catch (SQLException e1) {
		                    e1.printStackTrace();
		                } finally {
		                    try {
		                        if (donneeEt != null) donneeEt.close();
		                    } catch (SQLException e1) {
		                        e1.printStackTrace();
		                    }
		                }

		                // Ouvrir l'interface MesInfos avec les infos de l'étudiant
		                dispose();
		                MesInfos etf = new MesInfos(nom, prenom, email, id);
		                etf.setVisible(true);
		                etf.setLocationRelativeTo(null);

		            } else if (loginResult == 1) {
		                // Utilisateur administrateur
		                ResultSet donneeEt = et.FindEtByEmail(et.getEmail());
		                String nom = "";
		                try {
		                    if (donneeEt != null && donneeEt.next()) {
		                        nom = donneeEt.getString("prenom");
		                    }
		                    if (donneeEt != null) donneeEt.close();
		                } catch (SQLException e1) {
		                    e1.printStackTrace();
		                }

		                // Ouvrir l'interface Admin
		                dispose();
		                Admin LEt = new Admin(nom);
		                LEt.setVisible(true);
		                LEt.setLocationRelativeTo(null);
		            }

		        } else {
		            // Mot de passe incorrect
		            JOptionPane.showMessageDialog(null, "Mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
		        }

		        // Vider le tableau de caractères pour plus de sécurité
		        Arrays.fill(enteredPassword, '0');
		    }
		});

		
		JLabel lblNewLabel_2 = new JLabel("Vous n'avez pas de compte donc inscrivez-vous");
		lblNewLabel_2.setBounds(10, 398, 251, 14);
	    getContentPane().add(lblNewLabel_2);
		
		JButton btnInscrire = new JButton("S'inscrire");
		btnInscrire.setForeground(Color.WHITE);
		btnInscrire.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnInscrire.setBackground(new Color(0, 128, 64));
		btnInscrire.setBounds(266, 389, 89, 23);
		getContentPane().add(btnInscrire);
		btnInscrire.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Action à exécuter lors du clic
		    	// Ouvrir la page d'inscription
		    	dispose();
				Inscription pageInscription = new Inscription();
				pageInscription.setTitle("Inscription");
				pageInscription.setVisible(true);
				pageInscription.setLocationRelativeTo(null);
		    }

		});
		
		password = new JPasswordField();
		password.setBounds(10, 229, 504, 28);
		getContentPane().add(password);
	}

}
