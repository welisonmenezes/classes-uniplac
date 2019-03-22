/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.SeusPedidos;

import Models.PedidoProduto;
import Models.Produto;
import Templates.ButtonEditor;
import Templates.ButtonRenderer;
import Templates.ComboItem;
import Templates.SuggestionsBox;
import Utils.Dialogs;
import Utils.Methods;
import Utils.Navigation;
import Utils.Styles;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
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
    ArrayList<PedidoProduto> pedidosProdutos;
    private JLabel efinalizar;
    
   public FazerPedido() {
       this.self = this;
       this.mode = "add";
       initPage(Methods.getTranslation("FazerPedido"));
   }
   
   public FazerPedido(String id, String mode) {
       this.self = this;
       this.mode = mode;
       
        if(this.mode.equals("view")){
            initPage(Methods.getTranslation("SeuPedido"));
            pFilter.setVisible(false);
            pBottom.setVisible(false);
        } else if(this.mode.equals("edit")) {
            initPage(Methods.getTranslation("EditarSeuPedido"));
        }
   }
   
    private void initPage(String title) {
        
        pedidosProdutos = new ArrayList<PedidoProduto>();
        for (int i = 0; i < 5; i++) {
            Produto p = new Produto(i, "Nome Produto", "Caixa", "Descrição Produto", "1/12/2009");
            PedidoProduto pp = new PedidoProduto(p, 1, Methods.getTranslation("Pendente"));
            pedidosProdutos.add(pp);
        }
        
        initComponents();
        Styles.internalFrame(this, 1000, 600);
        Methods.setAccessibility(this);
        
        createBaseLayout();
        addTopContent(title);
        
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                Navigation.currentPage = "seusPedidos";
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
     
        barraRolagem = new JScrollPane();
        Styles.defaultScroll(barraRolagem);
        updateCenterContent();
        bg.add(barraRolagem, BorderLayout.CENTER);
        pCenter.add(bg, BorderLayout.CENTER);
    }
    
    private void updateCenterContent() {
        makeTable();
        barraRolagem.getViewport().setView(tabela);
    }
    
    /**
     * Adiciona o conteúdo à area de filtro da tela de conteúdo
     */
    public void addFilterContent() {
        pFilter = new JPanel();
        
        lproduto = new JLabel(Methods.getTranslation("BuscarProduto"));
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
        
        btnAddProduto = new JButton(Methods.getTranslation("Adicionar"));
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
                    if (!hasProduct(selectedProd.getId())) {
                        Produto p = new Produto(selectedProd.getId(), selectedProd.getDescription(), "Caixa", "Descrição Produto", "1/12/2009");
                        PedidoProduto pp = new PedidoProduto(p, 1, Methods.getTranslation("Pendente"));
                        pedidosProdutos.add(pp);
                        updateCenterContent();
                    }
                }
                
            }
        });
        
        pFilter.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pFilter.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
        pFilter.setOpaque(false);
        pCenter.add(this.pFilter, BorderLayout.NORTH);
    }
    
    private boolean hasProduct(int id) {
        for (int i = 0; i < pedidosProdutos.size(); i++) {
            PedidoProduto pp = pedidosProdutos.get(i);
            if (id == pp.getProduto().getId()) {
                return true;
            }
        }
        return false;
    }
    
    public void addBottomContent() {
        btnFinalizar = new JButton(Methods.getTranslation("SalvarPedido"));
        Styles.defaultButton(btnFinalizar);
        
        btnFinalizar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: finalizar pedido aqui
                if (pedidosProdutos.size() > 0) {
                    pedidosProdutos.forEach(pp -> {
                        System.out.println(pp.getProduto().getId() + " " + pp.getQuantidade());
                    });
                    Dialogs.showLoadPopup(bg);
                    timerTest();
                } else {
                    efinalizar.setText(Methods.getTranslation("SelecioneUmProduto"));
                }
                
            }
        });
        
        efinalizar = new JLabel("");
        Styles.errorLabel(efinalizar);
        efinalizar.setPreferredSize(new Dimension(250, 39));
        
        paction = new JPanel();
        paction.setLayout(new FlowLayout(FlowLayout.RIGHT));
        paction.setOpaque(false);
        paction.add(efinalizar);
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
        String[] colunas = {
            Methods.getTranslation("Codigo"), 
            Methods.getTranslation("Produto"), 
            Methods.getTranslation("Unidade"), 
            Methods.getTranslation("Quantidade"), 
            ""
        };
        if (! mode.equals("view")) {
            colunas[4] = Methods.getTranslation("Remover");
        } 
       // seta modelo
        tableModel = new DefaultTableModel(null, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (mode.equals("view")) {
                    return false;
                } else {
                    if(column != 3 && column != 4){
                        return false;
                    }
                }
                return true;
            }
        };
        // adiciona linhas
        pedidosProdutos.forEach(pp -> {
            Object[] data = {pp.getProduto().getId(),pp.getProduto().getNome(),pp.getProduto().getUnidade(),pp.getQuantidade(),""};
            if (! mode.equals("view")) {
                data[4] = Methods.getTranslation("Remover");
            }
            tableModel.addRow(data);
        });
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
                
                if (!tabela.getSelectionModel().isSelectionEmpty()) {
                    String newQtd = Methods.selectedTableItemValue(tabela, 3);
                    String idTable = Methods.selectedTableItemId(tabela);
                    for (int i = 0; i < pedidosProdutos.size(); i++) {
                        PedidoProduto pp = pedidosProdutos.get(i);
                        int idModel = pp.getProduto().getId();
                        if (idTable.equals(""+idModel)) {
                            pp.setQuantidade(Integer.parseInt(newQtd));
                            break;
                        }
                    }
                }
                
            }
        });
        
        if(! mode.equals("view")) {
            tabela.getColumn(Methods.getTranslation("Remover")).setCellRenderer(new ButtonRenderer());
            tabela.getColumn(Methods.getTranslation("Remover")).setCellEditor(new ButtonEditor(new JCheckBox()){
                @Override
                public void buttonAction() {
                    String idTable = Methods.selectedTableItemId(tabela);
                    for (int i = 0; i < pedidosProdutos.size(); i++) {
                        PedidoProduto pp = pedidosProdutos.get(i);
                        int idModel = pp.getProduto().getId();
                        if (idTable.equals(""+idModel)) {
                            pedidosProdutos.remove(pp);
                        }
                    }
                    updateCenterContent();
                }
            });
        }
    }
    
    private Timer t;
    private void timerTest() {
        
        t = new Timer(2000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dialogs.hideLoadPopup(bg);
                self.dispose();
                JOptionPane.showMessageDialog(null, Methods.getTranslation("PedidoEnviadoComSucesso"));
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
