/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Welison
 */
public class Methods {
    
    private static JPanel jBody;
    
    public static void makeFrameFullSize(JFrame aFrame)
    {
        Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        aFrame.setSize(screenSize.width, screenSize.height);
    }
    
    public static void makePanelfullSize(JPanel aFrame)
    {
        Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        aFrame.setSize(screenSize.width, screenSize.height);
    }
    
    public static void makePanelRelativeSize(JFrame aFrame, int w, int h)
    {
        Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        aFrame.setSize(w, h);
    }
    
    public static void positionFrameInCenter(JFrame aFrame) {
        Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        aFrame.setLocation(screenSize.width/2-aFrame.getSize().width/2, screenSize.height/2-aFrame.getSize().height/2);
    }
    
    public static void setJBody(JPanel jb) {
        jBody = jb;
    }
    
    public static JPanel getJBody() {
       return  jBody;
    }
    
    public static void clearStage(JPanel jb) {
        jb.removeAll();
        jb.revalidate();
    }
    
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
    
    public static String selectedTableItemId(JTable table) {
        int row = table.getSelectedRow();
        Object id = table.getValueAt(row, 0);
        return id.toString();
    }
    
    public static void removeSelectedTableRow(JTable table, DefaultTableModel tableModel){
        int row = table.getSelectedRow();
        tableModel.removeRow(row);
    }
}
