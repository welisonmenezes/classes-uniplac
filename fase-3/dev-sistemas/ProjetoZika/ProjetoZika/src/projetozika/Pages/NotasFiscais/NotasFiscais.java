/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.NotasFiscais;


import Models.Fornecedor;
import Models.NotaFiscal;
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
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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

    private JButton addMore;
    private JDateChooser fdata;
    private JTextField fcnpj;
    private JLabel ldata;
    private JLabel lcnpj;
    private JButton bSearch;
    private ArrayList<NotaFiscal> notasFiscais;
    
    /**
     * Creates new form NotasFiscais
     * @param params Parâmetros para filtro e paginação
     */
    public NotasFiscais(Properties params) {
        super();
        this.self = this;
        this.params = params;
        
        initPage();
    }
    
    private void initPage() {
        initComponents();
        createBaseLayout();
        
        notasFiscais = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Fornecedor f = new Fornecedor(333,"Nome Fornecedor","333000333","99999999","10/11/2008");
            NotaFiscal n = new NotaFiscal(i, 222, 20.4f, "10/10/2019", f);
            notasFiscais.add(n);
        }
        
        addTopContent(Methods.getTranslation("NotasFiscais"));
        addFilterContent();
        addCenterContent();
        addBottomContent();
        
        updateParams();
    }
    
    private void updateParams() {
        String date = ((JTextField) fdata.getDateEditor().getUiComponent()).getText();
        params.setProperty("page", "1");
        params.setProperty("cnpj", fcnpj.getText());
        params.setProperty("data", date);
    }
    
    private void addCenterContent() {
        barraRolagem = new JScrollPane(tabela);
        Styles.defaultScroll(barraRolagem);
        updateCenterContent();
        pCenter.add(barraRolagem, BorderLayout.CENTER);
    }
    
    private void updateCenterContent() {
        makeTable();
        barraRolagem.getViewport().setView(tabela);
    }
    
    private void makeTable() {
        // cria tabela
        tabela = new JTable();
        tabela.setRowHeight(35);
        // seta colunas
        String[] colunas = {
            Methods.getTranslation("Codigo"),
            Methods.getTranslation("Valor"),
            Methods.getTranslation("CNPJ"),
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
        notasFiscais.forEach(n -> {
            Object[] data = {
                n.getNumero(),
                n.getValor(),
                n.getFornecedor().getCnpj(),
                n.getData(),
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
                Navigation.updateLayout("editarNotaFiscal", id, params);
            }
        });
        
        tabela.getColumn(Methods.getTranslation("Excluir")).setCellRenderer(new ButtonRenderer());
        tabela.getColumn(Methods.getTranslation("Excluir")).setCellEditor(new ButtonEditor(new JCheckBox()){
            @Override
            public void buttonAction() {
                String idTabel = Methods.selectedTableItemId(tabela);

                int opcion = JOptionPane.showConfirmDialog(null, Methods.getTranslation("DesejaRealmenteExcluir?"), "Aviso", JOptionPane.YES_NO_OPTION);
                if (opcion == 0) {
                    for (int i = 0; i < notasFiscais.size(); i++) {
                        NotaFiscal n = notasFiscais.get(i);
                        if (idTabel.equals(""+n.getNumero())) {
                            notasFiscais.remove(n);
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
                Navigation.updateLayout("verNotaFiscal", id, params);
            }
        });
    }
    
    private void addFilterContent() {
        addMore = new JButton(Methods.getTranslation("CriarNovo"));
        Styles.defaultButton(addMore);
        
        fcnpj = new JTextField();
        Styles.defaultField(fcnpj, 150);
        fcnpj.setText(params.getProperty("cnpj", ""));
        
        lcnpj = new JLabel(Methods.getTranslation("CNPJ"));
        Styles.defaultLabel(lcnpj, false);
        
        fdata = new JDateChooser();
        Styles.defaultDateChooser(fdata);
        Methods.setDateChooserFormat(fdata);
        Methods.setParamsToDateChooser(fdata, params);
        
        ldata = new JLabel(Methods.getTranslation("Data"));
        Styles.defaultLabel(ldata, false);
        
        bSearch = new JButton("");
        Styles.searchButton(bSearch);
        
        JLabel hideL = new JLabel();
        hideL.setPreferredSize(new Dimension(50, 35));
        
        pFilter.add(lcnpj);
        pFilter.add(fcnpj);
        pFilter.add(ldata);
        pFilter.add(fdata);
        pFilter.add(bSearch);
        pFilter.add(hideL);
        pFilter.add(addMore);
        
        addMore.addActionListener((ActionEvent e) -> {
            Navigation.updateLayout("addNotaFiscal", params);
        });
        
        bSearch.addActionListener((ActionEvent e) -> {
            Dialogs.showLoadPopup(self);
            
            updateParams();
            
            timerTest();
        });
    }
    
    private void addBottomContent() {
        this.pagination(5);
    }
    
    private void pagination(int total) {
        /*
        Pagination pag = new Pagination(pBottom, total){
            @Override
            public void callbackPagination() {
                
                params.setProperty("page", ""+this.page);
                
                Dialogs.showLoadPopup(self);
                timerTest();
            }
        };
        */
    }
    
    private Timer t;
    private void timerTest() {
        
        t = new Timer(2000, (ActionEvent e) -> {
            Dialogs.hideLoadPopup(self);
            
            for (int i = 0; i < notasFiscais.size(); i++) {
                NotaFiscal n = notasFiscais.get(i);
                if (n.getNumero()> 10) {
                    notasFiscais.remove(n);
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

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
