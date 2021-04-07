package simple;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 吕宏力
 * @Title： Day10
 * @Package： simple
 * @Description TODO(不知道)
 * @date 2021-02-22 10:20
 * Copyright (c) ©1994-2018 Scjydz.com All Rights Reserved.
 */
public class Day10 {

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

 sssss
    public static void main(String[] args) {
        System.out.println(new Day10().summaryRanges(new int[]{0, 1, 2, 4, 5, 7}));
    }
}
