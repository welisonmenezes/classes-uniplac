/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.Produtos;

import Config.Environment;
import Models.Produto;
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
import javax.swing.DefaultComboBoxModel;
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
 * Tela de listagem do fornecedores
 * 
 * @author Welison
 */
public class Produtos extends Templates.BaseLayout {
    
    BaseLayout self;
    public static JTable tabela;
    public static DefaultTableModel tableModel;
    JButton addMore;
    JTextField fNome;
    JComboBox<String> funidade;
    JDateChooser fData;
    JLabel lNome;
    JLabel lUnidade;
    JLabel lSearch;
    JLabel lData;
    JButton bSearch;

    /**
     * Cria a tela de fornecedores
     */
    public Produtos() {
        super();
        self = this;
        initComponents();
        createBaseLayout();
        addTopContent("Produtos");
        addCenterContent();
        addBottomContent();
        addFilterContent();
    }
    
    // Adiciona conteúdo ao centro da area de conteúdo
    public void addCenterContent() {
        makeTable();
        JScrollPane barraRolagem = new JScrollPane(tabela);
        Styles.defaultScroll(barraRolagem);
        pCenter.add(barraRolagem, BorderLayout.CENTER);
    }
    
    /**
     * Gera a tabela com os dados
     */
    private void makeTable() {
        // cria tabela
        tabela = new JTable();
        tabela.setRowHeight(35);
        // seta colunas
        String[] colunas = {"Código", "Nome", "Unidade", "Data", "Editar", "Excluir", "Ver"};
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
            Produto p = new Produto(i, "Nome produto", "Unidade produto", "Descrição produto", "22/10/2019");
            Object[] data = {p.getId(),p.getNome(),p.getUnidade(),p.getData(),"Editar","Excluir","Ver"};
            tableModel.addRow(data);
        }
        // inicializa
        tabela.setModel(tableModel);
        
        tabela.getColumn("Editar").setCellRenderer(new ButtonRenderer());
        tabela.getColumn("Editar").setCellEditor(new ButtonEditor(new JCheckBox()){
            @Override
            public void buttonAction() {
                String id = Methods.selectedTableItemId(tabela);
                Navigation.updateLayout("editarProduto", id);
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
                Navigation.updateLayout("verProduto", id);
            }
        });
    }
    
    /**
     * Adiciona o conteúdo à area de filtro da tela de conteúdo
     */
    public void addFilterContent() {
        
        fNome = new JTextField();
        Styles.defaultField(fNome, 150);
        
        lNome = new JLabel("Nome:");
        Styles.defaultLabel(lNome, false);
        
        funidade = new JComboBox();
        funidade.setModel(new DefaultComboBoxModel(Environment.UNIDADES.toArray()));
        Styles.defaultComboBox(funidade);
        
        lUnidade = new JLabel("Unidade:");
        Styles.defaultLabel(lUnidade, false);
        
        fData = new JDateChooser();
        Styles.defaultDateChooser(fData);
        
        lData = new JLabel("Data:");
        Styles.defaultLabel(lData, false);
       
        bSearch = new JButton("");
        Styles.searchButton(bSearch);
        
        JLabel hideL = new JLabel();
        hideL.setPreferredSize(new Dimension(50, 35));
        
        addMore = new JButton("Criar Novo");
        Styles.defaultButton(addMore);
        
        pFilter.add(lNome);
        pFilter.add(fNome);
        pFilter.add(lUnidade);
        pFilter.add(funidade);
        pFilter.add(lData);
        pFilter.add(fData);
        pFilter.add(bSearch);
        pFilter.add(hideL);
        pFilter.add(addMore);
        
        // click do adicionar novo
        addMore.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Navigation.updateLayout("addProduto");
            }
        });
        
        // click do buscar
        bSearch.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Dialogs.showLoadPopup(self);
                timerTest();
                pagination(3);
            }
        });
    }
    
    /**
     * Adiciona o conteúdo à area de footer do conteúdo
     */
    public void addBottomContent() {
        this.pagination(5);
    }
    
    /**
     * Gera a paginação
     * 
     * @param total o total de páginas
     */
    public void pagination(int total) {
        Pagination pag = new Pagination(pBottom, total){
            @Override
            public void callbackPagination() {
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
                
                // reseta tabela
                tableModel.getDataVector().removeAllElements();
                tableModel.fireTableDataChanged();
                // adiciona novas linhas
                for(int i = 26; i < 35; i++) {
                    Produto p = new Produto(i, "Nome produto", "Unidade produto", "Descrição produto", "22/10/2019");
                    Object[] data = {p.getId(),p.getNome(),p.getUnidade(),p.getData(), "Editar","Excluir","Ver"};
                    tableModel.addRow(data);
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

        setBorder(javax.swing.BorderFactory.createEmptyBorder(50, 25, 50, 25));
        setMinimumSize(new java.awt.Dimension(1, 1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
