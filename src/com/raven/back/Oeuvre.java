package com.raven.back;

public class Oeuvre {
	
	private String Nom;
	private String Artiste;
	private String Description;
	private String Bibliographie;
	private int Pret;
	private String Statut;
	private String Lien;
	
	public Oeuvre(String nom, String artiste, String description, String bibliographie, int pret, String statut, String lien) {
		super();
		Nom = nom;
		Artiste = artiste;
		Description = description;
		Bibliographie = bibliographie;
		Pret = pret;
		Statut = statut;
		Lien = lien;
	}
	
	public Oeuvre() {
	}

	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getArtiste() {
		return Artiste;
	}
	public void setArtiste(String artiste) {
		Artiste = artiste;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getBibliographie() {
		return Bibliographie;
	}
	public void setBibliographie(String bibliographie) {
		Bibliographie = bibliographie;
	}
	public int getPret() {
		return Pret;
	}
	public void setPret(int pret) {
		Pret = pret;
	}
	public String getStatut() {
		return Statut;
	}
	public void setStatut(String statut) {
		Statut = statut;
	}
	
	public String getLien() {
		return Lien;
	}
	public void setLien(String lien) {
		Lien = lien;
	}
	
	
}
