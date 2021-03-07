package simple;

import java.util.*;
import java.util.concurrent.*;

/**
 * 每个类 30题
 */
public class Day08 {
    /**
     * 剑指 Offer 11. 旋转数组的最小数字
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i]<numbers[i-1]){
                return numbers[i];
            }
        }
        return numbers[0];
    }


    /**
     * 剑指 Offer 15. 二进制中1的个数
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int count = 0;
        int i = 0;
        while (i++ < 32) {
            count += n & 1;
            n >>= 1;
        }
        return count;
    }


    /**
     * 70. 爬楼梯
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n<2){
            return 1;
        }
        int[] arr=new int[n+1];
        arr[1]=1;
        arr[0]=1;
        for (int i = 2; i < arr.length; i++) {
            arr[i]=arr[i-1]+arr[i-2];

        }
        return arr[n];
    }

    /**
     * 剑指 Offer 17. 打印从1到最大的n位数
     * @param n
     * @return
     */
    public int[] printNumbers(int n) {
        int len=0;
        while (n>0){
            len=len*10+9;
            n--;
        }
        int arr[]=new int[len];
        for (int i = 1; i <=arr.length; i++) {
            arr[i-1]=i;
        }
        return arr;
    }


    /**
     * 剑指 Offer 18. 删除链表的节点
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode(ListNode head, int val) {
        if (head==null)
            return head;
        head.next=deleteNode(head.next,val);
        if (head.val==val)
            return head.next;
        return head;
    }


    /**
     * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
     * @param nums
     * @return
     */
    public int[] exchange(int[] nums) {
        int left=0;
        int right=nums.length-1;
        while (left<right){
            while (left<right){
                if (nums[left]%2==0)
                    break;
                left++;
            }

            while (right>left){
                if (nums[right]%2==1)
                    break;
                right--;
            }

            if (left<right){
                int tmp=nums[left];
                nums[left]=nums[right];
                nums[right]=tmp;
            }
        }
        return nums;
    }

    /**
     * 剑指 Offer 22. 链表中倒数第k个节点
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
       ListNode dum=head;
       int count=0;
       while (dum!=null){
           count++;
           dum=dum.next;
       }
       if (count==0)
           return null;

       while (head!=null){
           if (count==k)
               return head;
           count--;
           head=head.next;
       }
       return head;
    }

    /**
     * 226. 翻转二叉树
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root==null)
            return null;
        root.left=invertTree(root.left);
        root.right=invertTree(root.right);
        TreeNode tmp=root.left;
        root.left=root.right;
        root.right=tmp;
        return root;
    }



    /**
     *     剑指 Offer 27. 二叉树的镜像
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root==null)
            return null;
        root.left=mirrorTree(root.left);
        root.right=mirrorTree(root.right);
        TreeNode tmp=root.left;
        root.left=root.right;
        root.right=tmp;
        return root;
    }
    /**
     * 剑指 Offer 24. 反转链表
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode curr = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = curr;
            curr = head;
            head = next;
        }
        return curr;
    }

    /**
     *  剑指 Offer 25. 合并两个排序的链表
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        while (l2 != null) {
            l1 = mergeTwo(l1, new ListNode(l2.val));
            l2 = l2.next;
        }
        return l1;
    }

    private ListNode mergeTwo(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2.val > l1.val) {
            l1.next = mergeTwo(l1.next, l2);
        } else {
            l2.next = l1;
            l1 = l2;
        }
        return l1;
    }


    /**
     * 101. 对称二叉树
     * 剑指 Offer 28. 对称的二叉树
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root==null)
            return true;

        TreeNode left=mirrorTree(root.left);
        TreeNode right = root.right;
        return isSymmetric(left,right);
    }
    private boolean isSymmetric(TreeNode left,TreeNode  right) {
        if (left==null)
            return right==null;
        if (right==null)
            return left==null;
        boolean b = isSymmetric(left.left, right.left) && isSymmetric(left.right, right.right);
        if (b){
            return left.val==right.val;
        }
        return b;
    }


    /**
     * 剑指 Offer 29. 顺时针打印矩阵
     * @param matrix
     * @return
     */
    public int[] spiralOrder(int[][] matrix) {
            if (matrix==null||matrix.length==0)
                return null;
            int[] arr=new int[matrix.length*matrix[0].length];
            int len=0;
            int hangLeft=0;
            int hangRight=matrix[0].length-1;
            int lieLeft=0;
            int lieRight=matrix.length-1;
            while (hangLeft-hangRight<=1||lieLeft-lieRight<=1){
                for (int i = hangLeft; i <=hangRight; i++) {
                    if (len>=arr.length)
                        break;
                    arr[len++]=matrix[lieLeft][i];
                }
                lieLeft++;
                for (int i = lieLeft; i <=lieRight; i++) {
                    if (len>=arr.length)
                        break;
                    arr[len++]=matrix[i][hangRight];
                }
                hangRight--;
                for (int i = hangRight; i >=hangLeft; i--) {
                    if (len>=arr.length)
                        break;
                    arr[len++]=matrix[lieRight][i];
                }
                lieRight--;
                for (int i = lieRight; i >=lieLeft; i--) {
                    if (len>=arr.length)
                        break;
                    arr[len++]=matrix[i][hangLeft];
                }
                hangLeft++;
            }

            return arr;
    }

