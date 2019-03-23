/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.Pedidos;

import Config.Environment;
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
public class Pedidos extends Templates.BaseLayout {

    private JDateChooser fData;
    private JTextField fNome;
    private JComboBox<String> fStatus;
    private JLabel lNome;
    private JLabel lData;
    private JLabel lStatus;
    private JButton bSearch;
    private ArrayList<Pedido> pedidos;

    /**
     * Cria a tela de fornecedores
     * @param params Parâmetros para filtro e paginação
     */
    public Pedidos(Properties params) {
        super();
        this.self = this;
        this.params = params; 
        initPage();
    }
    
    private void initPage() {
        initComponents();
        createBaseLayout();
        
        pedidos = new ArrayList<>();
        Usuario u = new Usuario("111111-22", "Nome Usuario", "email@email.com", "99999-9999", "2222-2222", "Contabilidade", "M", "admin", "12/12/1989");
        for (int i = 0; i < 15; i++) {
            Pedido p = new Pedido("10/11/2019", "Pendente", u);
            p.setCodigo(i);
            pedidos.add(p);
        }
        
        addTopContent(Methods.getTranslation("Pedidos"));
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
            Methods.getTranslation("Nome"), 
            Methods.getTranslation("Data"), 
            Methods.getTranslation("Status"), 
            Methods.getTranslation("Ver/Editar"), 
            Methods.getTranslation("Entregar")
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
        };
        // adiciona linhas
        pedidos.forEach(p -> {
            String btnValue = Methods.getTranslation("Entregar");
            if ( p.getCodigo() % 2 == 0 ) {
                btnValue = "";
            } 
            Object[] data = {
                p.getCodigo(),
                p.getSolicitante().getNome(),
                p.getData(),
                p.getStatus(),
                Methods.getTranslation("Ver/Editar"), 
                btnValue
            };
            tableModel.addRow(data);
        });
        // inicializa
        tabela.setModel(tableModel);
        
        tabela.getColumn(Methods.getTranslation("Entregar")).setCellRenderer(new ButtonRenderer());
        tabela.getColumn(Methods.getTranslation("Entregar")).setCellEditor(new ButtonEditor(new JCheckBox()){
            @Override
            public void buttonAction() {
                String id = Methods.selectedTableItemId(tabela);
                int col = tabela.getSelectedColumn();
                int row = tabela.getSelectedRow();
                String actionValue = (String)tabela.getModel().getValueAt(row, col);
                if (!actionValue.equals("")) {
                    // TODO : tela de finalizar
                    Navigation.updateLayout("entregarPedido", id, params);
                }
            }
        });
        
        tabela.getColumn(Methods.getTranslation("Ver/Editar")).setCellRenderer(new ButtonRenderer());
        tabela.getColumn(Methods.getTranslation("Ver/Editar")).setCellEditor(new ButtonEditor(new JCheckBox()){
            @Override
            public void buttonAction() {
                String id = Methods.selectedTableItemId(tabela);
                Navigation.updateLayout("editarPedido", id, params);
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
        try {
            Date newDate = new SimpleDateFormat("dd/MM/yyyy").parse(params.getProperty("data", ""));
            fData.setDate(newDate);
        } catch (ParseException ex) {}
        
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
            for (int i = 0; i < pedidos.size(); i++) {
                Pedido p = pedidos.get(i);
                if (p.getCodigo() > 10) {
                    pedidos.remove(p);
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
