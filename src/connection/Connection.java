/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.SQLException;

/**
 *
 * @author desenv01
 */
public interface Connection {
    public java.sql.Connection getConnection() throws SQLException;
}
