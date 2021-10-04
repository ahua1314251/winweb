package org.winker.winweb.dao.mysql.entity;

import java.util.Date;


public class TableInfoQuery extends BaseQuery{

    private Long id;
    private Date gmtCreate;
    private Date gmtModified;
    private String tableName;
    private String dbName;
    private String dbType;
    private String sql;
    private String body;
    private Integer status;
    private Integer version;
    private String basePath;

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }
    public Date getGmtCreate(){
        return this.gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate){
        this.gmtCreate = gmtCreate;
    }
    public Date getGmtModified(){
        return this.gmtModified;
    }

    public void setGmtModified(Date gmtModified){
        this.gmtModified = gmtModified;
    }
    public String getTableName(){
        return this.tableName;
    }

    public void setTableName(String tableName){
        this.tableName = tableName;
    }
    public String getDbName(){
        return this.dbName;
    }

    public void setDbName(String dbName){
        this.dbName = dbName;
    }
    public String getDbType(){
        return this.dbType;
    }

    public void setDbType(String dbType){
        this.dbType = dbType;
    }
    public String getSql(){
        return this.sql;
    }

    public void setSql(String sql){
        this.sql = sql;
    }
    public String getBody(){
        return this.body;
    }

    public void setBody(String body){
        this.body = body;
    }
    public Integer getStatus(){
        return this.status;
    }

    public void setStatus(Integer status){
        this.status = status;
    }
    public Integer getVersion(){
        return this.version;
    }

    public void setVersion(Integer version){
        this.version = version;
    }
    public String getBasePath(){
        return this.basePath;
    }

    public void setBasePath(String basePath){
        this.basePath = basePath;
    }
}