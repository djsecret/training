package com.damon.concurrency.user_thread;

/**
 * Created by dongjun.wei on 16/3/30.
 */
public class StringTest {
    public static void main(String[] args) {
        StringTest stringTest = new StringTest();

        String string = "abc";
        stringTest.replace(string);
        System.out.println(string);

        Integer i = 1;
        stringTest.add(i);
        System.out.println(i);

        String s1 = "12";
        String s2 = "12";
        String s3 = new String("12");

        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
        System.out.println(s1 == s3);//false
        System.out.println(s1.equals(s3));//true
    }

    public void replace(String string) {
        string = string.replace('a', 'b');
        string = string.toUpperCase();
        System.out.println("inner:" + string);
    }

    public void add(Integer integer) {
        integer++;
        System.out.println("inner:" + integer);

    }
}
