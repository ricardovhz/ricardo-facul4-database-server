/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;

/**
 *
 * @author desenv01
 */
public class InterpreterFactory {

    public static Interpreter getInterpreter(connection.Connection connection, String stm) {
        String tratedStm = stm.trim().toUpperCase();
        if (tratedStm.startsWith("SELECT")) {
            return new ResponseInterpreter(connection);
        } else if (tratedStm.startsWith("INSERT")
                || tratedStm.startsWith("DELETE")
                || tratedStm.startsWith("UPDATE")) {
            return new OneWayInterpreter(connection);
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
