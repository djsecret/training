package com.damon.util.search;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by damon on 16/7/1.
 */
public class StringSearch {
    public static void main(String[] args) {
        Pattern uidPattern = Pattern.compile("<@U(\\d+?)>");
        Pattern gidPattern = Pattern.compile("<@G\\d+>");
        Pattern sessionPattern = Pattern.compile("<@Session>");
        Matcher uidMatcher = uidPattern.matcher("hi<@U123> 123 <@G123><@U123><@U123>");
        Matcher gidMatcher = gidPattern.matcher("<@G123><@U123>123, <@G123> 456");
        Matcher sessionMatcher = sessionPattern.matcher("<@U123>123, <@G123> <@Session>");
        while (uidMatcher.find()) {
            String group = uidMatcher.group(1);
            System.out.println(group);
        }
        System.out.println("=======");
        while (gidMatcher.find()) {
            String group = gidMatcher.group();
            System.out.println(group.substring(3, group.length() - 1));
        }
        while (sessionMatcher.find()) {
            System.out.println("@Session");
        }
    }
}
