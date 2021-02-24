package simple;
import sun.misc.Unsafe;
import javax.script.ScriptException;
import java.util.*;

/**
 * @author 吕宏力
 * @Title： Day09
 * @Package： simple
 * @Description TODO(不知道)
 * @date 2021-01-13 14:20
 * Copyright (c) ©1994-2018 Scjydz.com All Rights Reserved.
 */
public class Day09 {

    /**
     * 面试题 01.03. URL化
     * @param S
     * @param length
     * @return
     */
    public String replaceSpaces(String S, int length) {
        System.out.println(S.charAt(26));
//        StringBuilder sb=new StringBuilder(S.substring(0,length));
//        for (int i = 0; i < sb.length();) {
//            if (sb.charAt(i)==' '){
//                sb.deleteCharAt(i);
//                sb.insert(i,"%20");
//                i+=2;
//            }
//            i++;
//        }
//        return sb.toString();
        char[] charArray = S.toCharArray();
        char[] chars=new char[charArray.length];
        int index=0;
        for (int i = 0;i < index; i++) {
            if (charArray[i]!=' '){
                chars[index++]=charArray[i];
            }else {
                chars[index++]='%';
                chars[index++]='2';
                chars[index++]='0';
            }
        }
        System.out.println(chars.length);
        return new String(chars);
    }




