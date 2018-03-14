package com.ef.service;

import com.ef.common.FileTokenParser;
import com.ef.db.DBConnection;
import com.ef.db.DBConnectionFactory;
import com.ef.model.LogEntry;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by dustin on 2018/3/10.
 */
public class LogFileSave {
    private  Scanner scanner;

    public LogFileSave(File file){
        try {
            this.scanner = new Scanner(file,"UTF-8");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Save the log file into database
     */
    public  void saveLogIntoDB(){
        List<LogEntry> list = new LinkedList<>();
        scanner.useDelimiter("\\n+");
        while (scanner.hasNext()){
            getEntry(scanner.next()).ifPresent(list :: add);
        }
        DBConnection conn = DBConnectionFactory.getDBConnection();
        conn.save(list);

    }

    /**
     * Check the format of line
     * @param line the line of log file
     * @return if the format of ip and date is valid, return it else return empty
     */
    private static Optional<LogEntry> getEntry(String line){

        String[] tokens = line.split("\\s*\\|\\s*");
        Optional<String> date = FileTokenParser.dateParse(tokens[0]);
        Optional<String> ip = FileTokenParser.ipParse(tokens[1]);
        if(date.isPresent() && ip.isPresent()){
            return Optional.of(new LogEntry(ip.get(),date.get()));
        }
        else return Optional.empty();

    }

}
