package CustomFields;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.text.SimpleAttributeSet;

/**
 * Formata os campos de texto pra decimal
 * @author Welison
 */
public class FormataDecimal extends PlainDocument {

    private static final SimpleAttributeSet NULLATTRIBUTE = new SimpleAttributeSet();
    private final int qtdaDigitos;
    private final int casasDecimais;

    public FormataDecimal(int qtdaDigitos, int casasDecimais) {
        this.qtdaDigitos = qtdaDigitos;
        this.casasDecimais = casasDecimais;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) {
        try {
            String original = getText(0, getLength());
            //verifica se esta dentro do tamanho maximo definido
            if (original.length() <= qtdaDigitos) {
                StringBuilder mascarado = new StringBuilder();
                if (a != NULLATTRIBUTE) {
                    //limpa o campo
                    remove(-1, getLength());
                    mascarado.append((original + str).replaceAll("[^0-9]", ""));
                    for (int i = 0; i < mascarado.length(); i++) {
                        if (!Character.isDigit(mascarado.charAt(i))) {
                            mascarado.deleteCharAt(i);
                        }
                    }
                    Long number = new Long(mascarado.toString());
                    mascarado.replace(0, mascarado.length(), number.toString());

                    if (mascarado.length() < (casasDecimais + 1)) {

                        switch (casasDecimais) {
                        //
                            case 0:
                                break;
                            case 1:
                                if (mascarado.length() == 1) {
                                    mascarado.insert(0, ".");
                                    mascarado.insert(0, "0");
                                }   break;
                            case 2:
                                if (mascarado.length() == 1) {
                                    mascarado.insert(0, "0");
                                    mascarado.insert(0, ".");
                                    mascarado.insert(0, "0");
                                } else if (mascarado.length() == 2) {
                                    mascarado.insert(0, ".");
                                    mascarado.insert(0, "0");
                                }   break;
                            case 3:
                                switch (mascarado.length()) {
                                    case 1:
                                        mascarado.insert(0, "0");
                                        mascarado.insert(0, "0");
                                        mascarado.insert(0, ".");
                                        mascarado.insert(0, "0");
                                        break;
                                    case 2:
                                        mascarado.insert(0, "0");
                                        mascarado.insert(0, ".");
                                        mascarado.insert(0, "0");
                                        break;
                                    case 3:
                                        mascarado.insert(0, ".");
                                        mascarado.insert(0, "0");
                                        break;
                                    default:
                                        break;
                                }
                            default:
                                break;
                        }

                    } else {  //add o ponto separador de decimal conforme as casas decimais definidas
                        if (casasDecimais > 0) {
                            mascarado.insert(mascarado.length() - casasDecimais, ".");
                        }
                    }

                    super.insertString(0, mascarado.toString(), a);

                } else {
                    if (original.length() == 0) {
                        String vlPadrao = "";
                        super.insertString(0, vlPadrao, a);
                    }
                }
            }
        } catch (BadLocationException | NumberFormatException error) {}
    }

    @Override
    public void remove(int offs, int len) throws BadLocationException {
        if (len == getLength()) {
            super.remove(0, len);
            if (offs != -1) {
                insertString(0, "", NULLATTRIBUTE);
            }
        } else {
            String original = getText(0, getLength()).substring(0, offs) + getText(0, getLength()).substring(offs + len);
            super.remove(0, getLength());
            insertString(0, original, null);
        }
    }
}
