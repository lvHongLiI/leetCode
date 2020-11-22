package simple;

import java.util.Collection;
import java.util.HashMap;

/**
 * 每个类 30题
 */
public class Day06 {

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
     * 1160. 拼写单词
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
