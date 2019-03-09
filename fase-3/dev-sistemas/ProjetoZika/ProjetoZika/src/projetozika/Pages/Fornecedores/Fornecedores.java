/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.Fornecedores;

import Models.BaseLayout;
import Utils.UtilsElements;
import Utils.UtilsStyles;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

/**
 *
 * @author Welison
 */
public class Fornecedores extends Models.BaseLayout {
    
    JButton addMore;
    JButton next;
    JButton prev;
    BaseLayout self;
    JTable tabela;
    JTextField fFilter;
    JLabel lSearch;
    JButton bSearch;

    /**
     * Creates new form NewJPanel
     */
    public Fornecedores() {
        super();
        self = this;
        initComponents();
        createBaseLayout();
        addTopContent("Fornecedores");
        addCenterContent();
        addBottomContent();
        addFilterContent();
    }
    
    public void addCenterContent() {
        String [] colunas = {"Código", "Nome", "CNPJ", "Telefone", "Editar", "Excluir", "Ver"};
        Object [][] dados = {
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
            {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"}
        };
        tabela = new JTable();
        tabela.setRowHeight(35);
        tabela.setModel(new DefaultTableModel(dados, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
        });
        //tabela.setEnabled(false);
        
        JScrollPane barraRolagem = new JScrollPane(tabela);
        barraRolagem.setOpaque(false);
        barraRolagem.getViewport().setOpaque(false);
        barraRolagem.setBorder(null);
        barraRolagem.setViewportBorder(null);
        pCenter.add(barraRolagem, BorderLayout.CENTER);
    }
    
    public void addFilterContent() {
        addMore = new JButton("Criar Novo");
        UtilsStyles.defaultButton(addMore);
        
        fFilter = new JTextField();
        UtilsStyles.defaultField(fFilter);
        fFilter.setPreferredSize( new Dimension( 150, 39 ) );
        
        lSearch = new JLabel("Buscar");
        UtilsStyles.defaultLabel(lSearch);
        lSearch.setPreferredSize( new Dimension( 45, 39 ) );
        
        bSearch = new JButton("");
        UtilsStyles.searchButton(bSearch);
        
        JLabel hideL = new JLabel();
        hideL.setPreferredSize(new Dimension(50, 35));
        
        pFilter.add(lSearch);
        pFilter.add(fFilter);
        pFilter.add(bSearch);
        pFilter.add(hideL);
        pFilter.add(addMore);
        
        addMore.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                UtilsElements.updateLayout("addFornecedor");
            }
        });
        
        bSearch.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                UtilsElements.showLoadPopup(self);
                timerTest();
            }
        });
    }
    
    public void addBottomContent() {
        next = new JButton("Next");
        prev = new JButton("Previous");
        UtilsStyles.defaultButton(next);
        UtilsStyles.defaultButton(prev);
        pBottom.add(prev);
        pBottom.add(next);
        
        prev.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                UtilsElements.showLoadPopup(self);
                timerTest();
            }
        });
        
        next.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                UtilsElements.showLoadPopup(self);
                
                timerTest();
            }
        });
    }
    
    private Timer t;
    private void timerTest() {
        
        t = new Timer(2000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UtilsElements.hideLoadPopup(self);
                
                String [] colunas = {"Código", "Nome", "CNPJ", "Telefone", "Editar", "Excluir", "Ver"};
                Object [][] dados = {
                    {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
                    {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
                    {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
                    {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
                    {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
                    {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"},
                    {"2134", "Nome Fornecedor", "2333230-3", "(99) 99999-9999", "Editar", "Excluir", "Ver"}
                };
                tabela.setModel(new DefaultTableModel(dados, colunas) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                       return false;
                    }
                });
                
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

        setBackground(new java.awt.Color(37, 38, 39));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(50, 25, 50, 25));
        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
