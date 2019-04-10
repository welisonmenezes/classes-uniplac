package CustomFields;

import Utils.Methods;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 * Adiciona um JButton numa celula da JTable
 * 
 * @author Welison
 */
public class ButtonEditor extends DefaultCellEditor {

    protected JButton button;
    private String label;
    private boolean isPushed;

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        
        // resolve os ícones
        String text = value.toString();
        if (text.equals(Methods.getTranslation("Editar"))) {
            button.setIcon(new ImageIcon(this.getClass().getResource("/sources/edit.png")));
        } else if (text.equals(Methods.getTranslation("Excluir"))) {
            button.setIcon(new ImageIcon(this.getClass().getResource("/sources/delete.png")));
        } else if (text.equals(Methods.getTranslation("Ver"))) {
            button.setIcon(new ImageIcon(this.getClass().getResource("/sources/view.png")));
        } else if (text.equals(Methods.getTranslation("Cancelar"))) {
            button.setIcon(new ImageIcon(this.getClass().getResource("/sources/cancel.png")));
        } else if (text.equals(Methods.getTranslation("Ver/Editar"))) {
            button.setIcon(new ImageIcon(this.getClass().getResource("/sources/edit.png")));
        } else if (text.equals(Methods.getTranslation("Entregar"))) {
            button.setIcon(new ImageIcon(this.getClass().getResource("/sources/deliver.png")));
        } else if (text.equals(Methods.getTranslation("Remover"))) {
            button.setIcon(new ImageIcon(this.getClass().getResource("/sources/cancel.png")));
        }
        
        label = (value == null) ? "" : value.toString();
        //button.setText(label);
        button.setActionCommand(label);
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            this.buttonAction();
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
    
    /**
     * Metodo a ser sobreescrito, agindo como um callback do evento click do botão
     */
    public void buttonAction(){
        //Override it
    }
}
