/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Templates;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

/**
 *
 * @author welis
 */
public class ItemRenderer extends BasicComboBoxRenderer {
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        
        super.getListCellRendererComponent(list, value, index,
            isSelected, cellHasFocus);

        if (value != null) {
            ComboItem item = (ComboItem)value;
            setText( item.getDescription());
        }

        if (index == -1) {
            ComboItem item = (ComboItem)value;
            setText( "" + item.getId());
        }
        
        return this;
    }
}
