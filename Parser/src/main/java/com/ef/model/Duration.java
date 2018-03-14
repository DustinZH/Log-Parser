package com.ef.model;

import java.util.Calendar;

/**
 * Created by dustin on 2018/3/11.
 */
public enum  Duration {
    HOURLY("HOURLY",1,Calendar.HOUR),DAILY("DAILY",1,Calendar.DATE);
    private final String type;
    private final int period;
    private final int calenderFiled;
    Duration(String timeType,int period,int calenderFiled){
        this.type = timeType;
        this.period = period;
        this.calenderFiled = calenderFiled;
    }

    public static Duration getFromStr(String paramName){
        if(paramName == null || (!paramName.toUpperCase().equals("HOURLY") && !paramName.toUpperCase().equals("DAILY"))){
            throw  new IllegalArgumentException("The value of duration must be: HOURLY or DAILY");
        }
        return Duration.valueOf(paramName.toUpperCase());
    }

    public int getPeriod() {
        return period;
    }

    public int getCalenderFiled() {
        return calenderFiled;
    }
}
