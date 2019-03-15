/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.Usuarios;

import Utils.Dialogs;
import Utils.Methods;
import Utils.Navigation;
import Utils.Styles;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 *
 * @author Welison
 */
public class AddUsuario extends Templates.BaseFrame {
    private final JFrame self;
    private String mode;
    private JPanel bg;
    
    
    private JTextField fnome;
    private JLabel lnome;
    private JLabel enome;
    
    private JTextField fcpf;
    private JLabel lcpf;
    private JLabel ecpf;
    
    private ButtonGroup gsexo;
    private JRadioButton fhomem;
    private JRadioButton fmulher;
    private JLabel lsexo;
    private JLabel esexo;
    
    private JDateChooser fdata;
    private JLabel ldata;
    private JLabel edata;
    
    private JTextField fcelular;
    private JLabel lcelular;
    private JLabel ecelular;
    
    private JTextField ftelefone;
    private JLabel ltelefone;
    private JLabel etelefone;
    
    private JTextField femail;
    private JLabel lemail;
    private JLabel eemail;
    
    private JComboBox<String> fsetor;
    private JLabel lsetor;
    private JLabel esetor;
    
    private JComboBox<String> fpermissao;
    private JLabel lpermissao;
    private JLabel epermissao;
    
    private JButton bSave;
    private JPanel panelCaller;
    
   
    public AddUsuario() {
        this.self = this;
        this.mode = "add";
        initPage("Adicionar Usuario");
    }
    
    public AddUsuario(String id, String mode) {
        this.self = this;
        this.mode = mode;
        if(this.mode.equals("view")){
            initPage("Ver Usuario");
            Methods.disabledFields(bg);
        } else if (this.mode.equals("edit")){
            initPage("Editar Usuario");
        }
        
        fillFields(id);
    }
    
