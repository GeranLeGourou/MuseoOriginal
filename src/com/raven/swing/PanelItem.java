/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.swing;

import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author franc
 */
public class PanelItem extends JPanel {

    public PanelItem() {
        setBackground(Color.WHITE);
        setLayout(new WrapLayout(WrapLayout.LEFT, 20, 20));
    }
    
}

