/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;

import connection.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import response.CsvResponse;

/**
 *
 * @author desenv01
 */
public class ResponseInterpreter extends AbstractInterpreter {

    public ResponseInterpreter(Connection connection) {
        super(connection);
    }

    @Override
    public String interpret(String expression) {
        java.sql.Connection conn = null;
        try {
            conn = connection.getConnection();
            response.Response response = new CsvResponse();
            PreparedStatement stm = conn.prepareStatement(expression);
            ResultSet rs = stm.executeQuery();
            ResultSetMetaData data = rs.getMetaData();
            return "OK\n"+response.generateResponse(rs);
        } catch (SQLException ex) {
            Logger.getLogger(ResponseInterpreter.class.getName()).log(Level.SEVERE, null, ex);
            return "FAIL:"+ex.getMessage();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ResponseInterpreter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}
