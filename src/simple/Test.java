package simple;

import java.util.Arrays;

public class Test {
    private static boolean flag = false;

    private Test() {

        if (flag == false) {
            flag = !flag;
        } else {
            throw new RuntimeException("单例模式被侵犯！");
        }
    }
    public static void main(String[] args) {

        System.out.println(new Day02().guessNumber(10));
        //System.out.println(new Day02().firstBadVersion(2126753390));
    }
}
