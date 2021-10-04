package org.winker.winweb.dao.mysql.entity;
import java.io.Serializable;
import java.util.Date;


public class TemplateQuery extends BaseQuery{

    private Long id;
    private Date gmtCreate;
    private Date gmtModified;
    private String templateName;
    private String templateType;
    private String dbtype;
    private String content;
    private Integer status;
    private Integer version;
    private String fileName;

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
    public String getDbtype(){
        return this.dbtype;
    }

    public void setDbtype(String dbtype){
        this.dbtype = dbtype;
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
}