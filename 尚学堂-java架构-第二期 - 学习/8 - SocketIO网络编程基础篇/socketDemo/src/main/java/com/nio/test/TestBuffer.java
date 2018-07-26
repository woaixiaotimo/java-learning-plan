package com.nio.test;

import java.nio.IntBuffer;

public class TestBuffer {
    public static void main(String[] args) {
//        //1、基本操作
//        //创建指定长度的缓冲区
//        IntBuffer buffer = IntBuffer.allocate(10);
//        buffer.put(13);//position位置 0 - 1
//        buffer.put(21);//position位置 1 - 2
//        buffer.put(35);//position位置 2 - 3
//        //把位置复位为0，也就是position 3-0
//        buffer.flip();
//        System.out.println("使用flip复位：" + buffer);
//        System.out.println("容量为：" + buffer.capacity());
//        System.out.println("限制为：" + buffer.limit());
//
//        System.out.println("获取下表为1的元素： " + buffer.get(1));
//        System.out.println("get(index)方法，position位置不变： " + buffer.get(1));
//        buffer.put(1,4);
//        System.out.println("get(index,change)方法,position位置不变: " + buffer);
//        for (int i = 0; i < buffer.limit(); i++) {
//            //调用get方法会使其缓冲位置position向后递增一位
//            System.out.printf(buffer.get()+"\t");
//        }
//        buffer.flip();
//        //---------------------------------------------------------------------------------------------------------


//        //2 wrap方法使用-不建议使用
//        //wrap方法会包裹一个数组，一般这种用法不会先初始化缓存对象长度，因为没有意义，最后还会被wrap所包裹的数组覆盖掉
//        //并且wrap方法修改缓冲区对象的时候，数组本身也会发生变化
//        int[] arr = new int[]{1, 2, 5};
//        IntBuffer buffer1 = IntBuffer.wrap(arr);
//        System.out.println("buffer1 = " + buffer1);
//
//        IntBuffer buffer2 = IntBuffer.wrap(arr, 0, 2);
//        //这样使用表示容量为数组arr的长度，但是可操作的元素只有十几进入缓存区的元素长度
//        System.out.println("buffer2 = " + buffer2);


        //3 其他方法
        IntBuffer buffer3 = IntBuffer.allocate(10);
        int[] arr = new int[]{1, 2, 5};
        buffer3.put(arr);
        System.out.println("buffer3 = " + buffer3);

        //一种复制方法
        IntBuffer buffer4 = buffer3.duplicate();
        System.out.println("buffer4 = " + buffer4);
        //设置buffer3的位置属性
        buffer3.position(1);
        System.out.println("buffer3 = " + buffer3);
        System.out.println("buffer3的可读数据为 = " + buffer3.remaining());

        int[] arr2 = new int[buffer3.remaining()];
        //将缓冲数据放入arr2中
        buffer3.get(arr2);
        for (int i : arr2) {
            System.out.print(i + ",");
        }
        System.out.println();


    }
}
