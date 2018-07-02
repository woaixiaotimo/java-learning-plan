package com.futureDemo1;

public class Future {

    public static void main(String[] args) {
        FutureClient futureClient = new FutureClient();
        Data data = futureClient.request("请求参数");

        System.out.println("请求发送成功");
        System.out.println("做其他事情");

        String result = data.getRequest();
        System.out.println("result = " + result);

    }
}
