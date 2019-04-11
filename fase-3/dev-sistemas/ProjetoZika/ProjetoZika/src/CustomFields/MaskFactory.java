/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomFields;

import java.text.ParseException;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 * Fabrica máscara para a aplicação
 * @author welison
 */
public class MaskFactory {
    
    /**
     * máscara para cpf
     * @return DefaultFormatterFactory para ser usado como máscara
     */
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
    
    /**
     * máscara para cnpj
     * @return DefaultFormatterFactory para ser usado como máscara
     */
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
