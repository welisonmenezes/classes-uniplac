/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Models.ReportModel;
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
import java.util.ArrayList;
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
    private final String columns[];
    private final String infos[];
    private final int cols;
    private final ArrayList<String[]> data;
    
    public PDFGenerator(ReportModel report, JPanel context) {
        this.filename = report.getFilename();
        this.document = new Document();
        this.fileChooser = new JFileChooser();
        this.context = context;
        this.columns = report.getColumns();
        this.cols = this.columns.length;
        this.infos = report.getInfos();
        this.data = report.getData();
        
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

            int total = this.infos.length;
            for (int i = 0; i < total; i++) {
                Paragraph para = new Paragraph(this.infos[i]);
                document.add(para);
            }
            
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
            header.getDefaultCell().setPadding(10);
            
            // add content into header
            for (int i = 0; i < this.cols; i++) {
                header.addCell(columns[i]);
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
            tableMain.getDefaultCell().setPadding(5);
            
            int total = data.size();
            for(int i = 0; i < total; i++){
                String row[] = data.get(i);
                int totalCell = row.length;
                for (int x = 0; x < totalCell; x++) {
                    tableMain.addCell(row[x]);
                }
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
            c.setBackgroundColor(b ? BaseColor.WHITE : new BaseColor(244, 244, 244));
          }
          b = !b;
        }
    }
}
