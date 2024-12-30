package projetPoo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;

import LesBases.EtudiantClass;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class ChangerMDP extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField emaiL;
	private JPasswordField mdpO;
	private JPasswordField mdpN;
	private JPasswordField mdpC;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangerMDP window = new ChangerMDP();
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
	public ChangerMDP() {
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
		panel.setBorder(new LineBorder(new Color(0, 0, 153)));
		panel.setBackground(new Color(0, 0, 153));
		panel.setBounds(0, 0, 163, 423);
		getContentPane().add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(173, 92, 251, 14);
		getContentPane().add(lblNewLabel_1);
		
		emaiL = new JTextField();
		emaiL.setColumns(10);
		emaiL.setBounds(172, 108, 291, 20);
		getContentPane().add(emaiL);
		
		JLabel lblNewLabel = new JLabel("Changer de mot de passe");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(172, 21, 318, 38);
		getContentPane().add(lblNewLabel);
		
		JButton btnChanger = new JButton("Changer");
		btnChanger.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String email = emaiL.getText();
		        String oldPwd = new String(mdpO.getPassword());
		        String newPwd = new String(mdpN.getPassword());
		        String confPwd = new String(mdpC.getPassword());

		        // Vérifier que le nouveau mot de passe et la confirmation correspondent
		        if (!newPwd.equals(confPwd)) {
		            return;
		        }

		        // Créer un objet EtudiantClass et récupérer l'étudiant par email
		        EtudiantClass et = new EtudiantClass("", "", "", "", "");
		        et.setEmail(email);

		        ResultSet resultSet = et.FindEtByEmail(et.getEmail());
		        String pwdDB = "";

		        try {
		            if (resultSet != null && resultSet.next()) {
		                pwdDB = resultSet.getString("password");
		            } else {
		                return;
		            }

		            // Vérifier si l'ancien mot de passe est correct
		            if (pwdDB.equals(oldPwd)) {  
		                // Mettre à jour le mot de passe
		                boolean updateSuccess = et.UpdatePassword(email, oldPwd, newPwd, confPwd);

		                if (updateSuccess) {
		                    dispose();
		                    Connexion old = new Connexion();
		                    old.setVisible(true);
		                    old.setLocationRelativeTo(null);
		                } }

		        } catch (SQLException e1) {
		            e1.printStackTrace();
		        } finally {
		            try {
		                if (resultSet != null) resultSet.close();
		            } catch (SQLException e2) {
		                e2.printStackTrace();
		            }
		        }
		    }
		});

		btnChanger.setForeground(Color.WHITE);
		btnChanger.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnChanger.setBackground(new Color(0, 128, 64));
		btnChanger.setBounds(277, 308, 89, 23);
		getContentPane().add(btnChanger);
		
		JLabel lblNewLabel_1_1 = new JLabel("Ancien mot de passe");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(173, 139, 251, 14);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Nouveau mot de passe");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(173, 186, 251, 14);
		getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Confirmer le nouveau mot de passe");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_3.setBounds(173, 234, 251, 14);
		getContentPane().add(lblNewLabel_1_3);
		
		mdpO = new JPasswordField();
		mdpO.setBounds(173, 155, 290, 20);
		getContentPane().add(mdpO);
		
		mdpN = new JPasswordField();
		mdpN.setBounds(173, 203, 290, 20);
		getContentPane().add(mdpN);
		
		mdpC = new JPasswordField();
		mdpC.setBounds(173, 250, 290, 20);
		getContentPane().add(mdpC);
	}
}
