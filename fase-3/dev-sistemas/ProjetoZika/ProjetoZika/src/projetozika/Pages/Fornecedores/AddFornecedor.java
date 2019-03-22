/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.Fornecedores;

import Models.Fornecedor;
import Utils.Dialogs;
import Utils.Methods;
import Utils.Navigation;
import Utils.Styles;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import projetozika.Pages.NotasFiscais.AddNotaFiscal;

/**
 *
 * @author Welison
 */
public class AddFornecedor extends Templates.BaseFrame {
    private JPanel bg;
    private JTextField fname;
    private JLabel lname;
    private JLabel ename;
    private JTextField ftel;
    private JLabel ltel;
    private JLabel etel;
    private JTextField fcnpj;
    private JLabel lcnpj;
    private JLabel ecnpj;
    private JButton bSave;
    private JPanel panelCaller;
    
   
    public AddFornecedor(Properties params) {
        this.self = this;
        this.mode = "add";
        this.params = params;
        initPage(Methods.getTranslation("AdicionarFornecedor"));
    }
    
    public AddFornecedor(JPanel panelCaller, Properties params) {
        this.self = this;
        this.mode = "nota";
        this.params = params;
        this.panelCaller = panelCaller;
        initPage(Methods.getTranslation("AdicionarFornecedorPelaNota"));
    }
    
    public AddFornecedor(String id, String mode, Properties params) {
        this.self = this;
        this.mode = mode;
        this.params = params;
        
        switch (this.mode) {
            case "view":
                initPage(Methods.getTranslation("VerFornecedor"));
                Methods.disabledFields(bg);
                break;
            case "edit":
                initPage(Methods.getTranslation("EditarFornecedor"));
                break;
        }
        
        fillFields(id);
    }
    
    private void initPage(String title) {
        
        initComponents();
        Styles.internalFrame(this);
        Methods.setAccessibility(this);
        
        createBaseLayout();
        addTopContent(title);
        
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                Navigation.currentPage = "fornecedores";
            }
        });
        
        addCenterContent();
    }
    
    private void fillFields(String id) {
        Fornecedor f = new Fornecedor(111, "Nome Here", "34343354-3", "(99) 99999-9999", "12/12/2009");
        fname.setText(f.getNome());
        ftel.setText(f.getTelefone());
        fcnpj.setText(f.getCnpj());
    }
    
    private void addCenterContent() {
        bg = new JPanel();
        bg.setLayout(new AbsoluteLayout());
        bg.setOpaque(false);

        
        lname = new JLabel(Methods.getTranslation("Nome"));
        Styles.defaultLabel(lname);
        bg.add(lname, new AbsoluteConstraints(0, 0, -1, -1));

        fname = new JTextField();
        Styles.defaultField(fname);
        bg.add(fname, new AbsoluteConstraints(0, 40, -1, -1));
        
        ename = new JLabel("");
        Styles.errorLabel(ename);
        bg.add(ename, new AbsoluteConstraints(0, 75, -1, -1));
        
        ltel = new JLabel(Methods.getTranslation("Telefone"));
        Styles.defaultLabel(ltel);
        bg.add(ltel, new AbsoluteConstraints(220, 0, -1, -1));

        ftel = new JTextField();
        Styles.defaultField(ftel);
        bg.add(ftel, new AbsoluteConstraints(220, 40, -1, -1));
        
        etel = new JLabel("");
        Styles.errorLabel(etel);
        bg.add(etel, new AbsoluteConstraints(220, 75, -1, -1));
        
        lcnpj = new JLabel(Methods.getTranslation("CNPJ"));
        Styles.defaultLabel(lcnpj);
        bg.add(lcnpj, new AbsoluteConstraints(0, 90, -1, -1));

        fcnpj = new JTextField();
        Styles.defaultField(fcnpj);
        bg.add(fcnpj, new AbsoluteConstraints(0, 130, -1, -1));
        
        ecnpj = new JLabel("");
        Styles.errorLabel(ecnpj);
        bg.add(ecnpj, new AbsoluteConstraints(0, 165, -1, -1));
        
        bSave = new JButton(Methods.getTranslation("Salvar"));
        Styles.defaultButton(bSave);
        bSave.setPreferredSize(new Dimension(196, 34));
        bg.add(bSave, new AbsoluteConstraints(220, 132, -1, -1));
        
        bSave.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    
                if(fname.getText().equals("") || ftel.getText().equals("") || fcnpj.getText().equals("")){
                    if(fname.getText().equals("")){
                        ename.setText(Methods.getTranslation("CampoObrigatorio"));
                    }
                    if(ftel.getText().equals("")) {
                        etel.setText(Methods.getTranslation("CampoObrigatorio"));
                    }
                    if(fcnpj.getText().equals("")) {
                        ecnpj.setText(Methods.getTranslation("CampoObrigatorio"));
                    }
                } else {
                    Dialogs.showLoadPopup(bg);
                    timerTest();
                }
                
                
            }
        });
        
        pCenter.add(bg);
    }
    
    private Timer t;
    private void timerTest() {
        
        t = new Timer(2000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dialogs.hideLoadPopup(bg);
                 
                if (mode.equals("edit")) {

                    self.dispose();
                    JOptionPane.showMessageDialog(null, Methods.getTranslation("EditadoComSucesso"));
                    
                    Navigation.updateLayout("", new Properties());
                    Navigation.updateLayout("fornecedores", params);

                } else if(mode.equals("add")) {

                    self.dispose();
                    JOptionPane.showMessageDialog(null, Methods.getTranslation("AdicionadoComSucesso"));
                    
                    Navigation.updateLayout("", new Properties());
                    Navigation.updateLayout("fornecedores", params);
                    
                } else if(mode.equals("nota")){
                    AddNotaFiscal.fcnpj.setText(fcnpj.getText());
                    self.dispose();
                    
                    Navigation.updateLayout("", new Properties());
                    Navigation.updateLayout("notas", params);
                    
                } else {
                    self.dispose();
                    
                    Navigation.updateLayout("", new Properties());
                    Navigation.updateLayout("fornecedores", params);
                }
                
                
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(37, 38, 39));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
