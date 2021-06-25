package secondary;


import simple.ListNode;
import simple.TreeNode;
import sun.misc.Unsafe;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * 每个类 30题
 */
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
     * 2. 两数相加
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1==null)
            return l2;
        if (l2==null)
            return l1;
        return addTwoNumbers(l1,l2,0);
    }

    private ListNode addTwoNumbers(ListNode l1, ListNode l2,int on) {
        if (l1==null&&l2==null){
            if (on!=0)
                return new ListNode(on);
            return null;
        }

        int now=0;
        ListNode lastl1=null;
        ListNode lastl2=null;
        if (l1==null){
            now+=l2.val;
            lastl2=l2.next;
        }
        else if (l2==null){
            now+=l1.val;
            lastl1=l1.next;
        }else {
            now+=l1.val+l2.val;
            lastl2=l2.next;
            lastl1=l1.next;
        }
        ListNode node = new ListNode((now + on)%10);
        node.next= addTwoNumbers(lastl1, lastl2, (now + on)/10);
        return node;
    }

    /**
     * 189. 旋转数组
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int[] clone = nums.clone();
        for (int i = 0; i < nums.length; i++) {
            nums[(i+k)%nums.length]=clone[i];
        }
    }

    /**
     * 54. 螺旋矩阵
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix==null||matrix.length==0)
            return new ArrayList<>();
        LinkedList<Integer> list=new LinkedList<Integer>();
        int max=matrix[0].length*matrix.length;
        int hangLeft=0;
        int hangRight=matrix[0].length-1;
        int lieLeft=0;
        int lieRight=matrix.length-1;
        while (hangLeft-hangRight<=1||lieLeft-lieRight<=1){
            for (int i = hangLeft; i <=hangRight; i++) {
                if (list.size()==max)
                    break;
                list.add(matrix[lieLeft][i]);

            }
            lieLeft++;
            for (int i = lieLeft; i <=lieRight; i++) {
                if (list.size()==max)
                    break;
                list.add(matrix[i][hangRight]);
            }
            hangRight--;
            for (int i = hangRight; i >=hangLeft; i--) {
                if (list.size()==max)
                    break;
               list.add(matrix[lieRight][i]);
            }
            lieRight--;
            for (int i = lieRight; i >=lieLeft; i--) {
                if (list.size()==max)
                    break;
                list.add(matrix[i][hangLeft]);
            }
            hangLeft++;
        }

        return list;
    }


    /**
     * 102. 二叉树的层序遍历
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        ArrayList<List<Integer>> list = new ArrayList<>();
        levelOrder(root,0,list);
        return list;
    }

    public void levelOrder(TreeNode root,int level,List<List<Integer>> lists) {
        if (root==null)
            return;
        List<Integer> list=null;
        if (lists.size()<=level){
            list = new ArrayList<>();
            list.add(root.val);
            lists.add(list);
        }else {
            lists.get(level).add(root.val);
        }
        int nextLevel=level+1;
        levelOrder(root.left,nextLevel,lists);
        levelOrder(root.right,nextLevel,lists);
    }


    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] count={-1,-1};
        int left=0;
        int right=nums.length;
        while (left<right){
            int mid= left+(right-left)/2;
            if (nums[mid]>target){//错误版本
                right=mid;
            }else if (nums[mid]<target){//正确版本
                left=mid+1;
            }else {
                count[0]=mid;
                count[1]=mid;
                int l=mid-1;
                int r=mid+1;
                while (l>-1){
                    if (nums[l]!=target){
                        break;
                    }else {
                        count[0]=l--;
                    }
                }
                while (r<nums.length){
                    if (nums[r]!=target){
                        break;
                    }else {
                        count[1]=r++;
                    }
                }
                break;
            }
        }
        return count;
    }


    /**
     * 151. 翻转字符串里的单词
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        StringBuilder sb=new StringBuilder();
        int count=0;
        char[] chars = s.toCharArray();
        int kuai=chars.length-1;
        while (kuai>=0){
            if (chars[kuai]!=' '){
                count++;
            }else {
                for (int i = kuai+1; i <=kuai+count; i++) {
                    sb.append(chars[i]);
                }
                if (count>0){
                    sb.append(' ');
                    count=0;
                }
            }
            kuai--;
        }
        if (count!=0)
            for (int i = kuai+1; i <=kuai+count; i++) {
                sb.append(chars[i]);
            }
        if (sb.length()>0&&sb.charAt(sb.length()-1)==' ')
            sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }


    /**
     * 287. 寻找重复数
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        boolean[] arr=new boolean[nums.length+2];
        for (int num : nums) {
            if (!arr[num])
                arr[num]=true;
            else
                return num;
        }
        return -1;
    }

    /**
     * 144. 二叉树的前序遍历
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        preorderTraversal(root,list);
        return list;
    }


    private void preorderTraversal(TreeNode root,List<Integer> list) {
        if (root==null)
            return;
        preorderTraversal(root.left,list);
        list.add(root.val);
        preorderTraversal(root.right,list);
    }



}
