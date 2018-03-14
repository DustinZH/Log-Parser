package com.ef;

import com.ef.common.ArgumentParser;
import com.ef.model.Duration;
import com.ef.service.LogFileSave;
import com.ef.service.LogSearch;

import java.io.File;
import java.util.Map;

/**
 * Created by dustin on 2018/3/10.
 */
public class Parser {
    public static void main(String[] args) {
        Map<String,String> argsMap = ArgumentParser.parseArgument(args);
        //if input arguments include accesslog, save it into db
        if(argsMap.containsKey("accesslog")){
            File file = new File(argsMap.get("accesslog"));
            LogFileSave saver = new LogFileSave(file);
            saver.saveLogIntoDB();
        }
        // check whether arguments are valid
        String startDate = ArgumentParser.getStartTime(argsMap.get("startDate"));
        Duration duration = Duration.getFromStr(argsMap.get("duration"));
        Integer threshold = Integer.valueOf(argsMap.get("threshold"));

        LogSearch searcher = new LogSearch();
        searcher.printLogRequest(startDate,duration,threshold);

    }
}
