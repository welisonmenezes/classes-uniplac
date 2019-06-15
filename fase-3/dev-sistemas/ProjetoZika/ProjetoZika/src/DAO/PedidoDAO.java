package DAO;

import Models.GraphModel;
import Models.Pedido;
import Models.PedidoProduto;
import Models.Produto;
import Models.RelatorioPedido;
import Models.Usuario;
import Utils.FillModel;
import Utils.DateHandler;
import Utils.Methods;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

/**
 * Manipula os dados de pedidos
 * @author welison
 */
public class PedidoDAO {
    
    private Connection conn;
    private PreparedStatement stmt;
    private Statement st;
    private ResultSet rs;
    private final ConnectionFactory connFac;
    private final ArrayList<Pedido> pedidos = new ArrayList();
    private final ArrayList<PedidoProduto> pedidosProdutos = new ArrayList();
    private final FillModel helper = new FillModel();
    
    /**
     * método construtor, inicializa a conexão
     */
    public PedidoDAO() {
        connFac = new ConnectionFactory();
    }
    
    /**
     * insere um novo pedido na base de dados
     * @param pedido o pedido a ser inserido
     * @return o id do último item inserido
     */
    public int inserir(Pedido pedido) {
        String sql = "INSERT INTO pedidos (Status, Created, UsuarioId) VALUES (?, ?, ?)";
        try {
            conn = connFac.getConexao();
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
            connFac.closeAll(rs, stmt, st, conn);
            return lastInsertedId;
        } catch(SQLException error) {
            Methods.getLogger().error("PedidoDAO.inserir: " + error);
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
            conn = connFac.getConexao();
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
            connFac.closeAll(rs, stmt, st, conn);
            return lastInsertedId;
        } catch(SQLException error) {
            Methods.getLogger().error("PedidoDAO.inserirProduto: " + error);
            throw new RuntimeException("PedidoDAO.inserirProduto: " + error);
        }
    }
    
