package simple;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 吕宏力
 * @Date 2022-11-14 9:21
 */
public class Day12 {


    /**
     * 594. 最长和谐子序列
     * @param nums
     * @return
     */
    public int findLHS(int[] nums) {
        int max=0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            Integer val = map.get(num);
            if (val==null)
                val=0;
            map.put(num,val+1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();
            Integer integer = map.get(entry.getKey() + 1);
            if (integer==null){
                continue;
            }
            max=Math.max(max,value+integer);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Day12().findLHS(new int[]{1, 3, 2, 2, 5, 2, 3, 7}));
    }
}
