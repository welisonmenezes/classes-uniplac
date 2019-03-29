/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Usuario;
import Utils.Methods;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

/**
 *
 * @author welis
 */
public class UsuarioDAO {
    
    private final Connection conn;
    private PreparedStatement stmt;
    private Statement st;
    private ResultSet rs;
    private final ArrayList<Usuario> usuarios = new ArrayList();
    
    /**
     * método construtor, inicializa a conexão
     */
    public UsuarioDAO() {
        conn = new ConnectionFactory().getConexao();
    }
    
    /**
     * insere um novo usuário na base de dados
     * @param produto o usuário a ser inserido
     * @return o id do último item inserido
     */
    public int inserir(Usuario usuario) {
        String sql = "INSERT INTO usuarios "
                + "(Cpf, Nome, Sexo, Email, Telefone, Celular, DataNascimento, Setor, Permissao, Login, Senha, Status, Created) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
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
            ResultSet rs = stmt.getGeneratedKeys();
            int lastInsertedId = 0;
            if(rs.next()) {
                lastInsertedId = rs.getInt(1);
            }
            stmt.close();
            return lastInsertedId;
        } catch(Exception error) {
            throw new RuntimeException("UsuarioDAO.inserir: " + error);
        }
    }
    
    /**
     * altera o dado do usuario na base de dados
     * @param usuario o usuario a ser alterado
     */
    public void alterar(Usuario usuario) {
        String sql = "UPDATE usuarios "
                + "SET Cpf=?, Nome=?, Sexo=?, Email=?, Telefone=?, Celular=?, "
                + "DataNascimento=?, Setor=?, Permissao=?, Login=?, Senha=? "
                + "WHERE Id=?";
        try {
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
            stmt.setString(11, usuario.getSenha());
            stmt.setInt(12, usuario.getId());
            stmt.execute();
            stmt.close();
        } catch(Exception error) {
            throw new RuntimeException("UsuarioDAO.alterar: " + error);
        }
    }
    
    /**
     * 'deleta' o usuário da visualização, na base de dados altera apenas o status para 'Deleted'
     * @param Cpf o Cpf do usuário a ser 'deletado'
     */
    public void deletar(String Cpf) {
        String sql = "UPDATE usuarios SET Status='Deleted' WHERE Cpf=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, Cpf);
            stmt.execute();
            stmt.close();
        } catch(Exception error) {
            throw new RuntimeException("UsuarioDAO.deletar: " + error);
        }
    }
    
    /**
     * seleciona um usuário da base de dados pelo seu Id
     * @param Cpf o Cpf do usuário a ser retornado
     * @return o usuário com Id correspondente
     */
    public Usuario selecionarPorCpf(String Cpf) {
        String sql = "SELECT * FROM usuarios WHERE Cpf = " + Cpf;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            Usuario usuario = new Usuario();
            while(rs.next()) {
                usuario.setId(rs.getInt("Id"));
                usuario.setCpf(rs.getString("Cpf"));
                usuario.setNome(rs.getString("Nome"));
                usuario.setEmail(rs.getString("Email"));
                usuario.setDataNascimento(Methods.getFriendlyBirthday(rs.getString("DataNascimento")));
                usuario.setCelular(rs.getString("Celular"));
                usuario.setTelefone(rs.getString("Telefone"));
                usuario.setLogin(rs.getString("Login"));
                usuario.setSenha(rs.getString("Senha"));
                usuario.setSetor(rs.getString("Setor"));
                usuario.setCreated(Methods.getFriendlyDate(rs.getString("Created")));
                usuario.setPermissao(rs.getString("Permissao"));
                usuario.setStatus(rs.getString("Status"));
                usuario.setSexo(rs.getString("Sexo"));
            }
            st.close();
            return usuario;
        } catch (Exception error) {
            throw new RuntimeException("UsuarioDAO.selecionarPorId: " + error);
        }
    }
    
    /**
     * conta quantos usários exsitem com o dado cpf
     * @param cpf o cpf a ser buscado
     * @return o total de resultados
     */
    public int temCpf(String cpf) {
        String sql = "SELECT COUNT(Id) FROM usuarios WHERE Cpf = " + cpf;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            return rs.getInt(1);
        } catch(Exception error) {
            throw new RuntimeException("UsuarioDAO.temCpf: " + error);
        }
    }
    
    /**
     * conta quantos usários exsitem com o dado login
     * @param login o login a ser buscado
     * @return o total de resultados
     */
    public int temLogin(String login) {
        String sql = "SELECT COUNT(Id) FROM usuarios WHERE Login = " + login;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            return rs.getInt(1);
        } catch(Exception error) {
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
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("Id"));
                usuario.setCpf(rs.getString("Cpf"));
                usuario.setNome(rs.getString("Nome"));
                usuario.setEmail(rs.getString("Email"));
                usuario.setDataNascimento(Methods.getFriendlyBirthday(rs.getString("DataNascimento")));
                usuario.setCelular(rs.getString("Celular"));
                usuario.setTelefone(rs.getString("Telefone"));
                usuario.setLogin(rs.getString("Login"));
                usuario.setSenha(rs.getString("Senha"));
                usuario.setSetor(rs.getString("Setor"));
                usuario.setCreated(Methods.getFriendlyDate(rs.getString("Created")));
                usuario.setPermissao(rs.getString("Permissao"));
                usuario.setStatus(rs.getString("Status"));
                usuario.setSexo(rs.getString("Sexo"));
                usuarios.add(usuario);
            }
            st.close();
            return usuarios;
        } catch(Exception error) {
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
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            return rs.getInt(1);
        } catch(Exception error) {
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
        String nome = params.getProperty("nome", "");
        String email = params.getProperty("email", "");
        String setor = params.getProperty("setor", "");
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
            sql += " ORDER BY Id DESC";
            sql += " LIMIT 10 OFFSET " + (offset);
        }
            
        //System.out.println(sql);
        return sql;
    }
}
