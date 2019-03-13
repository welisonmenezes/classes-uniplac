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
import projetozika.Pages.NotasFiscais.AddNotaFiscal;
import projetozika.Pages.NotasFiscais.NotasFiscais;

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
    
    public static void updateLayout(String pageName, String id) {
        if (currentPage.equals(pageName)) return;
        resetLayout(pageName);
        switch (pageName) {
            case "editarFornecedor":
                updateSatusMenu("fornecedores");
                tmpFrame = new AddFornecedor(id, "edit");
                tmpFrame.setVisible(true);
                break;
            case "verFornecedor":
                updateSatusMenu("fornecedores");
                tmpFrame = new AddFornecedor(id, "view");
                tmpFrame.setVisible(true);
                break;
            default:
                setDefaultPage();
        }
        addNewPage();
    }
  
    public static void updateLayout(String pageName) {
        if (currentPage.equals(pageName)) return;
        resetLayout(pageName);
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
            case "notasFiscais":
                updateSatusMenu("notasFiscais");
                tmpPanel = new NotasFiscais();
                break;
            case "addNotaFiscal":
                updateSatusMenu("notasFiscais");
                tmpFrame = new AddNotaFiscal();
                tmpFrame.setVisible(true);
                break;
            case "addFornecedorNota":
                updateSatusMenu("notasFiscais");
                tmpFrame = new AddFornecedor(tmpPanel);
                tmpFrame.setVisible(true);
                break;
            default:
                setDefaultPage();
        }
        addNewPage();
    }
    
    private static void resetLayout(String pageName) {
        if (!pageName.equals("addFornecedorNota")){
            if (tmpFrame != null) {;
                tmpFrame.dispose();
                tmpFrame = null;
            }
        }
        currentPage = pageName;
        Methods.clearStage(Methods.getJBody());
    }
    
    private static void addNewPage() {
        getJBody().add(tmpPanel, BorderLayout.CENTER);
        tmpPanel.setVisible(true);
    }
    
    private static void setDefaultPage() {
        updateSatusMenu("");
        currentPage = "";
        tmpPanel = new NotFound();
    }
}
