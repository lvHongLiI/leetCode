package simple;

import sun.misc.Unsafe;

import javax.script.*;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
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


    /**
     * 225. 用队列实现栈
     */
    static class MyStack {

        private LinkedList<Integer> one=null;
        private LinkedList<Integer> tow=null;

        /** Initialize your data structure here. */
        public MyStack() {
            one=new LinkedList();
            tow=new LinkedList();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            LinkedList exportList=one.isEmpty()?tow:one;
            LinkedList importList=one.isEmpty()?one:tow;
            importList.add(x);
            while (!exportList.isEmpty()){
                importList.add(exportList.pop());
            }

        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            LinkedList<Integer> exportList=one.isEmpty()?tow:one;
            return exportList.pop();
        }

        /** Get the top element. */
        public int top() {
            LinkedList<Integer> exportList=one.isEmpty()?tow:one;
            return exportList.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
          return   one.isEmpty()&&tow.isEmpty();
        }
    }

    /**
     * 338. 比特位计数
     * @param n
     * @return
     */
    public int[] countBits(int n) {
        int[] ints = new int[n+1];
        for (int i = 1; i < n; i++) {
            ints[i]=hammingWeight(i);
        }
        return ints;
    }

    private int hammingWeight(int n) {
        int count = 0;
        int i = 0;
        while (i++ < 32) {
            count += n & 1;
            n >>= 1;
        }
        return count;
    }

    /**
     * 1720. 解码异或后的数组
     * @param encoded
     * @param first
     * @return
     */
    public int[] decode(int[] encoded, int first) {
        int[] ints = new int[encoded.length];
        ints[0]=first;
        ints[1]=encoded[0]^first;
        for (int i = 1; i < encoded.length; i++) {
            ints[i+1]=ints[i]^encoded[i];
        }
        return ints;
    }
    public static String cutDecimalZero(String str){
            if (str.indexOf(".")!=-1){
                StringBuilder sb=new StringBuilder(str);
                while (sb.charAt(sb.length()-1)=='0'){
                    sb.deleteCharAt(sb.length()-1);
                }
                if (sb.charAt(sb.length()-1)=='.'){
                    sb.deleteCharAt(sb.length()-1);
                }
                return sb.toString();
            }
        return str;
    }


    /**
     * 1221. 分割平衡字符串
     * @param s
     * @return
     */
    public int balancedStringSplit(String s) {
        int lNum=0;
        int rNum=0;
        int sum=0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]){
                case 'L':
                    lNum++;
                    break;
                case 'R':
                    rNum++;
                    break;
            }
            if (lNum==rNum&&lNum!=0){
                sum++;
                lNum=rNum=0;
            }
        }
        if (lNum==rNum&&lNum!=0){
            sum++;
            lNum=rNum=0;
        }
        return sum;
    }


    /**
     * 1844. 将所有数字用字符替换
     * @param s
     * @return
     */
    public String replaceDigits(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length()-1; i+=2) {
            chars[i+1]=shift(chars[i],chars[i+1]);
        }
        return new String(chars);
    }
    private char  shift(char chars,char x){
        return (char) (chars+x-48);
    }


    /**
     * 1796. 字符串中第二大的数字
     * @param s
     * @return
     */
    public int secondHighest(String s) {
        PriorityQueue<Character> queue=new PriorityQueue();
        //1.拿到所有数字
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)){
                queue.remove(c);
                if (queue.size()==2){
                   if (queue.peek().compareTo(c)<0){
                       queue.poll();
                       queue.add(c);
                   }
                }else {
                    queue.remove(c);
                    queue.add(c);
                }
            }
        }
        //2.拿到第二大数字
        if (queue.size()<2)
            return -1;
        return queue.poll()-'0';
    }


    public int[] sortByBits(int[] arr) {
        Integer[] list=new Integer[arr.length];
        for (int i = 0; i < list.length; i++) {
            list[i]=arr[i];
        }
        Arrays.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int one1 = getOne(o1);
                int one2 = getOne(o2);
                if (one1==one2)
                    return o1-o2;
                return one1-one2;
            }
        });

        for (int i = 0; i < list.length; i++) {
            arr[i]=list[i];
        }
        return arr;
    }

    private int getOne(int i){
        int count=0;
        String s = Integer.toBinaryString(i);
        for (char c : s.toCharArray()) {
            if (c=='1')
                count++;
        }
        return count;
    }


    /**
     * 561. 数组拆分 I
     * @param nums
     * @return
     */
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int num=0;
        for (int i = 0; i < nums.length; i+=2) {
            num+=nums[i];
        }
        return num;
    }

    /**
     * 剑指 Offer 68 - I. 二叉搜索树的最近公共祖先
     * 235. 二叉搜索树的最近公共祖先
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root==null){
            return null;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left==null){
            left=root.val==p.val?root:left;
            left=root.val==q.val?root:left;
        }
        if (right==null){
            right=root.val==p.val?root:right;
            right=root.val==q.val?root:right;
        }
        if (right!=null&&left!=null){
            return root;
        }

        return right!=null?right:left;
    }


    /**
     *501. 二叉搜索树中的众数
     * @param root
     * @return
     */
    public int[] findMode(TreeNode root) {
        List<Integer> list=new LinkedList<>();
        findMode(root,list);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Integer integer : list) {
            Integer num = map.get(integer);
            if (num==null){
                map.put(integer,1);
            }else {
                map.put(integer,num+1);
            }
        }
        int max=0;
        int num=0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int value = entry.getValue().intValue();
            if (max<value){
                num=0;
                max=value;
            }else if (max==value)
                num++;
        }
        int[] ints = new int[num];
        int i=0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int value = entry.getValue().intValue();
            if (max==value){
                ints[i++]=entry.getKey();
            }
        }
        return ints;
    }

    private void findMode(TreeNode root,List<Integer> list){
        if (root==null)
            return;
        findMode(root.left,list);
        list.add(root.val);
        findMode(root.right,list);
    }


    /**
     * 剑指 Offer II 052. 展平二叉搜索树
     * @param root
     * @return
     */
    public TreeNode increasingBST(TreeNode root) {
        List<Integer> list=new ArrayList<>();
        increasingBST(root,list);

        Iterator<Integer> iterator = list.iterator();
        TreeNode node=new TreeNode(iterator.next());
        TreeNode dummy=node;
        while (iterator.hasNext()){
            node.right=new TreeNode(iterator.next());
            node=node.right;
        }
        return dummy;
    }
    private void increasingBST(TreeNode root, List<Integer> list) {
        if (root==null)
            return;
        increasingBST(root.left,list);
        list.add(root.val);
        increasingBST(root.right,list);
    }


    /**
     * 563. 二叉树的坡度
     * @param root
     * @return
     */
    public int findTilt(TreeNode root) {
        AtomicInteger list = new AtomicInteger(0);
        findTilt(root,list);
        int sum=0;

        return list.get();
    }

    public int  findTilt(TreeNode root,AtomicInteger list){
        if (root==null){
            return 0;
        }
        int left = findTilt(root.left,list);
        int right = findTilt(root.right,list);
        list.addAndGet(Math.abs(left-right));
        return left+right+root.val;
    }


    /**
     * 面试题 04.02. 最小高度树v
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        int cent = nums.length / 2;
        TreeNode root = new TreeNode(nums[cent]);
        root.left=sortedArrayToBST(0,cent-1,nums);
        root.right=sortedArrayToBST(cent+1,nums.length-1,nums);
        return root;
    }

    private TreeNode sortedArrayToBST(int left,int right,int[] nums){
        if (left>right)
            return null;
        int cent = (right-left)/2+left;
        TreeNode treeNode = new TreeNode(nums[cent]);
        treeNode.left=sortedArrayToBST(left,cent-1,nums);
        treeNode.right=sortedArrayToBST(cent+1,right,nums);
        return treeNode;
    }


    /**
     * 剑指 Offer II 056. 二叉搜索树中两个节点之和
     * @param root
     * @param k
     * @return
     */
    public boolean findTarget(TreeNode root, int k) {
       return findTarget(root,k,new HashSet<Integer>());

    }

    public boolean findTarget(TreeNode root, int k,Set<Integer> set) {
        if (root==null)
            return false;
        boolean left = findTarget(root.left, k, set);
        boolean rigth = findTarget(root.right, k, set);
        boolean contains = set.contains(k - root.val);
        set.add(root.val);
        return contains||left||rigth;
    }


    /**
     * 面试题 02.03. 删除中间节点
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val=node.next.val;
        node.next=node.next.next;
    }


    class KthLargest {

        private PriorityQueue<Integer> maxList=new PriorityQueue<>();

        private int max;

        public KthLargest(int k, int[] nums) {
            max=k;
            for (int num : nums) {
                maxList.add(num);
                if (maxList.size()>k){
                    maxList.poll();
                }
            }
        }


        /**
         * 剑指 Offer II 059. 数据流的第 K 大数值
         * @param val
         * @return
         */
        public int add(int val) {
            maxList.add(val);
            if (maxList.size()>max)
                maxList.poll();
            return maxList.peek();
        }


        /**
         * 449. 序列化和反序列化二叉搜索树
         */
