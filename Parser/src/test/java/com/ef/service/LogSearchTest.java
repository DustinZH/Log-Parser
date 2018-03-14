package com.ef.service;

import com.ef.db.DBConnection;
import com.ef.db.DBConnectionFactory;
import com.ef.model.Duration;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dustin on 2018/3/13.
 */
public class LogSearchTest {
    public static DBConnection conn;
    @Before
    public void getDBConnection(){
        conn = DBConnectionFactory.getDBConnection();
    }

    @Test
    public void printLogRequest() throws Exception {
        LogSearch searcher = new LogSearch();
        searcher.printLogRequest("2017-01-01.15:00:00", Duration.HOURLY,200);
        System.out.println("===============================================");
        searcher.printLogRequest("2017-01-01.00:00:00",Duration.DAILY,500);
    }

}