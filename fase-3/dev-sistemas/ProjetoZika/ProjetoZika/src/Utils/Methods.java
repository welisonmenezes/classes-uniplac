/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

/**
 * Metodos e funcionalidades genéricas da aplicação
 * 
 * @author Welison
 */
public class Methods {
    
    private static JPanel jBody;
    private static JPanel jBg;
    private static JPanel jMenu;
    
    /**
     * Seta altura e largura full a um dado JFrame
     * 
     * @param aFrame o JFrame que será dimensionado
     */
    public static void makeFrameFullSize(JFrame aFrame)
    {
        Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        aFrame.setSize(screenSize.width, screenSize.height);
    }
    
    /**
     * Seta altura e largura full a um dado JPanel
     * 
     * @param aFrame o Jpanel que será dimensionado
     */
    public static void makePanelfullSize(JPanel aFrame)
    {
        Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        aFrame.setSize(screenSize.width, screenSize.height);
    }
    
    /**
     * Seta uma dada altura e uma dada largura a um dado JFrame
     * 
     * @param aFrame o JFrame que será dimensionado
     * @param w a largura desejada
     * @param h a altura desejada
     */
    public static void makePanelRelativeSize(JFrame aFrame, int w, int h)
    {
        Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        aFrame.setSize(w, h);
    }
    
    /**
     * Posiciona um dado JFrame no centro da tela
     * 
     * @param aFrame o JFrame a ser posicionado
     */
    public static void positionFrameInCenter(JFrame aFrame) {
        Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        aFrame.setLocation(screenSize.width/2-aFrame.getSize().width/2, screenSize.height/2-aFrame.getSize().height/2);
    }
    
    /**
     * Seta o container do conteúdo da apalicação
     * 
     * @param jb o JPanel que exibirá o conteúdo root
     */
    public static void setJBody(JPanel jb) {
        jBody = jb;
    }
    
    /**
     * Retorna o container que exibe o conteúdo root
     * 
     * @return o JPanel
     */
    public static JPanel getJBody() {
       return  jBody;
    }
    
    /**
     * Seta o container geral da aplicação
     * 
     * @param jb o JPanel que exibirá o conteúdo root
     */
    public static void setJBg(JPanel jb) {
        jBg = jb;
    }
    
    /**
     * Retorna o container geral da aplicação
     * 
     * @return o JPanel
     */
    public static JPanel getJBg() {
       return  jBg;
    }
    
    /**
     * Seta o container do menu da aplicação
     * 
     * @param jb o JPanel que exibirá o conteúdo root
     */
    public static void setJMenu(JPanel jb) {
        jMenu = jb;
    }
    
    /**
     * Retorna o container do menu da aplicação
     * 
     * @return o JPanel
     */
    public static JPanel getJMenu() {
       return  jMenu;
    }
    
    /**
     * Limpa a area de um dado JPanel
     * 
     * @param jb o JPanel a ser limpo
     */
    public static void clearStage(JPanel jb) {
        jb.removeAll();
        jb.repaint();
        jb.revalidate();
    }
    
    /**
     * Seta os elementos de um dado JComponent como habilitado ou desabilitado
     * 
     * @param el o JComponent a ser habilitado ou desabilitado
     * @param isEnable true pra habilitar ou false pra desabilitar
     */
    public static void setEnableRecursively(JComponent el, boolean isEnable) {
        Component[] comps = el.getComponents();
        if (comps.length > 0) {
            for (Component comp : comps) {
                if (comp instanceof JComponent) {
                    setEnableRecursively((JComponent) comp, isEnable);
                }
                comp.setEnabled(isEnable);
            }
        }
    }
    
    /**
     * Retorna o valor da primeira coluna da linha selecionda de um dado JTable
     * 
     * @param table o JTable cuja valor será retornado
     * @return o valor da primeira coluna da linha selecionada
     */
    public static String selectedTableItemId(JTable table) {
        int row = table.getSelectedRow();
        Object id = table.getValueAt(row, 0);
        return id.toString();
    }
    
    /**
     * Remove a linha selecionada de um dado JTable
     * 
     * @param table o JTable cuja linha selecionada será removida
     * @param tableModel o JTableModel cuja linha selecionada será removida
     */
    public static void removeSelectedTableRow(JTable table, DefaultTableModel tableModel){
        int row = table.getSelectedRow();
        tableModel.removeRow(row);
    }
  
    /**
     * Seta, para um dado JFrame, a escuta dos eventos VK_ESCAPE e VK_ENTER
     * 
     * @param frame o JFrame que receberá o listener
     */
    public static void setAccessibility(final JFrame frame) {
        JRootPane meurootpane = frame.getRootPane();
        
        // add listener para o VK_ESCAPE. Quando acionado fecha o frame
        meurootpane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESCAPE");
        meurootpane.getRootPane().getActionMap().put("ESCAPE", new AbstractAction("ESCAPE") {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        
        // add listener para o VK_ENTER.
        // Quando acionado simula o tab. Se o elemento focado for um botão, simula o click
        meurootpane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ENTER");
        meurootpane.getRootPane().getActionMap().put("ENTER", new AbstractAction("ENTER") {
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
     * Habilita o desabilita os componetes do JBody e JMenu no abrie e fecha deste frame
     */
    public static void disableEnableRootAndMenuPanel(JFrame frame) {
        frame.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent we) {
                Methods.setEnableRecursively(Methods.getJBody(), false);
                Methods.setEnableRecursively(Methods.getJMenu(), false);
            }
            
            public void windowClosed(WindowEvent we) {
                Methods.setEnableRecursively(Methods.getJBody(), true);
                Methods.setEnableRecursively(Methods.getJMenu(), true);
            }
        });
    }
}
