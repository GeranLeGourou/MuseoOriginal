/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.swing.table;

import com.raven.model.Model_Oeuvre;
/**
 *
 * @author franc
 */
public interface EventActionOeuvre {
    public void deleteOeuvre(Model_Oeuvre oeuvre);
    
    public void updateOeuvre(Model_Oeuvre oeuvre);
}
