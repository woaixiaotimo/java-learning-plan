package test;

import java.io.UnsupportedEncodingException;

public class Test {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String a = "https://bbs.csdn.net/topics/300154555.do";
        String[] b = a.split(".do");
        for (int i = 0; i < b.length; i++) {
            System.out.println("b[i] = " + b[i]);
        }

    }


}
















