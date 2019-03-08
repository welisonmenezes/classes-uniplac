package Utils;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Welison
 */
public class UtilsCreator {
    
    public void createBaseLayout(JPanel pContext, JPanel pTop, JPanel pCenter, JPanel pBottom, JPanel pFilter) {
        
        pTop.setLayout(new FlowLayout(FlowLayout.LEFT));
        pTop.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
        pTop.setOpaque(false);
        
        
        pCenter.setLayout(new BorderLayout());
        pCenter.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        pCenter.setOpaque(false);
        
        
        pFilter.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pFilter.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
        pFilter.setOpaque(false);
        
        
        pBottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pBottom.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
        pBottom.setOpaque(false);
         
        pContext.add(pTop, BorderLayout.NORTH);
        pContext.add(pCenter, BorderLayout.CENTER);
        pCenter.add(pFilter, BorderLayout.NORTH);
        pContext.add(pBottom, BorderLayout.SOUTH);
    }
}
