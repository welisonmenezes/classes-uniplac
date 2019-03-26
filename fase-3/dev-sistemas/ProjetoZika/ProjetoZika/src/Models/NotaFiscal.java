package Models;

/**
 * Modelo para Notas Fiscais
 * 
 * @author Welison
 */
public class NotaFiscal {
    private int Id;
    private long Numero;
    private int Serie;
    private float Valor;
    private String Status;
    private String Data;
    private Fornecedor Fornecedor;
    private String Created;
    
    public NotaFiscal() {}
    
    public NotaFiscal(long numero, int serie, float valor, String data, Fornecedor fornecedor) {
        Numero = numero;
        Serie = serie;
        Valor = valor;
        Data = data;
        Fornecedor = fornecedor;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public long getNumero() {
        return Numero;
    }

    public void setNumero(long Numero) {
        this.Numero = Numero;
    }

    public int getSerie() {
        return Serie;
    }

    public void setSerie(int Serie) {
        this.Serie = Serie;
    }

    public float getValor() {
        return Valor;
    }

    public void setValor(float Valor) {
        this.Valor = Valor;
    }

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public Fornecedor getFornecedor() {
        return Fornecedor;
    }

    public void setFornecedor(Fornecedor Fornecedor) {
        this.Fornecedor = Fornecedor;
    }

    public String getCreated() {
        return Created;
    }

    public void setCreated(String Created) {
        this.Created = Created;
    }
    
}
