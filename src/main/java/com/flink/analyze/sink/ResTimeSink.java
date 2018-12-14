package com.flink.analyze.sink;

import com.flink.analyze.entity.ResTimeBean;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

/**
 *
 */
public class ResTimeSink extends RichSinkFunction<ResTimeBean> {

    private static final long serialVersionUID = 1L;
    private Connection connection;
    private PreparedStatement preparedStatement;

    @Override
    public void open(Configuration parameters) throws Exception {
        // JDBC连接信息
        String USERNAME = "monitor";
        String PASSWORD = "monitor";
        String DRIVERNAME = "com.mysql.jdbc.Driver";
        String DBURL = "jdbc:mysql://10.161.0.91:3306/flink_test";
        // 加载JDBC驱动
        Class.forName(DRIVERNAME);
        // 获取数据库连接
        connection = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        String sql = "insert into flink_restime_analyze(userName,product,module,method,provCode,disposeDate,speedNum,invokeSum,totaltime) values (?,?,?,?,?,?,?,?,?)";
        System.out.println("[ResTimeSink-open]:" + sql);
        preparedStatement = connection.prepareStatement(sql);
        super.open(parameters);
    }

    @Override
    public void close() throws Exception {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
        super.close();
    }

    public void invoke(ResTimeBean value, Context context) throws Exception {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            String disposeDate = format.format(value.getDisposeDate());
            preparedStatement.setString(1, value.getUserName());
            preparedStatement.setString(2, value.getProduct());
            preparedStatement.setString(3, value.getModule());
            preparedStatement.setString(4, value.getMethod());
            preparedStatement.setString(5, value.getProvId());
            preparedStatement.setString(6, disposeDate);
            preparedStatement.setLong(7, value.getSpeedNum());
            preparedStatement.setLong(8, value.getInvokeSum());
            preparedStatement.setLong(9, value.getTotalTimes());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
