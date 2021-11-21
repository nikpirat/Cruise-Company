package dao;

import dao.exception.DaoException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class ConnectionPool {
    private static final Logger log = Logger.getLogger(ConnectionPool.class);
    private static final ConnectionPool CONNECTION_POOL = new ConnectionPool();
    private static String URL;
    private static String CLASS_NAME;
    private static String USER;
    private static String PASS;

    private void getResource() throws IOException {
        log.info("enter method");
        Properties prop = new Properties();
        String propFileName = "connection.properties";
        prop.load(getClass().getClassLoader().getResourceAsStream(propFileName));
        log.info("Took data from resource ");
        URL = prop.getProperty("url");
        CLASS_NAME = prop.getProperty("class_name");
        USER = prop.getProperty("user_name");
        PASS = prop.getProperty("password");
        log.info("exit method");
    }


    public static Connection getConnection() {
        log.info("enter method");
        try {
            CONNECTION_POOL.getResource();
        } catch (IOException e) {
            throw new DaoException("Cant take to resources", e);
        }
        try {
            Class.forName(CLASS_NAME);
            log.info("return connection");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            log.error("Cant connect to database");
            throw new DaoException("Cant connect to database", e);
        } catch (ClassNotFoundException e) {
            log.error("Driver not found");
            throw new DaoException("Driver not found", e);
        }
    }

    public static void close(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
