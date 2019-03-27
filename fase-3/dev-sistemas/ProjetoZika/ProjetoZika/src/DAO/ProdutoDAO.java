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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

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
    
    public ArrayList<Produto> selecionar(Properties params) {
        String sql = buildSelectQuery(params, false);
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
                produto.setCreated(Methods.getFriendlyDate(rs.getString("Created")));
                produtos.add(produto);
            }
            return produtos;
        } catch(Exception error) {
            throw new RuntimeException("ProdutoDAO.selecionar: " + error);
        }
    }
    
    public int total(Properties params) {
        String sql = buildSelectQuery(params, true);
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            return rs.getInt(1);
        } catch(Exception error) {
            throw new RuntimeException("ProdutoDAO.total: " + error);
        }
    }
    
    private String buildSelectQuery (Properties params, boolean isCount) {
        int offset = Integer.parseInt(params.getProperty("offset", "0"));
        String nome = params.getProperty("nome", "");
        String unidade = params.getProperty("unidade", "");
        String data = params.getProperty("data", "");
        String sql;
        
        if (! isCount) {
            sql = "SELECT * FROM produtos WHERE Status != 'Deleted'";
        } else {
            sql = "SELECT COUNT(Id) FROM produtos WHERE Status != 'Deleted'";
        }
        
        if (! nome.equals("")) {
            sql += " AND Nome LIKE '%" + nome + "%'";
        }
        
        if (! unidade.equals("")) {
            sql += " AND Unidade = '" + unidade + "'";
        }
        
        if (! data.equals("")) {
            String sqlDate = Methods.getSqlDate(data);
            sql += " AND Created >= '" + sqlDate + "'";
        }
        
        if (! isCount) {
            sql += " LIMIT 2 OFFSET " + (offset);
        }
            
        System.out.println(sql);
        return sql;
    }
}
