package com.ef.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Created by dustin on 2018/3/12.
 */
public class FileTokenParser {
    private static final String IPv4_FORMATE_CHEK = "^((\\d{1,2})|([1]\\d{2})|([2][0-5]{2}))\\." +
                                                    "((\\d{1,2})|([1]\\d{2})|([2][0-5]{2}))\\." +
                                                    "((\\d{1,2})|([1]\\d{2})|([2][0-5]{2}))\\." +
                                                    "((\\d{1,2})|([1]\\d{2})|([2][0-5]{2}))$";
    // The date format of log file must accurate to milliseconds
    private static final SimpleDateFormat DATA_FORMAT_CHECK = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");



    public static Optional<String> ipParse(String ipStr){
        if(Pattern.matches(IPv4_FORMATE_CHEK,ipStr)){
            return  Optional.of(ipStr);
        }
        else
            return Optional.empty();
    }

    public static Optional<String> dateParse(String dateStr){
        if(dateStr == null) return Optional.empty();
        try {
            Date date = DATA_FORMAT_CHECK.parse(dateStr);
            return Optional.of(DATA_FORMAT_CHECK.format(date));
        } catch (ParseException e) {
            return Optional.empty();
        }
    }

}
