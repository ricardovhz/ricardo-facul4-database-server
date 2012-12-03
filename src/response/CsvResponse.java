/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package response;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author desenv01
 */
public class CsvResponse implements Response {

    private static final String DELIMITER = ",";

    @Override
    public String generateResponse(ResultSet rs) {
        try {
            StringBuilder sb = new StringBuilder();
            ResultSetMetaData data = rs.getMetaData();
            sb.append(getColumnNames(data));
            sb.append("\n");
            while (rs.next()) {
                sb.append(getValues(rs));
                sb.append("\n");
            }
            return sb.toString();
        } catch (SQLException ex) {
            Logger.getLogger(CsvResponse.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

    private String getValues(ResultSet rs) throws SQLException {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            if (i > 1) {
                sb.append(DELIMITER);
            }
            if (rs.getMetaData().getColumnType(i) == java.sql.Types.DATE) {
                sb.append(new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate(i)));
            } else {
                sb.append(rs.getObject(i));
            }
        }
        
        return sb.toString();
    }

    private String getColumnNames(ResultSetMetaData data) throws SQLException {
        StringBuilder sb = new StringBuilder();
        int columns = data.getColumnCount();
        for (int i = 1; i <= columns; i++) {
            if (i > 1) {
                sb.append(DELIMITER);
            }
            sb.append(data.getColumnName(i));
        }

        return sb.toString();
    }
}
