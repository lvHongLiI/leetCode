package simple;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 吕宏力
 * @Date: 2021/08/27/10:28
 * @Description:
 */
public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}