package Models;

/**
 * Modelo para usuário
 * @author Welison
 */
public class Usuario {
    private int Id;
    private String Cpf;
    private String Nome;
    private String Email;
    private String Celular;
    private String Telefone;
    private String Setor;
    private String Sexo;
    private String Permissao;
    private String Status;
    private String DataNascimento;
    private String Login;
    private String Senha;
    private String Created;
    
    public Usuario() {}
    
    public Usuario(String cpf, String nome, String email, String celular, String telefone, String setor, String sexo, String permissao, String dataNascimento) {
        Cpf = cpf;
        Nome = nome;
        Email = email;
        Celular = celular;
        Telefone = telefone;
        Setor = setor;
        Sexo = sexo;
        Permissao = permissao;
        DataNascimento = dataNascimento;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String Cpf) {
        this.Cpf = Cpf;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String Celular) {
        this.Celular = Celular;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }

    public String getSetor() {
        return Setor;
    }

    public void setSetor(String Setor) {
        this.Setor = Setor;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public String getPermissao() {
        return Permissao;
    }

    public void setPermissao(String Permissao) {
        this.Permissao = Permissao;
    }

    public String getDataNascimento() {
        return DataNascimento;
    }

    public void setDataNascimento(String DataNascimento) {
        this.DataNascimento = DataNascimento;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String Login) {
        this.Login = Login;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }

    public String getCreated() {
        return Created;
    }

    public void setCreated(String Created) {
        this.Created = Created;
    }
    
}
