package org.winker.winweb.service;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.winker.winweb.dao.mysql.mapper.TableInfoMapper;
import org.winker.winweb.dao.mysql.mapper.TemplateMapper;
import org.winker.winweb.dao.mysql.entity.TableInfoDO;
import org.winker.winweb.dao.mysql.entity.TableInfoQuery;
import org.winker.winweb.dao.mysql.entity.TemplateDO;
import org.winker.winweb.dao.mysql.entity.TemplateQuery;
import org.winker.winweb.web.bean.Table;
import org.winker.winweb.web.bean.TemplateEntity;
import org.winker.winweb.utils.database.*;

import javax.sql.DataSource;
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
    @Autowired
    TableInfoMapper tableInfoMapper;
    @Autowired
    TemplateMapper templateMapper;

    public static Map<String, DataSource> map = new HashMap<>();


    public List<Table> getTableList2() throws SQLException {
        List<Table> tableList = new ArrayList<>();

        Connection connection = map.get("test1").getConnection();
        connection.setAutoCommit(true);
        ResultSet catalogsSet = connection.getMetaData().getCatalogs();

        while (catalogsSet.next()) {
            System.out.println(catalogsSet.getString(1));
        }
        catalogsSet.close();


        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tableSet = metaData.getTables("mysql", null, null, null);

        while (tableSet.next()) {
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

    public TableInfoDO getTable(Long id) throws SQLException {
        TableInfoDO tableInfoDO = tableInfoMapper.queryById(id);
        return tableInfoDO;
    }

    public List<TableInfoDO> getTableList(TableInfoQuery tableInfoQuery){
        List<TableInfoDO> tableInfoDOList = tableInfoMapper.queryPage(tableInfoQuery);
        return tableInfoDOList;
    }

    public List<TemplateDO> getTemplateList(TemplateQuery templateQuery){
        List<TemplateDO> templateDOList = templateMapper.queryPage(templateQuery);
        return templateDOList ;
    }

    public int updateTemplate(TemplateDO templateDO){
        return templateMapper.update(templateDO);
    }

    public int createTemplate(TemplateDO templateDO){
        return templateMapper.insert(templateDO);
    }

    public List<TemplateEntity>  createCode(Long sqlId, List<String> templateNames){
        TableInfoDO tableInfo = tableInfoMapper.queryById(sqlId);
        String sql = tableInfo.getSql().replaceAll("`","");
        org.winker.winweb.utils.database.Table table = MysqlParserUtils.getTable(sql);
        table.setBasePath(tableInfo.getBasePath());
        List<TemplateDO> templateDOList = templateMapper.queryByNames(templateNames);
        List<TemplateEntity> templateEntities = new ArrayList<>();
        templateDOList.forEach(item ->{
            TemplateEntity templateEntity = new TemplateEntity();
            templateEntity.setContent(item.getContent());
            templateEntity.setTemplateName(item.getTemplateName());
            templateEntity.setFileName(item.getFileName());
            templateEntities.add(templateEntity);
        });
        return MysqlParserUtils.fillTemplate(table, templateEntities);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        map.put("test1", dataSource);
    }
}
