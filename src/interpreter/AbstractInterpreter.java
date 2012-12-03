/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;

import java.net.Socket;

/**
 *
 * @author desenv01
 */
public abstract class AbstractInterpreter implements Interpreter {

    protected connection.Connection connection;
    protected String expression;
    protected Socket socket;

    public AbstractInterpreter(connection.Connection connection, String expression, Socket socket) {
        this.connection = connection;
        this.expression = expression;
        this.socket = socket;
    }

    @Override
    public String getStatement() {
        return this.expression;
    }
}
