package simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
/**
 * 每个类 30题
 */
public class Day02 {


    /**
     * 342. 4的幂
     * @param num
     * @return
     */
    public boolean isPowerOfFour(int num) {
        //第一个条件是数字必须大于0 第二个条件必须是2的次方，第3个条件必须是4的倍数  牛批
        return (num > 0) && ((num & (num - 1)) == 0) && (num % 3 == 1);
    }


    /**
     * 268. 缺失数字
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int sum=0;
        for (int i = 1; i <=nums.length; i++) {
            sum+=(i-nums[i-1]);
        }
        return sum;
    }

    /**
     * 389. 找不同
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference(String s, String t) {
        int letter=0;
        for (char c : s.toCharArray()) {
            letter=letter^c;
        }
        for (char c : t.toCharArray()) {
            letter=letter^c;
        }
        return (char)letter;
    }

    /**
     * 461. 汉明距离
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance(int x, int y) {
        int a=x^y;
        int count=0;
        while (a!=0){
            count+=(a&1);
            a=a>>1;
        }
        return count;
    }

    /**
     * 476. 数字的补数
     * @param num
     * @return
     */
    public int findComplement(int num) {
        //我的算法（太垃圾了（冗余））
        if (num==0)
            return 1;
        int newData=0;
        int count=0;
        int data=0;
        while (num!=0){
            count++;
            data=data<<1;
            if ((num&1)==0){
                data+=1;
            }
            num=num>>1;
        }
        while (count--!=0){
            newData=newData<<1;
            newData=newData+(data&1);
            data=data>>1;
        }
        return newData;

        //别的算法（很优雅）
//        int result = 0;
//        int temp = 1;
//
//        while (num > 0) {
//            //遇到为0的位 我们就要置1
//            if ((num & 1) == 0) {
//                result = result | temp;
//            }
//            // 让temp去移动可能要变更的位
//            temp <<= 1;
//            num >>= 1;
//        }
//
//        return result;
    }


    /**
     * 344. 反转字符串
     * @param s
     */
    public void reverseString(char[] s) {
        for (int i = 0; i < s.length / 2; i++) {
            char c=s[i];
            s[i]=s[s.length-1-i];
            s[s.length-1-i]=c;
        }
    }

