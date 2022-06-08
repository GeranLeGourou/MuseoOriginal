/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.model;

import com.raven.swing.table.EventAction;
import com.raven.swing.table.ModelProfile;
import com.raven.swing.table.ModelAction;
import javax.swing.Icon;
/**
 *
 * @author franc
 */
public class Model_Admin {

    public Icon getIcon() {
        return icon;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Model_Admin() {
        
    }

    public Model_Admin(Icon icon, String prenom, String nom, String email, String telephone) {
        this.icon = icon;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.telephone = telephone;
    }
    
    
    Icon icon;
    String prenom;
    String nom;
    String email;
    String telephone;
    public Object[] toRowTable(EventAction event) {
        return new Object[]{new ModelProfile(icon, prenom), nom, email,telephone,new ModelAction(this, event)};
    }
}
