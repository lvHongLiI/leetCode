package secondary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Day01 {

    /**
     * 229. 求众数 II
     */
    public List<Integer> majorityElement(int[] nums) {
        Map<Integer,Integer> map=new HashMap<>();
        for (int num : nums) {
            if (map.get(num)==null){
                map.put(num,1);
            }else {
               map.put(num,map.get(num)+1);
            }
        }
        List<Integer> list=new ArrayList<>();
        int x=nums.length/3;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue()>x)
                list.add(entry.getKey());
        }
        return list;
    }


    /**
     * 137. 只出现一次的数字 II
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new Day01().majorityElement(new int[]{1, 1, 1, 3, 3, 2, 2, 2}));
    }
}
