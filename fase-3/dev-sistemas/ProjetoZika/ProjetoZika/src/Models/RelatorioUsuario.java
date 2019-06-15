package Models;

/**
 * Modelo para cada item do Relatório de usuários
 * @author welison
 */
public class RelatorioUsuario {
    
    private int codigo;
    private String nome;
    private String setor;
    private String permissao;
    private String data;
    
    public RelatorioUsuario() {}
    
    public RelatorioUsuario(int codigo, String nome, String setor, String permissao, String data){
        this.codigo = codigo;
        this.nome = nome;
        this.setor = setor;
        this.permissao = permissao;
        this.data = data;
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

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    
}
