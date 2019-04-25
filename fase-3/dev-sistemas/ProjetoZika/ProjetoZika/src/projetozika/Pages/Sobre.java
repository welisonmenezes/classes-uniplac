package projetozika.Pages;

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
 * Cira a página Sobre
 * @author welison
 */
public class Sobre extends Templates.BaseFrame {
    private JScrollPane barraRolagem;
    private JPanel wrapText;
    private JLabel textContainer;
    private String textContent;

    /**
     * Creates new form Sobre
     * @param params Parâmetros para filtro e paginação
     */
    public Sobre(Properties params) {
        this.params = params;
        initPage();
    }
    
    /**
     * Inicializa a página (Add elementos e estilos)
     */
    private void initPage() {
        
        AccessibilityManager.setAccessibility(this);
        
        initComponents();
        Styles.internalFrame(this, 650, 500);
        createBaseLayout();
        setTitle(Methods.getTranslation("Sobre"));
        addTopContent(Methods.getTranslation("Sobre"));
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
            aboutContentEN();
        } if (System.getProperty("user.language").equals("es")) {
            aboutContentES();
        } else {
            aboutContentPT();
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
    private void aboutContentEN() {
        textContent = "<h2>ProjetoZika en-us</h2>"
                + "<br>"
                + "<p>"
                + "Donec faucibus nec orci ut sollicitudin. Proin fermentum nec augue nec"
                + "<br>luctus. Vivamus elementum molestie tortor rhoncus lobortis."
                + "</p>"
                + "<br>"
                + "<p>"
                + "Cras convallis ultricies diam, consequat consequat sapien condimentum a."
                + "<br>Phasellus nibh leo, luctus id tincidunt et, pharetra pharetra turpis."
                + "</p>"
                + "<br>"
                + "<p>"
                + "Quisque pretium tincidunt bibendum. Fusce malesuada diam id urna tristique,"
                + "<br>nec imperdiet dui malesuada. Morbi sodales mattis porta. Ut cursus."
                + "<br>Rutrum commodo Mauris tincidunt maximus suscipit. Aliquam erat volutpat."
                + "</p>"
                + "<br>"
                + "<p>"
                + "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
                + "<br>Donec faucibus nec orci ut sollicitudin."
                + "</p>";
    }
    
    /**
     * Conteúdo de página em inglês
     */
    private void aboutContentES() {
        textContent = "<h2>ProjetoZika es-es</h2>"
                + "<br>"
                + "<p>"
                + "Donec faucibus nec orci ut sollicitudin. Proin fermentum nec augue nec"
                + "<br>luctus. Vivamus elementum molestie tortor rhoncus lobortis."
                + "</p>"
                + "<br>"
                + "<p>"
                + "Cras convallis ultricies diam, consequat consequat sapien condimentum a."
                + "<br>Phasellus nibh leo, luctus id tincidunt et, pharetra pharetra turpis."
                + "</p>"
                + "<br>"
                + "<p>"
                + "Quisque pretium tincidunt bibendum. Fusce malesuada diam id urna tristique,"
                + "<br>nec imperdiet dui malesuada. Morbi sodales mattis porta. Ut cursus."
                + "<br>Rutrum commodo Mauris tincidunt maximus suscipit. Aliquam erat volutpat."
                + "</p>"
                + "<br>"
                + "<p>"
                + "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
                + "<br>Donec faucibus nec orci ut sollicitudin."
                + "</p>";
    }
    
    /**
     * Conteúdo de página em português
     */
    private void aboutContentPT() {
        textContent = "<h2>ProjetoZika pt-br</h2>"
                + "<br>"
                + "<p>"
                + "Donec faucibus nec orci ut sollicitudin. Proin fermentum nec augue nec"
                + "<br>luctus. Vivamus elementum molestie tortor rhoncus lobortis."
                + "</p>"
                + "<br>"
                + "<p>"
                + "Cras convallis ultricies diam, consequat consequat sapien condimentum a."
                + "<br>Phasellus nibh leo, luctus id tincidunt et, pharetra pharetra turpis."
                + "</p>"
                + "<br>"
                + "<p>"
                + "Quisque pretium tincidunt bibendum. Fusce malesuada diam id urna tristique,"
                + "<br>nec imperdiet dui malesuada. Morbi sodales mattis porta. Ut cursus."
                + "<br>Rutrum commodo Mauris tincidunt maximus suscipit. Aliquam erat volutpat."
                + "</p>"
                + "<br>"
                + "<p>"
                + "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
                + "<br>Donec faucibus nec orci ut sollicitudin."
                + "</p>";
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
