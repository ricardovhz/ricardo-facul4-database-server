/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import interpreter.InterpreterExecutor;
import service.MysqlService;

/**
 *
 * @author desenv01
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        InterpreterExecutor.initThreads();
        new MysqlService().start();
    }
}
