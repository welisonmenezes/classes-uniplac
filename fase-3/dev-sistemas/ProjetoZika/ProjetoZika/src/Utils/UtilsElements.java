/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Models.PageModel;
import Models.PagesModel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import projetozika.Pages.Dashboard;
import projetozika.Pages.Fornecedores.Fornecedores;
import projetozika.Pages.Login;

/**
 *
 * @author Welison
 */
public class UtilsElements {
    
    private static JPanel jBody;
    
    public static void makeFrameFullSize(JFrame aFrame)
    {
        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        aFrame.setSize(screenSize.width, screenSize.height);
    }
    
    public static void setJBody(JPanel jb) {
        jBody = jb;
    }
    
    public static JPanel getJBody() {
       return  jBody;
    }
    
    public static void setAllInvisible() {
        JPanel jb = getJBody();
        Component[] comps = jb.getComponents();
        System.out.println(comps.length);
        for (Component comp : comps) {
            comp.setVisible(false);
        }
    }
    
    /*
    public static void updateLayout(String pageName) {
        
        JPanel tmpPanel = null;
        
        setAllInvisible();
        
        switch (pageName) {
            case "login":
                tmpPanel = new Login();
                break;
            case "dashboard":
                tmpPanel = new Dashboard();
                break;
            case "fornecedores":
                tmpPanel = new Fornecedores();
                break;
            default:
                System.out.println("Page Not Found.");
        }
        
        if (tmpPanel != null) {
            getJBody().add(tmpPanel, BorderLayout.CENTER);
            tmpPanel.setVisible(true); 
        }
    }
    */
    //*
    public static void updateLayout(String pageName){
        setAllInvisible();
        if (PagesModel.getPage(pageName) != null) {
            JPanel tmpPanel = PagesModel.getPage(pageName).getPanel();
            getJBody().add(tmpPanel, BorderLayout.CENTER);
            tmpPanel.setVisible(true);
        } else {
            switch (pageName) {
                case "login":
                    PagesModel.addPage(new PageModel("login", new Login()));
                    updateLayout("login");
                    break;
                case "dashboard":
                    PagesModel.addPage(new PageModel("dashboard", new Dashboard()));
                    updateLayout("dashboard");
                    break;
                case "fornecedores":
                    PagesModel.addPage(new PageModel("fornecedores", new Fornecedores()));
                    updateLayout("fornecedores");
                    break;
                default:
                    System.out.println("Page Not Found.");
            }
        }
    }
    //*/
}
