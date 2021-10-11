package org.winker.winweb.dao.mysql.entity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;


public class TemplateDO implements Serializable {


    /**主键**/
    private Long id;

    /**创建时间**/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date gmtCreate;

    /**修改时间**/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date gmtModified;

    /**模板名称**/
    private String templateName;

    /**模板类型 自定义，系统**/
    private String templateType;

    /**数据库类型**/
    private String dbType;

    /**模板内容**/
    private String content;

    /**状态1,0**/
    private Integer status;

    /**数据版本控制**/
    private Integer version;

    /**生成文件名称**/
    private String fileName;

    /**生成文件目录**/
    private String filePath;

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
    public String getTemplateName(){
        return this.templateName;
    }

    public void setTemplateName(String templateName){
        this.templateName = templateName;
    }
    public String getTemplateType(){
        return this.templateType;
    }

    public void setTemplateType(String templateType){
        this.templateType = templateType;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getContent(){
        return this.content;
    }

    public void setContent(String content){
        this.content = content;
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
    public String getFileName(){
        return this.fileName;
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}