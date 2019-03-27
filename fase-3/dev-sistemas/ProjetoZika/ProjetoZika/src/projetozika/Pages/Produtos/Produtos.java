/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.Produtos;

import Config.Environment;
import DAO.ProdutoDAO;
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
import java.util.ArrayList;
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
import javax.swing.table.TableColumn;
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
    private ProdutoDAO produtoDao;
    private int totalProdutos;

    /**
     * Cria a tela de produtos
     * @param params Parâmetros para filtro e paginação
     */
    public Produtos(Properties params) {
        super();
        self = this;
        this.params = params;
        initPage();
    }
    
    /**
     * Inicializa a tela
     */
    private void initPage() {
        
        // carrega os dados
        produtoDao = new ProdutoDAO();
        produtos = produtoDao.selecionar(params);
        totalProdutos = produtoDao.total(params);
        
        // constroi o layout
        initComponents();
        createBaseLayout();
        addTopContent(Methods.getTranslation("Produtos"));
        addCenterContent();
        addBottomContent();
        addFilterContent();
        
        // seta os parâmetros
        updateParams();
    }
    
    /**
     * Seta os parâmetros a serem usados na paginação e no filtro
     */
    private void updateParams() {
        String date = ((JTextField) fData.getDateEditor().getUiComponent()).getText();
        params.setProperty("offset", "0");
        params.setProperty("page", "1");
        params.setProperty("nome", fNome.getText());
        params.setProperty("data", date);
        params.setProperty("unidade", funidade.getSelectedItem().toString());
    }
     
    /**
     * Adiciona conteúdo ao centro da area de conteúdo
     */
    private void addCenterContent() {
        barraRolagem = new JScrollPane();
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
                p.getCreated(),
                Methods.getTranslation("Editar"),
                Methods.getTranslation("Excluir"),
                Methods.getTranslation("Ver")
            };
            tableModel.addRow(data);
        });
        // inicializa
        tabela.setModel(tableModel);
        
        TableColumn colEditar = tabela.getColumn(Methods.getTranslation("Editar"));
        colEditar.setMaxWidth(65);
        colEditar.setCellRenderer(new ButtonRenderer());
        colEditar.setCellEditor(new ButtonEditor(new JCheckBox()){
            @Override
            public void buttonAction() {
                String id = Methods.selectedTableItemId(tabela);
                Navigation.updateLayout("editarProduto", id, params);
            }
        });
        
        TableColumn colExcluir = tabela.getColumn(Methods.getTranslation("Excluir"));
        colExcluir.setMaxWidth(65);
        colExcluir.setCellRenderer(new ButtonRenderer());
        colExcluir.setCellEditor(new ButtonEditor(new JCheckBox()){
            @Override
            public void buttonAction() {
                String idTabel = Methods.selectedTableItemId(tabela);

                int opcion = JOptionPane.showConfirmDialog(null, Methods.getTranslation("DesejaRealmenteExcluir?"), "Aviso", JOptionPane.YES_NO_OPTION);
                if (opcion == 0) {
                    
                    // deleta o produto da base
                    try {
                        produtoDao.deletar(Integer.parseInt(idTabel));
                        JOptionPane.showMessageDialog(null, Methods.getTranslation("DeletadoComSucesso"));
                    } catch(Exception error) {
                        JOptionPane.showMessageDialog(null, Methods.getTranslation("ErroAoTentarDeletar"));
                        throw new RuntimeException("Produtos.delete: " + error);
                    }
                    // 'recarrega a tela'
                    Navigation.updateLayout("", new Properties());
                    Navigation.updateLayout("produtos", params);

                }
            }
        });
        
        TableColumn colVer = tabela.getColumn(Methods.getTranslation("Ver"));
        colVer.setMaxWidth(60);
        colVer.setCellRenderer(new ButtonRenderer());
        colVer.setCellEditor(new ButtonEditor(new JCheckBox()){
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
        Methods.setDateChooserFormat(fData);
        Methods.setParamsToDateChooser(fData, params);
        
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
            // atualiza os parâmetros com os dados do form de busca
            updateParams();
            timerTest();
        });
    }
    
    /**
     * Adiciona o conteúdo à area de footer do conteúdo
     */
    private void addBottomContent() {
        this.pagination(totalProdutos);
    }
    
    /**
     * Gera a paginação com base no total de páginas
     * @param total o total de páginas
     */
    private void pagination(int total) {
        Pagination pag = new Pagination(pBottom, total, params){
            @Override
            public void callbackPagination() {
                Dialogs.showLoadPopup(self);
                timerTest();
            }
        };
    }
    
    private Timer t;
    private void timerTest() {
        
        t = new Timer(500, (ActionEvent e) -> {
            Dialogs.hideLoadPopup(self);
            
            // reseta tabela e recarrega os dados
            produtos.clear();
            produtos = produtoDao.selecionar(params);
            totalProdutos = produtoDao.total(params);
            updateCenterContent();
            pagination(totalProdutos);
            
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
