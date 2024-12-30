package LesBases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;


public class EtudiantClass {
	
	
	//les variables
	private int id;
	private String nom;
	private String prenom;
	private String email;
	private String password;
	private String passwordConfirmation;
	private int admin;
	String query;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	public EtudiantClass(String prenom, String nom, String email, String password, String passwordConfirmation){
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
		
	}
	public EtudiantClass() {
		super();
		this.prenom = "";
		this.nom = "";
		this.email = "";
		this.password = "";
		this.passwordConfirmation = "";
		
	}
	
	//Inscription d'un etudiant 
	
	public void RegisterDB(String s1, String s2, String s3, String s4, String s5) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {
	        // Initialisation de la connexion à la base de données (utilise DriverManager directement si nécessaire)
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/systeme", "root", "");
	        
	        String query = "INSERT INTO etudiant (nom, prenom, email, password, passwordConfirmation) VALUES(?,?,?,?,?)";
	        pstmt = conn.prepareStatement(query);
	        
	        // Remplissage des paramètres
	        pstmt.setString(1, s1);
	        pstmt.setString(2, s2);
	        pstmt.setString(3, s3);
	        pstmt.setString(4, s4);
	        pstmt.setString(5, s5);
	        
	        // Exécution de la requête
	        pstmt.executeUpdate();
	        
	        // Message de succès
	        JOptionPane.showMessageDialog(null, "Inscription réussie ! Connectez-vous pour accéder à la plateforme.", "Note", JOptionPane.INFORMATION_MESSAGE);
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        e.printStackTrace();
	    } finally {
	        // Fermer les ressources (connexion et PreparedStatement)
	        try {
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	//Enregistrement d'un etudiant dans le compte de l'admin
	 public void RegisterByAdmin(String s1, String s2, String s3) {
		 
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 
		 try {
			 
			// Initialisation de la connexion à la base de données (utilise DriverManager directement si nécessaire)
		        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/systeme", "root", "");
		        String query = "INSERT INTO etudiant(nom,prenom,email) VALUES(?,?,?)";
		        pstmt = conn.prepareStatement(query);
		        pstmt.setString(1,s1);
		        pstmt.setString(2,s2);
		        pstmt.setString(3,s3);
		        pstmt.executeUpdate();
		        JOptionPane.showMessageDialog(null,"Etudiant ajouté avec success","Notification",JOptionPane.INFORMATION_MESSAGE);
		        System.out.println("Données inserées avec success");
		 }catch(SQLException e) {
			 System.out.println(e.getMessage());
			 e.printStackTrace();
		 }
	 }
	 
	 //Fonction qui permet d'identifier un etudiant à travers un email
	 
	  public ResultSet FindEtByEmail(String email) {
		  
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet resultat = null;
	        
	        try {
	        	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/systeme", "root", "");
	            String query = "SELECT * FROM etudiant WHERE email = ?";
	            pstmt = conn.prepareStatement(query);
	            pstmt.setString(1, email);
	            resultat = pstmt.executeQuery();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	            e.printStackTrace();
	        }
	        return resultat;
	    }
	 
	 //Cette fonction permet d'identifier l'admin de l'etudiant
	 
	 public int FindAdmin(String email, String password) {
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 try {
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/systeme", "root", "");
			 String query = "SELECT * FROM etudiant WHERE email = ?";
			 pstmt = conn.prepareStatement(query);
			 pstmt.setString(1, email);
			 try(ResultSet resultat = pstmt.executeQuery()){
				 if(resultat.next()) {
					 if(resultat.getInt("admin")==1) {
						 return 1; //Utilisateur adminstrateur
					 }
					 else {
						 return -1;//Utlisateur etudiant
					 }
				 }else {
					 JOptionPane.showMessageDialog(null, "Email incorrect", "Erreur",JOptionPane.ERROR_MESSAGE);
					 return 0; //email incorrect
				 }
			 }
		 }catch(SQLException e) {
			 e.printStackTrace();
			 return 0; //erreur SQL
		 }
	 }
	 
	 //Fonction qui permet d'afficher les etudiants
	 public ResultSet PrintEt(){
		 
		 Connection conn = null;
		 try {
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/systeme", "root", "");
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery("select id, nom, prenom, email from etudiant");
			 
			 return rs;
		 }catch(SQLException e) {
			 e.printStackTrace();
			 return null;
		 }
	 }
	
	 //Fonction qui permet à l'admin de faire un mis à jour
	 
	 public void UpdateEt(String nom, String prenom, String email, int id) {
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 
		 try {
			 
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/systeme", "root", "");
			 String query = "UPDATE etudiant SET nom = ? , prenom = ? , email = ? WHERE id = ? LIMIT 1";
			 pstmt = conn.prepareStatement(query);
			 pstmt.setString(1, nom);
			 pstmt.setString(2, prenom);
			 pstmt.setString(3, email);
			 pstmt.setInt(4, id);
			 pstmt.executeUpdate();
			 
			 JOptionPane.showMessageDialog(null, "Mis à jour validée avec success","Notification",JOptionPane.INFORMATION_MESSAGE);
			  
		 }catch(SQLException e) {
			 System.out.println(e.getMessage());
			 e.printStackTrace();
		 }
	 }
	
	 //Fonction qui permet à un admin de supprimer un etudiant
	 
	 public void DeleteEt (int id) {
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 try {
			 
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/systeme", "root", "");
			 String query = "DELETE FROM etudiant WHERE id = ? LIMIT 1";
			 pstmt = conn.prepareStatement(query);
			 
			 pstmt.setInt(1, id);
			 pstmt.executeUpdate();
			 JOptionPane.showMessageDialog(null,"Mis à jour validée avec success", "Notification", JOptionPane.INFORMATION_MESSAGE);
			 
		 }catch(SQLException e) {
			 System.out.println(e.getMessage());
			 e.printStackTrace();
		 }
	 }
	 
	 //Changer de mot de passe
	 public boolean UpdatePassword(String email, String currentPassword, String newPassword, String confirmPassword) {
		    // Vérification si les nouveaux mots de passe correspondent
		    if (!newPassword.equals(confirmPassword)) {
		        JOptionPane.showMessageDialog(null, "Les nouveaux mots de passe ne correspondent pas", "Erreur", JOptionPane.ERROR_MESSAGE);
		        return false;
		    }
		    
		    // Connexion à la base de données
		    Connection conn = null;
		    PreparedStatement pst = null;
		    ResultSet rs = null;
		    
		    try {
		        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/systeme", "root", "");
		        
		        // Récupérer le mot de passe actuel dans la base pour l'email fourni
		        String selectQuery = "SELECT password FROM etudiant WHERE email = ?";
		        pst = conn.prepareStatement(selectQuery);
		        pst.setString(1, email);  // Correctement définir le paramètre pour l'email
		        rs = pst.executeQuery();  // Exécuter la requête SELECT

		        if (rs.next()) {  // Si un résultat est trouvé pour l'email
		            String storedPassword = rs.getString("password");

		            // Comparer le mot de passe actuel saisi avec celui dans la base
		            if (storedPassword.equals(currentPassword)) {
		                // Mettre à jour le mot de passe
		                String updateSql = "UPDATE etudiant SET password = ?, passwordConfirmation = ? WHERE email = ?";
		                pst = conn.prepareStatement(updateSql);
		                pst.setString(1, newPassword);  // Définir le nouveau mot de passe
		                pst.setString(2, confirmPassword);  // Définir la confirmation du mot de passe
		                pst.setString(3, email);  // Définir l'email pour le WHERE
		                int result = pst.executeUpdate();  // Exécuter l'UPDATE

		                if (result > 0) {
		                    JOptionPane.showMessageDialog(null, "Mot de passe mis à jour avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
		                    return true;
		                } else {
		                    JOptionPane.showMessageDialog(null, "Échec de la mise à jour du mot de passe", "Erreur", JOptionPane.ERROR_MESSAGE);
		                    return false;
		                }
		            } else {
		                JOptionPane.showMessageDialog(null, "Mot de passe actuel incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
		                return false;
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Email non trouvé", "Erreur", JOptionPane.ERROR_MESSAGE);
		            return false;
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    } finally {
		        // Fermeture des ressources
		        try {
		            if (rs != null) rs.close();
		            if (pst != null) pst.close();
		            if (conn != null) conn.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}

	 
}

      
