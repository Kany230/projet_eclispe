package LesBases;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.swing.JOptionPane;

public class EmpruntClass {
	
	/*Les variables*/
	private int idEt;
	private int idMat;
	private String statuts;
	String query;
	
	
	public EmpruntClass(int idEt, int idMat) {
		super();
		this.idEt = idEt;
		this.idMat = idMat;
	}

	public int getIdEt() {
		return idEt;
	}

	public void setIdEt(int idEt) {
		this.idEt = idEt;
	}

	public int getIdMat() {
		return idMat;
	}

	public void setIdMat(int idMat) {
		this.idMat = idMat;
	}

	public String getStatuts() {
		return statuts;
	}

	public void setStatuts(String statuts) {
		this.statuts = statuts;
	}
	
	/*Enregistrement d'un emprunt*/
	public void doEmprunt(int idEt , int idMat , LocalDate dateEmprunt) {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	try {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/systeme", "root", "");
		query = "INSERT INTO emprunt(idEt, idMat, dateEmprunt) VALUES(?,?,?)";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, idEt);
		pstmt.setInt(2, idMat);
		pstmt.setDate(3, Date.valueOf(dateEmprunt));
		pstmt.executeUpdate();
		JOptionPane.showMessageDialog(null, "Veuillez patienter d'ici 24h vous aurez votre reponse","Notification",JOptionPane.INFORMATION_MESSAGE);
	}catch(SQLException e) {
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	
	 
	} 


//Fonction qui permet d'afficher les emprunts des etudiants
public ResultSet PrintEtEmpMat() {
	Connection conn = null;
	ResultSet rs = null;
	try {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/systeme", "root", "");
		query = "SELECT emprunt.emprunt, etudiant.nom, etudiant.prenom, materiel.nom, emprunt.statut FROM etudiant INNER JOIN emprunt ON etudiant.id = emprunt.idET INNER JOIN materiel ON materiel.id = emprunt.idMat ORDER BY emprunt.emprunt ASC";
		Statement stmt = conn.createStatement();
		rs = stmt.executeQuery(query);
	}catch(SQLException e) {
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	return rs;
}

//Fonction qui permet de changer le statut de l'emprunt
public void ValidateStatut(String statut, int id) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	try {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/systeme", "root", "");
		query = "UPDATE emprunt SET statut = ? WHERE emprunt.emprunt = ? LIMIT 1";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, statut);
		pstmt.setInt(2, id);
		
		pstmt.executeUpdate();
	}catch(SQLException e) {
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
}

//Fonction pour la liste des emprunts retourner par un etudiant
public ResultSet PrintEmpRetour(int id) {
	query = "SELECT emprunt.emprunt, materiel.nom, materiel.marque, emprunt.statut " +
            "FROM materiel " +
            "INNER JOIN emprunt ON materiel.id = emprunt.idMat " +
            "INNER JOIN etudiant ON etudiant.id = emprunt.idET " +
            "WHERE etudiant.id = ? AND emprunt.statut ='VALIDER' ";
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/systeme", "root", "");
		 pstmt = conn.prepareStatement(query);
		 pstmt.setInt(1, id);
	     rs = pstmt.executeQuery();
	     return rs;

	}catch(SQLException e) {
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	return null;
}

//fonction permettant d'afficher l'historique de mes emprunts

public ResultSet PrintEmpHistorique(int id) {
		
		String query = "SELECT emprunt.emprunt, materiel.nom, materiel.marque, emprunt.statut " +
             "FROM materiel " +
             "INNER JOIN emprunt ON materiel.id = emprunt.idMat " +
             "INNER JOIN etudiant ON etudiant.id = emprunt.idET " +
             "WHERE etudiant.id = ? ORDER BY emprunt.emprunt DESC ";

				 Connection conn = null;
				 PreparedStatement pstmt = null;
				 ResultSet rs = null;
				 
				 try {
					 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/systeme", "root", "");
				     pstmt = conn.prepareStatement(query);
				     pstmt.setInt(1, id);
				     rs = pstmt.executeQuery();
				     
				     // Pour que le ResultSet soit fermé correctement, vous pouvez le retourner sous une autre forme ou gérer sa fermeture dans l'appelant.
				     return rs;
				     
				 } catch (SQLException e) {
				     System.out.println(e.getMessage());
				     e.printStackTrace();
				 } 
				 return null;
		
	}
	
	






}
