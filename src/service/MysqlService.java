/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import connection.MysqlConnection;

/**
 *
 * @author desenv01
 */
public class MysqlService extends AbstractService {

    public MysqlService() {
        super(new MysqlConnection("jdbc:mysql://localhost/teste", "root", "rootroot"), 1234);
    }
}
