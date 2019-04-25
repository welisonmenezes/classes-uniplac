package Utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.Properties;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * Gerencia a paginação da aplicação
 * @author Welison
 */
public class Pagination {
    
    public int pages;
    private final JPanel context;
    private final int total;
    private final int limit;
    private int offset;
    private final Properties params;
    private int page;
    
    /**
     * Construtor. Ao ser instanciada, a classe gera os botões e adiciona no Footer da aplicação
     * @param c o JPanel que receberá a paginação
     * @param total o total de páginas
     * @param params Parâmetros para filtro e paginação
     */
    public Pagination(JComponent c, int total, Properties params) {
        this.context = (JPanel) c;
        this.total = total;
        this.limit = 10;
        
        // variavel para arrdedondamento
        double pag = Math.ceil(((double)this.total / (double)this.limit));
        this.pages = (int) pag;
        this.params = params;
        this.offset = Integer.parseInt(params.getProperty("offset", "0"));
        this.page = Integer.parseInt(params.getProperty("page", "1"));

        Methods.clearStage(context);
        if (total > limit) {
            makePagination();
            updateActivePage();
        }
        
    }
    
    /**
     * Gera os botões da aplicação, com seus respectivos eventos, e os insere na tela
     */
    private void makePagination() {
        JButton next = new JButton(Methods.getTranslation("Proximo"));
        JButton prev = new JButton(Methods.getTranslation("Anterior"));
        Styles.defaultButton(next);
        Styles.defaultButton(prev);
        
        context.add(prev);
        for(int i = 1; i <= pages; i++) {
            JButton pBtn = new JButton("" + i);
            Styles.defaultButton(pBtn);
            pBtn.setPreferredSize(new Dimension(35, 35));
            
            pBtn.addActionListener((ActionEvent e) -> {
                if(e.getSource() instanceof JButton){
                    JButton tmp = (JButton) e.getSource();
                    page = Integer.parseInt(tmp.getText());
                    params.setProperty("page", page + "");
                    updateActivePage();
                    callbackPagination();
                }
            });
            
            context.add(pBtn);
        }
        context.add(next);
        
        prev.addActionListener((ActionEvent e) -> {
            if(e.getSource() instanceof JButton){
                if(page > 1) {
                    page--;
                    params.setProperty("page", page + "");
                    updateActivePage();
                    callbackPagination();
                }
            }
        });
        
        next.addActionListener((ActionEvent e) -> {
            if(e.getSource() instanceof JButton){
                if(page < pages) {
                    page++;
                    params.setProperty("page", page + "");
                    updateActivePage();
                    callbackPagination();
                }
            }
        });
        
        
    }
    
    /**
     * Metodo a ser sobreescrito. Será chamada ao click de cada botão
     */
    public void callbackPagination() {
        //System.out.println("Override it");
    }
    
    /**
     * Seta o estilo do link ativo da paginação
     */
    public final void updateActivePage() {
        
        offset = (page - 1) * (limit);
        params.setProperty("offset", offset + "");
        
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
     * @return a página corrente
     */
    public int getCurrentPage() {
        return page;
    }
}
