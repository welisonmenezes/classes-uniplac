package Models;

/**
 * Modelo para fornecedores
 * 
 * @author Welison
 */
public class Fornecedor {
    private int Id;
    private String Nome;
    private String Cnpj;
    private String Telefone;
    private String Data;
    
    public Fornecedor(){};
    
    public Fornecedor(int id, String nome, String cnpj, String telefone, String data){
        this.Id = id;
        this.Nome = nome;
        this.Cnpj = cnpj;
        this.Telefone = telefone;
        this.Data = data;
    }

    public int getID() {
        return Id;
    }

    public void setID(int ID) {
        this.Id = ID;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getCnpj() {
        return Cnpj;
    }

    public void setCnpj(String Cnpj) {
        this.Cnpj = Cnpj;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }
}
