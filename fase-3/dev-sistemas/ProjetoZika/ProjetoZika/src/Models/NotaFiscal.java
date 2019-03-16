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
    private String Cnpj;
    private float Valor;
    private String Status;
    private String Data;
    
    public NotaFiscal() {}
    
    public NotaFiscal(long numero, int serie, String cnpj, float valor, String data) {
        Numero = numero;
        Serie = serie;
        Cnpj = cnpj;
        Valor = valor;
        Data = data;
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

    public String getCnpj() {
        return Cnpj;
    }

    public void setCnpj(String Cnpj) {
        this.Cnpj = Cnpj;
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
    
    
}
