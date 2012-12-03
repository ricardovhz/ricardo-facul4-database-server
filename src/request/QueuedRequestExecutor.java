/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package request;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author desenv01
 */
public class QueuedRequestExecutor extends Thread {

    public static Queue<Request> requests = new LinkedList<>();

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (this) {
                    wait();
                    Request r = requests.poll();
                    interpreter.InterpreterExecutor.addInterpreter(r.getConnection(), r.getStm(), r.getSocket());
                }
            } catch (InterruptedException e) {
            }

        }
    }
}
