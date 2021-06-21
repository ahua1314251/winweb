package org.winker.winweb.service;

import com.alibaba.druid.sql.ast.statement.SQLCreateTableStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlCreateTableParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.winker.winweb.web.bean.Column;
import org.winker.winweb.web.bean.Table;
import org.winker.winweb.web.bean.TemplateBean;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MysqlParser {

    private static VelocityEngine velocityEngine = new VelocityEngine();

    public static Table getTable(String sql){
        Table table = new Table();
        // 新建 MySQL Parser
        MySqlCreateTableParser parser = new MySqlCreateTableParser(sql);


        // 使用Parser解析生成AST，这里SQLStatement就是AST
        SQLCreateTableStatement statement = parser.parseCreateTable();
        List<Column> columns = convertColumns(statement);
        table.setDbType(statement.getDbType());
        table.setTableName(columns.get(0).getTableName());
        table.setColumns(columns);
        table.setSchema(statement.getSchema());
        return table;
    }

    private static List<Column>  convertColumns(SQLCreateTableStatement statement){

        List<Column> columns = new ArrayList<>();
        statement.getColumnDefinitions().forEach(item->{
            Column column = new Column();
            column.setName(item.getColumnName());
            column.setType(item.getDataType().getName());
            column.setTableName(statement.getTableName());
            column.setDbType(statement.getDbType());
            columns.add(column);
        });
        return columns;
    }

    public static List<TemplateBean>  fillTemplate(Table ddl, List<TemplateBean> templateBeans){
        List<TemplateBean> resultList = new ArrayList<>();
        for(TemplateBean templateBean : templateBeans){
            VelocityContext ctx = new VelocityContext();
            ctx.put("table",ddl);
            StringWriter sw = new StringWriter();
            velocityEngine.evaluate(ctx,sw,"",templateBean.getContent());
            templateBean.setResult(sw.toString());
            resultList.add(templateBean);
        }
        return resultList;
    }

    public static void main(String[] args) throws JsonProcessingException {
        String sql = "CREATE TABLE search_index_community (\n" +
                "  id bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',\n" +
                "  gmt_create datetime NOT NULL COMMENT '创建时间',\n" +
                "  gmt_modified datetime NOT NULL COMMENT '修改时间',\n" +
                "  gmt_create_origin datetime NOT NULL COMMENT '业务创建时间',\n" +
                "  gmt_modified_origin datetime NOT NULL COMMENT '业务修改时间',\n" +
                "  biz_type varchar(50) NOT NULL COMMENT '垂类类型',\n" +
                "  sub_biz_type varchar(50) NOT NULL COMMENT '垂类二级类型',\n" +
                "  url varchar(300) NOT NULL COMMENT 'web-url地址',\n" +
                "  title text COMMENT '标题',\n" +
                "  img_url text COMMENT '缩略图url',\n" +
                "  content mediumtext NOT NULL COMMENT '索引主内容',\n" +
                "  description mediumtext COMMENT 'desc 摘要',\n" +
                "  keywords mediumtext COMMENT '索引关键字',\n" +
                "  score int(11) NOT NULL DEFAULT '0' COMMENT '索引排序分',\n" +
                "  product_code varchar(200) DEFAULT NULL COMMENT 'pipcode',\n" +
                "  product_name tinytext COMMENT '产品名称',\n" +
                "  nodes_info mediumtext COMMENT '面包屑信息',\n" +
                "  tags text COMMENT '标签',\n" +
                "  uk varchar(300) NOT NULL COMMENT '唯一键biz_type+sub_biz_type+id',\n" +
                "  out_biz_id varchar(200) NOT NULL COMMENT '外部结构单号,帮助文档为节点id',\n" +
                "  enable tinyint(4) NOT NULL DEFAULT '1' COMMENT '1.enable 0.disable',\n" +
                "  version int(11) NOT NULL DEFAULT '0' COMMENT '数据版本控制',\n" +
                "  PRIMARY KEY (id),\n" +
                "  UNIQUE KEY uk_uk (uk(200)),\n" +
                "  KEY idx_biz_type_enable (biz_type,enable),\n" +
                "  KEY idx_sub_biz_type (sub_biz_type),\n" +
                "  KEY idx_out_biz_id (out_biz_id)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=305259 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='开发者社区索引内容表'";
        ObjectMapper objectMapper = new ObjectMapper();
        Table table = getTable(sql);
        System.out.println(objectMapper.writeValueAsString(table));
//        System.out.println(fillTemplate(table, Arrays.asList("$table.tableName")));
    }
}
