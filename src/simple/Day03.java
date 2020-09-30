package simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Day03 {

    /**
     * 496. 下一个更大元素 I
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] arr=new int[nums1.length];//返回结果
        //编写逻辑
        Map<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            for (int j = i+1; j < nums2.length; j++) {
                if (nums2[i]<nums2[j]){
                    map.put(nums2[i],nums2[j]);
                    break;
                }
            }
        }
        for (int i = 0; i < nums1.length; i++) {
            Integer integer = map.get(nums1[i]);
            arr[i]=integer==null?-1:integer.intValue();
        }
        return arr;
    }


    /**
     * 414. 第三大的数
     * @param nums
     * @return
     */
    public int thirdMax(int[] nums) {
        TreeSet<Integer> set=new TreeSet<>();
        for (int num : nums) {
            set.add(num);
            if (set.size()==4){
                set.pollFirst();
            }
        }
        return set.size()==3?set.first():set.last();
    }


    /**
     * 409. 最长回文串
     * @param s
     * @return
     */
    public int longestPalindrome(String s) {
        int[] arr=new int[58];
        boolean flag=false;
        int sum=0;
        for (int i = 0; i < s.length(); i++)
            arr[s.charAt(i)-'A']++;
        for (int i : arr) {
            if (i%2==0)
                sum+=i;
            else{
                if (!flag){
                    sum+=i;
                    flag=true;
                }else
                    sum+=i-1;
            }
        }
        return sum;
    }


    /**
     * 412. Fizz Buzz
     * @param n
     * @return
     */
    public List<String> fizzBuzz(int n) {
        String fizz="Fizz";
        String buzz="Buzz";
        String fizzBuzz="FizzBuzz";
        List<String> list=new ArrayList<>();
        for (int i = 1; i <=n; i++) {
            if (i%15==0){
                list.add(fizzBuzz);
            }else if (i%5==0){
                list.add(buzz);
            }else if (i%3==0){
                list.add(fizz);
            }else {
                list.add(String.valueOf(i));
            }
        }
        return list;

    }

    /**
     * 482. 密钥格式化
     * @param S
     * @param K
     * @return
     */
    public String licenseKeyFormatting(String S, int K) {
        StringBuilder sb=new StringBuilder();
        int len=0;
        char[] chars = S.toCharArray();
        char c='\u0000';
        for (int i = chars.length; i >0 ; i--) {
            int index=i-1;
            if (chars[index]!='-'){
                c=chars[index];
                if (c>96&&c<124)
                    c-=32;
                sb.append(c);
                len++;
            }
            if (len!=0&&len%K==0){
                sb.append('-');
                len=0;
            }
        }
        len=sb.length()-1;
        if (len>=0&&sb.charAt(len)=='-')
            sb.deleteCharAt(len);
        return sb.reverse().toString();
    }


    /**
     * 575. 分糖果
     * @param candies
     * @return
     */
    public int distributeCandies(int[] candies) {
        Set<Integer> set=new HashSet<>();
        for (int candy : candies) {
            set.add(candy);
        }
        return Math.min(set.size(),candies.length/2);
    }


    /**
     * 606. 根据二叉树创建字符串
     * @param t
     * @return
     */
    public String tree2str(TreeNode t) {
        StringBuilder builder=new StringBuilder();
        tree2str(builder,t);
        return builder.toString();
    }

    private void tree2str(StringBuilder builder,TreeNode node){
        if(node == null)
            return;
        builder.append(node.val);
        builder.append("(");
        tree2str(builder,node.left);
        builder.append(")");
        builder.append("(");
        tree2str(builder,node.right);
        builder.append(")");
    }

    public static void main(String[] args) {
        TreeNode node=new TreeNode(1);
        TreeNode node1_1=new TreeNode(2);
        TreeNode node1_2=new TreeNode(3);
        TreeNode node1_1_2=new TreeNode(4);
        node1_1.right=node1_1_2;
        node.left=node1_1;
        node.right=node1_2;
        System.out.println(new Day03().tree2str(node));
    }
}
