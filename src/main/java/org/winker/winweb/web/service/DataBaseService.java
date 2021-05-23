package org.winker.winweb.web.service;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.winker.winweb.web.bean.Table;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataBaseService implements InitializingBean {


          @Autowired
          DataSource dataSource;
          public static Map<String,DataSource> map = new HashMap<>();


        public  List<Table> getTableList() throws SQLException {
            List<Table> tableList= new ArrayList<>();

            Connection connection = map.get("test1").getConnection();
            connection.setAutoCommit(true);
            ResultSet catalogsSet =connection.getMetaData().getCatalogs();

            while (catalogsSet.next()){
                System.out.println(catalogsSet.getString(1));
            }
            catalogsSet.close();


            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tableSet =  metaData.getTables("mysql",null,null,null);

            while (tableSet.next()){
                Table table = new Table();
                table.setTableName(tableSet.getString("TABLE_NAME"));
                table.setSchema(tableSet.getString("TABLE_CAT"));
//                System.out.println(tableSet.getString("TABLE_SCHEM")+tableSet.getString("TABLE_CAT")+tableSet.getString("TABLE_TYPE"));
//                System.out.println(tableSet.getString("TABLE_NAME"));
                tableList.add(table);
            }

            tableSet.close();
            connection.close();
            return tableList;
        }

    @Override
    public void afterPropertiesSet() throws Exception {
        map.put("test1",dataSource);
    }
}
