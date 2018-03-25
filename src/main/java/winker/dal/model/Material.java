package winker.dal.model;

import java.util.Date;

public class Material {
    private Integer id;

    private String materialname;

    private Integer materialquantity;

    private String applicablemodels;

    private String materialprice;

    private String customer;

    private String leader;

    private String remark;

    private Date entrytime;

    private Date outtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaterialname() {
        return materialname;
    }

    public void setMaterialname(String materialname) {
        this.materialname = materialname == null ? null : materialname.trim();
    }

    public Integer getMaterialquantity() {
        return materialquantity;
    }

    public void setMaterialquantity(Integer materialquantity) {
        this.materialquantity = materialquantity;
    }

    public String getApplicablemodels() {
        return applicablemodels;
    }

    public void setApplicablemodels(String applicablemodels) {
        this.applicablemodels = applicablemodels == null ? null : applicablemodels.trim();
    }

    public String getMaterialprice() {
        return materialprice;
    }

    public void setMaterialprice(String materialprice) {
        this.materialprice = materialprice == null ? null : materialprice.trim();
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer == null ? null : customer.trim();
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader == null ? null : leader.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getEntrytime() {
        return entrytime;
    }

    public void setEntrytime(Date entrytime) {
        this.entrytime = entrytime;
    }

    public Date getOuttime() {
        return outtime;
    }

    public void setOuttime(Date outtime) {
        this.outtime = outtime;
    }
}