package com.flink.analyze.filter;

import com.flink.analyze.entity.ServerLog;
import org.apache.flink.api.common.functions.FilterFunction;

public class Filter implements FilterFunction<ServerLog> {

    /**
     * 过滤getToken方法
     *
     * @param serverLog
     * @return
     * @throws Exception
     */
    public boolean filter(ServerLog serverLog) throws Exception {
        if (!serverLog.getMethod().equals("getToken")) {
            return true;
        } else {
            return false;
        }
    }
}
