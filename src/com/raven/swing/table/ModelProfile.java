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
public class ModelProfile {

    public Icon getIcon() {
        return icon;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public ModelProfile() {
    }

    public ModelProfile(Icon icon, String prenom) {
        this.icon = icon;
        this.prenom = prenom;
    }
    
    
    
    Icon icon;
    String prenom;
}
