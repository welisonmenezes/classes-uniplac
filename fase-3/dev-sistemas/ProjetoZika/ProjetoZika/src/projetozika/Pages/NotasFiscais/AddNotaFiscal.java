/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.NotasFiscais;

import Utils.Dialogs;
import Utils.Navigation;
import Utils.Styles;
import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import projetozika.Pages.Fornecedores.Fornecedores;

/**
 *
 * @author Welison
 */
public class AddNotaFiscal extends Templates.BaseFrame {
    
    private final JFrame self;
    private String mode;
    private JScrollPane bgScroll;
    private JPanel bg;
    private JLabel lnumero;
    private JTextField fnumero;
    private JLabel  enumero;
    private JLabel lserie;
    private JTextField fserie;
    private JLabel  eserie;
    private JLabel lcnpj;
    private JTextField fcnpj;
    private JLabel  ecnpj;
    private JLabel lvalor;
    private JTextField fvalor;
    private JLabel  evalor;
    private JLabel ldata;
    private JDateChooser fdata;
    private JLabel  edata;
    private JButton bSave;
    
    public AddNotaFiscal() {
        
        this.self = this;
        this.mode = "add";
        initPage("Adicionar Nota Fiscal");
    }
    
    private void initPage(String title) {
        
        initComponents();
        Styles.internalFrame(this, 850, 450);
        
        createBaseLayout();
        addTopContent(title);
        
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                Navigation.currentPage = "notasFiscais";
            }
        });
        
        addCenterContent();
    }
    
    public void addCenterContent() {
        bgScroll = new JScrollPane();
        Styles.resetScrollPanel(bgScroll);
        
        bg = new JPanel();
        bg.setLayout(new AbsoluteLayout());
        bg.setOpaque(false);
        
        lnumero = new JLabel("Número");
        Styles.defaultLabel(lnumero);
        bg.add(lnumero, new AbsoluteConstraints(0, 0, -1, -1));
        
        fnumero = new JTextField();
        Styles.defaultField(fnumero);
        bg.add(fnumero, new AbsoluteConstraints(0, 40, -1, -1));
        
        enumero = new JLabel("");
        Styles.errorLabel(enumero);
        bg.add(enumero, new AbsoluteConstraints(0, 75, -1, -1));
        
        lcnpj = new JLabel("CNPJ");
        Styles.defaultLabel(lcnpj);
        bg.add(lcnpj, new AbsoluteConstraints(220, 0, -1, -1));

        fcnpj = new JTextField();
        Styles.defaultField(fcnpj);
        bg.add(fcnpj, new AbsoluteConstraints(220, 40, -1, -1));
        
        ecnpj = new JLabel("");
        Styles.errorLabel(ecnpj);
        bg.add(ecnpj, new AbsoluteConstraints(220, 75, -1, -1));
        
        lserie = new JLabel("Nº de série");
        Styles.defaultLabel(lserie);
        bg.add(lserie, new AbsoluteConstraints(0, 90, -1, -1));

        fserie = new JTextField();
        Styles.defaultField(fserie);
        bg.add(fserie, new AbsoluteConstraints(0, 130, -1, -1));
        
        eserie = new JLabel("");
        Styles.errorLabel(eserie);
        bg.add(eserie, new AbsoluteConstraints(0, 165, -1, -1));
        
        lvalor = new JLabel("Valor");
        Styles.defaultLabel(lvalor);
        bg.add(lvalor, new AbsoluteConstraints(220, 90, -1, -1));

        fvalor = new JTextField();
        Styles.defaultField(fvalor);
        bg.add(fvalor, new AbsoluteConstraints(220, 130, -1, -1));
        
        evalor = new JLabel("");
        Styles.errorLabel(evalor);
        bg.add(evalor, new AbsoluteConstraints(220, 165, -1, -1));
        
        ldata = new JLabel("Data");
        Styles.defaultLabel(ldata);
        bg.add(ldata, new AbsoluteConstraints(0, 180, -1, -1));

        fdata = new JDateChooser();
        Styles.defaultDateChooser(fdata);
        bg.add(fdata, new AbsoluteConstraints(0, 220, -1, -1));
        
        edata = new JLabel("");
        Styles.errorLabel(edata);
        bg.add(edata, new AbsoluteConstraints(0, 255, -1, -1));
        
        bSave = new JButton("Salvar");
        Styles.defaultButton(bSave);
        bSave.setPreferredSize(new Dimension(196, 34));
        bg.add(bSave, new AbsoluteConstraints(220, 222, -1, -1));
        bSave.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    
                Dialogs.showLoadPopup(bg);
                timerTest();
                
                
            }
        });
        
        bgScroll.setViewportView(bg);
        pCenter.add(bgScroll);
    }
    
    private Timer t;
    private void timerTest() {
        
        t = new Timer(2000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dialogs.hideLoadPopup(bg);
                
                self.dispose();
                JOptionPane.showMessageDialog(null, "Item adicionado com sucesso!");
                
                
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
            java.util.logging.Logger.getLogger(AddNotaFiscal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddNotaFiscal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddNotaFiscal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddNotaFiscal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddNotaFiscal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
