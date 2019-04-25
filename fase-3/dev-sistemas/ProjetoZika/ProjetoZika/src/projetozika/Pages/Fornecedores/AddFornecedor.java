package projetozika.Pages.Fornecedores;

import CustomFields.MaxSize;
import DAO.FornecedorDAO;
import Models.Fornecedor;
import CustomFields.ComboItem;
import CustomFields.MaskFactory;
import Templates.BaseFrame;
import Utils.Dialogs;
import Utils.AccessibilityManager;
import Utils.Methods;
import Utils.Navigation;
import Utils.Styles;
import Utils.Validator;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import projetozika.Pages.NotasFiscais.AddNotaFiscal;

/**
 * Tela de add/ver/editar fornecedor
 * @author Welison
 */
public class AddFornecedor extends BaseFrame {
    private JPanel bg;
    private JTextField fname;
    private JLabel lname;
    private JLabel ename;
    private JTextField ftel;
    private JLabel ltel;
    private JLabel etel;
    private JFormattedTextField fcnpj;
    private JLabel lcnpj;
    private JLabel ecnpj;
    private JButton bSave;
    private FornecedorDAO fornecedorDao;
    private Fornecedor fornecedor;
    private String oldCnpj;
    
   /**
    * chama para adição
    * @param params parâmetros de filtro e paginação
    */
    public AddFornecedor(Properties params) {
        this.self = getInstance();
        this.mode = "add";
        this.params = params;
        initPage(Methods.getTranslation("AdicionarFornecedor"));
    }
    
    /**
     * chama para adição via nota fiscal
     * @param panelCaller o JPanel da nota fiscal
     * @param params parâmetros de filtro e paginação
     */
    public AddFornecedor(JPanel panelCaller, Properties params) {
        this.self = getInstance();
        this.mode = "nota";
        this.params = params;
        initPage(Methods.getTranslation("AdicionarFornecedorPelaNota"));
    }
    
