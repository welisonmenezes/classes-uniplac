package Models;

/**
 * Modelo para cada item do Relatório de notas fiscais
 * @author welison
 */
public class RelatorioNota {
    private int codigo;
    private String produtos;
    private String entrada;
    private String fornecedore;
    private long numero;
    private double valor;
    
    public RelatorioNota() {}
    
    public RelatorioNota(int codigo, String produtos, String entrada, String fornecedore, long numero, double valor) {
        this.codigo = codigo;
        this.produtos = produtos;
        this.entrada = entrada;
        this.fornecedore = fornecedore;
        this.numero = numero;
        this.valor = valor;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getProdutos() {
        return produtos;
    }

    public void setProdutos(String produtos) {
        this.produtos = produtos;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getFornecedore() {
        return fornecedore;
    }

    public void setFornecedore(String fornecedore) {
        this.fornecedore = fornecedore;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}
