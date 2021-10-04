package org.winker.winweb.web.bean;

import org.apache.commons.lang.StringUtils;

public class TemplateEntity {
    String templateName;
    String content;
    String result;
    String fileName;

    public TemplateEntity() {

    }

    public TemplateEntity(String templateName, String content, String result, String fileName) {
        this.templateName = templateName;
        this.content = content;
        this.result = result;
        this.fileName = StringUtils.defaultIfEmpty(fileName,templateName);
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
