/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.model;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author franc
 */
public class Model_Menu {

    public String getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public MenuType getType() {
        return type;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(MenuType type) {
        this.type = type;
    }
    
    public Model_Menu(String icon, String name, MenuType type) {
        this.icon = icon;
        this.name = name;
        this.type = type;
    }
    
    public Model_Menu (){
        
    }
    
    public Icon toIcon(){
        return new ImageIcon(getClass().getResource("/com/raven/icon/"+icon+".png"));
    }
    String icon;
    String name;
    MenuType type;
    
    public static enum MenuType {
        TITLE, MENU, EMPTY
}
}
