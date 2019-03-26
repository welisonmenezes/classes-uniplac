/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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
public class ProdutoDAO {
    
    private final Connection conn;
    private PreparedStatement stmt;
    private Statement st;
    private ResultSet rs;
    private ArrayList<Produto> produtos = new ArrayList();
    
    public ProdutoDAO() {
        conn = new ConnectionFactory().getConexao();
    }
    
    public void inserir(Produto produto) {
        String sql = "INSERT INTO produtos (Nome, Unidade, Descricao, Status, Created) VALUES (?, ?, ?, ?, ?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getUnidade());
            stmt.setString(3, produto.getDescricao());
            stmt.setString(4, "Publish");
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
    
    public ArrayList<Produto> selecionar() {
        String sql = "SELECT * FROM produtos WHERE Status != 'Deleted'";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("Id"));
                produto.setNome(rs.getString("Nome"));
                produto.setDescricao(rs.getString("Descricao"));
                produto.setUnidade(rs.getString("Unidade"));
                produto.setStatus(rs.getString("Status"));
                produto.setCreated(rs.getString("Created"));
                produtos.add(produto);
            }
        } catch(Exception error) {
            throw new RuntimeException("ProdutoDAO.selecionar: " + error);
        }
        return produtos;
    }
}
