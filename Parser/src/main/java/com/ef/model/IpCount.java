package com.ef.model;

/**
 * Created by dustin on 2018/3/10.
 */
public class IpCount {
    private String ip;
    private Integer count;
    public IpCount(String ip,Integer count){
        this.ip = ip;
        this.count = count;
    }

    public String getIp() {
        return ip;
    }

    public Integer getCount() {
        return count;
    }
    @Override
    public String toString(){
        return String.format("IP address: %s,count: %d ",this.ip,this.count);
    }
}