    /**
     * 345. 反转字符串中的元音字母
     * @param s
     * @return
     */
    public String reverseVowels(String s) {
        List<Integer> list=new ArrayList<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i <chars .length; i++) {
            if (vowel(chars[i])){
                list.add(i);
            }
        }
        for (int i = 0; i < list.size()/2; i++) {
            char c=chars[list.get(i)];
            chars[list.get(i)]=chars[list.get(list.size()-1-i)];
            chars[list.get(list.size()-1-i)]=c;
        }
        String.valueOf(chars);
        return list.toString();
    }

    private  boolean vowel(char c){
        String  vowel="aeouiAEOUI";//使用静态变量
        return vowel.indexOf(c)!=-1;
    }


    /**
     * 349. 两个数组的交集
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set=new TreeSet<>();
        for (int i : nums1) {
            set.add(i);
        }
        List<Integer> list=new LinkedList();
        for (int i : nums2) {
            if (set.contains(i)){
                list.add(i);
                set.remove(i);
            }

        }
        int[] nweArr=new int[list.size()];
        int i=0;
        for (Integer a : list) {
            nweArr[i++]=a;
        }
        return nweArr;
    }


    /**
     * 258. 各位相加
     * @param num
     * @return
     */
    public int addDigits(int num) {
        if (num<10)
            return num;
        int newNum=0;
        while (num!=0){
            newNum+=num%10;
            num/=10;
        }
       return addDigits(newNum);
        //LeetCode 思想
        // return (num - 1) % 9 + 1;
    }


    /**
     * 198. 打家劫舍
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
      if (nums==null||nums.length==0)
          return 0;
      if (nums.length==1)
           return nums[0];
      int[] sum=new int[nums.length];
      sum[0]=nums[0];
      sum[1]=Math.max(sum[0],nums[1]);
      for (int i = 2; i < nums.length; i++) {
        sum[i]=Math.max(sum[i-1],sum[i-2]+nums[i]);
      }
     return nums[nums.length-1];
    }


    /**
     *  350. 两个数组的交集 II
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for (int i : nums1) {
            if (map.get(i)==null)
                map.put(i,1);
            else
                map.put(i,map.get(i)+1);
        }

        List<Integer> list=new LinkedList<>();
        for (int i : nums2) {
            Integer integer = map.get(i);
            if (integer!=null||integer!=0){
               list.add(i);
               map.put(i,integer-1);
           }
        }

        int[] newArr=new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            newArr[i]=list.get(i);
        }
        return newArr;
    }



    /**
     *  278. 第一个错误的版本
     * @param n
     * @return
     */
    public int firstBadVersion(int n) {
      //二分法
        int left=1;
        int right=n;
        while (left<right){
            int mid= left+(right-left)/2;
            if (isBadVersion(mid)){//错误版本
                right=mid;
            }else {//正确版本
                left=mid+1;
            }
        }
        return left;
    }
   private boolean isBadVersion(int version){
        return !(version<1702766719);
    }

    /**
     * 283. 移动零
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int zero=0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]!=0){
                nums[zero]=nums[i];
                zero++;
            }
        }
        for (int i = zero; i < nums.length; i++) {
            nums[i]=0;
        }
    }

    /**
     * 367. 有效的完全平方数
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
           return false;
    }


    /**
     * 371. 两整数之和
     * @param a
     * @param b
     * @return
     */
    public int getSum(int a, int b) {
        if (b==0)
            return a;
        return getSum(a^b, (a&b)<<1);
    }


    /**
     * 374. 猜数字大小
     * @param num
     * @return
     */
    public int guessNumber(int n) {
        int l=0;
        int r=n;
        while (l<=r){
            int mid=l+(r-l)/2;
            if (guess(mid)<0)
                r=mid-1;
            else if (guess(mid)>0){
               l=mid+1;
            }else
                return mid;

        }
        return l;
    }


     private int guess(int num){
        if (num==6)
            return 0;
        else if (num<6)
            return 1;
        else
            return -1;
    }


    /**
     * 383. 赎金信
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] hash=new int[26];
        for (char c : magazine.toCharArray()) {
            hash[c-'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            if (hash[(c-'a')]<=0){
                return false;
            }
        }
        return true;
    }


    /**
     * 415. 字符串相加
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
        StringBuilder builder=new StringBuilder();
        if (num1==null||num1.length()==0)
            return num2;
        if (num2==null||num2.length()==0)
            return num1;
        char[] achar1 = num1.toCharArray();
        char[] achar2 = num2.toCharArray();
        int data=0;
        int alenght=achar1.length-1;
        int blenght=achar2.length-1;
        while (alenght>=0||blenght>=0){
            if (alenght>=0)
                data+=achar1[alenght--]-'0';
            if (blenght>=0)
                data+=achar2[blenght--]-'0';
            builder.append(data%10);
            data/=10;
        }
       if (data!=0)
           builder.append(data);
       return builder.reverse().toString();
    }

    /**
     * 485. 最大连续1的个数
     * @param nums
     * @return
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int i=0;
        int max=0;
        for (int num : nums) {
           i=num==1?++i:0;
            if (max<i)
                max=i;
        }
        return max;
    }


    /**
     * 507. 完美数
     * @param num
     * @return
     */
    public boolean checkPerfectNumber(int num) {
        if(num<2)
            return false;
        int sum=1;
        int sqrt= (int) Math.sqrt(num);
        for (int i = 2; i <=sqrt; i++) {
            if (num%i==0){
                if (num/i!=i){
                    sum+=num/i;
                }
                sum+=i;
            }
        }
        return sum==num;
    }


    /**
     * 相同的数
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p==null)
            return q==null;
        if (q==null)
            return p==null;
        if (p.val==q.val){
            return isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
        }
        return false;
    }


    /**
     * 326. 3的幂
     * @param n
     * @return
     */
    public boolean isPowerOfThree(int n) {
        if (n==0)
            return false;
        if (n==1)
            return true;
        if (n%3==0)
            return isPowerOfThree(n/3);
        return false;
    }

    /**
     * 387. 字符串中的第一个唯一字符
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (s.indexOf(chars[i])==s.lastIndexOf(chars[i]))
                return i;
        }
        return 0;
    }


    /**
     * 434. 字符串中的单词数
     * @param s
     * @return
     */
    public int countSegments(String s) {
        if (s==null||s.length()==0);
        return s.trim().split("\\s+").length;
    }


    /**
     * 441. 排列硬币
     * @param n
     * @return
     */
    public int arrangeCoins(int n) {
        if (n<=0)
            return 0;
        int k=1;
        while ((n=n-k)>=0){
            k++;
        }
        return k-1;
    }


    /**
     * 520. 检测大写字母
     * @param word
     * @return
     */
    public boolean detectCapitalUse(String word) {
        int big=0;
        int small=0;
        boolean first=false;
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i]>=65&&chars[i]<=90){
                big++;
                if (i==0){
                    first=true;
                }
            }
            else
                small++;
        }
        if (big==word.length()||small==word.length())
            return true;
        return first&&big==1;
    }


    /**
     * 541. 反转字符串 II
     * @param s
     * @param k
     * @return
     */
    public String reverseStr(String s, int k) {
       return null;
    }


    /**
     * 448. 找到所有数组中消失的数字
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int index=Math.abs(nums[i])-1;
            if (nums[index]>0)
            nums[index]*=-1;
        }
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]>0)
                list.add(i+1);
        }
        return list;
    }

    /**
     * 392. 判断子序列
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        char[] min = s.toCharArray();
        char[] max = t.toCharArray();
        int maxIndex=0;
        int minCount=0;
        for (int i = 0; i < min.length&&maxIndex<max.length; i++) {
            while (maxIndex<max.length){
                if (min[i]==max[maxIndex++]){
                    minCount++;
                    break;
                }
            }
        }
        return minCount==min.length;
    }

    /**
     * 492. 构造矩形
     * @param area
     * @return
     */
    public int[] constructRectangle(int area) {
        int sqrt = (int) Math.sqrt(area);
        int[] arr=new int[2]; //索引0 为长  索引1 为宽
        for (int i = sqrt; i >0; i--) {
            if (area%i==0){
                arr[0]=area/i;//长
                arr[1]=i;//宽
                return arr;
            }
        }
        return arr;
    }

}
