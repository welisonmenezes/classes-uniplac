package CustomFields;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * limita quantidade de caracteres no campo
 * @author Welison
 */
public class MaxSize extends PlainDocument {
    private final int maxSize;
    
    public MaxSize(int maxSize) {  
        super();  
        this.maxSize = maxSize;  
    }
    
    @Override
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
