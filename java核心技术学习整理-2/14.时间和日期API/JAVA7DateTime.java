package com;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class JAVA7DateTime {

    public static void getNowTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Calendar Cld = Calendar.getInstance();
        String timestamp = df.format(Cld.getTime());
        System.out.println("timestamp = " + timestamp);

    }

    public static void main(String[] args) {

    }
}
