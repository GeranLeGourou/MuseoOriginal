/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.event;

import com.raven.model.Model_Item;
import java.awt.Component;
/**
 *
 * @author franc
 */
public interface EventItem {
    public void itemClick(Component com, Model_Item item);
    
}
