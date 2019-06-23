/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almoxerifado;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

/**
 *
 * @author sanvingla
 */
public class FocosCor {
    
    public static void ganharfocos(JTextField txtField){
        txtField.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
                txtField.setBackground(Color.yellow); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void focusLost(FocusEvent e) {
                txtField.setBackground(Color.white); //To change body of generated methods, choose Tools | Templates.
            }
            
        });
    
}
    
}
