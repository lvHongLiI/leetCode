package secondary;

import org.omg.CORBA.INTERNAL;
import simple.ListNode;
import simple.TreeNode;

import java.util.*;


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
     * 199. 二叉树的右视图
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list=new ArrayList<>();
        rightSideView(root,list);
        return list;
    }

    private void rightSideView(TreeNode root,List<Integer> list) {
            if (root==null)
                return;
            list.add(root.val);
            if (root.right!=null)
                rightSideView(root.right,list);
            else
                rightSideView(root.left,list);
    }



    public static void main(String[] args) {
    }
}
