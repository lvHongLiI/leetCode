package secondary;

import simple.ListNode;
import simple.TreeNode;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *每个类 30题
 * @Author: 吕宏力
 * @Date: 2021/08/19/16:25
 * @Description:
 */
public class Day02 {

    /**
     * 701. 二叉搜索树中的插入操作
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root==null)
            return new TreeNode(val);
        if (val<root.val){
            root.left=insertIntoBST(root.left,val);
        }
        if (val>root.val){
            root.right=insertIntoBST(root.right,val);
        }
        return root;
    }


    /**
     * 1305. 两棵二叉搜索树中的所有元素
     * @param root1
     * @param root2
     * @return
     */
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        LinkedList<Integer> queue = new LinkedList<>();
        getAllElements(root1,queue);
        getAllElements(root2,queue);
        Collections.sort(queue);
        return queue;
    }

    private void getAllElements(TreeNode root, LinkedList<Integer> list){
        if (root==null)
            return;
        getAllElements(root.left,list);
        list.add(root.val);
        getAllElements(root.right,list);
    }


    /**
     *1382. 将二叉搜索树变平衡
     * @param root
     * @return
     */
    public TreeNode balanceBST(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        balanceBST(root,list);
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

    private void balanceBST(TreeNode root,List<Integer> list){
        if (root==null)
            return;
        balanceBST(root.left,list);
        list.add(root.val);
        balanceBST(root.right,list);
    }


    /**
     * 剑指 Offer II 021. 删除链表的倒数第 n 个结点
     * 19. 删除链表的倒数第 N 个结点
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode[] list=new ListNode[30];
        int len=0;
        ListNode dummy=head;
        while (dummy!=null){
            list[len++]=dummy;
            dummy=dummy.next;
        }
        int i = len - n;
        if (i-1<0){
            return list[i+1];
        }else {
            ListNode listNode = list[i- 1];
            listNode.next=listNode.next.next;
        }
        return head;
    }


    /**
     * 24. 两两交换链表中的节点
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        //1.前一个节点
        List<ListNode> oneList=new ArrayList<>();
        //2.后一个节点
        List<ListNode> towList=new ArrayList<>();
        //3.存储节点
        int number=0;
        ListNode dummy=head;
        while (dummy!=null){
            ListNode one=dummy;
            dummy=dummy.next;
            if (dummy==null){
                number++;
                oneList.add(one);
                break;
            }
            ListNode tow=dummy;
            oneList.add(one);
            towList.add(tow);
            number+=2;
            dummy=dummy.next;
        }
        if (number<2)
            return head;
        //4.交换
        for (int i = 0; i < towList.size(); i++) {
            towList.get(i).next=null;
            oneList.get(i).next=null;
            towList.get(i).next=oneList.get(i);
            if (i+1<towList.size()){
                oneList.get(i).next=towList.get(i+1);
            }else if (i+1<oneList.size()){
                oneList.get(i).next=oneList.get(i+1);
            }
        }
        //5.返回
        return towList.get(0);
    }


    /**
     * 剑指 Offer II 026. 重排链表
     * 143. 重排链表
     * @param head
     */
    public void reorderList(ListNode head) {
        //1.前一个节点
        List<ListNode> oneList=new ArrayList<>();
        //2.后一个节点
        List<ListNode> towList=new ArrayList<>();
        //3.存储节点
        int number=0;
        ListNode dummy=head;
        while (dummy!=null){
            oneList.add(dummy);
            number++;
            dummy=dummy.next;
        }

        if (number<2)
            return;
        int len = oneList.size() / 2;
        while (len!=0){
            towList.add(oneList.remove(oneList.size()-1));
            len--;
        }
        //4.交换
        for (int i = 0; i < towList.size(); i++) {
            towList.get(i).next=null;
            oneList.get(i).next=null;
            oneList.get(i).next=towList.get(i);
            if (i+1<oneList.size()){
                ListNode listNode = oneList.get(i + 1);
                listNode.next=null;
                towList.get(i).next=listNode;
            }
        }
    }


    /**
     * 725. 分隔链表
     * @param head
     * @param k
     * @return
     */
    public ListNode[] splitListToParts(ListNode head, int k) {
        List<ListNode> list=new ArrayList<>();
        ListNode dummy=head;
        while (dummy!=null){
            list.add(dummy);
            dummy=dummy.next;
        }
        ListNode[] nodes = new ListNode[k];
        int step=0;
        int remainder=0;
        if (list.size()<k){
             step=1;
        }else {
            step=list.size()/k;
            remainder=list.size()%k;
        }
        int len=0;
        int index=0;
        while (len<list.size()){
            ListNode listNode = list.get(len);
            nodes[index]=listNode;
            len+=step;
            if (index==0){
                len+=remainder;
            }
            list.get(len-1).next=null;
            index++;
        }
        return nodes;
    }


    /**
     * 剑指 Offer II 022. 链表中环的入口节点
     * 142. 环形链表 II
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set=new HashSet<>();
        while (head!=null){
            if (set.contains(head)){
                return head;
            }
            set.add(head);
            head=head.next;
        }
        return null;
    }


    /**
     * 92. 反转链表 II
     * @param head
     * @param left 1 2 3 4 5
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left==right)
            return head;
        List<ListNode> list = new ArrayList<>();
        while (head!=null){
           list.add(head);
           head=head.next;
        }
        if (list.size()==0){
            return null;
        }
        while (left<right){
            ListNode tmp = list.get(left - 1);
            list.set(left-1,list.get(right-1));
            list.set(right-1,tmp);
            left++;
            right--;
        }
        ListNode lastNode = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            ListNode listNode = list.get(i);
            listNode.next=null;
            lastNode.next=listNode;
            lastNode=listNode;

        }
        return list.get(0);
    }


    /**
     * 445. 两数相加 II
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ArrayList<Integer> listl1 = new ArrayList<>();
        ArrayList<Integer> listl2 = new ArrayList<>();
        addTwoNumber(l1,listl1);
        addTwoNumber(l2,listl2);
        int l1len=listl1.size()-1;
        int l2len=listl2.size()-1;
        int carry=0;
        ListNode lastNode=null;
        while (l1len>=0||l2len>=0){
            int l1Number=0;
            int l2Number=0;
            if (l1len>=0){
                l1Number=listl1.get(l1len--);
            }
            if (l2len>=0){
                l2Number=listl2.get(l2len--);
            }
            int number=(l1Number+l2Number+carry)%10;
            carry=(l1Number+l2Number+carry)/10;
            ListNode listNode = new ListNode(number);
            if (lastNode!=null){
                listNode.next=lastNode;
            }
            lastNode=listNode;
        }
        if (carry!=0){
           return new ListNode(carry,lastNode);
        }
        return lastNode;
    }

    private void addTwoNumber(ListNode listNode,List<Integer> list){
        if (listNode==null){
            return;
        }
        list.add(listNode.val);
        addTwoNumber(listNode.next,list);
    }


    /**
     * 1721. 交换链表中的节点
     * @param head
     * @param k
     * @return
     */
    public ListNode swapNodes(ListNode head, int k) {
        List<ListNode> list = new ArrayList<>();
        swapNodes(head,list);
        ListNode front=list.get(k-1);
        ListNode back=list.get(list.size()-k);
        int val = front.val;
        front.val=back.val;
        back.val=val;
        return list.get(0);
    }


    private void swapNodes(ListNode head,List<ListNode> list){
        if (head==null)
            return;
        list.add(head);
        swapNodes(head.next,list);

    }


    /**
     * 1669. 合并两个链表
     * @param list1
     * @param a
     * @param b
     * @param list2
     * @return
     */
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        List<ListNode> listNode1 = new ArrayList<>();
        mergeInBetween(list1,listNode1);
        if (a==0&&b==listNode1.size()-1)
            return list2;
        ListNode result=null;
        if (a == 0) {
            result=list2;
        }else {
            result=listNode1.get(0);
            listNode1.get(a-1).next=list2;
        }
        if (b==listNode1.size()-1){
            return result;
        }else {
            while (list2.next!=null){
                list2=list2.next;
            }
            list2.next=listNode1.get(b+1);
        }
        return result;
    }

    private void  mergeInBetween(ListNode head,List<ListNode> list){
        if (head==null)
            return;
        list.add(head);
        swapNodes(head.next,list);

    }


    /**
     * 面试题 04.03. 特定深度节点链表
     * @param tree
     * @return
     */
    public ListNode[] listOfDepth(TreeNode tree) {
        ArrayList<List<Integer>> list = new ArrayList<>();
        listOfDepth(tree,0,list);
        ListNode[] listNodes = new ListNode[list.size()];
        for (int i = 0; i < list.size(); i++) {
            List<Integer> integerList = list.get(i);
            ListNode node = new ListNode(integerList.get(0));
            listNodes[i]=node;
            for (int j = 1; j < integerList.size(); j++) {
                node.next=new ListNode(integerList.get(j));
                node=node.next;
            }
        }
        return listNodes;
    }

    private void listOfDepth(TreeNode tree,int level,List<List<Integer>> list){
        if (tree==null){
            return;
        }
        if (list.size()<level+1){
            list.add(new ArrayList<>());
        }
        List<Integer> list1 = list.get(level);
        if (list1==null){
            list1=new ArrayList<>();
            list.set(level,list1);
        }
        list1.add(tree.val);
        listOfDepth(tree.left,level+1,list);
        listOfDepth(tree.right,level+1,list);
    }


    /**
     * 剑指 Offer II 077. 链表排序
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        List<Integer> list=new ArrayList<>();
        while (head!=null){
            list.add(head.val);
            head=head.next;
        }
        if (list.size()==0)
            return null;
        Collections.sort(list);
        ListNode result = new ListNode(list.get(0));
        ListNode dummy=result;
        for (int i = 1; i < list.size(); i++) {
            dummy.next=new ListNode(list.get(i));
            dummy=dummy.next;
        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println(new Day02().swapNodes(new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5))))),2));
        System.out.println();
    }

}
