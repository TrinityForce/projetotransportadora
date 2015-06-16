package br.com.bomtransporte.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author JhonattanSouza_
 */
public class Conexao {

    /**
     *
     */
    protected Connection con;
    /**
     *
     */
    protected PreparedStatement stmt;
    /**
     *
     */
    protected ResultSet rs;

    /**
     * @throws java.sql.SQLException
     * @throws Exception
     */
    protected void open() throws SQLException, Exception {
        String url = "jdbc:mysql://localhost:3306/dbTransportadora";
        String usuario = "gus";
        String senha = "123";

        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url, usuario, senha);
    }

    /**
     *
     * @throws Exception
     */
    protected void close() throws Exception {
        if (con != null) {
            con.close();
        }
    }

    /**
     *
     * @throws Exception
     */
    public void inicializarAtributos() throws Exception {
        stmt = null;
        rs = null;
        open();
    }
}