    /**
     * 面试题 01.04. 回文排列
     * @param s
     * @return
     */
    public boolean canPermutePalindrome(String s) {
        int[] arr=new int[128];
        for (char c : s.toCharArray()) {
            arr['a']++;
        }
        int a=0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]%2!=0)
                a++;
        }
        return a<2;
    }


    /**
     * 面试题 01.06. 字符串压缩
     * @param S
     * @return
     */
    public String compressString(String S) {
        if (S.length()==0)
            return S;
        StringBuilder sb=new StringBuilder();
        int slow=0;//慢指针
        char[] chars = S.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[slow]!=chars[i]){
                sb.append(chars[slow]);
                sb.append(i-slow);
                slow=i;
            }
        }
        sb.append(chars[chars.length-1]);
        sb.append(chars.length-slow);
        if (sb.length()<chars.length)
            return sb.toString();
        return S;
    }


    /**
     * 面试题 01.09. 字符串轮转
     * @param s1
     * @param s2
     * @return
     */
    public boolean isFlipedString(String s1, String s2) {
        if (s1.length()!=s2.length())
            return false;
        if (s1.length()==0&&s2.length()==0)
            return true;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i)==s2.charAt(0)){
                if (s2.equals(s1.substring(i)+s1.substring(0,i)))
                    return true;
            }
        }
        return false;
    }


    /**
     * 面试题 02.01. 移除重复节点
     * @param head
     * @return
     */
    public ListNode removeDuplicateNodes(ListNode head) {
        ListNode dummy=head;
        while (dummy!=null&&dummy.next!=null){
            ListNode now=dummy;
            ListNode next=dummy;
            while (next!=null&&next.next!=null){
              if (now.val==next.next.val){
                  next.next=next.next.next;
              }else {
                  next=next.next;
              }
            }
            dummy=dummy.next;
        }

        return head;
    }


    /**
     * 面试题 02.02. 返回倒数第 k 个节点
     * @param head
     * @param k
     * @return
     */
    public int kthToLast(ListNode head, int k) {
        LinkedList<Integer> list = kthTo(head, k);
        return list.pollLast();
    }

    public LinkedList<Integer> kthTo(ListNode head, int k){
        if (head==null){
            return new LinkedList<>();
        }
        LinkedList<Integer> list = kthTo(head.next, k);
        if (list.size()<k){
            list.add(head.val);
        }
        return list;
    }

    /**
     * 面试题 02.06. 回文链表
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        for (int i = 0; i < list.size() / 2; i++) {
            if (!list.get(i).equals(list.get(list.size() - 1 - i)))
                return false;
        }
        return true;
    }


    /**
     * 160. 相交链表
     * 面试题 02.07. 链表相交
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set =new HashSet<>();
        ListNode dummy=headA;
        while (dummy!=null){
            set.add(dummy);
            dummy=dummy.next;
        }
        dummy=headB;
        while (dummy!=null){
            if (set.contains(dummy)){
                return dummy;
            }else {
                dummy=dummy.next;
            }
        }
        return null;
    }


    /**
     * 面试题 03.01. 三合一
     */
    static class TripleInOne {

        private int[] arr;
        private int[] size;
        private int stackSize;
        public TripleInOne(int stackSize) {
            arr=new int[stackSize*3];
            size=new int[3];
            this.stackSize=stackSize;
        }

        public void push(int stackNum, int value) {
            if (size[stackNum]==stackSize){
                return ;
            }
            arr[stackNum*stackSize+size[stackNum]++]=value;
        }

        public int pop(int stackNum) {
            if (size[stackNum]==0){
                return -1;
            }
            return arr[stackNum*stackSize-1+size[stackNum]--];
        }

        public int peek(int stackNum) {
            if (size[stackNum]==0){
                return -1;
            }
            return  arr[stackNum*stackSize-1+size[stackNum]];
        }

        public boolean isEmpty(int stackNum) {
                return size[stackNum]==0;
        }
    }


    /**
     * 面试题 03.02. 栈的最小值
     */
   static class MinStack {

        private int min;
        private Stack<Integer> stack=new Stack<>();
        /** initialize your data structure here. */
        public MinStack() {
            this.min=Integer.MAX_VALUE;

        }

        public void push(int x) {
            if (x<min){
                stack.push(min);
                min=x;
            }else {
                stack.push(min);
            }
            stack.push(x);
        }

        public void pop() {
            if (!stack.empty()){
                    stack.pop();
                    min=stack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }
    }

    /**
     * 面试题 05.06. 整数转换
     * @param A
     * @param B
     * @return
     */
    public int convertInteger(int A, int B) {
        int c=A^B;
        int count=0;
        String s = Integer.toBinaryString(c);
        for (char c1 : s.toCharArray()) {
            if (c1=='1'){
                count++;
            }
        }
        return count;
    }

    /**
     * 面试题 08.01. 三步问题
     * @param n
     * @return
     */
    public int waysToStep(int n) {
        if (n<3){
            return n;
        }
        int[] arr=new int[n+1];
        arr[2]=2;
        arr[1]=1;
        arr[0]=1;
        for (int i = 3; i < arr.length; i++) {
            int s=arr[i-1]+arr[i-2];
            s=s%1000000007;
            s+=arr[i-3];
            s=s%1000000007;
            arr[i]=s;
        }
        return arr[n];
    }

    /**
     * 面试题 08.03. 魔术索引
     * @param nums
     * @return
     */
    public int findMagicIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i==nums[i])
                return i;
        }
        return -1;
    }

    /**
     * 88. 合并两个有序数组
     * 面试题 10.01. 合并排序的数组
     * @param A
     * @param m
     * @param B
     * @param n
     */
    public void merge(int[] A, int m, int[] B, int n) {
        for (int i = 0; i <n; i++) {
            A[m+i]=B[i];
        }
        Arrays.sort(A);
    }

    /**
     * 面试题 10.05. 稀疏数组搜索
     * @param words
     * @param s
     * @return
     */
    public int findString(String[] words, String s) {
        if (words.length==1){
            return words[0].equals(s)?0:-1;
        }
        int len=words.length/2;
        for (int i = 0; i <=words.length/2; i++) {
            if (words[i].equals(s))
                return i;
            if (words[len-1+i].equals(s))
                return len-1+i;
        }
        return -1;
    }

    /**
     * 面试题 16.05. 阶乘尾数
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        int count=0;
        while (n!=0){
            count+=n/5;
            n=n/5;
        }
        return count;
    }

    /**
     * 面试题 16.07. 最大数值
     * @param a
     * @param b
     * @return
     */
    public int maximum(int a, int b) {
        return a>b?a:b;
    }

    /**
     * 面试题 16.11. 跳水板
     * @param shorter
     * @param longer
     * @param k
     * @return
     */
    public int[] divingBoard(int shorter, int longer, int k) {
        if (k==0)
            return new int[0];
        if (shorter==longer){
            return new int[]{shorter*k};
        }
        int[] arr=new int[k+1];
        arr[0]=shorter*k;
        int n=arr[0];
        for (int i = 1; i < k; i++) {
            n=n-shorter+longer;
            arr[i]=n;
        }
        arr[k]=longer*k;
        return arr;
    }


    /**
     * 面试题 17.01. 不用加号的加法
     * @param a
     * @param b
     * @return
     */
    public int add(int a, int b) {
        if ((a&b)==0){
            return a^b;
        }else {
            return add(a^b,(a&b)<<1);
        }
    }


    /**
     * 面试题 17.04. 消失的数字
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
       int[] arr=new int[nums.length+1];
        for (int num : nums) {
            arr[num]=1;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]==0)
                return i;
        }
        return -1;
    }


    /**
     *面试题 17.10. 主要元素
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        int count=0;
        int num=nums[nums.length/2];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]==num){
                count++;
            }
        }
        return count>nums.length/2?num:-1;
    }


    /**
     * 面试题 17.12. BiNode
     * @param root
     * @return
     */
    public TreeNode convertBiNode(TreeNode root) {
        if (root==null){
            return null;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.right=null;
        root.left=null;
        TreeNode node=null;
        if (left!=null){
            node = convertBiNode(left);
            node.left=null;
            TreeNode dumm=node;
            while (dumm.right!=null){
                dumm=dumm.right;
            }
            dumm.right=root;
        }

        if (right!=null){
            root.right= convertBiNode(right);
        }
        if (node==null){
            return root;
        }
        return node;
    }


    /**
     * 面试题 17.16. 按摩师
     * @param nums
     * @return
     */
    public int massage(int[] nums) {
        if(nums.length==0)
            return 0;
        if(nums.length<2)
            return nums[0];
        int[] counts=new int[nums.length+1];
        counts[1]=nums[0];
        counts[2]=Math.max(counts[1],nums[1]);
        for (int i = 2; i < nums.length; i++) {
            counts[i+1]=Math.max(counts[i],counts[i-1]+nums[i]);
        }
        return counts[nums.length];
    }


    /**
     * 28. 实现 strStr()
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }


    /**
     * 38. 外观数列
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        Unsafe.getUnsafe();
        if (n==1){
            return "1";
        }
        String s = countAndSay(n - 1);
        StringBuilder sb=new StringBuilder();
        int slow=0;//慢指针
        int fast=0;//快指针
        for (char c : s.toCharArray()) {
            if (c!=s.charAt(slow)){
                sb.append(fast-slow);
                sb.append(s.charAt(slow));
                slow=fast;
            }
            fast++;
        }
        if (fast-slow>0){
            sb.append(fast-slow);
            sb.append(s.charAt(fast-1));
            slow=fast;
        }
        return sb.toString();
    }


    /**
     * 58. 最后一个单词的长度
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        char[] chars = s.toCharArray();
        int slow=chars.length-1;//慢
        int fast=chars.length-1;//快
        int max=0;
        for (int i = chars.length-1; i >=0; i--) {
            if (chars[i]==' '){
                if (chars[slow]!=' ')
                    max=Math.max(max,slow-fast);
                else
                    max=Math.max(max,slow-1-fast);
                slow=fast;
                if (max>0)
                    return max;
            }
            fast--;
        }
        if (chars.length>0&&chars[slow]!=' ')
            slow++;
        max=Math.max(max,slow-1-fast);
        return max;
    }


    /**
     * 121. 买卖股票的最佳时机
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int max=0;
        PriorityQueue<Integer> queue = new PriorityQueue();
        for (int price : prices) {
            queue.add(price);
        }
        for (int i = prices.length-1; i >0; i--) {
            if (prices[i]==queue.peek()){
                queue.poll();
            }else {
                max=Math.max(max,prices[i]-queue.peek());
            }
        }
        return max;
    }


    /**
     * 167. 两数之和 II - 输入有序数组
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int left=0;
        int right=numbers.length-1;
        while (left<right){
            int sum = numbers[left] + numbers[right];
            if (sum==target){
                return new int[]{left+1,right+1};
            }else if (sum<target)
                left++;
            else
                right--;
        }
        return null;
    }

    /**
     * 168. Excel表列名称
     * @param n
     * @return
     */
    public String convertToTitle(int n) {
        StringBuilder sb=new StringBuilder();
        while (n>0){
            if (n%26==0){
                sb.insert(0,get(26));
                n-=26;
            }else {
                int s=n%26;
                sb.insert(0,get(s));

            }
            n=n/26;
        }
        return sb.toString();
    }

    private String get(int c){
        return String.valueOf((char)(64+c));
    }


    /**
     * 171. Excel表列序号
     * @param s
     * @return
     */
    public int titleToNumber(String s) {
        int sum=0;
        int jin=1;
        char[] chars = s.toCharArray();
        for (int i = chars.length-1; i >=0; i--) {
            sum+=((chars[i]-'@')*jin);
            jin*=26;
        }
        return sum;
    }


    public static void main(String[] args) throws ScriptException {
        System.out.println(new Day09().titleToNumber("ZY"));
    }
}
