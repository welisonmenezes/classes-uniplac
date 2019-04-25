package CustomFields;

/**
 * Modelo usado pelo combobox de sugestões
 * @author welison
 */
public class ComboItem {
    private final int id;
    private final String description;

    /**
     * inicializa o item de combobox
     * @param id o id
     * @param description o texto exibido
     */
    public ComboItem(int id, String description) {
        this.id = id;
        this.description = description;
    }
    
    /**
     * retorna o id do item
     * @return o id
     */
    public int getId() {
        return id;
    }
    
    /**
     * retonra o texto do item
     * @return o texto
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}