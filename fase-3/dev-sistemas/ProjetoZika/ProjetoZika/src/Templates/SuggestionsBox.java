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
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;
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
    
    private JPanel Panel;
    private JTextField Field;
    private JComboBox Combo;
    private int Width;
    private Vector Model;
    
    public SuggestionsBox(JPanel panel, JTextField field, JComboBox combo, int width) {
        Panel = panel;
        Field = field;
        Combo = combo;
        Width = width;
        Model = new Vector();
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
        Combo.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                
                JComboBox cb = (JComboBox) e.getSource();
                Object item = e.getActionCommand();

                if (item.equals("comboBoxChanged") && cb.getItemCount() > 0) {
                    String selected = cb.getSelectedItem().toString();
                    if (!selected.equals("")) {
                        Field.setText(selected);
                    }
                }
            }
        });
    }
    
    private void addTextFieldEvent() {
        Field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                Combo.removeAll();
                Combo.revalidate();
                Model.clear();
                Model.addElement(new ComboItem(0, ""));
                ArrayList<ComboItem> elements = addElements();
                elements.forEach((element) -> {
                    Model.add(element);
                });
                Combo.setModel(new DefaultComboBoxModel(Model));
                Combo.setRenderer( new ItemRenderer() );
                Combo.showPopup();
            }
        });
    }
    
    public ArrayList<ComboItem> addElements() {
        System.out.println("Override it!");
        return new ArrayList<ComboItem>();
    }
}
