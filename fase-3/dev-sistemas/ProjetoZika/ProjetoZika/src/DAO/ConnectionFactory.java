package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Faz a conexão com o banco de dados
 * @author welison
 */
public class ConnectionFactory {
    
    /**
     * Retorna a conexão com o banco de dados
     * @return a conexão com o banco de dados
     */
    public Connection getConexao() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/projetozika", "root", "");
        } catch(SQLException error) {
            throw new RuntimeException("ConnectionFactory.getConexao: " + error);
        }
    }
}
