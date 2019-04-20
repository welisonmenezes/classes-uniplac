package Utils;

import static Utils.Methods.setEnableRecursively;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Popups genéricos da aplicação
 * @author Welison
 */
public class Dialogs {
    private static JFrame dialog;
    
    /**
     * Mostra o popup de loading...
     * @param context o JPanel ou JFrame a partir de onde o popup será chamado
     */
    public static void showLoadPopup(JComponent context) {
        setEnableRecursively(Methods.getJBg(), false);
        setEnableRecursively(context, false);
        dialog = new JFrame();
        dialog.setAlwaysOnTop(true);
        dialog.setDefaultCloseOperation(JDialog .DISPOSE_ON_CLOSE);
        dialog.setSize(200, 200);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.requestFocus();
        dialog.setVisible(false);
	dialog.setUndecorated(true);
	dialog.setVisible(true);
        
        // adiciona gif animado
        JLabel lImage = new JLabel();
        lImage.setIcon(new ImageIcon(context.getClass().getResource("/sources/ajax-loader.gif")));
        lImage.setText("");
        lImage.setBorder(BorderFactory.createEmptyBorder(75, 0, 0, 0));
        
        JPanel dialogPanel = new JPanel();
        dialogPanel.setBackground(new Color(24,24,24));
        dialogPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        dialogPanel.add(lImage);
        
        dialog.add(dialogPanel);
    }
    
    /**
     * Esconde o popup de loading...
     * @param context o JPanel ou JFrame a partir de onde o popup foi chamado
     */
    public static void hideLoadPopup(JComponent context) {
        setEnableRecursively(Methods.getJBg(), true);
        setEnableRecursively(context, true);
        dialog.dispose();
    }
}
