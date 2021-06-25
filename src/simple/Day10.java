package simple;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


/**
 * @author 吕宏力
 * @Title： Day10
 * @Package： simple
 * @Description TODO(不知道)
 * @date 2021-02-22 10:20
 * Copyright (c) ©1994-2018 Scjydz.com All Rights Reserved.
 */
public  class Day10 {

    /**
     * 257. 二叉树的所有路径
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        LinkedList<String> list = new LinkedList<>();
        binaryTreePaths(root,list,null);
        return list;
    }

    private void binaryTreePaths(TreeNode node,List<String> list,String s){

        if (node==null)
            return;
        if (s==null){
            s=String.valueOf(node.val);
        }else {
            s=s+"->"+node.val;
        }

        if (node.left==null&&node.right==null){
            list.add(s);
        }

        if (node.left!=null)
            binaryTreePaths(node.left,list,s);
        if (node.right!=null)
            binaryTreePaths(node.right,list,s);
    }


    /**
     * 228. 汇总区间
     * @param nums
     * @return
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> list=new LinkedList<>();
        if (nums==null||nums.length==0)
            return list;
        int slow=nums[0];
        int fast=nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i]!=fast+1){
                if (slow==fast)
                    list.add(Integer.toString(slow));
                else
                    list.add(slow+"->"+fast);
                slow=nums[i];
            }
            fast=nums[i];
        }
        if (slow==fast)
            list.add(Integer.toString(slow));
        else
            list.add(slow+"->"+fast);
        return list;
    }


    /**
     * 108. 将有序数组转换为二叉搜索树
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        //1.创建中间节点
        int cent=nums.length/2;
        TreeNode root = new TreeNode(nums[cent]);
        root.left=sortedArrayToBST(nums,0,cent-1);
        root.right=sortedArrayToBST(nums,cent+1,nums.length-1);
        return root;
    }

    private TreeNode sortedArrayToBST(int[] nums,int left,int right) {
        if (left<right){
            int cent=(right-left)/2+left;
            TreeNode node = new TreeNode(nums[cent]);
            node.left=sortedArrayToBST(nums,left,cent-1);
            node.right=sortedArrayToBST(nums,cent+1,right);
            return node;
        }else if (left==right){
            return new TreeNode(nums[left]);
        }
        return null;
    }


    /**
     * 111. 二叉树的最小深度
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root==null)
            return 0;
        return minDepth(root,1);
    }

    private int minDepth(TreeNode root,int len) {
        if (root.left==null&&root.right==null)
            return len;
        int left=Integer.MAX_VALUE;
        int right=Integer.MAX_VALUE;
        if (root.left!=null){
            left=minDepth(root.left,len+1);
        }
        if (root.right!=null){
            right=minDepth(root.right,len+1);
        }
        return Math.min(right,left);
    }


    /**
     * 112. 路径总和
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root==null){
            return false;
        }
        if (root.val==targetSum&&root.right==null&&root.left==null){
            return true;
        }
        return hasPathSum(root.left, targetSum - root.val)|| hasPathSum(root.right, targetSum - root.val);
    }


    /**
     * 141. 环形链表
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
      Set<ListNode> set=new HashSet<>();
      while (head!=null){
          if (set.contains(head)){
              return true;
          }
          set.add(head);
          head=head.next;
      }
      return false;
    }


    /**
     * 263. 丑数(暴力破解法)
     * 官方：
     *     public boolean isUgly(int n) {
     *         if (n <= 0) {
     *             return false;
     *         }
     *         int[] factors = {2, 3, 5};
     *         for (int factor : factors) {
     *             while (n % factor == 0) {
     *                 n /= factor;
     *             }
     *         }
     *         return n == 1;
     *     }
     * @param n
     * @return
     */
    public boolean isUgly(int n) {
        if (n<=0)
            return false;
        Set<Integer> set=new HashSet<>();
        set.add(2);
        set.add(3);
        set.add(5);
        int a = (int) Math.sqrt(n);
        if (a<2||set.contains(n)){
            return true;
        }
        boolean flag=false;
        for (int i = 2; i <=a; i++) {
            if ((n%i==0)){
                if (!flag){
                    flag=true;
                }
                if (!isUgly(i,n/i,set))
                return false;
            }
        }
        return flag;
    }

    public boolean isUgly(int x,int y,Set<Integer> set) {
        return isUgly(x,set)&&isUgly(y,set);
    }

    private boolean isUgly(int n,Set<Integer> set){
        for (Integer i : set) {
            if (n%i==0){
                return true;
            }
        }
        return false;
    }


    /**
     * 290. 单词规律
     * @param pattern
     * @param s
     * @return
     */
    public boolean wordPattern(String pattern, String s) {
        String[] split = s.split("\\s+");
        if (split.length!=pattern.length()){
            return false;
        }
        List<Integer>[] arr=new List[26];
        char[] chars = pattern.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int index=chars[i]-97;
            if (arr[index]==null){
                arr[index]=new ArrayList<>();
            }
            arr[index].add(i);
        }

        Set<String> set=new HashSet<>();
        for (List<Integer> list : arr) {
            if (list!=null){
                Integer index = list.get(0);
                if (set.contains(split[index])){
                    return false;
                }
                set.add(split[index]);
                for (Integer i : list) {
                    if (!split[index].equals(split[i])){
                        return false;
                    }
                }
            }
        }
        return true;
    }


    /**
     * 405. 数字转换为十六进制数
     * @param num
     * @return
     */
    public String toHex(int num) {
       return Integer.toHexString(num);
    }


    /**
     * 463. 岛屿的周长
     * @param grid
     * @return
     */
    public int islandPerimeter(int[][] grid) {
        int chang=grid[0].length;
        int kuan=grid.length;
        int sum=0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j]==1){
                    sum+=4;

                    //上
                    if (i-1>=0){
                        if (grid[i-1][j]==1){
                            sum--;
                        }
                    }
                    //下
                    if (i+1<kuan){
                        if (grid[i+1][j]==1){
                            sum--;
                        }
                    }
                    //左
                    if (j-1>=0){
                        if (grid[i][j-1]==1){
                            sum--;
                        }
                    }
                    //右
                    if (j+1<chang){
                        if (grid[i][j+1]==1){
                            sum--;
                        }
                    }
                }
            }
        }
        return sum;
    }




    public static void main(String[] args) {
        System.out.println(new Day10().islandPerimeter(new int[][]{{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}}));
    }
}
