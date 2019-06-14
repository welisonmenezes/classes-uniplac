package Models;

/**
 * Modelo para cada item do Relatório de fornecedores
 * @author welison
 */
public class RelatorioFornecedor {

    private int codigo;
    private String nome;
    private String cnpj;
    private String data;
    private int totalNotas;
    
    public RelatorioFornecedor() { }

    public RelatorioFornecedor(int codigo, String nome, String cnpj, String data, int totalNotas) {
        this.codigo = codigo;
        this.nome = nome;
        this.cnpj = cnpj;
        this.data = data;
        this.totalNotas = totalNotas;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getTotalNotas() {
        return totalNotas;
    }

    public void setTotalNotas(int totalNotas) {
        this.totalNotas = totalNotas;
    }
    
    
}
