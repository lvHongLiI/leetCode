package simple;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import java.util.Collection;
import java.util.HashMap;


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
        int max = 0;
        int slow = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[slow] != chars[i]) {
                max = Math.max(max, i - slow);
                slow = i;
            } else if (i == chars.length - 1) {
                max = Math.max(max, chars.length - slow);
            }
        }
        return max;
    }
    /**
     * 925. 长按键入
     * @param name
     * @param typed
     * @return
     */
    public boolean isLongPressedName(String name, String typed) {
        int nameIndex=0;
        int typedIndex=0;
        char last='\u0000';
        while (nameIndex<name.length()||typedIndex<typed.length()){
            if (nameIndex<name.length()&&typedIndex<typed.length()&&name.charAt(nameIndex)==typed.charAt(typedIndex)){
                last=name.charAt(nameIndex);
                nameIndex++;
                typedIndex++;
                if (nameIndex==name.length()&&typedIndex==typed.length())
                    return true;
            }else {
                if (typed.length()==typedIndex)
                    return false;
                if (typed.charAt(typedIndex)==last){
                    typedIndex++;
                    if (nameIndex==name.length()&&typedIndex==typed.length())
                        return true;
                }else {
                    return false;
                }
            }
        }
        return false;
    }


    /**
     * 941. 有效的山脉数组
     * @param arr
     * @return
     */
    public boolean validMountainArray(int[] arr) {
        if (arr.length<3)
            return false;
        int leftPrint=0;
        int rightPrint=arr.length-1;
        while (leftPrint<arr.length-2){
            if (arr[leftPrint]<=arr[leftPrint+1])
                leftPrint++;
            else
                break;
        }

        while (rightPrint>1){
            if (arr[rightPrint]<=arr[rightPrint-1]){
                rightPrint--;
            }else
                break;
        }
        return leftPrint==rightPrint&&arr[rightPrint]>arr[arr.length-1]&&arr[leftPrint]>arr[0];
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



     /** 1160. 拼写单词
     * @param words
     * @param chars
     * @return
     */
    public int countCharacters(String[] words, String chars) {
        int[] arr=new int[26];
        int sum=0;
        for (char c : chars.toCharArray()) {
            arr[c-'a']++;
        }
        for (String word : words) {
            if (isChars(word,arr.clone())){
                sum+=word.length();
            }
        }
        return sum;
    }
    private boolean isChars(String s,int[] arr){
        for (char c : s.toCharArray()) {
            if (arr[c-'a']--<1)
                return false;

        }
        return true;
    }


    /**
     * 1295. 统计位数为偶数的数字
     * @param nums
     * @return
     */
    public int findNumbers(int[] nums) {
        int sum=0;
        for (int num : nums) {
            while (num>99){
                num/=100;
            }
            if (num>9&&num<100){
                sum++;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new Day06().countCharacters(new String[]{"cat","bt","hat","tree"},"atach"));
    }
}
