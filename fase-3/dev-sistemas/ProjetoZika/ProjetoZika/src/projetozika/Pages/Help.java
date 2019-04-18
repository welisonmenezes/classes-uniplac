/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages;

import Utils.Methods;
import Utils.Styles;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Properties;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author welis
 */
public class Help extends Templates.BaseLayout {
    private String textContent;
    private JLabel textContainer;
    private JPanel wrapText;

    /**
     * Creates new form Help
     * @param params
     */
    public Help(Properties params) {
        super();
        this.params = params;
        
        initPage();
        System.out.println("help page");
    }
    
    private void initPage() {
        initComponents();
        createBaseLayout();
        addTopContent(Methods.getTranslation("Ajuda"));
        addCenterContent();
    }
    
    /**
     * Adiciona conteúdo ao centro da area de conteúdo
     */
    private void addCenterContent() {
        barraRolagem = new JScrollPane();
        Styles.defaultScroll(barraRolagem);
        
        wrapText = new JPanel(new FlowLayout(FlowLayout.LEFT));
        wrapText.setOpaque(false);

        helpContentPT();
        
        textContainer = new JLabel("<html>"+textContent+"</html>");
        textContainer.setFont(new java.awt.Font("Tahoma", 0, 16));
        textContainer.setForeground(new java.awt.Color(255, 255, 255));
        
        wrapText.add(textContainer);
        barraRolagem.getViewport().setView(wrapText);
        pCenter.add(barraRolagem, BorderLayout.CENTER);
    }
    
    private void helpContentPT() {
        textContent = "<h2>Acessos Rápidos</h2>"
                + "<ul>"
                + "<li>Dashboard: <code>CTRL + D</code></li>"
                + "<li>Seus pedidos: <code>CTRL + Y</code></li>"
                + "<li>Pedidos: <code>CTRL + O</code></li>"
                + "<li>Fornecedores: <code>CTRL + F</code></li>"
                + "<li>Notas fiscais: <code>CTRL + I</code></li>"
                + "<li>Produtos: <code>CTRL + P</code></li>"
                + "<li>Usuários: <code>CTRL + U</code></li>"
                + "<li>Perfil: <code>CTRL + M</code></li>"
                + "<li>Relatórios: <code>CTRL + R</code></li>"
                + "<li>Sobre: <code>CTRL + A</code></li>"
                + "<li>Ajuda: <code>F1</code></li>"
                + "<li>Sair: <code>CTRL + L</code> ou <code>ESC</code></li>"
                + "<li>Adicionar Novo: <code>CTRL + N</code></li>"
                + "</ul>";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
