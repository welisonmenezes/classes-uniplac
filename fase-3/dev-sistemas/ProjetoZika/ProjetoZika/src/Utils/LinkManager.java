/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Properties;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;

/**
 *
 * @author welis
 */
public class LinkManager {
    
    private final Properties params;
    public final String page;
    private final JButton button;
    private final String action;
    private final KeyStroke keyStroke;
    
    public LinkManager(JButton button, String page, KeyStroke keyStroke, Properties params) {
        this.button = button;
        this.action = button.getText();
        this.page = page;
        this.keyStroke = keyStroke;
        this.params = params;
        
        addHotLink();
    }
    
    private void addHotLink() {
        Action buttonAction;
        buttonAction = new AbstractAction(action) {
            @Override
            public void actionPerformed(ActionEvent evt) {
                actionLink();
            }
        };
        button.setAction(buttonAction);
        button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, "");
        button.getActionMap().put("", buttonAction);
    }
    
    public void actionLink() {
        params.clear();
        Navigation.updateLayout(page, params);
    }
    
    public void addContextMenuAddNew(String page, String title) {
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem jMenuItemAddNew = new JMenuItem();
        jMenuItemAddNew.setText(title);
        
        jMenuItemAddNew.addActionListener((ActionEvent e) -> {
            Navigation.updateLayout(page, params);
        });
        
        jPopupMenu.add(jMenuItemAddNew);
        
        button.addMouseListener( new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            // Se o botão direito do mouse foi pressionado
            if (e.getButton() == MouseEvent.BUTTON3){
                // Exibe o popup menu na posição do mouse.
                jPopupMenu.show(button, e.getX(), e.getY());
            }
          }
        });
    }
}
