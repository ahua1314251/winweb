package org.winker.winweb.result;

public class ResultPageWrapper<T> extends ResultWrapper<T>{
    private int pageNo;
    private int pageSize;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public static ResultPageWrapper ofSuccess(Object data){
        ResultPageWrapper<Object> resultWrapper = new ResultPageWrapper<>();
        resultWrapper.setData(data);
        resultWrapper.setSuccess(true);
        return resultWrapper;
    }

}
