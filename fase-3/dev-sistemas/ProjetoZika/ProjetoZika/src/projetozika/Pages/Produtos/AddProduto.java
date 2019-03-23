/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.Produtos;

import Config.Environment;
import Models.Produto;
import Utils.Dialogs;
import Utils.Methods;
import Utils.Navigation;
import Utils.Styles;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.Properties;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 *
 * @author Welison
 */
public class AddProduto extends Templates.BaseFrame {

    private JPanel bg;
    private JTextField fnome;
    private JLabel lnome;
    private JLabel enome;
    private JComboBox<String> funidade;
    private JLabel lunidade;
    private JLabel eunidade;
    private JTextArea fdescricao;
    private JLabel ldescricao;
    private JLabel edescricao;
    private JButton bSave;
    
   
    public AddProduto(Properties params) {
        this.self = this;
        this.mode = "add";
        this.params = params;
        initPage(Methods.getTranslation("AdicionarProduto"));
    }
    
    public AddProduto(JPanel panelCaller, Properties params) {
        this.self = this;
        this.params = params;
        this.mode = "nota";
        
        initPage(Methods.getTranslation("AdicionarProdutoPelaNota"));
        
    }
    
    public AddProduto(String id, String mode, Properties params) {
        this.self = this;
        this.mode = mode;
        this.params = params;
        
        switch (this.mode) {
            case "view":
                initPage(Methods.getTranslation("VerProduto"));
                Methods.disabledFields(bg);
                break;
            case "edit":
                initPage(Methods.getTranslation("EditarProduto"));
                break;
        }
        
        fillFields(id);
    }
    
    private void initPage(String title) {
        
        initComponents();
        Styles.internalFrame(this, 450, 400);
        Methods.setAccessibility(this);
        
        createBaseLayout();
        addTopContent(title);
        
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                Navigation.currentPage = "produtos";
            }
        });
        
        addCenterContent();
    }
    
    private void fillFields(String id) {
        Produto p = new Produto(Integer.parseInt(id), "Nome produto", "Unidade produto", "Descrição produto", "22/10/2019");
        fnome.setText(p.getNome());
        funidade.setSelectedItem("Unidade");
        fdescricao.setText(p.getDescricao());
    }
    
    private void addCenterContent() {
        bg = new JPanel();
        bg.setLayout(new AbsoluteLayout());
        bg.setOpaque(false);

        lnome = new JLabel(Methods.getTranslation("Nome"));
        Styles.defaultLabel(lnome);
        bg.add(lnome, new AbsoluteConstraints(0, 0, -1, -1));

        fnome = new JTextField();
        Styles.defaultField(fnome);
        bg.add(fnome, new AbsoluteConstraints(0, 40, -1, -1));
        
        enome = new JLabel("");
        Styles.errorLabel(enome);
        bg.add(enome, new AbsoluteConstraints(0, 75, -1, -1));
        
        lunidade = new JLabel(Methods.getTranslation("Unidade"));
        Styles.defaultLabel(lunidade);
        bg.add(lunidade, new AbsoluteConstraints(220, 0, -1, -1));

        funidade = new JComboBox();
        funidade.setModel(new DefaultComboBoxModel(Environment.UNIDADES));
        Styles.defaultComboBox(funidade, 200, 39);
        bg.add(funidade, new AbsoluteConstraints(220, 40, -1, -1));
        
        eunidade = new JLabel("");
        Styles.errorLabel(eunidade);
        bg.add(eunidade, new AbsoluteConstraints(220, 75, -1, -1));
        
        ldescricao = new JLabel(Methods.getTranslation("Descricao"));
        Styles.defaultLabel(ldescricao);
        bg.add(ldescricao, new AbsoluteConstraints(0, 90, -1, -1));

        fdescricao = new JTextArea();
        JScrollPane sp = new JScrollPane();
        Styles.defaultTextArea(fdescricao, sp);
        bg.add(sp, new AbsoluteConstraints(0, 130, -1, -1));
        
        edescricao = new JLabel("");
        Styles.errorLabel(edescricao);
        bg.add(edescricao, new AbsoluteConstraints(0, 205, -1, -1));
        
        bSave = new JButton(Methods.getTranslation("Salvar"));
        Styles.defaultButton(bSave);
        bSave.setPreferredSize(new Dimension(196, 34));
        bg.add(bSave, new AbsoluteConstraints(220, 132, -1, -1));
        
        bSave.addActionListener((ActionEvent e) -> {
            if(fnome.getText().equals("") || funidade.getSelectedItem().equals("") || fdescricao.getText().equals("")){
                if(fnome.getText().equals("")){
                    enome.setText(Methods.getTranslation("CampoObrigatorio"));
                }
                if(funidade.getSelectedItem().equals("")) {
                    eunidade.setText(Methods.getTranslation("CampoObrigatorio"));
                }
                if(fdescricao.getText().equals("")) {
                    edescricao.setText(Methods.getTranslation("CampoObrigatorio"));
                }
            } else {
                Dialogs.showLoadPopup(bg);
                timerTest();
            }
        });
        
        pCenter.add(bg);
    }
    
    private Timer t;
    private void timerTest() {
        
        t = new Timer(2000, (ActionEvent e) -> {
            Dialogs.hideLoadPopup(bg);
            
            switch (mode) {
                case "edit":
                    self.dispose();
                    JOptionPane.showMessageDialog(null, Methods.getTranslation("EditadoComSucesso"));
                    Navigation.updateLayout("", new Properties());
                    Navigation.updateLayout("produtos", params);
                    break;
                case "add":
                    self.dispose();
                    JOptionPane.showMessageDialog(null, Methods.getTranslation("AdicionadoComSucesso"));
                    Navigation.updateLayout("", new Properties());
                    Navigation.updateLayout("produtos", params);
                    break;
                case "nota":
                    // TODO
                    self.dispose();
                    Navigation.updateLayout("", new Properties());
                    Navigation.updateLayout("notasFiscais", params);
                    break;
                default:
                    self.dispose();
                    Navigation.updateLayout("", new Properties());
                    Navigation.updateLayout("produtos", params);
                    break;
            }
            
            
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(37, 38, 39));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
