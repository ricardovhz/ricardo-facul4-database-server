/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author desenv01
 */
public abstract class AbstractService extends Thread implements Service {
    
    private static Logger logger = Logger.getLogger("AbstractService");

    protected int port;
    protected connection.Connection connection;

    public AbstractService(connection.Connection connection, int port) {
        this.connection = connection;
        this.port = port;
    }

    @Override
    public void run() {
        this.process();
    }

    @Override
    public void process() {
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Pess. ctrl-c to quit");

            while (true) {
                logger.info("Waiting for connection on port "+port+"...");
                Socket socket = server.accept();
                logger.info("connected to "+socket.getRemoteSocketAddress());
                new request.RequestExecutor(socket, connection).start();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(AbstractService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
