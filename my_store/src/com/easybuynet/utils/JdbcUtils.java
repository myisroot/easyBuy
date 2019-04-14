package com.easybuynet.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;
import javax.sql.DataSource;

import com.easybuynet.entity.Result;
import org.apache.commons.dbutils.QueryRunner;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.handlers.BeanHandler;

public class JdbcUtils {
    private static Connection conn = null;
    private static DataSource bs = null;
    private static QueryRunner qr = null;

    static {
        Properties params = new Properties();
        InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            params.load(is);
            bs = DruidDataSourceFactory.createDataSource(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        qr = new QueryRunner(bs);
    }

    public static SqlUtil getSqlUtil() {
        return new SqlUtil();
    }

    public static Connection getConnection() {
        try {
            conn = bs.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static DataSource getDataSource() {
        return bs;
    }

    public static QueryRunner getQueryRunner() {
        return qr;
    }

    public static void close(Connection connection) {
        if (null != connection) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close();
    }

    public static void close() {
        if (null != conn) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static int getPrimaryKey(String sql, Object... pasm) {
        Integer id = 0;
        try {
            PreparedStatement psm = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            if (null != pasm) {
                for (int i = 0; i < pasm.length; i++) {
                    psm.setObject(i + 1, pasm[i]);
                }
            }
            psm.executeUpdate();
            ResultSet rs = psm.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            rs.close();
            psm.close();
            close();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
