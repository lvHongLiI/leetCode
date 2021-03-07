package simple;

import java.util.LinkedList;
import java.util.TreeSet;

public class Day09 {

    /**
     * 面试题 03.04. 化栈为队
     */
    class MyQueue {
        private LinkedList<Integer> queue;
        /** Initialize your data structure here. */
        public MyQueue() {
            queue=new LinkedList<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            queue.add(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            return queue.pollFirst();
        }

        /** Get the front element. */
        public int peek() {
            return queue.peekFirst();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return queue.isEmpty();
        }
    }

    /**
     * 面试题 04.04. 检查平衡性
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root==null)
            return true;
        try {
           return Math.abs(isBalan(root.left)-isBalan(root.right))<2;
        }catch (Exception e){
            return false;
        }
    }

    private int isBalan(TreeNode root) {
        if (root==null)
            return 0;
        int abs = Math.max(isBalan(root.right) , isBalan(root.left));
        if (abs>1){
            throw  new RuntimeException("");
        }
        return abs+1;
    }


    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        TreeNode nodef = new TreeNode(2);
        TreeNode noder = new TreeNode(2);
        TreeNode nodeff = new TreeNode(3);
        TreeNode nodefr = new TreeNode(3);
        TreeNode nodefff = new TreeNode(4);
        TreeNode nodeffr = new TreeNode(4);
        node.left=nodef;
        node.right=noder;
        nodef.left=nodeff;
        nodef.right=nodefr;
        nodeff.left=nodefff;
        nodeff.right=nodeffr;
        System.out.println(new Day09().isBalanced(node));

    }
}
