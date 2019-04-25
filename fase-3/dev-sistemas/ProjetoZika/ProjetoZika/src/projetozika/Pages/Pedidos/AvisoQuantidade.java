/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.Pedidos;

import Models.EstoqueAviso;
import Utils.AccessibilityManager;
import Utils.Methods;
import Utils.Navigation;
import Utils.Styles;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.jfree.ui.tabbedui.VerticalLayout;

/**
 * Janela pra informar pedido que excede quantidade em estoque
 * @author welison
 */
public class AvisoQuantidade extends Templates.BaseFrame {
    
    private JScrollPane bgScroll;
    private JPanel bg;
    private final ArrayList<EstoqueAviso> avisos;
    
    /**
     * Creates new form AvisoQuantidade
     * @param avisos Lista de produtos com quantidade insuficiente em estoque
     */
    public AvisoQuantidade(ArrayList<EstoqueAviso> avisos) {
        this.avisos = avisos;
        initPage(Methods.getTranslation("EstoqueInsuficienteParaOsItensAbaixo"));
    }
    
    /**
     * Inicia a tela
     * @param title o título
     */
    private void initPage(String title) {
        
        AccessibilityManager.setAccessibility(this);
        setTitle(title);
        
        // carrega os elementos e o design da tela
        initComponents();
        Styles.internalFrame(this, 500, 400);
        createBaseLayout();
        addTopContent(title);
        addCenterContent();
        
        // seta a página pai como página corrente
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent windowEvent) {
                Navigation.currentPage = "pedidos";
            }
        });
    }
    
    /**
     * Adiciona o conteúdo da área central (o formulário em si)
     */
    private void addCenterContent() {
        bgScroll = new JScrollPane();
        Styles.resetScrollPanel(bgScroll);
        
        bg = new JPanel();
        bg.setLayout(new VerticalLayout());
        bg.setOpaque(false);
        
        avisos.forEach(aviso -> {
            String strLabel = Methods.getTranslation("OProduto") + " "
                    + aviso.getProduto().getNome() + " " 
                    + Methods.getTranslation("contemApenas") + " "
                    + aviso.getQuantidade() + " "
                    + Methods.getTranslation("itensEmEstoque");
            
            JLabel tmpL = new JLabel(strLabel);
            Styles.errorLabel(tmpL);
            bg.add(tmpL);
        });
        
        bgScroll.setViewportView(bg);
        pCenter.add(bgScroll);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
