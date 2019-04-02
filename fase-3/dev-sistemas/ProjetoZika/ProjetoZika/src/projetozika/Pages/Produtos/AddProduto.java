/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.Produtos;

import Config.Environment;
import DAO.ProdutoDAO;
import Models.Produto;
import Templates.ComboItem;
import Utils.Dialogs;
import Utils.Methods;
import Utils.Navigation;
import Utils.Styles;
import Utils.Validator;
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
import projetozika.Pages.NotasFiscais.SelecionarProduto;

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
    private Produto produto;
    private ProdutoDAO produtoDao;
    
   /**
    * Chamada para adição
    * @param params parâmetros de filtro e paginação
    */
    public AddProduto(Properties params) {
        this.self = this;
        this.mode = "add";
        this.params = params;
        initPage(Methods.getTranslation("AdicionarProduto"));
    }
    
    /**
     * Chamada para adição via nota fiscal
     * @param panelCaller o JPanel da nota fiscal
     * @param params parâmetros de filtro e paginação
     */
    public AddProduto(JPanel panelCaller, Properties params) {
        this.self = this;
        this.params = params;
        this.mode = "nota";
        
        initPage(Methods.getTranslation("AdicionarProdutoPelaNota"));
        
    }
    
    /**
     * Chamada para edição ou visualização
     * @param id o Id do produto
     * @param mode o modo (view|edit)
     * @param params parâmetros de filtro e paginação
     */
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
    
    /**
     * Inicia a tela
     * @param title o título
     */
    private void initPage(String title) {
        
        // cria objetos para carregar dados posteriormente
        produtoDao = new ProdutoDAO();
        produto = new Produto();
        
        // carrega os elementos e o design da tela
        initComponents();
        Styles.internalFrame(this, 450, 400);
        Methods.setAccessibility(this);
        createBaseLayout();
        addTopContent(title);
        addCenterContent();
        
        // seta a página pai como página corrente
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                Navigation.currentPage = "produtos";
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
            
            // limpa erros
            clearErrors();
            
            // validação
            boolean isValid = true;
            if (! Validator.validaCampo(fnome, enome)) isValid = false;
            if (! Validator.validaCampo(funidade, eunidade)) isValid = false;
            if (! Validator.validaCampo(fdescricao, edescricao)) isValid = false;
            if (isValid) {
                
                // seta os valores do formulário ao produto corrente
                produto.setNome(fnome.getText());
                produto.setUnidade(funidade.getSelectedItem().toString());
                produto.setDescricao(fdescricao.getText());
                
                Dialogs.showLoadPopup(bg);
                timerTest();
            }

        });
        
        pCenter.add(bg);
    }
    
    /**
     * preenche os campos do formulário com o produto cujo id é correspondente na base de dados
     * @param id o id do produto
     */
    private void fillFields(String id) {
        // carrega os dados do produto corrente baseado no id passado
        produto = produtoDao.selecionarPorId(id);
        if (produto != null) {
            fnome.setText(produto.getNome());
            funidade.setSelectedItem(produto.getUnidade());
            fdescricao.setText(produto.getDescricao());
        } 
    }
    
    /**
     * Limpa as mensagens de erro
     */
    private void clearErrors() {
        enome.setText("");
        eunidade.setText("");
        edescricao.setText("");
    }
    
    private Timer t;
    private void timerTest() {
        
        t = new Timer(500, (ActionEvent e) -> {
            Dialogs.hideLoadPopup(bg);
            self.dispose();
            
            switch (mode) {
                case "edit":
                    try {
                        // edita o produto
                        produtoDao.alterar(produto);
                        JOptionPane.showMessageDialog(null, Methods.getTranslation("EditadoComSucesso"));
                    } catch(Exception error) {
                        JOptionPane.showMessageDialog(null, Methods.getTranslation("ErroAoTentarEditar"));
                        throw new RuntimeException("AddProduto.edit: " + error);
                    }
                    // recarrega a tela pai
                    Navigation.updateLayout("", new Properties());
                    Navigation.updateLayout("produtos", params);
                    break;
                case "add":
                    try {
                        // adiciona um novo produto
                        produtoDao.inserir(produto);
                        JOptionPane.showMessageDialog(null, Methods.getTranslation("AdicionadoComSucesso"));
                    } catch(Exception error) {
                        JOptionPane.showMessageDialog(null, Methods.getTranslation("ErroAoTentarAdicionar"));
                        throw new RuntimeException("AddProduto.add: " + error);
                    }
                    // recarrega a tela pai
                    Navigation.updateLayout("", new Properties());
                    Navigation.updateLayout("produtos", params);
                    break;
                case "nota":
                    try {
                        // adiciona um novo produto via nota fiscal
                        int lastInsertedId = produtoDao.inserir(produto);
                        SelecionarProduto.fnome.setText(produto.getNome());
                        ComboItem ci = new ComboItem(lastInsertedId, produto.getNome()+" - "+produto.getUnidade());
                        SelecionarProduto.cnome.addItem(ci);
                        SelecionarProduto.cnome.setSelectedItem(ci);
                        SelecionarProduto.funidade.setText(produto.getUnidade());
                    } catch(Exception error) {
                        throw new RuntimeException("AddProduto.nota: " + error);
                    }
                    break;
                default:
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
