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
public class ModelActionOeuvre {

    public Model_Oeuvre getOeuvre() {
        return oeuvre;
    }

    public EventActionOeuvre getEventOeuvre() {
        return eventOeuvre;
    }

    public void setOeuvre(Model_Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
    }

    public void setEventOeuvre(EventActionOeuvre eventOeuvre) {
        this.eventOeuvre = eventOeuvre;
    }

    public ModelActionOeuvre() {
    }

    public ModelActionOeuvre(Model_Oeuvre oeuvre, EventActionOeuvre eventOeuvre) {
        this.oeuvre = oeuvre;
        this.eventOeuvre = eventOeuvre;
    }
    
    
    
    Model_Oeuvre oeuvre;
    EventActionOeuvre eventOeuvre;
}
