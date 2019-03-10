/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages;

import Utils.Navigation;
import Utils.Methods;
import Utils.Styles;
import javax.swing.JFrame;
import projetozika.Login;
import projetozika.Main;

/**
 *
 * @author Welison
 */
public class Menu extends javax.swing.JPanel {
    
    Main main;
    /**
     * Creates new form Menu
     */
    public Menu(Main main) {
        initComponents();
        this.main = main;
        
        Styles.menuButton(lDashboard);
        Styles.menuButton(lFornecedores);
        Styles.menuButton(lPedidos);
        Styles.menuButton(lProdutos);
        Styles.menuButton(lPerfil);
        Styles.menuButton(lNotasFiscais);
        Styles.menuButton(lRelatorios);
        Styles.menuButton(lUsuarios);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lNotasFiscais = new javax.swing.JLabel();
        lDashboard = new javax.swing.JLabel();
        lPerfil = new javax.swing.JLabel();
        lProdutos = new javax.swing.JLabel();
        lFornecedores = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lPedidos = new javax.swing.JLabel();
        lUsuarios = new javax.swing.JLabel();
        lRelatorios = new javax.swing.JLabel();

        setBackground(new java.awt.Color(24, 24, 24));
        setPreferredSize(new java.awt.Dimension(250, 50));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lNotasFiscais.setText("Notas Fiscais");
        lNotasFiscais.setName("notasFiscais"); // NOI18N
        lNotasFiscais.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lNotasFiscaisMouseClicked(evt);
            }
        });
        add(lNotasFiscais, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 230, 30));

        lDashboard.setText("Dashboard");
        lDashboard.setName("dashboard"); // NOI18N
        lDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lDashboardMouseClicked(evt);
            }
        });
        add(lDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 230, 30));

        lPerfil.setText("Perfil");
        lPerfil.setName("perfil"); // NOI18N
        add(lPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 230, 30));

        lProdutos.setText("Produtos");
        lProdutos.setName("produtos"); // NOI18N
        add(lProdutos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 230, 30));

        lFornecedores.setText("Fornecedores");
        lFornecedores.setName("fornecedores"); // NOI18N
        lFornecedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lFornecedoresMouseClicked(evt);
            }
        });
        add(lFornecedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 230, 30));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 0, 0));
        jButton1.setText("sair");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        jButton1.setContentAreaFilled(false);
        jButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton1.setPreferredSize(new java.awt.Dimension(80, 35));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 20));

        lPedidos.setText("Pedidos");
        lPedidos.setToolTipText("");
        lPedidos.setName("pedidos"); // NOI18N
        add(lPedidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 230, 30));

        lUsuarios.setText("Usuários");
        lUsuarios.setName("usuarios"); // NOI18N
        add(lUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 230, 30));

        lRelatorios.setText("Relatórios");
        lRelatorios.setName("relatorios"); // NOI18N
        add(lRelatorios, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 230, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        // logout
        this.main.dispose();
        JFrame login = new Login();
        login.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void lDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lDashboardMouseClicked
        // TODO add your handling code here:
        if (!evt.getComponent().isEnabled()) return;
        Navigation.updateLayout("dashboard");
    }//GEN-LAST:event_lDashboardMouseClicked

    private void lFornecedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lFornecedoresMouseClicked
        // TODO add your handling code here:
        if (!evt.getComponent().isEnabled()) return;
        Navigation.updateLayout("fornecedores");
    }//GEN-LAST:event_lFornecedoresMouseClicked

    private void lNotasFiscaisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lNotasFiscaisMouseClicked
        // TODO add your handling code here:
        if (!evt.getComponent().isEnabled()) return;
        Navigation.updateLayout("notas");
    }//GEN-LAST:event_lNotasFiscaisMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel lDashboard;
    private javax.swing.JLabel lFornecedores;
    private javax.swing.JLabel lNotasFiscais;
    private javax.swing.JLabel lPedidos;
    private javax.swing.JLabel lPerfil;
    private javax.swing.JLabel lProdutos;
    private javax.swing.JLabel lRelatorios;
    private javax.swing.JLabel lUsuarios;
    // End of variables declaration//GEN-END:variables
}
