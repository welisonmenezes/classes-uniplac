/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import projetozika.Main;
import projetozika.Pages.Dashboard;
import projetozika.Pages.Fornecedores.AddFornecedor;
import projetozika.Pages.Fornecedores.Fornecedores;
import projetozika.Pages.NotFound;

/**
 *
 * @author Welison
 */
public class UtilsElements {
    
    private static JPanel jBody;
    private static String currentPage = "";
    private static JFrame dialog;
    
    public static void makeFrameFullSize(JFrame aFrame)
    {
        Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        aFrame.setSize(screenSize.width, screenSize.height);
    }
    
    public static void makePanelfullSize(JPanel aFrame)
    {
        Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        aFrame.setSize(screenSize.width, screenSize.height);
    }
    
    public static void positionFrameInCenter(JFrame aFrame) {
        Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        aFrame.setLocation(screenSize.width/2-aFrame.getSize().width/2, screenSize.height/2-aFrame.getSize().height/2);
    }
    
    public static void setJBody(JPanel jb) {
        jBody = jb;
    }
    
    public static JPanel getJBody() {
       return  jBody;
    }
    
    public static String getCurrentPage() {
        return currentPage;
    }
    
    private static void clearStage() {
        JPanel jb = getJBody();
        jb.removeAll();
        jb.revalidate();
    }
    
    public static void updateSatusMenu(String name) {
        Component[] comps = Main.menu.getComponents();
        for (Component comp : comps) {
            String nameComp = comp.getName();
            if (nameComp != null && (comp instanceof javax.swing.JLabel)) {
                if (nameComp.equals(name)) {
                    UtilsStyles.setMenuButtonActive((JLabel)comp);
                } else {
                    UtilsStyles.setMenuButtonInactive((JLabel)comp);
                }
            }
        }
    }
  
    public static void updateLayout(String pageName) {
        if (currentPage.equals(pageName)) return;
        JPanel tmpPanel = null;
        currentPage = pageName;
        clearStage();
        switch (pageName) {
            case "dashboard":
                updateSatusMenu("dashboard");
                tmpPanel = new Dashboard();
                break;
            case "fornecedores":
                updateSatusMenu("fornecedores");
                tmpPanel = new Fornecedores();
                break;
            case "addFornecedor":
                updateSatusMenu("fornecedores");
                tmpPanel = new AddFornecedor();
                break;
            default:
                updateSatusMenu("");
                currentPage = "";
                tmpPanel = new NotFound();
        }
        getJBody().add(tmpPanel, BorderLayout.CENTER);
        tmpPanel.setVisible(true);
    }
    
    public static void setEnableRecursively(JComponent el, boolean isEnable) {
        Component[] comps = el.getComponents();
        if (comps.length > 0) {
            for (Component comp : comps) {
                if (comp instanceof JComponent) {
                    setEnableRecursively((JComponent) comp, isEnable);
                }
                //System.out.println(comp.toString());
                comp.setEnabled(isEnable);
            }
        }
    }
    
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
