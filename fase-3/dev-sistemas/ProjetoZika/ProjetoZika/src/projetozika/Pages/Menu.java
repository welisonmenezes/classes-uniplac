package projetozika.Pages;

import Config.Environment;
import Utils.AccessibilityManager;
import Utils.Methods;
import Utils.Navigation;
import Utils.Styles;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import projetozika.Login;
import projetozika.Main;

/**
 * O menu da aplicação
 * @author Welison
 */
public class Menu extends JPanel {
    
    private final Main main;
    private final Properties params;
    
    /**
     * Cria o menu da aplicação
     * @param main o Frame principal da aplicação
     */
    public Menu(Main main) {
        
        this.main = main;
        this.params = new Properties();
        
        initComponents();
        
        
        this.accessControl();
        this.addHotLinkToMenu();
        
        this.initPage();
    }
    
    private void initPage() {
        // Espaçamentos entre os elementos
        labelEspacoTopo.setPreferredSize(new Dimension(200, 45));
        labelEspacoLogout.setPreferredSize(new Dimension(200, 100));
        
        // Estilos customizados
        Styles.menuButton(bDashboard);
        Styles.menuButton(bPedidos);
        Styles.menuButton(bFornecedores);
        Styles.menuButton(bNotasFiscais);
        Styles.menuButton(bProdutos);
        Styles.menuButton(bUsuarios);
        Styles.menuButton(bSeusPedidos);
        Styles.menuButton(bPerfil);
        Styles.menuButton(bRelatorios);
        
        Styles.iconButton(logout, "/sources/logout.png", "/sources/logout-hover.png");
        Styles.iconButton(bHelp, "/sources/help.png", "/sources/help-hover.png");
        Styles.iconButton(bInfo, "/sources/information.png", "/sources/information-hover.png");
        
        pLinks.setOpaque(false);
        
        // Tradução dos textos
        this.translation();
    }
    
    /**
     * Define quais itens do menu será mostrado de acordo com a permissão do usuári logado
     */
    private void accessControl() {
        // controle de acesso
        if (Environment.getLoggedUser().getPermissao().equals(Methods.getTranslation("Almoxarife"))) {
            bRelatorios.setVisible(false);
            bUsuarios.setVisible(false);
        } else if(Environment.getLoggedUser().getPermissao().equals(Methods.getTranslation("Usuario"))) {
            bRelatorios.setVisible(false);
            bUsuarios.setVisible(false);
            bDashboard.setVisible(false);
            bPedidos.setVisible(false);
            bFornecedores.setVisible(false);
            bNotasFiscais.setVisible(false);
            bProdutos.setVisible(false);
        }
    }
    
    /**
     * adiciona actions e atalhos para os botões do menu
     */
    private void addHotLinkToMenu() {
        
        AccessibilityManager accessibilityManager = new AccessibilityManager(bDashboard,
                "dashboard",
                KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK),
                params);
        
