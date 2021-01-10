package simple;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * 每个类 30题
 */
public class Day07 {

    /**
     * 1309. 解码字母到整数映射
     * @param s
     * @return
     */
    public String freqAlphabets(String s) {
        StringBuilder sb=new StringBuilder();
        StringBuilder builder=new StringBuilder();
        char[] chars = s.toCharArray();
        int len=0;
        while (len<chars.length){
            if (builder.length()<2){
                builder.append(chars[len]);
            }else {
                if (chars[len]=='#'){
                    sb.append((char) (Integer.valueOf(builder.toString()).intValue()+96));
                    builder.delete(0,builder.length());
                }else {
                    builder.append(chars[len]);
                    sb.append((char) (builder.charAt(0)+48)) ;
                    builder.deleteCharAt(0);
                }
            }
            len++;
        }
        while (builder.length()>0){
            sb.append((char) (builder.charAt(0)+48)) ;
            builder.deleteCharAt(0);
        }
        return sb.toString();
    }

    /**
     * 1299. 将每个元素替换为右侧最大元素
     * @param arr
     * @return
     */
    public int[] replaceElements(int[] arr) {
        int[] num=new int[arr.length];
        num[arr.length-1]=-1;
        for (int i = arr.length-2; i >=0; i--) {
            num[i]=Math.max(arr[i+1],num[i+1]);
        }
        return num;
    }


    /**
     * 1629. 按键持续时间最长的键
     * @param releaseTimes
     * @param keysPressed
     * @return
     */
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int[] arr=new int[26];
        int lastNumber=0;
        int max=0;
        for (int i = 0; i < releaseTimes.length; i++) {
            char c = keysPressed.charAt(i);
            int index=c-'a';
            int now = releaseTimes[i] - lastNumber;
            if (now>arr[index]){
                arr[index] = now;
            }
            if (arr[index]>max){
                max=arr[index];
            }
            lastNumber = releaseTimes[i];
        }
        for (int i = arr.length-1; i >=0; i--) {
            if (arr[i]==max){
                return (char) (i+'a');
            }
        }
        return '\u0000';
    }


    /**
     * 1646. 获取生成数组中的最大值
     * @param n
     * @return
     */
    public int getMaximumGenerated(int n) {
        int[] arr=new int[n+1];
        int max=0;
        for (int i = 0; i < arr.length; i++) {
            if (i<=1) {
                arr[i] = i;
                if (arr[i]>max){
                    max=arr[i];
                }
            }
            int s=2*i;
            int z=s+1;
            if (s>=2&&s<=n){
                arr[s]=arr[i];
                if (arr[s]>max){
                    max=arr[s];
                }
            }
            if (z>=2&&z<=n){
                arr[z]=arr[i]+arr[i+1];
                if (arr[z]>max){
                    max=arr[z];
                }
            }
        }
       // System.out.println(Arrays.toString(arr));
        return max;
    }


    /**
     * 1678. 设计 Goal 解析器
     * @param command
     * @return
     */
    public String interpret(String command) {
        StringBuilder sb=new StringBuilder();
        int last=0;
        for (int i = 0; i < command.length(); i++) {
            if (command.charAt(i)=='G'){
                sb.append("G");
            }else if (command.charAt(i)==')'){
                if (last==1){
                    sb.append("o");
                }else {
                    sb.append("al");
                }
                last=0;
            }else {
                last++;
            }
        }
        return sb.toString();
    }

    /**
     * 剑指 Offer 06. 从尾到头打印链表
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        LinkedList<Integer> list=new LinkedList();
        reversePrint(head,list);
        int[] arr=new int[list.size()];
        int len=0;
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            arr[len++]=iterator.next().intValue();
        }
        return arr;
    }

    private void reversePrint(ListNode head,LinkedList list) {
        if (head==null)
            return;
        reversePrint(head.next,list);
        list.add(head.val);
    }

    /**
     * 剑指 Offer 09. 用两个栈实现队列
     */
    class CQueue {
        int[] arr;
        int addIndex;
        int size;
        int deleteIndex;
        public CQueue() {
            arr=new int[10000];
        }

        public void appendTail(int value) {
                arr[addIndex++]=value;
                size++;
        }

        public int deleteHead() {
                if (size==0)
                    return -1;
                size--;
                return arr[deleteIndex++];
        }
    }

    /**
     * 剑指 Offer 10- I. 斐波那契数列
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n<2){
            return n;
        }
        int[] arr=new int[n+1];
        arr[1]=1;
        for (int i = 2; i < arr.length; i++) {
            arr[i]=arr[i-1]+arr[i-2];
            if (arr[i]>1000000007){
                arr[i]=arr[i]%1000000007;
            }
        }
        return arr[n];
    }

    /**
     * 剑指 Offer 10- II. 青蛙跳台阶问题
     * @param n
     * @return
     */
    public int numWays(int n) {
        if (n<2){
            return 1;
        }
        int[] arr=new int[n+1];
        arr[1]=1;
        arr[0]=1;
        for (int i = 2; i < arr.length; i++) {
            arr[i]=arr[i-1]+arr[i-2];
            if (arr[i]>1000000007){
                arr[i]=arr[i]%1000000007;
            }
        }
        return arr[n];
    }



    public static void main(String[] args) {

            System.out.println(new Day07().numWays(4));

    }


}
