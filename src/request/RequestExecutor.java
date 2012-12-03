/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package request;

import interpreter.Interpreter;
import interpreter.InterpreterFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author desenv01
 */
public class RequestExecutor extends Thread {
    
    private static Logger logger = Logger.getLogger("RequestExecutor");
    
    private Socket socket;
    private connection.Connection connection;
    
    public RequestExecutor(Socket socket, connection.Connection connection) {
        this.socket = socket;
        this.connection = connection;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String stm = br.readLine();
            if (stm == null) {
                return;
            }
            logger.info("Statement: "+stm);
            
            try {
                Interpreter interpreter = InterpreterFactory.getInterpreter(connection,stm);
                socket.getOutputStream().write((interpreter.interpret(stm)+"\n").getBytes());
            } catch (RuntimeException e) {
                socket.getOutputStream().write(("FAIL:invalid stm\n").getBytes());
            }
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