    /**
     * chama para editar ou visualizar
     * @param id o Id do fornecedor
     * @param mode o modo de carregamento (view|edit)
     * @param params parâmetros de filtro e paginação
     */
    public AddFornecedor(String id, String mode, Properties params) {
        this.self = getInstance();
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
    
    /**
     * Inicia a tela
     * @param title o título
     */
    private void initPage(String title) {
        
        AccessibilityManager.setAccessibility(this);
        setTitle(title);
        
        // cria objetos para carregar dados posteriormente
        fornecedorDao = new FornecedorDAO();
        fornecedor = new Fornecedor();
        
        // carrega os elementos e o design da tela
        initComponents();
        Styles.internalFrame(this);
        createBaseLayout();
        addTopContent(title);
        addCenterContent();
        
        // seta a página pai como página corrente
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                Navigation.currentPage = "fornecedores";
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

        lname = new JLabel(Methods.getTranslation("Nome"));
        Styles.defaultLabel(lname);
        bg.add(lname, new AbsoluteConstraints(0, 0, -1, -1));

        fname = new JTextField();
        Styles.defaultField(fname);
        bg.add(fname, new AbsoluteConstraints(0, 40, -1, -1));
        fname.setDocument(new MaxSize(100));
        
        ename = new JLabel("");
        Styles.errorLabel(ename);
        bg.add(ename, new AbsoluteConstraints(0, 75, -1, -1));
        
        ltel = new JLabel(Methods.getTranslation("Telefone"));
        Styles.defaultLabel(ltel);
        bg.add(ltel, new AbsoluteConstraints(220, 0, -1, -1));

        ftel = new JTextField();
        Styles.defaultField(ftel);
        bg.add(ftel, new AbsoluteConstraints(220, 40, -1, -1));
        ftel.setDocument(new MaxSize(45));
        
        etel = new JLabel("");
        Styles.errorLabel(etel);
        bg.add(etel, new AbsoluteConstraints(220, 75, -1, -1));
        
        lcnpj = new JLabel(Methods.getTranslation("CNPJ"));
        Styles.defaultLabel(lcnpj);
        bg.add(lcnpj, new AbsoluteConstraints(0, 90, -1, -1));

        fcnpj = new JFormattedTextField();
        Styles.defaultField(fcnpj);
        bg.add(fcnpj, new AbsoluteConstraints(0, 130, -1, -1));
        fcnpj.setDocument(new MaxSize(18));
        fcnpj.setFormatterFactory(MaskFactory.setMaskCnpj());
        
        ecnpj = new JLabel("");
        Styles.errorLabel(ecnpj);
        bg.add(ecnpj, new AbsoluteConstraints(0, 165, -1, -1));
        
        bSave = new JButton(Methods.getTranslation("Salvar"));
        Styles.defaultButton(bSave);
        bSave.setPreferredSize(new Dimension(196, 34));
        bg.add(bSave, new AbsoluteConstraints(220, 132, -1, -1));
        
        bSave.addActionListener((ActionEvent e) -> {
            
            // limpa erros
            clearErrors();
            
            // validação
            boolean isValid = true;
            if (! Validator.validaCnpj(fcnpj, ecnpj)) isValid = false;
            if (! Validator.validaCampo(fname, ename, 100)) isValid = false;
            if (! Validator.validaTelefone(ftel, etel)) isValid = false;
            if (isValid) {
                String newCnpj = fcnpj.getText().replace(".","").replace("/","").replace("-","").replace("_","").trim();
                // seta os valores do formulário ao fornecedor corrente
                fornecedor.setCnpj(newCnpj);
                fornecedor.setNome(fname.getText().trim());
                fornecedor.setTelefone(ftel.getText().trim());
                
                // valida campos únicos
                if (mode.equals("edit") && (!oldCnpj.equals(newCnpj)) && (fornecedorDao.temCnpj(fornecedor.getCnpj()) > 0)) {
                    ecnpj.setText(Methods.getTranslation("EsteCNPJJaExiste"));
                } else if((!mode.equals("edit")) && fornecedorDao.temCnpj(fornecedor.getCnpj()) > 0) {
                    ecnpj.setText(Methods.getTranslation("EsteCNPJJaExiste"));
                } else {
                    Dialogs.showLoadPopup(bg);
                    timerTest();
                }
                
                
            }

        });
        
        pCenter.add(bg);
    }
    
    /**
     * preenche os campos do formulário com o fornecedor cujo id é correspondente na base de dados
     * @param id o id do fornecedor
     */
    private void fillFields(String id) {
        fornecedor = fornecedorDao.selecionarPorId(id);
        if (fornecedor != null) {
            oldCnpj = fornecedor.getCnpj();
            fname.setText(fornecedor.getNome());
            ftel.setText(fornecedor.getTelefone());
            fcnpj.setText(fornecedor.getCnpj());
        }
    }
    
    /**
     * Limpa as mensagens de erro
     */
    private void clearErrors() {
        ename.setText("");
        ecnpj.setText("");
        etel.setText("");
    }
    
    private Timer t;
    private void timerTest() {
        
        t = new Timer(250, (ActionEvent e) -> {
            Dialogs.hideLoadPopup(bg);
            
            switch (mode) {
                case "edit":
                    self.dispose();
                    try {
                        // edita o fornecedor
                        fornecedorDao.alterar(fornecedor);
                        JOptionPane.showMessageDialog(null, Methods.getTranslation("EditadoComSucesso"));
                    } catch(Exception error) {
                        JOptionPane.showMessageDialog(null, Methods.getTranslation("ErroAoTentarEditar"));
                        throw new RuntimeException("AddFornecedor.edit: " + error);
                    }
                    Navigation.updateLayout("", new Properties());
                    Navigation.updateLayout("fornecedores", params);
                    break;
                case "add":
                    self.dispose();
                    try {
                        // adiciona um novo fornecedor
                        fornecedorDao.inserir(fornecedor);
                        JOptionPane.showMessageDialog(null, Methods.getTranslation("AdicionadoComSucesso"));
                    } catch(Exception error) {
                        JOptionPane.showMessageDialog(null, Methods.getTranslation("ErroAoTentarAdicionar"));
                        throw new RuntimeException("AddFornecedor.add: " + error);
                    }
                    Navigation.updateLayout("", new Properties());
                    Navigation.updateLayout("fornecedores", params);
                    break;
                case "nota":
                    self.dispose();
                    try {
                        // adiciona um novo fornecedor via nota fiscal
                        int lastInsertedId = fornecedorDao.inserir(fornecedor);
                        AddNotaFiscal.fcnpj.setText(fcnpj.getText().trim());
                        ComboItem ci = new ComboItem(lastInsertedId, fornecedor.getCnpj());
                        AddNotaFiscal.ccnpj.addItem(ci);
                        AddNotaFiscal.ccnpj.setSelectedItem(ci);
                    } catch(Exception error) {
                        throw new RuntimeException("AddFornecedor.nota: " + error);
                    }
                    
                    break;
                default:
                    self.dispose();
                    Navigation.updateLayout("", new Properties());
                    Navigation.updateLayout("fornecedores", params);
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
