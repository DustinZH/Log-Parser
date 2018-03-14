package com.ef.db.mysql;

import com.ef.db.DBConnection;
import com.ef.db.DBConnectionFactory;
import com.ef.model.LogEntry;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by dustin on 2018/3/13.
 */
@FixMethodOrder(MethodSorters.JVM)
public class MySQLConnectionTest {
    public static DBConnection conn = null;
    @Before
    public void getInstance() throws Exception {
        conn = DBConnectionFactory.getDBConnection();
    }

    @Test
    public void save() throws Exception {
        List<LogEntry> list = new LinkedList<>();
        list.add(new LogEntry("127.0.0.1","2018:03:13 15:00:00.000"));
        conn.save(list);
    }

    @Test
    public void searchByTime() throws Exception {
        conn.searchByTime("2018:03:13.15:00:00","2018:03:13.16:00:00",1);
    }

}