package simple;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class Test  extends SegmentTree{
    public Test(int[] arr) {
        super(arr);
    }


    public static void main(String[] args) {
        for (Field declaredField : Test.class.getFields()) {
            System.out.println(declaredField.getName());
        }
    }
}
