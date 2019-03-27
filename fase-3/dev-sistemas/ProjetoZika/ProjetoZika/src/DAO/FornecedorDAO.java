/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author welis
 */
public class FornecedorDAO {
    
    private final Connection conn;
    private PreparedStatement stmt;
    private Statement st;
    private ResultSet rs;
    private final ArrayList<Fornecedor> fornecedores = new ArrayList();
    
    /**
     * método construtor, inicializa a conexão
     */
    public FornecedorDAO() {
        conn = new ConnectionFactory().getConexao();
    }
    
    /**
     * insere um novo fornecedor na base de dados
     * @param fornecedor o fornecedor a ser inserido
     */
    public int inserir(Fornecedor fornecedor) {
        String sql = "INSERT INTO fornecedores (Nome, Telefone, Cnpj, Status, Created) VALUES (?, ?, ?, ?, ?)";
        try {
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getTelefone());
            stmt.setString(3, fornecedor.getCnpj());
            stmt.setString(4, "Publish");
            //Date date = Methods.convertStringToDate(produto.getCreated());
            //java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            stmt.setDate(5, sqlDate);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            int lastInsertedId = 0;
            if(rs.next()) {
                lastInsertedId = rs.getInt(1);
            }
            stmt.close();
            return lastInsertedId;
        } catch(Exception error) {
            throw new RuntimeException("FornecedorDAO.inserir: " + error);
        }
    }
}
