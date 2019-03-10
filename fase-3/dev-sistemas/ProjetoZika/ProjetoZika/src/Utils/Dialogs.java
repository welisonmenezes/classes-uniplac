/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import static Utils.Methods.setEnableRecursively;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import projetozika.Main;

/**
 *
 * @author Welison
 */
public class Dialogs {
    private static JFrame dialog;
    
    public static void showLoadPopup(JComponent context) {
        setEnableRecursively(Main.rootComponent, false);
        setEnableRecursively(context, false);
        dialog = new JFrame();
        dialog.setAlwaysOnTop(true);
        dialog.setDefaultCloseOperation(JDialog .DISPOSE_ON_CLOSE);
        dialog.setSize(200, 200);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.requestFocus();
        dialog.setVisible(false);
	dialog.setUndecorated(true);
	dialog.setVisible(true);
        
        JLabel lImage = new JLabel();
        lImage.setIcon(new ImageIcon(context.getClass().getResource("/sources/ajax-loader.gif")));
        lImage.setText("");
        lImage.setBorder(BorderFactory.createEmptyBorder(75, 0, 0, 0));
        
        JPanel dialogPanel = new JPanel();
        dialogPanel.setBackground(new Color(24,24,24));
        dialogPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        dialogPanel.add(lImage);
        
        dialog.add(dialogPanel);
    }
    
    public static void hideLoadPopup(JComponent context) {
        setEnableRecursively(Main.rootComponent, true);
        setEnableRecursively(context, true);
        dialog.dispose();
    }
}
