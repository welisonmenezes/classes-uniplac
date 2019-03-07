/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javax.swing.JPanel;

/**
 *
 * @author Welison
 */
public class PageModel {
    private final String name;
    private final JPanel panel;
    
    public PageModel(String name, JPanel panel) {
        this.name = name;
        this.panel = panel;
    }
    
    public String getName() {
        return name;
    }
    
    public JPanel getPanel() {
        return panel;
    }
}
