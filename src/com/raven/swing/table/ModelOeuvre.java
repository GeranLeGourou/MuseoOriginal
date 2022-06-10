/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.swing.table;

import javax.swing.Icon;
/**
 *
 * @author franc
 */
public class ModelOeuvre {

    public Icon getImage() {
        return image;
    }

    public String getTitre() {
        return titre;
    }

    public void setImage(Icon image) {
        this.image = image;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public ModelOeuvre() {
    }

    public ModelOeuvre(Icon image, String titre) {
        this.image = image;
        this.titre = titre;
    }
    
    
    
    Icon image;
    String titre;
}
