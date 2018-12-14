package com.flink.analyze.handler;

import com.flink.analyze.aggregate.ResCodeCountAgg;
import com.flink.analyze.aggregate.ResTimeSumAgg;
import com.flink.analyze.entity.ResCodeBean;
import com.flink.analyze.entity.ResTimeBean;
import com.flink.analyze.entity.ServerLog;
import com.flink.analyze.filter.Filter;
import com.flink.analyze.sink.ResCodeSink;
import com.flink.analyze.sink.ResTimeSink;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.api.java.tuple.Tuple5;
import org.apache.flink.api.java.tuple.Tuple6;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

/**
 * 实时处理逻辑
 */
public class LogicProcessing {

    /**
     * 状态码计数逻辑处理
     *
     * @param dataStream
     */
    public static void statusCodeLogicProcessing(DataStream<ServerLog> dataStream) {


        dataStream.assignTimestampsAndWatermarks(new AscendingTimestampExtractor<ServerLog>() {

            /**
             * String类型的时间转换成Long，作为watermark
             * @param serverLog
             * @return
             */
            @Override
            public long extractAscendingTimestamp(ServerLog serverLog) {

                return Long.parseLong(serverLog.getReqTimestamp());
            }
        }).filter(new Filter())
                .keyBy("userCode", "prodName", "module", "method", "provCode", "resNum")
                .timeWindow(Time.minutes(5))
                .aggregate(new ResCodeCountAgg(), new WindowFunction<Long, ResCodeBean, Tuple, TimeWindow>() {

                    /**
                     * 窗口数据计算
                     * @param tuple
                     * @param window
                     * @param iterable
                     * @param collector
                     * @throws Exception
                     */
                    public void apply(Tuple tuple, TimeWindow window, Iterable<Long> iterable, Collector<ResCodeBean> collector) throws Exception {
                        String userName = ((Tuple6<String, String, String, String, String, String>) tuple).f0;
                        String product = ((Tuple6<String, String, String, String, String, String>) tuple).f1;
                        String module = ((Tuple6<String, String, String, String, String, String>) tuple).f2;
                        String method = ((Tuple6<String, String, String, String, String, String>) tuple).f3;
                        String provId = ((Tuple6<String, String, String, String, String, String>) tuple).f4;
                        String resNum = ((Tuple6<String, String, String, String, String, String>) tuple).f5;
                        Long count = iterable.iterator().next();
                        System.out.println("[LogicProcessing-elapsedTimeLogicProcessing-apply]:" + userName + "|" + product + "|" + module + "|" + method + "|" + provId + "|" + resNum + "|" + window.getEnd() + "|" + count);
                        collector.collect(ResCodeBean.of(userName, product, module, method, provId, resNum, window.getEnd(), count));
                    }
                }).keyBy("userName", "product", "module", "method", "provId", "resNum", "windowEnd")
                .addSink(new ResCodeSink());
    }

    /**
     * 耗时计数逻辑处理
     *
     * @param dataStream
     */
    public static void elapsedTimeLogicProcessing(DataStream<ServerLog> dataStream) {

        dataStream.assignTimestampsAndWatermarks(new AscendingTimestampExtractor<ServerLog>() {
            /**
             * String类型的时间转换成Long，作为watermark
             * @param serverLog
             * @return
             */
            @Override
            public long extractAscendingTimestamp(ServerLog serverLog) {

                return Long.parseLong(serverLog.getReqTimestamp());
            }
        }).filter(new Filter())
                .keyBy("userCode", "prodName", "module", "method", "provCode")
                .timeWindow(Time.minutes(5))
                .aggregate(new ResTimeSumAgg(), new WindowFunction<Tuple3<Long, Long, Long>, ResTimeBean, Tuple, TimeWindow>() {
                    /**
                     * @param tuple
                     * @param window
                     * @param aggResult
                     * @param collector
                     * @throws Exception
                     */
                    public void apply(Tuple tuple, TimeWindow window, Iterable<Tuple3<Long, Long, Long>> aggResult, Collector<ResTimeBean> collector) throws Exception {
                        String userName = ((Tuple5<String, String, String, String, String>) tuple).f0;
                        String product = ((Tuple5<String, String, String, String, String>) tuple).f1;
                        String module = ((Tuple5<String, String, String, String, String>) tuple).f2;
                        String method = ((Tuple5<String, String, String, String, String>) tuple).f3;
                        String provId = ((Tuple5<String, String, String, String, String>) tuple).f4;

                        long num = aggResult.iterator().next().f0;
                        long sum = aggResult.iterator().next().f0;
                        long total = aggResult.iterator().next().f0;
                        System.out.println("[LogicProcessing-elapsedTimeLogicProcessing-apply]:" + userName + "|" + product + "|" + module + "|" + method + "|" + provId + "|" + num + "|" + sum + "|" + window.getEnd() + "|" + total);
                        collector.collect(ResTimeBean.of(userName, product, module, method, provId, num, sum, window.getEnd(), total));
                    }
                }).keyBy("userName", "product", "module", "method", "provId", "disposeDate")
                .addSink(new ResTimeSink());
    }
}
