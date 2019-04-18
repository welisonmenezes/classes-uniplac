/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages;

import Config.Environment;
import Utils.Methods;
import Utils.Navigation;
import Utils.Styles;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Properties;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import projetozika.Login;
import projetozika.Main;

/**
 * O menu da aplicação
 * @author Welison
 */
public class Menu extends javax.swing.JPanel {
    
    private final Main main;
    private final Properties params;
    
    /**
     * Cria o menu da aplicação
     * @param main o Frame principal da aplicação
     */
    public Menu(Main main) {
        
        this.main = main;
        this.params = new Properties();
        
        this.initPage();
        
        this.accessControl();
        this.addHotLinkToMenu();
        this.addHotLinkToLogout();
    }
    
    private void initPage() {
        initComponents();
        // Espaçamentos entre os elementos
        labelEspacoTopo.setPreferredSize(new Dimension(200, 45));
        labelEspacoLogout.setPreferredSize(new Dimension(200, 100));
        
        // Estilos customizados
        Styles.menuButton(bDashboard);
        Styles.menuButton(bPedidos);
        Styles.menuButton(bFornecedores);
        Styles.menuButton(bNotasFiscais);
        Styles.menuButton(bProdutos);
        Styles.menuButton(bUsuarios);
        Styles.menuButton(bSeusPedidos);
        Styles.menuButton(bPerfil);
        Styles.menuButton(bRelatorios);
        Styles.redButton(logout);
        
        // Tradução dos textos
        this.translation();
    }
    
    private void accessControl() {
        // controle de acesso
        if (Environment.getLoggedUser().getPermissao().equals(Methods.getTranslation("Almoxarife"))) {
            bRelatorios.setVisible(false);
            bUsuarios.setVisible(false);
        } else if(Environment.getLoggedUser().getPermissao().equals(Methods.getTranslation("Usuario"))) {
            bRelatorios.setVisible(false);
            bUsuarios.setVisible(false);
            bDashboard.setVisible(false);
            bPedidos.setVisible(false);
            bFornecedores.setVisible(false);
            bNotasFiscais.setVisible(false);
            bProdutos.setVisible(false);
        }
    }
    
    private void addHotLinkToMenu() {
        Navigation.addHotLink(
                bDashboard, 
                Methods.getTranslation("Dashboard"), 
                "dashboard",
                KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK),
                params);
        
        Navigation.addHotLink(
                bPedidos, 
                Methods.getTranslation("Pedidos"), 
                "pedidos",
                KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK),
                params);
        
        Navigation.addHotLink(
                bFornecedores, 
                Methods.getTranslation("Fornecedores"), 
                "fornecedores",
                KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK),
                params);
        
        Navigation.addHotLink(
                bNotasFiscais, 
                Methods.getTranslation("NotasFiscais"), 
                "notasFiscais",
                KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK),
                params);
        
        Navigation.addHotLink(
                bProdutos, 
                Methods.getTranslation("Produtos"), 
                "produtos",
                KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK),
                params);
        
        Navigation.addHotLink(
                bUsuarios, 
                Methods.getTranslation("Usuarios"), 
                "usuarios",
                KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK),
                params);
        
        Navigation.addHotLink(
                bSeusPedidos, 
                Methods.getTranslation("SeusPedidos"), 
                "seusPedidos",
                KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK),
                params);
        
        Navigation.addHotLink(
                bPerfil, 
                Methods.getTranslation("Perfil"), 
                "perfil",
                KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK),
                params);
        
        Navigation.addHotLink(
                bRelatorios, 
                Methods.getTranslation("Relatorios"), 
                "relatorios",
                KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK),
                params);
    }
    
    private void addHotLinkToLogout() {
        Action buttonAction = new AbstractAction(Methods.getTranslation("Sair")) {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // logout
                main.dispose();
                JFrame login = new Login();
                login.setVisible(true);
            }
        };
        logout.setAction(buttonAction);
        logout.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK), "");
        logout.getActionMap().put("", buttonAction);
    }
    
    /**
     * Tradução dos textos do menu
     */
    private void translation() {
        bDashboard.setText(Methods.getTranslation("Dashboard"));
        bPedidos.setText(Methods.getTranslation("Pedidos"));
        bFornecedores.setText(Methods.getTranslation("Fornecedores"));
        bNotasFiscais.setText(Methods.getTranslation("NotasFiscais"));
        bProdutos.setText(Methods.getTranslation("Produtos"));
        bUsuarios.setText(Methods.getTranslation("Usuarios"));
        bSeusPedidos.setText(Methods.getTranslation("SeusPedidos"));
        bPerfil.setText(Methods.getTranslation("Perfil"));
        bRelatorios.setText(Methods.getTranslation("Relatorios"));
        logout.setText(Methods.getTranslation("Sair"));
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
        bSeusPedidos = new javax.swing.JButton();
        bPedidos = new javax.swing.JButton();
        bFornecedores = new javax.swing.JButton();
        bNotasFiscais = new javax.swing.JButton();
        bProdutos = new javax.swing.JButton();
        bUsuarios = new javax.swing.JButton();
        bPerfil = new javax.swing.JButton();
        bRelatorios = new javax.swing.JButton();
        labelEspacoLogout = new javax.swing.JLabel();
        logout = new javax.swing.JButton();

        setBackground(new java.awt.Color(24, 24, 24));
        setPreferredSize(new java.awt.Dimension(250, 50));
        add(labelEspacoTopo);

        bDashboard.setText("Dashboard");
        bDashboard.setName("dashboard"); // NOI18N
        add(bDashboard);

        bSeusPedidos.setText("Seus Pedidos");
        bSeusPedidos.setName("seusPedidos"); // NOI18N
        add(bSeusPedidos);

        bPedidos.setText("Pedidos");
        bPedidos.setName("pedidos"); // NOI18N
        add(bPedidos);

        bFornecedores.setText("Fornecedores");
        bFornecedores.setName("fornecedores"); // NOI18N
        add(bFornecedores);

        bNotasFiscais.setText("Notas Fiscais");
        bNotasFiscais.setName("notasFiscais"); // NOI18N
        add(bNotasFiscais);

        bProdutos.setText("Produtos");
        bProdutos.setName("produtos"); // NOI18N
        add(bProdutos);

        bUsuarios.setText("Usuários");
        bUsuarios.setName("usuarios"); // NOI18N
        add(bUsuarios);

        bPerfil.setText("Perfil");
        bPerfil.setName("perfil"); // NOI18N
        add(bPerfil);

        bRelatorios.setText("Relatórios");
        bRelatorios.setName("relatorios"); // NOI18N
        add(bRelatorios);
        add(labelEspacoLogout);

        logout.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        logout.setForeground(new java.awt.Color(255, 0, 0));
        logout.setText("sair");
        logout.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        logout.setContentAreaFilled(false);
        logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logout.setMargin(new java.awt.Insets(0, 0, 0, 0));
        logout.setPreferredSize(new java.awt.Dimension(80, 35));
        add(logout);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bDashboard;
    private javax.swing.JButton bFornecedores;
    private javax.swing.JButton bNotasFiscais;
    private javax.swing.JButton bPedidos;
    private javax.swing.JButton bPerfil;
    private javax.swing.JButton bProdutos;
    private javax.swing.JButton bRelatorios;
    private javax.swing.JButton bSeusPedidos;
    private javax.swing.JButton bUsuarios;
    private javax.swing.JLabel labelEspacoLogout;
    private javax.swing.JLabel labelEspacoTopo;
    private javax.swing.JButton logout;
    // End of variables declaration//GEN-END:variables
}
