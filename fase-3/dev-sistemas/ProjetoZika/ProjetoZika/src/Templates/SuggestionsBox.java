/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Templates;

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
 *
 * @author welis
 */
public class SuggestionsBox {
    
    private final JPanel Panel;
    private final JTextField Field;
    private final JComboBox Combo;
    private final int Width;
    private final ArrayList<ComboItem> Model;
    
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
    
    private void addStyles() {
        Panel.setLayout(new AbsoluteLayout());
        Panel.setPreferredSize(new Dimension(Width, 39));
        Panel.setOpaque(false);
        
        defaultField(Field, Width);
        defaultComboBox(Combo, Width, 39);
        
        Panel.add(Field, new AbsoluteConstraints(0, 0, -1, -1));
        Panel.add(Combo, new AbsoluteConstraints(0, 0, -1, -1));
    }
    
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
    
    private void setSelectedItem(String value) {
        for (int i = 0; i < Model.size(); i++) {
            ComboItem item = (ComboItem) Model.get(i);
            if (value.equals(item.getDescription())) {
                Combo.setSelectedItem(item);
                break;
            }
        }
    }
    
    public ArrayList<ComboItem> addElements() {
        System.out.println("Override it!");
        return new ArrayList<>();
    }
    
    public void afterSelectItem() {
        System.out.println("Override it!");
    }
}
