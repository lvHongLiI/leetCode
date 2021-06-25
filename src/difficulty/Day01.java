package difficulty;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * 每个类 30题
 */
public class Day01 {

    /**
     * 154. 寻找旋转排序数组中的最小值 II
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i]<nums[i-1]){
                return nums[i];
            }
        }
        return nums[0];
    }

    public static double add(Double v1, Double v2) {
        if(v1==null){
            return v2;
        }
        if(v2==null){
            return v1;
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    public     static  void main(String[] args) {
        System.out.println(Pattern.compile("^(([1-9]{1}\\d*)|(0{1}))$").matcher("1").matches());
        System.out.println(Pattern.compile("^(([1-9]{1}\\d*)|(0{1}))$").matcher("0").matches());
        System.out.println(Pattern.compile("^(([1-9]{1}\\d*)|(0{1}))$").matcher("1.2").matches());
        System.out.println(Pattern.compile("^(([1-9]{1}\\d*)|(0{1}))$").matcher("-1").matches());
    }
}
