/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author desenv01
 */
public class MysqlConnection implements Connection {
    
    private String jdbcUrl;
    private String user;
    private String pass;
    
    public MysqlConnection(String jdbcUrl, String user, String pass) {
        this.jdbcUrl = jdbcUrl;
        this.user = user;
        this.pass = pass;
    }

    @Override
    public java.sql.Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, user, pass);
    }
    
}
