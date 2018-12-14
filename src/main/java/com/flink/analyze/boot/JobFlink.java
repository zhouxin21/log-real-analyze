package com.flink.analyze.boot;

import com.flink.analyze.entity.ServerLog;
import com.flink.analyze.flatMap.FlatMap;
import com.flink.analyze.handler.LogicProcessing;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.runtime.state.filesystem.FsStateBackend;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer08;

import java.util.Properties;

/**
 * 任务入口
 */
public class JobFlink {

    public static void main(String[] args) throws Exception {

        ParameterTool parameter;
        try {
            parameter = ParameterTool.fromPropertiesFile(args[0]);
            System.out.println("++++++++++++++++++++++++++ LOAD CONF ++++++++++++++++++++++++++");
            System.out.println(parameter.toMap());
            System.out.println("++++++++++++++++++++++++++    END    ++++++++++++++++++++++++++");
        } catch (Exception e) {
            throw new Exception("Properties file does not exist!", e);
        }


        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.getConfig().enableSysoutLogging();
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        env.setParallelism(parameter.getInt("parallelism"));
        // 每1000毫秒启动一个检查点
        env.enableCheckpointing(parameter.getInt("checkpointing"));
        // 检查点必须在一分钟内完成，否则将被丢弃
        env.getCheckpointConfig().setCheckpointTimeout(parameter.getInt("checkpointTimeout"));
        // 设置模式为精确一次(这是默认设置)
        env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
        // 确保在检查点之间有500毫秒的进度
        env.getCheckpointConfig().setMinPauseBetweenCheckpoints(parameter.getInt("MinPauseBetweenCheckpoints"));
        // 只允许一个检查点同时进行
        env.getCheckpointConfig().setMaxConcurrentCheckpoints(parameter.getInt("MaxConcurrentCheckpoints"));
        // 启用作业取消后保留的外部化检查点
        env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
        // 重启策略，固定延迟重启策略重启3次每次重启间隔1秒
        env.getConfig().setRestartStrategy(RestartStrategies.fixedDelayRestart(parameter.getInt("RestartStrategies"), parameter.getInt("fixedDelayRestart")));
        //
        env.setStateBackend(new FsStateBackend(parameter.get("FsStateBackend"), true));


        //配置kafka参数
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", parameter.get("bootstrap.servers"));
        props.setProperty("zookeeper.connect", parameter.get("zookeeper.connect"));
        props.setProperty("group.id", parameter.get("group.id"));
        FlinkKafkaConsumer08<String> consumer08 = new FlinkKafkaConsumer08<String>(parameter.get("topic"), new SimpleStringSchema(), props);
        DataStreamSource<String> dataStreamSource = env.addSource(consumer08);
        DataStream<ServerLog> dataStream = dataStreamSource.flatMap(new FlatMap());

        LogicProcessing.statusCodeLogicProcessing(dataStream);
        LogicProcessing.elapsedTimeLogicProcessing(dataStream);

        try {
            env.execute("This is Flink Job!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
