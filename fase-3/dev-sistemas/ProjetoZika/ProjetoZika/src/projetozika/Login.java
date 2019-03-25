/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika;

import Config.Environment;
import Models.Usuario;
import Utils.Navigation;
import Utils.Methods;
import Utils.Styles;
import java.util.Arrays;
import java.util.Properties;
import javax.swing.JFrame;

/**
 * Tela de login
 * 
 * @author Welison
 */
public class Login extends javax.swing.JFrame {
    
    private Properties params;

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        
        // posiciona o frame na tela
        Methods.positionFrameInCenter(this);
        
        // seta estilos aos elementos
        Styles.defaultButton(bentrar);
        Styles.defaultField(flogin);
        Styles.defaultField(fsenha);
        
        // seta a acessibilidade
        Methods.setAccessibility(this);
        
        // Tradução
        translation();
    }
    
    private void translation() {
        ltitulo.setText(Methods.getTranslation("FacaSeuLogin"));
        lsenha.setText(Methods.getTranslation("Senha"));
        llogin.setText(Methods.getTranslation("Login"));
        bentrar.setText(Methods.getTranslation("Entrar"));
        setTitle(Methods.getTranslation("ProjetoZika"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBg = new javax.swing.JPanel();
        ltitulo = new javax.swing.JLabel();
        llogin = new javax.swing.JLabel();
        flogin = new javax.swing.JTextField();
        lsenha = new javax.swing.JLabel();
        bentrar = new javax.swing.JButton();
        lInfo = new javax.swing.JLabel();
        fsenha = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(37, 38, 39));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBg.setBackground(new java.awt.Color(37, 38, 39));
        jBg.setPreferredSize(new java.awt.Dimension(400, 300));
        jBg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ltitulo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        ltitulo.setForeground(new java.awt.Color(255, 255, 255));
        ltitulo.setText("Faça seu login");
        jBg.add(ltitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, -1));

        llogin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        llogin.setForeground(new java.awt.Color(255, 255, 255));
        llogin.setText("Login");
        jBg.add(llogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, -1, 20));

        flogin.setText("welison");
        jBg.add(flogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 240, -1));

        lsenha.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lsenha.setForeground(new java.awt.Color(255, 255, 255));
        lsenha.setText("Senha");
        jBg.add(lsenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, 20));

        bentrar.setText("Entrar");
        bentrar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        bentrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bentrarActionPerformed(evt);
            }
        });
        jBg.add(bentrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, 70, 30));

        lInfo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lInfo.setForeground(new java.awt.Color(255, 0, 0));
        jBg.add(lInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 160, 30));

        fsenha.setText("123456");
        jBg.add(fsenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 240, -1));

        getContentPane().add(jBg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Escuta o evento de click do botão de login
     * 
     * @param evt o ActionEvent
     */
    private void bentrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bentrarActionPerformed
        
        // reseta erro
        lInfo.setText("");
        
        // validação
        String login = flogin.getText();
        char[] password = fsenha.getPassword();
        String userPassword = "123456";
        if (login.equals("welison") && Arrays.equals(password, userPassword.toCharArray())) {
            
            // seta usuario logado
            Usuario u = new Usuario("22122-11","Welison Menezes","welison@email.com","9999-9999","2233-3322","Recursos Humanos","Masculino","Adminstrador","10/10/1998");
            u.setLogin("welison");
            u.setSenha("123456");
            Environment.setLoggedUser(u);
            
            this.setVisible(false);
            JFrame main = new Main();
            main.setVisible(true);
            Navigation.updateLayout("", params);
            Navigation.updateLayout("dashboard", params);
        } else {
            lInfo.setText(Methods.getTranslation("LoginOuSenhaInvalidos"));
        }
        
    }//GEN-LAST:event_bentrarActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bentrar;
    private javax.swing.JTextField flogin;
    private javax.swing.JPasswordField fsenha;
    private javax.swing.JPanel jBg;
    private javax.swing.JLabel lInfo;
    private javax.swing.JLabel llogin;
    private javax.swing.JLabel lsenha;
    private javax.swing.JLabel ltitulo;
    // End of variables declaration//GEN-END:variables

}
