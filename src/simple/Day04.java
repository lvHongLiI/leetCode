package simple;

import java.util.HashMap;
import java.util.Map;

public class Day04 {
    /**
     * 83. 删除排序链表中的重复元素
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy=head;
        while (head!=null&&head.next!=null){
            if (head.val==head.next.val){
                head.next=head.next.next;
            }else {
                head=head.next;
            }

        }
        return dummy;
    }

    /**
     * 13. 罗马数字转整数
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        int sum=0;
        if (s==null||s.length()==0)
            return sum;
        Map<Character,Integer> map=new HashMap<>();
        map.put('I',            1);
        map.put('V',            5);
        map.put('X',            10);
        map.put('L',            50);
        map.put('C',            100);
        map.put('D',            500);
        map.put('M',            1000);
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i!=chars.length-1){
                if (map.get(chars[i])<map.get(chars[i+1])){
                    sum-=map.get(chars[i]);
                    continue;
                }
            }
            sum+=map.get(chars[i]);
        }
        return sum;
    }

    /**
     * 110. 平衡二叉树
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



    public static void main(String[] args) {
    TreeNode node=new TreeNode(3);
    TreeNode nodel=new TreeNode(9);
    TreeNode noder=new TreeNode(20);
    TreeNode noderl=new TreeNode(15);
    TreeNode noderr=new TreeNode(7);
    noder.right=noderr;
    noder.left=noderl;
    node.left=nodel;
    node.right=noder;
        boolean balanced = new Day04().isBalanced(node);
        System.out.println(balanced);
    }
}
