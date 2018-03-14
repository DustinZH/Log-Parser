package com.ef.db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by dustin on 2018/3/10.
 */
public class MySQLTableCreation {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = null;
            try {
                System.out.println("Connecting to \n" + MySQLDBUtil.URL);
                conn = DriverManager.getConnection(MySQLDBUtil.URL);
            }
            catch (SQLException e){
                System.out.println("SQLException " + e.getMessage());
                System.out.println("SQLState " + e.getSQLState());
                System.out.println("VendorError " + e.getErrorCode());
            }
            if( conn == null) return;
            Statement stmt = conn.createStatement();
            String sql = "DROP TABLE IF EXISTS wallethub.loginfo";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT  EXISTS wallethub.loginfo"+
                  "(id INT NOT NULL  AUTO_INCREMENT, ip VARCHAR(20) NOT NULL, " +
                    "date DATETIME(3) NOT NULL," +
                    "PRIMARY KEY(`id`)," +
                    "CONSTRAINT  UNIQUE (`ip`,`date`))";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO wallethub.loginfo (ip, date) VALUES ('127.0.0.1', '2018-03-11 15:30:52.000')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO wallethub.loginfo (ip, date) VALUES ('127.0.0.1', '2018-03-11 15:40:52.000')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO wallethub.loginfo (ip, date) VALUES ('192.168.1.1', '2018-03-11 17:30:52.000')";
            stmt.executeUpdate(sql);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
