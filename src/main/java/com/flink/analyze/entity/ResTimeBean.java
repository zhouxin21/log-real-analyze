package com.flink.analyze.entity;

/**
 * 窗口操作的数据类型
 */
public class ResTimeBean {

    public String userName;
    public String product;
    public String module;
    public String method;
    public String provId;
    public long speedNum;
    public long invokeSum;
    public long disposeDate;
    public long totalTimes;

    public ResTimeBean() {
    }

    /**
     * @param userName
     * @param product
     * @param module
     * @param method
     * @param provId
     * @param speedNum
     * @param invokeSum
     * @param disposeDate
     * @param totalTimes
     * @return
     */
    public static ResTimeBean of(String userName, String product, String module, String method, String provId, long speedNum, long invokeSum, long disposeDate, long totalTimes) {

        ResTimeBean resTimeBean = new ResTimeBean();

        resTimeBean.userName = userName;
        resTimeBean.product = product;
        resTimeBean.module = module;
        resTimeBean.method = method;
        resTimeBean.provId = provId;
        resTimeBean.speedNum = speedNum;
        resTimeBean.invokeSum = invokeSum;
        resTimeBean.disposeDate = disposeDate;
        resTimeBean.totalTimes = totalTimes;

        return resTimeBean;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getProvId() {
        return provId;
    }

    public void setProvId(String provId) {
        this.provId = provId;
    }

    public long getSpeedNum() {
        return speedNum;
    }

    public void setSpeedNum(long speedNum) {
        this.speedNum = speedNum;
    }

    public long getInvokeSum() {
        return invokeSum;
    }

    public void setInvokeSum(long invokeSum) {
        this.invokeSum = invokeSum;
    }

    public long getDisposeDate() {
        return disposeDate;
    }

    public void setDisposeDate(long disposeDate) {
        this.disposeDate = disposeDate;
    }

    public long getTotalTimes() {
        return totalTimes;
    }

    public void setTotalTimes(long totalTimes) {
        this.totalTimes = totalTimes;
    }
}
