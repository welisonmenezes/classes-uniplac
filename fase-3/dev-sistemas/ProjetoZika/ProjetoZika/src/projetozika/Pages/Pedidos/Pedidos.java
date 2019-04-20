/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.Pedidos;

import Config.Environment;
import DAO.PedidoDAO;
import Models.Pedido;
import CustomFields.ButtonEditor;
import CustomFields.ButtonRenderer;
import Utils.DateHandler;
import Utils.Dialogs;
import Utils.Methods;
import Utils.Navigation;
import Utils.Pagination;
import Utils.Styles;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 * Tela de listagem do pedidos
 * @author Welison
 */
public class Pedidos extends Templates.BaseLayout {

    private JDateChooser fData;
    private JTextField fNome;
    private JComboBox<String> fStatus;
    private JLabel lNome;
    private JLabel lData;
    private JLabel lStatus;
    private JButton bSearch;
    private ArrayList<Pedido> pedidos;
    private PedidoDAO pedidoDao;
    private int totalPedidos;

    /**
     * Cria a tela de pedidos
     * @param params Parâmetros para filtro e paginação
     */
    public Pedidos(Properties params) {
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
        pedidoDao = new PedidoDAO();
        pedidos = pedidoDao.selecionar(params);
        totalPedidos = pedidoDao.total(params);
        
        // constroi o layout
        initComponents();
        createBaseLayout();
        addTopContent(Methods.getTranslation("Pedidos"));
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
        params.setProperty("orderby", "pedidos.Id");
        params.setProperty("orderkey", "0");
        params.setProperty("page", "1");
        params.setProperty("nome", fNome.getText().trim());
        params.setProperty("data", date);
        params.setProperty("status", fStatus.getSelectedItem().toString());
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
            Methods.getTranslation("Data"), 
            Methods.getTranslation("Status"), 
            "", 
            ""
        };
        // informando os tipos das colunas para auxiliar na ordenação
        final Class<?>[] columnClasses = new Class<?>[] {
            Integer.class, 
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
               if (column != 4 && column != 5) {
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
        pedidos.forEach(p -> {
            String btnValue = Methods.getTranslation("Entregar");
            String btnEdit = Methods.getTranslation("Ver");
            if (! p.getStatus().equals(Methods.getTranslation("AguardandoEntrega")) ) {
                btnValue = "";
            }
            if (p.getStatus().equals(Methods.getTranslation("Pendente"))) {
                btnEdit = Methods.getTranslation("Editar");
            }
            Object[] data = {
                p.getId(),
                p.getSolicitante().getNome(),
                DateHandler.getJavaDate(p.getCreated()),
                p.getStatus(),
                btnEdit, 
                btnValue
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
        TableColumn colVerEditar = tabela.getColumnModel().getColumn(4);
        colVerEditar.setPreferredWidth(40);
        colVerEditar.setMaxWidth(40);
        colVerEditar.setCellRenderer(new ButtonRenderer());
        colVerEditar.setCellEditor(new ButtonEditor(new JCheckBox()){
            @Override
            public void buttonAction() {
                String id = Methods.selectedTableItemId(tabela);
                String actionCommand = this.button.getActionCommand();
                if (actionCommand.equals(Methods.getTranslation("Editar"))) {
                    Navigation.updateLayout("editarPedido", id, params);
                } else {
                    Navigation.updateLayout("verPedido", id, params);
                }
            }
        });
        
        TableColumn colEntregar = tabela.getColumnModel().getColumn(5);
        colEntregar.setMaxWidth(40);
        colEntregar.setCellRenderer(new ButtonRenderer());
        colEntregar.setCellEditor(new ButtonEditor(new JCheckBox()){
            @Override
            public void buttonAction() {
                String id = Methods.selectedTableItemId(tabela);
                int col = tabela.getSelectedColumn();
                int row = tabela.getSelectedRow();
                String actionValue = (String)tabela.getModel().getValueAt(row, col);
                if (!actionValue.equals("")) {
                    Navigation.updateLayout("entregarPedido", id, params);
                }
            }
        });
    }
    
    /**
     * torna a tabela ordenável
     */
    private void sortTable() {
        
        // torna a tabela ordenável
        Methods.makeTableSortable(tabela, 3, params);
        
        // ouve o evento de click no header da tabela
        tabela.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = tabela.columnAtPoint(e.getPoint());
                
                if (col <= 3) {
                    Dialogs.showLoadPopup(self);
                    updateParams();

                    if (params.getProperty("order", "DESC").equals("DESC")) {
                        params.setProperty("order", "ASC");
                    } else {
                        params.setProperty("order", "DESC");
                    }

                    switch (col) {
                        case 0 : 
                            params.setProperty("orderby", "pedidos.Id");
                            params.setProperty("orderkey", "0");
                            break;
                        case 1 :
                            params.setProperty("orderby", "usuarios.Nome");
                            params.setProperty("orderkey", "1");
                            break;
                        case 2 :
                            params.setProperty("orderby", "pedidos.Created");
                            params.setProperty("orderkey", "2");
                            break;
                        case 3 :
                            params.setProperty("orderby", "pedidos.Status");
                            params.setProperty("orderkey", "3");
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
        
        fData = new JDateChooser();
        Styles.defaultDateChooser(fData);
        DateHandler.setDateChooserFormat(fData);
        DateHandler.setParamsToDateChooser(fData, params);
        
        lData = new JLabel(Methods.getTranslation("Data"));
        Styles.defaultLabel(lData, false);
        
        fStatus = new JComboBox();
        fStatus.setModel(new DefaultComboBoxModel(Environment.STATUS));
        Styles.defaultComboBox(fStatus);
        fStatus.setSelectedItem(params.getProperty("status", ""));
        
        lStatus = new JLabel(Methods.getTranslation("Status"));
        Styles.defaultLabel(lStatus, false);
        
        bSearch = new JButton("");
        Styles.searchButton(bSearch);
        
        pFilter.add(lNome);
        pFilter.add(fNome);
        pFilter.add(lData);
        pFilter.add(fData);
        pFilter.add(lStatus);
        pFilter.add(fStatus);
        pFilter.add(bSearch);
        

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
        this.pagination(totalPedidos);
    }
    
    /**
     * Gera a paginação
     * 
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
            pedidos.clear();
            pedidos = pedidoDao.selecionar(params);
            totalPedidos = pedidoDao.total(params);
            updateCenterContent();
            pagination(totalPedidos);
            
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
