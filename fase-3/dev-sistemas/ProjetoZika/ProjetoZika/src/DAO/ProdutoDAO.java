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
    private final ArrayList<Produto> produtos = new ArrayList();
    
    /**
     * método construtor, inicializa a conexão
     */
    public ProdutoDAO() {
        conn = new ConnectionFactory().getConexao();
    }
    
    /**
     * insere um novo produto na base de dados
     * @param produto o produto a ser inserido
     */
    public int inserir(Produto produto) {
        String sql = "INSERT INTO produtos (Nome, Unidade, Descricao, Status, Created) VALUES (?, ?, ?, ?, ?)";
        try {
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getUnidade());
            stmt.setString(3, produto.getDescricao());
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
            throw new RuntimeException("ProdutoDAO.inserir: " + error);
        }
    }
    /**
     * altera o dado produto na base de dados
     * @param produto o produto a ser alterado
     */
    public void alterar(Produto produto) {
        String sql = "UPDATE produtos SET Nome=?, Unidade=?, Descricao=? WHERE Id=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getUnidade());
            stmt.setString(3, produto.getDescricao());
            stmt.setInt(4, produto.getId());
            stmt.execute();
            stmt.close();
        } catch(Exception error) {
            throw new RuntimeException("ProdutoDAO.alterar: " + error);
        }
    }
    
    /**
     * 'deleta' o produto da visualização, na base de dados altera apenas o status para 'Deleted'
     * @param Id o Id do produto a ser 'deletado'
     */
    public void deletar(int Id) {
        String sql = "UPDATE produtos SET Status='Deleted' WHERE Id=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Id);
            stmt.execute();
            stmt.close();
        } catch(Exception error) {
            throw new RuntimeException("ProdutoDAO.deletar: " + error);
        }
    }
    
    /**
     * seleciona um produto da base de dados pelo seu Id
     * @param Id o Id do produto a ser retornado
     * @return o produto com Id correspondente
     */
    public Produto selecionarPorId(String Id) {
        String sql = "SELECT * FROM produtos WHERE Id = " + Id;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            Produto produto = new Produto();
            while(rs.next()) {
                produto.setId(rs.getInt("Id"));
                produto.setNome(rs.getString("Nome"));
                produto.setDescricao(rs.getString("Descricao"));
                produto.setUnidade(rs.getString("Unidade"));
                produto.setStatus(rs.getString("Status"));
                produto.setCreated(Methods.getFriendlyDate(rs.getString("Created")));
            }
            st.close();
            return produto;
        } catch (Exception error) {
            throw new RuntimeException("ProdutoDAO.selecionarPorId: " + error);
        }
    }
    
    /**
     * seleciona os produtos correspondentes aos parâmetros de filtragem e paginação
     * @param params os parâmetros de filtragem e paginação
     * @return uma lista de produtos correspondentes
     */
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
            st.close();
            return produtos;
        } catch(Exception error) {
            throw new RuntimeException("ProdutoDAO.selecionar: " + error);
        }
    }
    
    /**
     * o total de produtos que correspondem aos parâmetros de filtro e paginação
     * @param params os parâmetros de filtro e paginação
     * @return o total de produtos
     */
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
    
    /**
     * Auxiliza na construção da query de seleção com base nos parametros passados
     * @param params os parâmetros de filtro e paginação
     * @param isCount true se é pra retorna apenas o total
     * @return a query para ser usada na seleção
     */
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
            sql += " LIMIT 10 OFFSET " + (offset);
        }
            
        //System.out.println(sql);
        return sql;
    }
}
