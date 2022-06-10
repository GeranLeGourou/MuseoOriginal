/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Francis zhao
package com.raven.swing.table;

import com.raven.swing.scrollbar.ScrollBarCustom;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
/**
 *
 * @author franc
 */
public class TableOeuvre extends JTable{

    public TableOeuvre() {
        setShowHorizontalLines(true);
        setGridColor(new Color(230, 230, 230));
        setRowHeight(80);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object ob, boolean bln, boolean bln1, int i, int i1) {
                TableHeader header = new TableHeader(ob + "");
                if (i1 == 4) {
                    header.setHorizontalAlignment(JLabel.CENTER);
                }
                return header;
            }
            
        });
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object ob, boolean selected, boolean focus, int i, int i1) {
                if(ob instanceof ModelOeuvre) {
                    ModelOeuvre data = (ModelOeuvre)ob;
                    Oeuvre cell = new Oeuvre(data);
                    if(selected){
                        cell.setBackground(new Color(239, 244, 255));
                        
                    } else {
                        cell.setBackground(Color.WHITE);
                    }
                    return cell;
                    
                } else if (ob instanceof ModelActionOeuvre) {
                    ModelActionOeuvre data = (ModelActionOeuvre)ob;
                    ActionOeuvre cell = new ActionOeuvre(data);
                    if(selected){
                        cell.setBackground(new Color(239, 244, 255));
                    } else {
                        cell.setBackground(Color.WHITE);
                    }
                    return cell;
                } else {
                    Component com = super.getTableCellRendererComponent(jtable, ob, selected, focus, i, i1);
                    setBorder(noFocusBorder);
                    com.setForeground(new Color(102, 102, 102));
                    if(selected){
                        com.setBackground(new Color(239, 244, 255));
                        
                    } else {
                        com.setBackground(Color.WHITE);
                    }
                    return com;
                }
            }
            
        });
        
    }

    @Override
    public TableCellEditor getCellEditor(int row, int col) {
        if(col==4) {
            return new TableCellActionOeuvre();
        } else {
            return super.getCellEditor(row, col);
        }
    }
    
    
    
    public void addRow (Object[]row) {
        DefaultTableModel mod = (DefaultTableModel)getModel();
        mod.addRow(row);
    }
    
    
    public void fixTable(JScrollPane scroll) {
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setVerticalScrollBar(new ScrollBarCustom());
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        scroll.setBorder(new EmptyBorder(5, 10, 5, 10));
    }
    
}
