package com.raven.back;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JLabel;

public class databaseRequests extends javax.swing.JPanel {
	public static boolean checkLogin(String username, String password) {

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			String mysqlUrl = "jdbc:mysql://localhost/museo";
			Connection con = DriverManager.getConnection(mysqlUrl, "root", "");
			String query = "SELECT mail, password FROM utilisateur WHERE mail = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getString("mail").equals(username) && rs.getString("password").equals(password)) {
					System.out.print("Connexion réussie !");
					return true;
				} else {
					System.out.print("Connexion échouée, utilisateur ou mot de passe incorrect ! ");
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean checkAdmin(String username) {
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			String mysqlUrl = "jdbc:mysql://localhost/museo";
			Connection con = DriverManager.getConnection(mysqlUrl, "root", "");
			String query = "SELECT admin FROM utilisateur WHERE mail = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt("admin") == 1) {
					return true;
				} else {
					System.out.print("pas Admin");
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void addAdmin (String P, String N, String E, String T, String Passwd )  {
		try {
			
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		String mysqlUrl = "jdbc:mysql://localhost/museo_2";
		Connection con = DriverManager.getConnection(mysqlUrl, "root", "");
		String Prenom = P.toString();
		String Nom = N.toString();
		String Email = E.toString();
		String Telephone = T.toString();
		String Password = Passwd;
		
		
		String query = "INSERT INTO utilisateur VALUES (NULL, ?, ?, ?, ?, ?, ?, ?  ) "; 
		PreparedStatement pstmt = con.prepareStatement(query);

		pstmt.setString(1, Nom );
		pstmt.setString(2, Prenom);
		pstmt.setString(3, Email);
		pstmt.setString(4, Telephone);
		pstmt.setString(5, Password);
		pstmt.setInt(6, 1);
		pstmt.setString(7, "Museo Lyon");
		int rs = pstmt.executeUpdate();

		
			}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}
	
	public static void addOeuvre (String nom, String artiste, String type_objet, String num, String description, String bibliographie, int pret, String statut, int id_collection, int id_categorie )  {
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			String mysqlUrl = "jdbc:mysql://localhost/museo_2";
			Connection con = DriverManager.getConnection(mysqlUrl, "root", "");
			
			String Nom = nom;
			String Artiste = artiste;
			String Type_objet = type_objet;
			String Num = num;
			String Description = description;
			String Bibliographie = bibliographie;
			
			int Pret = pret;
			String Statut = statut; 
			int Id_collection = id_collection;
			int Id_categorie = id_categorie;
	
			String query = "INSERT INTO oeuvre VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) "; 
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, Nom );
			pstmt.setString(2, Artiste );
			pstmt.setString(3, Type_objet);
			pstmt.setString(4, Num);
			pstmt.setString(5,Description);
			pstmt.setString(6, Bibliographie);
			pstmt.setInt(7, Pret);
			pstmt.setString(8, Statut);
			pstmt.setInt(9, Id_collection);
			pstmt.setInt(10, Id_categorie);
			
			int rs = pstmt.executeUpdate();
			}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}
	
	
	public static ArrayList<Oeuvre> getAllOeuvres() {
		ArrayList<Oeuvre> listOeuvre = new ArrayList<Oeuvre>();
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			String mysqlUrl = "jdbc:mysql://localhost/museo_2";
			Connection con = DriverManager.getConnection(mysqlUrl, "root", "");
			String query = "SELECT * FROM oeuvre";
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Oeuvre oeuvre  = new Oeuvre();
				oeuvre.setArtiste(rs.getString("artiste"));
				oeuvre.setNom(rs.getString("nom"));
				oeuvre.setBibliographie(rs.getString("bibliographie"));
				oeuvre.setDescription(rs.getString("description"));
				oeuvre.setPret(rs.getInt("pret"));
				oeuvre.setLien(rs.getString("lien"));
				listOeuvre.add(oeuvre);
			}
			return listOeuvre;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOeuvre;
	}
	
	public static ArrayList<Admin> getAllAdmins() {
		ArrayList<Admin> listAdmin = new ArrayList<Admin>();
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			String mysqlUrl = "jdbc:mysql://localhost/museo_2";
			Connection con = DriverManager.getConnection(mysqlUrl, "root", "");
			String query = "SELECT * FROM utilisateur WHERE admin = 1";
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Admin admin  = new Admin();
				admin.setNom(rs.getString("nom"));
				admin.setPrenom(rs.getString("prenom"));
				admin.setNum(rs.getString("num"));
				admin.setMail(rs.getString("mail"));
				admin.setLien(rs.getString("lien"));
				listAdmin.add(admin);
			}
			return listAdmin;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listAdmin;

	}
}
