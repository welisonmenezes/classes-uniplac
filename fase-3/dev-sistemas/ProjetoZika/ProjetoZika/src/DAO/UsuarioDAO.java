package DAO;

import Models.RelatorioUsuario;
import Models.Usuario;
import Utils.DateHandler;
import Utils.FillModel;
import Utils.Methods;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

/**
 * Manipula os dados de usuários
 * @author welison
 */
public class UsuarioDAO {
    
    private Connection conn;
    private PreparedStatement stmt;
    private Statement st;
    private ResultSet rs;
    private final ConnectionFactory connFac;
    private final ArrayList<Usuario> usuarios = new ArrayList();
    private final FillModel helper = new FillModel();
    
    /**
     * método construtor, inicializa a conexão
     */
    public UsuarioDAO() {
        connFac = new ConnectionFactory();
    }
    
    /**
     * insere um novo usuário na base de dados
     * @param usuario o usuário a ser inserido
     * @return o id do último item inserido
     */
    public int inserir(Usuario usuario) {
        String sql = "INSERT INTO usuarios "
                + "(Cpf, Nome, Sexo, Email, Telefone, Celular, DataNascimento, Setor, Permissao, Login, Senha, Status, Created) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,SHA2(?, 224),?,?)";
        try {
            conn = connFac.getConexao();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, usuario.getCpf());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getSexo());
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getTelefone());
            stmt.setString(6, usuario.getCelular());
            stmt.setString(7, usuario.getDataNascimento());
            stmt.setString(8, usuario.getSetor());
            stmt.setString(9, usuario.getPermissao());
            stmt.setString(10, usuario.getLogin());
            stmt.setString(11, usuario.getSenha());
            stmt.setString(12, "Publish");
            java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            stmt.setDate(13, sqlDate);
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            int lastInsertedId = 0;
            if(rs.next()) {
                lastInsertedId = rs.getInt(1);
            }
            connFac.closeAll(rs, stmt, st, conn);
            return lastInsertedId;
        } catch(SQLException error) {
            Methods.getLogger().error("UsuarioDAO.inserir: " + error);
            throw new RuntimeException("UsuarioDAO.inserir: " + error);
        }
    }
    
    /**
     * altera o dado do usuario na base de dados
     * @param usuario o usuario a ser alterado
     */
    public void alterar(Usuario usuario) {
        String sql;
        if (! usuario.getSenha().equals("")) {
            sql = "UPDATE usuarios "
                + "SET Cpf=?, Nome=?, Sexo=?, Email=?, Telefone=?, Celular=?, "
                + "DataNascimento=?, Setor=?, Permissao=?, Login=?, Senha=SHA2(?, 224) "
                + "WHERE Id=?";
        } else {
            sql = "UPDATE usuarios "
                + "SET Cpf=?, Nome=?, Sexo=?, Email=?, Telefone=?, Celular=?, "
                + "DataNascimento=?, Setor=?, Permissao=?, Login=? "
                + "WHERE Id=?";
        }
        try {
            conn = connFac.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario.getCpf());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getSexo());
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getTelefone());
            stmt.setString(6, usuario.getCelular());
            stmt.setString(7, usuario.getDataNascimento());
            stmt.setString(8, usuario.getSetor());
            stmt.setString(9, usuario.getPermissao());
            stmt.setString(10, usuario.getLogin());
            if (! usuario.getSenha().equals("")) {
                stmt.setString(11, usuario.getSenha());
                stmt.setInt(12, usuario.getId());
            } else {
                stmt.setInt(11, usuario.getId());
            }
            stmt.execute();
            connFac.closeAll(rs, stmt, st, conn);
        } catch(SQLException error) {
            Methods.getLogger().error("UsuarioDAO.alterar: " + error);
            throw new RuntimeException("UsuarioDAO.alterar: " + error);
        }
    }
    
    /**
     * 'deleta' o usuário da visualização, na base de dados altera apenas o status para 'Deleted'
     * @param Id o Id do usuário a ser 'deletado'
     */
    public void deletar(String Id) {
        String sql = "UPDATE usuarios SET Status='Deleted' WHERE Id=?";
        try {
            conn = connFac.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, Id);
            stmt.execute();
            connFac.closeAll(rs, stmt, st, conn);
        } catch(SQLException error) {
            Methods.getLogger().error("UsuarioDAO.deletar: " + error);
            throw new RuntimeException("UsuarioDAO.deletar: " + error);
        }
    }
    
    /**
     * seleciona um usuário da base de dados pelo seu Cpf
     * @param Cpf o Cpf do usuário a ser retornado
     * @return o usuário com Id correspondente
     */
    public Usuario selecionarPorCpf(String Cpf) {
        String sql = "SELECT * FROM usuarios WHERE Cpf =?";
        try {
            conn = connFac.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, Cpf);
            rs = stmt.executeQuery();
            Usuario usuario = new Usuario();
            while(rs.next()) {
                this.helper.fillUsuario(usuario, rs);
            }
            connFac.closeAll(rs, stmt, st, conn);
            return usuario;
        } catch (SQLException error) {
            Methods.getLogger().error("UsuarioDAO.selecionarPorCpf: " + error);
            throw new RuntimeException("UsuarioDAO.selecionarPorCpf: " + error);
        }
    }
    
    /**
     * seleciona um usuário da base de dados pelo seu Id
     * @param Id o Id do usuário a ser retornado
     * @return o usuário com Id correspondente
     */
    public Usuario selecionarPorId(String Id) {
        String sql = "SELECT * FROM usuarios WHERE Id = ?";
        int queryId = Integer.parseInt(Id);
        try {
            conn = connFac.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, queryId);
            rs = stmt.executeQuery();
            Usuario usuario = new Usuario();
            while(rs.next()) {
                this.helper.fillUsuario(usuario, rs);
            }
            connFac.closeAll(rs, stmt, st, conn);
            return usuario;
        } catch (SQLException error) {
            Methods.getLogger().error("UsuarioDAO.selecionarPorId: " + error);
            throw new RuntimeException("UsuarioDAO.selecionarPorId: " + error);
        }
    }
    
    /**
     * seleciona os usuários correspondentes ao dado nome
     * @param nome o nome desejado
     * @return uma lista de usuários correspondentes
     */
    public ArrayList<Usuario> selecionarPorNome(String nome) {
        String sql = "SELECT * FROM usuarios "
                + "WHERE Status != 'Deleted' AND Nome LIKE ? LIMIT 50";
        try {
            conn = connFac.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%"+nome+"%");
            rs = stmt.executeQuery();
            while(rs.next()) {
                Usuario usuario = new Usuario();
                this.helper.fillUsuario(usuario, rs);
                usuarios.add(usuario);
            }
            connFac.closeAll(rs, stmt, st, conn);
            return usuarios;
        } catch(SQLException error) {
            Methods.getLogger().error("UsuarioDAO.selecionarPorNome: " + error);
            throw new RuntimeException("UsuarioDAO.selecionarPorNome: " + error);
        }
    }
    
    /**
     * seleciona os usuários com permissão de admin ou amoxarife correspondentes ao dado nome
     * @param nome o nome desejado
     * @param permissao permissão a ser excluída da busca
     * @return uma lista de usuários correspondentes
     */
    public ArrayList<Usuario> selecionarPorNomeEPermissaoExcluida(String nome, String permissao) {
        String sql = "SELECT * FROM usuarios "
                + "WHERE Status != 'Deleted' "
                + "AND Permissao != ? "
                + "AND Nome LIKE ? LIMIT 50";
        try {
            conn = connFac.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, permissao);
            stmt.setString(2, "%"+nome+"%");
            rs = stmt.executeQuery();
            while(rs.next()) {
                Usuario usuario = new Usuario();
                this.helper.fillUsuario(usuario, rs);
                usuarios.add(usuario);
            }
            connFac.closeAll(rs, stmt, st, conn);
            return usuarios;
        } catch(SQLException error) {
            Methods.getLogger().error("UsuarioDAO.selecionarPorNome: " + error);
            throw new RuntimeException("UsuarioDAO.selecionarPorNome: " + error);
        }
    }
    
    /**
     * seleciona um usuário da base de dados pelo seu Id
     * @param login o login
     * @param senha a senha
     * @return o usuário com Id correspondente
     */
    public Usuario selecionarAposLogin(String login, String senha) {
        String sql = "SELECT * FROM usuarios WHERE BINARY Login = ? AND BINARY Senha = SHA2(?, 224) AND Status != 'Deleted'";
        try {
            conn = connFac.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            Usuario usuario = new Usuario();
            while(rs.next()) {
                this.helper.fillUsuario(usuario, rs);
            }
            connFac.closeAll(rs, stmt, st, conn);
            return usuario;
        } catch (SQLException error) {
            Methods.getLogger().error("UsuarioDAO.selecionarPorId: " + error);
            throw new RuntimeException("UsuarioDAO.selecionarPorId: " + error);
        }
    }
    
    /**
     * conta quantos usários exsitem com o dado cpf
     * @param cpf o cpf a ser buscado
     * @return o total de resultados
     */
    public int temCpf(String cpf) {
        String sql = "SELECT COUNT(Id) FROM usuarios WHERE Cpf = ?";
        try {
            conn = connFac.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();
            rs.next();
            int ret = rs.getInt(1);
            connFac.closeAll(rs, stmt, st, conn);
            return ret;
        } catch(SQLException error) {
            Methods.getLogger().error("UsuarioDAO.temCpf: " + error);
            throw new RuntimeException("UsuarioDAO.temCpf: " + error);
        }
    }
    
    /**
     * conta quantos usários exsitem com o dado login
     * @param login o login a ser buscado
     * @return o total de resultados
     */
    public int temLogin(String login) {
        String sql = "SELECT COUNT(Id) FROM usuarios WHERE Login = ?";
        try {
            conn = connFac.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, login);
            rs = stmt.executeQuery();
            rs.next();
            int ret = rs.getInt(1);
            connFac.closeAll(rs, stmt, st, conn);
            return ret;
        } catch(SQLException error) {
            Methods.getLogger().error("UsuarioDAO.temLogin: " + error);
            throw new RuntimeException("UsuarioDAO.temLogin: " + error);
        }
    }
    
    /**
     * seleciona os usuários correspondentes aos parâmetros de filtragem e paginação
     * @param params os parâmetros de filtragem e paginação
     * @return uma lista de usuários correspondentes
     */
    public ArrayList<Usuario> selecionar(Properties params) {
        String sql = buildSelectQuery(params, false);
        try {
            conn = connFac.getConexao();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                Usuario usuario = new Usuario();
                this.helper.fillUsuario(usuario, rs);
                usuarios.add(usuario);
            }
            connFac.closeAll(rs, stmt, st, conn);
            return usuarios;
        } catch(SQLException error) {
            Methods.getLogger().error("UsuarioDAO.selecionar: " + error);
            throw new RuntimeException("UsuarioDAO.selecionar: " + error);
        }
    }
    
    /**
     * o total de usuários que correspondem aos parâmetros de filtro e paginação
     * @param params os parâmetros de filtro e paginação
     * @return o total de usuários
     */
    public int total(Properties params) {
        String sql = buildSelectQuery(params, true);
        try {
            conn = connFac.getConexao();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            int total = rs.getInt(1);
            connFac.closeAll(rs, stmt, st, conn);
            return total;
        } catch(SQLException error) {
            Methods.getLogger().error("UsuarioDAO.total: " + error);
            throw new RuntimeException("UsuarioDAO.total: " + error);
        }
    }
    
    /**
     * Auxiliza na construção da query de seleção com base nos parametros passados
     * @param params os parâmetros de filtro e paginação
     * @param isCount true se é pra retorna apenas o total
     * @return a query para ser usada na seleção
     */
    private String buildSelectQuery (Properties params, boolean isCount) {
        int offset = Integer.parseInt(params.getProperty("offset", "0"));
        String nome = Methods.scapeSQL(params.getProperty("nome", ""));
        String email = Methods.scapeSQL(params.getProperty("email", ""));
        String setor = Methods.scapeSQL(params.getProperty("setor", ""));
        String sql;
        
        if (! isCount) {
            sql = "SELECT * FROM usuarios WHERE Status != 'Deleted'";
        } else {
            sql = "SELECT COUNT(Id) FROM usuarios WHERE Status != 'Deleted'";
        }
        
        if (! nome.equals("")) {
            sql += " AND Nome LIKE '%" + nome + "%'";
        }
        
        if (! email.equals("")) {
            sql += " AND Email LIKE '%" + email + "%'";
        }
        
        if (! setor.equals("")) {
            sql += " AND Setor = '" + setor + "'";
        }
        
        if (! isCount) {
            sql += " ORDER BY " + params.getProperty("orderby", "Id") + " " + params.getProperty("order", "DESC");
            sql += " LIMIT 10 OFFSET " + (offset);
        }
        return sql;
    }
    
     /**
     * constrói a query baseado nos dados parâmetros e faz a consulta para o relatório de usuários
     * @param params os parâmetros de filtro e paginação
     * @return Uma lista de RelatorioUsuario
     */
    public ArrayList<RelatorioUsuario> relatorioUsuario(Properties params) {
        
        String dataDe = Methods.scapeSQL(params.getProperty("dataDe", ""));
        String dataAte = Methods.scapeSQL(params.getProperty("dataAte", ""));
        String statusSelecionado = Methods.scapeSQL(params.getProperty("statusSelecionado", ""));
        String permissaoSelecionada = Methods.scapeSQL(params.getProperty("permissaoSelecionada", ""));
        
        String sql = "SELECT Id AS codigo, "
                + "Setor AS setor, "
                + "Permissao AS permissao, "
                + "Nome AS nome, "
                + "Created AS data "
                + " FROM usuarios "
                + "WHERE usuarios.Id > 0 ";
        
        if (! statusSelecionado.equals("")) {
            sql += "AND usuarios.Setor = '" + statusSelecionado + "' ";
        }
        if (! permissaoSelecionada.equals("")) {
            sql += "AND usuarios.Permissao = '" + permissaoSelecionada + "' ";
        }
        if (! dataDe.equals("") ) {
            dataDe = DateHandler.getSqlDateTime(dataDe);
            sql += "AND usuarios.Created >= '" + dataDe + "' ";
        }
        if (! dataAte.equals("") ) {
            dataAte = DateHandler.getSqlDateTime(dataAte);
            sql += "AND usuarios.Created <= '" + dataAte + "' ";
        }
        
        sql += "AND usuarios.Status != 'Deleted' "
                + "GROUP BY usuarios.Id "
                + "ORDER BY usuarios.Id DESC";
        
        try {
            conn = connFac.getConexao();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            ArrayList<RelatorioUsuario> relatorioUsuarios = new ArrayList<>();
            while(rs.next()) {
                RelatorioUsuario item = new RelatorioUsuario();
                this.helper.fillRelatorioUsuario(item, rs);
                relatorioUsuarios.add(item);
            }
            connFac.closeAll(rs, stmt, st, conn);
            return relatorioUsuarios;
        } catch(SQLException error) {
            Methods.getLogger().error("UsuarioDAO.relatorioUsuario: " + error);
            throw new RuntimeException("UsuarioDAO.relatorioUsuario: " + error);
        }
    }
}
