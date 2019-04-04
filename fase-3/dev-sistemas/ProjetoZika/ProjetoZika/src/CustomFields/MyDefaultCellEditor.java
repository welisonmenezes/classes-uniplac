/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomFields;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Welison
 */
public class MyDefaultCellEditor extends DefaultCellEditor {
    
    protected JComboBox combo;
    
    public MyDefaultCellEditor(JComboBox combo) {
        super(new JTextField());
        this.combo = combo;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (value != null) {
            for(int i = 0; i <= Integer.parseInt(value.toString()); i++) {
                combo.addItem(i);
            }
        }
      return combo;
    }
}
