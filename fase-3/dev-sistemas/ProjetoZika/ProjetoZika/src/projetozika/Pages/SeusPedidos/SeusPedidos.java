/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.SeusPedidos;

import Config.Environment;
import DAO.PedidoDAO;
import Models.Pedido;
import Models.Usuario;
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
public class SeusPedidos extends Templates.BaseLayout {

    private JButton btnAddPedido;
    private JDateChooser fData;
    private JComboBox<String> fStatus;
    private JLabel lData;
    private JLabel lStatus;
    private JButton bSearch;
    private ArrayList<Pedido> pedidos;
    private PedidoDAO pedidoDao;
    private int totalPedidos;

    /**
     * Cria a tela de fornecedores
     * @param params Parâmetros para filtro e paginação
     */
    public SeusPedidos(Properties params) {
        super();
        self = this;
        this.params = params;
        
        initPage();
    }
    
    private void initPage() {
        initComponents();
        createBaseLayout();
        
        pedidoDao = new PedidoDAO();
        pedidos = pedidoDao.selecionarPorUsuario(Environment.getLoggedUser(), params);
        totalPedidos = pedidoDao.totalPorUsuario(Environment.getLoggedUser(), params);

        addTopContent(Methods.getTranslation("SeusPedidos"));
        addCenterContent();
        addBottomContent();
        addFilterContent();
        
        updateParams();
    }
    
    private void updateParams() {
        String date = ((JTextField) fData.getDateEditor().getUiComponent()).getText();
        params.setProperty("offset", "0");
        params.setProperty("page", "1");
        params.setProperty("data", date);
        params.setProperty("status", fStatus.getSelectedItem().toString());
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
            Methods.getTranslation("Data"), 
            Methods.getTranslation("Status"), 
            Methods.getTranslation("Editar"), 
            Methods.getTranslation("Cancelar"), 
            Methods.getTranslation("Ver")
        };
       // seta modelo
        tableModel = new DefaultTableModel(null, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
               if(column != 3 && column != 4 && column != 5){
                   return false;
               }
               return true;
            }
        };
        // adiciona linhas
        pedidos.forEach(p -> {
            String btnEditar = Methods.getTranslation("Editar");
            String btnCancelar = Methods.getTranslation("Cancelar");
            if (! p.getStatus().equals(Methods.getTranslation("Pendente")) ) {
                btnEditar = "";
                btnCancelar = "";
            } 
            Object[] data = {
                p.getId(),
                p.getCreated(),
                p.getStatus(),
                btnEditar,
                btnCancelar,
                Methods.getTranslation("Ver")
            };
            tableModel.addRow(data);
        });
        // inicializa
        tabela.setModel(tableModel);
        
        TableColumn colEditar = tabela.getColumn(Methods.getTranslation("Editar"));
        colEditar.setMaxWidth(40);
        colEditar.setCellRenderer(new ButtonRenderer());
        colEditar.setCellEditor(new ButtonEditor(new JCheckBox()){
            @Override
            public void buttonAction() {
                String id = Methods.selectedTableItemId(tabela);
                int row = tabela.getSelectedRow();
                String actionValue = (String)tabela.getModel().getValueAt(row, 4);
                if (!actionValue.equals("")) {
                    Navigation.updateLayout("editarSeuPedido", id, params);
                }
            }
        });
        
        TableColumn colCancelar = tabela.getColumn(Methods.getTranslation("Cancelar"));
        colCancelar.setMaxWidth(40);
        colCancelar.setPreferredWidth(40);
        colCancelar.setCellRenderer(new ButtonRenderer());
        colCancelar.setCellEditor(new ButtonEditor(new JCheckBox()){
            @Override
            public void buttonAction() {
                int row = tabela.getSelectedRow();
                String actionValue = (String)tabela.getModel().getValueAt(row, 4);
                String idTabel = Methods.selectedTableItemId(tabela);
                
                if (!actionValue.equals("")) {
                    
                    int opcion = JOptionPane.showConfirmDialog(null, Methods.getTranslation("DesejaRealmenteExcluir?"), "Aviso", JOptionPane.YES_NO_OPTION);
                    if (opcion == 0) {
                        
                        // deleta o produto da base
                        try {
                            pedidoDao.deletar(Integer.parseInt(idTabel));
                            JOptionPane.showMessageDialog(null, Methods.getTranslation("DeletadoComSucesso"));
                        } catch(Exception error) {
                            JOptionPane.showMessageDialog(null, Methods.getTranslation("ErroAoTentarDeletar"));
                            throw new RuntimeException("SeusPedidos.delete: " + error);
                        }
                        // 'recarrega a tela'
                        Navigation.updateLayout("", new Properties());
                        Navigation.updateLayout("seusPedidos", params);
                       
                    }
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
                Navigation.updateLayout("verSeuPedido", id, params);
            }
        });
    }
    
    /**
     * Adiciona o conteúdo à area de filtro da tela de conteúdo
     */
    private void addFilterContent() {
        fData = new JDateChooser();
        Styles.defaultDateChooser(fData);
        Methods.setDateChooserFormat(fData);
        Methods.setParamsToDateChooser(fData, params);
        
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
        
        btnAddPedido = new JButton(Methods.getTranslation("FazerNovoPedido"));
        Styles.defaultButton(btnAddPedido);
        btnAddPedido.setPreferredSize(new Dimension(200, 35));
        
        pFilter.add(lData);
        pFilter.add(fData);
        pFilter.add(lStatus);
        pFilter.add(fStatus);
        pFilter.add(bSearch);
        pFilter.add(btnAddPedido);
        

        // click do buscar
        bSearch.addActionListener((ActionEvent e) -> {
            Dialogs.showLoadPopup(self);
            
            updateParams();
            
            timerTest();
        });
        
        // click do add pedido
        btnAddPedido.addActionListener((ActionEvent e) -> {
            Navigation.updateLayout("fazerPedido", params);
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
            pedidos.clear();
            pedidos = pedidoDao.selecionarPorUsuario(Environment.getLoggedUser(), params);
            totalPedidos = pedidoDao.totalPorUsuario(Environment.getLoggedUser(), params);
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
