package com.flink.analyze.sink;

import com.flink.analyze.entity.ResCodeBean;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

/**
 * mysql sink
 */
public class ResCodeSink extends RichSinkFunction<ResCodeBean> implements SinkFunction<ResCodeBean> {

    private static final long serialVersionUID = 1L;
    private Connection connection;
    private PreparedStatement preparedStatement;

    /**
     * @param parameters
     * @throws Exception
     */
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
        String sql = "insert into flink_rescode_analyze(userCode,product,module,method,provCode,disposeDate,resNum,num) values (?,?,?,?,?,?,?,?)";
        System.out.println("[ResCodeSink-open]:" + sql);
        preparedStatement = connection.prepareStatement(sql);
        super.open(parameters);
    }

    /**
     * @throws Exception
     */
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

    /**
     * @param value
     * @param context
     * @throws Exception
     */
    public void invoke(ResCodeBean value, Context context) throws Exception {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            String disposeDate = format.format(value.getWindowEnd());
            preparedStatement.setString(1, value.getUserName());
            preparedStatement.setString(2, value.getProduct());
            preparedStatement.setString(3, value.getModule());
            preparedStatement.setString(4, value.getMethod());
            preparedStatement.setString(5, value.getProvId());
            preparedStatement.setString(6, disposeDate);
            preparedStatement.setString(7, value.getResNum());
            preparedStatement.setLong(8, value.getViewCount());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
