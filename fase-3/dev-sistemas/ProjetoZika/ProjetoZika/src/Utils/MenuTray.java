package Utils;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import projetozika.Login;

/**
 * Cria o menu SystemTray
 * @author welison
 */
public class MenuTray {
    private TrayIcon trayIcon;
    private SystemTray tray;
    private MenuItem openItem;
    private MenuItem exitItem;
    private MenuItem sobreItem;
    private MenuItem logoutItem;
    private final JFrame context;
    
    /**
     * Cria o menu SystemTray
     * @param frame o JFrame principal da aplicação
     */
    public MenuTray(JFrame frame) {
        context = frame;
    }
    
    /**
     * Cria o menu e adiciona os items no SystemTray
     */
    public void CreateMenuTray() {
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
    
    /**
     * Adiciona o ícone no SystemTray
     */
    public void addTrayIcon() {
        try {
            tray.add(trayIcon);
            context.setState(JFrame.ICONIFIED);
        } catch (AWTException error) {
            Methods.getLogger().error("MenuTray.addTrayIcon: " + error);
            System.out.println("TrayIcon could not be added.");
        }
    }
    
    /**
     * Adiciona actions para os item do menu
     */
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
            context.dispose();
            JFrame login = new Login();
            login.setVisible(true);
        });
    }
    
    /**
     * Reabre a aplicação e remove o ícon do SystemTray
     */
    private void reopenApp() {
        context.setVisible(true);
        context.setState(JFrame.NORMAL);
        tray.remove(trayIcon);
    }
    
    /**
     * Mostra opções para o usuário quando este tenta sair da aplicação
     */
    public void optionsClosing() {
        String[] options = new String[] {
            Methods.getTranslation("Encerrar"),
            Methods.getTranslation("Ficar"),
            Methods.getTranslation("Minimizar")
        };
        int option = JOptionPane.showOptionDialog(null, Methods.getTranslation("OQueVoceDesejaFazer?"), "",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        switch (option) {
            case 0: // sair
                System.exit(0);
                break;
            case 1:// ficar
                context.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                break;
            case 2: // minimizar
                context.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                this.addTrayIcon();
                context.setVisible(false);
                break;
        }
    }
}