    private void initPage(String title) {
        
        initComponents();
        Styles.internalFrame(this, 670, 500);
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
    
    private void fillFields(String id) {}
    
    public void addCenterContent() {
        bg = new JPanel();
        bg.setLayout(new AbsoluteLayout());
        bg.setOpaque(false);

        lnome = new JLabel("Nome");
        Styles.defaultLabel(lnome);
        bg.add(lnome, new AbsoluteConstraints(0, 0, -1, -1));

        fnome = new JTextField("");
        Styles.defaultField(fnome);
        bg.add(fnome, new AbsoluteConstraints(0, 40, -1, -1));
        
        enome = new JLabel("");
        Styles.errorLabel(enome);
        bg.add(enome, new AbsoluteConstraints(0, 75, -1, -1));
        
        lcpf = new JLabel("CPF");
        Styles.defaultLabel(lcpf);
        bg.add(lcpf, new AbsoluteConstraints(220, 0, -1, -1));

        fcpf = new JTextField();
        Styles.defaultField(fcpf);
        bg.add(fcpf, new AbsoluteConstraints(220, 40, -1, -1));
        
        ecpf = new JLabel("");
        Styles.errorLabel(ecpf);
        bg.add(ecpf, new AbsoluteConstraints(220, 75, -1, -1));
        
        lsexo = new JLabel("Sexo");
        Styles.defaultLabel(lsexo);
        bg.add(lsexo, new AbsoluteConstraints(440, 0, -1, -1));
        
        gsexo = new ButtonGroup();
        fhomem = new JRadioButton("Masculino");
        fmulher = new JRadioButton("Feminino");
        fhomem.setForeground(new Color(255, 255, 255));
        fmulher.setForeground(new Color(255, 255, 255));
        gsexo.add(fhomem);
        gsexo.add(fmulher);
        bg.add(fhomem, new AbsoluteConstraints(440, 50, -1, -1));
        bg.add(fmulher, new AbsoluteConstraints(550, 50, -1, -1));
        
        esexo = new JLabel("");
        Styles.errorLabel(esexo);
        bg.add(esexo, new AbsoluteConstraints(440, 75, -1, -1));
        
        ldata = new JLabel("Data de Nascimento");
        Styles.defaultLabel(ldata);
        bg.add(ldata, new AbsoluteConstraints(0, 90, -1, -1));

        fdata = new JDateChooser();
        Styles.defaultDateChooser(fdata);
        bg.add(fdata, new AbsoluteConstraints(0, 130, -1, -1));
        
        edata = new JLabel("");
        Styles.errorLabel(edata);
        bg.add(edata, new AbsoluteConstraints(0, 165, -1, -1));
        
        lcelular = new JLabel("Celular");
        Styles.defaultLabel(lcelular);
        bg.add(lcelular, new AbsoluteConstraints(220, 90, -1, -1));

        fcelular = new JTextField();
        Styles.defaultField(fcelular);
        bg.add(fcelular, new AbsoluteConstraints(220, 130, -1, -1));
        
        ecelular = new JLabel("");
        Styles.errorLabel(ecelular);
        bg.add(ecelular, new AbsoluteConstraints(220, 165, -1, -1));
        
        ltelefone = new JLabel("Telefone");
        Styles.defaultLabel(ltelefone);
        bg.add(ltelefone, new AbsoluteConstraints(440, 90, -1, -1));

        ftelefone = new JTextField();
        Styles.defaultField(ftelefone);
        bg.add(ftelefone, new AbsoluteConstraints(440, 130, -1, -1));
        
        etelefone = new JLabel("");
        Styles.errorLabel(etelefone);
        bg.add(etelefone, new AbsoluteConstraints(440, 165, -1, -1));
        
        lemail = new JLabel("Email");
        Styles.defaultLabel(lemail);
        bg.add(lemail, new AbsoluteConstraints(0, 180, -1, -1));

        femail = new JTextField();
        Styles.defaultField(femail);
        bg.add(femail, new AbsoluteConstraints(0, 220, -1, -1));
        
        eemail = new JLabel("");
        Styles.errorLabel(eemail);
        bg.add(eemail, new AbsoluteConstraints(0, 255, -1, -1));
        
        lsetor = new JLabel("Setor");
        Styles.defaultLabel(lsetor);
        bg.add(lsetor, new AbsoluteConstraints(220, 180, -1, -1));

        fsetor = new JComboBox();
        fsetor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"" ,"RH", "Contabilidade", "Manutenção", "Administração" }));
        Styles.defaultComboBox(fsetor, 200, 39);
        bg.add(fsetor, new AbsoluteConstraints(220, 220, -1, -1));
        
        esetor = new JLabel("");
        Styles.errorLabel(esetor);
        bg.add(esetor, new AbsoluteConstraints(220, 255, -1, -1));
        
        lpermissao = new JLabel("Permissão");
        Styles.defaultLabel(lpermissao);
        bg.add(lpermissao, new AbsoluteConstraints(440, 180, -1, -1));

        fpermissao = new JComboBox();
        fpermissao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"" , "Usuario", "Almoxerife", "Administrador"}));
        Styles.defaultComboBox(fpermissao, 200, 39);
        bg.add(fpermissao, new AbsoluteConstraints(440, 220, -1, -1));
        
        epermissao = new JLabel("");
        Styles.errorLabel(epermissao);
        bg.add(epermissao, new AbsoluteConstraints(440, 255, -1, -1));
        
        bSave = new JButton("Salvar");
        Styles.defaultButton(bSave);
        bSave.setPreferredSize(new Dimension(196, 34));
        bg.add(bSave, new AbsoluteConstraints(440, 290, -1, -1));
        
        bSave.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    
                Dialogs.showLoadPopup(bg);
                timerTest();
                
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
                
                JTable tabela = Usuarios.tabela;
                DefaultTableModel tableModel = Usuarios.tableModel;
                int row = tabela.getSelectedRow();
                    
                if (mode.equals("edit")) {
                    // TODO: edit here
                    self.dispose();
                    JOptionPane.showMessageDialog(null, "Item editado com sucesso!");

                } else if(mode.equals("add")) {
                    // TODO: save here
                    self.dispose();
                    JOptionPane.showMessageDialog(null, "Item adicionado com sucesso!");
                } else {
                    self.dispose();
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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}