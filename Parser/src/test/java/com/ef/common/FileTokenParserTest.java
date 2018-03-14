package com.ef.common;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dustin on 2018/3/13.
 */
public class FileTokenParserTest {
    @Test
    public void ipParse() throws Exception {
        Assert.assertEquals("Wrong IP Format","192.168.0.1",FileTokenParser.ipParse("192.168.0.1").orElse("fail"));
        Assert.assertEquals("Wrong IP Format","172.0.0.1",FileTokenParser.ipParse("172.0.0.1").orElse("fail"));
        Assert.assertEquals("Wrong IP Format","168.255.255.255",FileTokenParser.ipParse("168.255.255.255").orElse("fail"));
    }

    @Test
    public void ipParse_invalidIP() throws Exception{
        Assert.assertEquals("fail",FileTokenParser.ipParse("192.168.0.256").orElse("fail"));
        Assert.assertEquals("fail",FileTokenParser.ipParse("172.0.0.0.1").orElse("fail"));
        Assert.assertEquals("fail",FileTokenParser.ipParse("14.2522.255.255").orElse("fail"));
        Assert.assertEquals("fail",FileTokenParser.ipParse("1.0.0.300").orElse("fail"));
        Assert.assertEquals("fail",FileTokenParser.ipParse("192.168.256.255").orElse("fail"));
        Assert.assertEquals("fail",FileTokenParser.ipParse("3.168.-1.255").orElse("fail"));
    }

    @Test
    public void dateParse() throws Exception {
        Assert.assertEquals("Wrong Date Format","2017-01-01 00:00:59.410",FileTokenParser.dateParse("2017-01-01 00:00:59.410").orElse("fail"));
        Assert.assertEquals("Wrong Date Format","2017-01-01 00:01:19.242",FileTokenParser.dateParse("2017-01-01 00:01:19.242").orElse("fail"));
        Assert.assertEquals("Wrong Date Format","2017-01-01 00:02:15.555",FileTokenParser.dateParse("2017-01-01 00:02:15.555").orElse("fail"));
        Assert.assertEquals("Wrong Date Format","2017-01-01 00:02:16.626",FileTokenParser.dateParse("2017-01-01 00:02:16.626").orElse("fail"));
        Assert.assertEquals("Wrong Date Format","2017-01-01 23:59:37.974",FileTokenParser.dateParse("2017-01-01 23:59:37.974").orElse("fail"));
        Assert.assertEquals("Wrong Date Format","2017-01-01 23:59:37.974",FileTokenParser.dateParse("2017-01-01 23:59:37.974").orElse("fail"));

    }

}