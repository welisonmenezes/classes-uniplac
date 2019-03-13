/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.NotasFiscais;


import Models.NotaFiscal;
import Templates.BaseLayout;
import Templates.ButtonEditor;
import Templates.ButtonRenderer;
import Utils.Dialogs;
import Utils.Methods;
import Utils.Navigation;
import Utils.Pagination;
import Utils.Styles;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Welison
 */
public class NotasFiscais extends Templates.BaseLayout {
    
    BaseLayout self;
    public static DefaultTableModel tableModel;
    public static JTable tabela;
    JButton addMore;
    JTextField fFilter;
    JLabel lSearch;
    JButton bSearch;
    private JComboBox<String> cFilter;
    private JDateChooser fDate;
    
    /**
     * Creates new form NotasFiscais
     */
    public NotasFiscais() {
        super();
        self = this;
        initComponents();
        createBaseLayout();
        addTopContent("Notas Fiscais");
        addFilterContent();
        addCenterContent();
        addBottomContent();
    }
    
    public void addCenterContent() {
        // cria tabela
        tabela = new JTable();
        tabela.setRowHeight(35);
        // seta colunas
        String[] colunas = {"Código", "Valor", "CNPJ", "Data", "Editar", "Excluir", "Ver"};
       // seta modelo
        tableModel = new DefaultTableModel(null, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
               if(column != 4 && column != 5 && column != 6){
                   return false;
               }
               return true;
            }
        };
        // adiciona linhas
        for(int i = 0; i < 25; i++) {
            NotaFiscal n = new NotaFiscal(32323, 222, "232323-33", 20.4f, "10/10/2019");
            Object[] data = {n.getId(),n.getValor(),n.getCnpj(),n.getData(),"Editar","Excluir","Ver"};
            tableModel.addRow(data);
        }
        // inicializa
        tabela.setModel(tableModel);
        
        tabela.getColumn("Editar").setCellRenderer(new ButtonRenderer());
        tabela.getColumn("Editar").setCellEditor(new ButtonEditor(new JCheckBox()){
            @Override
            public void buttonAction() {
                String id = Methods.selectedTableItemId(tabela);
                //Navigation.updateLayout("editarNotaFiscal", id);
            }
        });
        
        tabela.getColumn("Excluir").setCellRenderer(new ButtonRenderer());
        tabela.getColumn("Excluir").setCellEditor(new ButtonEditor(new JCheckBox()){
            @Override
            public void buttonAction() {
                String id = Methods.selectedTableItemId(tabela);

                int opcion = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o item " + id + "?", "Aviso", JOptionPane.YES_NO_OPTION);
                if (opcion == 0) {
                    Methods.removeSelectedTableRow(tabela, tableModel);
                   JOptionPane.showMessageDialog(null, "Item " + id + " deletado com sucesso!");
                }
            }
        });
        
        tabela.getColumn("Ver").setCellRenderer(new ButtonRenderer());
        tabela.getColumn("Ver").setCellEditor(new ButtonEditor(new JCheckBox()){
            @Override
            public void buttonAction() {
                String id = Methods.selectedTableItemId(tabela);
                //Navigation.updateLayout("verNotaFiscal", id);
            }
        });
        
        JScrollPane barraRolagem = new JScrollPane(tabela);
        barraRolagem.setOpaque(false);
        barraRolagem.getViewport().setOpaque(false);
        barraRolagem.setBorder(null);
        barraRolagem.setViewportBorder(null);
        pCenter.add(barraRolagem, BorderLayout.CENTER);
    }
    
    public void addFilterContent() {
        addMore = new JButton("Criar Novo");
        Styles.defaultButton(addMore);
        
        fFilter = new JTextField();
        Styles.defaultField(fFilter);
        fFilter.setPreferredSize( new Dimension( 150, 39 ) );
        
        lSearch = new JLabel("Filtrar por");
        Styles.defaultLabel(lSearch);
        lSearch.setPreferredSize( new Dimension( 65, 39 ) );
        
        cFilter = new JComboBox();
        cFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código", "Cnpj", "Data" }));
        Styles.defaultComboBox(cFilter);
        cFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println(cFilter.getSelectedItem().toString());
                if (cFilter.getSelectedItem().toString().equals("Data")) {
                    fDate.setVisible(true);
                    fFilter.setVisible(false);
                } else {
                    fDate.setVisible(false);
                    fFilter.setVisible(true);
                }
            }
        });
        
        bSearch = new JButton("");
        Styles.searchButton(bSearch);
        
        JLabel hideL = new JLabel();
        hideL.setPreferredSize(new Dimension(50, 35));
        
        fDate = new JDateChooser();
        Styles.defaultDateChooser(fDate);
        fDate.setVisible(false);
        
        pFilter.add(lSearch);
        pFilter.add(cFilter);
        pFilter.add(fFilter);
        pFilter.add(fDate);
        pFilter.add(bSearch);
        pFilter.add(hideL);
        pFilter.add(addMore);
        
        addMore.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Navigation.updateLayout("addNotaFiscal");
            }
        });
        
        bSearch.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Dialogs.showLoadPopup(self);
                timerTest();
                pagination(3);
            }
        });
    }
    
    public void addBottomContent() {
        this.pagination(5);
    }
    
    public void pagination(int total) {
        Pagination pag = new Pagination(pBottom, total){
            @Override
            public void callbackPagination() {
                System.out.println(this.getCurrentPage());
                Dialogs.showLoadPopup(self);
                timerTest();
            }
        };
    }
    
    private Timer t;
    private void timerTest() {
        
        t = new Timer(2000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dialogs.hideLoadPopup(self);
                
                
                
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

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
