package com;

import java.lang.ref.WeakReference;

public class Test {

}

class test {
    public static void main(String[] args) {
        WeakReference<People> reference = new WeakReference<People>(new People("zhouqian", 20));
        System.out.println(reference.get());
        System.gc();//通知GVM回收资源
        System.out.println(reference.get());
    }
}

class People {
    public String name;
    public int age;

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "[name:" + name + ",age:" + age + "]";
    }
}
