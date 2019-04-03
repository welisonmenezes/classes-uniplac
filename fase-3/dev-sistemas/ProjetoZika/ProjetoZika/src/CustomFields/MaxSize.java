/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomFields;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Welison
 */
public class MaxSize extends PlainDocument {
    private int maxSize;
    
    public MaxSize(int maxSize) {  
        super();  
        this.maxSize = maxSize;  
    }
    
    public void insertString(int offset, String str, AttributeSet attr)  
                    throws BadLocationException {
        
        if (str == null) return;  
   
        //Define a condição para aceitar qualquer número de caracteres
        if (maxSize <= 0){
            super.insertString(offset, str, attr);
            return;
        }
  
        int tam = (getLength() + str.length());
        //Se o tamanho final for menor, chama insertString() aceitando a String
        if (tam <= maxSize)  super.insertString(offset, str, attr);
    
    }
}
