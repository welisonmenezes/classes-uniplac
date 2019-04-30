package Utils;

import Models.ReportModel;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.FontFactory;
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
 * Gera o relatório em PDF
 * @author welison
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
    private final String title;
    
    /**
     * Gera o relatório em PDF
     * @param report o modelo ReportModel com os dados do relatório
     * @param context o JPanel de onde a classe foi chamada
     */
    public PDFGenerator(ReportModel report, JPanel context) {
        this.filename = report.getFilename();
        this.title = report.getTitle();
        this.document = new Document();
        this.fileChooser = new JFileChooser();
        this.context = context;
        this.columns = report.getColumns();
        this.cols = this.columns.length;
        this.infos = report.getInfos();
        this.data = report.getData();
        
        chooseDirectory();
    }
    
    /**
     * Abre o selecionador de diretório e salva o pdf
     */
    public void chooseDirectory() {

        try {
            // chama o filechooser
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = fileChooser.showOpenDialog(context);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                
                // pega o diretório selecionada e concatena com o dado filename
                File file = fileChooser.getSelectedFile();
                String dest = file + "/" + filename;
                writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
                
                document.open();

                // add o conteúdo no documento
                addTop();
                addInfo();
                addTableHeader();
                addTableMain();

                document.close();
                
                // mensagem de sucesso
                JOptionPane.showMessageDialog(null, Methods.getTranslation("RelatorioGeradoComSucesso"));
            }
        } catch (IOException | DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }
    
    /**
     * Cria e adiciona o topo do relatório
     */
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
            PdfPCell c1 = new PdfPCell(new Phrase(title));
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            c1.setBorder(0);
            top.addCell(c1);
            
            // add in document
            document.add(top);
            
        } catch(DocumentException | IOException de) {
            throw new ExceptionConverter(de);
        }
    }
    
    /**
     * Cria e adiciona as meta informações do relatório
     */
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
    
    /**
     * Cria e adiciona a tabela header
     */
    private void addTableHeader() {
        try {
            // cria a tabela
            header = new PdfPTable(this.cols);
            header.setSpacingBefore(25f);
            header.getDefaultCell().setBorderColor(BaseColor.GRAY);
            header.getDefaultCell().setPadding(10);
            header.setWidthPercentage(100);
            
            // add o conteúdo na tabela
            for (int i = 0; i < this.cols; i++) {
                Phrase content = new Phrase(columns[i], FontFactory.getFont(FontFactory.HELVETICA, 11));
                header.addCell(content);
            }
            
            // estiliza o header
            for(PdfPCell c: header.getRow(0).getCells()) {
                c.setBackgroundColor(BaseColor.LIGHT_GRAY);
            }
            
            // add no documento
            document.add(header);
            
        }  catch(DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }
    
    /**
     * Cria e adiciona a tabela principal
     */
    private void addTableMain() {
        try {
            // cria e estiliza a tabela
            tableMain = new PdfPTable(this.cols);
            tableMain.getDefaultCell().setBorderColor(BaseColor.GRAY);
            tableMain.getDefaultCell().setPadding(3);
            tableMain.setWidthPercentage(100);
            //tableMain
            
            // adiciona os dados na tabela
            int total = data.size();
            for(int i = 0; i < total; i++){
                String row[] = data.get(i);
                int totalCell = row.length;
                for (int x = 0; x < totalCell; x++) {
                    Phrase content = new Phrase(row[x], FontFactory.getFont(FontFactory.HELVETICA, 9));
                    tableMain.addCell(content);
                }
            }
            
            // tabela zebrada
            alternateBGColorRow(tableMain);
            
            // add no documento
            document.add(tableMain);
            
        }  catch(DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }
    
    /**
     * Alterna o background das linhas de uma dada tabela
     * @param table a tabela a ser estilizada
     */
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
