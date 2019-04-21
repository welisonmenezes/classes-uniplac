/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.ArrayList;

/**
 *
 * @author welis
 */
public class ReportModel {
    private String filename;
    private String columns[];
    private String infos[];
    private ArrayList<String[]> data;
    
    public ReportModel() {}
    
    public ReportModel(String filename, String columns[], String infos[], ArrayList<String[]> data) {
        this.filename = filename;
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
