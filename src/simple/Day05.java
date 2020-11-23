package simple;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 每个类 30题
 */
public class Day05 implements Cloneable{

    /**
     * 896. 单调数列
     * @param A
     * @return
     */
    public boolean isMonotonic(int[] A) {

        if (A.length<=1)
            return true;
        boolean flag=A[0]<A[A.length-1];//false 大到小  true x小到大
        if (flag){
            for (int i = 1; i < A.length; i++) {
                if (A[i]<A[i-1])
                    return false;
            }
        }else {
            for (int i = 1; i < A.length; i++) {
                if (A[i]>A[i-1])
                    return false;
            }
        }
        return true;
    }

    /**
     * 897. 递增顺序查找树
     * @param root
     * @return
     */
    public TreeNode increasingBST(TreeNode root) {
        List<Integer> list=new ArrayList<>();
        increasingBST(root,list);

        Iterator<Integer> iterator = list.iterator();
        TreeNode node=new TreeNode(iterator.next());
        TreeNode dummy=node;
        while (iterator.hasNext()){
            node.right=new TreeNode(iterator.next());
            node=node.right;
        }
        return dummy;
    }
    private void increasingBST(TreeNode root, List<Integer> list) {
        if (root==null)
            return;
        increasingBST(root.left,list);
        list.add(root.val);
        increasingBST(root.right,list);
    }

    /**
     * 905. 按奇偶排序数组
     * @param A
     * @return
     */
    public int[] sortArrayByParity(int[] A) {
        int left=0;
        int right=A.length-1;
        while (left<right){
            while (left<right&&A[left]%2==0){
                left++;
            }
            while (right>left&&A[right]%2==1){
                right--;
            }
            if (left<right){
                int tmp = A[right];
                A[right]=A[left];
                A[left]=tmp;
            }
        }
        return A;
    }


