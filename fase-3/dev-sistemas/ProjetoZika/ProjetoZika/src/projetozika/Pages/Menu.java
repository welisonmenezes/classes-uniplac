/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages;

import Utils.Navigation;
import Utils.Styles;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
        
        
        labelEspacoTopo.setPreferredSize(new Dimension(200, 45));
        labelEspacoLogout.setPreferredSize(new Dimension(200, 100));
        
        Styles.menuButton(bDashboard);
        Styles.menuButton(bPedidos);
        Styles.menuButton(bFornecedores);
        Styles.menuButton(bNotasFiscais);
        Styles.menuButton(bProdutos);
        Styles.menuButton(bUsuarios);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelEspacoTopo = new javax.swing.JLabel();
        bDashboard = new javax.swing.JButton();
        bPedidos = new javax.swing.JButton();
        bFornecedores = new javax.swing.JButton();
        bNotasFiscais = new javax.swing.JButton();
        bProdutos = new javax.swing.JButton();
        bUsuarios = new javax.swing.JButton();
        labelEspacoLogout = new javax.swing.JLabel();
        logout = new javax.swing.JButton();

        setBackground(new java.awt.Color(24, 24, 24));
        setPreferredSize(new java.awt.Dimension(250, 50));
        add(labelEspacoTopo);

        bDashboard.setText("Dashboard");
        bDashboard.setName("dashboard"); // NOI18N
        bDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDashboardActionPerformed(evt);
            }
        });
        add(bDashboard);

        bPedidos.setText("Pedidos");
        bPedidos.setName("pedidos"); // NOI18N
        bPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPedidosActionPerformed(evt);
            }
        });
        add(bPedidos);

        bFornecedores.setText("Fornecedores");
        bFornecedores.setName("fornecedores"); // NOI18N
        bFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFornecedoresActionPerformed(evt);
            }
        });
        add(bFornecedores);

        bNotasFiscais.setText("Notas Fiscais");
        bNotasFiscais.setName("notasFiscais"); // NOI18N
        bNotasFiscais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNotasFiscaisActionPerformed(evt);
            }
        });
        add(bNotasFiscais);

        bProdutos.setText("Produtos");
        bProdutos.setName("produtos"); // NOI18N
        bProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bProdutosActionPerformed(evt);
            }
        });
        add(bProdutos);

        bUsuarios.setText("Usuários");
        bUsuarios.setName("usuarios"); // NOI18N
        bUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUsuariosActionPerformed(evt);
            }
        });
        add(bUsuarios);
        add(labelEspacoLogout);

        logout.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        logout.setForeground(new java.awt.Color(255, 0, 0));
        logout.setText("sair");
        logout.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        logout.setContentAreaFilled(false);
        logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logout.setMargin(new java.awt.Insets(0, 0, 0, 0));
        logout.setPreferredSize(new java.awt.Dimension(80, 35));
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        add(logout);
    }// </editor-fold>//GEN-END:initComponents

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed

        // logout
        this.main.dispose();
        JFrame login = new Login();
        login.setVisible(true);
    }//GEN-LAST:event_logoutActionPerformed

    private void bNotasFiscaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNotasFiscaisActionPerformed
        // TODO add your handling code here:
        //if (!evt.getComponent().isEnabled()) return;
        Navigation.updateLayout("notasFiscais");
    }//GEN-LAST:event_bNotasFiscaisActionPerformed

    private void bFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bFornecedoresActionPerformed
        // TODO add your handling code here:
        Navigation.updateLayout("fornecedores");
    }//GEN-LAST:event_bFornecedoresActionPerformed

    private void bDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDashboardActionPerformed
        // TODO add your handling code here:
        Navigation.updateLayout("dashboard");
    }//GEN-LAST:event_bDashboardActionPerformed

    private void bProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bProdutosActionPerformed
        // TODO add your handling code here:
        Navigation.updateLayout("produtos");
    }//GEN-LAST:event_bProdutosActionPerformed

    private void bUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUsuariosActionPerformed
        // TODO add your handling code here:
        Navigation.updateLayout("usuarios");
    }//GEN-LAST:event_bUsuariosActionPerformed

    private void bPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPedidosActionPerformed
        // TODO add your handling code here:
        Navigation.updateLayout("pedidos");
    }//GEN-LAST:event_bPedidosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bDashboard;
    private javax.swing.JButton bFornecedores;
    private javax.swing.JButton bNotasFiscais;
    private javax.swing.JButton bPedidos;
    private javax.swing.JButton bProdutos;
    private javax.swing.JButton bUsuarios;
    private javax.swing.JLabel labelEspacoLogout;
    private javax.swing.JLabel labelEspacoTopo;
    private javax.swing.JButton logout;
    // End of variables declaration//GEN-END:variables
}
