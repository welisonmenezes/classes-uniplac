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
 *
 * @author welis
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
                fornecedor.setCreated(Methods.getFriendlyDate(rs.getString("Created")));
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
            sql += " LIMIT 10 OFFSET " + (offset);
        }
            
        //System.out.println(sql);
        return sql;
    }
}
