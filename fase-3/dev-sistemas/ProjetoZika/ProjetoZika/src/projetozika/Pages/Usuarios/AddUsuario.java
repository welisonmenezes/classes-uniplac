/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.Usuarios;

import Config.Environment;
import CustomFields.MaskFactory;
import CustomFields.MaxSize;
import DAO.UsuarioDAO;
import Models.Usuario;
import Utils.Dialogs;
import Utils.Methods;
import Utils.Navigation;
import Utils.Styles;
import Utils.Validator;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Properties;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.Timer;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 * Tela para add/ver/editar usuário
 * @author Welison
 */
public class AddUsuario extends Templates.BaseFrame {

    private JPanel bg;
    private JTextField fnome;
    private JLabel lnome;
    private JLabel enome;
    private JFormattedTextField fcpf;
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
    private JTextField flogin;
    private JLabel llogin;
    private JLabel elogin;
    private JPasswordField fsenha;
    private JLabel lsenha;
    private JLabel esenha;
    private JButton bSave;
    private Usuario usuario;
    private UsuarioDAO usuarioDao;
    private String oldLogin;
    private String oldCpf;
    private String cpf;
    
    /**
     * chamada para adição
     * @param params parâmetros de filtro e paginação
     */
    public AddUsuario(Properties params) {
        this.self = this;
        this.mode = "add";
        this.params = params;
        initPage(Methods.getTranslation("AdicionarUsuario"));
    }
    
    /**
     * chamada para visualização ou edição
     * @param id o id do usuário
     * @param mode o modo (view|edit|perfil)
     * @param params parâmetros de filtro e paginação 
     */
    public AddUsuario(String id, String mode, Properties params) {
        this.self = this;
        this.mode = mode;
        this.params = params;
        this.cpf = id;
        switch (this.mode) {
            case "view":
                initPage(Methods.getTranslation("VerUsuario"));
                Methods.disabledFields(bg);
                break;
            case "edit":
                initPage(Methods.getTranslation("EditarUsuario"));
                break;
            case "perfil":
                initPage(Methods.getTranslation("EditarPerfil"));
                break;
        }
        
        fillFields(id);
    }
    
