package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnClass {
    public Connection connection;
    public Connection getConnection(){

        final String hostname = "hw2-db.cgvtjh1lky56.us-east-1.rds.amazonaws.com";
        final String dbName = "hw2_db";
        final String port = "3306";
        final String userName = "dtscott2";
        final String password = "Emily.123";
        final String AWS_URL = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(AWS_URL);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;

    }


}
