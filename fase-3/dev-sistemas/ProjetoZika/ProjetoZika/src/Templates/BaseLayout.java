package Templates;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Properties;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * Implementa layout base (baseado em BorderLayout) para paineis de visualização da raíz de cada menu
 * O Jpanel que é raíz de cada menu deve extende-la.
 * @author Welison
 */
public class BaseLayout extends javax.swing.JPanel {
    protected JPanel pTop;
    protected JPanel pCenter;
    protected JPanel pFilter;
    protected JPanel pBottom;
    protected BaseLayout self;
    protected Properties params;
    protected JTable tabela;
    protected DefaultTableModel tableModel;
    protected JScrollPane barraRolagem;
    
    protected BaseLayout() {
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
     * @param title o título da pagina adicionado no topo do grid
     */
    protected void addTopContent(String title) {
        JLabel label = new JLabel();
        label.setFont(new java.awt.Font("Tahoma", 1, 24));
        label.setForeground(new java.awt.Color(255, 255, 255));
        label.setText(title);
        pTop.add(label);
    }
    
    /**
     * Adiciona os paineis aos seus respectivos lugares no border layout
     */
    protected void createBaseLayout() {
        
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