        AccessibilityManager accessibilityManager1 = new AccessibilityManager(bPedidos,
                "pedidos",
                KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK),
                params);
        
        new AccessibilityManager(bFornecedores, 
            "fornecedores",
            KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK),
            params)
                .addContextMenuAddNew("addFornecedor", Methods.getTranslation("AdicionarFornecedor"));
        
        new AccessibilityManager(bNotasFiscais, 
            "notasFiscais",
            KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK),
            params)
                .addContextMenuAddNew("addNotaFiscal", Methods.getTranslation("AdicionarNotaFiscal"));
        
        new AccessibilityManager(bProdutos,
            "produtos",
            KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK),
            params)
                .addContextMenuAddNew("addProduto", Methods.getTranslation("AdicionarProduto"));
        
        new AccessibilityManager(bUsuarios,
            "usuarios",
            KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK),
            params)
                .addContextMenuAddNew("addUsuario", Methods.getTranslation("AdicionarUsuario"));
        
        new AccessibilityManager(bSeusPedidos, 
            "seusPedidos",
            KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK),
            params)
                .addContextMenuAddNew("fazerPedido", Methods.getTranslation("FazerPedido"));
        
        new AccessibilityManager(bPerfil,
            "perfil",
            KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK),
            params) {
                @Override
                public void actionLinkMenuContext() {
                    params.clear();
                    Navigation.updateLayout(page, params);
                    Navigation.updateLayout("editarPerfil", Environment.getLoggedUser().getId()+"", params);
                }
            }
                .addContextMenuAddNew("editarPerfil", Methods.getTranslation("EditarPerfil"));
        
        AccessibilityManager accessibilityManager2 = new AccessibilityManager(bRelatorios, 
                "relatorios",
                KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK),
                params);
        
        AccessibilityManager accessibilityManager3 = new AccessibilityManager(bHelp,
                "ajuda",
                KeyStroke.getKeyStroke("F1"),
                params);
        
        AccessibilityManager accessibilityManager4 = new AccessibilityManager(bInfo,
                "sobre",
                KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK),
                params);
        
        new AccessibilityManager(logout,
            "",
            KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK),
            params) {
                @Override
                public void actionLink() {
                    // logout
                    main.dispose();
                    JFrame login = new Login();
                    login.setVisible(true);
                }
            };

    }
    
    /**
     * Tradução dos textos do menu
     */
    private void translation() {
        bDashboard.setText(Methods.getTranslation("Dashboard"));
        bPedidos.setText(Methods.getTranslation("Pedidos"));
        bFornecedores.setText(Methods.getTranslation("Fornecedores"));
        bNotasFiscais.setText(Methods.getTranslation("NotasFiscais"));
        bProdutos.setText(Methods.getTranslation("Produtos"));
        bUsuarios.setText(Methods.getTranslation("Usuarios"));
        bSeusPedidos.setText(Methods.getTranslation("SeusPedidos"));
        bPerfil.setText(Methods.getTranslation("Perfil"));
        bRelatorios.setText(Methods.getTranslation("Relatorios"));
        logout.setToolTipText(Methods.getTranslation("Sair"));
        bHelp.setToolTipText(Methods.getTranslation("Ajuda"));
        bInfo.setToolTipText(Methods.getTranslation("Sobre"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelEspacoTopo = new javax.swing.JLabel();
        bDashboard = new javax.swing.JButton();
        bSeusPedidos = new javax.swing.JButton();
        bPedidos = new javax.swing.JButton();
        bFornecedores = new javax.swing.JButton();
        bNotasFiscais = new javax.swing.JButton();
        bProdutos = new javax.swing.JButton();
        bUsuarios = new javax.swing.JButton();
        bPerfil = new javax.swing.JButton();
        bRelatorios = new javax.swing.JButton();
        labelEspacoLogout = new javax.swing.JLabel();
        pLinks = new javax.swing.JPanel();
        bInfo = new javax.swing.JButton();
        bHelp = new javax.swing.JButton();
        logout = new javax.swing.JButton();

        setBackground(new java.awt.Color(24, 24, 24));
        setPreferredSize(new java.awt.Dimension(250, 50));
        add(labelEspacoTopo);

        bDashboard.setText("Dashboard");
        bDashboard.setName("dashboard"); // NOI18N
        add(bDashboard);

        bSeusPedidos.setText("Seus Pedidos");
        bSeusPedidos.setName("seusPedidos"); // NOI18N
        add(bSeusPedidos);

        bPedidos.setText("Pedidos");
        bPedidos.setName("pedidos"); // NOI18N
        add(bPedidos);

        bFornecedores.setText("Fornecedores");
        bFornecedores.setName("fornecedores"); // NOI18N
        add(bFornecedores);

        bNotasFiscais.setText("Notas Fiscais");
        bNotasFiscais.setName("notasFiscais"); // NOI18N
        add(bNotasFiscais);

        bProdutos.setText("Produtos");
        bProdutos.setName("produtos"); // NOI18N
        add(bProdutos);

        bUsuarios.setText("Usuários");
        bUsuarios.setName("usuarios"); // NOI18N
        add(bUsuarios);

        bPerfil.setText("Perfil");
        bPerfil.setName("perfil"); // NOI18N
        add(bPerfil);

        bRelatorios.setText("Relatórios");
        bRelatorios.setName("relatorios"); // NOI18N
        add(bRelatorios);
        add(labelEspacoLogout);

        bInfo.setName("information"); // NOI18N
        pLinks.add(bInfo);

        bHelp.setName("help"); // NOI18N
        pLinks.add(bHelp);

        logout.setName("sair"); // NOI18N
        pLinks.add(logout);

        add(pLinks);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bDashboard;
    private javax.swing.JButton bFornecedores;
    private javax.swing.JButton bHelp;
    private javax.swing.JButton bInfo;
    private javax.swing.JButton bNotasFiscais;
    private javax.swing.JButton bPedidos;
    private javax.swing.JButton bPerfil;
    private javax.swing.JButton bProdutos;
    private javax.swing.JButton bRelatorios;
    private javax.swing.JButton bSeusPedidos;
    private javax.swing.JButton bUsuarios;
    private javax.swing.JLabel labelEspacoLogout;
    private javax.swing.JLabel labelEspacoTopo;
    private javax.swing.JButton logout;
    private javax.swing.JPanel pLinks;
    // End of variables declaration//GEN-END:variables
}
