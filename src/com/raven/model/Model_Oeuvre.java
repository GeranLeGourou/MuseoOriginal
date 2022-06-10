/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.model;

import com.raven.swing.table.EventActionOeuvre;
import com.raven.swing.table.ModelOeuvre;
import com.raven.swing.table.ModelActionOeuvre;
import javax.swing.Icon;

/**
 *
 * @author franc
 */
public class Model_Oeuvre {

    public Icon getImage() {
        return image;
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

    public String getEtat() {
        return etat;
    }

    public void setImage(Icon image) {
        this.image = image;
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

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Model_Oeuvre() {
    }

    public Model_Oeuvre(Icon image, String titre, String artiste, String description, String etat) {
        this.image = image;
        this.titre = titre;
        this.artiste = artiste;
        this.description = description;
        this.etat = etat;
    }
    
    
    
    private Icon image;
    private String titre;
    private String artiste;
    private String description;
    private String etat;
    public Object[]toRowTable(EventActionOeuvre eventOeuvre) {
        return new Object[]{new ModelOeuvre(image, titre), artiste, description, etat, new ModelActionOeuvre(this, eventOeuvre)};
    }
}
