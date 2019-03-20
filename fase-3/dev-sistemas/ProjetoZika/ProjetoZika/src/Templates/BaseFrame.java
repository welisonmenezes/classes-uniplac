package Templates;

import Utils.Methods;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Implementa layout base para frames de visualização das telas internas às telas raízes
 * As telas internas às telas raízes deve extende-la
 * 
 * @author Welison
 */
public class BaseFrame extends javax.swing.JFrame{
    public JPanel pTop;
    public JPanel pCenter;
    public JPanel pFilter;
    public JPanel pBottom;
    public JPanel pBG;
    
    public BaseFrame() {
        pBG = new JPanel();
        pTop = new JPanel();
        pCenter = new JPanel();
        pFilter = new JPanel();
        pBottom = new JPanel();
        
        Methods.disableEnableRootAndMenuPanel(this);
    }
    
    
    /**
     * Adiciona um título para o topo do grid
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
        this.pBG.setLayout(new BorderLayout());
        this.pBG.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        this.pBG.setBackground(new Color(37, 38, 39));
        
        // corpo do grid
        this.pTop.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.pTop.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
        this.pTop.setOpaque(false);
        
        // topo do corpo do grid
        this.pCenter.setLayout(new BorderLayout());
        this.pCenter.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.pCenter.setOpaque(false);
        
        // rodapé do grid
        this.pBottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.pBottom.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
        this.pBottom.setOpaque(false);
         
        // adiciona os JPanels em seus respectivos lugares
        this.add(this.pBG, BorderLayout.CENTER);
        this.pBG.add(this.pTop, BorderLayout.NORTH);
        this.pBG.add(this.pCenter, BorderLayout.CENTER);
        this.pBG.add(this.pBottom, BorderLayout.SOUTH);
    }
}
