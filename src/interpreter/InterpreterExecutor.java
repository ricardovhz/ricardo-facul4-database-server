/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author desenv01
 */
public class InterpreterExecutor {

//    private static InterpreterThread oneWayInterpreter;
//    private static InterpreterThread responseInterpreter;
//
//    public static void initThreads() {
//        oneWayInterpreter = new InterpreterThread();
////        responseInterpreter = new InterpreterThread();
//        oneWayInterpreter.start();
////        responseInterpreter.start();
//    }
    public static enum TIPO_INTERPRETER {

        ONEWAY,
        RESPONSE
    }

    public static TIPO_INTERPRETER testStatement(String stm) {
        String tratedStm = stm.trim().toUpperCase();
        if (tratedStm.startsWith("SELECT")) {
            return TIPO_INTERPRETER.RESPONSE;
        } else if (tratedStm.startsWith("INSERT")
                || tratedStm.startsWith("DELETE")
                || tratedStm.startsWith("UPDATE")) {
            return TIPO_INTERPRETER.ONEWAY;
        } else {
            return null;
        }
    }

    public static void addInterpreter(connection.Connection connection, String stm, Socket socket) {
        TIPO_INTERPRETER tipo = testStatement(stm);

        switch (tipo) {
            case RESPONSE:
                new ResponseInterpreter(connection, stm, socket).interpret();
                break;
            case ONEWAY:
                new OneWayInterpreter(connection, stm, socket).interpret();
                break;
            default:
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(InterpreterExecutor.class.getName()).log(Level.SEVERE, null, ex);
                }
                throw new UnsupportedOperationException();
        }
    }
}
