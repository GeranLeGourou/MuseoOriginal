/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.model;

import javax.swing.Icon;

/**
 *
 * @author franc
 */
public class Model_Item {

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getArtiste() {
        return artiste;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public Icon getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setImage(Icon image) {
        this.image = image;
    }
    
        public String getBibliographie() {
        return bibliographie;
    }

    public void setBibliographie(String bibliographie) {
        this.bibliographie = bibliographie;
    }

    public Model_Item() {
    }

    public Model_Item(int id, String titre, String artiste, String description, String bibliographie, String date, Icon image) {
        this.id = id;
        this.titre = titre;
        this.artiste = artiste;
        this.description = description;
        this.bibliographie = bibliographie;
        this.date = date;
        this.image = image;
    }
    
    int id;
    String titre;
    String artiste;
    String description;
    String bibliographie;
    String date;
    Icon image;
   
}
