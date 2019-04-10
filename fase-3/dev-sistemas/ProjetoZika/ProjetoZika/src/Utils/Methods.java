/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Config.Environment;
import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import projetozika.Main;

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
    public static void makePanelRelativeSize(JFrame aFrame, int w, int h)
    {
        Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
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
                    int opcion = JOptionPane.showConfirmDialog(null, Methods.getTranslation("DesejaRealmenteSair?"), "Aviso", JOptionPane.YES_NO_OPTION);
                    if (opcion == 0) {
                        System.exit(0);
                    }
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
     * Seta o parâmetro correspondente ao campo de data no formato do idioma da aplicação
     * @param field o campo de data
     * @param params os parâmetros correntes da aplicação 
     */
    public static void setParamsToDateChooser(JDateChooser field, Properties params) {
        SimpleDateFormat sdf;
        if (Environment.getCurrentLang().equals("en")) {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        } else {
            sdf = new SimpleDateFormat("dd/MM/yyyy");
        }
        try {
            String sData = params.getProperty("data", "");
            if (!sData.equals("")) {
                Date newDate = sdf.parse(sData);
                field.setDate(newDate);
            }
        } catch (ParseException error) {
            throw new RuntimeException("Methods.setParamsToDateChooser: " + error);
        }
    }
    
    /**
     * Seta uma data default para um campo de data no formato do idiomma da aplicação
     * @param field o campo de data
     * @param date a data a ser inserida no campo
     */
    public static void setDateToDateChooser(JDateChooser field, String date) {
        SimpleDateFormat sdf;
        if (Environment.getCurrentLang().equals("en")) {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        } else {
            sdf = new SimpleDateFormat("dd/MM/yyyy");
        }
        try {
            Date newDate = sdf.parse(date);
            field.setDate(newDate);
        } catch (ParseException error) {
            throw new RuntimeException("Methods.setDateToDateChooser: " + error);
        }
    }
    
    /**
     * Seta o formato do campo de data conforme o idioma da aplicação
     * @param field o campo de data
     */
    public static void setDateChooserFormat(JDateChooser field) {
        if (Environment.getCurrentLang().equals("en")) {
            field.setDateFormatString("yyyy-MM-dd");
        } else {
            field.setDateFormatString("dd/MM/yyyy");
        }
    }
    
    public static String getFriendlyDate(String sqlDate) {
        try {
            SimpleDateFormat dt = new SimpleDateFormat("yyyyy-MM-dd hh:mm:ss"); 
            Date date = dt.parse(sqlDate);
            SimpleDateFormat sdf;
            if (Environment.getCurrentLang().equals("en")) {
                sdf = new SimpleDateFormat("yyyy-MM-dd");
            } else {
                sdf = new SimpleDateFormat("dd/MM/yyyy");
            }
            return sdf.format(date);
        } catch (ParseException error) {
            throw new RuntimeException("Methods.getFriendlyDate: " + error);
        }
    }
    
    /**
     * Parsea e retorna a data no formato sql datetime
     * @param appDate a data a ser parseada
     * @return a data parseada no formato sql datetime
     */
    public static String getSqlDateTime(String appDate) {
        try {
            SimpleDateFormat sdf;
            if (Environment.getCurrentLang().equals("en")) {
                sdf = new SimpleDateFormat("yyyy-MM-dd");
            } else {
                sdf = new SimpleDateFormat("dd/MM/yyyy");
            }
            Date date = sdf.parse(appDate);
            SimpleDateFormat dt = new SimpleDateFormat("yyyyy-MM-dd"); 
            return dt.format(date);
            
        } catch (ParseException error) {
            throw new RuntimeException("Methods.getSqlDate: " + error);
        }
    }
    
    /**
     * Retonra uma data parseada a partir de um sql Date
     * @param sqlDate o sql Date
     * @return  a data parseada
     */
    public static String getFriendlyBirthday(String sqlDate) {
        try {
            SimpleDateFormat dt = new SimpleDateFormat("yyyyy-MM-dd"); 
            Date date = dt.parse(sqlDate);
            SimpleDateFormat sdf;
            if (Environment.getCurrentLang().equals("en")) {
                sdf = new SimpleDateFormat("yyyy-MM-dd");
            } else {
                sdf = new SimpleDateFormat("dd/MM/yyyy");
            }
            return sdf.format(date);
        } catch (ParseException error) {
            throw new RuntimeException("Methods.getFriendlyDate: " + error);
        }
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
    
    public static Date getJavaDate(String s) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyy-MM-dd hh:mm:ss");
            Date d = sdf.parse(s);
            return d;

        } catch (ParseException ex) {
            //Logger.getLogger(TableBasic.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
