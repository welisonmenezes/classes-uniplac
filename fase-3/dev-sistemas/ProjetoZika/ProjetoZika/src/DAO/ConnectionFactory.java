/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

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
        } catch(Exception error) {
            throw new RuntimeException("ConnectionFactory.getConexao: " + error);
        }
    }
}
