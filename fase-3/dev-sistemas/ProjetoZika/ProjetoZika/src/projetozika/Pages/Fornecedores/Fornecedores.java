/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.Fornecedores;

import DAO.FornecedorDAO;
import Models.Fornecedor;
import Templates.ButtonEditor;
import Templates.ButtonRenderer;
import Utils.Dialogs;
import Utils.Methods;
import Utils.Navigation;
import Utils.Pagination;
import Utils.Styles;
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
import javax.swing.table.TableColumn;
/**
 * Tela de listagem do fornecedores
 * 
 * @author Welison
 */
public class Fornecedores extends Templates.BaseLayout {
    
    private JButton addMore;
    private JTextField fCnpj;
    private JTextField fNome;
    private JTextField fTelefone;
    private JLabel lNome;
    private JLabel lCnpj;
    private JLabel lTelefone;
    private JButton bSearch;
    private ArrayList<Fornecedor> fornecedores;
    private FornecedorDAO fornecedorDAO;
    private int totalFornecedores;

    /**
     * Cria a tela de fornecedores
     * @param params Parâmetros para filtro e paginação
     */
    public Fornecedores(Properties params) {
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
        fornecedorDAO = new FornecedorDAO();
        fornecedores = fornecedorDAO.selecionar(params);
        totalFornecedores = fornecedorDAO.total(params);
        
        // constroi o layout
        initComponents();
        createBaseLayout();
        addTopContent(Methods.getTranslation("Fornecedores"));
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
        params.setProperty("offset", "0");
        params.setProperty("page", "1");
        params.setProperty("nome", fNome.getText());
        params.setProperty("cnpj", fCnpj.getText());
        params.setProperty("telefone", fTelefone.getText());
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
            Methods.getTranslation("CNPJ"),
            Methods.getTranslation("Telefone"),
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
        fornecedores.forEach(f -> {
            Object[] data = {
                f.getId(),
                f.getNome(),
                f.getCnpj(),
                f.getTelefone(),
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
                Navigation.updateLayout("editarFornecedor", id, params);
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
                    
                    // deleta o fornecedor da base
                    try {
                        fornecedorDAO.deletar(Integer.parseInt(idTabel));
                        JOptionPane.showMessageDialog(null, Methods.getTranslation("DeletadoComSucesso"));
                    } catch(Exception error) {
                        JOptionPane.showMessageDialog(null, Methods.getTranslation("ErroAoTentarDeletar"));
                        throw new RuntimeException("Fornecedores.delete: " + error);
                    }
                    // 'recarrega a tela'
                    Navigation.updateLayout("", new Properties());
                    Navigation.updateLayout("fornecedores", params);
                    
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
                Navigation.updateLayout("verFornecedor", id, params);
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
        
        fCnpj = new JTextField();
        Styles.defaultField(fCnpj, 150);
        fCnpj.setText(params.getProperty("cnpj", ""));
        
        lCnpj = new JLabel(Methods.getTranslation("CNPJ"));
        Styles.defaultLabel(lCnpj, false);
        
        fTelefone = new JTextField();
        Styles.defaultField(fTelefone, 150);
        fTelefone.setText(params.getProperty("telefone", ""));
        
        lTelefone = new JLabel(Methods.getTranslation("Telefone"));
        Styles.defaultLabel(lTelefone, false);
        
        bSearch = new JButton("");
        Styles.searchButton(bSearch);
        
        JLabel hideL = new JLabel();
        hideL.setPreferredSize(new Dimension(50, 35));
        
        addMore = new JButton(Methods.getTranslation("CriarNovo"));
        Styles.defaultButton(addMore);
        
        pFilter.add(lNome);
        pFilter.add(fNome);
        pFilter.add(lCnpj);
        pFilter.add(fCnpj);
        pFilter.add(lTelefone);
        pFilter.add(fTelefone);
        pFilter.add(bSearch);
        pFilter.add(hideL);
        pFilter.add(addMore);
        
        // click do adicionar novo
        addMore.addActionListener((ActionEvent e) -> {
            Navigation.updateLayout("addFornecedor", params);
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
        this.pagination(totalFornecedores);
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
            fornecedores.clear();
            fornecedores = fornecedorDAO.selecionar(params);
            totalFornecedores = fornecedorDAO.total(params);
            updateCenterContent();
            pagination(totalFornecedores);
            
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
        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
