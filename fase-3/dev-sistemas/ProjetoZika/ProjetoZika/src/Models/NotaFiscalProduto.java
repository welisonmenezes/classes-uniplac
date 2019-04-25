package Models;

/**
 * Modelo para notas fiscais produtos
 * @author welison
 */
public class NotaFiscalProduto {
    private NotaFiscal NotaFiscal;
    private Produto Produto;
    private int Quantidade;
    private double Valor;
    private String Created;
    
    public  NotaFiscalProduto() {}
    
    public NotaFiscalProduto(NotaFiscal notaFiscal, Produto produto, int quantidade, double valor, String data) {
        NotaFiscal = notaFiscal;
        Produto = produto;
        Quantidade = quantidade;
        Valor = valor;
        Created = data;
    }
    
    public NotaFiscalProduto(Produto produto, int quantidade, double valor, String data) {
        Produto = produto;
        Quantidade = quantidade;
        Valor = valor;
        Created = data;
    }

    public NotaFiscal getNotaFiscal() {
        return NotaFiscal;
    }

    public void setNotaFiscal(NotaFiscal NotaFiscal) {
        this.NotaFiscal = NotaFiscal;
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

    public double getValor() {
        return Valor;
    }

    public void setValor(double Valor) {
        this.Valor = Valor;
    }

    public String getCreated() {
        return Created;
    }

    public void setCreated(String Created) {
        this.Created = Created;
    }
    
}