    /**
     * Muda o status do pedido na base de dados
     * @param pedido o pedido a ser alterado
     */
    public void xxxmudaStatus(Pedido pedido) {
        String sql = "UPDATE pedidos SET Status=? WHERE Id=?";
        try {
            conn = connFac.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, pedido.getStatus());
            stmt.setInt(2, pedido.getId());
            stmt.execute();
            connFac.closeAll(rs, stmt, st, conn);
        } catch(SQLException error) {
            Methods.getLogger().error("PedidoDAO.mudaStatus: " + error);
            throw new RuntimeException("PedidoDAO.mudaStatus: " + error);
        }
    }
    
    /**
     * Finaliza ou nega o pedido na base de dados
     * @param pedido o pedido a ser alterado
     * @param almoxarife o Almoxarife que aprovou ou negou
     */
    public void finalizar(Pedido pedido, Usuario almoxarife) {
        String sql;
        if (pedido.getStatus().equals(Methods.getTranslation("AguardandoEntrega"))) {
            sql = "UPDATE pedidos SET Status=?, AlmoxarifeId=?, Aproved=(SELECT NOW()) WHERE Id=?";
        } else if (pedido.getStatus().equals(Methods.getTranslation("Finalizado"))) {
            sql = "UPDATE pedidos SET Status=?, AlmoxarifeId=?, Done=(SELECT NOW()) WHERE Id=?";
        } else {
            sql = "UPDATE pedidos SET Status=?, AlmoxarifeId=? WHERE Id=?";
        }
        System.out.println(sql);
        try {
            conn = connFac.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, pedido.getStatus());
            stmt.setInt(2, almoxarife.getId());
            stmt.setInt(3, pedido.getId());
            stmt.execute();
            connFac.closeAll(rs, stmt, st, conn);
        } catch(SQLException error) {
            Methods.getLogger().error("PedidoDAO.finalizar: " + error);
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
            conn = connFac.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, pedidoProduto.getQuantidadeAprovada());
            stmt.setInt(2, pedidoProduto.getId());
            stmt.execute();
            connFac.closeAll(rs, stmt, st, conn);
        } catch(SQLException error) {
            Methods.getLogger().error("PedidoDAO.mudaQuantidadeAprovada: " + error);
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
            conn = connFac.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, pedidoId);
            stmt.execute();
            connFac.closeAll(rs, stmt, st, conn);
        } catch(SQLException error) {
            Methods.getLogger().error("PedidoDAO.deletarPedidoProdutos: " + error);
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
            conn = connFac.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Id);
            stmt.execute();
            connFac.closeAll(rs, stmt, st, conn);
        } catch(SQLException error) {
            Methods.getLogger().error("PedidoDAO.deletar: " + error);
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
                + "WHERE pedidos.Id = ?";
        int queryId = Integer.parseInt(Id);
        try {
            conn = connFac.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, queryId);
            rs = stmt.executeQuery();
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
            connFac.closeAll(rs, stmt, st, conn);
            return pedidosProdutos;
        } catch (SQLException error) {
            Methods.getLogger().error("PedidoDAO.selecionarPorId: " + error);
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
            conn = connFac.getConexao();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                Pedido pedido = new Pedido();
                this.helper.fillPedido(pedido, rs);
                pedidos.add(pedido);
            }
            connFac.closeAll(rs, stmt, st, conn);
            return pedidos;
        } catch (SQLException error) {
            Methods.getLogger().error("PedidoDAO.selecionarPorUsuario: " + error);
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
                + "WHERE pedidosprodutos.PedidoId = ?";
        int queryId = Integer.parseInt(Id);
        try {
            conn = connFac.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, queryId);
            rs = stmt.executeQuery();
            while(rs.next()) {
                Pedido pedido = new Pedido();
                this.helper.fillPedido(pedido, rs);
                
                Produto produto = new Produto();
                this.helper.fillProduto(produto, rs);
                
                PedidoProduto pedidoProduto = new PedidoProduto();
                this.helper.fillPedidoProduto(pedidoProduto, pedido, produto, rs);
                
                pedidosProdutos.add(pedidoProduto);
            }
            connFac.closeAll(rs, stmt, st, conn);
            return pedidosProdutos;
        } catch (SQLException error) {
            Methods.getLogger().error("PedidoDAO.selecionarPedidoProdutoPorUsuario: " + error);
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
            conn = connFac.getConexao();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            int total = rs.getInt(1);
            connFac.closeAll(rs, stmt, st, conn);
            return total;
        } catch(SQLException error) {
            Methods.getLogger().error("PedidoDAO.total: " + error);
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
            conn = connFac.getConexao();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            ArrayList graphs = new ArrayList();
            while(rs.next()) {
                GraphModel graph = new GraphModel();
                this.helper.fillGraph(graph, rs);
                graphs.add(graph);
            }
            connFac.closeAll(rs, stmt, st, conn);
            return graphs;
        } catch(SQLException error) {
            Methods.getLogger().error("PedidoDAO.graphData: " + error);
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
            conn = connFac.getConexao();
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
            connFac.closeAll(rs, stmt, st, conn);
            return pedidos;
        } catch(SQLException error) {
            Methods.getLogger().error("PedidoDAO.selecionar: " + error);
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
            conn = connFac.getConexao();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            int total = rs.getInt(1);
            connFac.closeAll(rs, stmt, st, conn);
            return total;
        } catch(SQLException error) {
            Methods.getLogger().error("PedidoDAO.total: " + error);
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
        String nome = Methods.scapeSQL(params.getProperty("nome", ""));
        String status = Methods.scapeSQL(params.getProperty("status", ""));
        String data = Methods.scapeSQL(params.getProperty("data", ""));
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
        String status = Methods.scapeSQL(params.getProperty("status", ""));
        String data = Methods.scapeSQL(params.getProperty("data", ""));
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
    
    /**
     * constrói a query baseado nos dados parâmetros e faz a consulta para o relatório de pedidos
     * @param params os parâmetros de filtro e paginação
     * @return Uma lista de RelatorioPedido
     */
    public ArrayList<RelatorioPedido> relatorioPedido(Properties params) {
        
        String dataDe = Methods.scapeSQL(params.getProperty("dataDe", ""));
        String dataAte = Methods.scapeSQL(params.getProperty("dataAte", ""));
        String usuarioId = Methods.scapeSQL(params.getProperty("usuarioId", ""));
        String aprovadorId = Methods.scapeSQL(params.getProperty("aprovadorId", ""));
        String produtoId = Methods.scapeSQL(params.getProperty("produtoId", ""));
        String status = Methods.scapeSQL(params.getProperty("status", ""));
        
        String sql = "SELECT pedidos.Id AS 'codigo', "
                + "usuarios.Nome AS 'solicitante', "
                + "aprovadores.Nome AS 'aprovador', "
                + "pedidos.Status AS 'status', "
                + "pedidos.Created AS 'data', "
                + "SUM(pedidosprodutos.QuantidadeAprovada) AS 'total', "
                + "GROUP_CONCAT(CONCAT(produtos.Nome, '/', produtos.Unidade, '/', pedidosprodutos.QuantidadeAprovada, '\n\n') SEPARATOR '') AS 'produtos' "
                + "FROM `pedidos` "
                + "LEFT JOIN usuarios ON usuarios.Id = pedidos.UsuarioId "
                + "LEFT JOIN usuarios AS aprovadores ON aprovadores.Id = pedidos.AlmoxarifeId "
                + "LEFT JOIN pedidosprodutos ON pedidosprodutos.PedidoId = pedidos.Id "
                + "LEFT JOIN produtos ON pedidosprodutos.ProdutoId = produtos.Id AND pedidosprodutos.QuantidadeAprovada > 0 "
                + "WHERE pedidos.Id > 0 ";
                
        
        if (! usuarioId.equals("") ) {
            sql += "AND pedidos.UsuarioId = " + usuarioId + " ";
        }
        if (! aprovadorId.equals("") ) {
            sql += "AND pedidos.AlmoxarifeId = " + aprovadorId + " ";
        }
        if (! produtoId.equals("") ) {
            sql += "AND pedidosprodutos.ProdutoId = " + produtoId + " ";
        }
        if (! status.equals("") ) {
            sql += "AND pedidos.Status = '" + status + "' ";
        }
        if (! dataDe.equals("") ) {
            dataDe = DateHandler.getSqlDateTime(dataDe);
            sql += "AND pedidos.Created >= '" + dataDe + "' ";
        }
        if (! dataAte.equals("") ) {
            dataAte = DateHandler.getSqlDateTime(dataAte);
            sql += "AND pedidos.Created <= '" + dataAte + "' ";
        }
        
        sql += "AND pedidos.Status != 'Deleted' "
                + "GROUP BY pedidos.Id "
                + "ORDER BY pedidos.Id DESC";
        
        try {
            conn = connFac.getConexao();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            ArrayList<RelatorioPedido> relatorioPedidos = new ArrayList<>();
            while(rs.next()) {
                RelatorioPedido item = new RelatorioPedido();
                this.helper.fillRelatorioPedido(item, rs);
                relatorioPedidos.add(item);
            }
            connFac.closeAll(rs, stmt, st, conn);
            return relatorioPedidos;
        } catch(SQLException error) {
            Methods.getLogger().error("PedidoDAO.relatorioPedido: " + error);
            throw new RuntimeException("PedidoDAO.relatorioPedido: " + error);
        }
    }
}
