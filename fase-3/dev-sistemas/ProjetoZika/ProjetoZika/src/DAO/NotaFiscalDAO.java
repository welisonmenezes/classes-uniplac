/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.NotaFiscal;
import Models.NotaFiscalProduto;
import Utils.Methods;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

/**
 *
 * @author welis
 */
public class NotaFiscalDAO {
    
    private final Connection conn;
    private PreparedStatement stmt;
    private Statement st;
    private ResultSet rs;
    
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
            ResultSet rs = stmt.getGeneratedKeys();
            int lastInsertedId = 0;
            if(rs.next()) {
                lastInsertedId = rs.getInt(1);
            }
            stmt.close();
            return lastInsertedId;
        } catch(Exception error) {
            throw new RuntimeException("NotaFiscalDAO.inserir: " + error);
        }
    }
    
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
            ResultSet rs = stmt.getGeneratedKeys();
            int lastInsertedId = 0;
            if(rs.next()) {
                lastInsertedId = rs.getInt(1);
            }
            stmt.close();
            return lastInsertedId;
        } catch(Exception error) {
            throw new RuntimeException("NotaFiscalDAO.inserirProduto: " + error);
        }
    }
    
    /**
     * seleciona uma nota fiscal da base de dados pelo seu Id
     * @param Id o Id da nota fiscal a ser retornada
     * @return a nota fiscal com Id correspondente
     */
    public NotaFiscal selecionarPorId(String Id) {
        String sql = "SELECT * FROM notasfiscais WHERE Id = " + Id;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            NotaFiscal notaFiscal = new NotaFiscal();
            while(rs.next()) {
                notaFiscal.setId(rs.getInt("Id"));
                notaFiscal.setNumero(rs.getLong("Numero"));
                notaFiscal.setSerie(rs.getInt("Serie"));
                notaFiscal.setData(Methods.getFriendlyBirthday(rs.getString("Data")));
                notaFiscal.setValor(rs.getFloat("Valor"));
                notaFiscal.setStatus(rs.getString("Status"));
                notaFiscal.setFornecedor(null);
                notaFiscal.setCreated(Methods.getFriendlyDate(rs.getString("Created")));
            }
            st.close();
            return notaFiscal;
        } catch (Exception error) {
            throw new RuntimeException("NotaFiscalDAO.selecionarPorId: " + error);
        }
    }
}
