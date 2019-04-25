/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.SeusPedidos;

import Config.Environment;
import DAO.PedidoDAO;
import DAO.ProdutoDAO;
import Models.Pedido;
import Models.PedidoProduto;
import Models.Produto;
import Models.Usuario;
import CustomFields.ButtonEditor;
import CustomFields.ButtonRenderer;
import CustomFields.ComboItem;
import CustomFields.SuggestionsBox;
import Utils.Dialogs;
import Utils.AccessibilityManager;
import Utils.Methods;
import Utils.Navigation;
import Utils.Styles;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *  Tela de fazer/ver/editar pedido
 * @author Welison
 */
public class FazerPedido extends Templates.BaseFrame {
    private JPanel bg;
    private JTable tabela;
    private DefaultTableModel tableModel;
    private JScrollPane barraRolagem;
    private JButton btnFinalizar;
    private JPanel paction;
    private JComboBox<String> cproduto;
    private JTextField fproduto;
    private JLabel lproduto;
    private JButton btnAddProduto;
    private JPanel pSuggestions;
    private ArrayList<PedidoProduto> pedidosProdutos;
    private JLabel efinalizar;
    private ProdutoDAO produtoDao;
    private ArrayList<Produto> produtos;
    private PedidoDAO pedidoDao;
    private Pedido pedido;
    private String id;
    
    /**
     * chamada para adição
     * @param params parâmetros de filtro e paginação
     */
    public FazerPedido(Properties params) {
        this.self = this;
        this.mode = "add";
        this.params = params;

        initPage(Methods.getTranslation("FazerPedido"));
    }
   
    /**
     * chamada para adição ou visualização
     * @param id o id do pedido
     * @param mode o mode de exibição (view|edit)
     * @param params parâmetros de filtro e paginação
     */
    public FazerPedido(String id, String mode, Properties params) {
        this.self = this;
        this.mode = mode;
        this.params = params;
        this.id = id;

         switch (this.mode) {
             case "view":
                 initPage(Methods.getTranslation("SeuPedido"));
                 pFilter.setVisible(false);
                 pBottom.setVisible(false);
                 break;
             case "edit":
                 initPage(Methods.getTranslation("EditarSeuPedido"));
                 break;
         }
    }
   
