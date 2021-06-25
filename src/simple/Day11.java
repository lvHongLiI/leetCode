package simple;


import javax.script.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;


/**
 * @author 吕宏力
 * @Description: TODO(用一句话描述该文件)
 * @date 2021/4/23 15:23
 */
public class Day11 {


    /**
     * 495. 提莫攻击
     * @param timeSeries
     * @param duration
     * @return
     */
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int last=timeSeries[0];
        int sum=0;
        for (int i = 1; i < timeSeries.length; i++) {
            int time=timeSeries[i]-last;
            if (time<duration){
                sum+=time;
            }else {
                sum+=duration;
            }
            last=timeSeries[i];
        }
        return sum+duration;
    }


    /**
     * 二叉树的后序遍历
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        postorderTraversal(root,list);
        return list;
    }

    public void postorderTraversal(TreeNode root,List<Integer> list) {
        if (root==null)
            return;
        postorderTraversal(root.left,list);
        postorderTraversal(root.right,list);
        list.add(root.val);
    }


    /**
     * 506. 相对名次
     * @param score
     * @return
     */
    public String[] findRelativeRanks(int[] score) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < score.length; i++) {
            map.put(score[i],i);
        }
        Arrays.sort(score);
        String[] arr = new String[score.length];
        int len = score.length - 3;
        len=len<0?0:len;
        String[] str={null,"Gold Medal", "Silver Medal", "Bronze Medal"};
        int index=0;
        for (int i = score.length-1; i >=0; i--) {
            if (++index<4){
                arr[map.get(score[i])]=str[index];
            }else {
                arr[map.get(score[i])]=String.valueOf(index);
            }

        }
        return arr;
    }


    /**
     * 94. 二叉树的中序遍历
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        inorderTraversal(root,list);
        return list;
    }

    private void inorderTraversal(TreeNode treeNode,List<Integer> list){
        if (treeNode==null)
            return;
        inorderTraversal(treeNode.left,list);
        list.add(treeNode.val);
        inorderTraversal(treeNode.right,list);
    }


    /**
     * [-2,1,-3,4,-1,2,1,-5,4]
     * 53. 最大子序和 使用动态规划解决该问题
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int maxSum=nums[0];//最大和
        int currSum=nums[0];//当前和
        for (int i = 1; i < nums.length; i++) {
            if (currSum<0)
                currSum=0;
            currSum+=nums[i];
            maxSum=Math.max(maxSum,currSum);
        }
        return maxSum;
    }


    /**
     * 122. 买卖股票的最佳时机 II
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int min=prices[0];
        int max=0;
        int sum=0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i]<min){
                sum+=max;
                max=0;
                min=prices[i];
            }else {
                if (prices[i]<prices[i-1]){
                    sum+=max;
                    max=0;
                    min=prices[i];
                }else {
                    max=prices[i]-min;
                }
            }
        }
        return sum+max;
    }


    /**
     * 242. 有效的字母异位词
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s.length()!=t.length()){
            return false;
        }
        int[] arr=new int[26];
        for (char c : s.toCharArray()) {
            arr[c-97]++;
        }
        for (char c : t.toCharArray()) {
            arr[c-97]--;
        }
        for (int i : arr) {
            if (i!=0)
                return false;
        }
        return true;
    }

    /**
     * 292. Nim 游戏
     * @param n
     * @return
     */
    public boolean canWinNim(int n) {
      return !(n%4==0);
    }




    public static void main(String[] param){
        for (int i = 1; i < 1000; i++) {
            System.out.println("值："+i+" "+new Day11().canWinNim(i));
        }
    }



}
