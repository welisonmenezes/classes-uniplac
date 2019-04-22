/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.GraphModel;
import Models.Pedido;
import Models.PedidoProduto;
import Models.Produto;
import Models.Usuario;
import Utils.FillModel;
import Utils.DateHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

/**
 * Manipula os dados de pedidos
 * @author welison
 */
public class PedidoDAO {
    
    private final Connection conn;
    private PreparedStatement stmt;
    private Statement st;
    private ResultSet rs;
    private final ArrayList<Pedido> pedidos = new ArrayList();
    private final ArrayList<PedidoProduto> pedidosProdutos = new ArrayList();
    private final FillModel helper = new FillModel();
    
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
            rs = stmt.getGeneratedKeys();
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
            rs = stmt.getGeneratedKeys();
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
     * Muda o status do pedido na base de dados
     * @param pedido o pedido a ser alterado
     */
    public void mudaStatus(Pedido pedido) {
        String sql = "UPDATE pedidos SET Status=? WHERE Id=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, pedido.getStatus());
            stmt.setInt(2, pedido.getId());
            stmt.execute();
            stmt.close();
        } catch(Exception error) {
            throw new RuntimeException("PedidoDAO.mudaStatus: " + error);
        }
    }
    
    /**
     * Finaliza ou nega o pedido na base de dados
     * @param pedido o pedido a ser alterado
     * @param almoxarife o Almoxarife que aprovou ou negou
     */
    public void finalizar(Pedido pedido, Usuario almoxarife) {
        String sql = "UPDATE pedidos SET Status=?, AlmoxarifeId=? WHERE Id=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, pedido.getStatus());
            stmt.setInt(2, almoxarife.getId());
            stmt.setInt(3, pedido.getId());
            stmt.execute();
            stmt.close();
        } catch(Exception error) {
            throw new RuntimeException("PedidoDAO.finalizar: " + error);
        }
    }
    
    /**
     * Muda a quantidade aprovada do produto do pedido na base de dados
     * @param pedidoProduto o pedidoProduto a ser alterado
     */
    public void mudaQuantidadeAprovada(PedidoProduto pedidoProduto) {
        String sql = "UPDATE pedidosprodutos SET QuantidadeAprovada=? WHERE Id=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, pedidoProduto.getQuantidadeAprovada());
            stmt.setInt(2, pedidoProduto.getId());
            stmt.execute();
            stmt.close();
        } catch(Exception error) {
            throw new RuntimeException("PedidoDAO.mudaQuantidadeAprovada: " + error);
        }
    }
    
    /**
     * Deleta os prdutos da do pedido
     * @param pedidoId o Id do pedido cujo produtos serão deletados
     */
    public void deletarPedidoProdutos(int pedidoId) {
        String sql = "DELETE FROM pedidosprodutos WHERE PedidoId=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, pedidoId);
            stmt.execute();
            stmt.close();
        } catch(Exception error) {
            throw new RuntimeException("PedidoDAO.deletarPedidoProdutos: " + error);
        }
    }
    
    /**
     * 'deleta' o pedido da visualização, na base de dados altera apenas o status para 'Deleted'
     * @param Id o Id do pedido a ser 'deletado'
     */
    public void deletar(int Id) {
        String sql = "UPDATE pedidos SET Status='Deleted' WHERE Id=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Id);
            stmt.execute();
            stmt.close();
        } catch(Exception error) {
            throw new RuntimeException("PedidoDAO.deletar: " + error);
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
                + "LEFT JOIN estoque ON estoque.ProdutoId = produtos.Id "
                + "LEFT JOIN usuarios ON pedidos.UsuarioId = usuarios.Id "
                + "WHERE pedidos.Id = " + Id;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                Pedido pedido = new Pedido();
                this.helper.fillPedido(pedido, rs);
                
                Usuario usuario = new Usuario();
                this.helper.fillUsuario(usuario, rs);
                pedido.setSolicitante(usuario);
                
                Produto produto = new Produto();
                this.helper.fillProduto(produto, rs);
                
                PedidoProduto pedidoProduto = new PedidoProduto();
                this.helper.fillPedidoProduto(pedidoProduto, pedido, produto, rs);
                
                pedidosProdutos.add(pedidoProduto);
            }
            st.close();
            return pedidosProdutos;
        } catch (Exception error) {
            throw new RuntimeException("PedidoDAO.selecionarPorId: " + error);
        }
    }
    
    /**
     * seleciona um pedido da base de dados pelo seu usuário
     * @param usuario o usuário solicitante
     * @param params os parâmetros de filtro e paginação
     * @return o pedido com usuário correspondente
     */
    public ArrayList<Pedido> selecionarPorUsuario(Usuario usuario, Properties params) {
        String sql = buildSelectQueryPorUsuario(params, false, usuario);
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                Pedido pedido = new Pedido();
                this.helper.fillPedido(pedido, rs);
                pedidos.add(pedido);
            }
            st.close();
            return pedidos;
        } catch (Exception error) {
            throw new RuntimeException("PedidoDAO.selecionarPorUsuario: " + error);
        }
    }
    
    /**
     * seleciona os pedidosProdutos da base de dados pelo id do pedido
     * @param Id o id do pedido
     * @return os pedidosProdutos com usuário correspondente
     */
    public ArrayList<PedidoProduto> selecionarPedidoProdutoPorId(String Id) {
        String sql = "SELECT * FROM pedidosprodutos "
                + "LEFT JOIN pedidos ON pedidosprodutos.PedidoId = pedidos.Id "
                + "LEFT JOIN produtos ON pedidosprodutos.ProdutoId = produtos.Id "
                + "LEFT JOIN estoque ON estoque.ProdutoId = produtos.Id "
                + "WHERE pedidosprodutos.PedidoId = " + Id;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                Pedido pedido = new Pedido();
                this.helper.fillPedido(pedido, rs);
                
                Produto produto = new Produto();
                this.helper.fillProduto(produto, rs);
                
                PedidoProduto pedidoProduto = new PedidoProduto();
                this.helper.fillPedidoProduto(pedidoProduto, pedido, produto, rs);
                
                pedidosProdutos.add(pedidoProduto);
            }
            st.close();
            return pedidosProdutos;
        } catch (Exception error) {
            throw new RuntimeException("PedidoDAO.selecionarPedidoProdutoPorUsuario: " + error);
        }
    }
    
    /**
     * o total de pedidos por usuario que correspondem aos parâmetros de filtro e paginação
     * @param usuario o usuario solicitante
     * @param params os parâmetros de filtro e paginação
     * @return o total de pedidos por usuário
     */
    public int totalPorUsuario(Usuario usuario, Properties params) {
        String sql = buildSelectQueryPorUsuario(params, true, usuario);
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
     * seleciona total de pedidos dos últimos 6 meses
     * @return lista de total de pedidos e seu respectivo mês
     */
    public ArrayList<GraphModel> graphData() {
        String sql = "SELECT COUNT(Id) as total, MONTH(Created) as month " +
                        "FROM pedidos " +
                        "WHERE Created > DATE_SUB(now(), INTERVAL 6 MONTH) " +
                        "GROUP BY MONTH(Created) ORDER BY Created";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            ArrayList graphs = new ArrayList();
            while(rs.next()) {
                GraphModel graph = new GraphModel();
                graph.setQuantidade(rs.getInt("total"));
                graph.setMonth(rs.getInt("month"));
                graphs.add(graph);
            }
            st.close();
            return graphs;
        } catch(Exception error) {
            throw new RuntimeException("PedidoDAO.graphData: " + error);
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
                this.helper.fillPedido(pedido, rs);
                
                Usuario usuario = new Usuario();
                this.helper.fillUsuario(usuario, rs);
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
     * o total de pedidos que correspondem aos parâmetros de filtro e paginação
     * @param params os parâmetros de filtro e paginação
     * @return o total de pedidos
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
            String sqlDate = DateHandler.getSqlDateTime(data);
            sql += " AND pedidos.Created >= '" + sqlDate + "'";
        }
        
        if (! isCount) {
            sql += " ORDER BY " + params.getProperty("orderby", "pedidos.Id") + " " + params.getProperty("order", "DESC");
            sql += " LIMIT 10 OFFSET " + (offset);
        }
            
        return sql;
    }
    
    /**
     * Auxiliza na construção da query de seleção com base nos parametros passados
     * @param params os parâmetros de filtro e paginação
     * @param isCount true se é pra retorna apenas o total
     * @param usuario o usuário solicitante
     * @return a query para ser usada na seleção
     */
    private String buildSelectQueryPorUsuario (Properties params, boolean isCount, Usuario usuario) {
        int offset = Integer.parseInt(params.getProperty("offset", "0"));
        String status = params.getProperty("status", "");
        String data = params.getProperty("data", "");
        String sql;
        
        if (! isCount) {
            sql = "SELECT * FROM pedidos "
                    + "WHERE pedidos.Status != 'Deleted' "
                    + "AND pedidos.UsuarioId = " + usuario.getId();
        } else {
            sql = "SELECT COUNT(pedidos.Id) FROM pedidos WHERE pedidos.Status != 'Deleted' AND pedidos.UsuarioId = " + usuario.getId();
        }
        
        if (! status.equals("")) {
            sql += " AND pedidos.Status = '" + status + "'";
        }
        
        if (! data.equals("")) {
            String sqlDate = DateHandler.getSqlDateTime(data);
            sql += " AND pedidos.Created >= '" + sqlDate + "'";
        }
        
        if (! isCount) {
            sql += " ORDER BY " + params.getProperty("orderby", "pedidos.Id") + " " + params.getProperty("order", "DESC");
            sql += " LIMIT 10 OFFSET " + (offset);
        }
            
        return sql;
    }
    
    
    public void relatorioPedido(Properties params) {
        String sql = "SELECT pedidos.Id AS 'codigo', "
                + "usuarios.Nome AS 'solicitante', "
                + "aprovadores.Nome AS 'aprovador', "
                + "pedidos.Status AS 'status', "
                + "pedidos.Created AS 'data', "
                + "SUM(pedidosprodutos.QuantidadeAprovada) AS 'total' "
                + "FROM `pedidos` "
                + "LEFT JOIN usuarios ON usuarios.Id = pedidos.UsuarioId "
                + "LEFT JOIN usuarios AS aprovadores ON aprovadores.Id = pedidos.AlmoxarifeId "
                + "LEFT JOIN pedidosprodutos ON pedidosprodutos.PedidoId = pedidos.Id "
                + "WHERE pedidos.Id > 0 "
                + "AND pedidos.UsuarioId = 8 "
                + "AND pedidos.AlmoxarifeId = 5 "
                + "AND pedidos.Status = 'Aguardando entrega' "
                + "AND pedidos.Created >= '2019-01-01 00:00:00' "
                + "AND pedidos.Created <= '2019-04-01 00:00:00' "
                + "GROUP BY pedidos.Id "
                + "ORDER BY pedidos.Id DESC";
        
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                System.out.println(rs.getInt("codigo")+" - "+rs.getString("solicitante")+" - "+rs.getString("aprovador")+" - "+rs.getString("status")+" - "+rs.getDate("data")+" - "+rs.getInt("total"));
            }
            st.close();
            //return pedidos;
        } catch(Exception error) {
            throw new RuntimeException("PedidoDAO.relatorioPedido: " + error);
        }
    }
}