    /**
     * Inicia a tela
     * @param title o título
     */
    private void initPage(String title) {
        
        AccessibilityManager.setAccessibility(this);
        setTitle(title);
        
        // carrega os dados
        pedidoDao = new PedidoDAO();
        pedido = new Pedido();
        produtoDao = new ProdutoDAO();
        produtos = new ArrayList<>();
        pedidosProdutos = new ArrayList<>();
        if (! mode.equals("add")) {
            pedidosProdutos = pedidoDao.selecionarPedidoProdutoPorId(id);
            pedido = pedidosProdutos.get(0).getPedido();
        }
        
        // carrega os elementos e o design da tela
        initComponents();
        Styles.internalFrame(this, 1000, 600);
        createBaseLayout();
        addTopContent(title);
        addCenterContent();
        addFilterContent();
        addBottomContent();
        
        // seta a página pai como página corrente
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                Navigation.currentPage = "seusPedidos";
            }
        });
    }
    
    /**
     * Adiciona o conteúdo da área central (o formulário em si)
     */
    private void addCenterContent() {
        bg = new JPanel();
        bg.setLayout(new BorderLayout());
        bg.setOpaque(false);
     
        barraRolagem = new JScrollPane();
        Styles.defaultScroll(barraRolagem);
        updateCenterContent();
        bg.add(barraRolagem, BorderLayout.CENTER);
        pCenter.add(bg, BorderLayout.CENTER);
    }
    
    /**
     * Atualiza o conteúdo da área central
     */
    private void updateCenterContent() {
        makeTable();
        barraRolagem.getViewport().setView(tabela);
    }
    
    /**
     * Adiciona o conteúdo à area de filtro da tela de conteúdo
     */
    private void addFilterContent() {
        pFilter = new JPanel();
        
        lproduto = new JLabel(Methods.getTranslation("BuscarProduto"));
        Styles.defaultLabel(lproduto, false);
        
        // suggestion box
        pSuggestions = new JPanel();
        fproduto = new JTextField();
        cproduto = new JComboBox();
        new SuggestionsBox(pSuggestions, fproduto, cproduto, 300) {
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
        
        btnAddProduto = new JButton(Methods.getTranslation("Adicionar"));
        Styles.defaultButton(btnAddProduto);
       
        pFilter.add(lproduto);
        pFilter.add(pSuggestions);
        pFilter.add(btnAddProduto);
        
        // click do add produto
        btnAddProduto.addActionListener((ActionEvent e) -> {
            ComboItem selectedProd = (ComboItem)cproduto.getSelectedItem();
            String typedText = fproduto.getText().trim();
            if (selectedProd != null && selectedProd.getDescription().equals(typedText)) {
    
                if (!hasProduct(selectedProd.getId())) {
                    // adiciona produto à lista de pedidos
                    Produto produto = produtoDao.selecionarPorId(selectedProd.getId()+"");
                    if (produto != null && produto.getId() > 0) {
                        Usuario usuario = Environment.getLoggedUser();
                        if (usuario != null && usuario.getId() > 0) {
                            Pedido pedido = new Pedido ("", Methods.getTranslation("Pendente"), usuario);
                            PedidoProduto pp = new PedidoProduto(produto, pedido, 1);
                            pedidosProdutos.add(pp);
                            updateCenterContent();
                            // limpa o campo de busca
                            fproduto.setText("");
                        }
                    }
                    
                    
                }
            }
        });
        
        pFilter.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pFilter.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
        pFilter.setOpaque(false);
        pCenter.add(this.pFilter, BorderLayout.NORTH);
    }
    
    /**
     * Verifica se existe produto com id correspondente na lista de pedidosProdutos
     * @param id o id do produto a ser buscado
     * @return true se existir produto com o dado id
     */
    private boolean hasProduct(int id) {
        for (int i = 0; i < pedidosProdutos.size(); i++) {
            PedidoProduto pp = pedidosProdutos.get(i);
            if (id == pp.getProduto().getId()) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Adiciona o conteúdo à àrea bottom do layout
     */
    private void addBottomContent() {
        btnFinalizar = new JButton(Methods.getTranslation("SalvarPedido"));
        Styles.defaultButton(btnFinalizar);
        
        btnFinalizar.addActionListener((ActionEvent e) -> {
            
            // limpa erro
            efinalizar.setText("");
            
            // validação
            if (pedidosProdutos.size() > 0) {
                Dialogs.showLoadPopup(bg);
                timerTest();
            } else {
                efinalizar.setText(Methods.getTranslation("SelecioneUmProduto"));
            }
            
        });
        
        efinalizar = new JLabel("");
        Styles.errorLabel(efinalizar);
        efinalizar.setPreferredSize(new Dimension(250, 39));
        
        paction = new JPanel();
        paction.setLayout(new FlowLayout(FlowLayout.RIGHT));
        paction.setOpaque(false);
        paction.add(efinalizar);
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
            Methods.getTranslation("Unidade"), 
            Methods.getTranslation("Quantidade"), 
            ""
        };
        if (mode.equals("view")) {
            colunas[4] = Methods.getTranslation("QuantidadeAprovada");
        }
       // seta modelo
        tableModel = new DefaultTableModel(null, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (mode.equals("view")) {
                    return false;
                } else {
                    if(column != 3 && column != 4){
                        return false;
                    }
                }
                return true;
            }
        };
        // adiciona linhas
        pedidosProdutos.forEach(pp -> {
            Object[] data = {pp.getProduto().getId(),pp.getProduto().getNome(),pp.getProduto().getUnidade(),pp.getQuantidade(),""};
            if (! mode.equals("view")) {
                data[4] = Methods.getTranslation("Remover");
            } else {
                data[4] = pp.getQuantidadeAprovada();
            }
            tableModel.addRow(data);
        });
        // inicializa
        tabela.setModel(tableModel);
        
        TableColumn quantidadeCol = tabela.getColumnModel().getColumn(3);
        JComboBox cquantidade = new JComboBox();
        for(int i = 1; i <= 15; i++) {
            cquantidade.addItem(i);
        }
        quantidadeCol.setCellEditor(new DefaultCellEditor(cquantidade));
        
        tabela.getModel().addTableModelListener((TableModelEvent e) -> {
            if (!tabela.getSelectionModel().isSelectionEmpty()) {
                String newQtd = Methods.selectedTableItemValue(tabela, 3);
                String idTable = Methods.selectedTableItemId(tabela);
                // atualiza a quantidade do produto edito na tabela
                for (int i = 0; i < pedidosProdutos.size(); i++) {
                    PedidoProduto pp = pedidosProdutos.get(i);
                    int idModel = pp.getProduto().getId();
                    if (idTable.equals(""+idModel)) {
                        pp.setQuantidade(Integer.parseInt(newQtd));
                        break;
                    }
                }
            }
        });
        
        if(! mode.equals("view")) {
            TableColumn colRemover = tabela.getColumnModel().getColumn(4);
            colRemover.setMaxWidth(40);
            colRemover.setCellRenderer(new ButtonRenderer());
            colRemover.setCellEditor(new ButtonEditor(new JCheckBox()){
                @Override
                public void buttonAction() {
                    String idTable = Methods.selectedTableItemId(tabela);
                    // remove o produto da lista de pedidosProdutos do pedido
                    for (int i = 0; i < pedidosProdutos.size(); i++) {
                        PedidoProduto pp = pedidosProdutos.get(i);
                        int idModel = pp.getProduto().getId();
                        if (idTable.equals(""+idModel)) {
                            pedidosProdutos.remove(pp);
                        }
                    }
                    updateCenterContent();
                }
            });
        }
    }
    
    private Timer t;
    private void timerTest() {
        
        t = new Timer(250, (ActionEvent e) -> {
            Dialogs.hideLoadPopup(bg);
            self.dispose();
            
            switch (this.mode) {
                case "add":
                    // cria pedido e add no banco de dados
                    try {
                        pedido.setSolicitante(Environment.getLoggedUser());
                        int pedidoId = pedidoDao.inserir(pedido);
                        pedido.setId(pedidoId);
                        // insere os produtos ao pedido
                        pedidosProdutos.forEach(pp -> {
                            pp.setPedido(pedido);
                            pedidoDao.inserirProduto(pp);
                        });
                        JOptionPane.showMessageDialog(null, Methods.getTranslation("PedidoEnviadoComSucesso"));
                    } catch(Exception error) {
                        JOptionPane.showMessageDialog(null, Methods.getTranslation("ErroAoTentarEnviar"));
                        throw new RuntimeException("FazerPedido.add: " + error);
                    }
                    break;
                case "edit":
                    // edita o pedido no banco de dados
                    try {
                        if (pedido.getId() > 0) {
                            pedidoDao.deletarPedidoProdutos(pedido.getId());
                            pedidosProdutos.forEach(pp -> {
                                pp.setPedido(pedido);
                                pedidoDao.inserirProduto(pp);
                            });
                            JOptionPane.showMessageDialog(null, Methods.getTranslation("PedidoAtualizadoComSucesso"));
                        }
                    } catch (Exception error) {
                        JOptionPane.showMessageDialog(null, Methods.getTranslation("ErroAoTentarAtualizar"));
                        throw new RuntimeException("FazerPedido.edit: " + error);
                    }
                    break;
            }
            
            Navigation.updateLayout("", new Properties());
            Navigation.updateLayout("seusPedidos", params);
            
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
