/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.Fornecedores;

import Utils.UtilsElements;
import Utils.UtilsStyles;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 *
 * @author Welison
 */
public class AddFornecedor extends Models.BaseFrame {
    private JFrame self;
    private JPanel bg;
    /**
     * Creates new form AddFornecedor
     */
    public AddFornecedor() {
        this.self = this;
        initComponents();
        
        UtilsElements.configInternalFrame(this);
        
        createBaseLayout();
        addTopContent("Adicionar Fornecedor");
        
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                UtilsElements.currentPage = "fornecedores";
            }
        });
        
        addCenterContent();
    }
    
    public void addCenterContent() {
        bg = new JPanel();
        bg.setLayout(new AbsoluteLayout());
        bg.setOpaque(false);

        
        JLabel lname = new JLabel("Nome");
        UtilsStyles.defaultLabel(lname);
        bg.add(lname, new AbsoluteConstraints(0, 0, -1, -1));

        JTextField fname = new JTextField();
        UtilsStyles.defaultField(fname);
        bg.add(fname, new AbsoluteConstraints(0, 40, -1, -1));
        
        JLabel lemail = new JLabel("Email");
        UtilsStyles.defaultLabel(lemail);
        bg.add(lemail, new AbsoluteConstraints(220, 0, -1, -1));

        JTextField femail = new JTextField();
        UtilsStyles.defaultField(femail);
        bg.add(femail, new AbsoluteConstraints(220, 40, -1, -1));
        
        JLabel lcnpj = new JLabel("CNPJ");
        UtilsStyles.defaultLabel(lcnpj);
        bg.add(lcnpj, new AbsoluteConstraints(0, 90, -1, -1));

        JTextField fcnpj = new JTextField();
        UtilsStyles.defaultField(fcnpj);
        bg.add(fcnpj, new AbsoluteConstraints(0, 130, -1, -1));
        
        JButton bSave = new JButton("Salvar");
        UtilsStyles.defaultButton(bSave);
        bSave.setPreferredSize(new Dimension(196, 34));
        bg.add(bSave, new AbsoluteConstraints(220, 132, -1, -1));
        
        bSave.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                UtilsElements.showLoadPopup(bg);
                timerTest();
            }
        });
        
        pCenter.add(bg);
    }
    
    private Timer t;
    private void timerTest() {
        
        t = new Timer(2000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UtilsElements.hideLoadPopup(bg);
                self.dispose();
                t.stop();
            }
        });
        t.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(37, 38, 39));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(AddFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddFornecedor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
