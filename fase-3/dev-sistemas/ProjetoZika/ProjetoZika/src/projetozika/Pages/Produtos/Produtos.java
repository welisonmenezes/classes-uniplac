/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.Produtos;

import Config.Environment;
import Models.Produto;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
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

    private JButton addMore;
    private JTextField fNome;
    private JComboBox<String> funidade;
    private JDateChooser fData;
    private JLabel lNome;
    private JLabel lUnidade;
    private JLabel lData;
    private JButton bSearch;
    private JLabel hideL;
    private ArrayList<Produto> produtos;

    /**
     * Cria a tela de fornecedores
     * @param params Parâmetros para filtro e paginação
     */
    public Produtos(Properties params) {
        super();
        self = this;
        this.params = params;
        initPage();
    }
    
    private void initPage() {
        initComponents();
        createBaseLayout();
        
        produtos = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Produto p = new Produto(i, "Nome produto", "Unidade produto", "Descrição produto", "22/10/2019");
            p.setId(i);
            produtos.add(p);
        }
        
        addTopContent(Methods.getTranslation("Produtos"));
        addCenterContent();
        addBottomContent();
        addFilterContent();
        
        updateParams();
    }
    
    private void updateParams() {
        String date = ((JTextField) fData.getDateEditor().getUiComponent()).getText();
        params.setProperty("page", "1");
        params.setProperty("nome", fNome.getText());
        params.setProperty("data", date);
        params.setProperty("unidade", funidade.getSelectedItem().toString());
    }
     
    // Adiciona conteúdo ao centro da area de conteúdo
    private void addCenterContent() {
        barraRolagem = new JScrollPane();
        Styles.defaultScroll(barraRolagem);
        updateCenterContent();
        pCenter.add(barraRolagem, BorderLayout.CENTER);
    }
    
    private void updateCenterContent() {
        makeTable();
        barraRolagem.getViewport().setView(tabela);
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
            Methods.getTranslation("Nome"), 
            Methods.getTranslation("Unidade"), 
            Methods.getTranslation("Data"), 
            Methods.getTranslation("Editar"), 
            Methods.getTranslation("Excluir"), 
            Methods.getTranslation("Ver")
        };
       // seta modelo
        tableModel = new DefaultTableModel(null, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
               if (column != 4 && column != 5 && column != 6) {
                   return false;
               }
               return true;
            }
        };
        // adiciona linhas
        produtos.forEach(p -> {
            Object[] data = {
                p.getId(),
                p.getNome(),
                p.getUnidade(),
                p.getData(),
                Methods.getTranslation("Editar"),
                Methods.getTranslation("Excluir"),
                Methods.getTranslation("Ver")
            };
            tableModel.addRow(data);
        });
        // inicializa
        tabela.setModel(tableModel);
        
        tabela.getColumn(Methods.getTranslation("Editar")).setCellRenderer(new ButtonRenderer());
        tabela.getColumn(Methods.getTranslation("Editar")).setCellEditor(new ButtonEditor(new JCheckBox()){
            @Override
            public void buttonAction() {
                String id = Methods.selectedTableItemId(tabela);
                Navigation.updateLayout("editarProduto", id, params);
            }
        });
        
        tabela.getColumn(Methods.getTranslation("Excluir")).setCellRenderer(new ButtonRenderer());
        tabela.getColumn(Methods.getTranslation("Excluir")).setCellEditor(new ButtonEditor(new JCheckBox()){
            @Override
            public void buttonAction() {
                String idTabel = Methods.selectedTableItemId(tabela);

                int opcion = JOptionPane.showConfirmDialog(null, Methods.getTranslation("DesejaRealmenteExcluir?"), "Aviso", JOptionPane.YES_NO_OPTION);
                if (opcion == 0) {
                    for (int i = 0; i < produtos.size(); i++) {
                        Produto p = produtos.get(i);
                        if (idTabel.equals(""+p.getId())) {
                            produtos.remove(p);
                        }
                    }
                    updateCenterContent();
                   JOptionPane.showMessageDialog(null, Methods.getTranslation("DeletadoComSucesso"));
                }
            }
        });
        
        tabela.getColumn(Methods.getTranslation("Ver")).setCellRenderer(new ButtonRenderer());
        tabela.getColumn(Methods.getTranslation("Ver")).setCellEditor(new ButtonEditor(new JCheckBox()){
            @Override
            public void buttonAction() {
                String id = Methods.selectedTableItemId(tabela);
                Navigation.updateLayout("verProduto", id, params);
            }
        });
    }
    
    /**
     * Adiciona o conteúdo à area de filtro da tela de conteúdo
     */
    private void addFilterContent() {
        
        fNome = new JTextField();
        Styles.defaultField(fNome, 150);
        fNome.setText(params.getProperty("nome", ""));
        
        lNome = new JLabel(Methods.getTranslation("Nome"));
        Styles.defaultLabel(lNome, false);
        
        funidade = new JComboBox();
        funidade.setModel(new DefaultComboBoxModel(Environment.UNIDADES));
        Styles.defaultComboBox(funidade);
        funidade.setSelectedItem(params.getProperty("unidade", ""));
        
        lUnidade = new JLabel(Methods.getTranslation("Unidade"));
        Styles.defaultLabel(lUnidade, false);
        
        fData = new JDateChooser();
        Styles.defaultDateChooser(fData);
        try {
            Date newDate = new SimpleDateFormat("dd/MM/yyyy").parse(params.getProperty("data", ""));
            fData.setDate(newDate);
        } catch (ParseException ex) {}
        
        lData = new JLabel(Methods.getTranslation("Data"));
        Styles.defaultLabel(lData, false);
       
        bSearch = new JButton("");
        Styles.searchButton(bSearch);
        
        hideL = new JLabel();
        hideL.setPreferredSize(new Dimension(50, 35));
        
        addMore = new JButton(Methods.getTranslation("CriarNovo"));
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
        addMore.addActionListener((ActionEvent e) -> {
            Navigation.updateLayout("addProduto", params);
        });
        
        // click do buscar
        bSearch.addActionListener((ActionEvent e) -> {
            Dialogs.showLoadPopup(self);
            
            updateParams();
            
            timerTest();
        });
    }
    
    /**
     * Adiciona o conteúdo à area de footer do conteúdo
     */
    private void addBottomContent() {
        this.pagination(5);
    }
    
    /**
     * Gera a paginação
     * 
     * @param total o total de páginas
     */
    private void pagination(int total) {
        Pagination pag = new Pagination(pBottom, total){
            @Override
            public void callbackPagination() {
                
                params.setProperty("page", ""+this.page);
                
                Dialogs.showLoadPopup(self);
                timerTest();
            }
        };
    }
    
    private Timer t;
    private void timerTest() {
        
        t = new Timer(2000, (ActionEvent e) -> {
            Dialogs.hideLoadPopup(self);
            
            // reseta tabela
            for (int i = 0; i < produtos.size(); i++) {
                Produto p = produtos.get(i);
                if (p.getId()> 10) {
                    produtos.remove(p);
                }
            }
            updateCenterContent();
            pagination(3);
            
            t.stop();
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
