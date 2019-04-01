/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Pedido;
import Models.PedidoProduto;
import Models.Produto;
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
public class PedidoDAO {
    
    private final Connection conn;
    private PreparedStatement stmt;
    private Statement st;
    private ResultSet rs;
    private final ArrayList<Produto> produtos = new ArrayList();
    private final ArrayList<Pedido> pedidos = new ArrayList();
    private final ArrayList<PedidoProduto> pedidosProdutos = new ArrayList();
    
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
    
    /**
     * seleciona um pedido da base de dados pelo seu Id
     * @param Id o Id do pedido a ser retornado
     * @return o pedido com Id correspondente
     */
    public ArrayList<PedidoProduto> selecionarPorId(String Id) {
        String sql = "SELECT * FROM pedidos "
                + "LEFT JOIN pedidosprodutos ON pedidosprodutos.PedidoId = pedidos.Id "
                + "LEFT JOIN produtos ON pedidosprodutos.ProdutoId = produtos.Id "
                + "LEFT JOIN usuarios ON pedidos.UsuarioId = usuarios.Id "
                + "WHERE pedidos.Id = " + Id;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            Pedido pedido = new Pedido();
            while(rs.next()) {
                pedido.setId(rs.getInt("pedidos.Id"));
                pedido.setStatus(rs.getString("Status"));
                pedido.setCreated(Methods.getFriendlyDate(rs.getString("pedidos.Created")));
                
                Usuario usuario = new Usuario();
                fillUser(usuario, rs);
                pedido.setSolicitante(usuario);
                
                Produto produto = new Produto();
                produto.setId(rs.getInt("produtos.Id"));
                produto.setNome(rs.getString("produtos.Nome"));
                produto.setDescricao(rs.getString("produtos.Descricao"));
                produto.setStatus(rs.getString("produtos.Status"));
                produto.setUnidade(rs.getString("produtos.Unidade"));
                produto.setCreated(Methods.getFriendlyDate(rs.getString("produtos.Created")));
                
                PedidoProduto pedidoProduto = new PedidoProduto();
                pedidoProduto.setId(rs.getInt("pedidosprodutos.Id"));
                pedidoProduto.setQuantidade(rs.getInt("pedidosprodutos.QuantidadeSolicitada"));
                pedidoProduto.setQuantidadeAprovada(rs.getInt("pedidosprodutos.QuantidadeAprovada"));
                pedidoProduto.setPedido(pedido);
                pedidoProduto.setProduto(produto);
                
                pedidosProdutos.add(pedidoProduto);
            }
            st.close();
            return pedidosProdutos;
        } catch (Exception error) {
            throw new RuntimeException("PedidoDAO.selecionarPorId: " + error);
        }
    }
    
    /**
     * seleciona os pedidos correspondentes aos parâmetros de filtragem e paginação
     * @param params os parâmetros de filtragem e paginação
     * @return uma lista de pedidos correspondentes
     */
    public ArrayList<Pedido> selecionar(Properties params) {
        String sql = buildSelectQuery(params, false);
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("pedidos.Id"));
                pedido.setStatus(rs.getString("pedidos.Status"));
                pedido.setCreated(Methods.getFriendlyDate(rs.getString("pedidos.Created")));
                
                Usuario usuario = new Usuario();
                fillUser(usuario, rs);
                pedido.setSolicitante(usuario);
                
                pedidos.add(pedido);
            }
            st.close();
            return pedidos;
        } catch(Exception error) {
            throw new RuntimeException("PedidoDAO.selecionar: " + error);
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
            throw new RuntimeException("PedidoDAO.total: " + error);
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
        String status = params.getProperty("status", "");
        String data = params.getProperty("data", "");
        String sql;
        
        if (! isCount) {
            sql = "SELECT * FROM pedidos "
                    + "LEFT JOIN usuarios ON usuarios.Id = pedidos.UsuarioId "
                    + "WHERE pedidos.Status != 'Deleted'";
        } else {
            sql = "SELECT COUNT(pedidos.Id) FROM pedidos LEFT JOIN usuarios ON usuarios.Id = pedidos.UsuarioId WHERE pedidos.Status != 'Deleted'";
        }
        
        if (! nome.equals("")) {
            sql += " AND usuarios.Nome LIKE '%" + nome + "%'";
        }
        
        if (! status.equals("")) {
            sql += " AND pedidos.Status = '" + status + "'";
        }
        
        if (! data.equals("")) {
            String sqlDate = Methods.getSqlDate(data);
            sql += " AND pedidos.Created >= '" + sqlDate + "'";
        }
        
        if (! isCount) {
            sql += " ORDER BY pedidos.Id DESC";
            sql += " LIMIT 10 OFFSET " + (offset);
        }
            
        //System.out.println(sql);
        return sql;
    }
    
    /**
     * Popula o usuario corrente com o resultado da consulta
     * @param usuario o usuario a ser populado
     * @param rs o ResultSet da consulta
     */
    private void fillUser(Usuario usuario, ResultSet rs) {
        try {
            usuario.setId(rs.getInt("usuarios.Id"));
            usuario.setCpf(rs.getString("usuarios.Cpf"));
            usuario.setNome(rs.getString("usuarios.Nome"));
            usuario.setEmail(rs.getString("usuarios.Email"));
            usuario.setDataNascimento(Methods.getFriendlyBirthday(rs.getString("usuarios.DataNascimento")));
            usuario.setCelular(rs.getString("usuarios.Celular"));
            usuario.setTelefone(rs.getString("usuarios.Telefone"));
            usuario.setLogin(rs.getString("usuarios.Login"));
            usuario.setSenha(rs.getString("usuarios.Senha"));
            usuario.setSetor(rs.getString("usuarios.Setor"));
            usuario.setCreated(Methods.getFriendlyDate(rs.getString("usuarios.Created")));
            usuario.setPermissao(rs.getString("usuarios.Permissao"));
            usuario.setStatus(rs.getString("usuarios.Status"));
            usuario.setSexo(rs.getString("usuarios.Sexo"));
        } catch(Exception error) {
            throw new RuntimeException("PedidoDAO.fillUser: " + error);
        }
    }
}
