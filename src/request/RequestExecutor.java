/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package request;

import connection.Connection;
import interpreter.Interpreter;
import interpreter.InterpreterExecutor;
import interpreter.InterpreterExecutor.TIPO_INTERPRETER;
import interpreter.ResponseInterpreter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author desenv01
 */
public class RequestExecutor extends Thread {

    private static Logger logger = Logger.getLogger("RequestExecutor");
    
    private Connection connection;
    private Socket socket;
    private QueuedRequestExecutor mainThread;
    
    public RequestExecutor(Connection connection, Socket socket, QueuedRequestExecutor mainThread) {
        this.connection = connection;
        this.socket = socket;
        this.mainThread = mainThread;
    }

    @Override
    public void run() {
        process();
    }

    public void process() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String stm = br.readLine();
            if (stm == null) {
                return;
            }
            logger.info("Statement: " + stm);
            TIPO_INTERPRETER tipo = InterpreterExecutor.testStatement(stm);
            if (TIPO_INTERPRETER.RESPONSE.equals(tipo)) {
                new Thread(new ResponseInterpreter(connection, stm, socket)).start();
            } else {
                synchronized (mainThread) {
                    mainThread.requests.add(new Request(socket, stm, connection));
                    mainThread.notify();
                }
            }
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
}
