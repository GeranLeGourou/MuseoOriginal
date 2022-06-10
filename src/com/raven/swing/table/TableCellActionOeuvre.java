/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.swing.table;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author franc
 */
public class TableCellActionOeuvre extends DefaultCellEditor{

    private ModelActionOeuvre data;
    public TableCellActionOeuvre() {
        super(new JCheckBox());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object o, boolean bln, int i, int il) {
        data = (ModelActionOeuvre)o;
        ActionOeuvre cell = new ActionOeuvre(data);
        cell.setBackground(new Color(239, 244, 255));
        return cell;
    }

    // this method to pass data to cell rendre when focus lose in cell
    @Override
    public Object getCellEditorValue() {
        return data;
    }
    
    
    
    
    
}
