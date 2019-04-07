/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Fornecedor;
import Models.NotaFiscal;
import Models.NotaFiscalProduto;
import Models.Produto;
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
public class EstoqueDAO {
    private final Connection conn;
    private PreparedStatement stmt;
    private Statement st;
    private ResultSet rs;
    private final ArrayList<Fornecedor> fornecedores = new ArrayList();
    
    /**
     * método construtor, inicializa a conexão
     */
    public EstoqueDAO() {
        conn = new ConnectionFactory().getConexao();
    }

    public int inserir(int idProduto, int quantidade) {
        String sql = "INSERT INTO estoque (ProdutoId, Total) VALUES (?, ?)";
        try {
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, idProduto);
            stmt.setInt(2, quantidade);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            int lastInsertedId = 0;
            if(rs.next()) {
                lastInsertedId = rs.getInt(1);
            }
            stmt.close();
            return lastInsertedId;
        } catch(Exception error) {
            throw new RuntimeException("EstoqueDAO.inserir: " + error);
        }
    }
    

    public void alterar(int idProduto, int quantidade) {
        String sql = "UPDATE estoque SET Total=Total+(?) WHERE ProdutoId=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, quantidade);
            stmt.setInt(2, idProduto);
            stmt.execute();
            stmt.close();
        } catch(Exception error) {
            throw new RuntimeException("EstoqueDAO.alterar: " + error);
        }
    }
}
