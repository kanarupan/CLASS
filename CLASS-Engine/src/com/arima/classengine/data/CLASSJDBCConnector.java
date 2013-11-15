package com.arima.classengine.data;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Created with IntelliJ IDEA.
 * User: jayrksih
 * Date: 11/15/13
 * Time: 1:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class CLASSJDBCConnector {

    /**
     * Make CLASSJDBCConnector Singleton, so only one connection for class database
     */
    private static CLASSJDBCConnector instance = null;
    private static Connection jdbConnection;


    protected CLASSJDBCConnector() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        connect(Constants.CLASS_DB_NAME,Constants.CLASS_DB_USERNAME,Constants.CLASS_DB_PASSWORD);

    }

    private static Connection connect(String dbURL, String username,String password) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        jdbConnection=  DriverManager.getConnection(dbURL, username, password);
        return jdbConnection;
    }


    /**
     * jdbc connection is a singleton object. If no connection object create one and connect.
     * If a previous connection object is there but closed, then just opens it again.
     * @return    com.mysql.jdbc.Connection object with connected to Constants.CLASS_DB_NAME
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static Connection openConnection() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        //If no connection object create one and connect.
        if(instance == null) {
            instance = new CLASSJDBCConnector();
        }
        // A previous connection object is there but closed, so just open again.
        else if(jdbConnection.isClosed()) {
            connect(Constants.CLASS_DB_NAME,Constants.CLASS_DB_USERNAME,Constants.CLASS_DB_PASSWORD);
        }
        return jdbConnection;
    }


    /**
     * Close the class jdbc connection, but keep the connection instance.
     * @return
     */
    public static boolean closeConnection(){
        try {
            jdbConnection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }

    }
}
