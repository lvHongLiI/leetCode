package simple;

import java.util.*;
import java.util.stream.Collectors;
/**
 * 每个类 30题
 */
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


    /**
     * 804. 唯一摩尔斯密码词
     * @param words
     * @return
     */
    public int uniqueMorseRepresentations(String[] words) {
        String[] map=new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        Set<String> set=new HashSet<>();
        StringBuilder sb=null;
        for (String word : words) {
            sb=new StringBuilder();
            for (char c : word.toCharArray()) {
                sb.append(map[c-'a']);
            }
            set.add(sb.toString());
        }
        return set.size();
    }


    /**
     * 806. 写字符串需要的行数
     * @param widths
     * @param S
     * @return
     */
    public int[] numberOfLines(int[] widths, String S) {
        int sum=0;
        int row=0;
        for (char c : S.toCharArray()) {
            int s=widths[c-'a'];
            if (sum+s<=100){
                sum+=s;
            }else {
                sum=0;
                sum+=s;
                row++;
            }
        }
        return new int[]{row+1,sum};
    }


    /**
     * 811. 子域名访问计数
     * @param cpdomains
     * @return
     */
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String,Integer> map=new HashMap<>();
        for (String cpdomain : cpdomains) {
            String[] split = cpdomain.split("\\s+");
            int num=Integer.valueOf(split[0]);
            String s=split[1];
            while (s!=null){
                Integer integer = map.get(s);
                if (integer==null){
                    map.put(s,num);
                }else {
                    map.put(s,num+integer);
                }
                int i = s.indexOf('.');
                if (i==-1){
                    s=null;
                }else {
                    s=s.substring(i+1);
                }
            }
        }
        List<String> ans = new ArrayList();
        for (String dom: map.keySet())
            ans.add("" + map.get(dom) + " " + dom);
        return ans;
    }

    /**
     * 821. 字符的最短距离
     * @param S
     * @param C
     * @return
     */
    public int[] shortestToChar(String S, char C) {
        char[] chars = S.toCharArray();
        int[] arr=new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            if (!(chars[i]==C)){
                    int left=i-1;
                    int right=i+1;
                    while (left>=0||right<chars.length){
                        if (left>=0){
                            if (chars[left]==C){
                                arr[i]=Math.abs(i-left);
                                break;
                            }
                            left--;
                        }
                        if (right<chars.length){
                            if (chars[right]==C){
                                arr[i]=Math.abs(right-i);
                                break;
                            }
                            right++;
                        }
                    }
            }
        }
        return arr;
    }

    /**
     * 830. 较大分组的位置
     * @param s
     * @return
     */
    public List<List<Integer>> largeGroupPositions(String s) {
        int slow=0;//慢
        int fast=0;//快
        char[] chars = s.toCharArray();
        List<List<Integer>> lists=new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[slow]==chars[fast])
                fast++;
            else {
                if (fast-slow>2){
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(slow);
                    list.add(fast-1);
                    lists.add(list);
                }
                slow=fast;
                fast++;

            }
        }
        if (fast-slow>2&&chars[slow]==chars[fast-1]){
            ArrayList<Integer> list = new ArrayList<>();
            list.add(slow);
            list.add(fast-1);
            lists.add(list);
        }
        return lists;
    }

    /**
     * 844. 比较含退格的字符串
     * @param S
     * @param T
     * @return
     */
    public boolean backspaceCompare(String S, String T) {
      return   delete(S).equals(delete(T));
    }
    private String delete(String s){
        StringBuilder builder=new StringBuilder(s);
        for (int i = 0; i < builder.length();) {
            if (builder.charAt(i)=='#'){
                builder.deleteCharAt(i);
                if (i>0){
                    builder.deleteCharAt(i-1);
                    i--;
                }
            }else {
                i++;
            }
        }
        return builder.toString();
    }

    /**
     * 852. 山脉数组的峰顶索引
     * @param arr
     * @return
     */
    public int peakIndexInMountainArray(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i]<arr[i-1]){
                return i-1;
            }
        }
        return 0;
    }


    /**
     * 859. 亲密字符串
     * @param A
     * @param B
     * @return
     */
    public boolean buddyStrings(String A, String B) {
        if (A.length()!=B.length())
            return false;

        char[] a = A.toCharArray();
        char[] b = B.toCharArray();
        for (int i = 0; i < a.length; i++) {
            if (a[i]!=b[i]){
                char c=b[i];
                for (int j = 0; j < a.length; j++) {
                    if (c==a[j]){
                        char tmp=a[j];
                        a[j]=a[i];
                        a[i]=tmp;
                        return Arrays.equals(a,b);
                    }
                }
                return false;
            }
        }
        HashSet<Character> set = new HashSet<>();
        for (char c : a) {
            set.add(c);
        }

        return set.size()!=a.length;
    }


    /**
     * 860. 柠檬水找零
     * @param bills
     */
    public boolean lemonadeChange(int[] bills) {
        int wu=0;
        int shi=0;
        for (int bill : bills) {
             if (bill==5)
                 wu++;
             else if (bill==10){
                 shi++;
                 int[] ints = lemonadeChange(5, wu, shi);
                 if (ints==null)
                     return false;
                 else {
                     wu=ints[0];
                     shi=ints[1];
                 }
             }else {
                 int[] ints = lemonadeChange(15, wu, shi);
                 if (ints==null)
                     return false;
                 else {
                     wu=ints[0];
                     shi=ints[1];
                 }
             }
        }
        return true;
    }
    private int[] lemonadeChange(int money,int wu,int shi){
        while (money!=0){
            if (money==15&&shi>0){
                money-=10;
                shi--;
                continue;
            }
            if (money>=5){
                if (wu==0)
                    return null;
                money-=5;
                wu--;
            }
        }
        return new int[]{wu,shi};
    }


    /**
     * 868. 二进制间距
     * @param n
     * @return
     */
    public int binaryGap(int n) {
        String s = Integer.toBinaryString(n);
        char[] chars = s.toCharArray();
        int max=0;
        int slow=0;//慢
        int fast=0;//快
        for (int i = 0; i < chars.length; i++) {
            if (chars[slow]==chars[fast]){
                max=Math.max(max,fast-slow);
                slow=fast;
            }
            fast++;
        }
        return max;
    }


    /**
     * 872. 叶子相似的树
     * @param root1
     * @param root2
     * @return
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        leafSimilar(root1,list1);
        leafSimilar(root2,list2);
        return list1.equals(list2);
    }

    private void  leafSimilar(TreeNode node,List<Integer> list){
        if (node==null)
            return;
        if (node.left!=null){
            leafSimilar(node.left,list);
        }
        if (node.right!=null){
            leafSimilar(node.right,list);
        }else if (node.left==null){
            list.add(node.val);
        }
    }

    /**
     * 876. 链表的中间结点
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        List<ListNode> list=new ArrayList<>();
        while (head!=null){
            list.add(head);
            head=head.next;
        }
        return list.get(list.size()/2);
    }


    /**
     * 884. 两句话中的不常见单词
     * @param A
     * @param B
     * @return
     */
    public String[] uncommonFromSentences(String A, String B) {
        String[] a = A.split("\\s+");
        String[] b = B.split("\\s+");
        Map<String,Integer> map=new HashMap<>();
        uncommonFromSentences(a,map);
        uncommonFromSentences(b,map);
       return map.entrySet().stream().filter(s->s.getValue()==1).map(s->s.getKey()).toArray(String[]::new);
    }
    private void uncommonFromSentences(String[] strings,Map<String,Integer> map){
        for (String s : strings) {
            Integer integer = map.get(s);
            if (integer==null)
                map.put(s,1);
            else {
                map.put(s,0);
            }
        }
    }



    public static void main(String[] args) {

        int i = new Day04().binaryGap(8);
        System.out.println( i);
    }
}
