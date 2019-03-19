/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.Pedidos;

import Models.PedidoProduto;
import Models.Produto;
import Utils.Dialogs;
import Utils.Methods;
import Utils.Navigation;
import Utils.Styles;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 *
 * @author Welison
 */
public class EntregarPedido extends Templates.BaseFrame {
    private final JFrame self;
    private String mode;
    public static JTable tabela;
    public static DefaultTableModel tableModel;
    private JLabel title;
    private JScrollPane barraRolagem;
    private JPanel ptable;
    private JPanel pform;
    private JLabel ltitle;
    private JLabel llogin;
    private JTextField flogin;
    private JLabel elogin;
    private JLabel lsenha;
    private JTextField fsenha;
    private JLabel esenha;
    private JButton btnConfirm;
    
   public EntregarPedido() {
       this.self = this;
   }
    
    public EntregarPedido(String id, String mode) {
        this.self = this;
        this.mode = mode;
        
        initPage("Confirmação de Entrega");
    }
    
    private void initPage(String title) {
        
        initComponents();
        Styles.internalFrame(this, 1000, 600);
        Methods.setAccessibility(this);
        
        createBaseLayout();
        addTopContent(title);
        
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                Navigation.currentPage = "pedidos";
            }
        });
        
        addCenterContent();
    }
    
    public void addCenterContent() {
        ptable = new JPanel();
        ptable.setLayout(new BorderLayout());
        ptable.setOpaque(false);
        ptable.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        
        title = new JLabel("Detalhes do pedido");
        Styles.defaultLabel(title);
        ptable.add(title, BorderLayout.NORTH);
        
        makeTable();
        barraRolagem = new JScrollPane(tabela);
        Styles.defaultScroll(barraRolagem);
        ptable.add(barraRolagem, BorderLayout.CENTER);
        
        
        
        pform = new JPanel();
        pform.setLayout(new AbsoluteLayout());
        pform.setBackground(new Color(24,24,24));
        pform.setPreferredSize(new Dimension(400, 350));
        
        ltitle = new JLabel("Usuário e senha do solicitante");
        Styles.defaultLabel(ltitle);
        ltitle.setFont(new Font("Tahoma", 0, 14));
        ltitle.setPreferredSize(new Dimension(200, 35));
        pform.add(ltitle, new AbsoluteConstraints(100, 50, -1, -1));
        
        llogin = new JLabel("Login");
        Styles.defaultLabel(llogin);
        pform.add(llogin, new AbsoluteConstraints(100, 90, -1, -1));

        flogin = new JTextField();
        Styles.defaultField(flogin);
        pform.add(flogin, new AbsoluteConstraints(100, 130, -1, -1));
        
        elogin = new JLabel("");
        Styles.errorLabel(elogin);
        pform.add(elogin, new AbsoluteConstraints(100, 165, -1, -1));
        
        lsenha = new JLabel("Senha");
        Styles.defaultLabel(lsenha);
        pform.add(lsenha, new AbsoluteConstraints(100, 180, -1, -1));

        fsenha = new JTextField();
        Styles.defaultField(fsenha);
        pform.add(fsenha, new AbsoluteConstraints(100, 220, -1, -1));
        
        esenha = new JLabel("");
        Styles.errorLabel(esenha);
        pform.add(esenha, new AbsoluteConstraints(100, 255, -1, -1));
        
        btnConfirm = new JButton("Confirmar Recebimento");
        Styles.defaultButton(btnConfirm);
        btnConfirm.setPreferredSize(new Dimension(200, 35));
        pform.add(btnConfirm, new AbsoluteConstraints(100, 290, -1, -1));
        
        btnConfirm.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    
                Dialogs.showLoadPopup(pCenter);
                timerTest();
                
            }
        });
        
        pCenter.add(pform, BorderLayout.WEST);
        pCenter.add(ptable, BorderLayout.CENTER);
    }
    
    /**
     * Gera a tabela com os dados
     */
    private void makeTable() {
        // cria tabela
        tabela = new JTable();
        tabela.setRowHeight(35);
        // seta colunas
        String[] colunas = {"Produto", "Quantidade solicitada", "Quantidade aprovada"};
       // seta modelo
        tableModel = new DefaultTableModel(null, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
        };
        // adiciona linhas
        for(int i = 0; i < 25; i++) {
            Produto p = new Produto(212, "Nome Produto", "Caixa", "Descrição Produto", "1/12/2009");
            PedidoProduto pp = new PedidoProduto(p, 5, "Pendente");
            Object[] data = {pp.getProduto().getNome(), pp.getQuantidade(), pp.getQuantidadeAprovada()};
            tableModel.addRow(data);
        }
        // inicializa
        tabela.setModel(tableModel);
    }
    
    private Timer t;
    private void timerTest() {
        
        t = new Timer(2000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dialogs.hideLoadPopup(pCenter);
                self.dispose();
                JOptionPane.showMessageDialog(null, "Item entregue com sucesso!");
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
            java.util.logging.Logger.getLogger(EntregarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EntregarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EntregarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EntregarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EntregarPedido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
