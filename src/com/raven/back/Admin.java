package com.raven.back;

public class Admin {
	private String Nom;
	private String Prenom;
	private String Mail;
	private String Num;
	private String Lien;
	
	public Admin(String nom, String prenom, String mail, String num, String lien) {
		super();
		Nom = nom;
		Prenom = prenom;
		Mail = mail;
		Num = num;
		Lien = lien;
	}

	public Admin() {
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getPrenom() {
		return Prenom;
	}

	public void setPrenom(String prenom) {
		Prenom = prenom;
	}

	public String getMail() {
		return Mail;
	}

	public void setMail(String mail) {
		Mail = mail;
	}

	public String getNum() {
		return Num;
	}

	public void setNum(String num) {
		Num = num;
	}

	public String getLien() {
		return Lien;
	}

	public void setLien(String lien) {
		Lien = lien;
	}
	
	
}
