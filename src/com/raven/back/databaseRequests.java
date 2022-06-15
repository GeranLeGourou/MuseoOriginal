package com.raven.back;

import java.sql.*;
import java.util.ArrayList;

public class databaseRequests {
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
	
	public static ArrayList<Oeuvre> getAllOeuvres() {
		ArrayList<Oeuvre> listOeuvre = new ArrayList<Oeuvre>();
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			String mysqlUrl = "jdbc:mysql://localhost/museo";
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
			String mysqlUrl = "jdbc:mysql://localhost/museo";
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
