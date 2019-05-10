package DAO;

import Utils.Methods;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Manipula dados do Estoque
 * @author welison
 */
public class EstoqueDAO {
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;
    private final ConnectionFactory connFac;
    
    /**
     * método construtor, inicializa a conexão
     */
    public EstoqueDAO() {
        connFac = new ConnectionFactory();
    }
    /**
     * Insere o produto e a quantidade na base de dados
     * @param idProduto o id do produto a ser inserido
     * @param quantidade a quantidade do produto
     * @return retorna o item inserido
     */
    public int inserir(int idProduto, int quantidade) {
        String sql = "INSERT INTO estoque (ProdutoId, Total) VALUES (?, ?)";
        try {
            conn = connFac.getConexao();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, idProduto);
            stmt.setInt(2, quantidade);
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            int lastInsertedId = 0;
            if(rs.next()) {
                lastInsertedId = rs.getInt(1);
            }
            connFac.closeAll(rs, stmt, null, conn);
            return lastInsertedId;
        } catch(SQLException error) {
            Methods.getLogger().error("EstoqueDAO.inserir: " + error);
            throw new RuntimeException("EstoqueDAO.inserir: " + error);
        }
    }
    
    /**
     * Altera a quantide do prouto na base de dados, sempre mantende maior ou igual a 0
     * @param idProduto o id do produto a ser alterado
     * @param quantidade a quantidade a ser atualziada
     */
    public void alterar(int idProduto, int quantidade) {
        String sql = "UPDATE estoque SET Total = CASE WHEN (Total+(?)) < 0 THEN 0 ELSE (Total+(?)) END WHERE ProdutoId=?";
        try {
            conn = connFac.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, quantidade);
            stmt.setInt(2, quantidade);
            stmt.setInt(3, idProduto);
            stmt.execute();
            connFac.closeAll(rs, stmt, null, conn);
        } catch(SQLException error) {
            Methods.getLogger().error("EstoqueDAO.alterar: " + error);
            throw new RuntimeException("EstoqueDAO.alterar: " + error);
        }
    }
    
    /**
     * Pega o total de um dado produto
     * @param IdProduto o id do produto
     * @return o total do produto
     */
    public int quantidade(int IdProduto) {
        String sql = "SELECT total FROM estoque WHERE ProdutoId= ?";
        try {
            conn = connFac.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, IdProduto);
            rs = stmt.executeQuery();
            int total = 0;
            while(rs.next()) {
                total = rs.getInt("Total");
            }
            connFac.closeAll(rs, stmt, null, conn);
            return total;
        } catch (SQLException error) {
            Methods.getLogger().error("EstoqueDAO.quantidade: " + error);
            throw new RuntimeException("EstoqueDAO.quantidade: " + error);
        }
    }
    
    /**
     * atualiza a quantidade do produto na base de dados
     * @param idProduto o id do produto
     * @param quantidade a nova quantidade
     */
    public void normalizarEstoqueProduto(int idProduto, int quantidade) {
        String sql = "UPDATE estoque SET Total = ? WHERE ProdutoId = ?";
        try {
            conn = connFac.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, quantidade);
            stmt.setInt(2, idProduto);
            stmt.execute();
            connFac.closeAll(rs, stmt, null, conn);
        } catch(SQLException error) {
            Methods.getLogger().error("EstoqueDAO.normalizarEstoqueProduto: " + error);
            throw new RuntimeException("EstoqueDAO.normalizarEstoqueProduto: " + error);
        }
    }
}
