package CustomFields;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

/**
 * Renderer usado pelo combobox de sugestão
 * @author welison
 */
public class ItemRenderer extends BasicComboBoxRenderer {
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        
        super.getListCellRendererComponent(list, value, index,
            isSelected, cellHasFocus);

        if (value != null) {
            ComboItem item = (ComboItem)value;
            setText( item.getDescription());
            if (index == -1) {
                setText(item.getId() + "");
            }
        }
        return this;
    }
}
