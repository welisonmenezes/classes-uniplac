/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Config.Environment;
import Templates.ComboItem;
import com.toedter.calendar.JDateChooser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author welis
 */
public class Validator {
    
    public static boolean isEmpty(JTextField field) {
        return field.getText().equals("");
    }
    
    public static boolean isInteger(JTextField field) {
        String s = field.getText();
        try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException | NullPointerException e) { 
            return false; 
        }
        return true;
    }
    
    public static boolean isDouble(JTextField field) {
        String s = field.getText();
        try { 
            Double.parseDouble(s);
        } catch(NumberFormatException | NullPointerException e) { 
            return false; 
        }
        return true;
    }
    
    public static boolean isValidSize(JTextField field, int size) {
        return (field.getText().length() <= size);
    }
    
    public static boolean isValidDate(String data) {
        try {
            SimpleDateFormat sdf;
            if (Environment.getCurrentLang().equals("en")) {
                sdf = new SimpleDateFormat("yyyy-MM-dd");
            } else {
                sdf = new SimpleDateFormat("dd/MM/yyyy");
            }
            sdf.setLenient(false);
            sdf.parse(data);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }
    
    public static boolean isComboBoxSelected(JComboBox combo) {
        ComboItem ci = (ComboItem) combo.getSelectedItem();
        if (combo.getSelectedItem() == null || ci.getId() == 0) {
            return false;
        }
        return true;
    }
    
    
    
    
    public static boolean validaCampo(JTextField field, JLabel errorLabel) {
        if (isEmpty(field)) {
            errorLabel.setText(Methods.getTranslation("CampoObrigatorio"));
            return false;
        }
        return true;
    }
    
    public static boolean validaNumero(JTextField field, JLabel errorLabel) {
        if (isEmpty(field)|| !isInteger(field)) {
            errorLabel.setText(Methods.getTranslation("NumeroInvalido"));
            return false;
        }
        return true;
    }
    
    public static boolean validaTelefone(JTextField field, JLabel errorLabel) {
        if (isEmpty(field)|| !isValidSize(field, 20)) {
            errorLabel.setText(Methods.getTranslation("TelefoneInvalido"));
            return false;
        }
        return true;
    }
    
    public static boolean validaCnpj(JTextField field, JLabel errorLabel) {
        if (isEmpty(field) || !isValidSize(field, 20)) {
            errorLabel.setText(Methods.getTranslation("CnpjInvalido"));
            return false;
        }
        return true;
    }
    
    public static boolean validaData(JDateChooser field, JLabel errorLabel) {
        String data = ((JTextField)field.getDateEditor().getUiComponent()).getText();
        if (data.isEmpty() || !isValidDate(data)) {
            errorLabel.setText(Methods.getTranslation("DataInvalida"));
            return false;
        }
        return true;
    }
    
    public static boolean validaComboBox(JComboBox combo, JLabel errorLabel) {
        if (!isComboBoxSelected(combo)) {
            errorLabel.setText(Methods.getTranslation("CampoObrigatorio"));
            return false;
        }
        return true;
    }
    
    public static boolean validaValor(JTextField field, JLabel errorLabel) {
        if (isEmpty(field)|| !isDouble(field)) {
            errorLabel.setText(Methods.getTranslation("ValorInvalido"));
            return false;
        }
        return true;
    }
    
}
