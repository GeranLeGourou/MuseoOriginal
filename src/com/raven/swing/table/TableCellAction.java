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
public class TableCellAction extends DefaultCellEditor{
    private ModelAction data;
    
    public TableCellAction() {
        super(new JCheckBox());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object o, boolean bln, int i, int il) {
        data=(ModelAction)o;
        Action cell = new Action(data);
        cell.setBackground(new Color(239, 244, 255));
        return cell;
    }

    // This method to pass data to cell render when focus lose in cell
    @Override
    public Object getCellEditorValue() {
        return data;
    }
    
    
    
}
