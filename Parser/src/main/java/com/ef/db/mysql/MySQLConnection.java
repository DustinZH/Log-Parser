package com.ef.db.mysql;

import com.ef.db.DBConnection;
import com.ef.model.IpCount;
import com.ef.model.LogEntry;

import java.sql.*;
import java.util.*;

/**
 * Created by dustin on 2018/3/10.
 * Singleton mode
 */
public class MySQLConnection implements DBConnection {
    private static MySQLConnection instance;

    public static MySQLConnection getInstance(){
        if (instance == null){
            instance = new MySQLConnection();
        }
        return instance;
    }

    private Connection conn = null;
    private MySQLConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(MySQLDBUtil.URL);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public Set<IpCount> searchByTime(String startTime, String endTime, int threshold) {
        Set<IpCount> result = new HashSet<>();
        try {
            String query = "SELECT ip,COUNT(IP) as cnt FROM wallethub.loginfo WHERE date >= ? and date <= ? GROUP BY ip HAVING COUNT(IP) >= ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1,startTime);
            stmt.setString(2,endTime);
            stmt.setInt(3,threshold);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()){
                result.add(new IpCount(resultSet.getString("ip"),resultSet.getInt("cnt")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void save(List<LogEntry> logEntries) {
        if(logEntries == null){
            throw new java.lang.IllegalArgumentException("null argument");
        }
        if(logEntries.size() == 0){
            System.out.println("Empty logEntries");
            return;
        }
        String query = "INSERT INTO loginfo(ip,date) VALUES(?,?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            int count = 0;
            Iterator<LogEntry> iter = logEntries.iterator();
            while ( iter.hasNext()){
                LogEntry entry = iter.next();
                stmt.setString(1,entry.getIp());
                stmt.setString(2,entry.getTime());
                stmt.addBatch();
                count++;
                if(count % 10000 == 0){ // batch size is 1000
                    stmt.executeBatch();
                }
            }
            stmt.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void close() {

    }
}
