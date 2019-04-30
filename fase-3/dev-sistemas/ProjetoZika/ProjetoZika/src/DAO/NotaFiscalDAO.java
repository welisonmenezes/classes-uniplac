package DAO;

import Models.NotaFiscal;
import Models.NotaFiscalProduto;
import Models.Produto;
import Utils.FillModel;
import Utils.DateHandler;
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
    
    private final Connection conn;
    private PreparedStatement stmt;
    private Statement st;
    private ResultSet rs;
    private final ArrayList<NotaFiscal> notasFiscais = new ArrayList();
    private final FillModel helper = new FillModel();
    
    /**
     * método construtor, inicializa a conexão
     */
    public NotaFiscalDAO() {
        conn = new ConnectionFactory().getConexao();
    }
    
    /**
     * insere uma nova nota fiscal na base de dados
     * @param notaFiscal a nota fiscal a ser inserida
     * @return o id da última nota fiscal inserida
     */
    public int inserir(NotaFiscal notaFiscal) {
        String sql = "INSERT INTO notasfiscais (Numero, Serie, Valor, Data, Status, Created, FornecedorId) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
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
            stmt.close();
            return lastInsertedId;
        } catch(SQLException error) {
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
            stmt.close();
            return lastInsertedId;
        } catch(SQLException error) {
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
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, notaFiscal.getNumero());
            stmt.setInt(2, notaFiscal.getSerie());
            stmt.setDouble(3, notaFiscal.getValor());
            stmt.setString(4, notaFiscal.getData());
            stmt.setInt(5, notaFiscal.getFornecedor().getId());
            stmt.setInt(6, notaFiscal.getId());
            stmt.execute();
            stmt.close();
        } catch(SQLException error) {
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
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, notaId);
            stmt.execute();
            stmt.close();
        } catch(SQLException error) {
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
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, notaId);
            stmt.setInt(2, ProdutoId);
            stmt.execute();
            stmt.close();
        } catch(SQLException error) {
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
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, notaId);
            stmt.execute();
            stmt.close();
        } catch(SQLException error) {
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
                    + " FROM notasFiscais LEFT JOIN fornecedores ON fornecedores.Id = notasFiscais.FornecedorId WHERE notasFiscais.Id = " + Id;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            NotaFiscal notaFiscal = new NotaFiscal();
            while(rs.next()) {
                this.helper.fillNotas(notaFiscal, rs);
            }
            st.close();
            return notaFiscal;
        } catch (SQLException error) {
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
                    "WHERE NotaFiscalId = " + Id + " AND produtos.Status != 'Deleted'";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            
            ArrayList<NotaFiscalProduto> nfps = new ArrayList();
            while(rs.next()) {
                NotaFiscalProduto nfp = new NotaFiscalProduto();
                nfp.setQuantidade(rs.getInt("Quantidade"));
                nfp.setValor(rs.getFloat("Valor"));
                nfp.setCreated(rs.getString("notasfiscaisprodutos.Created"));
                // o produto da nota
                Produto p = new Produto();
                p.setId(rs.getInt("Id"));
                p.setNome(rs.getString("Nome"));
                p.setDescricao(rs.getString("Descricao"));
                p.setStatus(rs.getString("Status"));
                p.setUnidade(rs.getString("Unidade"));
                p.setCreated(rs.getString("produtos.Created"));
                
                nfp.setProduto(p);
                nfps.add(nfp);
            }
            st.close();
            return nfps;
        } catch (SQLException error) {
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
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                NotaFiscal notaFiscal = new NotaFiscal();
                this.helper.fillNotas(notaFiscal, rs);
                notasFiscais.add(notaFiscal);
            }
            st.close();
            return notasFiscais;
        } catch(SQLException error) {
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
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            return rs.getInt(1);
        } catch(SQLException error) {
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
        String numero = params.getProperty("numero", "");
        String cnpj = params.getProperty("cnpj", "");
        String data = params.getProperty("data", "");
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
}
