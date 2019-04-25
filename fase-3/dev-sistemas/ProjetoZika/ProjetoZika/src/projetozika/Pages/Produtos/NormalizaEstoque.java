/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.Produtos;

import CustomFields.FormataDecimal;
import DAO.EstoqueDAO;
import DAO.ProdutoDAO;
import Models.Produto;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 * Tela para normalizar quantidade do produto no estoque
 * @author welison
 */
public class NormalizaEstoque extends Templates.BaseFrame {
    private JPanel bg;
    private JLabel lprod;
    private JLabel lqtd;
    private JTextField fqtd;
    private JLabel eqtd;
    private JButton bSave;
    private final String id;
    private final ProdutoDAO produtoDao;
    private final Produto produto;
    private final EstoqueDAO estoqueDao;

    /**
     * Janela para normalizar estoque de um dado produto
     * @param id o id do produto a ser normalizado
     * @param mode o mode de visualiação da página (edit)
     * @param params parâmetros de filtro e paginação
     */
    public NormalizaEstoque(String id, String mode, Properties params) {
        this.self = this;
        this.id = id;
        this.params = params;
        
        AccessibilityManager.setAccessibility(this);
        
        // cria objetos para carregar dados posteriormente
        produtoDao = new ProdutoDAO();
        produto = produtoDao.selecionarPorId(id);
        estoqueDao = new EstoqueDAO();
        
        // carrega os elementos e o design da tela
        initComponents();
        Styles.internalFrame(this, 280, 360);
        createBaseLayout();
        addTopContent(Methods.getTranslation("NormalizarEstoque"));
        setTitle(Methods.getTranslation("NormalizarEstoque"));
        addCenterContent();
    }
    
    /**
     * Adiciona o conteúdo da área central (o formulário em si)
     */
    private void addCenterContent() {
        bg = new JPanel();
        bg.setLayout(new AbsoluteLayout());
        bg.setOpaque(false);
        
        String titleProd = Methods.getTranslation("Produto") + ": "
                + produto.getNome() + " - " + produto.getUnidade();
        lprod = new JLabel(titleProd);
        Styles.defaultLabel(lprod);
        lprod.setPreferredSize(new Dimension(600, 30));
        bg.add(lprod, new AbsoluteConstraints(0, 0, -1, -1));
        
        lqtd = new JLabel(Methods.getTranslation("Quantidade"));
        Styles.defaultLabel(lqtd);
        bg.add(lqtd, new AbsoluteConstraints(0, 40, -1, -1));

        fqtd = new JTextField();
        Styles.defaultField(fqtd);
        bg.add(fqtd, new AbsoluteConstraints(0, 70, -1, -1));
        fqtd.setDocument(new FormataDecimal(11, 0));
        fqtd.setText(produto.getTotal()+"");
        
        eqtd = new JLabel("");
        Styles.errorLabel(eqtd);
        bg.add(eqtd, new AbsoluteConstraints(0, 105, -1, -1));
        
        bSave = new JButton(Methods.getTranslation("Salvar"));
        Styles.defaultButton(bSave);
        bSave.setPreferredSize(new Dimension(196, 34));
        bg.add(bSave, new AbsoluteConstraints(0, 142, -1, -1));
        bSave.addActionListener((ActionEvent e) -> {
            
            // limpa erros
            clearErrors();
            
            // validação
            boolean isValid = true;
            if (! Validator.validaNumero(fqtd, eqtd)) isValid = false;
            if (isValid) {
                Dialogs.showLoadPopup(bg);
                timerTest();
            }
        });
        
        
        pCenter.add(bg);
    }
    
    /**
     * Limpa os labels de erros
     */
    private void clearErrors() {
        eqtd.setText("");
    }
    
    private Timer t;
    private void timerTest() {
        
        t = new Timer(250, (ActionEvent e) -> {
            Dialogs.hideLoadPopup(bg);
            self.dispose();
            
            // atualiza o estoque na base de dados
            int qtd = Integer.parseInt(fqtd.getText().trim());
            int prodId = produto.getId();
            estoqueDao.normalizarEstoqueProduto(prodId, qtd);
            
            // recarrega janelas anteriores
            Navigation.updateLayout("", new Properties());
            Navigation.updateLayout("produtos", params);
            Navigation.updateLayout("editarProduto", id, params);
            
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
