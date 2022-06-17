package com.raven.back;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;

public class databaseRequests extends javax.swing.JPanel {

	public javax.swing.JComboBox<String> getjComboBox2() {
		return jComboBox2;
	}

	public void setjComboBox2(javax.swing.JComboBox<String> jComboBox2) {
		this.jComboBox2 = jComboBox2;
	}

	public javax.swing.JComboBox<String> getjComboBox3() {
		return jComboBox3;
	}

	public void setjComboBox3(javax.swing.JComboBox<String> jComboBox3) {
		this.jComboBox3 = jComboBox3;
	}

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

	public static boolean userExist(String Mail) {
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			String mysqlUrl = "jdbc:mysql://localhost/museo_2";
			Connection con = DriverManager.getConnection(mysqlUrl, "root", "");

			String query = "SELECT prenom FROM utilisateur WHERE prenom = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, Mail);

			final ResultSet rs = pstmt.executeQuery();
			System.out.print("AAAAAAAAAAAA");
			while (rs.next()) {
				if (rs.getString("mail").equals(Mail)) {
					return true;
				} else {
					System.out.print("Compte inexistant");
					return false;

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return false;
	}

	public static String cryptage(String mdp) {
		String mdpcrypt = "";
		char[] chars = mdp.toCharArray();
		for (char c : chars) {
			c += 5;
			mdpcrypt = mdpcrypt + c;
			System.out.print(mdpcrypt);
		}
		return mdpcrypt;
	}

	public static boolean checkPhone(String tel) {
		String regex = "^(?:(?:\\+|00)33[\\s.-]{0,3}(?:\\(0\\)[\\s.-]{0,3})?|0)[1-9](?:(?:[\\s.-]?\\d{2}){4}|\\d{2}(?:[\\s.-]?\\d{3}){2})$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(tel);
		return matcher.matches();
	}

	public static void addAdmin(String P, String N, String E, String T, String Passwd) {
		try {

			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			String mysqlUrl = "jdbc:mysql://localhost/museo_2";
			Connection con = DriverManager.getConnection(mysqlUrl, "root", "");
			String Prenom = P.toString();
			String Nom = N.toString();
			String Email = E.toString();
			String Telephone = T.toString();
			String Password = cryptage(Passwd);

			System.out.println(Passwd);
			System.out.println(Password);

			String regex = "^(.+)@(.+)$";

			String query = "INSERT INTO utilisateur VALUES (NULL, ?, ?, ?, ?, ?, ?, ?  ) ";
			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, Nom);
			pstmt.setString(2, Prenom);
			pstmt.setString(3, Email);
			pstmt.setString(4, Telephone);
			pstmt.setString(5, Password);
			pstmt.setInt(6, 1);
			pstmt.setString(7, "Museo Lyon");

			if (userExist(Email) == false && Email.matches(regex) == true) {
				System.out.println("teeest");
				if (checkPhone(Telephone) == true) {
					int rs = pstmt.executeUpdate();
				} else {
					System.out.println("dddddzzzz");
				}
			} else {
				System.out.println("aaaaaaa");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	public static String getCollection(String libelle) {
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			String mysqlUrl = "jdbc:mysql://localhost/museo_2";
			Connection con = DriverManager.getConnection(mysqlUrl, "root", "");

			String query = "SELECT id_collection FROM collections WHERE libelle = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, libelle);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.print(rs);
				return rs.getString("id_collection");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}return "aOOOOOOOO";
	}

	public static String getCategorie(String libe) {
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			String mysqlUrl = "jdbc:mysql://localhost/museo_2";
			Connection con = DriverManager.getConnection(mysqlUrl, "root", "");

			String query = "SELECT id_categorie FROM categorie WHERE libelle = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, libe);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.print(rs);
				return rs.getString("id_categorie");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} return "***";
	}

	public static void addOeuvre(String titre, String artiste, String type_objet, String num, String description,
			String bibliographie, int pret, Object statut, Object collection, Object categorie) {
		try {

			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			String mysqlUrl = "jdbc:mysql://localhost/museo_2";
			Connection con = DriverManager.getConnection(mysqlUrl, "root", "");

			String Titre = titre;
			String Artiste = artiste;
			String Type_objet = type_objet;
			String Num = num;
			String Description = description;
			String Bibliographie = bibliographie;

			int Pret = pret;
			String Statut = statut.toString();
			System.out.println(Statut);

			String temp = collection.toString();
			System.out.print(temp);
			String Collection = getCollection(temp);

			String temp1 = categorie.toString();
			System.out.print(temp1);
			String Categorie = getCategorie(temp1);

			/*
			 * String queryCateg = "SELECT id_categorie FROM categorie WHERE libelle = ?";
			 * PreparedStatement pstmt2 = con.prepareStatement(queryCateg);
			 * pstmt2.setString(1, Categorie); System.out.println(pstmt2); int cat =
			 * pstmt2.executeUpdate(); System.out.println(pstmt2);
			 * 
			 */
			String query = "INSERT INTO oeuvre VALUES (NULL, ?, ? , ?, ?, ?, ?, ?, ? , ? , ? , ?) ";

			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, Titre);
			pstmt.setString(2, Artiste);
			pstmt.setString(3, Type_objet);
			pstmt.setString(4, Num);
			pstmt.setString(5, Description);
			pstmt.setString(6, Bibliographie);
			pstmt.setInt(7, Pret);
			pstmt.setString(8, Statut);
			pstmt.setString(9, Collection);
			pstmt.setString(10, Categorie);
			pstmt.setString(11, "/com/raven/icon/bijoux1.jpg"); 

			System.out.println(pstmt);
			int rs = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	public static boolean checkAdminExist(String username) {
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			String mysqlUrl = "jdbc:mysql://localhost/museo_2";
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
			String mysqlUrl = "jdbc:mysql://localhost/museo_2";
			Connection con = DriverManager.getConnection(mysqlUrl, "root", "");
			String query = "SELECT * FROM oeuvre";
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Oeuvre oeuvre = new Oeuvre();
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
				Admin admin = new Admin();
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
