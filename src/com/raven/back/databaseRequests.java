package com.raven.back;

import java.sql.*;

import javax.swing.JLabel;

public class databaseRequests extends javax.swing.JPanel {
	public static boolean checkLogin(String username, String password) {

		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			String mysqlUrl = "jdbc:mysql://localhost/museo";
			Connection con = DriverManager.getConnection(mysqlUrl, "root", "");
			String query = "SELECT mail, password FROM utilisateur WHERE mail = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getString("mail").equals(username) && rs.getString("password").equals(password)) {
					System.out.print("Connexion r�ussie !");
					return true;
				} else {
					System.out.print("Connexion �chou�e, utilisateur ou mot de passe incorrect ! ");
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
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
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
}
