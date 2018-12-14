package com.flink.analyze.entity;

/**
 * 窗口操作的输出类型
 */
public class ResCodeBean {

    public String userName;
    public String product;
    public String module;
    public String method;
    public String provId;
    public String resNum;
    public long windowEnd;
    public long viewCount;

    public static ResCodeBean of(String userName, String product, String module, String method, String provId, String resNum, long windowEnd, long viewCount) {

        ResCodeBean result = new ResCodeBean();

        result.userName = userName;
        result.product = product;
        result.module = module;
        result.method = method;
        result.provId = provId;
        result.resNum = resNum;
        result.windowEnd = windowEnd;
        result.viewCount = viewCount;

        return result;
    }

    @Override
    public String toString() {
        return "ResCodeBean{" +
                "userName='" + userName + '\'' +
                ", product='" + product + '\'' +
                ", module='" + module + '\'' +
                ", method='" + method + '\'' +
                ", provId='" + provId + '\'' +
                ", resNum='" + resNum + '\'' +
                ", windowEnd=" + windowEnd +
                ", viewCount=" + viewCount +
                '}';
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

    public String getResNum() {
        return resNum;
    }

    public void setResNum(String resNum) {
        this.resNum = resNum;
    }

    public long getWindowEnd() {
        return windowEnd;
    }

    public void setWindowEnd(long windowEnd) {
        this.windowEnd = windowEnd;
    }

    public long getViewCount() {
        return viewCount;
    }

    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }
}
