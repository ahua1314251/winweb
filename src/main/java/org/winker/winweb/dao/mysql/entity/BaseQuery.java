package org.winker.winweb.dao.mysql.entity;

public class BaseQuery {

    private int pageNo = 1;
    private int pageSize = 20;

    public int getPageNo() {
        return pageNo-1;
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
}
