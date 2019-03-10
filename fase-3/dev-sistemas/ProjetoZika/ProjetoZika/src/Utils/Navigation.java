/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import static Utils.Methods.getJBody;
import java.awt.BorderLayout;
import java.awt.Component;
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
public class Navigation {
    
    private static JFrame tmpFrame;
    private static JPanel tmpPanel;
    public static String currentPage = "";
    
    public static void updateSatusMenu(String name) {
        Component[] comps = Main.menu.getComponents();
        for (Component comp : comps) {
            String nameComp = comp.getName();
            if (nameComp != null && (comp instanceof javax.swing.JLabel)) {
                if (nameComp.equals(name)) {
                    Styles.setMenuButtonActive((JLabel)comp);
                } else {
                    Styles.setMenuButtonInactive((JLabel)comp);
                }
            }
        }
    }
  
    public static void updateLayout(String pageName) {
        if (currentPage.equals(pageName)) return;
        if (tmpFrame != null) {
            tmpFrame.dispose();
            tmpFrame = null;
        }
        currentPage = pageName;
        Methods.clearStage();
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
                tmpFrame = new AddFornecedor();
                tmpFrame.setVisible(true);
                break;
            default:
                updateSatusMenu("");
                currentPage = "";
                tmpPanel = new NotFound();
        }
        getJBody().add(tmpPanel, BorderLayout.CENTER);
        tmpPanel.setVisible(true);
    }
}
