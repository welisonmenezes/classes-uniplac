package DAO;

import Models.NotaFiscal;
import Models.NotaFiscalProduto;
import Models.RelatorioNota;
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
 * Manipula os dados de nota fiscal
 * @author welison
 */
public class NotaFiscalDAO {
    
    private Connection conn;
    private PreparedStatement stmt;
    private Statement st;
    private ResultSet rs;
    private final ConnectionFactory connFac;
    private final ArrayList<NotaFiscal> notasFiscais = new ArrayList();
    private final FillModel helper = new FillModel();
    
    /**
     * método construtor, inicializa a conexão
     */
    public NotaFiscalDAO() {
        connFac = new ConnectionFactory();
    }
    
    /**
     * insere uma nova nota fiscal na base de dados
     * @param notaFiscal a nota fiscal a ser inserida
     * @return o id da última nota fiscal inserida
     */
    public int inserir(NotaFiscal notaFiscal) {
        String sql = "INSERT INTO notasfiscais (Numero, Serie, Valor, Data, Status, Created, FornecedorId) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = connFac.getConexao();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, notaFiscal.getNumero());
            stmt.setInt(2, notaFiscal.getSerie());
            stmt.setDouble(3, notaFiscal.getValor());
            stmt.setString(4, notaFiscal.getData());
            stmt.setString(5, "Publish");
            java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            stmt.setDate(6, sqlDate);
            stmt.setInt(7, notaFiscal.getFornecedor().getId());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            int lastInsertedId = 0;
            if(rs.next()) {
                lastInsertedId = rs.getInt(1);
            }
            connFac.closeAll(rs, stmt, st, conn);
            return lastInsertedId;
        } catch(SQLException error) {
            Methods.getLogger().error("NotaFiscalDAO.inserir: " + error);
            throw new RuntimeException("NotaFiscalDAO.inserir: " + error);
        }
    }
    
    /**
     * add produto na nota fiscal
     * @param notaProduto O objeto NotaFiscalProduto a ser adicionado
     * @return o id do item inserido
     */
    public int inserirProduto(NotaFiscalProduto notaProduto) {
        String sql = "INSERT INTO notasfiscaisprodutos (NotaFiscalId, ProdutoId, Valor, Quantidade, Created) VALUES (?, ?, ?, ?, ?)";
        try {
            conn = connFac.getConexao();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, notaProduto.getNotaFiscal().getId());
            stmt.setInt(2, notaProduto.getProduto().getId());
            stmt.setDouble(3, notaProduto.getValor());
            stmt.setInt(4, notaProduto.getQuantidade());
            java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            stmt.setDate(5, sqlDate);
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            int lastInsertedId = 0;
            if(rs.next()) {
                lastInsertedId = rs.getInt(1);
            }
            connFac.closeAll(rs, stmt, st, conn);
            return lastInsertedId;
        } catch(SQLException error) {
            Methods.getLogger().error("NotaFiscalDAO.inserirProduto: " + error);
            throw new RuntimeException("NotaFiscalDAO.inserirProduto: " + error);
        }
    }
    
    /**
     * altera os dados da nota fiscal na base de dados
     * @param notaFiscal a nota fiscal a ser alterada
     */
    public void alterar(NotaFiscal notaFiscal) {
        String sql = "UPDATE notasfiscais SET Numero=?, Serie=?, Valor=?, "
                + "Data=?, FornecedorId=? WHERE Id=?";
        try {
            conn = connFac.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, notaFiscal.getNumero());
            stmt.setInt(2, notaFiscal.getSerie());
            stmt.setDouble(3, notaFiscal.getValor());
            stmt.setString(4, notaFiscal.getData());
            stmt.setInt(5, notaFiscal.getFornecedor().getId());
            stmt.setInt(6, notaFiscal.getId());
            stmt.execute();
            connFac.closeAll(rs, stmt, st, conn);
        } catch(SQLException error) {
            Methods.getLogger().error("NotaFiscalDAO.alterar: " + error);
            throw new RuntimeException("NotaFiscalDAO.alterar: " + error);
        }
    }
    
    /**
     * Deleta os prdutos da nota fiscal
     * @param notaId o Id da nota fiscal cujo produtos serão deletados
     */
    public void deletarProdutos(int notaId) {
        String sql = "DELETE FROM notasfiscaisprodutos WHERE NotaFiscalId=?";
        try {
            conn = connFac.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, notaId);
            stmt.execute();
            connFac.closeAll(rs, stmt, st, conn);
        } catch(SQLException error) {
            Methods.getLogger().error("NotaFiscalDAO.deletarProdutos: " + error);
            throw new RuntimeException("NotaFiscalDAO.deletarProdutos: " + error);
        }
    }
    
    /**
     * Deleta o produto da nota fiscal
     * @param notaId o Id da nota fiscal 
     * @param ProdutoId o Id do produto 
     */
    public void deletarProduto(int notaId, int ProdutoId) {
        String sql = "DELETE FROM notasfiscaisprodutos WHERE NotaFiscalId=? AND ProdutoId=?";
        try {
            conn = connFac.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, notaId);
            stmt.setInt(2, ProdutoId);
            stmt.execute();
            connFac.closeAll(rs, stmt, st, conn);
        } catch(SQLException error) {
            Methods.getLogger().error("NotaFiscalDAO.deletarProduto: " + error);
            throw new RuntimeException("NotaFiscalDAO.deletarProduto: " + error);
        }
    }
    
    /**
     * 'deleta' a nota fiscal da visualização, na base de dados altera apenas o status para 'Deleted'
     * @param notaId o Id da nota fiscal a ser 'deletado'
     */
    public void deletar(int notaId) {
        String sql = "UPDATE notasfiscais SET Status='Deleted' WHERE Id=?";
        try {
            conn = connFac.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, notaId);
            stmt.execute();
            connFac.closeAll(rs, stmt, st, conn);
        } catch(SQLException error) {
            Methods.getLogger().error("NotaFiscalDAO.deletar: " + error);
            throw new RuntimeException("NotaFiscalDAO.deletar: " + error);
        }
    }
    
    /**
     * seleciona uma nota fiscal da base de dados pelo seu Id
     * @param Id o Id da nota fiscal a ser retornada
     * @return a nota fiscal com Id correspondente
     */
    public NotaFiscal selecionarPorId(String Id) {
        String sql = "SELECT notasFiscais.Id as nId, notasFiscais.Numero as nNumero, notasFiscais.Serie as nSerie,"
                    + "notasFiscais.Data as nData, notasFiscais.Valor as nValor, notasFiscais.Status as nStatus,"
                    + "notasFiscais.Created as nCreated, fornecedores.Id as fId, fornecedores.Cnpj as fCnpj,"
                    + "fornecedores.Nome as fNome, fornecedores.Status as fStatus, fornecedores.Telefone as fTelefone,"
                    + "fornecedores.Created as fCreated"
                    + " FROM notasFiscais LEFT JOIN fornecedores ON fornecedores.Id = notasFiscais.FornecedorId WHERE notasFiscais.Id = ?";
        int queryId = Integer.parseInt(Id);
        try {
            conn = connFac.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, queryId);
            rs = stmt.executeQuery();
            NotaFiscal notaFiscal = new NotaFiscal();
            while(rs.next()) {
                this.helper.fillNota(notaFiscal, rs);
            }
            connFac.closeAll(rs, stmt, st, conn);
            return notaFiscal;
        } catch (SQLException error) {
            Methods.getLogger().error("NotaFiscalDAO.selecionarPorId: " + error);
            throw new RuntimeException("NotaFiscalDAO.selecionarPorId: " + error);
        }
    }
    
    /**
     * seleciona os produtos da nota fiscal pelo id da nota fiscal
     * @param Id o Id da nota fiscal
     * @return os produtos da nota fiscal com Id correspondente
     */
    public ArrayList<NotaFiscalProduto> selecionarProdutos(String Id) {
        String sql = "SELECT * FROM notasfiscaisprodutos\n" +
                    "LEFT JOIN produtos on notasfiscaisprodutos.ProdutoId = produtos.Id\n" +
                    "WHERE NotaFiscalId = ? AND produtos.Status != 'Deleted'";
        int queryId = Integer.parseInt(Id);
        try {
            conn = connFac.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, queryId);
            rs = stmt.executeQuery();
            ArrayList<NotaFiscalProduto> nfps = new ArrayList();
            while(rs.next()) {
                NotaFiscalProduto nfp = new NotaFiscalProduto();
                this.helper.fillNotaFiscalProduto(nfp, rs);
                nfps.add(nfp);
            }
            connFac.closeAll(rs, stmt, st, conn);
            return nfps;
        } catch (SQLException error) {
            Methods.getLogger().error("NotaFiscalDAO.selecionarProdutos: " + error);
            throw new RuntimeException("NotaFiscalDAO.selecionarProdutos: " + error);
        }
    }
    
    /**
     * seleciona as notas fiscais correspondentes aos parâmetros de filtragem e paginação
     * @param params os parâmetros de filtragem e paginação
     * @return uma lista de notas fiscais correspondentes
     */
    public ArrayList<NotaFiscal> selecionar(Properties params) {
        String sql = buildSelectQuery(params, false);
        try {
            conn = connFac.getConexao();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                NotaFiscal notaFiscal = new NotaFiscal();
                this.helper.fillNota(notaFiscal, rs);
                notasFiscais.add(notaFiscal);
            }
            connFac.closeAll(rs, stmt, st, conn);
            return notasFiscais;
        } catch(SQLException error) {
            Methods.getLogger().error("NotaFiscalDAO.selecionar: " + error);
            throw new RuntimeException("NotaFiscalDAO.selecionar: " + error);
        }
    }
    
    /**
     * o total de notas fiscais que correspondem aos parâmetros de filtro e paginação
     * @param params os parâmetros de filtro e paginação
     * @return o total de notas fiscais
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
            Methods.getLogger().error("NotaFiscalDAO.total: " + error);
            throw new RuntimeException("NotaFiscalDAO.total: " + error);
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
        String numero = Methods.scapeSQL(params.getProperty("numero", ""));
        String cnpj = Methods.scapeSQL(params.getProperty("cnpj", ""));
        String data = Methods.scapeSQL(params.getProperty("data", ""));
        String sql;
        
        if (! isCount) {
            sql = "SELECT notasFiscais.Id as nId, notasFiscais.Numero as nNumero, notasFiscais.Serie as nSerie,"
                    + "notasFiscais.Data as nData, notasFiscais.Valor as nValor, notasFiscais.Status as nStatus,"
                    + "notasFiscais.Created as nCreated, fornecedores.Id as fId, fornecedores.Cnpj as fCnpj,"
                    + "fornecedores.Nome as fNome, fornecedores.Status as fStatus, fornecedores.Telefone as fTelefone,"
                    + "fornecedores.Created as fCreated"
                    + " FROM notasFiscais LEFT JOIN fornecedores ON fornecedores.Id = notasfiscais.FornecedorId WHERE notasfiscais.Status != 'Deleted'";
        } else {
            sql = "SELECT COUNT(notasFiscais.Id) FROM notasFiscais LEFT JOIN fornecedores ON fornecedores.Id = notasfiscais.FornecedorId WHERE notasfiscais.Status != 'Deleted'";
        }
        
        if (! numero.equals("")) {
            sql += " AND Numero LIKE '%" + numero + "%'";
        }
        
        if (! cnpj.equals("")) {
            sql += " AND Cnpj LIKE '%" + cnpj + "%'";
        }
        
        if (! data.equals("")) {
            String sqlDate = DateHandler.getSqlDateTime(data);
            sql += " AND notasFiscais.Created >= '" + sqlDate + "'";
        }
        
        if (! isCount) {
            sql += " ORDER BY " + params.getProperty("orderby", "nId") + " " + params.getProperty("order", "DESC");
            sql += " LIMIT 10 OFFSET " + (offset);
        }
            
        return sql;
    }
    
    /**
     * constrói a query baseado nos dados parâmetros e faz a consulta para o relatório de notas fiscais
     * @param params os parâmetros de filtro e paginação
     * @return Uma lista de RelatorioNota
     */
    public ArrayList<RelatorioNota> relatorioNota(Properties params) {
        
        String dataDe = Methods.scapeSQL(params.getProperty("dataDe", ""));
        String dataAte = Methods.scapeSQL(params.getProperty("dataAte", ""));
        String fornecedorId = Methods.scapeSQL(params.getProperty("fornecedorId", ""));
        String produtoId = Methods.scapeSQL(params.getProperty("produtoId", ""));
        
        String sql = "SELECT notasfiscais.Id AS codigo, "
                + "notasfiscais.Created AS data, "
                + "notasfiscais.Numero AS numero, "
                + "notasfiscais.Valor AS valor, "
                + "fornecedores.Nome AS fornecedor, "
                + "GROUP_CONCAT(DISTINCT(produtos.Nome) SEPARATOR '\\n\\n') AS 'produtos' "
                + "FROM notasfiscais  "
                + "LEFT JOIN notasfiscaisprodutos ON notasfiscaisprodutos.NotaFiscalId = notasfiscais.Id "
                + "LEFT JOIN fornecedores ON fornecedores.Id = notasfiscais.FornecedorId "
                + "LEFT JOIN produtos ON produtos.Id = notasfiscaisprodutos.ProdutoId "
                + "WHERE notasfiscais.Id > 0 ";
        
        if (! fornecedorId.equals("")) {
            sql += "AND fornecedores.Id = " + fornecedorId + " ";
        }
        if (! produtoId.equals("")) {
            sql += "AND produtos.Id = " + produtoId + " ";
        }
        if (! dataDe.equals("") ) {
            dataDe = DateHandler.getSqlDateTime(dataDe);
            sql += "AND notasfiscais.Created >= '" + dataDe + "' ";
        }
        if (! dataAte.equals("") ) {
            dataAte = DateHandler.getSqlDateTime(dataAte);
            sql += "AND notasfiscais.Created <= '" + dataAte + "' ";
        }
        
        sql += "AND produtos.Status != 'Deleted' "
                + "AND fornecedores.Status != 'Deleted' "
                + "AND notasfiscais.Status != 'Deleted' "
                + "GROUP BY notasfiscais.Id "
                + "ORDER BY notasfiscais.Id DESC";
        
        System.out.println(sql);
        
        try {
            conn = connFac.getConexao();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            ArrayList<RelatorioNota> relatorioNotas = new ArrayList<>();
            while(rs.next()) {
                RelatorioNota item = new RelatorioNota();
                this.helper.fillRelatorioNota(item, rs);
                relatorioNotas.add(item);
            }
            connFac.closeAll(rs, stmt, st, conn);
            return relatorioNotas;
        } catch(SQLException error) {
            Methods.getLogger().error("NotaFiscalDAO.relatorioNota: " + error);
            throw new RuntimeException("NotaFiscalDAO.relatorioNota: " + error);
        }
    }
}
