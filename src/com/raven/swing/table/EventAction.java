/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.swing.table;

import com.raven.model.Model_Admin;
/**
 *
 * @author franc
 */
public interface EventAction {
    public void delete(Model_Admin admin);
    
    public void update(Model_Admin admin);
}
