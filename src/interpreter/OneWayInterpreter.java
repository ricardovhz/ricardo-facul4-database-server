/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;

import connection.Connection;
import java.io.IOException;
import java.net.Socket;
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
    public synchronized void interpret() {
        java.sql.Connection conn = null;
        try {
            conn = connection.getConnection();
            PreparedStatement stm = conn.prepareStatement(expression);
            stm.executeUpdate();
            socket.getOutputStream().write("OK\n".getBytes());
        } catch (IOException ex) {
            Logger.getLogger(OneWayInterpreter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
//            Logger.getLogger(OneWayInterpreter.class.getName()).log(Level.SEVERE, null, ex);
            try {
                socket.getOutputStream().write(("FAIL:" + ex.getMessage()+"\n").getBytes());
            } catch (IOException e) {
                Logger.getLogger(OneWayInterpreter.class.getName()).log(Level.SEVERE, null, e);
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OneWayInterpreter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (socket!=null) {
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(OneWayInterpreter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public OneWayInterpreter(Connection connection, String expression, Socket socket) {
        super(connection, expression, socket);
    }

}
