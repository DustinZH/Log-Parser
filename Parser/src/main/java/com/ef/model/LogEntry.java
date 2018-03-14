package com.ef.model;

import java.util.Date;

/**
 * Created by dustin on 2018/3/10.
 */
public class LogEntry {
    private String ip;
    private String time;

    public LogEntry(String ip,String time){
        this.ip = ip;
        this.time = time;
    }


    public String getIp() {
        return ip;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "LogEntry{" +
                ", ip='" + ip + '\'' +
                ", time=" + time +
                '}';
    }
}
