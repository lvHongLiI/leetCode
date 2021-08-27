package secondary;


import simple.ListNode;
import simple.TreeNode;
import sun.misc.Unsafe;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
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


    /**
     * 300. 最长递增子序列
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
            int max=0;
            int cur=1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i]>nums[i-1]){
                cur++;
                max=Math.max(max,cur);
            }else {
                cur=1;
            }
        }
        return max;
    }


    private void preorderTraversal(TreeNode root,List<Integer> list) {
        if (root==null)
            return;
        preorderTraversal(root.left,list);
        list.add(root.val);
        preorderTraversal(root.right,list);
    }


    /**
     * 面试题 04.05. 合法二叉搜索树
     * 98. 验证二叉搜索树
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        List<Integer> list=new LinkedList();
        validBST(root,list);
        if (list.size()<2)
            return true;
        Iterator<Integer> iterator = list.iterator();
         int last=iterator.next();
         while (iterator.hasNext()){
             Integer next = iterator.next();
             if (last>=next)
                 return false;
             last=next;
         }
         return true;
    }


    private void validBST(TreeNode root,List<Integer> list){
        if (root==null)
            return;
        validBST(root.left,list);
        list.add(root.val);
        validBST(root.right,list);
    }


    /**
     * -2 -1 0 1 2 3 4 5 6 7
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        //1.先排波序
        Arrays.sort(nums);
        List<List<Integer>> list=new LinkedList<>();
        int len=0;
        while (len<nums.length-2){
            //1.初始化两个变量
            int left=len+1;
            int right=nums.length-1;
            //如果后一个数和当前数已有则跳过
            if (len>0&&nums[len]==nums[len-1]){
                len++;
                continue;
            }

            while (left<right){
                int i = nums[len] + nums[right] + nums[left];
                if (i==0){
                    list.add(Arrays.asList(nums[len],nums[right] ,nums[left]));
                    left++;
                    right--;
                    while (left<right&&nums[left-1]==nums[left]){
                        left++;
                    }
                    while (left<right&&nums[right+1]==nums[right]){
                        right--;
                    }
                }else if (i<0){
                    left++;
                }else {
                    right--;
                }
            }
            len++;
        }
        return list;
    }


    /**
     * 剑指 Offer II 055. 二叉搜索树迭代器
     * 173. 二叉搜索树迭代器
     */
    class BSTIterator {

        private LinkedList<Integer> list;

        public BSTIterator(TreeNode root) {
            LinkedList<Integer> linkedList = new LinkedList<>();
            test(root,linkedList);
            list=linkedList;
        }

        private void test(TreeNode root,List<Integer> list){
            if (root==null)
                return;
            test(root.left,list);
            list.add(root.val);
            test(root.right,list);
        }

        public int next() {
            return list.pollFirst();
        }

        public boolean hasNext() {
            return !list.isEmpty();
        }
    }


    /**
     * 230. 二叉搜索树中第K小的元素
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
       return test(root, k, new AtomicInteger(0));
    }

    private int test(TreeNode root, int k, AtomicInteger integer){
        if (root.left!=null){
            int num = test(root.left, k, integer);
            if (num!=0)
                return num;
        }

        if (integer.addAndGet(1)==k)
            return root.val;

        if (root.right!=null){
            int num = test(root.right, k, integer);
            if (num!=0)
                return num;
        }

        return 0;
    }


    /**
     * 1038. 把二叉搜索树转换为累加树
     * 538. 把二叉搜索树转换为累加树
     * 剑指 Offer II 054. 所有大于等于节点的值之和
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        LinkedList<TreeNode> list=new LinkedList<>();
        cent(root,list);
        int sum=0;
        TreeNode dummy=null;
        if ((dummy=list.pollLast())!=null){
            int val = dummy.val;
            dummy.val+=sum;
            sum+=val;
        }
        return root;
    }


    private void cent(TreeNode root,List<TreeNode> list){
        if (root==null)
            return;
        cent(root.left,list);
        list.add(root);
        cent(root.right,list);
    }



    /**
     * 450. 删除二叉搜索树中的节点
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root==null)
            return null;
        if (root.val>key){
            root.left=deleteNode(root.left,key);
            return root;
        }

        if (root.val<key){
             root.right=deleteNode(root.right,key);
             return root;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        if (left==null)
            return right;
        if (right==null)
            return left;
        TreeNode treeNode = minNode(right);
        treeNode.right=deleteMinNode(right);
        treeNode.left=left;
        return treeNode;

    }


    public TreeNode minNode(TreeNode treeNode){
        if (treeNode.left==null)
            return treeNode;
        return minNode(treeNode.left);
    }

    public TreeNode deleteMinNode(TreeNode treeNode){
        if (treeNode.left==null)
            return treeNode.right;
        treeNode.left= deleteMinNode(treeNode.left);
        return treeNode;
    }


    /**
     * 114. 二叉树展开为链表
     * @param root
     */
    public void flatten(TreeNode root) {
        List<Integer> list=new ArrayList<>();
        increasingBST(root,list);
        Iterator<Integer> iterator = list.iterator();
        if (list.size()<1){
            return;
        }
        root.val=iterator.next();
        root.left=null;
        TreeNode dummy=root;
        while (iterator.hasNext()){
            dummy.right=new TreeNode(iterator.next());
            dummy=dummy.right;
        }
    }
    private void increasingBST(TreeNode root, List<Integer> list) {
        if (root==null)
            return;
        list.add(root.val);
        increasingBST(root.left,list);
        increasingBST(root.right,list);
    }



    /**
     * 109. 有序链表转换二叉搜索树
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head!=null){
            list.add(head.val);
            head=head.next;
        }
        return sortedArrayToBST(list);
    }

    public TreeNode sortedArrayToBST(List<Integer> nums) {
        int cent = nums.size() / 2;
        TreeNode root = new TreeNode(nums.get(cent));
        root.left=sortedArrayToBST(0,cent-1,nums);
        root.right=sortedArrayToBST(cent+1,nums.size()-1,nums);
        return root;
    }

    private TreeNode sortedArrayToBST(int left,int right,List<Integer> nums){
        if (left>right)
            return null;
        int cent = (right-left)/2+left;
        TreeNode treeNode = new TreeNode(nums.get(cent));
        treeNode.left=sortedArrayToBST(left,cent-1,nums);
        treeNode.right=sortedArrayToBST(cent+1,right,nums);
        return treeNode;
    }


    /**
     * 面试题 04.06. 后继者
     * 剑指 Offer II 053. 二叉搜索树中的中序后继
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        ArrayList<TreeNode> list = new ArrayList<>();
        inorderSuccessor(root,list);
        Iterator<TreeNode> iterator = list.iterator();
        while (iterator.hasNext()){
            if (iterator.next()==p){
                if (iterator.hasNext()){
                    return iterator.next();
                }
                return null;
            }
        }
        return null;
    }

    private void inorderSuccessor(TreeNode root,List<TreeNode> list){
        if (root==null)
            return;
        inorderSuccessor(root.left,list);
        list.add(root);
        inorderSuccessor(root.right,list);
    }



    public static void main(String[] args) {
      System.out.println(new Day01().addTwoNumbers(new ListNode(7,new ListNode(2,new ListNode(4,new ListNode(4)))),new  ListNode(5,new ListNode(6,new ListNode(4)))));
    }

}
