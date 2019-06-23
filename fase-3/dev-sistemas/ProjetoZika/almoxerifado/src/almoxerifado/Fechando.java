/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almoxerifado;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 *
 * @author sanvingla
 */
public class Fechando {
    
    public static void windowClosing(JFrame frame) {
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent evt) {
                String[] options = new String[]{
                    ("Continuar"),
                    ("Minimizar"),
                    ("Sair")
                };
                int Answer = JOptionPane.showOptionDialog(null, ("O que deseja fazer?"), "",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                switch (Answer) {
                    case 0:
                        frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                        break;
                    case 1:
                        frame.setVisible(false);
                        SysTray systemtray = new SysTray(frame);
                        break;
                    case 2:
                        System.exit(0);
                }
            }
        });
        
        JRootPane meurootpane = frame.getRootPane();

        //Quando a tecla Esc for pressionada fecha a tela
        meurootpane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESCAPE");
        meurootpane.getRootPane().getActionMap().put("ESCAPE", new AbstractAction("ESCAPE") {
            public void actionPerformed(ActionEvent e) {
                String[] options = new String[]{
                    ("Continuar"),
                    ("Minimizar"),
                    ("Sair")
                };
                int Answer = JOptionPane.showOptionDialog(null, ("O que deseja fazer?"), "",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                switch (Answer) {
                    case 0:
                        frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                        break;
                    case 1:
                        frame.setVisible(false);
                        SysTray systemtray = new SysTray(frame);
                        break;
                    case 2:
                        System.exit(0);
                }

            }
        });
    }
    
}
