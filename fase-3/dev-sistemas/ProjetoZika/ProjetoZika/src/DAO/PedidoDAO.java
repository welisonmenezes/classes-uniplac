/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Pedido;
import Models.PedidoProduto;
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
public class PedidoDAO {
    
    private final Connection conn;
    private PreparedStatement stmt;
    private Statement st;
    private ResultSet rs;
    private final ArrayList<Produto> produtos = new ArrayList();
    
    /**
     * método construtor, inicializa a conexão
     */
    public PedidoDAO() {
        conn = new ConnectionFactory().getConexao();
    }
    
    /**
     * insere um novo pedido na base de dados
     * @param pedido o pedido a ser inserido
     * @return o id do último item inserido
     */
    public int inserir(Pedido pedido) {
        String sql = "INSERT INTO pedidos (Status, Created, UsuarioId) VALUES (?, ?, ?)";
        try {
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, "Pendente");
            java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            stmt.setDate(2, sqlDate);
            stmt.setInt(3, pedido.getSolicitante().getId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            int lastInsertedId = 0;
            if(rs.next()) {
                lastInsertedId = rs.getInt(1);
            }
            stmt.close();
            return lastInsertedId;
        } catch(Exception error) {
            throw new RuntimeException("PedidoDAO.inserir: " + error);
        }
    }
    
    /**
     * insere um novo produto ao pedido na base de dados
     * @param pedidoProduto o pedidoProduto a ser inserido
     * @return o id do último item inserido
     */
    public int inserirProduto(PedidoProduto pedidoProduto) {
        String sql = "INSERT INTO pedidosprodutos (PedidoId, ProdutoId, QuantidadeSolicitada, QuantidadeAprovada) VALUES (?, ?, ?, ?)";
        try {
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, pedidoProduto.getPedido().getId());
            stmt.setInt(2, pedidoProduto.getProduto().getId());
            stmt.setInt(3, pedidoProduto.getQuantidade());
            stmt.setInt(4, pedidoProduto.getQuantidade());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            int lastInsertedId = 0;
            if(rs.next()) {
                lastInsertedId = rs.getInt(1);
            }
            stmt.close();
            return lastInsertedId;
        } catch(Exception error) {
            throw new RuntimeException("PedidoDAO.inserirProduto: " + error);
        }
    }
}
