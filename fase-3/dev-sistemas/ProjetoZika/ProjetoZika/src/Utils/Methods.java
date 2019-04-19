/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * Metodos e funcionalidades genéricas da aplicação
 * @author Welison
 */
public class Methods {
    
    private static JPanel jBody;
    private static JPanel jBg;
    private static JPanel jMenu;
    
    /**
     * Seta altura e largura full a um dado JFrame ou JPanel
     * @param comp o Component que será dimensionado
     */
    public static void makeComponentFullSize(Component comp)
    {
        Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        comp.setSize(screenSize.width, screenSize.height);
    }
    
    /**
     * Seta uma dada altura e uma dada largura a um dado JFrame
     * @param aFrame o JFrame que será dimensionado
     * @param w a largura desejada
     * @param h a altura desejada
     */
    public static void makePanelRelativeSize(JFrame aFrame, int w, int h){
        aFrame.setSize(w, h);
    }
    
    /**
     * Posiciona um dado JFrame no centro da tela
     * @param aFrame o JFrame a ser posicionado
     */
    public static void positionFrameInCenter(JFrame aFrame) {
        Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        aFrame.setLocation(screenSize.width/2-aFrame.getSize().width/2, screenSize.height/2-aFrame.getSize().height/2);
    }
    
    /**
     * Seta o container do conteúdo da apalicação
     * @param jb o JPanel que exibirá o conteúdo root
     */
    public static void setJBody(JPanel jb) {
        jBody = jb;
    }
    
    /**
     * Retorna o container que exibe o conteúdo root
     * @return o JPanel
     */
    public static JPanel getJBody() {
       return  jBody;
    }
    
    /**
     * Seta o container geral da aplicação
     * @param jb o JPanel que exibirá o conteúdo root
     */
    public static void setJBg(JPanel jb) {
        jBg = jb;
    }
    
    /**
     * Retorna o container geral da aplicação
     * @return o JPanel
     */
    public static JPanel getJBg() {
       return  jBg;
    }
    
    /**
     * Seta o container do menu da aplicação
     * @param jb o JPanel que exibirá o conteúdo root
     */
    public static void setJMenu(JPanel jb) {
        jMenu = jb;
    }
    
    /**
     * Retorna o container do menu da aplicação
     * @return o JPanel
     */
    public static JPanel getJMenu() {
       return  jMenu;
    }
    
    /**
     * Limpa a area de um dado JPanel
     * @param jb o JPanel a ser limpo
     */
    public static void clearStage(JPanel jb) {
        jb.removeAll();
        jb.repaint();
        jb.revalidate();
    }
    
    /**
     * Seta os elementos de um dado JComponent como habilitado ou desabilitado
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
     * @param table o JTable cuja valor será retornado
     * @return o valor da primeira coluna da linha selecionada
     */
    public static String selectedTableItemId(JTable table) {
        int row = table.getSelectedRow();
        Object id = table.getValueAt(row, 0);
        return id.toString();
    }
    
    /**
     * Retorna o valor de uma dada coluna da linha selecionda de um dado JTable
     * @param table o JTable cuja valor será retornado
     * @param col o Index da coluna desejada
     * @return o valor da primeira coluna da linha selecionada
     */
    public static String selectedTableItemValue(JTable table, int col) {
        int row = table.getSelectedRow();
        Object value = table.getValueAt(row, col);
        return value.toString();
    }
    
    /**
     * Remove a linha selecionada de um dado JTable
     * @param table o JTable cuja linha selecionada será removida
     * @param tableModel o JTableModel cuja linha selecionada será removida
     */
    public static void removeSelectedTableRow(JTable table, DefaultTableModel tableModel){
        int row = table.getSelectedRow();
        tableModel.removeRow(row);
    }
    
    /**
     * Habilita o desabilita os componetes d
     * @param frame o JBody e JMenu no abrir e fecha deste frame
     */
    public static void disableEnableRootAndMenuPanel(JFrame frame) {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent we) {
                Methods.setEnableRecursively(Methods.getJBody(), false);
                Methods.setEnableRecursively(Methods.getJMenu(), false);
            }
            
            @Override
            public void windowClosed(WindowEvent we) {
                Methods.setEnableRecursively(Methods.getJBody(), true);
                Methods.setEnableRecursively(Methods.getJMenu(), true);
            }
        });
    }
    
    /**
     * Desabilita os campos de formulario
     * @param wrap o container cujo filhos serão desabilitados
     */
    public static void disabledFields(JComponent wrap) {
        Component[] comps = wrap.getComponents();
        for (int i = 0; i < comps.length; i++) {
            //System.out.println(comps[i]);
            if(comps[i] instanceof JScrollPane) {
                JScrollPane comp = (JScrollPane) comps[i];
                disabledFields(comp);
            } else if(comps[i] instanceof JViewport) {
                JViewport comp = (JViewport) comps[i];
                disabledFields(comp);
            } else if (comps[i] instanceof JTextField) {
                JTextField comp = (JTextField)comps[i];
                comp.setEditable(false);
            } else if (comps[i] instanceof JTextArea) {
                JTextArea comp = (JTextArea)comps[i];
                comp.setEditable(false);
            } else if (comps[i] instanceof JButton) {
                JButton comp = (JButton)comps[i];
                comp.setVisible(false);
            } else {
                comps[i].setEnabled(false);
            }
        }
    }
    
    /**
     * Retorna o texto correspondente a uma dada chave e ao idioma da aplicação
     * @param text a chave do texto do arquivo bundle a ser traduzido
     * @return o texto traduzido
     */
    public static String getTranslation(String text) {
        ResourceBundle bundle = ResourceBundle.getBundle("sources/Bundle");
        return bundle.getString(text);
    }
    
    /**
     * Seta como marcado o elemento cujo valor coincide com um dado valor
     * @param rdValue o valor a ser marcado
     * @param elements o elementos do ButtonGroup
     */
    public static void setButtonGroup(String rdValue, Enumeration elements ){
        while (elements.hasMoreElements()){
            AbstractButton button = (AbstractButton)elements.nextElement();
            if(button.getActionCommand().equals(rdValue)){
                button.setSelected(true);
            }
        }
    }
    
    /**
     * torna uma dada tabela ordenável
     * @param tabela a tabela a ser ordenável
     * @param indexCol o index da última coluna a ser ordenável 
     * @param params parâmetros de paginação, ordenação e filtros
     */
    public static void makeTableSortable(JTable tabela, int indexCol, Properties params) {
        tabela.setAutoCreateRowSorter(true);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabela.getModel()){
            @Override
            public boolean isSortable(int column) {
                return (column <= indexCol);
            };
        };
        tabela.setRowSorter(sorter);
        ArrayList list = new ArrayList();
        
        SortOrder so;
        if (params.getProperty("order", "DESC").equals("DESC")) {
            so = SortOrder.DESCENDING;
        } else {
            so = SortOrder.ASCENDING;
        }
        
        list.add( new RowSorter.SortKey(Integer.parseInt(params.getProperty("orderkey", "0")), so));
        sorter.setSortKeys(list);
    }
}
