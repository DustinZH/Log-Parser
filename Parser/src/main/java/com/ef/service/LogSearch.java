package com.ef.service;

import com.ef.common.ArgumentParser;
import com.ef.model.Duration;
import com.ef.db.DBConnection;
import com.ef.db.DBConnectionFactory;
import com.ef.model.IpCount;

import java.util.Collection;

/**
 * Created by dustin on 2018/3/10.
 */
public class LogSearch {
        /**
         * Print the IPs from MySQL that mode more than a certain number of requests for a given time period
         * @param startDate The start time of  the given period
         * @param duration The duration of the period, which only can be one hour or one day
         * @param threshold The minimum number of IP requests
         */
        public  void printLogRequest(String startDate, Duration duration,Integer threshold){
        DBConnection dbConn = DBConnectionFactory.getDBConnection();
        String endTime = ArgumentParser.getEndTime(startDate,duration);
        System.out.println(String.format("Begin to check IP LOG REQUEST: startTime: %s, endTime: %s, threshold: %d",
                                     startDate,endTime,threshold));

        Collection<IpCount> logResult = dbConn.searchByTime(startDate,endTime,threshold);

        System.out.println("-------------------------------------");


        logResult.stream().map(ipCount -> String.format("|ip = %s , count = %d ",ipCount.getIp(),ipCount.getCount()))
                      .forEach(System.out :: println);


        System.out.println("-------------------------------------");
    }
}
