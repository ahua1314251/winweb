package org.winker.winweb.dao.mysql.entity;

import java.util.Date;


public class SpiderQuery extends BaseQuery{

   private Long id;
   private Date gmtCreate;
   private Date gmtModified;
   private Date gmtCreateOrigin;
   private Date gmtModifiedOrigin;
   private String bizType;
   private String subBizType;
   private String url;
   private String title;
   private String imgUrl;
   private String content;
   private String description;
   private String keywords;
   private Integer score;
   private String productCode;
   private String productName;
   private String nodesInfo;
   private String tags;
   private String uk;
   private String outBizId;
   private Integer level;
   private Integer status;
   private Integer version;
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
