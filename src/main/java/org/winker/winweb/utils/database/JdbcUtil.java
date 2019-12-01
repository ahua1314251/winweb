package org.winker.winweb.utils.database;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcUtil {


    public static List<Table> getTables(Connection connection, String schema) {
        List<Table> tables = new ArrayList<>();
        try {
            ResultSet rs = connection.getMetaData().getTables(null, schema, null, null);
            while (rs != null && rs.next()) {
                System.out.println(rs.getMetaData().getColumnCount());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tables;
    }


    public static List<Column> getColumns() {
        List<Column> columns = new ArrayList<>();


        return columns;
    }


    public static List<String> getSchema(Connection connection, String dataBaseName) {
        List<String> resultList = new ArrayList<>();
        try {
            connection.setCatalog(dataBaseName);
            ResultSet rs = connection.getMetaData().getSchemas();
            while (rs != null && rs.next()) {
                resultList.add(rs.getString("TABLE_SCHEM"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

}


