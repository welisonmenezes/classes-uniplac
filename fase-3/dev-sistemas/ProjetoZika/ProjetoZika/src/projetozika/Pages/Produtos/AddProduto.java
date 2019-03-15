/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.Produtos;

import Models.Produto;
import Utils.Dialogs;
import Utils.Methods;
import Utils.Navigation;
import Utils.Styles;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 *
 * @author Welison
 */
public class AddProduto extends Templates.BaseFrame {
    private final JFrame self;
    private String mode;
    private JPanel bg;
    private JTextField fnome;
    private JLabel lnome;
    private JLabel enome;
    private JComboBox<String> funidade;
    private JLabel lunidade;
    private JLabel eunidade;
    private JTextArea fdescricao;
    private JLabel ldescricao;
    private JLabel edescricao;
    private JButton bSave;
    private JPanel panelCaller;
    
   
    public AddProduto() {
        this.self = this;
        this.mode = "add";
        initPage("Adicionar Produto");
    }
    
    public AddProduto(JPanel panelCaller) {
        this.self = this;
        this.mode = "add";
        initPage("Adicionar Produto");
        this.mode = "nota";
        this.panelCaller = panelCaller;
    }
    
    public AddProduto(String id, String mode) {
        this.self = this;
        this.mode = mode;
        if(this.mode.equals("view")){
            initPage("Ver Produto");
            Methods.disabledFields(bg);
        } else if (this.mode.equals("edit")){
            initPage("Editar Produto");
        }
        
        fillFields(id);
    }
    
    private void initPage(String title) {
        
        initComponents();
        Styles.internalFrame(this, 450, 400);
        Methods.setAccessibility(this);
        
        createBaseLayout();
        addTopContent(title);
        
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                Navigation.currentPage = "produtos";
            }
        });
        
        addCenterContent();
    }
    
    private void fillFields(String id) {
        Produto p = new Produto(Integer.parseInt(id), "Nome produto", "Unidade produto", "Descrição produto", "22/10/2019");
        fnome.setText(p.getNome());
        funidade.setSelectedItem("Unidade");
        fdescricao.setText(p.getDescricao());
    }
    
    public void addCenterContent() {
        bg = new JPanel();
        bg.setLayout(new AbsoluteLayout());
        bg.setOpaque(false);

        lnome = new JLabel("Nome");
        Styles.defaultLabel(lnome);
        bg.add(lnome, new AbsoluteConstraints(0, 0, -1, -1));

        fnome = new JTextField();
        Styles.defaultField(fnome);
        bg.add(fnome, new AbsoluteConstraints(0, 40, -1, -1));
        
        enome = new JLabel("");
        Styles.errorLabel(enome);
        bg.add(enome, new AbsoluteConstraints(0, 75, -1, -1));
        
        lunidade = new JLabel("Unidade");
        Styles.defaultLabel(lunidade);
        bg.add(lunidade, new AbsoluteConstraints(220, 0, -1, -1));

        funidade = new JComboBox();
        funidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"", "Quilo", "Litro", "Caixa", "Unidade" }));
        Styles.defaultComboBox(funidade);
        bg.add(funidade, new AbsoluteConstraints(220, 40, -1, -1));
        
        eunidade = new JLabel("");
        Styles.errorLabel(eunidade);
        bg.add(eunidade, new AbsoluteConstraints(220, 75, -1, -1));
        
        ldescricao = new JLabel("Descrição");
        Styles.defaultLabel(ldescricao);
        bg.add(ldescricao, new AbsoluteConstraints(0, 90, -1, -1));

        fdescricao = new JTextArea();
        JScrollPane sp = new JScrollPane();
        Styles.defaultTextArea(fdescricao, sp);
        bg.add(sp, new AbsoluteConstraints(0, 130, -1, -1));
        
        edescricao = new JLabel("");
        Styles.errorLabel(edescricao);
        bg.add(edescricao, new AbsoluteConstraints(0, 205, -1, -1));
        
        bSave = new JButton("Salvar");
        Styles.defaultButton(bSave);
        bSave.setPreferredSize(new Dimension(196, 34));
        bg.add(bSave, new AbsoluteConstraints(220, 132, -1, -1));
        
        bSave.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    
                if(fnome.getText().equals("") || funidade.getSelectedItem().equals("") || fdescricao.getText().equals("")){
                    if(fnome.getText().equals("")){
                        enome.setText("Campo obrigatório");
                    }
                    if(funidade.getSelectedItem().equals("")) {
                        eunidade.setText("Campo obrigatório");
                    }
                    if(fdescricao.getText().equals("")) {
                        edescricao.setText("Campo obrigatório");
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
                
                JTable tabela = Produtos.tabela;
                DefaultTableModel tableModel = Produtos.tableModel;
                int row = tabela.getSelectedRow();
                    
                if (mode.equals("edit")) {
                    tableModel.setValueAt(fnome.getText() , row, 1);
                    tableModel.setValueAt(funidade.getSelectedItem() , row, 2);
                    self.dispose();
                    JOptionPane.showMessageDialog(null, "Item editado com sucesso!");

                } else if(mode.equals("add")) {
                    tableModel.addRow(new Object[]{"5454",fnome.getText(),funidade.getSelectedItem(),"10/10/2000","Editar","Excluir","Ver"});
                    self.dispose();
                    JOptionPane.showMessageDialog(null, "Item adicionado com sucesso!");
                } else if(mode.equals("nota")){
                    
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
            java.util.logging.Logger.getLogger(AddProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddProduto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
