/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.NotasFiscais;


import DAO.NotaFiscalDAO;
import Models.NotaFiscal;
import CustomFields.ButtonEditor;
import CustomFields.ButtonRenderer;
import CustomFields.MaskFactory;
import DAO.EstoqueDAO;
import Models.NotaFiscalProduto;
import Utils.DateHandler;
import Utils.Dialogs;
import Utils.Methods;
import Utils.Navigation;
import Utils.Pagination;
import Utils.Styles;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 * Tela de listagem das notas fiscais
 * @author Welison
 */
public class NotasFiscais extends Templates.BaseLayout {

    private JButton addMore;
    private JDateChooser fdata;
    private JFormattedTextField fcnpj;
    private JLabel ldata;
    private JLabel lcnpj;
    private JButton bSearch;
    private ArrayList<NotaFiscal> notasFiscais;
    private NotaFiscalDAO notaFiscalDao;
    private int totalNotasFiscais;
    private JTextField fnumero;
    private JLabel lnumero;
    private EstoqueDAO estoqueDao;
    
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
    
    /**
     * Inicializa a tela
     */
    private void initPage() {
        
        // carrega os dados
        notaFiscalDao = new NotaFiscalDAO();
        notasFiscais = notaFiscalDao.selecionar(params);
        totalNotasFiscais = notaFiscalDao.total(params);
        estoqueDao = new EstoqueDAO();
        
        // constroi o layout
        initComponents();
        createBaseLayout();
        addTopContent(Methods.getTranslation("NotasFiscais"));
        addFilterContent();
        addCenterContent();
        addBottomContent();
        
        // seta os parâmetros
        updateParams();
    }
    
    /**
     * Seta os parâmetros a serem usados na paginação e no filtro
     */
    private void updateParams() {
        String date = ((JTextField) fdata.getDateEditor().getUiComponent()).getText();
        params.setProperty("offset", "0");
        params.setProperty("orderby", "nId");
        params.setProperty("orderkey", "0");
        params.setProperty("page", "1");
        params.setProperty("cnpj", fcnpj.getText().replace(".","").replace("/","").replace("-","").replace("_",""));
        params.setProperty("numero", fnumero.getText());
        params.setProperty("data", date);
    }
    
    /**
     * Adiciona conteúdo ao centro da area de conteúdo
     */
    private void addCenterContent() {
        barraRolagem = new JScrollPane(tabela);
        Styles.defaultScroll(barraRolagem);
        updateCenterContent();
        pCenter.add(barraRolagem, BorderLayout.CENTER);
    }
    
