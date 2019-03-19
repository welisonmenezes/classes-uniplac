/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.SeusPedidos;

import Models.Produto;
import Templates.ButtonEditor;
import Templates.ButtonRenderer;
import Templates.ComboItem;
import Templates.ItemRenderer;
import Templates.SuggestionsBox;
import Utils.Dialogs;
import Utils.Methods;
import Utils.Navigation;
import Utils.Styles;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Welison
 */
public class FazerPedido extends Templates.BaseFrame {
    private final JFrame self;
    private String mode;
    private JPanel bg;
    public static JTable tabela;
    public static DefaultTableModel tableModel;
    private JScrollPane barraRolagem;
    private JButton btnFinalizar;
    private JPanel paction;
    private JComboBox<String> cproduto;
    private JTextField fproduto;
    private JLabel lproduto;
    private JButton btnAddProduto;
    private JPanel pSuggestions;
    
   public FazerPedido() {
       this.self = this;
       initPage("Fazer Pedido (requisição de produtos)");
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
        addFilterContent();
        addBottomContent();
    }
    
    public void addCenterContent() {
        bg = new JPanel();
        bg.setLayout(new BorderLayout());
        bg.setOpaque(false);
     
        makeTable();
        barraRolagem = new JScrollPane(tabela);
        Styles.defaultScroll(barraRolagem);
        bg.add(barraRolagem, BorderLayout.CENTER);
        
        pCenter.add(bg, BorderLayout.CENTER);
    }
    
    /**
     * Adiciona o conteúdo à area de filtro da tela de conteúdo
     */
    public void addFilterContent() {
        pFilter = new JPanel();
        
        lproduto = new JLabel("Buscar Produto:");
        Styles.defaultLabel(lproduto, false);
        
        // suggestion box
        pSuggestions = new JPanel();
        fproduto = new JTextField();
        cproduto = new JComboBox();
        new SuggestionsBox(pSuggestions, fproduto, cproduto, 300) {
            public ArrayList<ComboItem> addElements() {
                ArrayList<ComboItem> elements = new ArrayList<ComboItem>();
                for (int i = 1; i <= 25; i++) {
                    // TODO: implements real database results
                    elements.add(new ComboItem(i, "Nome_"+i));
                }
                return elements;
            }
        };
        
        btnAddProduto = new JButton("Adicionar");
        Styles.defaultButton(btnAddProduto);
       
        pFilter.add(lproduto);
        pFilter.add(pSuggestions);
        pFilter.add(btnAddProduto);
        
        // click do buscar
        btnAddProduto.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ComboItem selectedProd = (ComboItem)cproduto.getSelectedItem();
                String typedText = fproduto.getText();
                if (selectedProd != null && selectedProd.getDescription().equals(typedText)) {
                    // TODO: implement real database add product
                    Produto p = new Produto(selectedProd.getId(), selectedProd.getDescription(), "Caixa", "Descrição Produto", "1/12/2009");
                    Object[] data = {p.getId(),p.getNome(),p.getUnidade(),0,"Remover" };
                    tableModel.addRow(data);
                }
                
            }
        });
        
        pFilter.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pFilter.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
        pFilter.setOpaque(false);
        pCenter.add(this.pFilter, BorderLayout.NORTH);
    }
    
    public void addBottomContent() {
        btnFinalizar = new JButton("Enviar Pedido");
        Styles.defaultButton(btnFinalizar);
        
        btnFinalizar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: finalizar pedido aqui
                Dialogs.showLoadPopup(bg);
                timerTest();
            }
        });
        
        paction = new JPanel();
        paction.setLayout(new FlowLayout(FlowLayout.RIGHT));
        paction.setOpaque(false);
        paction.add(btnFinalizar);
        pBottom.add(paction, BorderLayout.SOUTH);
    }
    
    /**
     * Gera a tabela com os dados
     */
    private void makeTable() {
        // cria tabela
        tabela = new JTable();
        tabela.setRowHeight(35);
        // seta colunas
        String[] colunas = {"Código", "Produto", "Unidade", "Quantidade", "Remover"};
       // seta modelo
        tableModel = new DefaultTableModel(null, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
               if(column != 3 && column != 4){
                   return false;
               }
               return true;
            }
        };
        // adiciona linhas
        for(int i = 0; i < 5; i++) {
            Produto p = new Produto(212, "Nome Produto", "Caixa", "Descrição Produto", "1/12/2009");
            Object[] data = {p.getId(),p.getNome(),p.getUnidade(),0,"Remover" };
            tableModel.addRow(data);
        }
        // inicializa
        tabela.setModel(tableModel);
        
        TableColumn quantidadeCol = tabela.getColumnModel().getColumn(3);
        JComboBox cquantidade = new JComboBox();
        for(int i = 1; i <= 15; i++) {
            cquantidade.addItem(i);
        }
        quantidadeCol.setCellEditor(new DefaultCellEditor(cquantidade));
        
        tabela.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                // TODO: editar produto do pedido
            }
        });
        
        
        tabela.getColumn("Remover").setCellRenderer(new ButtonRenderer());
        tabela.getColumn("Remover").setCellEditor(new ButtonEditor(new JCheckBox()){
            @Override
            public void buttonAction() {
                String id = Methods.selectedTableItemId(tabela);
                Methods.removeSelectedTableRow(tabela, tableModel);
            }
        });
    }
    
    private Timer t;
    private void timerTest() {
        
        t = new Timer(2000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dialogs.hideLoadPopup(bg);
                self.dispose();
                JOptionPane.showMessageDialog(null, "Pedido enviado com sucesso!");
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
            java.util.logging.Logger.getLogger(FazerPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FazerPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FazerPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FazerPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FazerPedido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private static class ComboboxItem extends PopupMenu {

        public ComboboxItem() {
        }
    }
}
