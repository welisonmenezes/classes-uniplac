/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.NotasFiscais;

import DAO.ProdutoDAO;
import Models.NotaFiscalProduto;
import Models.Produto;
import Templates.ComboItem;
import Templates.SuggestionsBox;
import Utils.Methods;
import Utils.Navigation;
import Utils.Styles;
import Utils.Validator;
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
import org.netbeans.lib.awtextra.AbsoluteConstraints;

/**
 *
 * @author Welison
 */
public class SelecionarProduto extends javax.swing.JPanel {
    
    private JPanel self;
    private JLabel lnome;
    public static JTextField fnome;
    private JLabel addProduto;
    private JLabel enome;
    private JLabel lunidade;
    public static JTextField funidade;
    private JLabel eunidade;
    private JLabel lquantidade;
    private JTextField fquantidade;
    private JLabel equantidade;
    private JLabel lvalor;
    private JTextField fvalor;
    private JLabel evalor;
    private JButton selProd;
    private JPanel pSuggestions;
    public static JComboBox cnome;
    private Properties params;
    private final AddNotaFiscal caller;
    private final ProdutoDAO produtoDao;
    private ArrayList<Produto> produtos;
    private Produto produto;
    private JLabel eselProd;

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
        
        // carrega os dados
        produtoDao = new ProdutoDAO();
        produtos = new ArrayList<>();
    }
    
    /**
     * Adiciona os elementos na tela
     */
    private void addElements() {
        lnome = new JLabel(Methods.getTranslation("Nome"));
        Styles.defaultLabel(lnome);
        add(lnome, new AbsoluteConstraints(20, 20, -1, -1));
        
        // suggestion box
        pSuggestions = new JPanel();
        fnome = new JTextField();
        cnome = new JComboBox();
        new SuggestionsBox(pSuggestions, fnome, cnome, 200) {
            @Override
            public ArrayList<ComboItem> addElements() {
                ArrayList<ComboItem> elements = new ArrayList<>();
                // atualiza a lista de sugestões dos produtos
                produtos.clear();
                produtos = produtoDao.selecionarPorNome(fnome.getText());
                produtos.forEach(produto -> {
                    elements.add(new ComboItem(produto.getId(), produto.getNome() + " - " + produto.getUnidade()));
                });
                return elements;
            }
            @Override
            public void afterSelectItem() {
                // adiciona o produto selecionado
                ComboItem selectedProd = (ComboItem)cnome.getSelectedItem();
                if (selectedProd != null) {
                    produto = produtoDao.selecionarPorId(selectedProd.getId()+"");
                    if (produto != null && produto.getId() > 0) {
                        funidade.setText(produto.getUnidade());
                    }
                }
            }
        };
        add(pSuggestions, new AbsoluteConstraints(20, 50, -1, -1));
        
        addProduto = new JLabel("<html><u>"+ Methods.getTranslation("AdicionarNovo") +"</u></html>");
        Styles.defaultLabel(addProduto);
        addProduto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(addProduto, new AbsoluteConstraints(230, 45, -1, -1));
        
        // click botão add produto
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
        
        eselProd = new JLabel("");
        Styles.errorLabel(eselProd);
        add(eselProd, new AbsoluteConstraints(240, 325, -1, -1));
        
        selProd = new JButton(Methods.getTranslation("Adicionar"));
        Styles.defaultButton(selProd);
        selProd.setPreferredSize(new Dimension(100, 34));
        add(selProd, new AbsoluteConstraints(240, 293, -1, -1));
        selProd.addActionListener((ActionEvent e) -> {
            
            // limpa erros
            clearErrors();

            // validação
            boolean isValid = true;
            if (! Validator.validaCampo(funidade, eunidade)) isValid = false;
            if (! Validator.validaNumero(fquantidade, equantidade)) isValid = false;
            if (! Validator.validaValor(fvalor, evalor)) isValid = false;
            if (! Validator.validaComboBox(cnome, enome)) isValid = false;
            if (isValid) {
                // cria NotaFiscalProduto e adiciona à lista de produtos da nota fiscal
                NotaFiscalProduto notaProduto = new NotaFiscalProduto(produto, Integer.parseInt(fquantidade.getText()), Double.parseDouble(fvalor.getText()), "");
                if (caller.addProduto(notaProduto)) {
                    clearFields();
                } else {
                    eselProd.setText(Methods.getTranslation("ProdutoJaAdicionado"));
                }
            }
            
        });
    }
    
    /**
     * Limpa os labels de erros
     */
    private void clearErrors() {
        eunidade.setText("");
        equantidade.setText("");
        evalor.setText("");
        enome.setText("");
    }
    
    /**
     * Reseta os campos do formulário
     */
    private void clearFields() {
        funidade.setText("");
        fquantidade.setText("");
        fnome.setText("");
        fvalor.setText("");
        cnome.removeAll();
        cnome.revalidate();
        cnome.setSelectedIndex(0);
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
