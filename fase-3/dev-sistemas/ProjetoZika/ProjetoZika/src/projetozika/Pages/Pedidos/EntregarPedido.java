package projetozika.Pages.Pedidos;

import DAO.PedidoDAO;
import DAO.UsuarioDAO;
import Models.Pedido;
import Models.PedidoProduto;
import Models.Usuario;
import Templates.BaseFrame;
import Utils.Dialogs;
import Utils.AccessibilityManager;
import Utils.Methods;
import Utils.Navigation;
import Utils.Styles;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 * Tela de entregar pedido
 * @author Welison
 */
public class EntregarPedido extends BaseFrame {

    private JTable tabela;
    private DefaultTableModel tableModel;
    private JLabel title;
    private JScrollPane barraRolagem;
    private JPanel ptable;
    private JPanel pform;
    private JLabel ltitle;
    private JLabel llogin;
    private JTextField flogin;
    private JLabel lsenha;
    private JPasswordField fsenha;
    private JButton btnConfirm;
    private JLabel linfo;
    private final ArrayList<PedidoProduto> pedidosProdutos;
    private final PedidoDAO pedidoDao;
    private final UsuarioDAO usuarioDao;
    private Usuario usuario;
    private Pedido pedido;
    private Usuario solicitante;
    
    /**
     * chamada pra entregar pedido
     * @param id o id do pedido
     * @param mode o modo de visualização (edit)
     * @param params parâmetros de filtro e paginação
     */
    public EntregarPedido(String id, String mode, Properties params) {
        this.self = getInstance();
        this.mode = mode;
        this.params = params;
        
        pedidoDao = new PedidoDAO();
        pedidosProdutos = pedidoDao.selecionarPorId(id);
        usuarioDao = new UsuarioDAO();
        if (pedidosProdutos.size() > 0) {
            pedido = pedidosProdutos.get(0).getPedido();
            usuario = pedidosProdutos.get(0).getPedido().getSolicitante();
        }
        
        initPage(Methods.getTranslation("ConfirmacaoDeRetirada"));
    }
    
    /**
     * Inicia a tela
     * @param title o título
     */
    private void initPage(String title) {
        
        AccessibilityManager.setAccessibility(this);
        setTitle(title);
        
        // carrega os elementos e o design da tela
        initComponents();
        Styles.internalFrame(this, 1000, 600);
        createBaseLayout();
        addTopContent(title);
        addCenterContent();
        
        // seta a página pai como página corrente
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                Navigation.currentPage = "pedidos";
            }
        });
        
    }
    
    /**
     * Adiciona o conteúdo da área central (o formulário em si)
     */
    private void addCenterContent() {
        ptable = new JPanel();
        ptable.setLayout(new BorderLayout());
        ptable.setOpaque(false);
        ptable.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        
        title = new JLabel(Methods.getTranslation("DetalhesDoPedido"));
        Styles.defaultLabel(title);
        ptable.add(title, BorderLayout.NORTH);
        
        makeTable();
        barraRolagem = new JScrollPane(tabela);
        Styles.defaultScroll(barraRolagem);
        ptable.add(barraRolagem, BorderLayout.CENTER);
        
        pform = new JPanel();
        pform.setLayout(new AbsoluteLayout());
        pform.setBackground(new Color(24,24,24));
        pform.setPreferredSize(new Dimension(400, 350));
        
        ltitle = new JLabel(Methods.getTranslation("UsuarioESenhaDoSolicitante"));
        Styles.defaultLabel(ltitle);
        ltitle.setFont(new Font("Tahoma", 0, 14));
        ltitle.setPreferredSize(new Dimension(200, 35));
        pform.add(ltitle, new AbsoluteConstraints(100, 50, -1, -1));
        
        llogin = new JLabel(Methods.getTranslation("Login"));
        Styles.defaultLabel(llogin);
        pform.add(llogin, new AbsoluteConstraints(100, 90, -1, -1));

        flogin = new JTextField();
        Styles.defaultField(flogin);
        pform.add(flogin, new AbsoluteConstraints(100, 130, -1, -1));
        
        lsenha = new JLabel(Methods.getTranslation("Senha"));
        Styles.defaultLabel(lsenha);
        pform.add(lsenha, new AbsoluteConstraints(100, 180, -1, -1));

        fsenha = new JPasswordField();
        Styles.defaultField(fsenha);
        pform.add(fsenha, new AbsoluteConstraints(100, 220, -1, -1));
        
        linfo = new JLabel();
        Styles.errorLabel(linfo);
        linfo.setPreferredSize(new Dimension(200, 20));
        pform.add(linfo, new AbsoluteConstraints(100, 260, -1, -1));
        
        btnConfirm = new JButton(Methods.getTranslation("ConfirmarRetirada"));
        Styles.defaultButton(btnConfirm);
        btnConfirm.setPreferredSize(new Dimension(200, 35));
        pform.add(btnConfirm, new AbsoluteConstraints(100, 290, -1, -1));
        
        btnConfirm.addActionListener((ActionEvent e) -> {
            
            // reseta erro
            linfo.setText("");
            
            // validação do solicitante
            if (pedidosProdutos.size() > 0) {
                String login = flogin.getText().trim();
                String password = new String(fsenha.getPassword());
                
                solicitante = usuarioDao.selecionarAposLogin(login, password);
                if (solicitante != null && solicitante.getId() > 0) {
                    if (usuario.getId() == solicitante.getId()) {
                        Dialogs.showLoadPopup(pCenter);
                        timerTest();
                    } else {
                        linfo.setText(Methods.getTranslation("OPedidoNaoEDesteUsuario"));
                    }
                } else {
                    linfo.setText(Methods.getTranslation("LoginOuSenhaInvalidos"));
                }
            }

        });
        
        pCenter.add(pform, BorderLayout.WEST);
        pCenter.add(ptable, BorderLayout.CENTER);
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
            Methods.getTranslation("Produto"),
            Methods.getTranslation("Unidade"),
            Methods.getTranslation("QuantidadeSolicitada"), 
            Methods.getTranslation("QuantidadeAprovada")
        };
       // seta modelo
        tableModel = new DefaultTableModel(null, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
        };
        // adiciona linhas
        pedidosProdutos.forEach(pp -> {
            Object[] data = {pp.getProduto().getNome(),pp.getProduto().getUnidade(), pp.getQuantidade(), pp.getQuantidadeAprovada(), pp.getPedido().getStatus()};
            tableModel.addRow(data);
        });
        // inicializa
        tabela.setModel(tableModel);
    }
    
    private Timer t;
    private void timerTest() {
        
        t = new Timer(250, (ActionEvent e) -> {
            Dialogs.hideLoadPopup(pCenter);
            self.dispose();
            
            // finaliza o pedido na base de dados
            pedido.setStatus(Methods.getTranslation("Finalizado"));
            pedidoDao.mudaStatus(pedido);
            JOptionPane.showMessageDialog(null, Methods.getTranslation("RetiradaRealizadaComSucesso"));
            
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
