/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Config.Environment;
import com.toedter.calendar.JDateChooser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 *
 * @author welis
 */
public class DateHandler {
    /**
     * Seta o parâmetro correspondente ao campo de data no formato do idioma da aplicação
     * @param field o campo de data
     * @param params os parâmetros correntes da aplicação 
     */
    public static void setParamsToDateChooser(JDateChooser field, Properties params) {
        try {
            SimpleDateFormat sdf;
            if (Environment.getCurrentLang().equals("en")) {
                sdf = new SimpleDateFormat("yyyy-MM-dd");
            } else {
                sdf = new SimpleDateFormat("dd/MM/yyyy");
            }
            String sData = params.getProperty("data", "");
            if (!sData.equals("")) {
                Date newDate = sdf.parse(sData);
                field.setDate(newDate);
            }
        } catch (ParseException error) {
            throw new RuntimeException("Methods.setParamsToDateChooser: " + error);
        }
    }
    
    /**
     * Seta uma data default para um campo de data no formato do idiomma da aplicação
     * @param field o campo de data
     * @param date a data a ser inserida no campo
     */
    public static void setDateToDateChooser(JDateChooser field, String date) {
        try {
            SimpleDateFormat sdf;
            //if (Environment.getCurrentLang().equals("en")) {
                sdf = new SimpleDateFormat("yyyy-MM-dd");
            //} else {
                //sdf = new SimpleDateFormat("dd/MM/yyyy");
            //}
            Date newDate = sdf.parse(date);
            field.setDate(newDate);
        } catch (ParseException error) {
            throw new RuntimeException("Methods.setDateToDateChooser: " + error);
        }
    }
    
    /**
     * Seta o formato do campo de data conforme o idioma da aplicação
     * @param field o campo de data
     */
    public static void setDateChooserFormat(JDateChooser field) {
        if (Environment.getCurrentLang().equals("en")) {
            field.setDateFormatString("yyyy-MM-dd");
        } else {
            field.setDateFormatString("dd/MM/yyyy");
        }
    }
    
    public static String xxxgetFriendlyDate(String sqlDate) {
        try {
            SimpleDateFormat dt = new SimpleDateFormat("yyyyy-MM-dd hh:mm:ss"); 
            Date date = dt.parse(sqlDate);
            SimpleDateFormat sdf;
            if (Environment.getCurrentLang().equals("en")) {
                sdf = new SimpleDateFormat("yyyy-MM-dd");
            } else {
                sdf = new SimpleDateFormat("dd/MM/yyyy");
            }
            return sdf.format(date);
        } catch (ParseException error) {
            throw new RuntimeException("Methods.getFriendlyDate: " + error);
        }
    }
    
    /**
     * Parsea e retorna a data no formato sql datetime
     * @param appDate a data a ser parseada
     * @return a data parseada no formato sql datetime
     */
    public static String getSqlDateTime(String appDate) {
        try {
            SimpleDateFormat sdf;
            if (Environment.getCurrentLang().equals("en")) {
                sdf = new SimpleDateFormat("yyyy-MM-dd");
            } else {
                sdf = new SimpleDateFormat("dd/MM/yyyy");
            }
            Date date = sdf.parse(appDate);
            SimpleDateFormat dt = new SimpleDateFormat("yyyyy-MM-dd"); 
            return dt.format(date);
            
        } catch (ParseException error) {
            throw new RuntimeException("Methods.getSqlDate: " + error);
        }
    }
    
    /**
     * Retonra uma data parseada a partir de um sql Date
     * @param sqlDate o sql Date
     * @return  a data parseada
     */
    public static String getFriendlyBirthday(String sqlDate) {
        try {
            SimpleDateFormat dt = new SimpleDateFormat("yyyyy-MM-dd"); 
            Date date = dt.parse(sqlDate);
            SimpleDateFormat sdf;
            if (Environment.getCurrentLang().equals("en")) {
                sdf = new SimpleDateFormat("yyyy-MM-dd");
            } else {
                sdf = new SimpleDateFormat("dd/MM/yyyy");
            }
            return sdf.format(date);
        } catch (ParseException error) {
            throw new RuntimeException("Methods.getFriendlyDate: " + error);
        }
    }
    
    
    
    public static Date getJavaDate(String s) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyy-MM-dd");
            Date d = sdf.parse(s);
            return d;

        } catch (ParseException ex) {
            //Logger.getLogger(TableBasic.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
