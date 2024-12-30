package projetPoo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Menu extends JFrame{


	

	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
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
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		getContentPane().setBackground(new Color(244, 244, 244));
		getContentPane().setForeground(new Color(255, 255, 255));
		setBounds(100, 100, 514, 311);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 128), 2));
		panel.setBounds(10, 11, 476, 56);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("GESTION DES PRETS MATERIEL");
		lblNewLabel.setForeground(new Color(0, 0, 64));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 26));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 7, 456, 38);
		panel.add(lblNewLabel);
		
		JButton btnInscription = new JButton("INSCRIPTION");
		btnInscription.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnInscription.setForeground(Color.WHITE);
		btnInscription.setBackground(new Color(0, 0, 128));
		btnInscription.setBounds(192, 209, 133, 28);
		getContentPane().add(btnInscription);
		btnInscription.addActionListener(new ActionListener() {
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
		
		
		JButton btnConnexion = new JButton("CONNEXION");
		btnConnexion.setForeground(Color.WHITE);
		btnConnexion.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnConnexion.setBackground(new Color(0, 0, 128));
		btnConnexion.setBounds(192, 170, 133, 28);
		btnConnexion.setBorder(null);
		getContentPane().add(btnConnexion);
		btnConnexion.addActionListener(new ActionListener() {
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
		
	}

	
}
