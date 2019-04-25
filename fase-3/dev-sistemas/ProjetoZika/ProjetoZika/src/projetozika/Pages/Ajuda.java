package projetozika.Pages;

import Templates.BaseFrame;
import Utils.AccessibilityManager;
import Utils.Methods;
import Utils.Navigation;
import Utils.Styles;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Properties;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Tela de ajuda
 * @author welison
 */
public class Ajuda extends BaseFrame {
    private JScrollPane barraRolagem;
    private JPanel wrapText;
    private JLabel textContainer;
    private String textContent;

    /**
     * Creates new form Ajuda
     * @param params Parâmetros para filtro e paginação
     */
    public Ajuda(Properties params) {
        this.params = params;
        initPage();
    }
    
    /**
     * Inicializa a página (Add elementos e estilos)
     */
    private void initPage() {
        
        AccessibilityManager.setAccessibility(this);
        
        initComponents();
        Styles.internalFrame(this, 600, 500);
        createBaseLayout();
        setTitle(Methods.getTranslation("Ajuda"));
        addTopContent(Methods.getTranslation("Ajuda"));
        addCenterContent();
        
        // seta a página pai como página corrente
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                Navigation.currentPage = "";
            }
        });
    }
    
    /**
     * Adiciona conteúdo ao centro da area de conteúdo
     */
    private void addCenterContent() {
        barraRolagem = new JScrollPane();
        Styles.defaultScroll(barraRolagem);
        
        wrapText = new JPanel(new FlowLayout(FlowLayout.LEFT));
        wrapText.setOpaque(false);
        
        if (System.getProperty("user.language").equals("en")) {
            helpContentEN();
        } if (System.getProperty("user.language").equals("es")) {
            helpContentES();
        } else {
            helpContentPT();
        }
        
        
        textContainer = new JLabel("<html>"+textContent+"</html>");
        textContainer.setFont(new java.awt.Font("Tahoma", 0, 16));
        textContainer.setForeground(new java.awt.Color(255, 255, 255));
        
        wrapText.add(textContainer);
        barraRolagem.getViewport().setView(wrapText);
        pCenter.add(barraRolagem, BorderLayout.CENTER);
    }
    
    /**
     * Conteúdo de página em inglês
     */
    private void helpContentEN() {
        textContent = "<h2>Quick Links</h2>"
                + "<ul>"
                + "<li>Dashboard: <code>CTRL + D</code></li>"
                + "<li>Your requests: <code>CTRL + Y</code></li>"
                + "<li>Requests: <code>CTRL + O</code></li>"
                + "<li>Providers: <code>CTRL + F</code></li>"
                + "<li>Invoices: <code>CTRL + I</code></li>"
                + "<li>Products: <code>CTRL + P</code></li>"
                + "<li>Users: <code>CTRL + U</code></li>"
                + "<li>Profile: <code>CTRL + M</code></li>"
                + "<li>Reports: <code>CTRL + R</code></li>"
                + "<li>About: <code>CTRL + A</code></li>"
                + "<li>Help: <code>F1</code></li>"
                + "<li>Logout: <code>CTRL + L</code> ou <code>ESC</code></li>"
                + "<li>Add new: <code>CTRL + N</code></li>"
                + "</ul>";
    }
    
    /**
     * Conteúdo de página em inglês
     */
    private void helpContentES() {
        textContent = "<h2>Accesos rápidos</h2>"
                + "<ul>"
                + "<li>Dashboard: <code>CTRL + D</code></li>"
                + "<li>Sus pedidos: <code>CTRL + Y</code></li>"
                + "<li>Aplicaciones: <code>CTRL + O</code></li>"
                + "<li>Proveedores: <code>CTRL + F</code></li>"
                + "<li>Notas fiscales: <code>CTRL + I</code></li>"
                + "<li>Productos: <code>CTRL + P</code></li>"
                + "<li>Usuarios: <code>CTRL + U</code></li>"
                + "<li>Perfil: <code>CTRL + M</code></li>"
                + "<li>Informes: <code>CTRL + R</code></li>"
                + "<li>Acerca de: <code>CTRL + A</code></li>"
                + "<li>Ayuda: <code>F1</code></li>"
                + "<li>Salir: <code>CTRL + L</code> ou <code>ESC</code></li>"
                + "<li>Añadir nuevo: <code>CTRL + N</code></li>"
                + "</ul>";
    }
       
    /**
     * Conteúdo da página em português
     */
    private void helpContentPT() {
        textContent = "<h2>Acessos Rápidos</h2>"
                + "<ul>"
                + "<li>Dashboard: <code>CTRL + D</code></li>"
                + "<li>Seus pedidos: <code>CTRL + Y</code></li>"
                + "<li>Pedidos: <code>CTRL + O</code></li>"
                + "<li>Fornecedores: <code>CTRL + F</code></li>"
                + "<li>Notas fiscais: <code>CTRL + I</code></li>"
                + "<li>Produtos: <code>CTRL + P</code></li>"
                + "<li>Usuários: <code>CTRL + U</code></li>"
                + "<li>Perfil: <code>CTRL + M</code></li>"
                + "<li>Relatórios: <code>CTRL + R</code></li>"
                + "<li>Sobre: <code>CTRL + A</code></li>"
                + "<li>Ajuda: <code>F1</code></li>"
                + "<li>Sair: <code>CTRL + L</code> ou <code>ESC</code></li>"
                + "<li>Adicionar Novo: <code>CTRL + N</code></li>"
                + "</ul>";
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
