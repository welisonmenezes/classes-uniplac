/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almoxerifado;

import static almoxerifado.MenuPrincipal.jPanelTela;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

/**
 *
 * @author sanvingla
 */
public class Acelerador {
    public static void Acelerador(final JFrame frame){
        JRootPane meurootpane = frame.getRootPane();
	meurootpane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK), "ESCAPE");
	meurootpane.getRootPane().getActionMap().put("ESCAPE", new AbstractAction("ESCAPE") {
		public void actionPerformed(ActionEvent e) {
		MenuPrincipal.jPanelTela.removeAll();
                MenuPrincipal.jPanelTela.revalidate();
                 AddFornecedor fornecedor  = new AddFornecedor();
                MenuPrincipal.jPanelTela.add(fornecedor);
                 fornecedor.setVisible(true);
                    //System.out.println("test fornecedor");
	}});
        //JRootPane meurootpane = frame.getRootPane();
	meurootpane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK), "CTRL");
	meurootpane.getRootPane().getActionMap().put("CTRL", new AbstractAction("CTRL") {
		public void actionPerformed(ActionEvent e) {
		MenuPrincipal.jPanelTela.removeAll();
                MenuPrincipal.jPanelTela.revalidate();
                listarPedido Pe = new listarPedido();
                MenuPrincipal.jPanelTela.add(Pe);
                Pe.setVisible(true);
            // System.out.println("test pedidos");
         
	}});
        
    }}
