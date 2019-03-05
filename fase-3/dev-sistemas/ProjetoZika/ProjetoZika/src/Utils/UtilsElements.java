/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Welison
 */
public class UtilsElements {
    
    public static void makeFrameFullSize(JFrame aFrame)
    {
        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        aFrame.setSize(screenSize.width, screenSize.height);
    }
}
