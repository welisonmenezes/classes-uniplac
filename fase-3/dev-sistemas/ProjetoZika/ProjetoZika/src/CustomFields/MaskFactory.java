/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomFields;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author welison
 */
public class MaskFactory {

    public static DefaultFormatterFactory setMaskCpf(){
        MaskFormatter mask = null;
        try{
            mask = new MaskFormatter("###.###.###-##");
            mask.setPlaceholderCharacter('_');
        }catch(ParseException error){
            System.out.println("MaskFactory.setMaskCpf: " + error);
        }
        return new DefaultFormatterFactory(mask,mask);
    }
    
    public static DefaultFormatterFactory setMaskCnpj(){
        MaskFormatter mask = null;
        try{
            mask = new MaskFormatter("##.###.###/####-##");
            mask.setPlaceholderCharacter('_');
        }catch(ParseException error){
            System.out.println("MaskFactory.setMaskCpf: " + error);
        }
        return new DefaultFormatterFactory(mask,mask);
    }
}
