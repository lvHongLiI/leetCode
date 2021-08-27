import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 吕宏力
 * @Date: 2021/07/30/15:23
 * @Description:
 */
public class Main {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
       // Test2 test2 = new Test2();
        //Test2.class.newInstance();
        Constructor<Unsafe> declaredConstructor = Unsafe.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        Unsafe unsafe = declaredConstructor.newInstance();

        Object o = unsafe.allocateInstance(Test2.class);
        System.out.println(o);
    }
}
