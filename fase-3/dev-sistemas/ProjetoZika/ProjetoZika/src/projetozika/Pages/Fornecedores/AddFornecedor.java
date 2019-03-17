/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.Fornecedores;

import Models.Fornecedor;
import Utils.Dialogs;
import Utils.Methods;
import Utils.Navigation;
import Utils.Styles;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import projetozika.Pages.NotasFiscais.AddNotaFiscal;

/**
 *
 * @author Welison
 */
public class AddFornecedor extends Templates.BaseFrame {
    private final JFrame self;
    private String mode;
    private JPanel bg;
    private JTextField fname;
    private JLabel lname;
    private JLabel ename;
    private JTextField ftel;
    private JLabel ltel;
    private JLabel etel;
    private JTextField fcnpj;
    private JLabel lcnpj;
    private JLabel ecnpj;
    private JButton bSave;
    private JPanel panelCaller;
    
   
    public AddFornecedor() {
        this.self = this;
        this.mode = "add";
        initPage("Adicionar Fornecedor");
    }
    
    public AddFornecedor(JPanel panelCaller) {
        this.self = this;
        initPage("Adicionar Fornecedor pela Nota");
        this.mode = "nota";
        this.panelCaller = panelCaller;
    }
    
    public AddFornecedor(String id, String mode) {
        this.self = this;
        this.mode = mode;
        if(this.mode.equals("view")){
            initPage("Ver Fornecedor");
            Methods.disabledFields(bg);
        } else if (this.mode.equals("edit")){
            initPage("Editar Fornecedor");
        }
        
        fillFields(id);
    }
    
    private void initPage(String title) {
        
        initComponents();
        Styles.internalFrame(this);
        Methods.setAccessibility(this);
        
        createBaseLayout();
        addTopContent(title);
        
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                Navigation.currentPage = "fornecedores";
            }
        });
        
        addCenterContent();
    }
    
    private void fillFields(String id) {
        Fornecedor f = new Fornecedor(111, "Nome Here", "34343354-3", "(99) 99999-9999", "12/12/2009");
        fname.setText(f.getNome());
        ftel.setText(f.getTelefone());
        fcnpj.setText(f.getCnpj());
    }
    
    public void addCenterContent() {
        bg = new JPanel();
        bg.setLayout(new AbsoluteLayout());
        bg.setOpaque(false);

        
        lname = new JLabel("Nome");
        Styles.defaultLabel(lname);
        bg.add(lname, new AbsoluteConstraints(0, 0, -1, -1));

        fname = new JTextField();
        Styles.defaultField(fname);
        bg.add(fname, new AbsoluteConstraints(0, 40, -1, -1));
        
        ename = new JLabel("");
        Styles.errorLabel(ename);
        bg.add(ename, new AbsoluteConstraints(0, 75, -1, -1));
        
        ltel = new JLabel("Telefone");
        Styles.defaultLabel(ltel);
        bg.add(ltel, new AbsoluteConstraints(220, 0, -1, -1));

        ftel = new JTextField();
        Styles.defaultField(ftel);
        bg.add(ftel, new AbsoluteConstraints(220, 40, -1, -1));
        
        etel = new JLabel("");
        Styles.errorLabel(etel);
        bg.add(etel, new AbsoluteConstraints(220, 75, -1, -1));
        
        lcnpj = new JLabel("CNPJ");
        Styles.defaultLabel(lcnpj);
        bg.add(lcnpj, new AbsoluteConstraints(0, 90, -1, -1));

        fcnpj = new JTextField();
        Styles.defaultField(fcnpj);
        bg.add(fcnpj, new AbsoluteConstraints(0, 130, -1, -1));
        
        ecnpj = new JLabel("");
        Styles.errorLabel(ecnpj);
        bg.add(ecnpj, new AbsoluteConstraints(0, 165, -1, -1));
        
        bSave = new JButton("Salvar");
        Styles.defaultButton(bSave);
        bSave.setPreferredSize(new Dimension(196, 34));
        bg.add(bSave, new AbsoluteConstraints(220, 132, -1, -1));
        
        bSave.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    
                if(fname.getText().equals("") || ftel.getText().equals("") || fcnpj.getText().equals("")){
                    if(fname.getText().equals("")){
                        ename.setText("Campo obrigatório");
                    }
                    if(ftel.getText().equals("")) {
                        etel.setText("Campo obrigatório");
                    }
                    if(fcnpj.getText().equals("")) {
                        ecnpj.setText("Campo obrigatório");
                    }
                } else {
                    Dialogs.showLoadPopup(bg);
                    timerTest();
                }
                
                
            }
        });
        
        pCenter.add(bg);
    }
    
    private Timer t;
    private void timerTest() {
        
        t = new Timer(2000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dialogs.hideLoadPopup(bg);
                 
                if (mode.equals("edit")) {
                    JTable tabela = Fornecedores.tabela;
                    DefaultTableModel tableModel = Fornecedores.tableModel;
                    int row = tabela.getSelectedRow();
                    tableModel.setValueAt(fname.getText() , row, 1);
                    tableModel.setValueAt(fcnpj.getText() , row, 2);
                    tableModel.setValueAt(ftel.getText() , row, 3);
                    self.dispose();
                    JOptionPane.showMessageDialog(null, "Item editado com sucesso!");

                } else if(mode.equals("add")) {
                    DefaultTableModel tableModel = Fornecedores.tableModel;
                    tableModel.addRow(new Object[]{"5454",fname.getText(),fcnpj.getText(),ftel.getText(),"Editar","Excluir","Ver"});
                    self.dispose();
                    JOptionPane.showMessageDialog(null, "Item adicionado com sucesso!");
                } else if(mode.equals("nota")){
                    AddNotaFiscal.fcnpj.setText(fcnpj.getText());
                    self.dispose();
                } else {
                    self.dispose();
                }
                
                
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
