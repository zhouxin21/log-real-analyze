package com.flink.analyze.flatMap;

import com.flink.analyze.entity.ServerLog;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

/**
 * 分割kafka消息存入java对象
 */
public class FlatMap implements FlatMapFunction<String, ServerLog> {

    public void flatMap(String log, Collector<ServerLog> collector) throws Exception {
        if (StringUtils.isBlank(log) || log.split("\\^").length != 23) {
            log = "restserver^0^INFO^SCNT^20^200^flink_test^CD1BC28CAF762A86D75BBC072D8B4F08^00000000000000^flink-module^getFlinkTest" +
                    "^{\"value\":\"C433B65373BE738FD74371302A3E6B7B\",\"key\":\"test-18988843189\"}^flink^0^1^" +
                    "117.107.128.114^4jg5slfts65vp1m4cu_1354256^00000000000000^flink-product^JZNB010^0^15^null";
            System.out.println("[ServerLog]: serverLog is null or error!");
        } else {
            System.out.println("[ServerLog]:" + log);
        }
        collector.collect(setServerLog(log));
    }


    /**
     * @param log
     * @return
     */
    private ServerLog setServerLog(String log) {
        String[] splited = log.split("\\^");
        ServerLog logs = new ServerLog();
        if (splited != null && splited.length == 23) {
            logs.setSysName(splited[0].trim());
            logs.setAppNo(splited[1].trim());
            logs.setType(splited[2].trim());
            logs.setGrade(splited[3].trim());
            logs.setResDate(splited[4].trim());
            logs.setResNum(splited[5].trim());
            logs.setUserCode(splited[6].trim());
            logs.setApiKey(splited[7].trim());
            logs.setReqTime(splited[8].trim());
            logs.setModule(splited[9].trim());
            logs.setMethod(splited[10].trim());
            logs.setReqParam(splited[11].trim());
            logs.setProvCode(splited[12].trim());
            logs.setRateUnit(splited[13].trim());
            logs.setRateCount(splited[14].trim());
            logs.setIp(splited[15].trim());
            logs.setBusNum(splited[16].trim());
            logs.setReqTimestamp(splited[17].trim());
            logs.setProdName(splited[18].trim());
            logs.setProductCode(splited[19].trim());
            logs.setTaskId(splited[20].trim());
            logs.setExtend1(splited[21].trim());
            logs.setExtend2(splited[22].trim());
        }
        return logs;
    }
}
