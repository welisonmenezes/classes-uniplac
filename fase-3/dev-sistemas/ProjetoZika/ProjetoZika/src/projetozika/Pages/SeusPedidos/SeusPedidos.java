/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.SeusPedidos;

import projetozika.Pages.Pedidos.*;
import Config.Environment;
import Models.Pedido;
import Models.Usuario;
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
public class SeusPedidos extends Templates.BaseLayout {
    
    private BaseLayout self;
    public static JTable tabela;
    public static DefaultTableModel tableModel;
    private JButton btnAddPedido;
    private JDateChooser fData;
    private JComboBox<String> fStatus;
    private JLabel lData;
    private JLabel lStatus;
    private JLabel lSearch;
    private JButton bSearch;
    private JScrollPane barraRolagem;

    /**
     * Cria a tela de fornecedores
     */
    public SeusPedidos() {
        super();
        self = this;
        initComponents();
        createBaseLayout();
        addTopContent("Seus Pedidos");
        addCenterContent();
        addBottomContent();
        addFilterContent();
    }
    
    // Adiciona conteúdo ao centro da area de conteúdo
    public void addCenterContent() {
        makeTable();
        barraRolagem = new JScrollPane(tabela);
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
        String[] colunas = {"Código", "Data", "Status", "Ver"};
       // seta modelo
        tableModel = new DefaultTableModel(null, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
               if(column != 3){
                   return false;
               }
               return true;
            }
        };
        // adiciona linhas
        for(int i = 0; i < 25; i++) {
            Usuario u = new Usuario("111111-22", "Nome Usuario", "email@email.com", "99999-9999", "2222-2222", "Contabilidade", "M", "admin", "12/12/1989");
            Pedido p = new Pedido("10/11/2019", "Pendente", u);
            Object[] data = {p.getCodigo(),p.getData(),p.getStatus(),"Ver"};
            tableModel.addRow(data);
        }
        // inicializa
        tabela.setModel(tableModel);
        
        tabela.getColumn("Ver").setCellRenderer(new ButtonRenderer());
        tabela.getColumn("Ver").setCellEditor(new ButtonEditor(new JCheckBox()){
            @Override
            public void buttonAction() {
                String id = Methods.selectedTableItemId(tabela);
                //Navigation.updateLayout("editarPedido", id);
            }
        });
    }
    
    /**
     * Adiciona o conteúdo à area de filtro da tela de conteúdo
     */
    public void addFilterContent() {
        fData = new JDateChooser();
        Styles.defaultDateChooser(fData);
        
        lData = new JLabel("Data:");
        Styles.defaultLabel(lData, false);
        
        fStatus = new JComboBox();
        fStatus.setModel(new DefaultComboBoxModel(Environment.STATUS.toArray()));
        Styles.defaultComboBox(fStatus);
        
        lStatus = new JLabel("Status:");
        Styles.defaultLabel(lStatus, false);
        
        bSearch = new JButton("");
        Styles.searchButton(bSearch);
        
        btnAddPedido = new JButton("Requisitar Produto");
        Styles.defaultButton(btnAddPedido);
        btnAddPedido.setPreferredSize(new Dimension(200, 35));
        
        pFilter.add(lData);
        pFilter.add(fData);
        pFilter.add(lStatus);
        pFilter.add(fStatus);
        pFilter.add(bSearch);
        pFilter.add(btnAddPedido);
        

        // click do buscar
        bSearch.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Dialogs.showLoadPopup(self);
                timerTest();
                pagination(3);
            }
        });
        
        // click do add pedido
        btnAddPedido.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // 
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
                for(int i = 0; i < 8; i++) {
                    Usuario u = new Usuario("111111-22", "Nome Usuario", "email@email.com", "99999-9999", "2222-2222", "Contabilidade", "M", "admin", "12/12/1989");
                    Pedido p = new Pedido("10/11/2019", "Pendente", u);
                    String btnValue = "Finalizar";
                    if ( i % 2 == 0 ) {
                        btnValue = "";
                    } 
                    Object[] data = {p.getCodigo(), p.getSolicitante().getNome(),p.getData(),p.getStatus(),"Ver", btnValue};
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
