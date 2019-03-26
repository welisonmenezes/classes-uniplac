package Templates;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * Implementa layout base (baseado em BorderLayout) para paineis de visualização da raíz de cada menu
 * O Jpanel que é raíz de cada menu deve extende-la.
 * 
 * @author Welison
 */
public class BaseLayout extends javax.swing.JPanel {
    public JPanel pTop;
    public JPanel pCenter;
    public JPanel pFilter;
    public JPanel pBottom;
    
    public BaseLayout() {
        pTop = new JPanel();
        pCenter = new JPanel();
        pFilter = new JPanel();
        pBottom = new JPanel();
        
        setBackground(new Color(37, 38, 39));
        setBorder(BorderFactory.createEmptyBorder(50, 25, 50, 25));
        setLayout(new BorderLayout());
    }
    
    /**
     * Adiciona um título ao topo do grid
     * 
     * @param title o título da pagina adicionado no topo do grid
     */
    public void addTopContent(String title) {
        JLabel label = new JLabel();
        label.setFont(new java.awt.Font("Tahoma", 1, 24));
        label.setForeground(new java.awt.Color(255, 255, 255));
        label.setText(title);
        pTop.add(label);
    }
    
    /**
     * Adiciona os paineis aos seus respectivos lugares no border layout
     */
    public void createBaseLayout() {
        
        // topo do grid
        this.pTop.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.pTop.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
        this.pTop.setOpaque(false);
        
        // corpo do grid
        this.pCenter.setLayout(new BorderLayout());
        this.pCenter.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.pCenter.setOpaque(false);
        
        // topo do corpo do grid
        this.pFilter.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.pFilter.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
        this.pFilter.setOpaque(false);
        
        // rodapé do grid
        this.pBottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.pBottom.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
        this.pBottom.setOpaque(false);
        
        // adiciona os JPanels em seus respectivos lugares
        this.add(this.pTop, BorderLayout.NORTH);
        this.add(this.pCenter, BorderLayout.CENTER);
        this.pCenter.add(this.pFilter, BorderLayout.NORTH);
        this.add(this.pBottom, BorderLayout.SOUTH);
    }
}
