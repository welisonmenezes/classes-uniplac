package projetozika;

import Utils.AccessibilityManager;
import Utils.MenuTray;
import projetozika.Pages.Menu;
import Utils.Methods;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A tela principal da aplicação
 * @author Welison
 */
public class Main extends JFrame {
    
    private JPanel jBG; // container geral
    private JPanel jBODY; // container do conteúdo
    private JPanel jSIDE; // container do menu
    public static JPanel menu; // o menu
    public final MenuTray menuTray;
    
    /**
     * Carrega a tela principal
     */
    public Main() {
        initComponents();
        menu = new Menu(this);
        initLayout();
        
        AccessibilityManager.setAccessibility(getInstance());
        
        translation();
        
        menuTray = new MenuTray(this);
        menuTray.CreateMenuTray();
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                menuTray.optionsClosing();
            }
        });
    }
    
    /**
     * retorna a instância atual
     * @return a instância atual do JFrame
     */
    private JFrame getInstance() {
        return this;
    }
    
    /**
     * Resolva as traduções
     */
    private void translation() {
        setTitle(Methods.getTranslation("ProjetoZika"));
    }
    
    /**
     * Inicializa o layout da aplicação baseado em BorderLayout
     * a aplicação recebera um conteiner geral, um container para o menu e um para o conteúdo
     */
    private void initLayout() {
        
        // add icon
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/sources/saturn.png")));
        
        // seta o tamanho do frame
        Methods.makeComponentFullSize(this);
        
        // inicializa os panels (containers)
        jBG = new JPanel();
        jBODY = new JPanel();
        jSIDE = new JPanel();
        
        // seta os containers base da aplicação
        Methods.setJBg(jBG);
        Methods.setJBody(jBODY);
        Methods.setJMenu(jSIDE);
        
        this.setLayout(new BorderLayout());
        this.add(jBG);
    
        jBG.setLayout(new BorderLayout());
        jBODY.setLayout(new BorderLayout());
        jSIDE.setLayout(new BorderLayout());
        
        jSIDE.add(menu, BorderLayout.CENTER);
        
        jBG.add(jSIDE, BorderLayout.WEST);
        jBG.add(jBODY, BorderLayout.CENTER);

        Methods.setJBody(jBODY);
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
        setTitle("Projeto Zika");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 714, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
