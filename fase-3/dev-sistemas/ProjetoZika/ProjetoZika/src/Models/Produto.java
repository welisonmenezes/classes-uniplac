package Models;

/**
 * Modelo para produto
 * @author Welison
 */
public class Produto {
    private int Id;
    private String Nome;
    private String Unidade;
    private String Descricao;
    private String Status;
    private String Created;
    private int Total;
    
    public Produto() {}
    
    public Produto(int id, String nome, String unidade, String descricao, String created) {
        Id = id;
        Nome = nome;
        Unidade = unidade;
        Descricao = descricao;
        Created = created;
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

    public String getUnidade() {
        return Unidade;
    }

    public void setUnidade(String Unidade) {
        this.Unidade = Unidade;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
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

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }
    
}
