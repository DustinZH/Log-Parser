package com.ef.common;

import com.ef.model.Duration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by dustin on 2018/3/11.
 */
public class ArgumentParser {

    private static final SimpleDateFormat DATA_FORMAT_CHECK= new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss");
    private static final Pattern ARGU_NAME_CHECK = Pattern.compile("^[-]{2}(\\w+)[=](.+)$");

    /**
     * Check the input startTime, the format must be:yyyy-MM-dd.HH:mm:ss
     * @param strDate input startTime
     * @return valid starTime
     */
    public static String getStartTime(String strDate){
        if(strDate == null){
            throw  new java.lang.IllegalArgumentException();
        }

        try {
            Date date =DATA_FORMAT_CHECK.parse(strDate);
            return DATA_FORMAT_CHECK.format(date);
        } catch (ParseException e) {
            throw new java.lang.IllegalArgumentException("The input date format should be: yyyy-MM-dd.HH:mm:ss");
        }
    }

    /**
     *  Get the endTime of the given period
     * @param startDate a valid startTime
     * @param duration The duration of the period, which only can be one hour or one day
     * @return a valid endTime, the format is yyyy-MM-dd.HH:mm:ss
     */
    public static String getEndTime(String startDate,Duration duration){
        if(startDate == null || duration == null){
            throw new java.lang.IllegalArgumentException();
        }
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = DATA_FORMAT_CHECK.parse(startDate);
            calendar.setTime(date);
            calendar.add(duration.getCalenderFiled(),duration.getPeriod());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return DATA_FORMAT_CHECK.format(calendar.getTime());
    }

    /**
     *  Parse arguments and change it to key-value pair
     * @param args arguments from main class
     * @return map, which store valid arguments
     */
    public static Map<String,String> parseArgument(String... args){
        if(args == null){
            throw  new java.lang.IllegalArgumentException();
        }
        Map<String,String> map = Arrays.asList(args).stream().map(ARGU_NAME_CHECK :: matcher)
                                    .filter(Matcher :: find)
                                    .collect(Collectors.toMap(matcher -> matcher.group(1),matcher -> matcher.group(2)));
        if(!map.containsKey("startDate") ||!map.containsKey("duration") || !map.containsKey("threshold")){
            throw  new java.lang.IllegalArgumentException("input format must has --startDate,--duration,--threshold, optional:--accesslog");
        }
        return map;
    }


}
