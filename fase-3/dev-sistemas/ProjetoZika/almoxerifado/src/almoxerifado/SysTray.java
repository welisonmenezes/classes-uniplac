/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almoxerifado;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author sanvingla
 */
public class SysTray {


    static TrayIcon trayIcon;

    public SysTray(JFrame frame) {
        trayIcon = new TrayIcon(createIcon("/Imagens/java.jpeg", "Icon"), "Almoxarifado v1.0");

        // Aqui vamos testar se o recurso é suportado
        if (SystemTray.isSupported()) {

            //declarando uma variavel  do tipo SystemTray
            SystemTray tray = SystemTray.getSystemTray();

            // Criamos um ActionListener para a ação de encerramento do programa.
            ActionListener fechaAplicacao = new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    int Answer = JOptionPane.showConfirmDialog(null, "Voce deseja sair?", "Sair", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (Answer == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }

                }

            };

            // Criamos um ActionListener para abrir o programa com dois cliques
            ActionListener abreAplicacao = new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(true);
                    tray.remove(trayIcon);
                }
            };

            // Criamos um ActionListener para abrir o programa com dois cliques
            ActionListener minimizaAplicacao = new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                }
            };

            // Criamos um ActionListener para abrir a popup de sobre
            ActionListener sobreAplicacao = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "Almoxarifado v1.0\nCriado por Leonardo Salomon", "Sobre", JOptionPane.INFORMATION_MESSAGE);

                }
            };

            //Criando um objeto PopupMenu
            PopupMenu popup = new PopupMenu("Menu de Opções");

            //criando itens do menu
            MenuItem aplicacao = new MenuItem("Aplicação");

            MenuItem sobre = new MenuItem("Sobre");

            MenuItem minimizar = new MenuItem("Minimizar");

            MenuItem sair = new MenuItem("Sair");

            //na linha a seguir associamos os objetos aos eventos
            aplicacao.addActionListener(abreAplicacao);

            sobre.addActionListener(sobreAplicacao);

            minimizar.addActionListener(minimizaAplicacao);

            sair.addActionListener(fechaAplicacao);

            //Adicionando itens ao PopupMenu
            popup.add(aplicacao);
            popup.add(sobre);

            popup.addSeparator();

            popup.add(minimizar);
            popup.add(sair);

            //criando um objeto do tipo TrayIcon
            trayIcon.setPopupMenu(popup);

            ActionListener actionListener = new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(true);
                }

            };

            //Na linha a seguir a imagem a ser utilizada como icone sera redimensionada
            trayIcon.setImageAutoSize(true);

            //Seguida adicionamos os actions listeners
            trayIcon.addActionListener(actionListener);

            //Tratamento de erros
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println("Erro, TrayIcon não sera adicionado.");
            }

        } else {
            //Caso o item  System Tray não for suportado
            JOptionPane.showMessageDialog(null, "recurso ainda não esta disponível pra o seu sistema");

        }

    }

    protected static Image createIcon(String path, String desc) {
        URL imageURL = SysTray.class.getResource(path);
        return (new ImageIcon(imageURL, desc)).getImage();
    }

}


    

