package Models;

import java.util.ArrayList;

/**
 * Modelo de dados do relatório
 * @author welison
 */
public class ReportModel {
    private String filename;
    private String title;
    private String columns[];
    private String infos[];
    private ArrayList<String[]> data;
    
    public ReportModel() {}
    
    public ReportModel(String filename, String title, String columns[], String infos[], ArrayList<String[]> data) {
        this.filename = filename;
        this.title = title;
        this.columns = columns;
        this.infos = infos;
        this.data = data;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public String[] getInfos() {
        return infos;
    }

    public void setInfos(String[] infos) {
        this.infos = infos;
    }

    public ArrayList<String[]> getData() {
        return data;
    }

    public void setData(ArrayList<String[]> data) {
        this.data = data;
    }
    
}
