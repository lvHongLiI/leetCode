package simple;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 每个类 30题
 */
public class Day06 {


    /**
     * 1431. 拥有最多糖果的孩子
     * @param candies
     * @param extraCandies
     * @return
     */
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        //1.获取最多的糖果
        int[] arr=candies.clone();
        Arrays.sort(arr);
        int max=arr[arr.length-1];
        List<Boolean> list=new LinkedList<>();
        for (int candy : candies) {
            list.add(candy+extraCandies>=max);
        }
        return list;
    }

    /**
     * 1394. 找出数组中的幸运数
     * @param arr
     * @return
     */
    public int findLucky(int[] arr) {
        int[] ints=new int[501];
        for (int i : arr) {
            ints[i]++;
        }
        for (int i = ints.length-1; i >0 ; i--) {
            if (ints[i]==i)
                return i;
        }
        return -1;
    }


    /**
     * 1446. 连续字符
     * @param s
     * @return
     */
    public int maxPower(String s) {
        int max=0;
        int slow=0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[slow]!=chars[i]){
                max=Math.max(max,i-slow);
                slow=i;
            }else if (i==chars.length-1){
                max=Math.max(max,chars.length-slow);
            }
        }
        return max;
    }


    /**
     * 1450. 在既定时间做作业的学生人数
     * @param startTime
     * @param endTime
     * @param queryTime
     * @return
     */
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
            int sum=0;
            for (int i = 0; i < startTime.length; i++) {
                if (startTime[i]<=queryTime&&endTime[i]>=queryTime)
                    sum++;
            }
            return sum;
    }


    /**
     * 1455. 检查单词是否为句中其他单词的前缀
     * @param sentence
     * @param searchWord
     * @return
     */
    public int isPrefixOfWord(String sentence, String searchWord) {
        char[] chars = sentence.toCharArray();
        int space=0;
        int wordIndex=0;
        boolean flag=true;
        for (int i = 0; i < chars.length; i++) {
            char c=chars[i];
            if (c==' '){
                space++;
                wordIndex=0;
                flag=true;
                continue;
            }else {
             if (flag&&c==searchWord.charAt(wordIndex)){
                 if (wordIndex==searchWord.length()-1)
                     return space+1;
                 else
                     wordIndex++;
             }else {
                 flag=false;
             }
            }
        }
        return -1;
//        String[] split = sentence.split("\\s+");
//        for (int i = 0; i < split.length; i++) {
//            if (isPrefix(split[i],searchWord))
//                return i+1;
//        }
//        return -1;
    }

    /**
     * 1460. 通过翻转子数组使两个数组相等
     * @param target
     * @param arr
     * @return
     */
    public boolean canBeEqual(int[] target, int[] arr) {
        int[] nums=new int[1000];
        for (int i = 0; i < target.length; i++) {
            nums[target[i]]++;
            nums[arr[i]]++;
        }
        for (int num : nums) {
            if (num%2==1)
                return false;
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(new Day06().isPrefixOfWord("ellohello hellohellohello","ell"));
    }
}
