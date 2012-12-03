/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;

/**
 *
 * @author desenv01
 */
public abstract class AbstractInterpreter implements Interpreter {
    protected connection.Connection connection;
    
    public AbstractInterpreter(connection.Connection connection) {
        this.connection = connection;
    }
}
