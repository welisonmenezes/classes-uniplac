/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.Relatorios;

import CustomFields.ComboItem;
import CustomFields.SuggestionsBox;
import Utils.DateHandler;
import Utils.Methods;
import Utils.Styles;
import Utils.Validator;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 * Tela para gerar relatórios de pedidos
 * @author welison
 */
public class RelatorioPedidos extends javax.swing.JPanel {
    private JLabel lusuario;
    private JPanel susuario;
    private JTextField fusuario;
    private JComboBox cusuario;
    private JLabel lproduto;
    private JPanel sproduto;
    private JTextField fproduto;
    private JComboBox cproduto;
    private JLabel ldatafrom;
    private JDateChooser fdatafrom;
    private JLabel edatafrom;
    private JLabel ldatato;
    private JDateChooser fdatato;
    private JLabel edatato;
    private JButton btnRelatorioPedido;
    private final JPanel self;
    private JLabel title;

    /**
     * Creates new form RelatorioPedidos
     */
    public RelatorioPedidos() {
        initComponents();
        this.self = this;
        addCamposPedidos();
    }
    
    private void addCamposPedidos() {
        setBackground(new Color(27, 28, 29));
        setLayout(new AbsoluteLayout());
        
        title = new JLabel();
        title.setFont(new java.awt.Font("Tahoma", 1, 24));
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText(Methods.getTranslation("RelatorioDePedidos"));
        add(title, new AbsoluteConstraints(20, 20, -1, -1));
        
        lusuario = new JLabel(Methods.getTranslation("Usuario"));
        Styles.defaultLabel(lusuario);
        add(lusuario, new AbsoluteConstraints(20, 60, -1, -1));
        
        // suggestion box
        susuario = new JPanel();
        fusuario = new JTextField();
        cusuario = new JComboBox();
        new SuggestionsBox(susuario, fusuario, cusuario, 300) {
            @Override
            public ArrayList<ComboItem> addElements() {
                ArrayList<ComboItem> elements = new ArrayList<>();
                for (int i = 1; i <= 25; i++) {
                    // TODO: implements real database results
                    elements.add(new ComboItem(i, "Nome_"+i));
                }
                return elements;
            }
        };
        add(susuario, new AbsoluteConstraints(20, 90, -1, -1));
        
        
        lproduto = new JLabel(Methods.getTranslation("Produto"));
        Styles.defaultLabel(lproduto);
        add(lproduto, new AbsoluteConstraints(340, 60, -1, -1));
        
        // suggestion box
        sproduto = new JPanel();
        fproduto = new JTextField();
        cproduto = new JComboBox();
        new SuggestionsBox(sproduto, fproduto, cproduto, 300) {
            @Override
            public ArrayList<ComboItem> addElements() {
                ArrayList<ComboItem> elements = new ArrayList<>();
                for (int i = 1; i <= 25; i++) {
                    // TODO: implements real database results
                    elements.add(new ComboItem(i, "Nome_"+i));
                }
                return elements;
            }
        };
        add(sproduto, new AbsoluteConstraints(340, 90, -1, -1));
        
        
        ldatafrom = new JLabel(Methods.getTranslation("De"));
        Styles.defaultLabel(ldatafrom);
        add(ldatafrom, new AbsoluteConstraints(20, 140, -1, -1));
        
        fdatafrom = new JDateChooser();
        Styles.defaultDateChooser(fdatafrom, 300);
        DateHandler.setDateChooserFormat(fdatafrom);
        add(fdatafrom, new AbsoluteConstraints(20, 170, -1, -1));
        
        edatafrom = new JLabel("");
        Styles.errorLabel(edatafrom);
        add(edatafrom, new AbsoluteConstraints(20, 210, -1, -1));
        
        ldatato = new JLabel(Methods.getTranslation("Ate"));
        Styles.defaultLabel(ldatato);
        add(ldatato, new AbsoluteConstraints(340, 140, -1, -1));
        
        fdatato = new JDateChooser();
        Styles.defaultDateChooser(fdatato, 300);
        DateHandler.setDateChooserFormat(fdatato);
        add(fdatato, new AbsoluteConstraints(340, 170, -1, -1));
        
        edatato = new JLabel("");
        Styles.errorLabel(edatato);
        edatato.setPreferredSize( new Dimension( 300, 20 ) );
        add(edatato, new AbsoluteConstraints(340, 210, -1, -1));
        
        
        btnRelatorioPedido = new JButton(Methods.getTranslation("GerarRelatorio"));
        Styles.defaultButton(btnRelatorioPedido, 300);
        add(btnRelatorioPedido, new AbsoluteConstraints(340, 250, -1, -1));
        btnRelatorioPedido.addActionListener((ActionEvent e) -> {
            
            // limpa erros
            clearErrors();
            
            // validação
            boolean isValid = true;
            if (! Validator.validaData(fdatafrom, edatafrom)) isValid = false;
            if (! Validator.validaData(fdatato, edatato)) isValid = false;
            // verifica se data é maior ou menor
            if (!Validator.isDateBeforeThen(fdatafrom, fdatato, edatato)) isValid = false;
            if (isValid) {
                try {
                    this.createPdf();
                } catch (IOException | DocumentException error) {
                    System.out.println("error: " + error);
                }
            }
            
        });
    }
    
