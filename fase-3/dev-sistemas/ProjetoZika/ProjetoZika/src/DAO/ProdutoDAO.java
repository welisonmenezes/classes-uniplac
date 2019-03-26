/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Produto;
import Utils.Methods;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author welis
 */
public class ProdutoDAO {
    
    private final Connection conn;
    private PreparedStatement stmt;
    
    public ProdutoDAO() {
        conn = new ConnectionFactory().getConexao();
    }
    
    public void inserir(Produto produto) {
        String sql = "INSERT INTO Produtos (Nome, Unidade, Descricao, Status, Created) VALUES (?, ?, ?, ?, ?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getUnidade());
            stmt.setString(3, produto.getDescricao());
            stmt.setString(4, produto.getStatus());
            //Date date = Methods.convertStringToDate(produto.getCreated());
            //java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            stmt.setDate(5, sqlDate);
            stmt.execute();
            stmt.close();
        } catch(Exception error) {
            throw new RuntimeException("ProdutoDAO.inserir: " + error);
        }
    }
}
