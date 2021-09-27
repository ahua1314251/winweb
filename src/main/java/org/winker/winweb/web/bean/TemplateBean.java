package org.winker.winweb.web.bean;

public class TemplateBean {
    String templateName;
    String content;
    String result;

    public TemplateBean() {

    }

    public TemplateBean(String templateName,
                        String content,
                        String result) {
        this.templateName = templateName;
        this.content = content;
        this.result = result;
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
}
