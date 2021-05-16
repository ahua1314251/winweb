package org.winker.winweb.result;

import java.io.Serializable;

public class ResultWrapper<T> implements Serializable {
    private T data;
    private Boolean success = false;
    private String code;
    private String message;


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ResultWrapper ofSuccess(Object data){
        ResultWrapper<Object> resultWrapper = new ResultWrapper<>();
        resultWrapper.setData(data);
        resultWrapper.setSuccess(true);
        return resultWrapper;
    }

}
