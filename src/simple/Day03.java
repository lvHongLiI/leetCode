package simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
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
     * 628. 三个数的最大乘积
     * @param nums
     * @return
     */
    public int maximumProduct(int[] nums) {
        //1.找出最大的三个数
        Arrays.sort(nums);

        int length = nums.length;
        return Math.max(nums[length-1]*nums[0]*nums[1],nums[length-1]*nums[length-2]*nums[length-3]);
    }

    /**
     * 637. 二叉树的层平均值
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        return null;
    }

    /**
     * 645. 错误的集合
     * @param nums
     * @return
     */
    public int[] findErrorNums(int[] nums) {
        int[] arr=new int[2];
        for (int i = 0; i < nums.length; i++) {
            int index=Math.abs(nums[i])-1;
            if (nums[index]>0)
                nums[index]*=-1;
            else
                arr[0]=index+1;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]>0){
                arr[1]=i+1;
                break;
            }
        }
        return arr;
    }

    /**
     * 643. 子数组最大平均数 I
     * @param nums
     * @param k
     * @return
     */
    public double findMaxAverage(int[] nums, int k) {
        double sum=0;
        for(int i=0;i<k;i++)
            sum+=nums[i];
        double res=sum;
        for(int i=k;i<nums.length;i++){
            sum+=nums[i]-nums[i-k];
            res=Math.max(res,sum);
        }
        return res/k;
    }


    /**
     * 653. 两数之和 IV - 输入 BST
     * @param root
     * @param k
     * @return
     */
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list=new LinkedList<>();
        findTarget(list,root);
        Set<Integer> set=new HashSet<>();
        for (Integer integer : list) {
            if (set.contains(integer)){
                set.add(k-integer);
                continue;
            }
            return true;
        }
        return false;
    }

    private void findTarget(List list,TreeNode root){
        if (root==null)
            return;
        list.add(root.val);
        findTarget(list,root.left);
        findTarget(list,root.right);
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
        if(node == null){
            return;
        }
        builder.append(node.val);
        if (node.right!=null||node.left!=null){
            builder.append("(");
            tree2str(builder,node.left);
            builder.append(")");
        }
        if (node.right!=null){
            builder.append("(");
            tree2str(builder,node.right);
            builder.append(")");
        }
    }

    /**
     * 617. 合并二叉树
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1==null)
            return t2;

        if (t2==null)
            return t1;
        TreeNode node = new TreeNode(t1.val + t2.val);
        node.left = mergeTrees(t1.left, t2.left);
        node.right = mergeTrees(t1.right, t2.right);
        return node;
    }

    /**
     * 657. 机器人能否返回原点
     * @param moves
     * @return
     */
    public boolean judgeCircle(String moves) {
        int r=0,l=0,d=0,u=0;
        for (char c : moves.toCharArray()) {
            switch (c){
                case 'U':
                    u++;
                    break;
                case 'D':
                    d++;
                    break;
                case 'R':
                    r++;
                    break;
                case 'L':
                    l++;
                    break;
            }
        }
        return r==l&&u==d;
    }


    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        TreeNode root1=new TreeNode(2);
        TreeNode root2=new TreeNode(3);
        TreeNode root11=new TreeNode(4);
        root1.right=root11;
        root.left=root1;
        root.right=root2;
        String s = new Day03().tree2str(root);
        System.out.println(s);

    }
}