    /**
     * 155. 最小栈
     * 剑指 Offer 30. 包含min函数的栈
     */
    class MinStack {

        int[] arr;
        int size;
        int pushIndex;
        int min;
        /** initialize your data structure here. */
        public MinStack() {
            arr=new int[20000];
        }

        public void push(int x) {
            arr[pushIndex++]=x;
            size++;
            if (min>x)
                min=x;
        }

        public void pop() {
            if (size>0){
                pushIndex--;
                size--;
            }
        }

        public int top() {
            if (size>0){
                return arr[pushIndex-1];
            }else {
                return -1;
            }
        }

        public int min() {
            return min;
        }
    }

    /**
     * 剑指 Offer 39. 数组中出现次数超过一半的数字
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }


    /**
     * 剑指 Offer 40. 最小的k个数
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        Arrays.sort(arr);
        int[] ks=new int[k];
        for (int i = 0; i < ks.length; i++) {
            ks[i]=arr[i];
        }
        return ks;
    }

    /**
     * 剑指 Offer 32 - II. 从上到下打印二叉树 II
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
     * 剑指 Offer 53 - I. 在排序数组中查找数字 I
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int count=0;
        int left=0;
        int right=nums.length;
        while (left<right){
            int mid= left+(right-left)/2;
            if (nums[mid]>target){//错误版本
                right=mid;
            }else if (nums[mid]<target){//正确版本
                left=mid+1;
            }else {
                count=1;
                int l=mid-1;
                int r=mid+1;
                while (l>-1){
                    if (nums[l]!=target)
                        break;
                    l--;
                    count++;
                }
                while (r<nums.length){
                    if (nums[r]!=target)
                        break;
                    r++;
                    count++;
                }
                break;
            }
        }
        return count;
    }


    /**
     * 剑指 Offer 53 - II. 0～n-1中缺失的数字
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int last=-1;
        for (int i = 0; i < nums.length; i++) {
            if (i>0)
                last=nums[i-1];
            if (nums[i]!=last+1)
                return last+1;
        }
        return nums.length;
    }


    /**
     * 剑指 Offer 54. 二叉搜索树的第k大节点
     * @param root
     * @param k
     * @return
     */
    public int kthLargest(TreeNode root, int k) {
        List<Integer> list=new ArrayList<>();
        search(root,list);
        return list.get(list.size()-k);
    }

    private void  search(TreeNode root,List<Integer> list){
        if (root==null)
            return;
        search(root.left,list);
        list.add(root.val);
        search(root.right,list);
    }

    /**
     * 剑指 Offer 55 - I. 二叉树的深度
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        return maxDepth(root,0);
    }

    private int maxDepth(TreeNode treeNode,int length){
        if (treeNode==null)
            return length;
        int left=maxDepth(treeNode.left,length+1);
        int right=maxDepth(treeNode.right,length+1);
        return Math.max(left,right);
    }


    /**
     * 剑指 Offer 55 - II. 平衡二叉树
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root==null)
            return true;
        boolean left = isBalanced(root.left);
        boolean right = isBalanced(root.right);
        if (right&&left)
            return Math.abs(isBed(root.left)-isBed(root.right))<2;
        return false;
    }

    private int isBed(TreeNode node){
        if (node==null)
            return 0;
        return Math.max(isBed(node.left),isBed(node.right))+1;
    }


    /**
     * 剑指 Offer 57. 和为s的两个数字
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] arr=new int[1000001];
        for (int num : nums) {
            if (target<num)
                continue;
            int x = target - num;
            if (arr[x]!=0)
                return new int[]{x,num};
            else
                arr[num]=1;
        }
        return null;
    }


    /**
     * 剑指 Offer 58 - I. 翻转单词顺序
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
        return sb.toString()+"**";
    }


    /**
     * 剑指 Offer 58 - II. 左旋转字符串
     * @param s
     * @param n
     * @return
     */
    public String reverseLeftWords(String s, int n) {
       char[] chars=new char[s.length()];
        n=n%s.length();
        if (n==0)
            return s;
        for (int i = 0; i < s.length(); i++) {
            chars[i]=s.charAt((i+n)%s.length());
        }
        return new String(chars);

    }
    public static void main(String[] args) {
//        0 0
//        1  2  3  4
//        5  6  7  8
//        9  10 11 12
//        13 14 15 16
        ScheduledThreadPoolExecutor executor=new ScheduledThreadPoolExecutor(1);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis()+"当前线程是：" + Thread.currentThread().getName());
            }
        };
        for (int i = 0; i < 1000; i++) {
            executor.execute(runnable);
        }
    }
}
