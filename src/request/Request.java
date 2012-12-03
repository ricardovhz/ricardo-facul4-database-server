/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package request;

import java.net.Socket;

/**
 *
 * @author desenv01
 */
public class Request {

    private Socket socket;
    private String stm;
    private connection.Connection Connection;

    public Request() {
    }

    public Request(Socket socket, String stm, connection.Connection Connection) {
        this.socket = socket;
        this.stm = stm;
        this.Connection = Connection;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getStm() {
        return stm;
    }

    public void setStm(String stm) {
        this.stm = stm;
    }

    public connection.Connection getConnection() {
        return Connection;
    }

    public void setConnection(connection.Connection Connection) {
        this.Connection = Connection;
    }
}
