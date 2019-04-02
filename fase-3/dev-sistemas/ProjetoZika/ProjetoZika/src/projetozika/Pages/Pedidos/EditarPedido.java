/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.Pedidos;

import Config.Environment;
import DAO.PedidoDAO;
import DAO.UsuarioDAO;
import Models.Pedido;
import Models.PedidoProduto;
import Models.Usuario;
import Utils.Dialogs;
import Utils.Methods;
import Utils.Navigation;
import Utils.Styles;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *  Tela de editar/ver pedido
 * @author Welison
 */
public class EditarPedido extends Templates.BaseFrame {
    private JPanel bg;
    private JTable tabela;
    private DefaultTableModel tableModel;
    private JLabel title;
    private JScrollPane barraRolagem;
    private JButton btnFinalizar;
    private JPanel paction;
    private final ArrayList<PedidoProduto> pedidosProdutos;
    private JButton btnNegar;
    private final PedidoDAO pedidoDao;
    private Pedido pedido;
    private Usuario almoxarife;
    
    /**
     * chamada pra ver/editar pedido
     * @param id o id do pedido
     * @param mode o modo de visualização (view|edit)
     * @param params 
     */
    public EditarPedido(String id, String mode, Properties params) {
        this.self = this;
        this.mode = mode;
        this.params = params;
        
        pedidoDao = new PedidoDAO();
        pedidosProdutos = pedidoDao.selecionarPorId(id);
        
        if (pedidosProdutos.size() > 0) {
            pedido = pedidosProdutos.get(0).getPedido();
            int idAlmo = pedidosProdutos.get(0).getPedido().getAlmoxarifeId();
            if (idAlmo > 0) {
                UsuarioDAO usuarioDao = new UsuarioDAO();
                almoxarife = usuarioDao.selecionarPorId(idAlmo);
            }
            initPage(Methods.getTranslation("Pedido") + " " + id + " - " + pedido.getCreated());
        } else {
            initPage(Methods.getTranslation("Pedido") + " " + id);
        }
        
    }
    
