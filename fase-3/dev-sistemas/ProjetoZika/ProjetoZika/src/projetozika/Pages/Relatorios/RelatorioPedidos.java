/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.Relatorios;

import Config.Environment;
import CustomFields.ComboItem;
import CustomFields.SuggestionsBox;
import DAO.ProdutoDAO;
import DAO.UsuarioDAO;
import Models.Produto;
import Models.ReportModel;
import Models.Usuario;
import Utils.DateHandler;
import Utils.Methods;
import Utils.PDFGenerator;
import Utils.Styles;
import Utils.Validator;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 * Tela para gerar relatórios de pedidos
 * @author welison
 */
public class RelatorioPedidos extends javax.swing.JPanel {
    private JLabel lusuario;
    private JPanel susuario;
    private JTextField fusuario;
    private JComboBox cusuario;
    private JLabel lproduto;
    private JPanel sproduto;
    private JTextField fproduto;
    private JComboBox cproduto;
    private JLabel ldatafrom;
    private JDateChooser fdatafrom;
    private JLabel edatafrom;
    private JLabel ldatato;
    private JDateChooser fdatato;
    private JLabel edatato;
    private JButton btnRelatorioPedido;
    private final JPanel self;
    private JLabel title;
    private JPanel saprover;
    private JTextField faprover;
    private JComboBox caprover;
    private JLabel laprover;
    private JComboBox fStatus;
    private JLabel lstatus;
    private final ProdutoDAO produtoDao;
    private ArrayList<Produto> produtos;
    private final UsuarioDAO usuarioDao;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Usuario> aprovadores;

    /**
     * Creates new form RelatorioPedidos
     */
    public RelatorioPedidos() {
        initComponents();
        this.self = this;
        
        produtoDao = new ProdutoDAO();
        produtos = new ArrayList<>();
        usuarioDao = new UsuarioDAO();
        usuarios = new ArrayList<>();
        aprovadores = new ArrayList<>();
        
        addCamposPedidos();
    }
    
