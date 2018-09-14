package proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB代理
 * JDK实现动态代理需要实现类通过接口定义业务方法。
 * CGLib采用了非常底层的字节码技术，其原理是通过目标类的字节码为一个类创建子类，
 * 并在子类中采用方法拦截的技术拦截所有父类方法的调用，顺势织入横切逻辑。
 * 底层使用字节码处理框架ASM，来转换字节码并生成新的类。
 * 更详细一点说，代理类将目标类作为自己的父类并为其中的每个非final委托方法创建两个方法：
 * 一个是与目标方法签名相同的方法，它在方法中会通过super调用目标方法；
 * 另一个是代理类独有的方法，称之为Callback回调方法，它会判断这个方法是否绑定了拦截器（实现了MethodInterceptor接口的对象），
 * 若存在则将调用intercept方法对目标方法进行代理，也就是在前后加上一些增强逻辑。intercept中就会调用上面介绍的签名相同的方法。
 */
public class Test {
    public static void main(String[] args) {

        Cglib cglib = new Cglib();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(YuShengJun.class);
        enhancer.setCallback(cglib);
        Hose hose = (Hose) enhancer.create();
        hose.maifang();

    }
}

interface Hose {

    void maifang();
}

class YuShengJun implements Hose {

    @Override
    public void maifang() {
        System.out.println("我是余胜军，终于可以买房啦!!!");
    }

}

//cglib 动态代理
class Cglib implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("我是中介,你买房开始交给我啦!!!");
        Object invokeSuper = methodProxy.invokeSuper(o, objects);
        System.out.println("我是中介,你买房开结束啦!!!");
        return invokeSuper;
    }
}