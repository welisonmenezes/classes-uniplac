/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.Relatorios;

import Templates.ComboItem;
import Templates.SuggestionsBox;
import Utils.Dialogs;
import Utils.Styles;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 *
 * @author welis
 */
public class RelatorioPedidos extends javax.swing.JPanel {
    private JLabel lusuario;
    private JPanel susuario;
    private JTextField fusuario;
    private JComboBox cusuario;
    private JLabel lproduto;
    private JPanel sproduto;
    private JTextField fproduto;
    private JComboBox cproduto;
    private JLabel ldatafrom;
    private JDateChooser fdatafrom;
    private JLabel edatafrom;
    private JLabel ldatato;
    private JDateChooser fdatato;
    private JLabel edatato;
    private JButton btnRelatorioPedido;
    private JPanel self;
    private JLabel title;

    /**
     * Creates new form RelatorioPedidos
     */
    public RelatorioPedidos() {
        initComponents();
        this.self = this;
        addCamposPedidos();
        
    }
    
    private void addCamposPedidos() {
        setBackground(new Color(27, 28, 29));
        setLayout(new AbsoluteLayout());
        
        title = new JLabel();
        title.setFont(new java.awt.Font("Tahoma", 1, 24));
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("Relatório de Pedidos");
        add(title, new AbsoluteConstraints(20, 20, -1, -1));
        
        lusuario = new JLabel("Usuário");
        Styles.defaultLabel(lusuario);
        add(lusuario, new AbsoluteConstraints(20, 60, -1, -1));
        
        // suggestion box
        susuario = new JPanel();
        fusuario = new JTextField();
        cusuario = new JComboBox();
        new SuggestionsBox(susuario, fusuario, cusuario, 300) {
            public ArrayList<ComboItem> addElements() {
                ArrayList<ComboItem> elements = new ArrayList<ComboItem>();
                for (int i = 1; i <= 25; i++) {
                    // TODO: implements real database results
                    elements.add(new ComboItem(i, "Nome_"+i));
                }
                return elements;
            }
        };
        add(susuario, new AbsoluteConstraints(20, 90, -1, -1));
        
        
        lproduto = new JLabel("Produto");
        Styles.defaultLabel(lproduto);
        add(lproduto, new AbsoluteConstraints(340, 60, -1, -1));
        
        // suggestion box
        sproduto = new JPanel();
        fproduto = new JTextField();
        cproduto = new JComboBox();
        new SuggestionsBox(sproduto, fproduto, cproduto, 300) {
            public ArrayList<ComboItem> addElements() {
                ArrayList<ComboItem> elements = new ArrayList<ComboItem>();
                for (int i = 1; i <= 25; i++) {
                    // TODO: implements real database results
                    elements.add(new ComboItem(i, "Nome_"+i));
                }
                return elements;
            }
        };
        add(sproduto, new AbsoluteConstraints(340, 90, -1, -1));
        
        
        ldatafrom = new JLabel("De*");
        Styles.defaultLabel(ldatafrom);
        add(ldatafrom, new AbsoluteConstraints(20, 140, -1, -1));
        
        fdatafrom = new JDateChooser();
        Styles.defaultDateChooser(fdatafrom, 300);
        add(fdatafrom, new AbsoluteConstraints(20, 170, -1, -1));
        
        edatafrom = new JLabel("");
        Styles.errorLabel(edatafrom);
        add(edatafrom, new AbsoluteConstraints(20, 210, -1, -1));
        
        ldatato = new JLabel("Até*");
        Styles.defaultLabel(ldatato);
        add(ldatato, new AbsoluteConstraints(340, 140, -1, -1));
        
        fdatato = new JDateChooser();
        Styles.defaultDateChooser(fdatato, 300);
        add(fdatato, new AbsoluteConstraints(340, 170, -1, -1));
        
        edatato = new JLabel("");
        Styles.errorLabel(edatato);
        add(edatato, new AbsoluteConstraints(340, 210, -1, -1));
        
        
        btnRelatorioPedido = new JButton("Gerar Relatório");
        Styles.defaultButton(btnRelatorioPedido, 300);
        add(btnRelatorioPedido, new AbsoluteConstraints(340, 250, -1, -1));
        btnRelatorioPedido.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    
                Dialogs.showLoadPopup(self);
                timerTest();
                
                
            }
        });
    }
    
    private Timer t;
    private void timerTest() {
        
        t = new Timer(2000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dialogs.hideLoadPopup(self);
                JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso!");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
