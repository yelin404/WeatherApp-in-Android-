package com.example.wether.juhe;

public class URLUtils {

    public static final String key = "2fe153b8f91ae4ac9fe7ca5290769b74";
    public static String temp_url = "http://apis.juhe.cn/simpleWeather/query";
    public static String index_url = "http://apis.juhe.cn/simpleWeather/life";

    public static String getURL(String city) {

        String url = temp_url+"?city="+city+"&key="+key;
        return url;

    }

    public static String getIndexURL(String city) {
        String url = index_url+"?city="+city+"&key="+key;
        return url;
    }

}
