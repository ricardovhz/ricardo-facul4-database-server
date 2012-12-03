/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;

import connection.Connection;
import java.io.IOException;
import java.net.Socket;
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
public class ResponseInterpreter extends AbstractInterpreter implements Runnable {

    public ResponseInterpreter(Connection connection, String expression, Socket socket) {
        super(connection, expression, socket);
    }

    @Override
    public void interpret() {
        java.sql.Connection conn = null;
        try {
            conn = connection.getConnection();
            response.Response response = new CsvResponse();
            PreparedStatement stm = conn.prepareStatement(expression);
            ResultSet rs = stm.executeQuery();
            ResultSetMetaData data = rs.getMetaData();
            socket.getOutputStream().write(("OK\n"+response.generateResponse(rs)).getBytes());
        } catch (IOException ex) {
            Logger.getLogger(ResponseInterpreter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
//            Logger.getLogger(ResponseInterpreter.class.getName()).log(Level.SEVERE, null, ex);
            try {
                socket.getOutputStream().write(("FAIL:".getBytes())); //"+ex.getMessage()).getBytes());
            } catch (IOException e) {
                Logger.getLogger(ResponseInterpreter.class.getName()).log(Level.SEVERE, null, e);
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ResponseInterpreter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (socket!=null) {
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(ResponseInterpreter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    @Override
    public void run() {
        interpret();
    }
}
