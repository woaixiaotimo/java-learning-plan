package proxy.staticProxy;

//静态代理
public class Test {
    public static void main(String[] args) {
        Hose hose = new Proxy(new YuShengJun());
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

// 静态代理
class Proxy implements Hose {
    // 代理对象
    private YuShengJun yuShengJun;

    public Proxy(YuShengJun yuShengJun) {
        this.yuShengJun = yuShengJun;
    }

    @Override
    public void maifang() {
        System.out.println("我是中介,你买房开始交给我啦!!!");
        yuShengJun.maifang();
        System.out.println("我是中介,你买房开结束啦!!!");
    }

}