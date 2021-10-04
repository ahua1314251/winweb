package org.winker.winweb.dao.mysql.entity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;


public class TableInfoDO implements Serializable {


    /**表信息**/
    private Long id;

    /**表信息**/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    /**表信息**/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;

    /**表信息**/
    private String tableName;

    /**表信息**/
    private String dbName;

    /**表信息**/
    private String dbType;

    /**表信息**/
    private String sql;

    /**表信息**/
    private String body;

    /**表信息**/
    private Integer status;

    /**表信息**/
    private Integer version;

    /**表信息**/
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