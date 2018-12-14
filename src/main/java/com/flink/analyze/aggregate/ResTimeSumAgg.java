package com.flink.analyze.aggregate;

import com.flink.analyze.entity.ServerLog;
import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.java.tuple.Tuple3;

/**
 * COUNT SUM 统计的聚合函数实现，每出现一条记录加一和耗时求和
 */
public class ResTimeSumAgg implements AggregateFunction<ServerLog, Tuple3<Long, Long, Long>, Tuple3<Long, Long, Long>> {


    /**
     * @return
     */
    public Tuple3<Long, Long, Long> createAccumulator() {
        return new Tuple3<Long, Long, Long>(0L, 0L, 0L);
    }

    /**
     * @param serverLog
     * @param acc
     * @return
     */
    public Tuple3<Long, Long, Long> add(ServerLog serverLog, Tuple3<Long, Long, Long> acc) {

        long num = acc.f0;
        long sum = acc.f1;
        long total = acc.f2;

        if (Integer.parseInt(serverLog.getResDate()) > 300) {
            //用户调用接口超速个数
            num++;
        }
        //用户调用接口个数
        sum++;
        //用户调用接口总耗时
        total += Long.parseLong(serverLog.getResDate());
        System.out.println("[ResTimeSumAgg-add]:超时次数[" + num + "]/调用次数[" + sum + "]/接口耗时[" + serverLog.getResDate() + "]/总耗时[" + total + "]");
        return new Tuple3<Long, Long, Long>(num, sum, total);
    }

    /**
     * @param acc
     * @return
     */
    public Tuple3<Long, Long, Long> getResult(Tuple3<Long, Long, Long> acc) {

        return acc;
    }

    /**
     * @param acc1
     * @param acc2
     * @return
     */
    public Tuple3<Long, Long, Long> merge(Tuple3<Long, Long, Long> acc1, Tuple3<Long, Long, Long> acc2) {

        return new Tuple3<Long, Long, Long>(acc1.f0 + acc2.f0, acc1.f1 + acc2.f1, acc1.f2 + acc2.f2);
    }
}