    /**
     * Inicia a tela
     * @param title o título
     */
    private void initPage(String title) {
        // adiciona elementos na tela
        initComponents();
        Styles.internalFrame(this, 1000, 600);
        Methods.setAccessibility(this);
        createBaseLayout();
        addTopContent(title);
        addCenterContent();
        
        // add botões de negar e finalizar se estiver no modo edit
        if (this.mode.equals("edit")) {
            addBottomContent();
        }
        
        // seta a página pai como página corrente
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                Navigation.currentPage = "pedidos";
            }
        });
    }
    
    /**
     * Adiciona o conteúdo da área central
     */
    private void addCenterContent() {
        bg = new JPanel();
        bg.setLayout(new BorderLayout());
        bg.setOpaque(false);
        
        if (pedidosProdutos.size() > 0) {
            String nome = pedido.getSolicitante().getNome();
            if (almoxarife != null && almoxarife.getId() > 0) {
                title = new JLabel(Methods.getTranslation("PedidoDoColaborador") + ": " + nome + " | " + Methods.getTranslation("AprovadoPor") + ": " + almoxarife.getNome());
            } else {
                title = new JLabel(Methods.getTranslation("PedidoDoColaborador") + ": " + nome);
            }
            
            Styles.defaultLabel(title);
            bg.add(title, BorderLayout.NORTH);
        }
        
        makeTable();
        barraRolagem = new JScrollPane(tabela);
        Styles.defaultScroll(barraRolagem);
        bg.add(barraRolagem, BorderLayout.CENTER);
        
        pCenter.add(bg);
    }
    
    /**
     * Adiciona o conteúdo da área bottom
     */
    private void addBottomContent() {
        btnFinalizar = new JButton(Methods.getTranslation("FinalizarPedido"));
        Styles.defaultButton(btnFinalizar);
        // click finalizar pedido (editar)
        btnFinalizar.addActionListener((ActionEvent e) -> {
            Dialogs.showLoadPopup(bg);
            timerTest("finalizar");
        });
        
        
        btnNegar = new JButton(Methods.getTranslation("NegarPedido"));
        Styles.redButton(btnNegar);
        // click negar pedido
        btnNegar.addActionListener((ActionEvent e) -> {
            Dialogs.showLoadPopup(bg);
            timerTest("negar");
        });
        
        paction = new JPanel();
        paction.setLayout(new FlowLayout(FlowLayout.RIGHT));
        paction.setOpaque(false);
        paction.add(btnNegar);
        paction.add(btnFinalizar);
        pBottom.add(paction, BorderLayout.SOUTH);
    }
    
    /**
     * Gera a tabela com os dados
     */
    private void makeTable() {
        // cria tabela
        tabela = new JTable();
        tabela.setRowHeight(35);
        // seta colunas
        String[] colunas = {
            Methods.getTranslation("Codigo"),
            Methods.getTranslation("Produto"), 
            Methods.getTranslation("QuantidadeSolicitada"), 
            Methods.getTranslation("QuantidadeAprovada")
        };
       // seta modelo
        tableModel = new DefaultTableModel(null, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (mode.equals("view")) {
                    return false;
                } else {
                    if (column != 3 && column != 4){
                        return false;
                    }
                    return true;
                }
            }
        };
        // adiciona linhas
        pedidosProdutos.forEach(pp -> {
            Object[] data = {pp.getId(), pp.getProduto().getNome(), pp.getQuantidade(), pp.getQuantidadeAprovada()};
            tableModel.addRow(data);
        });

        // inicializa
        tabela.setModel(tableModel);
        
        TableColumn quantidadeCol = tabela.getColumnModel().getColumn(3);
        JComboBox cquantidade = new JComboBox();
        for(int i = 0; i <= 15; i++) {
            cquantidade.addItem(i);
        }
        quantidadeCol.setCellEditor(new DefaultCellEditor(cquantidade));
      
        tabela.getModel().addTableModelListener((TableModelEvent e) -> {
            // edita quantidade produto do pedido
            if (!tabela.getSelectionModel().isSelectionEmpty()) {
                String newQtd = Methods.selectedTableItemValue(tabela, 3);
                String idTable = Methods.selectedTableItemId(tabela);
                for (int i = 0; i < pedidosProdutos.size(); i++) {
                    PedidoProduto pp = pedidosProdutos.get(i);
                    int idModel = pp.getId();
                    if (idTable.equals(""+idModel)) {
                        pp.setQuantidadeAprovada(Integer.parseInt(newQtd));
                        break;
                    }
                }
            }
        });
    }
    
    private Timer t;
    private void timerTest(String action) {
        
        t = new Timer(500, (ActionEvent e) -> {
            Dialogs.hideLoadPopup(bg);
            self.dispose();
            
            switch (action) {
                case "finalizar": 
                    // finaliza o pedido (colocá-o no modo de Aguardando entrega)
                    if (pedidosProdutos.size() > 0) {
                        pedido.setStatus(Methods.getTranslation("AguardandoEntrega"));
                        pedidoDao.finalizar(pedido, Environment.getLoggedUser());
                        pedidosProdutos.forEach(pp -> {
                            pedidoDao.mudaQuantidadeAprovada(pp);
                        });
                        JOptionPane.showMessageDialog(null, Methods.getTranslation("OkItemAguardandoRetirada"));
                    }
                    break;
                case "negar":
                    // nega o pedido
                    if (pedidosProdutos.size() > 0) {
                        pedido.setStatus(Methods.getTranslation("Negado"));
                        pedidosProdutos.forEach(pp -> {
                            pp.setQuantidadeAprovada(0);
                            pedidoDao.mudaQuantidadeAprovada(pp);
                        });
                        pedidoDao.finalizar(pedido, Environment.getLoggedUser());
                    }
                    JOptionPane.showMessageDialog(null, Methods.getTranslation("OkPedidoNegado"));
                    break;
            }
            
            Navigation.updateLayout("", new Properties());
            Navigation.updateLayout("pedidos", params);
            
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
