package Utils;

import java.awt.AWTKeyStroke;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JRootPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import projetozika.Main;

/**
 * Gerencia acessibiliade da aplicação (o que inclui links e atalhos)
 * @author welison
 */
public class AccessibilityManager {
    
    private final Properties params;
    public final String page;
    public String subpage;
    private final JButton button;
    private final String action;
    private final KeyStroke keyStroke;
    
    /**
     * Gerencia acessibiliade da aplicação (o que inclui links e atalhos)
     * @param button o botão a receber actions e atalhos
     * @param page a pagina para a qual o botão navegará
     * @param keyStroke o atalho
     * @param params  parametros de navegação e filtro
     */
    public AccessibilityManager(JButton button, String page, KeyStroke keyStroke, Properties params) {
        this.button = button;
        this.action = button.getText();
        this.page = page;
        this.keyStroke = keyStroke;
        this.params = params;
        
        addHotLink();
    }
    
    /**
     * adiciona um action e um atalho para o botão
     */
    private void addHotLink() {
        Action buttonAction;
        buttonAction = new AbstractAction(action) {
            @Override
            public void actionPerformed(ActionEvent evt) {
                actionLink();
            }
        };
        button.setAction(buttonAction);
        button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, "");
        button.getActionMap().put("", buttonAction);
    }
    
    /**
     * callback default pra o action do botão (este poderá ser sobreescrito)
     */
    public void actionLink() {
        params.clear();
        Navigation.updateLayout(page, params);
    }
    
    /**
     * callback default para o action do menu de contexto (este poderá ser sobreescrito)
     */
    public void actionLinkMenuContext() {
        params.clear();
        Navigation.updateLayout(page, params);
        Navigation.updateLayout(subpage, params);
    }
    
    /**
     * Adiciona menu de contexto no botão
     * @param subpage a página para a qual o item de menu de contexto navegará
     * @param title o texto do menu de contexto
     */
    public void addContextMenuAddNew(String subpage, String title) {
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem jMenuItemAddNew = new JMenuItem();
        jMenuItemAddNew.setText(title);
        
        jMenuItemAddNew.addActionListener((ActionEvent e) -> {
            this.subpage = subpage;
            actionLinkMenuContext();
        });
        
        jPopupMenu.add(jMenuItemAddNew);
        
        button.addMouseListener( new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            // Se o botão direito do mouse foi pressionado
            if (e.getButton() == MouseEvent.BUTTON3){
                // Exibe o popup menu na posição do mouse.
                jPopupMenu.show(button, e.getX(), e.getY());
            }
          }
        });
    }
  
    /**
     * Seta, para um dado JFrame, a escuta dos eventos VK_ESCAPE e VK_ENTER
     * @param frame o JFrame que receberá o listener
     */
    public static void setAccessibility(final JFrame frame) {
        JRootPane meurootpane = frame.getRootPane();
        
        // add listener para o VK_ESCAPE. Quando acionado fecha o frame
        meurootpane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESCAPE");
        meurootpane.getRootPane().getActionMap().put("ESCAPE", new AbstractAction("ESCAPE") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (frame instanceof Main) {
                    ((Main)frame).menuTray.optionsClosing();
                } else {
                    frame.dispose();
                }
            }
        });
        
        // add listener para o VK_ENTER.
        // Quando acionado simula o tab. Se o elemento focado for um botão, simula o click
        meurootpane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ENTER");
        meurootpane.getRootPane().getActionMap().put("ENTER", new AbstractAction("ENTER") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(frame.getFocusOwner() instanceof JButton) {
                    JButton btn = (JButton) frame.getFocusOwner();
                    btn.doClick();
                } else {
                    frame.getFocusOwner().transferFocus();
                }
            }
        });
    }
    
    /**
     * Navega entre as tabs por ctrl+tab
     * @param tabbedPane o JTabbedPane
     */
    public static void setupTabTraversalKeys(JTabbedPane tabbedPane) {
        KeyStroke ctrlTab = KeyStroke.getKeyStroke("ctrl TAB");
        KeyStroke ctrlShiftTab = KeyStroke.getKeyStroke("ctrl shift TAB");

        // Remove ctrl-tab from normal focus traversal
        Set<AWTKeyStroke> forwardKeys = new HashSet<>(tabbedPane.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        forwardKeys.remove(ctrlTab);
        tabbedPane.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, forwardKeys);

        // Remove ctrl-shift-tab from normal focus traversal
        Set<AWTKeyStroke> backwardKeys = new HashSet<>(tabbedPane.getFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS));
        backwardKeys.remove(ctrlShiftTab);
        tabbedPane.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, backwardKeys);

        // Add keys to the tab's input map
        InputMap inputMap = tabbedPane.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(ctrlTab, "navigateNext");
        inputMap.put(ctrlShiftTab, "navigatePrevious");
    }
}
