/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika;

import projetozika.Pages.Menu;
import Utils.Methods;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.JPanel;

/**
 * A tela principal da aplicação
 * 
 * @author Welison
 */
public class Main extends javax.swing.JFrame {
    
    private JPanel jBG; // container geral
    private JPanel jBODY; // container do conteúdo
    private JPanel jSIDE; // container do menu
    public static JPanel menu; // o menu
    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        menu = new Menu(this);
        initLayout();
        
        Methods.setAccessibility(this);
        
        translation();
    }
    
    /**
     * Resolva as traduções
     */
    private void translation() {
        setTitle(Methods.getTranslation("ProjetoZika"));
    }
    
    /**
     * Inicializa o layout da aplicação baseado em BorderLayout
     * a aplicação recebera um conteiner geral, um container para o menu e um para o conteúdo
     */
    private void initLayout() {
        
        // add icon
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/sources/saturn.png")));
        
        // seta o tamanho do frame
        Methods.makeFrameFullSize(this);
        
        // inicializa os panels (containers)
        jBG = new JPanel();
        jBODY = new JPanel();
        jSIDE = new JPanel();
        
        // seta os containers base da aplicação
        Methods.setJBg(jBG);
        Methods.setJBody(jBODY);
        Methods.setJMenu(jSIDE);
        
        this.setLayout(new BorderLayout());
        this.add(jBG);
    
        jBG.setLayout(new BorderLayout());
        jBODY.setLayout(new BorderLayout());
        jSIDE.setLayout(new BorderLayout());
        
        jSIDE.add(menu, BorderLayout.CENTER);
        
        jBG.add(jSIDE, BorderLayout.WEST);
        jBG.add(jBODY, BorderLayout.CENTER);

        Methods.setJBody(jBODY);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Projeto Zika");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 714, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
