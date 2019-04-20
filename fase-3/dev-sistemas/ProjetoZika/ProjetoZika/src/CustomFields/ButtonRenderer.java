package CustomFields;

import Utils.Methods;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

/**
 * Renderizador de células da JTable
 * @author Welison
 */
public class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
        setPreferredSize(new Dimension(35, 35));
        
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(UIManager.getColor("Button.background"));
        }
        
        // resolve os ícones
        String text = value.toString();
        if (text.equals(Methods.getTranslation("Editar"))) {
            setIcon(new ImageIcon(this.getClass().getResource("/sources/edit.png")));
        } else if (text.equals(Methods.getTranslation("Excluir"))) {
            setIcon(new ImageIcon(this.getClass().getResource("/sources/delete.png")));
        } else if (text.equals(Methods.getTranslation("Ver"))) {
            setIcon(new ImageIcon(this.getClass().getResource("/sources/view.png")));
        } else if (text.equals(Methods.getTranslation("Cancelar"))) {
            setIcon(new ImageIcon(this.getClass().getResource("/sources/cancel.png")));
        } else if (text.equals(Methods.getTranslation("Ver/Editar"))) {
            setIcon(new ImageIcon(this.getClass().getResource("/sources/edit.png")));
        } else if (text.equals(Methods.getTranslation("Entregar"))) {
            setIcon(new ImageIcon(this.getClass().getResource("/sources/deliver.png")));
        } else if (text.equals(Methods.getTranslation("Remover"))) {
            setIcon(new ImageIcon(this.getClass().getResource("/sources/cancel.png")));
        }
        
        if (text.equals("")) {
            setEnabled(false);
            setVisible(false);
            setIcon(null);
        } else {
            setEnabled(true);
            setVisible(true);
        }
        
        //setText(value.toString());
        setText("");
        setActionCommand(value.toString());
        return this;
    }
}
