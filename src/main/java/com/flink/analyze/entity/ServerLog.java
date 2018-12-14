package com.flink.analyze.entity;

/**
 * 计数日志实体类
 */
public class ServerLog {

    // 系统名称(各子模块的唯一标识)
    private String sysName;
    // 应用编号(标识相同系统名称的不同应用服务，从0开始递增，不同应用服务不能重复)
    private String appNo;
    // 日志级别(INFO)
    private String grade;
    // 日志类型(SCNT)
    private String type;
    // 响应耗时(从请求开始到返回的耗时，单位ms)
    private String resDate;
    // 响应编码(只在RestServer、批量服务、AOP中进行设置：RestServer模块和批量服务模块调用成功则输出200，针对失败的情况：RestServer中输出对应的http响应码，批量服务模块输出500；AOP中捕获到异常则输出1失败，否则输出0表示成功)
    private String resNum;
    // 用户标识(用户表中的“用户标识”字段的值)
    private String userCode;
    // APIKEY
    private String apiKey;
    // 请求时间(请求此模块的时间，格式为yyyyMMddHHmmss)
    private String reqTime;
    // 模块(功能模块)
    private String module;
    // 方法(功能中的具体接口，模块+方法唯一确定一个接口)
    private String method;
    // 请求参数(用户请求的参数，多个参数之间用","分割)
    private String reqParam;
    // 省份标识(省份的缩写，编码规则见附录。默认取值为“BJ”)
    private String provCode;
    // 计数单位(计数单位，例如“平方米”、“条”、“件”等。默认取值为“item”)
    private String rateUnit;
    // 计数量(计数量，例如“10”、"0.23"等。默认取值为“1”)
    private String rateCount;
    // 访问者IP(调用方的IP，如果是RestServer模块，则调用方为用户IP；其他模块为直接请求方的IP)
    private String ip;
    // 流水号(唯一标识一个用户的请求,多个请求日志中流水号一致则表明此日志为同一用户请求产生的日志)
    private String busNum;
    // 请求时间戳(请求时间的时间戳格式)
    private String reqTimestamp;
    // 产品名称
    private String prodName;
    // 服务项编码(服务接口对应唯一编码)
    private String productCode;
    // 该任务ID用于标识批量任务中的任务ID，其他应用可以为null
    private String taskId;
    // 用于后续计数扩展使用，暂时可以用null
    private String extend1;
    // 用于后续计数扩展使用，暂时可以用null
    private String extend2;

    /**
     *
     */
    public ServerLog() {
    }

    /**
     * @param sysName
     * @param appNo
     * @param grade
     * @param type
     * @param resDate
     * @param resNum
     * @param userCode
     * @param apiKey
     * @param reqTime
     * @param module
     * @param method
     * @param reqParam
     * @param provCode
     * @param rateUnit
     * @param rateCount
     * @param ip
     * @param busNum
     * @param reqTimestamp
     * @param prodName
     * @param productCode
     * @param taskId
     * @param extend1
     * @param extend2
     */
    public ServerLog(String sysName, String appNo, String grade, String type, String resDate, String resNum, String userCode, String apiKey, String reqTime, String module, String method, String reqParam, String provCode, String rateUnit, String rateCount, String ip, String busNum, String reqTimestamp, String prodName, String productCode, String taskId, String extend1, String extend2) {
        this.sysName = sysName;
        this.appNo = appNo;
        this.grade = grade;
        this.type = type;
        this.resDate = resDate;
        this.resNum = resNum;
        this.userCode = userCode;
        this.apiKey = apiKey;
        this.reqTime = reqTime;
        this.module = module;
        this.method = method;
        this.reqParam = reqParam;
        this.provCode = provCode;
        this.rateUnit = rateUnit;
        this.rateCount = rateCount;
        this.ip = ip;
        this.busNum = busNum;
        this.reqTimestamp = reqTimestamp;
        this.prodName = prodName;
        this.productCode = productCode;
        this.taskId = taskId;
        this.extend1 = extend1;
        this.extend2 = extend2;
    }

    @Override
    public String toString() {
        return "ServerLog{" +
                "sysName='" + sysName + '\'' +
                ", appNo='" + appNo + '\'' +
                ", grade='" + grade + '\'' +
                ", type='" + type + '\'' +
                ", resDate='" + resDate + '\'' +
                ", resNum='" + resNum + '\'' +
                ", userCode='" + userCode + '\'' +
                ", apiKey='" + apiKey + '\'' +
                ", reqTime='" + reqTime + '\'' +
                ", module='" + module + '\'' +
                ", method='" + method + '\'' +
                ", reqParam='" + reqParam + '\'' +
                ", provCode='" + provCode + '\'' +
                ", rateUnit='" + rateUnit + '\'' +
                ", rateCount='" + rateCount + '\'' +
                ", ip='" + ip + '\'' +
                ", busNum='" + busNum + '\'' +
                ", reqTimestamp='" + reqTimestamp + '\'' +
                ", prodName='" + prodName + '\'' +
                ", productCode='" + productCode + '\'' +
                ", taskId='" + taskId + '\'' +
                ", extend1='" + extend1 + '\'' +
                ", extend2='" + extend2 + '\'' +
                '}';
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getAppNo() {
        return appNo;
    }

    public void setAppNo(String appNo) {
        this.appNo = appNo;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResDate() {
        return resDate;
    }

    public void setResDate(String resDate) {
        this.resDate = resDate;
    }

    public String getResNum() {
        return resNum;
    }

    public void setResNum(String resNum) {
        this.resNum = resNum;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getReqTime() {
        return reqTime;
    }

    public void setReqTime(String reqTime) {
        this.reqTime = reqTime;
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

    public String getReqParam() {
        return reqParam;
    }

    public void setReqParam(String reqParam) {
        this.reqParam = reqParam;
    }

    public String getProvCode() {
        return provCode;
    }

    public void setProvCode(String provCode) {
        this.provCode = provCode;
    }

    public String getRateUnit() {
        return rateUnit;
    }

    public void setRateUnit(String rateUnit) {
        this.rateUnit = rateUnit;
    }

    public String getRateCount() {
        return rateCount;
    }

    public void setRateCount(String rateCount) {
        this.rateCount = rateCount;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getBusNum() {
        return busNum;
    }

    public void setBusNum(String busNum) {
        this.busNum = busNum;
    }

    public String getReqTimestamp() {
        return reqTimestamp;
    }

    public void setReqTimestamp(String reqTimestamp) {
        this.reqTimestamp = reqTimestamp;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getExtend1() {
        return extend1;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1;
    }

    public String getExtend2() {
        return extend2;
    }

    public void setExtend2(String extend2) {
        this.extend2 = extend2;
    }
}
