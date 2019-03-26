/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * Gerencia a paginação da aplicação
 * 
 * @author Welison
 */
public class Pagination {
    
    private int page;
    private JPanel context;
    private int total;
    
    /**
     * Construtor. Ao ser instanciada, a classe gera os botões e adiciona no Footer da aplicação
     * 
     * @param c o JPanel que receberá a paginação
     * @param total o total de páginas
     */
    public Pagination(JComponent c, int total) {
        this.page = 1;
        this.context = (JPanel) c;
        this.total = total;
        
        Methods.clearStage(context);
        makePagination();
    }
    
    /**
     * Gera os botões da aplicação, com seus respectivos eventos, e os insere na tela
     */
    private void makePagination() {
        JButton next = new JButton("Next");
        JButton prev = new JButton("Previous");
        Styles.defaultButton(next);
        Styles.defaultButton(prev);
        
        context.add(prev);
        for(int i = 1; i <= total; i++) {
            JButton pBtn = new JButton("" + i);
            Styles.defaultButton(pBtn);
            pBtn.setPreferredSize(new Dimension(35, 35));
            
            if(i == 1) {
                pBtn.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
                pBtn.setForeground(new Color(255, 255, 255));
            }
            
            pBtn.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() instanceof JButton){
                        JButton tmp = (JButton) e.getSource();
                        page = Integer.parseInt(tmp.getText());
                        updateActivePage();
                        callbackPagination();
                    }
                }
            });
            
            context.add(pBtn);
        }
        context.add(next);
        
        prev.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() instanceof JButton){
                    if(page > 1) {
                        page--;
                        updateActivePage();
                        callbackPagination();
                    }
                }
            }
        });
        
        next.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() instanceof JButton){
                    if(page < total) {
                        page++;
                        updateActivePage();
                        callbackPagination();
                    }
                }
            }
        });
    }
    
    /**
     * Metodo a ser sobreescrito. Será chamada ao click de cada botão
     */
    public void callbackPagination() {
        System.out.println("Override it");
    }
    
    /**
     * Seta o estilo do link ativo da paginação
     */
    public void updateActivePage() {
        Component[] comps = context.getComponents();
        for(int i = 0; i < comps.length; i++) {
            if (comps[i] instanceof JButton) {
                JButton tmp = (JButton) comps[i];
                if(this.page == i){
                    tmp.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
                    tmp.setForeground(new Color(255, 255, 255));
                } else {
                    tmp.setForeground(new Color(8, 253, 216));
                    tmp.setBorder(BorderFactory.createLineBorder(new Color(8, 253, 216)));
                }
            }
        }
    }
    
    /**
     * Pega a página corrent da paginação
     * 
     * @return a página corrente
     */
    public int getCurrentPage() {
        return page;
    }
}
