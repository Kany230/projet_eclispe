package LesBases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class MaterielClass {
	
	//Les variables
	private int id;
	private String nom;
	private String marque;
	private int stock;
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
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public MaterielClass(String nom, String marque, int stock) {
		super();
		this.nom = nom;
		this.marque = marque;
		this.stock = stock;
		
	}
	//fonction permettant d'afficher les materiels
	
	public ResultSet PrintMat(){
		 
		 Connection conn = null;
		 try {
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/systeme", "root", "");
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery("select * from materiel");
			 
			 return rs;
		 }catch(SQLException e) {
			 e.printStackTrace();
			 return null;
		 }
	 }
	
	//Fonction qui permet d'ajouter un materiel
 public void AddMat(String s1, String s2, int i) {
		 
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 
		 try {
			 
			// Initialisation de la connexion à la base de données (utilise DriverManager directement si nécessaire)
		        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/systeme", "root", "");
		        String query = "INSERT INTO materiel(nom,marque,stock) VALUES(?,?,?)";
		        pstmt = conn.prepareStatement(query);
		        pstmt.setString(1,s1);
		        pstmt.setString(2,s2);
		        pstmt.setInt(3,i);
		        pstmt.executeUpdate();
		        JOptionPane.showMessageDialog(null,"Materiel ajouté avec success","Notification",JOptionPane.INFORMATION_MESSAGE);
		        System.out.println("Données inserées avec success");
		 }catch(SQLException e) {
			 System.out.println(e.getMessage());
			 e.printStackTrace();
		 }
	 }
 
 //Fonction qui permet de faire un mis à jour
 public void UpdateMat(String nom, String marque, int stock, int id) {
	 Connection conn = null;
	 PreparedStatement pstmt = null;
	 
	 try {
		 
		 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/systeme", "root", "");
		 String query = "UPDATE materiel SET nom = ? , marque = ? , stock = ? WHERE id = ? LIMIT 1";
		 pstmt = conn.prepareStatement(query);
		 pstmt.setString(1, nom);
		 pstmt.setString(2, marque);
		 pstmt.setInt(3, stock);
		 pstmt.setInt(4, id);
		 pstmt.executeUpdate();
		 
		 JOptionPane.showMessageDialog(null, "Mis à jour validée avec success","Notification",JOptionPane.INFORMATION_MESSAGE);
		 System.out.println("Mis à jour avec success");
	 }catch(SQLException e) {
		 System.out.println(e.getMessage());
		 e.printStackTrace();
	 }
 }
 
 //Fonction qui permet à un admin de supprimer un materiel
 
 public void DeleteMat (int id) {
	 Connection conn = null;
	 PreparedStatement pstmt = null;
	 try {
		 
		 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/systeme", "root", "");
		 String query = "DELETE FROM materiel WHERE id = ? LIMIT 1";
		 pstmt = conn.prepareStatement(query);
		 
		 pstmt.setInt(1, id);
		 pstmt.executeUpdate();
		 JOptionPane.showMessageDialog(null,"Suppression validée avec success", "Notification", JOptionPane.INFORMATION_MESSAGE);
		 System.out.println("Supprimer avec success");
	 }catch(SQLException e) {
		 System.out.println(e.getMessage());
		 e.printStackTrace();
	 }
 }
 
 //Diminuer la quantiter du stock apres un emprunt
 public void UpdateMatAfterEmprunt(int stock, int id) {
	 Connection conn = null;
	 PreparedStatement pstmt = null;
	 try {
		 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/systeme", "root", "");
		 query = "UPDATE materiel SET stock = ? WHERE id = ? LIMIT 1";
		 pstmt = conn.prepareStatement(query);
		 pstmt.setInt(1,stock);
		 pstmt.setInt(2, id);
		 pstmt.executeUpdate();
		 PrintMat();
	 }catch(SQLException e) {
		 System.out.println(e.getMessage());
		 e.printStackTrace();
	 }
 }
	
	

}
