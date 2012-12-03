/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;

import connection.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author desenv01
 */
public class OneWayInterpreter extends AbstractInterpreter {

    @Override
    public String interpret(String expression) {
        java.sql.Connection conn = null;
        try {
            conn = connection.getConnection();
            PreparedStatement stm = conn.prepareStatement(expression);
            stm.executeUpdate();
            return "OK";
        } catch (SQLException ex) {
            Logger.getLogger(OneWayInterpreter.class.getName()).log(Level.SEVERE, null, ex);
            return "FAIL:" + ex.getMessage();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OneWayInterpreter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public OneWayInterpreter(Connection connection) {
        super(connection);
    }
}
