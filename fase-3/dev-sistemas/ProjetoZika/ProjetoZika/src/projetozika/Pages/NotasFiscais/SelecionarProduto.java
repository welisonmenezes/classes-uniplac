/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.NotasFiscais;

import Models.Produto;
import Templates.ComboItem;
import Templates.SuggestionsBox;
import Utils.Dialogs;
import Utils.Methods;
import Utils.Navigation;
import Utils.Styles;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
    private JPanel pSuggestions;
    private JComboBox cnome;
    private Properties params;
    private final AddNotaFiscal caller;

    /**
     * Creates new form SelecionarProduto
     * @param caller A tela que invocou esse panal
     */
    public SelecionarProduto(JFrame caller) {
        initComponents();
        this.self = this;
        this.caller = (AddNotaFiscal)caller;
        
        Styles.setBorderTitle(this, Methods.getTranslation("Adicionar/SelecionarProduto"));
        
        addElements();
    }
    
    private void addElements() {
        lnome = new JLabel(Methods.getTranslation("Nome"));
        Styles.defaultLabel(lnome);
        add(lnome, new AbsoluteConstraints(20, 20, -1, -1));
        
        // suggestion box
        pSuggestions = new JPanel();
        fnome = new JTextField();
        cnome = new JComboBox();
        new SuggestionsBox(pSuggestions, fnome, cnome, 200) {
            public ArrayList<ComboItem> addElements() {
                ArrayList<ComboItem> elements = new ArrayList<>();
                for (int i = 1; i <= 25; i++) {
                    // TODO: implements real database results
                    elements.add(new ComboItem(i, "Nome_"+i));
                }
                return elements;
            }
        };
        add(pSuggestions, new AbsoluteConstraints(20, 50, -1, -1));
        
        addProduto = new JLabel("<html><u>"+ Methods.getTranslation("AdicionarNovo") +"</u></html>");
        Styles.defaultLabel(addProduto);
        addProduto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(addProduto, new AbsoluteConstraints(230, 45, -1, -1));
        
        addProduto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!addProduto.isEnabled()) return;
                Navigation.updateLayout("addProdutoNota", params);
            }
        });
        
        enome = new JLabel("");
        Styles.errorLabel(enome);
        add(enome, new AbsoluteConstraints(20, 85, -1, -1));
        
        lunidade = new JLabel(Methods.getTranslation("Unidade"));
        Styles.defaultLabel(lunidade);
        add(lunidade, new AbsoluteConstraints(20, 100, -1, -1));
        
        funidade = new JTextField();
        Styles.defaultField(funidade);
        funidade.setEnabled(false);
        add(funidade, new AbsoluteConstraints(20, 130, -1, -1));
        
        eunidade = new JLabel("");
        Styles.errorLabel(eunidade);
        add(eunidade, new AbsoluteConstraints(20, 165, -1, -1));
        
        lquantidade = new JLabel(Methods.getTranslation("Quantidade"));
        Styles.defaultLabel(lquantidade);
        add(lquantidade, new AbsoluteConstraints(20, 180, -1, -1));
        
        fquantidade = new JTextField();
        Styles.defaultField(fquantidade);
        add(fquantidade, new AbsoluteConstraints(20, 210, -1, -1));
        
        equantidade = new JLabel("");
        Styles.errorLabel(equantidade);
        add(equantidade, new AbsoluteConstraints(20, 245, -1, -1));
        
        lvalor = new JLabel(Methods.getTranslation("Valor"));
        Styles.defaultLabel(lvalor);
        add(lvalor, new AbsoluteConstraints(20, 260, -1, -1));
        
        fvalor = new JTextField();
        Styles.defaultField(fvalor);
        add(fvalor, new AbsoluteConstraints(20, 290, -1, -1));
        
        evalor = new JLabel("");
        Styles.errorLabel(evalor);
        add(evalor, new AbsoluteConstraints(20, 325, -1, -1));
        
        selProd = new JButton(Methods.getTranslation("Adicionar"));
        Styles.defaultButton(selProd);
        selProd.setPreferredSize(new Dimension(100, 34));
        add(selProd, new AbsoluteConstraints(240, 293, -1, -1));
        selProd.addActionListener((ActionEvent e) -> {
            Dialogs.showLoadPopup(self);
            timerTest();
        });
    }
    
    private Timer t;
    private void timerTest() {
        
        t = new Timer(2000, (ActionEvent e) -> {
            Dialogs.hideLoadPopup(self);
            
            caller.addProduto(new Produto(333,"Prod Teste","Caixa","Desc teste","12/11/2008"));
            
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

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
