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
    private String Created;
    private String Status;
    
    public Fornecedor(){};
    
    public Fornecedor(int id, String nome, String cnpj, String telefone, String created){
        this.Id = id;
        this.Nome = nome;
        this.Cnpj = cnpj;
        this.Telefone = telefone;
        this.Created = created;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
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

    public String getCreated() {
        return Created;
    }

    public void setCreated(String Created) {
        this.Created = Created;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
}
