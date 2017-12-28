package com.blog.init;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.stream.Collectors;

/**
 * SQLite 数据库操作
 * <p>
 * Created by biezhi on 2017/3/4.
 */
@Slf4j
@NoArgsConstructor
public final class SqliteJdbc {

    public static final String DB_NAME = "blog";
    public static String DB_PATH;
    public static String DB_SRC;
    public static String LOCALE_PORT="127.0.0.1:3306/";
    public static String USER = "root";
    public static String PASSWORD = "123456";
    public static String DB_CONFIG = "useSSL=true&useUnicode=true&characterEncoding=UTF8";
    public static String TEST_URL;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            log.error("load mysql driver error", e);
        }
    }

    /**
     * 测试连接并导入数据库
     */
    public static void importSql(boolean devMode) {
        try {

            DB_PATH = LOCALE_PORT + DB_NAME + "?"+ DB_CONFIG;
            DB_SRC = "jdbc:mysql://" + DB_PATH;

            if (devMode) {
                DB_PATH = LOCALE_PORT + DB_NAME +"?"+ DB_CONFIG;
                DB_SRC = "jdbc:mysql://" + DB_PATH;
            }

            log.info("blade dev mode: {}", devMode);
            log.info("load mysql database path [{}]", DB_PATH);
            log.info("load mysql database src [{}]", DB_SRC);
            TEST_URL = DB_SRC+"&"+"user="+USER+"&"+"password="+PASSWORD;
            Connection con       = DriverManager.getConnection(TEST_URL);
            Statement  statement = con.createStatement();
            ResultSet  rs        = statement.executeQuery("select count(*) from information_schema.tables where table_schema='"+DB_NAME+"'");
            int count = 0;
            while (rs!=null && rs.next()){
                count = rs.getInt(1);
            }
            if (count == 0) {
                String            cp  = SqliteJdbc.class.getClassLoader().getResource("").getPath();
                InputStreamReader isr = new InputStreamReader(new FileInputStream(cp + "schema.sql"), "UTF-8");

                String sql = new BufferedReader(isr).lines().collect(Collectors.joining("\n"));
                int    r   = statement.executeUpdate(sql);
                log.info("initialize import database - {}", r);
            }
            rs.close();
            statement.close();
            con.close();
            log.info("database path is: {}", DB_PATH);
        } catch (Exception e) {
            log.error("initialize database fail", e);
        }
    }

}