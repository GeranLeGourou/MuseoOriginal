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
public class ModelAction {

    public Model_Admin getAdmin() {
        return admin;
    }

    public EventAction getEvent() {
        return event;
    }

    public void setAdmin(Model_Admin admin) {
        this.admin = admin;
    }

    public void setEvent(EventAction event) {
        this.event = event;
    }

    public ModelAction() {
    }

    public ModelAction(Model_Admin admin, EventAction event) {
        this.admin = admin;
        this.event = event;
    }
    
    
    Model_Admin admin;
    EventAction event;
}
