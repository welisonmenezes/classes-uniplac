package CustomFields;

import static Utils.Styles.defaultComboBox;
import static Utils.Styles.defaultField;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 * Comobobox de sugestão
 * @author welison
 */
public class SuggestionsBox {
    
    private final JPanel Panel;
    private final JTextField Field;
    private final JComboBox Combo;
    private final int Width;
    private final ArrayList<ComboItem> Model;
    
    /**
     * Inicializa o combobox de sugestão
     * @param panel o JPanel onde os elementos serão inseridos
     * @param field o JTextField onde será digitado a busca
     * @param combo o JComboBox onde será adicionado as sugestões
     * @param width a largura do elemento
     */
    public SuggestionsBox(JPanel panel, JTextField field, JComboBox combo, int width) {
        Panel = panel;
        Field = field;
        Combo = combo;
        Width = width;
        Model = new ArrayList();
        addStyles();
        addComboboxEvent();
        addTextFieldEvent();
    }
    
    /**
     * Adiciona estilos aos elementos e os posiciona no painel container
     */
    private void addStyles() {
        Panel.setLayout(new AbsoluteLayout());
        Panel.setPreferredSize(new Dimension(Width, 39));
        Panel.setOpaque(false);
        
        defaultField(Field, Width);
        defaultComboBox(Combo, Width, 39);
        
        Panel.add(Field, new AbsoluteConstraints(0, 0, -1, -1));
        Panel.add(Combo, new AbsoluteConstraints(0, 0, -1, -1));
    }
    
    /**
     * Respondo à seleção de um item do combobox e adicona o texto no campo de busca
     */
    private void addComboboxEvent() {
        Combo.addActionListener ((ActionEvent e) -> {
            JComboBox cb = (JComboBox) e.getSource();
            Object item = e.getActionCommand();
            
            if (item.equals("comboBoxChanged") && cb.getItemCount() > 0) {
                String selected = cb.getSelectedItem().toString();
                if (!selected.equals("")) {
                    Field.setText(selected);
                    afterSelectItem();
                }
            }
        });
    }
    
    /**
     * Responde aos KeyKvents do campo de busca
     */
    private void addTextFieldEvent() {
        Field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                handleKeyEvents(e);
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyEvents(e);
            }
            
            @Override
            public void keyTyped(KeyEvent e) {
                handleKeyEvents(e);
            }
        });
    }
    
    /**
     * Reseta o combobox e adiciona novos elementos retornados por addElements()
     * @param e o KeyEvent disparado pelo camopo de busca
     */
    private void handleKeyEvents(KeyEvent e) {
        JTextField tf = (JTextField) e.getSource();
        Combo.removeAll();
        Combo.revalidate();
        Model.clear();
        Model.add(new ComboItem(0, ""));
        ArrayList<ComboItem> elements = addElements();
        elements.forEach((element) -> {
            Model.add(element);
        });
        Combo.setModel(new DefaultComboBoxModel(Model.toArray()));
        Combo.setRenderer( new ItemRenderer() );
        Combo.showPopup();

        setSelectedItem(tf.getText());
    }
    
    /**
     * Seta como selecionado o texto do campo de busca que coincide com algum item do combobox
     * @param value o texto digitado no campo de busca
     */
    private void setSelectedItem(String value) {
        Combo.setSelectedIndex(0);
        for (ComboItem Model1 : Model) {
            ComboItem item = (ComboItem) Model1;
            if (value.equals(item.getDescription())) {
                Combo.setSelectedItem(item);
                break;
            }
        }
    }
    
    /**
     * Adiciona novos elementos no comobobox. (Método a ser sobreescrito)
     * @return um ArrayList com os novos elementos a serem adicionados no combobox
     */
    public ArrayList<ComboItem> addElements() {
        //Override it!
        return new ArrayList<>();
    }
    
    /**
     * Método chamado como callback após a seleção de algum item do combobox (Método a ser sobreescrito)
     */
    public void afterSelectItem() {
        //Override it!
    }
}
