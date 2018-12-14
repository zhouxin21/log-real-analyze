package com.flink.analyze.aggregate;

import com.flink.analyze.entity.ServerLog;
import org.apache.flink.api.common.functions.AggregateFunction;

/**
 * COUNT 统计的聚合函数实现，每出现一条记录加一
 */
public class ResCodeCountAgg implements AggregateFunction<ServerLog, Long, Long> {
    public Long createAccumulator() {
        return 0L;
    }

    public Long add(ServerLog serverLog, Long acc) {
        return acc + 1;
    }

    public Long getResult(Long acc) {
        return acc;
    }

    public Long merge(Long acc1, Long acc2) {
        return acc1 + acc2;
    }
}
