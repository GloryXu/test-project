package com.redsun.main;

import com.redsun.redis.RedisClient;

public class Main {

    public static final String host = "192.168.13.161";
    public static final int port = 26379;
//    public static final String pwd = "dIzs#4@G*Wvgw3XF!EVNrc%F";

    public static void main(String[] args) {
        new RedisClient().show();
    }
}
