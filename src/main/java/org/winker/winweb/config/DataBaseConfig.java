package org.winker.winweb.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DataBaseConfig {

    @Bean
    public DataSource initMysqlDatasource() throws SQLException {
//        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUsername("root");
//        dataSource.setUrl("jdbc:mysql://118.24.247.119:3306/mydb");
//        dataSource.setPassword("1314251");
//        dataSource.setName("test1");
//        dataSource.setMaxActive(2);
//        dataSource.setInitialSize(2);
//        dataSource.setMaxWait(2);
//        dataSource.setInitSQL("select 1");
//        dataSource.setMinIdle(2);

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/mydb");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver"); //这个可以缺省的，会根据url自动识别
        dataSource.setUsername("root");
        dataSource.setPassword("ahua1314251");
        dataSource.setName("test1");

        //下面都是可选的配置
        dataSource.setInitialSize(2);  //初始连接数，默认0
        dataSource.setMaxActive(3);  //最大连接数，默认8
        dataSource.setMinIdle(1);  //最小闲置数
        dataSource.setMaxWait(2000);  //获取连接的最大等待时间，单位毫秒
        dataSource.setPoolPreparedStatements(true); //缓存PreparedStatement，默认false
        dataSource.setMaxOpenPreparedStatements(3); //缓存PreparedStatement的最大数量，默认-1（不缓存）。大于0时会自动开启缓存PreparedStatement，所以可以省略上一句代码
        dataSource.init();
        return dataSource;
    }




}
