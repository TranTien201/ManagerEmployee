package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    private String url;
    private String user;
    private Connection connect;

    public DBConnect() throws Exception {
        String url = "jdbc:mysql://localhost:3306/employee";
        String user = "root";
        String password = "";
        connect = DriverManager.getConnection(url,user,password);
        System.out.println("connect successful.....");

    }
    public Connection getConnect() {
        return connect;
    }

}
