package org.winker.web.service;


import org.springframework.stereotype.Service;
import org.winker.web.Table;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataBaseService {
          public static Map<String,DataSource> map = new HashMap<>();
          public DataSource initMysqlDatasource(){
              org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
              dataSource.setDriverClassName("com.mysql.jdbc.Driver");
              dataSource.setUsername("root");
              dataSource.setUrl("jdbc:mysql://118.24.247.119:3306/mydb");
              dataSource.setPassword("111111");
              dataSource.setName("test1");
              dataSource.setMaxActive(2);
              map.put("test1",dataSource);
              return dataSource;
          }


        public  List<Table> getTableList() throws SQLException {
            List<Table> tableList= new ArrayList<>();
            ResultSet catalogsSet = map.get("test1").getConnection().getMetaData().getCatalogs();

            while (catalogsSet.next()){
                System.out.println(catalogsSet.getString(1));
            }

            ResultSet tableSet =  map.get("test1").getConnection().getMetaData().getTables("mydb",null,null,null);

            while (tableSet.next()){
                System.out.println(tableSet.getString(1));
            }
            return tableList;
        }

}
