/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.Pedidos;

import projetozika.Pages.Fornecedores.*;
import Models.Fornecedor;
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
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
 * Tela de listagem do fornecedores
 * 
 * @author Welison
 */
public class Pedidos extends Templates.BaseLayout {
    
    BaseLayout self;
    public static JTable tabela;
    public static DefaultTableModel tableModel;
    JButton addMore;
    JTextField fCnpj;
    JTextField fNome;
    JTextField fTelefone;
    JLabel lNome;
    JLabel lCnpj;
    JLabel lTelefone;
    JLabel lSearch;
    JButton bSearch;

    /**
     * Cria a tela de fornecedores
     */
    public Pedidos() {
        super();
        self = this;
        initComponents();
        createBaseLayout();
        addTopContent("Pedidos");
        addCenterContent();
        addBottomContent();
        addFilterContent();
    }
    
    // Adiciona conteúdo ao centro da area de conteúdo
    public void addCenterContent() {
        makeTable();
        JScrollPane barraRolagem = new JScrollPane(tabela);
        barraRolagem.setOpaque(false);
        barraRolagem.getViewport().setOpaque(false);
        barraRolagem.setBorder(null);
        barraRolagem.setViewportBorder(null);
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
        String[] colunas = {"Código", "Nome", "CNPJ", "Telefone", "Ver", "Finalizar"};
       // seta modelo
        tableModel = new DefaultTableModel(null, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
               if(column != 4 && column != 5){
                   return false;
               }
               return true;
            }
        };
        // adiciona linhas
        for(int i = 0; i < 25; i++) {
            Usuario u = new Usuario("111111-22", "Nome Usuario", "email@email.com", "99999-9999", "2222-2222", "Contabilidade", "M", "admin", "12/12/1989");
            Pedido p = new Pedido("10/11/2019", "Pendente", u);
            Object[] data = {p.getCodigo(), p.getSolicitante().getNome(),p.getData(),p.getStatus(),"Ver", "-"};
            tableModel.addRow(data);
        }
        // inicializa
        tabela.setModel(tableModel);
        
        tabela.getColumn("Finalizar").setCellRenderer(new ButtonRenderer());
        tabela.getColumn("Finalizar").setCellEditor(new ButtonEditor(new JCheckBox()){
            @Override
            public void buttonAction() {
                String id = Methods.selectedTableItemId(tabela);
                Navigation.updateLayout("finalizarPedido", id);
            }
        });
        
        tabela.getColumn("Ver").setCellRenderer(new ButtonRenderer());
        tabela.getColumn("Ver").setCellEditor(new ButtonEditor(new JCheckBox()){
            @Override
            public void buttonAction() {
                String id = Methods.selectedTableItemId(tabela);
                Navigation.updateLayout("verPedido", id);
            }
        });
    }
    
    /**
     * Adiciona o conteúdo à area de filtro da tela de conteúdo
     */
    public void addFilterContent() {
        
        fNome = new JTextField();
        Styles.defaultField(fNome, 150);
        
        lNome = new JLabel("Nome:");
        Styles.defaultLabel(lNome, false);
        
        fCnpj = new JTextField();
        Styles.defaultField(fCnpj, 150);
        
        lCnpj = new JLabel("CNPJ:");
        Styles.defaultLabel(lCnpj, false);
        
        fTelefone = new JTextField();
        Styles.defaultField(fTelefone, 150);
        
        lTelefone = new JLabel("Telefone:");
        Styles.defaultLabel(lTelefone, false);
        
        bSearch = new JButton("");
        Styles.searchButton(bSearch);
        
        JLabel hideL = new JLabel();
        hideL.setPreferredSize(new Dimension(50, 35));
        
        addMore = new JButton("Criar Novo");
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
        addMore.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Navigation.updateLayout("addPedido");
            }
        });
        
        // click do buscar
        bSearch.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Dialogs.showLoadPopup(self);
                timerTest();
                pagination(3);
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
                
                /*
                // reseta tabela
                tableModel.getDataVector().removeAllElements();
                tableModel.fireTableDataChanged();
                // adiciona novas linhas
                for(int i = 26; i < 35; i++) {
                    Fornecedor f = new Fornecedor(i, "Nome Here", "34343354-3", "(99) 99999-9999", "12/12/2009");
                    Object[] data = {f.getID(),f.getNome(),f.getCnpj(),f.getTelefone(),"Editar","Excluir","Ver"};
                    tableModel.addRow(data);
                }
                */
                
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
