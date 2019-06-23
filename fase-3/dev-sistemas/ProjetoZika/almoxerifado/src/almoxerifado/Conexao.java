/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almoxerifado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author sanvingla
 */
public class Conexao {

    public Connection conexao = null;
    public Statement instrucao = null;
    public ResultSet resultado = null;

// MySQL
    final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //final String DB_URL = "jdbc:mysql://localhost:3306/BancoZika?user=root&password=&verifyServerCertificate=false&useSSL=false&requireSSL=false";
    String DB_URL = "jdbc:mysql://localhost:3306/BancoZika?user=root&password=&verifyServerCertificate=false&useSSL=false&requireSSL=false";

    public void conectarBanco() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        Class.forName(JDBC_DRIVER);
        conexao = DriverManager.getConnection(DB_URL);
    }

    public void desconectarBanco() throws SQLException {

        if (resultado != null) {
            resultado.close();
        }

        if (instrucao != null) {
            instrucao.close();
        }

        if (conexao != null) {
            conexao.close();
        }

        resultado = null;
        instrucao = null;
        conexao = null;
    }

    public int operacaoDados(String dados) throws SQLException {

        instrucao = conexao.createStatement();
        int x = instrucao.executeUpdate(dados,Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = instrucao.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
            
        

    }

    public void fazerConsulta(String consulta) throws SQLException {

        instrucao = conexao.createStatement();
        resultado = instrucao.executeQuery(consulta);

    }

}
