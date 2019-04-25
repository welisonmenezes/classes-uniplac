package Models;

/**
 * Modelo para estoqueAviso
 * @author welison
 */
public class EstoqueAviso {
    private Produto Produto;
    private int Quantidade;
    
    public EstoqueAviso(Produto produto, int quantidade) {
        Produto = produto;
        Quantidade = quantidade;
    }

    public Produto getProduto() {
        return Produto;
    }

    public void setProduto(Produto Produto) {
        this.Produto = Produto;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    }
    
}