    private void addCamposPedidos() {
        setBackground(new Color(27, 28, 29));
        setLayout(new AbsoluteLayout());
        
        title = new JLabel();
        title.setFont(new java.awt.Font("Tahoma", 1, 24));
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText(Methods.getTranslation("RelatorioDePedidos"));
        add(title, new AbsoluteConstraints(20, 20, -1, -1));
        
        lusuario = new JLabel(Methods.getTranslation("Usuario"));
        Styles.defaultLabel(lusuario);
        add(lusuario, new AbsoluteConstraints(20, 60, -1, -1));
        
        // suggestion box
        susuario = new JPanel();
        fusuario = new JTextField();
        cusuario = new JComboBox();
        new SuggestionsBox(susuario, fusuario, cusuario, 300) {
            @Override
            public ArrayList<ComboItem> addElements() {
                // sugestão de produtos
                ArrayList<ComboItem> elements = new ArrayList<>();
                usuarios.clear();
                usuarios = usuarioDao.selecionarPorNome(fusuario.getText().trim());
                usuarios.forEach(usuario -> {
                    elements.add(new ComboItem(usuario.getId(), usuario.getNome()));
                });
                return elements;
            }
        };
        add(susuario, new AbsoluteConstraints(20, 90, -1, -1));
        
        lproduto = new JLabel(Methods.getTranslation("Produto"));
        Styles.defaultLabel(lproduto);
        add(lproduto, new AbsoluteConstraints(340, 60, -1, -1));
        
        // suggestion box
        sproduto = new JPanel();
        fproduto = new JTextField();
        cproduto = new JComboBox();
        new SuggestionsBox(sproduto, fproduto, cproduto, 300) {
            @Override
            public ArrayList<ComboItem> addElements() {
                // sugestão de produtos
                ArrayList<ComboItem> elements = new ArrayList<>();
                produtos.clear();
                produtos = produtoDao.selecionarPorNome(fproduto.getText().trim());
                produtos.forEach(produto -> {
                    elements.add(new ComboItem(produto.getId(), produto.getNome() + " - " + produto.getUnidade()));
                });
                return elements;
            }
        };
        add(sproduto, new AbsoluteConstraints(340, 90, -1, -1));
        
        laprover = new JLabel(Methods.getTranslation("Aprovador"));
        Styles.defaultLabel(laprover);
        add(laprover, new AbsoluteConstraints(20, 140, -1, -1));
        
        // suggestion box
        saprover = new JPanel();
        faprover = new JTextField();
        caprover = new JComboBox();
        new SuggestionsBox(saprover, faprover, caprover, 300) {
            @Override
            public ArrayList<ComboItem> addElements() {
                // sugestão de produtos
                ArrayList<ComboItem> elements = new ArrayList<>();
                aprovadores.clear();
                aprovadores = usuarioDao.selecionarPorNomeEPermissaoExcluida(faprover.getText().trim(), Methods.getTranslation("Usuario"));
                aprovadores.forEach(usuario -> {
                    elements.add(new ComboItem(usuario.getId(), usuario.getNome()));
                });
                return elements;
            }
        };
        add(saprover, new AbsoluteConstraints(20, 170, -1, -1));
        
        lstatus = new JLabel(Methods.getTranslation("Status"));
        Styles.defaultLabel(lstatus);
        add(lstatus, new AbsoluteConstraints(340, 140, -1, -1));
        
        fStatus = new JComboBox();
        fStatus.setModel(new DefaultComboBoxModel(Environment.STATUS));
        Styles.defaultComboBox(fStatus, 300, 39);
        add(fStatus, new AbsoluteConstraints(340, 170, -1, -1));
        
        ldatafrom = new JLabel(Methods.getTranslation("De"));
        Styles.defaultLabel(ldatafrom);
        add(ldatafrom, new AbsoluteConstraints(20, 220, -1, -1));
        
        fdatafrom = new JDateChooser();
        Styles.defaultDateChooser(fdatafrom, 300);
        DateHandler.setDateChooserFormat(fdatafrom);
        add(fdatafrom, new AbsoluteConstraints(20, 250, -1, -1));
        
        edatafrom = new JLabel("");
        Styles.errorLabel(edatafrom);
        add(edatafrom, new AbsoluteConstraints(20, 300, -1, -1));
        
        ldatato = new JLabel(Methods.getTranslation("Ate"));
        Styles.defaultLabel(ldatato);
        add(ldatato, new AbsoluteConstraints(340, 220, -1, -1));
        
        fdatato = new JDateChooser();
        Styles.defaultDateChooser(fdatato, 300);
        DateHandler.setDateChooserFormat(fdatato);
        add(fdatato, new AbsoluteConstraints(340, 250, -1, -1));
        
        edatato = new JLabel("");
        Styles.errorLabel(edatato);
        edatato.setPreferredSize( new Dimension( 300, 20 ) );
        add(edatato, new AbsoluteConstraints(340, 300, -1, -1));
        
        btnRelatorioPedido = new JButton(Methods.getTranslation("GerarRelatorio"));
        Styles.defaultButton(btnRelatorioPedido, 300);
        add(btnRelatorioPedido, new AbsoluteConstraints(340, 360, -1, -1));
        btnRelatorioPedido.addActionListener((ActionEvent e) -> {
            
            // limpa erros
            clearErrors();
            
            // validação
            boolean isValid = true;
            if (! Validator.validaData(fdatafrom, edatafrom)) isValid = false;
            if (! Validator.validaData(fdatato, edatato)) isValid = false;
            // verifica se data é maior ou menor
            if (!Validator.isDateBeforeThen(fdatafrom, fdatato, edatato)) isValid = false;
            if (isValid) {
                
                String filename = "ProjetoZika-Pedidos-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".pdf";
                String header[] = {"Código", "Solicitante", "Status", "Data", "Qtd Produto"};
                String infos[] = {"Usuário: Todos", "Produto: Todos", "Período: 12/11/2018 à 01/02/2019"};
                
                ArrayList<String[]> data = new ArrayList();
                for (int i = 0; i < 100; i++) {
                    String row[] = {i+"","Fulano_" + i, "Status here", "11/11/2011", "33"};
                    data.add(row);
                }
                
                ReportModel relatorio = new ReportModel(filename, header, infos, data);
                
                new PDFGenerator(relatorio, this);
            }
            
        });
    }
    
    private void clearErrors() {
        edatafrom.setText("");
        edatato.setText("");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
