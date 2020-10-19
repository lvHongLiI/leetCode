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


    /**
     * 762. 二进制表示中质数个计算置位
     * @param L
     * @param R
     * @return
     */
    public int countPrimeSetBits(int L, int R) {
        int sum=0;
        for (int i = L; i <=R; i++) {
            if (binaryCount(i)){
                sum++;
            }
        }
        return sum;
    }


    private boolean binaryCount(int i) {
        int count=0;
        while (i>0){
            count+=i&1;
            i=i>>1;
        }
        if (count>1){
            int sqrt = (int) Math.sqrt(count);
            for (int j =2; j <=sqrt; j++) {
                if (count%j==0)
                    return false;
            }
            return true;
        }
        return false;
    }

    /**
     * 771. 宝石与石头
     * @param J
     * @param S
     * @return
     */
    public int numJewelsInStones(String J, String S) {
        Map<Character,Integer> map=new HashMap<>();
        char[] chars = S.toCharArray();
        for (char c : chars) {
            Integer integer = map.get(c);
            if (integer==null){
                map.put(c,1);
            }else {
                map.put(c,integer+1);
            }
        }
        int j=0;
        for (char c : J.toCharArray()) {
            Integer integer = map.get(c);
            if (integer!=null)
                j+=integer;
        }
        return j;
    }

    /**
     * 788. 旋转数字
     * @param N
     * @return
     */
    public int rotatedDigits(int N) {
        int sum=0;
        for (int i = 1; i <= N; i++) {
            try {
                if (goodNumbers(i)!=i)
                    sum++;
            }catch (Exception e){

            }
        }
        return sum;
    }
    private int goodNumbers(int number) {

            int num=number%10;
            if (num==2){
                num=5;
            }else if (num==5){
                num=2;
            }else if (num==6){
                num=9;
            }else if (num==9){
                num=6;
            }else if (num==3||num==4||num==7){
                throw new RuntimeException();
            }
            if (number>=10)
             return goodNumbers(number/10)*10+num;
            return num;


    }

    /**
     * 796. 旋转字符串
     * @param A
     * @param B
     * @return
     */
    public boolean rotateString(String A, String B) {
        if (A==null||B==null)
            return false;
        if (A.length()!=B.length())
            return false;
        if (!A.equals(B)){
            char[] a = A.toCharArray();
            char[] b = B.toCharArray();
            a:for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < b.length; j++) {
                    if (a[(i+j)%a.length]!=b[j])
                        continue a;
                }
                return true;
            }
            return false;
        }
        return true;
    }
    

    public static void main(String[] args) {

        boolean balanced = new Day04().rotateString("abcde","abced");
        System.out.println(balanced);
    }
}
