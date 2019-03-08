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
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

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
        String [] colunas = {"Nome", "Telefone", "Email"};
        Object [][] dados = {
            {"Ana Monteiro", "48 9923-7898", "ana.monteiro@gmail.com"},
            {"João da Silva", "48 8890-3345", "joaosilva@hotmail.com"},
            {"Pedro Cascaes", "48 9870-5634", "pedrinho@gmail.com"},
            {"Ana Monteiro", "48 9923-7898", "ana.monteiro@gmail.com"},
            {"João da Silva", "48 8890-3345", "joaosilva@hotmail.com"},
            {"Pedro Cascaes", "48 9870-5634", "pedrinho@gmail.com"},
            {"Ana Monteiro", "48 9923-7898", "ana.monteiro@gmail.com"},
            {"João da Silva", "48 8890-3345", "joaosilva@hotmail.com"},
            {"Pedro Cascaes", "48 9870-5634", "pedrinho@gmail.com"},
            {"Ana Monteiro", "48 9923-7898", "ana.monteiro@gmail.com"},
            {"João da Silva", "48 8890-3345", "joaosilva@hotmail.com"},
            {"Pedro Cascaes", "48 9870-5634", "pedrinho@gmail.com"},
            {"Ana Monteiro", "48 9923-7898", "ana.monteiro@gmail.com"},
            {"João da Silva", "48 8890-3345", "joaosilva@hotmail.com"},
            {"Pedro Cascaes", "48 9870-5634", "pedrinho@gmail.com"},
            {"Ana Monteiro", "48 9923-7898", "ana.monteiro@gmail.com"},
            {"João da Silva", "48 8890-3345", "joaosilva@hotmail.com"},
            {"Pedro Cascaes", "48 9870-5634", "pedrinho@gmail.com"},
            {"Ana Monteiro", "48 9923-7898", "ana.monteiro@gmail.com"},
            {"João da Silva", "48 8890-3345", "joaosilva@hotmail.com"},
            {"Pedro Cascaes", "48 9870-5634", "pedrinho@gmail.com"},
            {"Ana Monteiro", "48 9923-7898", "ana.monteiro@gmail.com"},
            {"João da Silva", "48 8890-3345", "joaosilva@hotmail.com"},
            {"Pedro Cascaes", "48 9870-5634", "pedrinho@gmail.com"},
            {"Ana Monteiro", "48 9923-7898", "ana.monteiro@gmail.com"},
                
            {"Ana Monteiro", "48 9923-7898", "ana.monteiro@gmail.com"},
            {"João da Silva", "48 8890-3345", "joaosilva@hotmail.com"},
            {"Pedro Cascaes", "48 9870-5634", "pedrinho@gmail.com"},
            {"Ana Monteiro", "48 9923-7898", "ana.monteiro@gmail.com"},
            {"João da Silva", "48 8890-3345", "joaosilva@hotmail.com"},
            {"Pedro Cascaes", "48 9870-5634", "pedrinho@gmail.com"},
            {"Ana Monteiro", "48 9923-7898", "ana.monteiro@gmail.com"},
            {"João da Silva", "48 8890-3345", "joaosilva@hotmail.com"},
            {"Pedro Cascaes", "48 9870-5634", "pedrinho@gmail.com"},
            {"Ana Monteiro", "48 9923-7898", "ana.monteiro@gmail.com"},
            {"João da Silva", "48 8890-3345", "joaosilva@hotmail.com"},
            {"Pedro Cascaes", "48 9870-5634", "pedrinho@gmail.com"},
            {"Ana Monteiro", "48 9923-7898", "ana.monteiro@gmail.com"},
            {"João da Silva", "48 8890-3345", "joaosilva@hotmail.com"},
            {"Pedro Cascaes", "48 9870-5634", "pedrinho@gmail.com"},
            {"Ana Monteiro", "48 9923-7898", "ana.monteiro@gmail.com"},
            {"João da Silva", "48 8890-3345", "joaosilva@hotmail.com"},
            {"Pedro Cascaes", "48 9870-5634", "pedrinho@gmail.com"},
            {"Ana Monteiro", "48 9923-7898", "ana.monteiro@gmail.com"},
            {"João da Silva", "48 8890-3345", "joaosilva@hotmail.com"},
            {"Pedro Cascaes", "48 9870-5634", "pedrinho@gmail.com"},
            {"Ana Monteiro", "48 9923-7898", "ana.monteiro@gmail.com"},
            {"João da Silva", "48 8890-3345", "joaosilva@hotmail.com"},
            {"Pedro Cascaes", "48 9870-5634", "pedrinho@gmail.com"},
            {"Ana Monteiro", "48 9923-7898", "ana.monteiro@gmail.com"}
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
        
        pFilter.add(fFilter);
        pFilter.add(addMore);
        
        addMore.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                UtilsElements.updateLayout("addFornecedor");
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
                
                String [] colunas = {"Nome", "Telefone", "Email"};
                Object [][] dados = {
                    {"Fulano de Tal", "48 9923-7898", "fulano.tal@gmail.com"},
                    {"Fulano de Tal", "48 9923-7898", "fulano.tal@gmail.com"},
                    {"Fulano de Tal", "48 9923-7898", "fulano.tal@gmail.com"},
                    {"Fulano de Tal", "48 9923-7898", "fulano.tal@gmail.com"},
                    {"Fulano de Tal", "48 9923-7898", "fulano.tal@gmail.com"}
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
