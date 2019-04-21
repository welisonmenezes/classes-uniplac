/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author welis
 */
public final class PDFGenerator {
    private final String filename;
    private final Document document;
    private final JFileChooser fileChooser;
    private final JPanel context;
    private PdfWriter writer;
    private PdfPTable top;
    private PdfPTable header;
    private PdfPTable tableMain;
    private final String textHeader[];
    private final int cols;
    
    public PDFGenerator(String filename, String[] textHeader, JPanel context) {
        this.filename = filename;
        this.document = new Document();
        this.fileChooser = new JFileChooser();
        this.context = context;
        this.textHeader = textHeader;
        this.cols = this.textHeader.length;
        
        chooseDirectory();
    }
    
    public void chooseDirectory() {

        try {
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = fileChooser.showOpenDialog(context);
            if (returnVal == JFileChooser.APPROVE_OPTION) {

                File file = fileChooser.getSelectedFile();
                String dest = file + "/" + filename;
                writer = PdfWriter.getInstance(document, new FileOutputStream(dest));

                document.open();

                addTop();
                addInfo();
                addTableHeader();
                addTableMain();

                document.close();

                JOptionPane.showMessageDialog(null, Methods.getTranslation("RelatorioGeradoComSucesso"));

            } 
        } catch (IOException | DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }
    
    private void addTop(){
        top = new PdfPTable(2);
        try {
            
            // style top
            top.setWidths(new int[]{10, 90});
            top.setTotalWidth(525);
            top.setLockedWidth(true);
            top.getDefaultCell().setFixedHeight(64);
            top.getDefaultCell().setBorder(0);
            top.getDefaultCell().setPaddingRight(10);

            // add logo
            Image logo = Image.getInstance(getClass().getResource("/sources/saturn.png"));
            top.addCell(logo);
            
            // add/style text
            PdfPCell c1 = new PdfPCell(new Phrase("ProjetoZika - Pedidos"));
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            c1.setBorder(0);
            top.addCell(c1);
            
            // add in document
            document.add(top);
            
        } catch(DocumentException de) {
            throw new ExceptionConverter(de);
        } catch (MalformedURLException e) {
            throw new ExceptionConverter(e);
        } catch (IOException e) {
            throw new ExceptionConverter(e);
        }
    }
    
    private void addInfo() {
        try {
            // create paragraphs
            Paragraph paraUser = new Paragraph("Usuário: Todos");
            Paragraph paraProd = new Paragraph("Produto: Todos");
            Paragraph paraDate = new Paragraph("Período: 12/11/2018 à 01/02/2019");
            
            // add in document
            document.add(paraUser);
            document.add(paraProd);
            document.add(paraDate);
            
        } catch(DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }
    
    private void addTableHeader() {
        try {
            // crate table
            header = new PdfPTable(this.cols);
            header.setSpacingBefore(25f);
            header.getDefaultCell().setBorderColor(BaseColor.GRAY);
            
            // add content into header
            for (int i = 0; i < this.cols; i++) {
                header.addCell(textHeader[i]);
            }
            
            // style header
            for(PdfPCell c: header.getRow(0).getCells()) {
                c.setBackgroundColor(BaseColor.LIGHT_GRAY);
            }
            
            // add in document
            document.add(header);
            
        }  catch(DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }
    
    private void addTableMain() {
        try {
            
            tableMain = new PdfPTable(this.cols);
            tableMain.getDefaultCell().setBorderColor(BaseColor.GRAY);
            
            for(int aw = 0; aw < 800; aw++){
                tableMain.addCell("hi");
            }
            
            alternateBGColorRow(tableMain);
            document.add(tableMain);
            
        }  catch(DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }
    
    private void alternateBGColorRow(PdfPTable table) {
        boolean b = true;
        for(PdfPRow r: table.getRows()) {
          for(PdfPCell c: r.getCells()) {
            c.setBackgroundColor(b ? BaseColor.WHITE : BaseColor.LIGHT_GRAY);
          }
          b = !b;
        }
    }
}