//        public class Codec {
//
//            // Encodes a tree to a single string.
//            public String serialize(TreeNode root) {
//
//                Queue<TreeNode> queue=new LinkedList();
//                queue.add(root);
//                while (queue.size()!=0){
//                    TreeNode o = queue.peek();
//                    if (o!=null){
//                        queue.add(o.left);
//                        queue.add(o.right);
//                    }
//
//                }
//            }
//
//            private void serialize(TreeNode root,StringBuilder sb){
//
//            }
//
//            // Decodes your encoded data to tree.
//            public TreeNode deserialize(String data) {
//                String[] split = data.split(",");
//                return treeNode(split,0);
//            }
//
//            private TreeNode treeNode(String[] arr,int index){
//                TreeNode node = new TreeNode(Integer.valueOf(arr[index]));
//                int left =index*2+1;
//                int right=index*2+2;
//                if (left<arr.length){
//                    node.left=treeNode(arr,left);
//                }
//                if (right<arr.length){
//                    node.right=treeNode(arr,right);
//                }
//                return node;
//            }
//        }
    }



    public static void main(String[] args) throws UnsupportedEncodingException {
//        KthLargest kl= new Day11().new KthLargest(3,new int[]{4, 5, 8, 2});
//        System.out.println(kl.add(3));
//        System.out.println(kl.add(5));
//        System.out.println(kl.add(10));
//        System.out.println(kl.add(9));
//        System.out.println(kl.add(4));

//        KthLargest kl= new Day11().new KthLargest(1,new int[]{});
//        System.out.println(kl.add(-3));
//        System.out.println(kl.add(-2));
//        System.out.println(kl.add(-4));
//        System.out.println(kl.add(0));
//        System.out.println(kl.add(4));

//        KthLargest kl= new Day11().new KthLargest(2,new int[]{0});
//        System.out.println(kl.add(-1));
//        System.out.println(kl.add(1));
//        System.out.println(kl.add(-2));
//        System.out.println(kl.add(-4));
//        System.out.println(kl.add(3));

//        KthLargest kl= new Day11().new KthLargest(3,new int[]{5,-1});
//        System.out.println(kl.add(2));
//        System.out.println(kl.add(1));
//        System.out.println(kl.add(-1));
//        System.out.println(kl.add(3));
//        System.out.println(kl.add(4));
       // System.out.println(new String(Base64.getEncoder().encode("rKYUwJ2RUvc6I2z6NwE8VCent2v0B72Bt4TScdV+g46xnVmVBp5xkQ=="),"UTF-8"));
        System.out.println(BigDecimal.valueOf(Runtime.getRuntime().freeMemory()).divide(BigDecimal.valueOf(1024*1024))+"mb");
        String str="测试数据：%s";
        System.out.println(String.format(str, "dd"));
    }
}
