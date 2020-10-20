package simple;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Day03 {

    /**
     * 496. 下一个更大元素 I
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] arr=new int[nums1.length];//返回结果
        //编写逻辑
        Map<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            for (int j = i+1; j < nums2.length; j++) {
                if (nums2[i]<nums2[j]){
                    map.put(nums2[i],nums2[j]);
                    break;
                }
            }
        }
        for (int i = 0; i < nums1.length; i++) {
            Integer integer = map.get(nums1[i]);
            arr[i]=integer==null?-1:integer.intValue();
        }
        return arr;
    }


    /**
     * 414. 第三大的数
     * @param nums
     * @return
     */
    public int thirdMax(int[] nums) {
        TreeSet<Integer> set=new TreeSet<>();
        for (int num : nums) {
            set.add(num);
            if (set.size()==4){
                set.pollFirst();
            }
        }
        return set.size()==3?set.first():set.last();
    }


    /**
     * 409. 最长回文串
     * @param s
     * @return
     */
    public int longestPalindrome(String s) {
        int[] arr=new int[58];
        boolean flag=false;
        int sum=0;
        for (int i = 0; i < s.length(); i++)
            arr[s.charAt(i)-'A']++;
        for (int i : arr) {
            if (i%2==0)
                sum+=i;
            else{
                if (!flag){
                    sum+=i;
                    flag=true;
                }else
                    sum+=i-1;
            }
        }
        return sum;
    }


    /**
     * 412. Fizz Buzz
     * @param n
     * @return
     */
    public List<String> fizzBuzz(int n) {
        String fizz="Fizz";
        String buzz="Buzz";
        String fizzBuzz="FizzBuzz";
        List<String> list=new ArrayList<>();
        for (int i = 1; i <=n; i++) {
            if (i%15==0){
                list.add(fizzBuzz);
            }else if (i%5==0){
                list.add(buzz);
            }else if (i%3==0){
                list.add(fizz);
            }else {
                list.add(String.valueOf(i));
            }
        }
        return list;

    }

    /**
     * 482. 密钥格式化
     * @param S
     * @param K
     * @return
     */
    public String licenseKeyFormatting(String S, int K) {
        StringBuilder sb=new StringBuilder();
        int len=0;
        char[] chars = S.toCharArray();
        char c='\u0000';
        for (int i = chars.length; i >0 ; i--) {
            int index=i-1;
            if (chars[index]!='-'){
                c=chars[index];
                if (c>96&&c<124)
                    c-=32;
                sb.append(c);
                len++;
            }
            if (len!=0&&len%K==0){
                sb.append('-');
                len=0;
            }
        }
        len=sb.length()-1;
        if (len>=0&&sb.charAt(len)=='-')
            sb.deleteCharAt(len);
        return sb.reverse().toString();
    }


    /**
     * 575. 分糖果
     * @param candies
     * @return
     */
    public int distributeCandies(int[] candies) {
        Set<Integer> set=new HashSet<>();
        for (int candy : candies) {
            set.add(candy);
        }
        return Math.min(set.size(),candies.length/2);
    }

    /**
     * 628. 三个数的最大乘积
     * @param nums
     * @return
     */
    public int maximumProduct(int[] nums) {
        //1.找出最大的三个数
        Arrays.sort(nums);

        int length = nums.length;
        return Math.max(nums[length-1]*nums[0]*nums[1],nums[length-1]*nums[length-2]*nums[length-3]);
    }

    /**
     * 637. 二叉树的层平均值
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        return null;
    }

    /**
     * 645. 错误的集合
     * @param nums
     * @return
     */
    public int[] findErrorNums(int[] nums) {
        int[] arr=new int[2];
        for (int i = 0; i < nums.length; i++) {
            int index=Math.abs(nums[i])-1;
            if (nums[index]>0)
                nums[index]*=-1;
            else
                arr[0]=index+1;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]>0){
                arr[1]=i+1;
                break;
            }
        }
        return arr;
    }

    /**
     * 643. 子数组最大平均数 I
     * @param nums
     * @param k
     * @return
     */
    public double findMaxAverage(int[] nums, int k) {
        double sum=0;
        for(int i=0;i<k;i++)
            sum+=nums[i];
        double res=sum;
        for(int i=k;i<nums.length;i++){
            sum+=nums[i]-nums[i-k];
            res=Math.max(res,sum);
        }
        return res/k;
    }


    /**
     * 653. 两数之和 IV - 输入 BST
     * @param root
     * @param k
     * @return
     */
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list=new LinkedList<>();
        findTarget(list,root);
        Set<Integer> set=new HashSet<>();
        for (Integer integer : list) {
            if (set.contains(integer)){
                set.add(k-integer);
                continue;
            }
            return true;
        }
        return false;
    }

    private void findTarget(List list,TreeNode root){
        if (root==null)
            return;
        list.add(root.val);
        findTarget(list,root.left);
        findTarget(list,root.right);
    }

    /**
     * 606. 根据二叉树创建字符串
     * @param t
     * @return
     */
    public String tree2str(TreeNode t) {
        StringBuilder builder=new StringBuilder();
        tree2str(builder,t);
        return builder.toString();
    }

    private void tree2str(StringBuilder builder,TreeNode node){
        if(node == null){
            return;
        }
        builder.append(node.val);
        if (node.right!=null||node.left!=null){
            builder.append("(");
            tree2str(builder,node.left);
            builder.append(")");
        }
        if (node.right!=null){
            builder.append("(");
            tree2str(builder,node.right);
            builder.append(")");
        }
    }

    /**
     * 617. 合并二叉树
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1==null)
            return t2;

        if (t2==null)
            return t1;
        TreeNode node = new TreeNode(t1.val + t2.val);
        node.left = mergeTrees(t1.left, t2.left);
        node.right = mergeTrees(t1.right, t2.right);
        return node;
    }

    /**
     * 657. 机器人能否返回原点
     * @param moves
     * @return
     */
    public boolean judgeCircle(String moves) {
        int r=0,l=0,d=0,u=0;
        for (char c : moves.toCharArray()) {
            switch (c){
                case 'U':
                    u++;
                    break;
                case 'D':
                    d++;
                    break;
                case 'R':
                    r++;
                    break;
                case 'L':
                    l++;
                    break;
            }
        }
        return r==l&&u==d;
    }

    /**
     * 669. 修剪二叉搜索树
     * @param root
     * @param low
     * @param high
     * @return
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root==null)
            return null;
        if (root.val<low){//当前节点左范围都小 直接去右节点找
            return trimBST(root.right,low,high);
        }else if (root.val>high){//当前节点比右范围大直接左节点找
            return trimBST(root.left,low,high);
        }else if (root.val==low){
            root.left=null;
            root.right=trimBST(root.right,root.val+1,high);
        }else if (root.val==high){
            root.right=null;
            root.left=trimBST(root.left,low,root.val-1);
        }else {
            root.right=trimBST(root.right,root.val+1,high);
            root.left=trimBST(root.left,low,root.val-1);

        }
        return  root;
    }

    /**
     * 605. 种花问题
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length&&n>0; i++) {
            if (flowerbed[i]==0){
                if (left(flowerbed,i-1)&&right(flowerbed,i+1)){
                    flowerbed[i]=1;
                    n--;
                }

            }
        }
        return n==0;
    }

    private boolean left(int[] flowerbed, int index){
            return index<0||flowerbed[index]==0;
    }

    private boolean right(int[] flowerbed, int index){
        return index>=flowerbed.length||flowerbed[index]==0;
    }

    /**
     *
     * 682. 棒球比赛
     * 1.整数（一轮的得分）：直接表示您在本轮中获得的积分数。
     * 2. "+"（一轮的得分）：表示本轮获得的得分是前两轮有效 回合得分的总和。
     * 3. "D"（一轮的得分）：表示本轮获得的得分是前一轮有效 回合得分的两倍。
     * 4. "C"（一个操作，这不是一个回合的分数）：表示您获得的最后一个有效 回合的分数是无效的，应该被移除。
     * @param ops
     * @return
     */
    public int calPoints(String[] ops) {
        List<Integer> arr=new ArrayList();
        for (int i = 0; i < ops.length; i++) {
            int size=arr.size();
            if ("+".equals(ops[i])){
                arr.add((size>0?arr.get(size-1):0)+(size>1?arr.get(size-2):0));
            }else if ("D".equals(ops[i])){
                arr.add((size>0?arr.get(size-1):0)*2);
            }else if ("C".equals(ops[i])){
                if (size>0)
                    arr.remove(size-1);
            }else {
                arr.add(Integer.valueOf(ops[i]).intValue());
            }
        }
        int sum=0;
        for (Integer integer : arr) {
            sum+=integer;
        }
        return sum;
    }

    /**
     * 671. 二叉树中第二小的节点
     * @param root
     * @return
     */
    public int findSecondMinimumValue(TreeNode root) {
        TreeSet<Integer> set=new TreeSet<>();
        value(set,root);
        if (set.size()<2)
            return -1;
        else {
            set.pollFirst();
            return set.pollFirst();
        }

    }

    private void value(TreeSet<Integer> treeSet,TreeNode root){
        if (root==null)
            return;
        treeSet.add(root.val);
        value(treeSet,root.left);
        value(treeSet,root.right);
    }

    /***
     * 674. 最长连续递增序列
     * @param nums
     * @return
     */
    public int findLengthOfLCIS(int[] nums) {
        int max=nums.length>0?1:0;
        int now=1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i]>nums[i-1]){
                now++;
            }else {
                now=1;
            }
            if (now>max)
                max=now;
        }
        return max;
    }

    /**
     * 690. 员工的重要性
     * @param employees
     * @param id
     * @return
     */
    public int getImportance(List<Employee> employees, int id) {
        /*笨方法*/
        int sum=0;
        if (employees!=null&&employees.size()>0){
            for (Employee employee : employees) {
                if ( employee.id==id){
                     sum=employee.importance+getImportance(employee.subordinates,employees);
                    return sum;
                }
            }
        }
        return sum;
    }

    private int getImportance(List<Integer> id, List<Employee> employees) {
        int sum=0;
        for (Integer integer : id) {
            for (Employee employee : employees) {
                if (integer.equals(employee.id)){
                    sum+=employee.importance+getImportance(employee.subordinates,employees);
                }
            }
        }
        return sum;
    }

    /**
     * 693. 交替位二进制数
     * @param n
     * @return
     */
    public boolean hasAlternatingBits(int n) {
        int last=2;
        do{
            int i=n&1;
            if (last==i)
                return false;
            else {
                last=i;
                n=n>>1;
            }
        } while (n>0);
        return true;
    }

    /**
     * 700. 二叉搜索树中的搜索
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root==null)
            return null;
        if (root.val==val)
            return root;
       else if (root.val<val)
           return searchBST(root.right,val);
       else
            return searchBST(root.left,val);
    }


    /**
     * 704. 二分查找
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int l=0;
        int r=nums.length;
        while (l<r){
            int now=((l+r)/2);
            if (nums[now]<target){
                l=now+1;
            }else if (nums[now]>target){
                r=now;
            }else {
                return now;
            }
        }
        return -1;
    }


    /**
     * 709. 转换成小写字母
     * @param str
     * @return
     */
    public String toLowerCase(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i]>64&&chars[i]<98){
                chars[i]+=32;
            }
        }
        return new String(chars);
    }

    /**
     * 717. 1比特与2比特字符
     * @param bits
     * @return
     */
    public boolean isOneBitCharacter(int[] bits) {
        int last=0;
        for (int i = 0; i < bits.length; i++) {
            if (bits[i]==1){
                if (last==1)
                    last=0;
                else
                    last=1;
            }else {
                if (i==bits.length-1&&last==0){
                    return true;
                }
                if (last==1){
                    last=0;
                }
            }
        }
        return false;
    }

    /**
     * 724. 寻找数组的中心索引
     * @param nums
     * @return
     */
    public int pivotIndex(int[] nums) {
        if (nums==null)
            return -1;
        for (int i = 0; i < nums.length; i++) {
            if (pivotIndex(nums,i))
                return i;
        }
        return -1;
    }

    public boolean pivotIndex(int[] nums,int index){
        int l=0;
        int r=0;
        for (int i = 0; i < index; i++) {
            l+=nums[i];
        }
        for (int i = index+1; i<nums.length; i++) {
            r+=nums[i];
        }
      return l==r;
    }

    /**
     * 728. 自除数
     * @param left
     * @param right
     * @return
     */
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> list=new ArrayList<>();
        for (int i = left; i <=right; i++) {
                if (isSelfDividingNumbers(i))
                    list.add(i);
        }
        return list;
    }


    private boolean isSelfDividingNumbers(int num){
        int i=0;
        int now=num;
        while (num>0){
            i=num%10;
            if (i==0||now%i!=0){
                return false;
            }
            num=num/10;
        }
        return true;
    }

    /**
     * 744. 寻找比目标字母大的最小字母
     * @param letters
     * @param target
     * @return
     */
    public char nextGreatestLetter(char[] letters, char target) {
        for (int i = 0; i < letters.length; i++) {
            if (target<letters[i])
                return letters[i];
        }
        return letters[0];
    }

    /**
     * 1207. 独一无二的出现次数
     * @param arr
     * @return
     */
    public boolean uniqueOccurrences(int[] arr) {
        if (arr==null||arr.length==0)
            return true;
        Map<Integer,Integer> map=new HashMap<>();
        for (int i : arr) {
            Integer integer = map.get(i);
            if (integer==null)
                map.put(i,1);
            else
                map.put(i,integer+1);
        }

        Set<Integer> set=new HashSet<>();
        set.addAll(map.values());
        return map.values().size()==set.size();
    }


    /**
     * 1290. 二进制链表转整数
     * @param head
     * @return
     */
    public int getDecimalValue(ListNode head) {
       int num=0;
       while (head!=null){
           num=num*2+head.val;
           head=head.next;
       }
       return num;
    }

    /**
     * 1281. 整数的各位积和之差
     * @param n
     * @return
     */
    public int subtractProductAndSum(int n) {
        int ji=1,he=0;
        if (n<10)
            return 0;
        while (n>0){
            int s=n%10;
            he+=s;
            ji=ji*s;
           n/=10;
        }
        return ji-he;
    }


    public static void main(String[] args) {

        System.out.println(new Day03().subtractProductAndSum(234));
    }


}
