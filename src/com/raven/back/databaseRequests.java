package com.raven.back;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

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
				if (rs.getString("mail").equals(username) && decryptage(rs.getString("password")).equals(password)) {
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
			String mysqlUrl = "jdbc:mysql://localhost/museo";
			Connection con = DriverManager.getConnection(mysqlUrl, "root", "");
			
			String query = "SELECT prenom FROM utilisateur WHERE prenom = ?"; 
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, Mail );
			
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
		}
		catch (SQLException e) {
			e.printStackTrace();
			
		} return false;
	}
	
	public static String cryptage(String mdp) {
		String mdpcrypt = "";
        char[] chars = mdp.toCharArray();
        for(char c : chars) {
            c += 5;
            mdpcrypt = mdpcrypt + c;
            System.out.print(mdpcrypt);
        }
        return mdpcrypt;
    }
	
	public static String decryptage(String mdp) {
		String mdpcrypt = "";
        char[] chars = mdp.toCharArray();
        for(char c : chars) {
            c -= 5;
            mdpcrypt = mdpcrypt + c;
        }
        return mdpcrypt;
    }
	
	public static boolean checkPhone(String tel) {
        String regex = "^(?:(?:\\+|00)33[\\s.-]{0,3}(?:\\(0\\)[\\s.-]{0,3})?|0)[1-9](?:(?:[\\s.-]?\\d{2}){4}|\\d{2}(?:[\\s.-]?\\d{3}){2})$";
          Pattern pattern = Pattern.compile(regex);
          Matcher matcher = pattern.matcher(tel);
          return matcher.matches();
    }
	
	public static void addAdmin (String P, String N, String E, String T, String Passwd )  {
		try {
			
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		String mysqlUrl = "jdbc:mysql://localhost/museo";
		Connection con = DriverManager.getConnection(mysqlUrl, "root", "");
		String Prenom = P.toString();
		String Nom = N.toString();
		String Email = E.toString();
		String Telephone = T.toString();
		String Password = cryptage(Passwd);
		
		System.out.println(Passwd);
		System.out.println(Password);
		
		String regex = "^(.+)@(.+)$";

		
		String query = "INSERT INTO utilisateur VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?  ) "; 
		PreparedStatement pstmt = con.prepareStatement(query);

		pstmt.setString(1, Nom );
		pstmt.setString(2, Prenom);
		pstmt.setString(3, Email);
		pstmt.setString(4, Telephone);
		pstmt.setString(5, Password);
		pstmt.setInt(6, 1);
		pstmt.setString(7, "Museo Lyon");
		pstmt.setString(8, "/com/raven/icon/Profile.jpg");
		
		if (userExist(Email) == false && Email.matches(regex) == true ) {
			System.out.println("teeest");
			if (checkPhone(Telephone) == true){
				int rs = pstmt.executeUpdate();
			}
			else {
				System.out.println("dddddzzzz");
			}
		}
		else {
			System.out.println("aaaaaaa");
		}

		
			}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}
	
	public static void addOeuvre (String nom, String artiste, String type_objet, String num, String description, String bibliographie, int pret, String statut, int id_collection, int id_categorie )  {
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			String mysqlUrl = "jdbc:mysql://localhost/museo";
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
	
	public static boolean checkAdminExist(String username) {
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
	
	public static void addImage() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            System.out.println("You chose to copy this file: " +
                selectedFile.getName());

            Path newPath = Paths.get("./src/com/raven/icon", selectedFile.getName());
            try {
                Files.copy(selectedFile.toPath(), newPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	
	public static void deleteOeuvre(String name) {
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			String mysqlUrl = "jdbc:mysql://localhost/museo";
			Connection con = DriverManager.getConnection(mysqlUrl, "root", "");
			
			String query = "DELETE FROM oeuvre WHERE nom = ?"; 
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, name );
			pstmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	public static void deleteAdmin(String mail) {
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			String mysqlUrl = "jdbc:mysql://localhost/museo";
			Connection con = DriverManager.getConnection(mysqlUrl, "root", "");
			
			String query = "DELETE FROM utilisateur WHERE mail = ?"; 
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, mail );
			pstmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	public static int countOeuvre() {
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			String mysqlUrl = "jdbc:mysql://localhost/museo";
			Connection con = DriverManager.getConnection(mysqlUrl, "root", "");
			
			String query = "SELECT COUNT(id_oeuvre) FROM oeuvre"; 
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				return rs.getInt("COUNT(id_oeuvre)");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			
		}
		return 0;
	}
	
	public static int countCategorie() {
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			String mysqlUrl = "jdbc:mysql://localhost/museo";
			Connection con = DriverManager.getConnection(mysqlUrl, "root", "");
			
			String query = "SELECT COUNT(id_categorie) FROM categorie"; 
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				return rs.getInt("COUNT(id_categorie)");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			
		}
		return 0;
	}
	
	public static int countArtiste() {
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			String mysqlUrl = "jdbc:mysql://localhost/museo";
			Connection con = DriverManager.getConnection(mysqlUrl, "root", "");
			
			String query = "SELECT COUNT( DISTINCT artiste) as artistes FROM oeuvre;"; 
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				return rs.getInt("artistes");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			
		}
		return 0;
	}
}