    private void clearErrors() {
        edatafrom.setText("");
        edatato.setText("");
    }
    
    public void createPdf() throws IOException, DocumentException {
        
        String filename = "ProjetoZikaPediso-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".pdf";
        Document document = new Document();
        JFileChooser fc = new JFileChooser();
        
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            
            File file = fc.getSelectedFile();
            String dest = file + "/" + filename;
            
            //document.open();
            
            //PdfWriter.getInstance(document, new FileOutputStream(dest));

            
            //onStartPage(PdfWriter.getInstance(document, new FileOutputStream(dest)), document);
            addHeader(PdfWriter.getInstance(document, new FileOutputStream(dest)), document);
            PdfPTable table = new PdfPTable(8);
            table.setSpacingBefore(25f);
            for(int aw = 0; aw < 116; aw++){
                table.addCell("hi");
            }
            
            document.add(table);
            document.close();
            
            JOptionPane.showMessageDialog(null, Methods.getTranslation("RelatorioGeradoComSucesso"));
            
        } else {
            System.out.println("Nada escolhido");
        }
    }
    
    /*
    public void onStartPage(PdfWriter writer, Document document) {
        document.open();
       // ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Top Left"), 30, 800, 0);
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Top Right"), 550, 800, 0);
    }
    */
    
    private void addHeader(PdfWriter writer, Document document){
        PdfPTable header = new PdfPTable(2);
        try {
            document.open();
            // set defaults
            header.setWidths(new int[]{10, 90});
            header.setTotalWidth(525);
            //header.setSpacingAfter(30);
            //header.getDefaultCell().setPaddingBottom(164);
            header.setLockedWidth(true);
            header.getDefaultCell().setFixedHeight(64);
            header.getDefaultCell().setBorder(0);
            header.getDefaultCell().setPaddingRight(10);
            //header.getDefaultCell().setBorder(Rectangle.BOTTOM);
            //header.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

            // add image
            Image logo = Image.getInstance(getClass().getResource("/sources/saturn.png"));
            header.addCell(logo);
            PdfPCell c1 = new PdfPCell(new Phrase("ProjetoZika - Pedidos"));
            //c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            c1.setBorder(0);
            //c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.addCell(c1);
            
            //header.addCell("");

            //header.setComplete(true);
            // write content
            document.add(header);
            //header.writeSelectedRows(0, -1, 34, 823, writer.getDirectContent());
        } catch(DocumentException de) {
            throw new ExceptionConverter(de);
        } catch (MalformedURLException e) {
            throw new ExceptionConverter(e);
        } catch (IOException e) {
            throw new ExceptionConverter(e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
