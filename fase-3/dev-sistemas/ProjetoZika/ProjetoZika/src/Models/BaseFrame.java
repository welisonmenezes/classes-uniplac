/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Welison
 */
public class BaseFrame extends javax.swing.JFrame{
    public JPanel pTop;
    public JPanel pCenter;
    public JPanel pFilter;
    public JPanel pBottom;
    public JPanel pBG;
    
    public BaseFrame() {
        pBG = new JPanel();
        pTop = new JPanel();
        pCenter = new JPanel();
        pFilter = new JPanel();
        pBottom = new JPanel();
    }
    
    public void addTopContent(String title) {
        JLabel label = new JLabel();
        label.setFont(new java.awt.Font("Tahoma", 1, 24));
        label.setForeground(new java.awt.Color(255, 255, 255));
        label.setText(title);
        pTop.add(label);
    }
    
    public void createBaseLayout() {
        
        this.pBG.setLayout(new BorderLayout());
        this.pBG.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        this.pBG.setBackground(new Color(37, 38, 39));
        
        this.pTop.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.pTop.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
        this.pTop.setOpaque(false);
        
        this.pCenter.setLayout(new BorderLayout());
        this.pCenter.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.pCenter.setOpaque(false);
        
        this.pBottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.pBottom.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
        this.pBottom.setOpaque(false);
         
        this.add(this.pBG, BorderLayout.CENTER);
        this.pBG.add(this.pTop, BorderLayout.NORTH);
        this.pBG.add(this.pCenter, BorderLayout.CENTER);
        this.pBG.add(this.pBottom, BorderLayout.SOUTH);
    }
}