    /**
     * Inicia a tela
     * @param title o título
     */
    private void initPage(String title) {
        
        // cria objetos para carregar dados posteriormente
        usuarioDao = new UsuarioDAO();
        usuario = new Usuario();
        
        // carrega os elementos e o design da tela
        initComponents();
        Styles.internalFrame(this, 670, 550);
        Methods.setAccessibility(this);
        createBaseLayout();
        addTopContent(title);
        addCenterContent();
        
        // seta a página pai como página corrente
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                if (mode.equals("perfil")) {
                    Navigation.currentPage = "perfil";
                } else {
                    Navigation.currentPage = "usuarios";
                }
            }
        });
    }
    
    /**
     * Adiciona o conteúdo da área central (o formulário em si)
     */
    private void addCenterContent() {
        bg = new JPanel();
        bg.setLayout(new AbsoluteLayout());
        bg.setOpaque(false);

        lnome = new JLabel(Methods.getTranslation("Nome"));
        Styles.defaultLabel(lnome);
        bg.add(lnome, new AbsoluteConstraints(0, 0, -1, -1));

        fnome = new JTextField("");
        Styles.defaultField(fnome);
        bg.add(fnome, new AbsoluteConstraints(0, 40, -1, -1));
        fnome.setDocument(new MaxSize(100));
        
        enome = new JLabel("");
        Styles.errorLabel(enome);
        bg.add(enome, new AbsoluteConstraints(0, 75, -1, -1));
        
        lcpf = new JLabel(Methods.getTranslation("CPF"));
        Styles.defaultLabel(lcpf);
        bg.add(lcpf, new AbsoluteConstraints(220, 0, -1, -1));

        fcpf = new JFormattedTextField();
        Styles.defaultField(fcpf);
        bg.add(fcpf, new AbsoluteConstraints(220, 40, -1, -1));
        fcpf.setDocument(new MaxSize(14));
        fcpf.setFormatterFactory(MaskFactory.setMaskCpf());
        
        ecpf = new JLabel("");
        Styles.errorLabel(ecpf);
        bg.add(ecpf, new AbsoluteConstraints(220, 75, -1, -1));
        
        lsexo = new JLabel(Methods.getTranslation("Sexo"));
        Styles.defaultLabel(lsexo);
        bg.add(lsexo, new AbsoluteConstraints(440, 0, -1, -1));
        
        gsexo = new ButtonGroup();
        fhomem = new JRadioButton(Methods.getTranslation("Masculino"));
        fhomem.setActionCommand(Methods.getTranslation("Masculino"));
        fmulher = new JRadioButton(Methods.getTranslation("Feminino"));
        fmulher.setActionCommand(Methods.getTranslation("Feminino"));
        fhomem.setForeground(new Color(255, 255, 255));
        fmulher.setForeground(new Color(255, 255, 255));
        gsexo.add(fhomem);
        gsexo.add(fmulher);
        bg.add(fhomem, new AbsoluteConstraints(440, 50, -1, -1));
        bg.add(fmulher, new AbsoluteConstraints(550, 50, -1, -1));
        
        esexo = new JLabel("");
        Styles.errorLabel(esexo);
        bg.add(esexo, new AbsoluteConstraints(440, 75, -1, -1));
        
        ldata = new JLabel(Methods.getTranslation("DataDeNascimento"));
        Styles.defaultLabel(ldata);
        bg.add(ldata, new AbsoluteConstraints(0, 90, -1, -1));

        fdata = new JDateChooser();
        Styles.defaultDateChooser(fdata);
        Methods.setDateChooserFormat(fdata);
        bg.add(fdata, new AbsoluteConstraints(0, 130, -1, -1));
        
        edata = new JLabel("");
        Styles.errorLabel(edata);
        bg.add(edata, new AbsoluteConstraints(0, 165, -1, -1));
        
        lcelular = new JLabel(Methods.getTranslation("Celular"));
        Styles.defaultLabel(lcelular);
        bg.add(lcelular, new AbsoluteConstraints(220, 90, -1, -1));

        fcelular = new JTextField();
        Styles.defaultField(fcelular);
        bg.add(fcelular, new AbsoluteConstraints(220, 130, -1, -1));
        fcelular.setDocument(new MaxSize(45));
        
        ecelular = new JLabel("");
        Styles.errorLabel(ecelular);
        bg.add(ecelular, new AbsoluteConstraints(220, 165, -1, -1));
        
        ltelefone = new JLabel(Methods.getTranslation("Telefone"));
        Styles.defaultLabel(ltelefone);
        bg.add(ltelefone, new AbsoluteConstraints(440, 90, -1, -1));

        ftelefone = new JTextField();
        Styles.defaultField(ftelefone);
        bg.add(ftelefone, new AbsoluteConstraints(440, 130, -1, -1));
        ftelefone.setDocument(new MaxSize(45));
        
        etelefone = new JLabel("");
        Styles.errorLabel(etelefone);
        bg.add(etelefone, new AbsoluteConstraints(440, 165, -1, -1));
        
        lemail = new JLabel(Methods.getTranslation("Email"));
        Styles.defaultLabel(lemail);
        bg.add(lemail, new AbsoluteConstraints(0, 180, -1, -1));

        femail = new JTextField();
        Styles.defaultField(femail);
        bg.add(femail, new AbsoluteConstraints(0, 220, -1, -1));
        femail.setDocument(new MaxSize(100));
        
        eemail = new JLabel("");
        Styles.errorLabel(eemail);
        bg.add(eemail, new AbsoluteConstraints(0, 255, -1, -1));
        
        lsetor = new JLabel(Methods.getTranslation("Setor"));
        Styles.defaultLabel(lsetor);
        bg.add(lsetor, new AbsoluteConstraints(220, 180, -1, -1));

        fsetor = new JComboBox();
        fsetor.setModel(new DefaultComboBoxModel(Environment.SETORES));
        Styles.defaultComboBox(fsetor, 200, 39);
        bg.add(fsetor, new AbsoluteConstraints(220, 220, -1, -1));
        
        esetor = new JLabel("");
        Styles.errorLabel(esetor);
        bg.add(esetor, new AbsoluteConstraints(220, 255, -1, -1));
        
        lpermissao = new JLabel(Methods.getTranslation("Permissao"));
        Styles.defaultLabel(lpermissao);
        bg.add(lpermissao, new AbsoluteConstraints(440, 180, -1, -1));

        fpermissao = new JComboBox();
        fpermissao.setModel(new DefaultComboBoxModel(Environment.PERMISSOES));
        Styles.defaultComboBox(fpermissao, 200, 39);
        bg.add(fpermissao, new AbsoluteConstraints(440, 220, -1, -1));
        
        epermissao = new JLabel("");
        Styles.errorLabel(epermissao);
        bg.add(epermissao, new AbsoluteConstraints(440, 255, -1, -1));
        
        llogin = new JLabel(Methods.getTranslation("Login"));
        Styles.defaultLabel(llogin);
        bg.add(llogin, new AbsoluteConstraints(0, 270, -1, -1));

        flogin = new JTextField();
        Styles.defaultField(flogin);
        bg.add(flogin, new AbsoluteConstraints(0, 310, -1, -1));
        flogin.setDocument(new MaxSize(45));
        
        elogin = new JLabel("");
        Styles.errorLabel(elogin);
        bg.add(elogin, new AbsoluteConstraints(0, 345, -1, -1));
        
        lsenha = new JLabel(Methods.getTranslation("Senha"));
        Styles.defaultLabel(lsenha);
        bg.add(lsenha, new AbsoluteConstraints(220, 270, -1, -1));

        fsenha = new JPasswordField();
        Styles.defaultField(fsenha);
        bg.add(fsenha, new AbsoluteConstraints(220, 310, -1, -1));
        fsenha.setDocument(new MaxSize(100));
        
        esenha = new JLabel("");
        Styles.errorLabel(esenha);
        bg.add(esenha, new AbsoluteConstraints(220, 345, -1, -1));
        
        bSave = new JButton(Methods.getTranslation("Salvar"));
        Styles.defaultButton(bSave);
        bSave.setPreferredSize(new Dimension(196, 34));
        bg.add(bSave, new AbsoluteConstraints(440, 313, -1, -1));
        
        bSave.addActionListener((ActionEvent e) -> {
            
            // limpa erros
            clearErrors();
            
            // validação
            boolean isValid = true;
            if (! Validator.validaCampo(fnome, enome, 100)) isValid = false;
            if (! Validator.validaCpf(fcpf, ecpf)) isValid = false;
            if (! Validator.validaButtonGroup(gsexo, esexo)) isValid = false;
            if (! Validator.validaData(fdata, edata)) isValid = false;
            if (! Validator.validaTelefone(fcelular, ecelular)) isValid = false;
            if (! Validator.validaTelefone(ftelefone, etelefone)) isValid = false;
            if (! Validator.validaCampo(femail, eemail, 100)) isValid = false;
            if (! Validator.validaCampo(fsetor, esetor)) isValid = false;
            if (! Validator.validaCampo(fpermissao, epermissao)) isValid = false;
            if (! Validator.validaCampo(flogin, elogin)) isValid = false;
            if (! Validator.validaCampo(fsenha, esenha)) isValid = false;
            if (isValid) {
                
                // seta os valores do formulário ao usuário corrente
                usuario.setNome(fnome.getText());
                usuario.setCpf(fcpf.getText());
                usuario.setSexo(gsexo.getSelection().getActionCommand());
                
                java.util.Date pega = fdata.getDate();
                SimpleDateFormat sdf;
                if (Environment.getCurrentLang().equals("en")) {
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                } else {
                    sdf = new SimpleDateFormat("dd/MM/yyyy");
                }
                String data = sdf.format(pega);
                usuario.setDataNascimento(Methods.getSqlDateTime(data));
                
                usuario.setCelular(fcelular.getText());
                usuario.setTelefone(ftelefone.getText());
                usuario.setEmail(femail.getText());
                usuario.setSetor(fsetor.getSelectedItem().toString());
                usuario.setPermissao(fpermissao.getSelectedItem().toString());
                usuario.setLogin(flogin.getText());
                String senha = new String(fsenha.getPassword());
                usuario.setSenha(senha);
                
                // valida campos únicos
                if (!mode.equals("add") && (!oldCpf.equals(fcpf.getText())) && (usuarioDao.temCpf(usuario.getCpf()) > 0)) {
                    ecpf.setText(Methods.getTranslation("EsteCPFJaExiste"));
                } else if (!mode.equals("add") && (!oldLogin.equals(flogin.getText())) && (usuarioDao.temLogin(usuario.getLogin()) > 0)) {
                    elogin.setText(Methods.getTranslation("EsteLoginJaExiste"));
                } else if((mode.equals("add")) && usuarioDao.temCpf(usuario.getCpf()) > 0) {
                    ecpf.setText(Methods.getTranslation("EsteCPFJaExiste"));
                } else if((mode.equals("add")) && usuarioDao.temLogin(usuario.getLogin()) > 0) {
                    elogin.setText(Methods.getTranslation("EsteLoginJaExiste"));
                } else {
                    Dialogs.showLoadPopup(bg);
                    timerTest();
                }
            }
            
        });
        
        pCenter.add(bg);
    }
    
    /**
     * preenche os campos do formulário com o usuário cujo cpf é correspondente na base de dados
     * @param id o id do produto
     */
    private void fillFields(String cpf) {
        usuario = usuarioDao.selecionarPorCpf(cpf);
        if (usuario != null ) {
            oldLogin = usuario.getLogin();
            oldCpf = usuario.getCpf();
            fnome.setText(usuario.getNome());
            fcpf.setText(usuario.getCpf());
            Methods.setButtonGroup(usuario.getSexo(), gsexo.getElements());
            Methods.setDateToDateChooser(fdata, usuario.getDataNascimento());
            fcelular.setText(usuario.getCelular());
            ftelefone.setText(usuario.getTelefone());
            femail.setText(usuario.getEmail());
            fsetor.setSelectedItem(usuario.getSetor());
            fpermissao.setSelectedItem(usuario.getPermissao());
            flogin.setText(usuario.getLogin());
            fsenha.setText(usuario.getSenha());
        }
    }
    
    /**
     * Limpa as mensagens de erro
     */
    private void clearErrors() {
        enome.setText("");
        ecpf.setText("");
        esexo.setText("");
        edata.setText("");
        ecelular.setText("");
        etelefone.setText("");
        eemail.setText("");
        esetor.setText("");
        epermissao.setText("");
        elogin.setText("");
        esenha.setText("");
    }
    
    private Timer t;
    private void timerTest() {
        
        t = new Timer(500, (ActionEvent e) -> {
            Dialogs.hideLoadPopup(bg);
            
            switch (mode) {
                case "edit":
                    // TODO: edit here
                    self.dispose();
                    try {
                        // edita o usuario
                        usuarioDao.alterar(usuario);
                        JOptionPane.showMessageDialog(null, Methods.getTranslation("EditadoComSucesso"));
                    } catch(Exception error) {
                        JOptionPane.showMessageDialog(null, Methods.getTranslation("ErroAoTentarEditar"));
                        throw new RuntimeException("AddUsuario.edit: " + error);
                    }
                    // recarrega a tela pai
                    Navigation.updateLayout("", new Properties());
                    Navigation.updateLayout("usuarios", params);
                    break;
                case "add":
                    // TODO: save here
                    self.dispose();
                    try {
                        // adiciona um novo produto
                        usuarioDao.inserir(usuario);
                        JOptionPane.showMessageDialog(null, Methods.getTranslation("AdicionadoComSucesso"));
                    } catch(Exception error) {
                        JOptionPane.showMessageDialog(null, Methods.getTranslation("ErroAoTentarAdicionar"));
                        throw new RuntimeException("AddUsuario.add: " + error);
                    }
                    // recarrega a tela pai
                    Navigation.updateLayout("", new Properties());
                    Navigation.updateLayout("usuarios", params);
                    break;
                case "perfil":
                    // TODO: save here
                    self.dispose();
                    try {
                        // edita o usuario
                        usuarioDao.alterar(usuario);
                        usuario = usuarioDao.selecionarPorCpf(cpf);
                        Environment.setLoggedUser(usuario);
                        JOptionPane.showMessageDialog(null, Methods.getTranslation("EditadoComSucesso"));
                    } catch(Exception error) {
                        JOptionPane.showMessageDialog(null, Methods.getTranslation("ErroAoTentarEditar"));
                        throw new RuntimeException("AddUsuario.perfil: " + error);
                    }
                    // recarrega a tela pai
                    Navigation.updateLayout("", new Properties());
                    Navigation.updateLayout("perfil", params);
                    break;
                default:
                    self.dispose();
                    Navigation.updateLayout("", new Properties());
                    Navigation.updateLayout("usuarios", params);
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
