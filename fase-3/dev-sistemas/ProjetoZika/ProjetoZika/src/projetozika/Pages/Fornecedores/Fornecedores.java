/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.Fornecedores;

import Utils.UtilsCreator;
import Utils.UtilsElements;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Welison
 */
public class Fornecedores extends javax.swing.JPanel {
    
    JPanel pTop;
    JPanel pCenter;
    JPanel pFilter;
    JPanel pBottom;
    JButton addMore;

    /**
     * Creates new form NewJPanel
     */
    public Fornecedores() {
        initComponents();
        System.out.println("projetozika.Pages.Fornecedores.Fornecedores.<init>()");
        
        pTop = new JPanel();
        pCenter = new JPanel();
        pFilter = new JPanel();
        pBottom = new JPanel();
        UtilsCreator creator = new UtilsCreator();
        creator.createBaseLayout(this , pTop, pCenter, pBottom, pFilter);
        
        addTopContent();
        addCenterContent();
        addFilterContent();
        addBottomContent();
    }
    
    public void addTopContent() {
        JLabel label = new JLabel();
        label.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        label.setForeground(new java.awt.Color(255, 255, 255));
        label.setText("Fornecedores");
        pTop.add(label);
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
        JTable tabela = new JTable();
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
        JTextField fFilter = new JTextField();
        fFilter.setPreferredSize( new Dimension( 150, 30 ) );
        
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
        JButton bNext = new JButton("Next");
        JButton bPrev = new JButton("Previous");
        pBottom.add(bPrev);
        pBottom.add(bNext);
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
