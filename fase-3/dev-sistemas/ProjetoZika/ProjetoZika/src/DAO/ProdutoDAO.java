package DAO;

import Models.GraphModel;
import Models.Produto;
import Models.RelatorioProduto;
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
 * Manipula os dados de produtos
 * @author welison
 */
public class ProdutoDAO {
    
    private final Connection conn;
    private PreparedStatement stmt;
    private Statement st;
    private ResultSet rs;
    private final ArrayList<Produto> produtos = new ArrayList();
    private final FillModel helper = new FillModel();
    
    /**
     * método construtor, inicializa a conexão
     */
    public ProdutoDAO() {
        conn = new ConnectionFactory().getConexao();
    }
    
    /**
     * insere um novo produto na base de dados
     * @param produto o produto a ser inserido
     * @return o id do último item inserido
     */
    public int inserir(Produto produto) {
        String sql = "INSERT INTO produtos (Nome, Unidade, Descricao, Status, Created) VALUES (?, ?, ?, ?, ?)";
        try {
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getUnidade());
            stmt.setString(3, produto.getDescricao());
            stmt.setString(4, "Publish");
            java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            stmt.setDate(5, sqlDate);
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            int lastInsertedId = 0;
            if(rs.next()) {
                lastInsertedId = rs.getInt(1);
            }
            stmt.close();
            return lastInsertedId;
        } catch(SQLException error) {
            Methods.getLogger().error("ProdutoDAO.inserir: " + error);
            throw new RuntimeException("ProdutoDAO.inserir: " + error);
        }
    }
    
    /**
     * altera o dado do produto na base de dados
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
        } catch(SQLException error) {
            Methods.getLogger().error("ProdutoDAO.alterar: " + error);
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
        } catch(SQLException error) {
            Methods.getLogger().error("ProdutoDAO.deletar: " + error);
            throw new RuntimeException("ProdutoDAO.deletar: " + error);
        }
    }
    
    /**
     * seleciona um produto da base de dados pelo seu Id
     * @param Id o Id do produto a ser retornado
     * @return o produto com Id correspondente
     */
    public Produto selecionarPorId(String Id) {
        String sql = "SELECT * FROM produtos LEFT JOIN estoque ON estoque.ProdutoId=Id WHERE Id = ?";
        int queryId = Integer.parseInt(Id);
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, queryId);
            rs = stmt.executeQuery();
            Produto produto = new Produto();
            while(rs.next()) {
                this.helper.fillProduto(produto, rs);
            }
            stmt.close();
            return produto;
        } catch (SQLException error) {
            Methods.getLogger().error("ProdutoDAO.selecionarPorId: " + error);
            throw new RuntimeException("ProdutoDAO.selecionarPorId: " + error);
        }
    }
    
    /**
     * seleciona os produtos correspondentes ao dado nome
     * @param nome o nome desejado
     * @return uma lista de produtos correspondentes
     */
    public ArrayList<Produto> selecionarPorNome(String nome) {
        String sql = "SELECT * FROM produtos "
                + "LEFT JOIN estoque ON estoque.ProdutoId=Id "
                + "WHERE Status != 'Deleted' AND Nome LIKE ? LIMIT 50";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();
            while(rs.next()) {
                Produto produto = new Produto();
                this.helper.fillProduto(produto, rs);
                produtos.add(produto);
            }
            stmt.close();
            return produtos;
        } catch(SQLException error) {
            Methods.getLogger().error("ProdutoDAO.selecionarPorNome: " + error);
            throw new RuntimeException("ProdutoDAO.selecionarPorNome: " + error);
        }
    }
    
    /**
     * seleciona total de produtos dos últimos 6 meses
     * @return lista de total de produtos e seu respectivo mês
     */
    public ArrayList<GraphModel> graphData() {
        String sql = "SELECT COUNT(Id) as total, MONTH(Created) as month " +
                        "FROM produtos " +
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
        } catch(SQLException error) {
            Methods.getLogger().error("ProdutoDAO.graphData: " + error);
            throw new RuntimeException("ProdutoDAO.graphData: " + error);
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
                this.helper.fillProduto(produto, rs);
                produtos.add(produto);
            }
            st.close();
            return produtos;
        } catch(SQLException error) {
            Methods.getLogger().error("ProdutoDAO.selecionar: " + error);
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
        } catch(SQLException error) {
            Methods.getLogger().error("ProdutoDAO.total: " + error);
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
        String nome = Methods.scapeSQL(params.getProperty("nome", ""));
        String unidade = Methods.scapeSQL(params.getProperty("unidade", ""));
        String data = Methods.scapeSQL(params.getProperty("data", ""));
        String sql;
        
        if (! isCount) {
            sql = "SELECT * FROM produtos LEFT JOIN estoque ON estoque.ProdutoId=Id WHERE Status != 'Deleted'";
        } else {
            sql = "SELECT COUNT(Id) FROM produtos LEFT JOIN estoque ON estoque.ProdutoId=Id WHERE Status != 'Deleted'";
        }
        
        if (! nome.equals("")) {
            sql += " AND Nome LIKE '%" + nome + "%'";
        }
        
        if (! unidade.equals("")) {
            sql += " AND Unidade = '" + unidade + "'";
        }
        
        if (! data.equals("")) {
            String sqlDate = DateHandler.getSqlDateTime(data);
            sql += " AND Created >= '" + sqlDate + "'";
        }
        
        if (! isCount) {
            sql += " ORDER BY " + params.getProperty("orderby", "Id") + " " + params.getProperty("order", "DESC");
            sql += " LIMIT 10 OFFSET " + (offset);
        }
            
        return sql;
    }
    
    /**
     * constrói a query baseado nos dados parâmetros e faz a consulta para o relatório de produtos
     * @param params os parâmetros de filtro e paginação
     * @return Uma lista de RelatorioProduto
     */
    public ArrayList<RelatorioProduto> relatorioProduto(Properties params) {
        
        String dataDe = Methods.scapeSQL(params.getProperty("dataDe", ""));
        String dataAte = Methods.scapeSQL(params.getProperty("dataAte", ""));
        String fornecedorId = Methods.scapeSQL(params.getProperty("fornecedorId", ""));
        String produtoId = Methods.scapeSQL(params.getProperty("produtoId", ""));
        
        String sql = "SELECT produtos.Id AS 'codigo', "
                + "CONCAT(produtos.Nome, '/', produtos.Unidade) AS 'produto', "
                + "GROUP_CONCAT(DISTINCT(fornecedores.Nome) SEPARATOR '\n\n') AS 'fornecedores', "
                + "(SELECT SUM(notasfiscaisprodutos.Quantidade) FROM notasfiscaisprodutos WHERE notasfiscaisprodutos.ProdutoId = produtos.Id) AS 'entrada', "
                + "(SELECT SUM(QuantidadeAprovada) from pedidosprodutos WHERE pedidosprodutos.ProdutoId = produtos.Id) AS 'saida', "
                + "estoque.Total AS 'estoqueAtual' "
                + "FROM produtos "
                + "LEFT JOIN estoque ON estoque.ProdutoId = produtos.Id "
                + "LEFT JOIN notasfiscaisprodutos ON notasfiscaisprodutos.ProdutoId = produtos.Id "
                + "LEFT JOIN notasfiscais ON notasfiscais.Id = notasfiscaisprodutos.NotaFiscalId "
                + "LEFT JOIN fornecedores ON fornecedores.Id = notasfiscais.FornecedorId "
                + "WHERE produtos.Id > 0 ";
        
        if (! fornecedorId.equals("")) {
            sql += "AND fornecedores.Id = " + fornecedorId + " ";
        }
        if (! produtoId.equals("")) {
            sql += "AND produtos.Id = " + produtoId + " ";
        }
        if (! dataDe.equals("") ) {
            dataDe = DateHandler.getSqlDateTime(dataDe);
            sql += "AND notasfiscaisprodutos.Created >= '" + dataDe + "' ";
        }
        if (! dataAte.equals("") ) {
            dataAte = DateHandler.getSqlDateTime(dataAte);
            sql += "AND notasfiscaisprodutos.Created <= '" + dataAte + "' ";
        }
        
        sql += "AND produtos.Status != 'Deleted' "
                + "AND fornecedores.Status != 'Deleted' "
                + "AND notasfiscais.Status != 'Deleted' "
                + "GROUP BY produtos.Id "
                + "ORDER BY produtos.Id DESC";
        
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            ArrayList<RelatorioProduto> relatorioProdutos = new ArrayList<>();
            while(rs.next()) {
                RelatorioProduto item = new RelatorioProduto();
                item.setCodigo(rs.getInt("codigo"));
                item.setProduto(rs.getString("produto"));
                item.setEntrada(rs.getInt("entrada"));
                item.setSaida(rs.getInt("saida"));
                item.setEstoqueAtual(rs.getInt("estoqueAtual"));
                item.setFornecedores(rs.getString("fornecedores"));
                relatorioProdutos.add(item);
            }
            st.close();
            return relatorioProdutos;
        } catch(SQLException error) {
            Methods.getLogger().error("ProdutoDAO.relatorioProduto: " + error);
            throw new RuntimeException("ProdutoDAO.relatorioProduto: " + error);
        }
    }
}
