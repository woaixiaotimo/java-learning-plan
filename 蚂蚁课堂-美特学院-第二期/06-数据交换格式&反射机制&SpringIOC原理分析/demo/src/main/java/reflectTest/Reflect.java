package reflectTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Properties;


public class Reflect {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, FileNotFoundException, IOException {

        Class<?> cs = Class.forName("reflectTest.Subject");
        //获取基本属性
        cs.getPackage();
        //获取类的属性（public）
        System.out.println(Modifier.toString(cs.getModifiers()) + "," + Modifier.isInterface(cs.getModifiers()));//public,false
        //获取类名
        System.out.println("类名：" + cs.getName());
        //获取所有接口
        Class<?>[] inter = cs.getInterfaces();
        for (int i = 0; i < inter.length; i++) {
            System.out.println("实现的接口：" + inter[i].getName());
        }

        //获取继承的父类
        System.out.println("继承类：" + cs.getSuperclass());

        //获取所有的属性
        Field[] fields = cs.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            System.out.println("属性：" + fields[i]);
            // 权限修饰符
            int mo = fields[i].getModifiers();
            String priv = Modifier.toString(mo);
            // 属性类型
            Class<?> type = fields[i].getType();
            System.out.println(priv + " " + type.getName() + " "
                    + fields[i].getName() + ";");
        }

        //给某个属性赋值

        //获取id属性
        Field idF = null;
        try {
            idF = cs.getDeclaredField("id");
        } catch (NoSuchFieldException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (SecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        //实例化这个类赋给o
        Object o = cs.newInstance();
        //打破封装
        idF.setAccessible(true); //使用反射机制可以打破封装性，导致了java对象的属性不安全。
        //给o对象的id属性赋值"110"
        idF.set(o, 11); //set
        //get
        System.out.println("id的值为" + idF.get(o));  //11

        /*属性：private int reflectTest.Subject.id
        private int id;
        属性：private java.lang.String reflectTest.Subject.name
        private java.lang.String name;*/

        //获取所有构造函数
        //又get到一点，Class对象的方法不是只适合让最大的类使用，方法也可以用，获取属性什么的
        Constructor<?>[] constructors = cs.getConstructors();
        for (int i = 0; i < constructors.length; i++) {
            System.out.println("构造方法：" + constructors[i]);
        }
        for (int i = 0; i < constructors.length; i++) {
            Class<?> p[] = constructors[i].getParameterTypes();
            System.out.print("构造方法：  ");
            int mo = constructors[i].getModifiers();
            System.out.print(Modifier.toString(mo) + " ");
            System.out.print(constructors[i].getName());
            System.out.print("(");
            for (int j = 0; j < p.length; ++j) {
                System.out.print(p[j].getName() + " arg" + i);
                if (j < p.length - 1) {
                    System.out.print(",");
                }
            }
            System.out.println("){}");

        }


        //获取所有的方法
        Method[] methods = cs.getMethods();
        for (int i = 0; i < methods.length; i++) {
            System.out.println("方法：" + methods[i]);
            //方法具体的拼接就不写了吧
        }

        //调用某个方法
        try {
            //调用describe()方法
            Method method = cs.getMethod("describe");
            method.invoke(cs.newInstance());
            //调用describe(id，name)方法
            Method method1 = cs.getMethod("describe", int.class);
            method1.invoke(cs.newInstance(), 1);
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //读取文件（工厂模式）
        SubjectChild oj = (SubjectChild) Class.forName(Reflect.getPro().getProperty("subject")).newInstance();
        oj.describe();


        // 在泛型为Integer的ArrayList中存放一个String类型的对象。
        ArrayList<Integer> list = new ArrayList<Integer>();
        Method method = null;
        try {
            method = list.getClass().getMethod("add", Object.class);
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        method.invoke(list, "Java反射机制实例。");
        System.out.println(list.get(0));


        //通过反射取得并修改数组的信息
        int[] temp = {1, 2, 3, 4, 5};
        Class<?> demo = temp.getClass().getComponentType();
        System.out.println("数组类型： " + demo.getName());
        System.out.println("数组长度  " + Array.getLength(temp));
        System.out.println("数组的第一个元素: " + Array.get(temp, 0));
        Array.set(temp, 0, 100);
        System.out.println("修改之后数组第一个元素为： " + Array.get(temp, 0));

    }

    public static Properties getPro() throws FileNotFoundException, IOException {
        Properties pro = new Properties();
        File f = new File("subject.properties");
        if (f.exists()) {
            System.out.println("aa");
            pro.load(new FileInputStream(f));
        } else {
            pro.setProperty("subject", "reflectTest.Subject");
            pro.store(new FileOutputStream(f), "SUBJECT CLASS");
        }
        return pro;
    }

}