    /**
     * 169. 多数元素
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
       Arrays.sort(nums);
       return nums[nums.length/2];
    }

    /**
     * 938. 二叉搜索树的范围和
     * @param root
     * @param L
     * @param R
     * @return
     */
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root==null)
            return 0;
        if (root.val<L){
            return rangeSumBST(root.right,L,R);
        }else if (root.val>R){
            return rangeSumBST(root.left,L,R);
        }else {
            int left = rangeSumBST(root.left, L, root.val);
            int right = rangeSumBST(root.right, root.val, R);
            return left+right+root.val;
        }
    }



    /**
     * 917. 仅仅反转字母
     * @param S
     * @return
     */
    public String reverseOnlyLetters(String S) {
        if (S==null||S.length()<2)
            return S;
        char[] chars = S.toCharArray();
        int left=0;
        int right=chars.length-1;
        while (left<right){
            while (left<right&&!Character.isLetter(chars[left])){
                left++;
            }
            while (right>left&&!Character.isLetter(chars[right])){
                right--;
            }
            if (left<right){
                char tmp = chars[right];
                chars[right]=chars[left];
                chars[left]=tmp;
                left++;
                right--;
            }
        }
        return new StringBuilder( ).append(chars).toString();
    }


    /**
     * 965. 单值二叉树
     * @param root
     * @return
     */
    public boolean isUnivalTree(TreeNode root) {
        return isUnivalTree(root,root.val);
    }
    private boolean isUnivalTree(TreeNode root,int num) {
        if (root==null)
            return true;
        return  isUnivalTree(root.left,num)&&isUnivalTree(root.right,num)&&root.val==num;
    }


    /**
     * 976. 三角形的最大周长
     * @param A
     * @return
     */
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        for (int i = A.length-1; i >1; i--) {
            int len = A[i - 1] + A[i - 2];
            if (len>A[i])
                return len+A[i];
        }
        return 0;
    }

    /**
     * 989. 数组形式的整数加法
     * @param A
     * @param K
     * @return
     */
    public List<Integer> addToArrayForm(int[] A, int K) {
        LinkedList<Integer> list=new LinkedList<>();
        int len=A.length-1;//数组长度
        int next=0;//进位
        while (K>0||len>=0){
            int num=K%10;
            int i =num+next;
            if (len>=0){
                i+=A[len--];
            }
            list.addFirst((i%10));
            next=i/10;
            K=K/10;
        }
        if (next>0)
            list.addFirst(next);
        return list;
    }


    /**
     * 1009. 十进制整数的反码
     * @param N
     * @return
     */
    public int bitwiseComplement(int N) {
        String s = Integer.toBinaryString(N);
        char[] chars = s.toCharArray();
        int num=0;
        for (char c : chars) {
            num=num*2;
            if (c=='0'){
                num+=1;
            }
        }
        return num;
    }


    /**
     * 1010. 总持续时间可被 60 整除的歌曲
     * @param time
     * @return
     */
    public int numPairsDivisibleBy60(int[] time) {
        int[] seconds = new int[60];
        int res = 0;
        for(int t : time) {
            int i = 60 - t % 60;
            res += seconds[ i == 60 ? 0 : i];
            seconds[t % 60] += 1;
        }
        return res;
    }


    /**
     * 977. 有序数组的平方
     * @param A
     * @return
     */
    public int[] sortedSquares(int[] A) {
        int[] arr=new int[A.length];
        for (int i = 0; i < A.length; i++) {
            arr[i]=A[i]*A[i];
        }
        Arrays.sort(arr);
        return arr;
    }


    /**
     * 1013. 将数组分成和相等的三个部分
     * @param A
     * @return
     */
    public boolean canThreePartsEqualSum(int[] A) {
        int sum=0;
        int num=0;
        for (int i : A) {
            sum+=i;
        }
        if (sum%3!=0){
            return false;
        }
        int avg=sum/3;
        for (int i = 0; i < A.length; i++) {
            if (i==0||num!=avg){
                num+=A[i];
                continue;
            }else if (num==avg){
                num=0;
                for (int j = i; j < A.length; j++) {
                    if (j==i||num!=avg){
                        num+=A[j];
                        continue;
                    }else if (num==avg){
                        num=0;
                        for (int k = j; k < A.length; k++) {
                                num+=A[k];
                        }
                        if (num==avg)
                            return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 1018. 可被 5 整除的二进制前缀
     * @param A
     * @return
     */
    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> list=new ArrayList<>();
        int sum=0;
        for (int i = 0; i < A.length; i++) {
            sum=sum*2+A[i]%5;
            list.add(sum%5==0);
        }
        return list;
    }

    /**
     * 1022. 从根到叶的二进制数之和
     * @param root
     * @return
     */
    public int sumRootToLeaf(TreeNode root) {
        return sumRootToLeaf(root,0);
    }
    private int sumRootToLeaf(TreeNode root,int num){
        if (root==null)
            return 0;
        if (root.left==null&&root.right==null)
            return num*2+root.val;
        int left=0;
        int right=0;
        left = sumRootToLeaf(root.left, num*2+root.val);
        right = sumRootToLeaf(root.right, num*2+root.val);
        return left+right;
    }
    /**
     * 1025. 除数博弈
     * @param N
     * @return
     */
    public boolean divisorGame(int N) {
        int num=0;
        while (N>1){
            N=N-1;
            num++;
        }
        return num%2!=0;
    }

    /**
     * 1046. 最后一块石头的重量
     * @param stones
     * @return
     */
    public int lastStoneWeight(int[] stones) {
        LinkedList<Integer> list=new LinkedList<>();
        for (int stone : stones) {
            list.add(stone);
        }
        while (list.size()>1){
            Collections.sort(list);
            int y=list.pollLast();
            int x=list.pollLast();
            int s=y-x;
            if (s!=0)
                list.add(s);
        }
        return list.size()==0?0:list.pollFirst();
    }


    /**
     * 1089. 复写零
     * @param arr
     */
    public void duplicateZeros(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]==0){
                for (int j =arr.length-1;j>i;j--) {
                    arr[j]=arr[j-1];
                }
                i++;
            }
        }
    }

    /**
     * 1108. IP 地址无效化
     * @param address
     * @return
     */
    public String defangIPaddr(String address) {
        StringBuilder builder = new StringBuilder(address);
        for (int i = 0; i < builder.length(); i++) {
            if (builder.charAt(i)=='.'){
                builder.insert(i,'[');
                builder.insert(i+2,']');
                i+=2;
            }
        }
        return builder.toString();
    }

    /**
     * 1047. 删除字符串中的所有相邻重复项
     * @param S
     * @return
     */
    public String removeDuplicates(String S) {
       StringBuilder sb=new StringBuilder(S);
        for (int i = 0; i < sb.length()-1;) {
            if (sb.charAt(i)==sb.charAt(i+1)){
                sb.deleteCharAt(i);
                sb.deleteCharAt(i);
                if (i>0)
                    i--;
            }else {
                i++;
            }
        }
        return sb.toString();
    }

    /**
     * 按序打印
     */
    static class Foo {

        private Lock lock;
        private Condition first;
        private Condition second;
        private Condition third;
        private volatile int flag=1;
        public Foo() {
            lock=new ReentrantLock();
            first=lock.newCondition();
            second=lock.newCondition();
            third=lock.newCondition();
        }

        public void first(Runnable printFirst) throws InterruptedException {
            lock.lock();
            if (flag!=1)
                first.await();
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            flag=2;
            second.signal();
            lock.unlock();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            lock.lock();
            if (flag!=2)
                second.await();
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            flag=3;
            third.signal();
            lock.unlock();
        }

        public void third(Runnable printThird) throws InterruptedException {
            lock.lock();
            if (flag!=3)
                third.await();
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
            flag=1;
            first.signal();
            lock.unlock();
        }
    }

    /**
     * 1304. 和为零的N个唯一整数
     * @param n
     * @return
     */
    public int[] sumZero(int n) {
        int[] ints = new int[n];
        boolean flag=n%2==1;
        while (n>0){
            if (n==1&&flag)
                ints[n-1]=0;
            else {
                if (n%2==0){
                    ints[n-1]=n/2;
                }else {
                    if (flag){
                        ints[n-1]=-(n/2);
                    }else {
                        ints[n-1]=-((n+1)/2);
                    }
                }
            }
            n--;
        }
        return ints;
    }

    /**
     * 1313. 解压缩编码列表
     * @param nums
     * @return
     */
    public int[] decompressRLElist(int[] nums) {
        LinkedList<Integer> list=new LinkedList<>();
        int size=0;
        int num=0;
        for (int i = 0; i < nums.length; i+=2) {
            size=nums[i];
            num=nums[i+1];
            while (size-->0){
                list.add(num);
            }
        }
        int[] arr=new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i]=list.get(i);
        }
        return arr;
    }


    /**
     *1323. 6 和 9 组成的最大数字
     * @param num
     * @return
     */
    public int maximum69Number (int num) {
        StringBuilder sb=new StringBuilder();
        sb.append(num);
        int nu=0;
        boolean flag=false;
        while (sb.length()>0){
            char c = sb.charAt(0);
            if (!flag){
                if (flag=c=='6'){
                    nu=nu*10+9;
                }else {
                    nu=nu*10+(c-48);
                }
            }else {
                nu=nu*10+(c-48);
            }
            sb.deleteCharAt(0);
        }
        return nu;

    }


    /**
     * 1346. 检查整数及其两倍数是否存在
     * @param arr
     * @return
     */
    public boolean checkIfExist(int[] arr) {
        HashSet<Integer> set=new HashSet<>();
        for (int i : arr) {
            if (set.contains(i*2)){
               return true;
            }else {
               if (i%2==0&&set.contains(i/2)){
                   return true;
               }
               set.add(Integer.valueOf(i));
            }
        }
        return false;
    }

    /**
     * 1342. 将数字变成 0 的操作次数
     * @param num
     * @return
     */
    public int numberOfSteps (int num) {
        int count=0;
        while (num>0){
            int n=num&1;
            if (n==0){
                num/=2;
            }else {
                num-=1;
            }
            count++;
        }
        return count;
    }

    /**
     * 1365. 有多少小于当前数字的数字
     * @param nums
     * @return
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] arr=new int[101];
        for (int num : nums) {
            arr[num]++;
        }
        int[] list=new int[nums.length];
        for (int i = 0; i < list.length; i++) {
            list[i]=get(nums[i],arr);
        }
        return list;
    }
    private int get(int tmp,int[] arr){
        int sum=0;
        for (int i = 0; i < tmp; i++) {
            sum+=arr[i];
        }
        return sum;
    }

    /**
     * 1370. 上升下降字符串
     * @param s
     * @return
     */
    public String sortString(String s) {
        int[] chars=new int[26];
        StringBuilder sb=new StringBuilder();
        int sum=0;
        for (char c : s.toCharArray()) {
            chars[c-'a']++;
            sum++;
        }

        while (sum>0){
            int num=0;
            while (sum>0&&num<chars.length){
                if (chars[num]!=0){
                    sb.append((char)(97+num));
                    chars[num]--;
                    sum--;
                }
                num++;
            }
            num--;
            while (sum>0&&num>=0){
                if (chars[num]!=0){
                    sb.append((char)(97+num));
                    chars[num]--;
                    sum--;
                }
                num--;
            }
        }
        return sb.toString();
    }

    /**
     * 1374. 生成每种字符都是奇数个的字符串
     * @param n
     * @return
     */
    public String generateTheString(int n) {
        StringBuilder builder=new StringBuilder();
        boolean flag=n%2==0;
        while (n>0){
            if (n==1&&flag){
                builder.append('b');
                n--;
            }else {
                builder.append('a');
                n--;
            }
        }
        return builder.toString();
    }

    /**
     * 1417. 重新格式化字符串
     * @param s
     * @return
     */
    public String reformat(String s) {
            int np=0;//数字指针
            int ep=0;//英文指针
            StringBuilder sb=new StringBuilder();
            while (s.length()!=sb.length()&&(np<s.length()||ep<s.length())){
                while (np<s.length()&&!isNumber(s.charAt(np))){
                    np++;
                }
                if (np<s.length()){
                    if (sb.length()>0&&isNumber(sb.charAt(sb.length()-1))){
                        sb.insert(0,s.charAt(np++));
                    }else {
                        sb.append(s.charAt(np++));
                    }
                }

                while (ep<s.length()&&!isEnglish(s.charAt(ep))){
                    ep++;
                }
                if (ep<s.length()){
                    if (sb.length()>0&&isEnglish(sb.charAt(sb.length()-1))){
                        if (isEnglish(sb.charAt(0))){
                            break;
                        }
                        sb.insert(0,s.charAt(ep++));
                    }else {
                        sb.append(s.charAt(ep++));
                    }
                    continue;
                }
                break;
            }
            return s.length()!=sb.length()?"":sb.toString();
    }
    private boolean isNumber(char c){
        return c>=48&&c<=57;
    }

    private boolean isEnglish(char c){
        return c>=97&&c<=122;
    }




    public static void main(String[] args) {

    }
}
