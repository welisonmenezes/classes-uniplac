/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.Relatorios;

import Templates.BaseLayout;
import Templates.ComboItem;
import Templates.SuggestionsBox;
import Utils.Dialogs;
import Utils.Styles;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
/**
 * Tela de listagem do fornecedores
 * 
 * @author Welison
 */
public class Relatorios extends Templates.BaseLayout {
    
    private BaseLayout self;
    public static JTable tabela;
    public static DefaultTableModel tableModel;
    private JTabbedPane jTabbedPane1;
    private JPanel panelPedidos;
    private JPanel panelProdutos;
    private JTextField fproduto;
    private JComboBox cproduto;
    private JLabel lproduto;
    private JPanel sproduto;
    private JLabel ldatafrom;
    private JDateChooser fdatafrom;
    private JLabel ldatato;
    private JDateChooser fdatato;
    private JLabel lusuario;
    private JTextField fusuario;
    private JPanel susuario;
    private JComboBox cusuario;
    private JButton btnRelatorioPedido;
    private JLabel edatafrom;
    private JLabel edatato;

    /**
     * Cria a tela de fornecedores
     */
    public Relatorios() {
        super();
        self = this;
        initComponents();
        createBaseLayout();
        addTopContent("Relatórios");
        addCenterContent();
    }
    
    // Adiciona conteúdo ao centro da area de conteúdo
    public void addCenterContent() {
        jTabbedPane1 = new JTabbedPane();
        jTabbedPane1.setOpaque(false);
        
        panelPedidos = new JPanel();
        panelPedidos.setBackground(new Color(27, 28, 29));
        panelProdutos = new JPanel();
        panelProdutos.setBackground(new Color(27, 28, 29));
        
        jTabbedPane1.addTab("Pedidos", panelPedidos);
        jTabbedPane1.addTab("Produtos", panelProdutos);
        
        addCamposPedidos();
        
        pCenter.add(jTabbedPane1, BorderLayout.CENTER);
    }
    
    private void addCamposPedidos() {
        panelPedidos.setLayout(new AbsoluteLayout());
        
        lusuario = new JLabel("Usuário");
        Styles.defaultLabel(lusuario);
        panelPedidos.add(lusuario, new AbsoluteConstraints(20, 20, -1, -1));
        
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
        panelPedidos.add(susuario, new AbsoluteConstraints(20, 50, -1, -1));
        
        
        lproduto = new JLabel("Produto");
        Styles.defaultLabel(lproduto);
        panelPedidos.add(lproduto, new AbsoluteConstraints(340, 20, -1, -1));
        
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
        panelPedidos.add(sproduto, new AbsoluteConstraints(340, 50, -1, -1));
        
        
        ldatafrom = new JLabel("De*");
        Styles.defaultLabel(ldatafrom);
        panelPedidos.add(ldatafrom, new AbsoluteConstraints(20, 90, -1, -1));
        
        fdatafrom = new JDateChooser();
        Styles.defaultDateChooser(fdatafrom, 300);
        panelPedidos.add(fdatafrom, new AbsoluteConstraints(20, 120, -1, -1));
        
        edatafrom = new JLabel("");
        Styles.errorLabel(edatafrom);
        panelPedidos.add(edatafrom, new AbsoluteConstraints(20, 160, -1, -1));
        
        ldatato = new JLabel("Até*");
        Styles.defaultLabel(ldatato);
        panelPedidos.add(ldatato, new AbsoluteConstraints(340, 90, -1, -1));
        
        fdatato = new JDateChooser();
        Styles.defaultDateChooser(fdatato, 300);
        panelPedidos.add(fdatato, new AbsoluteConstraints(340, 120, -1, -1));
        
        edatato = new JLabel("");
        Styles.errorLabel(edatato);
        panelPedidos.add(edatato, new AbsoluteConstraints(340, 160, -1, -1));
        
        
        btnRelatorioPedido = new JButton("Gerar Relatório");
        Styles.defaultButton(btnRelatorioPedido, 300);
        panelPedidos.add(btnRelatorioPedido, new AbsoluteConstraints(340, 200, -1, -1));
        btnRelatorioPedido.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    
                Dialogs.showLoadPopup(pCenter);
                timerTest();
                
                
            }
        });
    }
    
    
    private Timer t;
    private void timerTest() {
        
        t = new Timer(2000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dialogs.hideLoadPopup(pCenter);
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

        setBorder(javax.swing.BorderFactory.createEmptyBorder(50, 25, 50, 25));
        setMinimumSize(new java.awt.Dimension(1, 1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
