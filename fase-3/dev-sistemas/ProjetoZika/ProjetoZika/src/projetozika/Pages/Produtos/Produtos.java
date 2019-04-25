package projetozika.Pages.Produtos;

import Config.Environment;
import DAO.ProdutoDAO;
import Models.Produto;
import CustomFields.ButtonEditor;
import CustomFields.ButtonRenderer;
import Templates.BaseLayout;
import Utils.DateHandler;
import Utils.Dialogs;
import Utils.AccessibilityManager;
import Utils.Methods;
import Utils.Navigation;
import Utils.Pagination;
import Utils.Styles;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 * Tela de listagem do produtos
 * @author Welison
 */
public class Produtos extends BaseLayout {

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
        self = getInstance();
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
        params.setProperty("orderby", "Id");
        params.setProperty("orderkey", "0");
        params.setProperty("page", "1");
        params.setProperty("nome", fNome.getText().trim());
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
            Methods.getTranslation("Total"),
            "", 
            "",
            ""
        };
        // informando os tipos das colunas para auxiliar na ordenação
        final Class<?>[] columnClasses = new Class<?>[] {
            Integer.class, 
            String.class, 
            String.class, 
            Date.class, 
            Integer.class, 
            String.class, 
            String.class, 
            String.class
        };
       // seta modelo
        tableModel = new DefaultTableModel(null, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
               return !(column != 5 && column != 6 && column != 7);
            }
            @Override
            public Class<?> getColumnClass(int column) {
                return columnClasses[column];
            }
        };
        // adiciona linhas
        produtos.forEach(p -> {
            Object[] data = {
                p.getId(),
                p.getNome(),
                p.getUnidade(),
                DateHandler.getJavaDate(p.getCreated()),
                p.getTotal(),
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
        TableColumn colEditar = tabela.getColumnModel().getColumn(5);
        colEditar.setMaxWidth(40);
        colEditar.setCellRenderer(new ButtonRenderer());
        colEditar.setCellEditor(new ButtonEditor(new JCheckBox()){
            @Override
            public void buttonAction() {
                String id = Methods.selectedTableItemId(tabela);
                Navigation.updateLayout("editarProduto", id, params);
            }
        });
        
        TableColumn colExcluir = tabela.getColumnModel().getColumn(6);
        colExcluir.setMaxWidth(40);
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
                    } catch(HeadlessException | NumberFormatException error) {
                        JOptionPane.showMessageDialog(null, Methods.getTranslation("ErroAoTentarDeletar"));
                        throw new RuntimeException("Produtos.delete: " + error);
                    }
                    // 'recarrega a tela'
                    Navigation.updateLayout("", new Properties());
                    Navigation.updateLayout("produtos", params);

                }
            }
        });
        
        TableColumn colVer = tabela.getColumnModel().getColumn(7);
        colVer.setMaxWidth(40);
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
                            params.setProperty("orderby", "Id");
                            params.setProperty("orderkey", "0");
                            break;
                        case 1 :
                            params.setProperty("orderby", "Nome");
                            params.setProperty("orderkey", "1");
                            break;
                        case 2 :
                            params.setProperty("orderby", "Unidade");
                            params.setProperty("orderkey", "2");
                            break;
                        case 3 :
                            params.setProperty("orderby", "Created");
                            params.setProperty("orderkey", "3");
                            break;
                        case 4 :
                            params.setProperty("orderby", "Total");
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
        DateHandler.setDateChooserFormat(fData);
        DateHandler.setParamsToDateChooser(fData, params);
        
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
        new AccessibilityManager(addMore, 
            "addProduto",
            KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK),
            params) {
                @Override
                public void actionLink() {
                    Navigation.updateLayout(this.page, params);
                }
            };
        
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
