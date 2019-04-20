/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Config.Environment;
import CustomFields.ComboItem;
import com.toedter.calendar.JDateChooser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author welis
 */
public class Validator {
    
    /**
     * Checa se o valor do campo é vazio
     * @param field o campo a ser verificado
     * @return true se vazio
     */
    public static boolean isEmpty(JTextField field) {
        return field.getText().trim().equals("");
    }
    
    /**
     * Checa se o valor do campo é vazio
     * @param field o campo a ser verificado
     * @return true se vazio
     */
    public static boolean isEmpty(JTextArea field) {
        return field.getText().trim().equals("");
    }
    
    /**
     * Checa se o valor do campo é vazio
     * @param combo o combobox a ser verificado
     * @return true se vazio
     */
    public static boolean isEmpty(JComboBox combo) {
        return combo.getSelectedItem().toString().equals("");
    }
    
    /**
     * Checa se o valor do campo é um inteiro
     * @param field o campo a ser verificado
     * @return true se é inteiro
     */
    public static boolean isInteger(JTextField field) {
        String s = field.getText().trim();
        try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException | NullPointerException e) { 
            return false; 
        }
        return true;
    }
    
    /**
     * Checa se o valor do campo é um long
     * @param field o campo a ser verificado
     * @return true se é long
     */
    public static boolean isLong(JTextField field) {
        String s = field.getText().trim();
        try { 
            Long.parseLong(field.getText());
        } catch(NumberFormatException | NullPointerException e) { 
            return false; 
        }
        return true;
    }
    
    /**
     * Checa se o valor do campo é um double
     * @param field o campo a ser verificado
     * @return true se é um double
     */
    public static boolean isDouble(JTextField field) {
        String s = field.getText().trim();
        try { 
            Double.parseDouble(s);
        } catch(NumberFormatException | NullPointerException e) { 
            return false; 
        }
        return true;
    }
    
    /**
     * Checa se o tamanho do valor do campo é menor ou igual a um dado tamanho
     * @param field o campo a ser verificado
     * @param size o tamanho limite
     * @return true se é menor ou igual
     */
    public static boolean isValidSize(JTextField field, int size) {
        return (field.getText().trim().length() <= size);
    }
    
    /**
     * Checa se o tamanho do valor do campo é menor ou igual a um dado tamanho
     * @param field o campo a ser verificado
     * @param size o tamanho limite
     * @return true se é menor ou igual
     */
    public static boolean isValidSize(JTextArea field, int size) {
        return (field.getText().trim().length() <= size);
    }
    
    /**
     * Checa se a data respeita o formato de data do idioma da aplicação
     * @param data a data a ser validada
     * @return true se é uma data válida
     */
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
    
    /**
     * Checa se o combobox do campo de sugestão tem um item selecionado
     * @param combo o combobox do campo de sugestão
     * @return true se tem um item selecionado
     */
    public static boolean isComboBoxSelected(JComboBox combo) {
        ComboItem ci = (ComboItem) combo.getSelectedItem();
        if (combo.getSelectedItem() == null || ci.getId() == 0) {
            return false;
        }
        return true;
    }
    
    /**
     * Checa se um dado ButtonGroup tem um item marcado
     * @param group o ButtonGroup a ser verificado
     * @return true se tiver um item marcado
     */
    public static boolean isButtonGroupSelected(ButtonGroup group) {
        return group.getSelection() != null;
    }
    
    /**
     * Valida o se o campo está vazio e adiciona a mensagem de erro no seu respectivo label
     * @param field o campo a ser validado
     * @param errorLabel o label que receberá a mensagem
     * @return true se não é vazio
     */
    public static boolean validaCampo(JTextField field, JLabel errorLabel) {
        if (isEmpty(field)|| !isValidSize(field, 45)) {
            errorLabel.setText(Methods.getTranslation("CampoObrigatorio"));
            return false;
        }
        return true;
    }
    
    /**
     * Valida o se o campo está vazio e adiciona a mensagem de erro no seu respectivo label
     * @param field o campo a ser validado
     * @param errorLabel o label que receberá a mensagem
     * @param size o tamanho maximo permitido
     * @return true se não é vazio
     */
    public static boolean validaCampo(JTextField field, JLabel errorLabel, int size) {
        if (isEmpty(field)|| !isValidSize(field, size)) {
            errorLabel.setText(Methods.getTranslation("CampoObrigatorio"));
            return false;
        }
        return true;
    }
    
    /**
     * Valida o se o campo está vazio e adiciona a mensagem de erro no seu respectivo label
     * @param field o campo a ser validado
     * @param errorLabel o label que receberá a mensagem
     * @return true se não é vazio
     */
    public static boolean validaCampo(JTextArea field, JLabel errorLabel) {
        if (isEmpty(field)|| !isValidSize(field, 255)) {
            errorLabel.setText(Methods.getTranslation("CampoObrigatorio"));
            return false;
        }
        return true;
    }
    
    /**
     * Valida o se o campo tem item selecionado e adiciona a mensagem de erro no seu respectivo label
     * @param field o campo a ser validado
     * @param errorLabel o label que receberá a mensagem
     * @return true se tem item selecionado
     */
    public static boolean validaCampo(JComboBox field, JLabel errorLabel) {
        if (isEmpty(field)) {
            errorLabel.setText(Methods.getTranslation("CampoObrigatorio"));
            return false;
        }
        return true;
    }
    
    /**
     * Valida o se o campo recebeou um número válido e adiciona a mensagem de erro no seu respectivo label
     * @param field o campo a ser validado
     * @param errorLabel o label que receberá a mensagem
     * @return true se recebeu número válido
     */
    public static boolean validaNumero(JTextField field, JLabel errorLabel) {
        if (isEmpty(field)|| !isLong(field)) {
            errorLabel.setText(Methods.getTranslation("NumeroInvalido"));
            return false;
        }
        return true;
    }
    
    /**
     * Valida o se o campo recebeu telefone válido e adiciona a mensagem de erro no seu respectivo label
     * @param field o campo a ser validado
     * @param errorLabel o label que receberá a mensagem
     * @return true se recebeu telefone válido
     */
    public static boolean validaTelefone(JTextField field, JLabel errorLabel) {
        if (isEmpty(field)|| !isValidSize(field, 20)) {
            errorLabel.setText(Methods.getTranslation("TelefoneInvalido"));
            return false;
        }
        return true;
    }
    
    /**
     * Valida o se o campo recebeu cnpj válido e adiciona a mensagem de erro no seu respectivo label
     * @param field o campo a ser validado
     * @param errorLabel o label que receberá a mensagem
     * @return true se recebeu cnpj válido
     */
    public static boolean validaCnpj(JTextField field, JLabel errorLabel) {
        if (isEmpty(field) || !isValidSize(field, 18)) {
            errorLabel.setText(Methods.getTranslation("CnpjInvalido"));
            return false;
        }
        return true;
    }
    
    /**
     * Valida o se o campo recebeu cpf válido e adiciona a mensagem de erro no seu respectivo label
     * @param field o campo a ser validado
     * @param errorLabel o label que receberá a mensagem
     * @return true se recebeu cpf válido
     */
    public static boolean validaCpf(JTextField field, JLabel errorLabel) {
        if (isEmpty(field) || !isValidSize(field, 14)) {
            errorLabel.setText(Methods.getTranslation("CpfInvalido"));
            return false;
        }
        return true;
    }
    
    /**
     * Valida o se o campo data válida e adiciona a mensagem de erro no seu respectivo label
     * @param field o campo a ser validado
     * @param errorLabel o label que receberá a mensagem
     * @return true se recebeu data válida
     */
    public static boolean validaData(JDateChooser field, JLabel errorLabel) {
        String data = ((JTextField)field.getDateEditor().getUiComponent()).getText();
        if (data.isEmpty() || !isValidDate(data)) {
            errorLabel.setText(Methods.getTranslation("DataInvalida"));
            return false;
        }
        return true;
    }
    
    /**
     * Valida o se o combobox de sugestão foi tem item selecionado e adiciona a mensagem de erro no seu respectivo label
     * @param combo o campo a ser validado
     * @param errorLabel o label que receberá a mensagem
     * @return true se tem item selecionado
     */
    public static boolean validaComboBox(JComboBox combo, JLabel errorLabel) {
        if (!isComboBoxSelected(combo)) {
            errorLabel.setText(Methods.getTranslation("CampoObrigatorio"));
            return false;
        }
        return true;
    }
    
    /**
     * Valida o se o campo recebeu valor válido e adiciona a mensagem de erro no seu respectivo label
     * @param field o campo a ser validado
     * @param errorLabel o label que receberá a mensagem
     * @return true se recebou valor válido
     */
    public static boolean validaValor(JTextField field, JLabel errorLabel) {
        if (isEmpty(field)|| !isDouble(field)) {
            errorLabel.setText(Methods.getTranslation("ValorInvalido"));
            return false;
        }
        return true;
    }
    
    /**
     * Valida o se o group tem item marcado e adiciona a mensagem de erro no seu respectivo label
     * @param group o campo a ser validado
     * @param errorLabel o label que receberá a mensagem
     * @return true se tem item marcado
     */
    public static boolean validaButtonGroup(ButtonGroup group, JLabel errorLabel) {
        if (! isButtonGroupSelected(group)) {
            errorLabel.setText(Methods.getTranslation("CampoObrigatorio"));
            return false;
        }
        return true;
    }
    
    public static boolean isDateBeforeThen(JDateChooser from, JDateChooser to, JLabel edatato) {
        try {
            String vFrom = ((JTextField)from.getDateEditor().getUiComponent()).getText();
            String vTo = ((JTextField)to.getDateEditor().getUiComponent()).getText();
            
            String sfrom = DateHandler.getSqlDateTime(vFrom);
            String sto = DateHandler.getSqlDateTime(vTo);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateFrom = sdf.parse(sfrom);
            Date dateTo = sdf.parse(sto);
            
            if (dateFrom.compareTo(dateTo) < 0) {
                return true;
            } else {
                edatato.setText(Methods.getTranslation("DataMaiorQueODe"));
                return false;
            } 
        } catch (Exception error) {
            edatato.setText(Methods.getTranslation("DataMaiorQueODe"));
            return false;
        }
    }
    
}
