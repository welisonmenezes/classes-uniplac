/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 *
 * @author Welison
 */
public class UtilsStyles {
    
    public static JButton defaultButton(JButton btn) {
        
        btn.setFont(new java.awt.Font("Tahoma", 0, 12));
        btn.setForeground(new Color(8, 253, 216));
        btn.setBorder(BorderFactory.createLineBorder(new Color(8, 253, 216)));
        btn.setContentAreaFilled(false);
        btn.setMargin(new Insets(0, 0, 0, 0));
        btn.setPreferredSize(new Dimension(100, 35));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBackground(new Color(8, 253, 216));
        
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setForeground(new Color(0, 0, 0));
                btn.setContentAreaFilled(true);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setForeground(new Color(8, 253, 216));
                btn.setContentAreaFilled(false);
            }
        });
        
        return btn;
    }
    
    public static JTextField defaultField(JTextField field) {
        field.setBackground(new Color(55, 57, 59));
        field.setForeground(new Color(255, 255, 255));
        field.setCaretColor(new Color(255, 255, 255));
        field.setOpaque(false);
        field.setPreferredSize(new Dimension(200, 39));
        
        return field;
    }
}
