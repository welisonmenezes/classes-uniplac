/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import static javax.swing.BorderFactory.createEmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;

/**
 *  Gerencia os estilos comuns dos elementos da aplicação
 * 
 * @author Welison
 */
public class Styles {
    
    /**
     * Estiliza os botões default
     * 
     * @param btn o JButton a ser estilizado
     */
    public static void defaultButton(JButton btn) {
        // styles
        btn.setFont(new Font("Tahoma", 0, 12));
        btn.setForeground(new Color(8, 253, 216));
        btn.setBorder(BorderFactory.createLineBorder(new Color(8, 253, 216)));
        btn.setContentAreaFilled(false);
        btn.setMargin(new Insets(0, 0, 0, 0));
        btn.setPreferredSize(new Dimension(100, 35));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBackground(new Color(8, 253, 216));
        // hover event
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btn.setForeground(new Color(0, 0, 0));
                btn.setContentAreaFilled(true);
            }
            public void mouseExited(MouseEvent evt) {
                btn.setForeground(new Color(8, 253, 216));
                btn.setContentAreaFilled(false);
            }
        });
    }
    
    /**
     * Estiliza os JTextField default da aplicação
     * 
     * @param field o JTextField a ser estilizado
     */
    public static void defaultField(JTextField field) {
        field.setBackground(new Color(55, 57, 59));
        field.setForeground(new Color(255, 255, 255));
        field.setCaretColor(new Color(255, 255, 255));
        field.setOpaque(false);
        field.setPreferredSize(new Dimension(200, 39));
    }
    
    /**
     * Estiliza os JDateChooser default da aplicação
     * 
     * @param fDate o JDateChooser a ser estilizado
     */
    public static void defaultDateChooser(JDateChooser fDate) {
        fDate.setBackground(new Color(55, 57, 59));
        fDate.setForeground(new Color(255, 255, 255));
        fDate.getDateEditor().getUiComponent().setBackground(new Color(55, 57, 59));
        fDate.getDateEditor().getUiComponent().setForeground(new Color(255, 255, 255));
        fDate.setPreferredSize(new Dimension(200, 39));
    }
    
    /**
     * Estiliza os JCombobox default da aplicação
     * 
     * @param field o JCombobox a ser estilizado
     */
    public static void defaultComboBox(JComboBox field) {
        field.setOpaque(false);
        field.setPreferredSize(new Dimension(160, 39));
    }
    
    /**
     * Estiliza os JLabel default da aplicação
     * 
     * @param label o JLabel a ser estilizado
     */
    public static void defaultLabel(JLabel label) {
        label.setPreferredSize( new Dimension( 150, 39 ) );
        label.setForeground(new Color(255, 255, 255));
        label.setFont(new Font("Tahoma", 0, 12));
    }
    
    /**
     * Estliza os JLabel que exibem mensagens de erro da aplicação
     * 
     * @param label o JLabel a ser estilizado
     */
    public static void errorLabel(JLabel label) {
        label.setForeground(new Color(255, 0, 0));
        label.setFont(new Font("Tahoma", 0, 12));
        label.setPreferredSize( new Dimension( 150, 20 ) );
    }
    
    /**
     * Estliza os JButtons do menu da aplicação
     * 
     * @param btn o JButton a ser estilizado
     */
    public static void menuButton(JButton btn) {
        btn.setBackground(new Color(7, 7, 7));
        btn.setFont(new Font("Tahoma", 1, 14));
        btn.setForeground(new Color(8, 253, 216));
        btn.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        btn.setPreferredSize( new Dimension( 230, 35 ) );
    }
    
    /**
     * Estiliza o JButton do menu ativo da aplicação
     * 
     * @param btn o JButton a ser estilizado
     */
    public static void setMenuButtonActive(JButton btn) {
        btn.setBackground(new Color(8, 253, 216));
        btn.setForeground(new Color(7, 7, 7));
    }
    
    /**
     * Estiliza o JButton do menu não ativo da aplicação
     * 
     * @param btn o JButton a ser estilizado
     */
    public static void setMenuButtonInactive(JButton btn) {
        btn.setBackground(new Color(7, 7, 7));
        btn.setForeground(new Color(8, 253, 216));
    }
    
    /**
     * Estliza o JButton de busca da aplicação
     * 
     * @param btn o JButton a ser estilizado
     */
    public static void searchButton(JButton btn) {
        btn.setContentAreaFilled(false);
        btn.setPreferredSize(new Dimension(35, 35));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setIcon(new ImageIcon(btn.getClass().getResource("/sources/lupa.png")));
    }
    
    /**
     * Estiliza os JFrame de add/editar/vizualizar (ou outro interno qualquer)
     * Esse metodo fixa altura e largura (450x350)
     * 
     * @param frame o JFrame a ser estilizado
     */
    public static void internalFrame(JFrame frame) {
        Methods.makePanelRelativeSize(frame, 450, 350);
        _internalFramesBase(frame);
    }
    
    /**
     * Estiliza os JFrame de add/editar/vizualizar (ou outro interno qualquer)
     * 
     * @param frame o JFrame a ser estilizado
     * @param width a largura desejada
     * @param height a altura desejada
     */
    public static void internalFrame(JFrame frame, int width, int height) {
        Methods.makePanelRelativeSize(frame, width, height);
        _internalFramesBase(frame);
    }
    
    /**
     * Seta os estilos base de um JFrame interno da aplicação
     * 
     * @param frame o JFrame a ser estilizado
     */
    private static void _internalFramesBase(JFrame frame) {
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setBackground(new Color(37, 38, 39));
        Methods.positionFrameInCenter(frame);
        frame.setAlwaysOnTop(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.requestFocus();
    }
    
    /**
     * Estiliza os JScrollPane default da aplicação
     * 
     * @param panel o JScrollPane a ser estilizado
     */
    public static void resetScrollPanel(JScrollPane panel) {
        panel.setLayout(new ScrollPaneLayout());
        panel.getViewport().setOpaque(false);
        panel.getViewport().setBackground(new Color(37, 38, 39));
        panel.setBorder(createEmptyBorder());
        panel.setViewportBorder(null);
    }
    
    /**
     * Estiliza os componentes de sugestões da aplicação
     * 
     * @param panel o JScrollpane onde o JList de sugestão será inserido
     * @param list o JList onde as sugestões serão inseridas
     * @param model as sugestões um uma lista de strig
     */
    public static void defaultSuggestions(JScrollPane panel, JList list, String[] model) {
        list.setModel(new AbstractListModel<String>() {
            String[] strings = model;
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
            });
        panel.setPreferredSize( new Dimension( 200, 200 ) );
        panel.setViewportView(list);
        panel.setVisible(false);
    }
}
