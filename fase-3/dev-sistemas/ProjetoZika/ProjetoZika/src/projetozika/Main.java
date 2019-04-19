/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika;

import Utils.AccessibilityManager;
import projetozika.Pages.Menu;
import Utils.Methods;
import Utils.Navigation;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 * A tela principal da aplicação
 * @author Welison
 */
public class Main extends javax.swing.JFrame {
    
    private JPanel jBG; // container geral
    private JPanel jBODY; // container do conteúdo
    private JPanel jSIDE; // container do menu
    public static JPanel menu; // o menu
    private SystemTray tray;
    private TrayIcon trayIcon;
    private MenuItem openItem;
    private MenuItem exitItem;
    private MenuItem sobreItem;
    private MenuItem logoutItem;
    
    /**
     * Carrega a tela principal
     */
    public Main() {
        initComponents();
        menu = new Menu(this);
        initLayout();
        
        AccessibilityManager.setAccessibility(this);
        
        translation();
        
        CreateMenuTray();
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                String[] options = new String[] {
                    Methods.getTranslation("Encerrar"),
                    Methods.getTranslation("Ficar"),
                    Methods.getTranslation("Minimizar")
                };
                int option = JOptionPane.showOptionDialog(null, Methods.getTranslation("OQueVoceDesejaFazer?"), "",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                
                switch (option) {
                    case 0: // sair
                        setDefaultCloseOperation(EXIT_ON_CLOSE);
                        break;
                    case 1:// ficar
                        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                        break;
                    case 2: // minimizar
                        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                        addTrayIcon();
                        break;
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {
                setVisible(false);
            }
        });
    }
    
    private void CreateMenuTray() {
        // verifica se tem suporte
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        
        // cria o icon tray menu
        final PopupMenu popup = new PopupMenu();
        trayIcon = new TrayIcon(new ImageIcon(this.getClass().getResource("/sources/saturn-small.png")).getImage());
        tray = SystemTray.getSystemTray();
       
        // cria a pop-up menu components
        openItem = new MenuItem(Methods.getTranslation("AbrirProjetoZika"));
        exitItem = new MenuItem(Methods.getTranslation("EncerrarProjetoZika"));
        sobreItem = new MenuItem(Methods.getTranslation("SobreProjetoZika"));
        logoutItem = new MenuItem(Methods.getTranslation("FazerLogout"));
        
        addActionToMenuItems();
       
        // add itens no pop-up menu
        popup.add(openItem);
        popup.add(exitItem);
        popup.add(sobreItem);
        popup.add(logoutItem);
        
        // add pop-up menu no tray
        trayIcon.setPopupMenu(popup);
    }
    
    private void addTrayIcon() {
        try {
            tray.add(trayIcon);
            this.setState(ICONIFIED);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
        }
    }
    
    private void addActionToMenuItems() {
        // abrir
        openItem.addActionListener((ActionEvent actionEvent) -> {
            reopenApp();
        });
        // encerrar
        exitItem.addActionListener((ActionEvent actionEvent) -> {
            System.exit(0);
        });
        // sobre
        sobreItem.addActionListener((ActionEvent actionEvent) -> {
            Navigation.updateLayout("sobre", new Properties());
        });
        // logout
        logoutItem.addActionListener((ActionEvent actionEvent) -> {
            reopenApp();
            dispose();
            JFrame login = new Login();
            login.setVisible(true);
        });
    }
    
    private void reopenApp() {
        setVisible(true);
        setState(JFrame.NORMAL);
        tray.remove(trayIcon);
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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
