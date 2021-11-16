package org.winker.winweb.dao.mysql.entity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;


public class SpiderDO implements Serializable {


    /**主键**/
    private Long id;

    /**创建时间**/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date gmtCreate;

    /**修改时间**/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date gmtModified;

    /**业务创建时间**/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date gmtCreateOrigin;

    /**业务修改时间**/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date gmtModifiedOrigin;

    /**垂类类型 其他**/
    private String bizType;

    /**垂类二级类型 网站**/
    private String subBizType;

    /**web-url地址**/
    private String url;

    /**标题**/
    private String title;

    /**缩略图url**/
    private String imgUrl;

    /**索引主内容**/
    private String content;

    /**desc 摘要**/
    private String description;

    /**索引关键字**/
    private String keywords;

    /**索引排序分**/
    private Integer score;

    /**pipcodes 多个英文逗号分隔**/
    private String productCode;

    /**产品名称，多个英文逗号分隔**/
    private String productName;

    /**面包屑信息**/
    private String nodesInfo;

    /**标签英文逗号分隔**/
    private String tags;

    /**唯一键biz_type@@sub_biz_type@@id**/
    private String uk;

    /**爬虫使用url作为唯一主键**/
    private String outBizId;

    /**爬虫层级**/
    private Integer level;

    /**1.正常 2.异常**/
    private Integer status;

    /**数据版本控制**/
    private Integer version;

    /**链接来源a标签文本,可能会作为title**/
    private String tagText;

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
    public Date getGmtCreateOrigin(){
        return this.gmtCreateOrigin;
    }

    public void setGmtCreateOrigin(Date gmtCreateOrigin){
        this.gmtCreateOrigin = gmtCreateOrigin;
    }
    public Date getGmtModifiedOrigin(){
        return this.gmtModifiedOrigin;
    }

    public void setGmtModifiedOrigin(Date gmtModifiedOrigin){
        this.gmtModifiedOrigin = gmtModifiedOrigin;
    }
    public String getBizType(){
        return this.bizType;
    }

    public void setBizType(String bizType){
        this.bizType = bizType;
    }
    public String getSubBizType(){
        return this.subBizType;
    }

    public void setSubBizType(String subBizType){
        this.subBizType = subBizType;
    }
    public String getUrl(){
        return this.url;
    }

    public void setUrl(String url){
        this.url = url;
    }
    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public String getImgUrl(){
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }
    public String getContent(){
        return this.content;
    }

    public void setContent(String content){
        this.content = content;
    }
    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }
    public String getKeywords(){
        return this.keywords;
    }

    public void setKeywords(String keywords){
        this.keywords = keywords;
    }
    public Integer getScore(){
        return this.score;
    }

    public void setScore(Integer score){
        this.score = score;
    }
    public String getProductCode(){
        return this.productCode;
    }

    public void setProductCode(String productCode){
        this.productCode = productCode;
    }
    public String getProductName(){
        return this.productName;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }
    public String getNodesInfo(){
        return this.nodesInfo;
    }

    public void setNodesInfo(String nodesInfo){
        this.nodesInfo = nodesInfo;
    }
    public String getTags(){
        return this.tags;
    }

    public void setTags(String tags){
        this.tags = tags;
    }
    public String getUk(){
        return this.uk;
    }

    public void setUk(String uk){
        this.uk = uk;
    }
    public String getOutBizId(){
        return this.outBizId;
    }

    public void setOutBizId(String outBizId){
        this.outBizId = outBizId;
    }
    public Integer getLevel(){
        return this.level;
    }

    public void setLevel(Integer level){
        this.level = level;
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
    public String getTagText(){
        return this.tagText;
    }

    public void setTagText(String tagText){
        this.tagText = tagText;
    }
}
