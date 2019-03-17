/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.NotasFiscais;

import Utils.Dialogs;
import Utils.Navigation;
import Utils.Styles;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

/**
 *
 * @author Welison
 */
public class SelecionarProduto extends javax.swing.JPanel {
    
    private JPanel self;
    private JLabel lnome;
    private JTextField fnome;
    private JScrollPane scrollList;
    private JLabel addProduto;
    private JLabel enome;
    private JLabel lunidade;
    private JTextField funidade;
    private JLabel eunidade;
    private JLabel lquantidade;
    private JTextField fquantidade;
    private JLabel equantidade;
    private JLabel lvalor;
    private JTextField fvalor;
    private JLabel evalor;
    private JButton selProd;

    /**
     * Creates new form SelecionarProduto
     */
    public SelecionarProduto() {
        initComponents();
        self = this;
        Styles.setBorderTitle(this, "Adicionar/Selecionar Produto");
        
        addElements();
    }
    
    private void addElements() {
        lnome = new JLabel("Nome");
        Styles.defaultLabel(lnome);
        add(lnome, new AbsoluteConstraints(20, 20, -1, -1));
        
        fnome = new JTextField();
        Styles.defaultField(fnome);
        add(fnome, new AbsoluteConstraints(20, 50, -1, -1));
        
        scrollList = new JScrollPane();
        JList lSugestoes = new JList<>();
        String[] strings = {};
        Styles.defaultSuggestions(scrollList, lSugestoes, strings);
        scrollList.setPreferredSize( new Dimension( 200, 150 ) );
        add(scrollList, new AbsoluteConstraints(20, 85, -1, -1));
        
        fnome.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String[] strings = {"Nome Produto 1","Nome Produto 2","Nome Produto 3","Nome Produto 4","Nome Produto 5","Nome Produto 6","Nome Produto 7","Nome Produto 8","Nome Produto 9"};
                lSugestoes.setListData(strings);
                scrollList.setVisible(true);
            }
        });
        
        lSugestoes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //fnome.setText(lSugestoes.getSelectedValue());
                scrollList.setVisible(false);
            }
        });
        
        lSugestoes.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {}

            @Override
            public void focusLost(FocusEvent e) {
                scrollList.setVisible(false);
            }
        });
        
        addProduto = new JLabel("<html><u>Novo Produto</u></html>");
        Styles.defaultLabel(addProduto);
        addProduto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(addProduto, new AbsoluteConstraints(230, 45, -1, -1));
        
        addProduto.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (!addProduto.isEnabled()) return;
                Navigation.updateLayout("addProdutoNota");
            }
        });
        
        enome = new JLabel("");
        Styles.errorLabel(enome);
        add(enome, new AbsoluteConstraints(20, 85, -1, -1));
        
        lunidade = new JLabel("Unidade");
        Styles.defaultLabel(lunidade);
        add(lunidade, new AbsoluteConstraints(20, 100, -1, -1));
        
        funidade = new JTextField();
        Styles.defaultField(funidade);
        funidade.setEnabled(false);
        add(funidade, new AbsoluteConstraints(20, 130, -1, -1));
        
        eunidade = new JLabel("");
        Styles.errorLabel(eunidade);
        add(eunidade, new AbsoluteConstraints(20, 165, -1, -1));
        
        lquantidade = new JLabel("Quantidade");
        Styles.defaultLabel(lquantidade);
        add(lquantidade, new AbsoluteConstraints(20, 180, -1, -1));
        
        fquantidade = new JTextField();
        Styles.defaultField(fquantidade);
        add(fquantidade, new AbsoluteConstraints(20, 210, -1, -1));
        
        equantidade = new JLabel("");
        Styles.errorLabel(equantidade);
        add(equantidade, new AbsoluteConstraints(20, 245, -1, -1));
        
        lvalor = new JLabel("Quantidade");
        Styles.defaultLabel(lvalor);
        add(lvalor, new AbsoluteConstraints(20, 260, -1, -1));
        
        fvalor = new JTextField();
        Styles.defaultField(fvalor);
        add(fvalor, new AbsoluteConstraints(20, 290, -1, -1));
        
        evalor = new JLabel("");
        Styles.errorLabel(evalor);
        add(evalor, new AbsoluteConstraints(20, 325, -1, -1));
        
        selProd = new JButton("Adicionar");
        Styles.defaultButton(selProd);
        selProd.setPreferredSize(new Dimension(100, 34));
        add(selProd, new AbsoluteConstraints(240, 293, -1, -1));
        selProd.addActionListener(new ActionListener(){
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

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
