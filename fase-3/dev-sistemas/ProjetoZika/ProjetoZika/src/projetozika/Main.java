/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika;

import Utils.Dialogs;
import projetozika.Pages.Menu;
import Utils.Methods;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.CheckboxMenuItem;
import java.awt.Frame;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.Timer;
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
    
    /**
     * Carrega a tela principal
     */
    public Main() {
        initComponents();
        menu = new Menu(this);
        initLayout();
        
        Methods.setAccessibility(this);
        
        translation();
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                int opcion = JOptionPane.showConfirmDialog(null, Methods.getTranslation("DesejaRealmenteSair?"), "Aviso", JOptionPane.YES_NO_OPTION);
                if (opcion != 0) {
                    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                } else {
                    setDefaultCloseOperation(EXIT_ON_CLOSE);
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {
                System.out.println("xxxx");
                setVisible(false);
            }
        });
        
        this.exemploMenuContexto();
        
        //this.timerTest();
    }
    
    public void exemploMenuTray() {
        //Check the SystemTray is supported
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon =
                new TrayIcon(new ImageIcon(this.getClass().getResource("/sources/cancel.png")).getImage());
        final SystemTray tray = SystemTray.getSystemTray();
       
        // Create a pop-up menu components
        MenuItem aboutItem = new MenuItem("About");
        CheckboxMenuItem cb1 = new CheckboxMenuItem("Set auto size");
        CheckboxMenuItem cb2 = new CheckboxMenuItem("Set tooltip");
        Menu displayMenu = new Menu(this);
        MenuItem errorItem = new MenuItem("Error");
        MenuItem warningItem = new MenuItem("Warning");
        MenuItem infoItem = new MenuItem("Info");
        MenuItem noneItem = new MenuItem("None");
        MenuItem exitItem = new MenuItem("Exit");
       
        //Add components to pop-up menu
        popup.add(aboutItem);
        popup.addSeparator();
        popup.add(cb1);
        popup.add(cb2);
        popup.addSeparator();
        popup.add(exitItem);
       
        trayIcon.setPopupMenu(popup);
       
        try {
            tray.add(trayIcon);
            System.out.println("xxxxaa");
            this.setState(this.ICONIFIED);
            //int state = getExtendedState();
            //state = state & ~Frame.ICONIFIED;
           // this.setExtendedState(state);
           // setVisible(true);
            //this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            //this.dispose();
            //System.exit(0);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
        }
        
    }
    
    private Timer t;
    private void timerTest() {
        
        t = new Timer(250, (ActionEvent e) -> {
            this.exemploMenuTray();
            t.stop();
        });
        t.start();
    }
    
    
    private void exemploMenuContexto() {
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem jMenuItemAlterar = new JMenuItem();
        jMenuItemAlterar.setText("Alterar");
        
        jMenuItemAlterar.addActionListener( new ActionListener() {
          // Importe a classe java.awt.event.ActionEvent
          public void actionPerformed(ActionEvent e) { 
              System.out.println("teste menu de contexto");
          }
        });
        
        jPopupMenu.add(jMenuItemAlterar);
        
        jSIDE.addMouseListener( new MouseAdapter() {
          //Importe a classe java.awt.event.MouseEvent
          public void mouseClicked(MouseEvent e) {
            // Se o botão direito do mouse foi pressionado
            if (e.getButton() == MouseEvent.BUTTON3){
              // Exibe o popup menu na posição do mouse.
                jPopupMenu.show(jSIDE, e.getX(), e.getY());
                System.out.println("Clicado vacilão");
            }
          }
        });
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