    /**
     * Atualiza o conteúdo do centro da area de conteúdo
     */
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
            Methods.getTranslation("Numero"),
            Methods.getTranslation("Valor"),
            Methods.getTranslation("CNPJ"),
            Methods.getTranslation("Data"),
            Methods.getTranslation("Editar"),
            Methods.getTranslation("Excluir"),
            Methods.getTranslation("Ver")
        };
        // informando os tipos das colunas para auxiliar na ordenação
        final Class<?>[] columnClasses = new Class<?>[] {
            Integer.class, 
            Long.class, 
            Double.class, 
            String.class, 
            Date.class, 
            String.class, 
            String.class, 
            String.class
        };
       // seta modelo
        tableModel = new DefaultTableModel(null, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
               if (column != 5 && column != 6 && column != 7) {
                   return false;
               }
               return true;
            }
            @Override
            public Class<?> getColumnClass(int column) {
                return columnClasses[column];
            }
        };
        // adiciona linhas
        notasFiscais.forEach(n -> {
            Object[] data = {
                n.getId(),
                n.getNumero(),
                n.getValor(),
                n.getFornecedor().getCnpj(),
                DateHandler.getJavaDate(n.getData()),
                Methods.getTranslation("Editar"),
                Methods.getTranslation("Excluir"),
                Methods.getTranslation("Ver")
            };
            tableModel.addRow(data);
        });
        // inicializa
        tabela.setModel(tableModel);
        
        // add actions para os botões da tabela
        actionsTable();
        
        // add funcionalidade de ordenação na tabela
        sortTable();
    }
    
    /**
     * adiciona ações para os botões da tabela
     */
    private void actionsTable() {
        TableColumn colEditar = tabela.getColumn(Methods.getTranslation("Editar"));
        colEditar.setMaxWidth(40);
        colEditar.setCellRenderer(new ButtonRenderer());
        colEditar.setCellEditor(new ButtonEditor(new JCheckBox()){
            @Override
            public void buttonAction() {
                String id = Methods.selectedTableItemId(tabela);
                Navigation.updateLayout("editarNotaFiscal", id, params);
            }
        });
        
        TableColumn colExcluir = tabela.getColumn(Methods.getTranslation("Excluir"));
        colExcluir.setMaxWidth(40);
        colExcluir.setCellRenderer(new ButtonRenderer());
        colExcluir.setCellEditor(new ButtonEditor(new JCheckBox()){
            @Override
            public void buttonAction() {
                String idTabel = Methods.selectedTableItemId(tabela);

                int opcion = JOptionPane.showConfirmDialog(null, Methods.getTranslation("DesejaRealmenteExcluir?"), "Aviso", JOptionPane.YES_NO_OPTION);
                if (opcion == 0) {
                    // deleta o a nota fiscal da base
                    try {
                        notaFiscalDao.deletar(Integer.parseInt(idTabel));
                        
                        ArrayList<NotaFiscalProduto> oldProdutos = notaFiscalDao.selecionarProdutos(idTabel);
                        oldProdutos.forEach(oldProduto -> {
                            // subtrai do estoque
                            estoqueDao.alterar(oldProduto.getProduto().getId(), -oldProduto.getQuantidade());
                        });
                        
                        JOptionPane.showMessageDialog(null, Methods.getTranslation("DeletadoComSucesso"));
                    } catch(HeadlessException | NumberFormatException error) {
                        JOptionPane.showMessageDialog(null, Methods.getTranslation("ErroAoTentarDeletar"));
                        throw new RuntimeException("NotasFiscais.delete: " + error);
                    }
                    // 'recarrega a tela'
                    Navigation.updateLayout("", new Properties());
                    Navigation.updateLayout("notasFiscais", params);
                }
            }
        });
        
        TableColumn colVer = tabela.getColumn(Methods.getTranslation("Ver"));
        colVer.setMaxWidth(40);
        colVer.setCellRenderer(new ButtonRenderer());
        colVer.setCellEditor(new ButtonEditor(new JCheckBox()){
            @Override
            public void buttonAction() {
                String id = Methods.selectedTableItemId(tabela);
                Navigation.updateLayout("verNotaFiscal", id, params);
            }
        });
    }
    
    /**
     * torna a tabela ordenável
     */
    private void sortTable() {
        
        // torna a tabela ordenável
        Methods.makeTableSortable(tabela, 4, params);
        
        // ouve o evento de click no header da tabela
        tabela.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = tabela.columnAtPoint(e.getPoint());
                
                if (col <= 4) {
                    Dialogs.showLoadPopup(self);
                    updateParams();

                    if (params.getProperty("order", "DESC").equals("DESC")) {
                        params.setProperty("order", "ASC");
                    } else {
                        params.setProperty("order", "DESC");
                    }

                    switch (col) {
                        case 0 : 
                            params.setProperty("orderby", "nId");
                            params.setProperty("orderkey", "0");
                            break;
                        case 1 :
                            params.setProperty("orderby", "nNumero");
                            params.setProperty("orderkey", "1");
                            break;
                        case 2 :
                            params.setProperty("orderby", "nValor");
                            params.setProperty("orderkey", "2");
                            break;
                        case 3 :
                            params.setProperty("orderby", "fCnpj");
                            params.setProperty("orderkey", "3");
                            break;
                        case 4 :
                            params.setProperty("orderby", "nData");
                            params.setProperty("orderkey", "4");
                            break;
                    }

                    timerTest();
                }
            }
        });
    }
    
    /**
     * Adiciona o conteúdo à area de filtro da tela de conteúdo
     */
    private void addFilterContent() {
        addMore = new JButton(Methods.getTranslation("CriarNovo"));
        Styles.defaultButton(addMore);
        
        fcnpj = new JFormattedTextField();
        Styles.defaultField(fcnpj, 150);
        fcnpj.setText(params.getProperty("cnpj", ""));
        fcnpj.setFormatterFactory(MaskFactory.setMaskCnpj());
        
        lcnpj = new JLabel(Methods.getTranslation("CNPJ"));
        Styles.defaultLabel(lcnpj, false);
        
        fnumero = new JTextField();
        Styles.defaultField(fnumero, 150);
        fnumero.setText(params.getProperty("numero", ""));
        
        lnumero = new JLabel(Methods.getTranslation("Numero"));
        Styles.defaultLabel(lnumero, false);
        
        fdata = new JDateChooser();
        Styles.defaultDateChooser(fdata);
        DateHandler.setDateChooserFormat(fdata);
        DateHandler.setParamsToDateChooser(fdata, params);
        
        ldata = new JLabel(Methods.getTranslation("Data"));
        Styles.defaultLabel(ldata, false);
        
        bSearch = new JButton("");
        Styles.searchButton(bSearch);
        
        JLabel hideL = new JLabel();
        hideL.setPreferredSize(new Dimension(50, 35));
        
        pFilter.add(lnumero);
        pFilter.add(fnumero);
        pFilter.add(lcnpj);
        pFilter.add(fcnpj);
        pFilter.add(ldata);
        pFilter.add(fdata);
        pFilter.add(bSearch);
        pFilter.add(hideL);
        pFilter.add(addMore);
        
        // click do adicionar novo
        addMore.addActionListener((ActionEvent e) -> {
            Navigation.updateLayout("addNotaFiscal", params);
        });
        
        // click do buscar
        bSearch.addActionListener((ActionEvent e) -> {
            Dialogs.showLoadPopup(self);
            // atualiza os parâmetros com os dados do form de busca
            updateParams();
            timerTest();
        });
    }
    
    /**
     * Adiciona o conteúdo à area de footer do conteúdo
     */
    private void addBottomContent() {
        this.pagination(totalNotasFiscais);
    }
    
    /**
     * Gera a paginação com base no total de páginas
     * @param total o total de páginas
     */
    private void pagination(int total) {
        new Pagination(pBottom, total, params){
            @Override
            public void callbackPagination() {
                Dialogs.showLoadPopup(self);
                timerTest();
            }
        };
    }
    
    private Timer t;
    private void timerTest() {
        
        t = new Timer(250, (ActionEvent e) -> {
            Dialogs.hideLoadPopup(self);
            
            // reseta tabela e recarrega os dados
            notasFiscais.clear();
            notasFiscais = notaFiscalDao.selecionar(params);
            totalNotasFiscais = notaFiscalDao.total(params);
            updateCenterContent();
            pagination(totalNotasFiscais);
            
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
