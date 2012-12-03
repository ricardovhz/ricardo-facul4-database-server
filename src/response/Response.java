/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package response;

import java.sql.ResultSet;

/**
 *
 * @author desenv01
 */
public interface Response {
    public String generateResponse(ResultSet rs);
}
