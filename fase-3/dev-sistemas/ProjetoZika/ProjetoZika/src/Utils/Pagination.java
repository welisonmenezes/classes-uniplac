/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;

/**
 *
 * @author Welison
 */
public class Pagination {
    
    public void Pagination() {}
    
    public void makePagination(int total, JComponent context) {
        int pages = 5;
        JButton next = new JButton("Next");
        JButton prev = new JButton("Previous");
        Styles.defaultButton(next);
        Styles.defaultButton(prev);
        
        context.add(prev);
        for(int i = 1; i <= pages; i++) {
            JButton pBtn = new JButton("" + i);
            Styles.defaultButton(pBtn);
            pBtn.setPreferredSize(new Dimension(35, 35));
            
            pBtn.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    callbackPagination();
                }
            });
            
            context.add(pBtn);
        }
        context.add(next);
        
        prev.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                callbackPagination();
            }
        });
        
        next.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                callbackPagination();
            }
        });
    }
    
    public void callbackPagination() {
        System.out.println("Override it");
    }
}
