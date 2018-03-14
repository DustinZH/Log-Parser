package com.ef.db;

import com.ef.model.IpCount;
import com.ef.model.LogEntry;
import java.util.List;
import java.util.Set;

/**
 * Created by dustin on 2018/3/10.
 */
public interface DBConnection {
    /**
     *find IPs that mode more than a certain number of requests for a given time period
     *
     * @param startTime start time of the given period
     * @param endTime end time of the give period
     * @param threshold the number of times need should be beyond by count(ip)
     * @return
     */
    Set<IpCount> searchByTime(String startTime, String endTime, int threshold);

    /** save entries into database
     * @param logEntries which is collections of log entry
     */
    void save(List<LogEntry> logEntries);

    /**
     * Close the database connection
     */
    void close();
}
