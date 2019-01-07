package test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * @Auther: what
 * @Date: 2019/1/3 10:36
 * @Description:
 */
public class CollectionsTest {
    public static void main(String[] args) {
        Map a = (Map) Collections.synchronizedMap(new HashMap<>());
        if (a instanceof Hashtable) {
            System.out.println("Hashtable");
        }
        if (a instanceof HashMap) {
            System.out.println("HashMap");
        }
        if (a instanceof Map) {
            System.out.println("map");
        }
    }
}
