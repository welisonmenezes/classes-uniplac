/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Fornecedor;
import Utils.Methods;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

/**
 *  Manipula os dados de fornecedores
 * @author welison
 */
public class FornecedorDAO {
    
    private final Connection conn;
    private PreparedStatement stmt;
    private Statement st;
    private ResultSet rs;
    private final ArrayList<Fornecedor> fornecedores = new ArrayList();
    
    /**
     * método construtor, inicializa a conexão
     */
    public FornecedorDAO() {
        conn = new ConnectionFactory().getConexao();
    }
    
    /**
     * insere um novo fornecedor na base de dados
     * @param fornecedor o fornecedor a ser inserido
     * @return o id do último item inserido
     */
    public int inserir(Fornecedor fornecedor) {
        String sql = "INSERT INTO fornecedores (Nome, Telefone, Cnpj, Status, Created) VALUES (?, ?, ?, ?, ?)";
        try {
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getTelefone());
            stmt.setString(3, fornecedor.getCnpj());
            stmt.setString(4, "Publish");
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
            throw new RuntimeException("FornecedorDAO.inserir: " + error);
        }
    }
    
    /**
     * altera o dado do fornecedor na base de dados
     * @param fornecedor o fornecedor a ser alterado
     */
    public void alterar(Fornecedor fornecedor) {
        String sql = "UPDATE fornecedores SET Nome=?, Telefone=?, Cnpj=? WHERE Id=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getTelefone());
            stmt.setString(3, fornecedor.getCnpj());
            stmt.setInt(4, fornecedor.getId());
            stmt.execute();
            stmt.close();
        } catch(Exception error) {
            throw new RuntimeException("FornecedorDAO.alterar: " + error);
        }
    }
    
    /**
     * 'deleta' o fornecedor da visualização, na base de dados altera apenas o status para 'Deleted'
     * @param Id o Id do fornecedor a ser 'deletado'
     */
    public void deletar(int Id) {
        String sql = "UPDATE fornecedores SET Status='Deleted' WHERE Id=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Id);
            stmt.execute();
            stmt.close();
        } catch(Exception error) {
            throw new RuntimeException("FornecedorDAO.deletar: " + error);
        }
    }
    
    /**
     * seleciona um fornecedor da base de dados pelo seu Id
     * @param Id o Id do fornecedor a ser retornado
     * @return o fornecedor com Id correspondente
     */
    public Fornecedor selecionarPorId(String Id) {
        String sql = "SELECT * FROM fornecedores WHERE Id = " + Id;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            Fornecedor fornecedor = new Fornecedor();
            while(rs.next()) {
                fornecedor.setId(rs.getInt("Id"));
                fornecedor.setCnpj(rs.getString("Cnpj"));
                fornecedor.setNome(rs.getString("Nome"));
                fornecedor.setStatus(rs.getString("Status"));
                fornecedor.setTelefone(rs.getString("Telefone"));
                //fornecedor.setCreated(Methods.getFriendlyDate(rs.getString("Created")));
                fornecedor.setCreated(rs.getString("Created"));
            }
            st.close();
            return fornecedor;
        } catch (Exception error) {
            throw new RuntimeException("FornecedorDAO.selecionarPorId: " + error);
        }
    }
    
    /**
     * conta quantos fornecedores exsitem com o dado cnpj
     * @param cnpj o cnpj a ser buscado
     * @return o total de resultados
     */
    public int temCnpj(String cnpj) {
        String sql = "SELECT COUNT(Id) FROM fornecedores WHERE Cnpj = '" + cnpj + "'";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            return rs.getInt(1);
        } catch(Exception error) {
            throw new RuntimeException("FornecedorDAO.temCnpj: " + error);
        }
    }
    
    /**
     * seleciona os fornecedores por um dado cnpj
     * @param cnpj o cnpj do fornecedor desejado
     * @return uma lista de fornecedores correspondentes
     */
    public ArrayList<Fornecedor> selecionarPorCnpj(String cnpj) {
        String sql = "SELECT * FROM fornecedores WHERE Status != 'Deleted' AND Cnpj LIKE '%"+ cnpj +"%' LIMIT 50";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("Id"));
                fornecedor.setCnpj(rs.getString("Cnpj"));
                fornecedor.setNome(rs.getString("Nome"));
                fornecedor.setStatus(rs.getString("Status"));
                fornecedor.setTelefone(rs.getString("Telefone"));
                //fornecedor.setCreated(Methods.getFriendlyDate(rs.getString("Created")));
                fornecedor.setCreated(rs.getString("Created"));
                fornecedores.add(fornecedor);
            }
            st.close();
            //System.out.println(sql);
            return fornecedores;
        } catch(Exception error) {
            throw new RuntimeException("FornecedorDAO.selecionarPorCnpj: " + error);
        }
    }
    
    /**
     * seleciona os fornecedores correspondentes aos parâmetros de filtragem e paginação
     * @param params os parâmetros de filtragem e paginação
     * @return uma lista de fornecedores correspondentes
     */
    public ArrayList<Fornecedor> selecionar(Properties params) {
        String sql = buildSelectQuery(params, false);
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("Id"));
                fornecedor.setCnpj(rs.getString("Cnpj"));
                fornecedor.setNome(rs.getString("Nome"));
                fornecedor.setStatus(rs.getString("Status"));
                fornecedor.setTelefone(rs.getString("Telefone"));
                //fornecedor.setCreated(Methods.getFriendlyDate(rs.getString("Created")));
                fornecedor.setCreated(rs.getString("Created"));
                fornecedores.add(fornecedor);
            }
            st.close();
            return fornecedores;
        } catch(Exception error) {
            throw new RuntimeException("FornecedorDAO.selecionar: " + error);
        }
    }
    
    /**
     * o total de fornecedores que correspondem aos parâmetros de filtro e paginação
     * @param params os parâmetros de filtro e paginação
     * @return o total de fornecedores
     */
    public int total(Properties params) {
        String sql = buildSelectQuery(params, true);
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            return rs.getInt(1);
        } catch(Exception error) {
            throw new RuntimeException("FornecedorDAO.total: " + error);
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
        String cnpj = params.getProperty("cnpj", "");
        String telefone = params.getProperty("telefone", "");
        String sql;
        
        if (! isCount) {
            sql = "SELECT * FROM fornecedores WHERE Status != 'Deleted'";
        } else {
            sql = "SELECT COUNT(Id) FROM fornecedores WHERE Status != 'Deleted'";
        }
        
        if (! nome.equals("")) {
            sql += " AND Nome LIKE '%" + nome + "%'";
        }
        
        if (! telefone.equals("")) {
            sql += " AND Telefone LIKE '%" + telefone + "%'";
        }
        
        if (! cnpj.equals("")) {
            sql += " AND Cnpj LIKE '%" + cnpj + "%'";
        }
        
        
        
        if (! isCount) {
            //sql += " ORDER BY Id DESC";
            sql += " ORDER BY " + params.getProperty("orderby", "Id") + " " + params.getProperty("order", "DESC");
            sql += " LIMIT 10 OFFSET " + (offset);
        }
            
        //System.out.println(sql);
        return sql;
    }
}
