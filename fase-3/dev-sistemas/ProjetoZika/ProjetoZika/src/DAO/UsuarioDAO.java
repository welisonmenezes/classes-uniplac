/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Produto;
import Models.Usuario;
import Utils.Methods;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
}
