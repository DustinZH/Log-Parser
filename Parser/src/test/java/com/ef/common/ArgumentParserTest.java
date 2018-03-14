package com.ef.common;

import com.ef.model.Duration;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dustin on 2018/3/13.
 */
public class ArgumentParserTest {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void getStartTime_validFormat() throws Exception {
        Assert.assertEquals("Wrong data format","2017-01-01.00:00:00",ArgumentParser.getStartTime("2017-01-01.00:00:00"));
        Assert.assertEquals("Wrong data format","2018-03-11.00:50:00",ArgumentParser.getStartTime("2018-03-11.00:50:00"));
        Assert.assertEquals("Wrong data format","2018-03-11.12:00:00",ArgumentParser.getStartTime("2018-03-11.12:00:00"));
        Assert.assertEquals("Wrong data format","2018-01-11.00:00:00",ArgumentParser.getStartTime("2018-01-11.00:00:00"));
        Assert.assertEquals("Wrong data format","2017-03-13.00:00:01",ArgumentParser.getStartTime("2017-03-13.00:00:01"));
        Assert.assertEquals("Wrong data format","2018-03-11.00:40:00",ArgumentParser.getStartTime("2018-03-11.00:40:00"));
        Assert.assertEquals("Wrong data format","2018-03-11.00:59:00",ArgumentParser.getStartTime("2018-03-11.00:59:00"));
        Assert.assertEquals("Wrong data format","2018-07-11.00:12:00",ArgumentParser.getStartTime("2018-07-11.00:12:00"));
    }

    @Test
    public void getStartTime_invalidFormat() throws IllegalArgumentException {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("The input date format should be: yyyy-MM-dd.HH:mm:ss");
        ArgumentParser.getStartTime("2018-13-00 00:00:00");
    }

    @Test
    public void getEndTime() throws Exception {
        Assert.assertEquals("Wrong endTime","2017-01-01.01:00:00"
                ,ArgumentParser.getEndTime("2017-01-01.00:00:00", Duration.HOURLY));

        Assert.assertEquals("Wrong endTime","2017-01-02.00:00:00"
        ,ArgumentParser.getEndTime("2017-01-01.00:00:00", Duration.DAILY));
    }

    @Test
    public void parseArgument_invalidArgs() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("input format must has --startDate,--duration,--threshold, optional:--accesslog");
        ArgumentParser.parseArgument("--startDate=a","--duration=b");
    }

    @Test
    public void parseArgument() throws Exception{

        Assert.assertNotNull(ArgumentParser.parseArgument("--startDate=a","--duration=b","--threshold=c"));
        Assert.assertNotNull(ArgumentParser.parseArgument("--startDate=a","--duration=b","--threshold=c,--accesslog=d"));
    }

}