package simple;

import javax.xml.soap.Node;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

/**
 * 每个类 30题
 */
public class Day01 {

    /**
     * 1. 两数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(target - nums[i]) == null) {
                map.put(nums[i], i);
            } else {
                return new int[]{map.get(target - nums[i]), i};
            }
        }
        return null;
    }

    /**
     * 7. 整数反转
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        long n = 0;
        while (x != 0) {
            n = n * 10 + x % 10;//得到然后赋值给第一位
            x = x / 10;//将x缩小10倍
        }
        return n != (int) n ? 0 : (int) n;
    }


    /**
     * 9. 回文数
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        String str = String.valueOf(x);
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - i - 1))
                return false;
        }
        return true;
    }


    /**
     * 14. 最长公共前缀
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        int length = strs[0].length();
        StringBuilder builder = new StringBuilder(strs[0]);
        a:
        for (int i = 1; i < strs.length; i++) {
            String str = strs[i];
            for (int j = 0; j < str.length(); j++) {
                if (j < length) {
                    if (str.charAt(j) != builder.charAt(j)) {
                        length = j;
                        continue a;
                    }
                } else {
                    length = j;
                    continue a;
                }
            }
            length = str.length();
        }
        if (length == 0)
            return "";
        else
            return builder.substring(0, length);
    }


    /**
     * 20. 有效的括号
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        LinkedList<Character> list = new LinkedList();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                list.addFirst(c);
                continue;
            }
            if (list.size() == 0)
                return false;
            Character character = list.poll();
            if (c == ')' && character != '(')
                return false;
            if (c == '}' && character != '{')
                return false;
            if (c == ']' && character != '[')
                return false;

        }
        return list.size() == 0;
    }


    /**
     * 21. 合并两个有序链表
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        while (l2 != null) {
            l1 = mergeTwo(l1, new ListNode(l2.val));
            l2 = l2.next;
        }
        return l1;
    }

    private ListNode mergeTwo(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2.val > l1.val) {
            l1.next = mergeTwo(l1.next, l2);
        } else {
            l2.next = l1;
            l1 = l2;
        }
        return l1;
    }


    /**
     * 26. 删除排序数组中的重复项
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        //思路一（自己的）
//        int length=nums.length;
//        for (int i = 0; i < length-1;) {
//            if (nums[i]==nums[i+1]){
//                for (int j = i; j < nums.length-1; j++) {
//                    nums[j]=nums[j+1];
//                }
//                length--;
//            }else {
//                i++;
//            }
//        }
//        return length;

        //思路二（LeetCode）
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    /**
     * 27. 移除元素
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int length = 0;//数组长度
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[length] = nums[i];
                length++;
            }
        }
        return length;
    }


    /**
     * 35. 搜索插入位置
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target)
                return i;
            if (nums[i] > target)
                return i - 1;
        }
        return nums.length;
    }

    /**
     * 66. 加一
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        //思路一
//        LinkedList<Integer> list=new LinkedList();
//        int item=1;
//        for (int i = digits.length-1; i >= 0; i--) {
//            item+=digits[i];
//            list.addFirst(item%10);
//            item/=10;
//        }
//        if (item!=0)
//            list.addFirst(item);
//        int[] newArr=new int[list.size()];
//        for (int i = 0; i < newArr.length; i++) {
//            newArr[i]=list.poll();
//        }
//        return newArr;
        //思路二
        int item = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] == 9 && item == 1) {
                digits[i] = 0;
            } else {
                digits[i]++;
                return digits;
            }
        }
        int[] newArr = new int[digits.length + 1];
        newArr[0] = 1;
        return newArr;

    }


    /**
     * 67. 二进制求和
     * 给你两个二进制字符串，返回它们的和（用二进制表示）。
     * 试例输入的二进制串  00000010100101000001111010011100 表示无符号整数 43261596，
     * 其二进制表示形式为  00111001011110000010100101000000。 因此返回 964176192，
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        StringBuilder builder = new StringBuilder();
        int carry = 0;
        if (a.length() == 0)
            return b;
        if (b.length() == 0)
            return a;
        char[] ar = a.toCharArray();
        char[] br = b.toCharArray();
        int alength = ar.length - 1;
        int blength = br.length - 1;
        while (alength >= 0 || blength >= 0) {
            if (alength >= 0)
                carry += (ar[alength--] - '0');
            if (blength >= 0)
                carry += (br[blength--] - '0');
            builder.append(carry % 2);
            carry /= 2;
        }
        if (carry == 0)
            builder.append(1);
        return builder.reverse().toString();
    }


    /**
     * 69. x 的平方根
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        return (int) Math.sqrt(x);
    }

    /**
     * 83. 删除排序链表中的重复元素
     * @param head
     * @return
     */
//    public ListNode deleteDuplicates(ListNode head) {
//
//    }

    /**
     * 104. 二叉树的最大深度
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        return maxDepth(root, 0);
    }

    private int maxDepth(TreeNode treeNode, int length) {
        if (treeNode == null)
            return length;
        int left = maxDepth(treeNode.left, length + 1);
        int right = maxDepth(treeNode.right, length + 1);
        return Math.max(left, right);
    }



    /**
     * 107. 二叉树的层次遍历 II
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> listList = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> oneLevel = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                oneLevel.add(poll.val);
                if (poll.left != null)
                    queue.add(poll.left);
                if (poll.right != null)
                    queue.add(poll.right);
            }
            listList.addFirst(oneLevel);
        }
        return listList;
    }


    /**
     * 104. 二叉树的最小深度
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        return 0;
    }

    /**
     * 112. 路径总和
     *
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return sum == 0;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    /**
     * 118. 杨辉三角
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> row = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            ArrayList<Integer> lie = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i)
                    lie.add(1);
                else {
                    lie.add(row.get(i - 1).get(j - 1) + row.get(i - 1).get(j));
                }
            }
            row.add(lie);
        }
        return row;
    }


    /**
     * 119. 杨辉三角 II
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        return generate(rowIndex + 1).get(rowIndex + 1);
    }

    /**
     * 125. 验证回文串
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     *
     * @param s
     * @return
     */
    // 验证回文串
    public boolean isPalindrome(String s) {
        String aCase = s.toLowerCase();
        StringBuilder builder = new StringBuilder();
        for (char c : aCase.toCharArray()) {
            if (Character.isLetterOrDigit(c))
                builder.append(c);
        }
        return builder.toString().equals(builder.reverse().toString());
    }


    /**
     * 136. 只出现一次的数字
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int num = nums[0];
        for (int i = 1; i < nums.length; i++) {
            num = num ^ nums[i];
        }
        return num;
    }


    /**
     * 189. 旋转数组
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int count = k % nums.length;
        if (count == 0)
            return;
        for (int i = 0; i < count; i++) {//外循环空值旋转次数
            int end = nums[nums.length - 1];//将最后一个的值存储起来
            for (int j = nums.length - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = end;
        }
    }


    /**
     * 190. 颠倒二进制位
     *
     * @param n
     * @return
     */
    public int reverseBits(int n) {
        int count = 32;
        int data = 0;//颠倒后的数
        while (count-- > 0) {
            int end = n & 1;//拿到最后一位
            data = data << 1;
            n = n >> 1;
            data += end;
        }
        return data;
    }



    /**
     * 191. 位1的个数
     * 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。
     *
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int count = 0;
        int i = 0;
        while (i++ < 32) {
            count += n & 1;
            n >>= 1;
        }
        return count;
    }



    /**
     * 202. 快乐数
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        int sum = 0;
        int i = 0;
        while (n != 0) {
            i = n % 10;
            sum += i * i;
            n /= 10;
        }
        if (sum == 1)
            return true;
        if (sum == 4)
            return false;
        return isHappy(sum);

    }


    /**
     * 203. 移除链表元素
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode();//定义虚拟节点
        dummy.next = head;
        ListNode per = dummy;
        while (dummy.next != null) {
            if (dummy.next.val == val) {
                dummy.next = dummy.next.next;
            } else {
                dummy = dummy.next;
            }
        }
        return per.next;
    }


    /**
     * 204. 计数质数
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        if (n <= 2)
            return 0;
        if (n == 3) {
            return 1;
        }
        int count = 2;
        a:
        for (int i = 4; i < n; i++) {
            int sqrt = (int) Math.sqrt(i);
            for (int j = 2; j <= sqrt; j++) {
                if (i % j == 0) {
                    continue a;
                }
            }
            count++;
        }
        return count;
    }


    /**
     * 206. 反转链表
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {

        ListNode curr = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = curr;
            curr = head;
            head = next;
        }
        return curr;
    }


    /**
     * 217. 存在重复元素
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            } else {
                set.add(nums[i]);
            }
        }
        return false;
    }


    /**
     * 219. 存在重复元素 II
     * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer index = map.get(nums[i]);
            if (index != null) {
                if (i - index <= k)
                    return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }


    /**
     * 231. 2的幂
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        //思路一O(logN)时间复杂度
        if (n < 0)
            return false;
        int count = 0;
        while (n != 0) {
            count += n & 1;
            n = n >> 1;
        }
        return count == 1;
        //思路二 O(1)时间复杂度
//        if (n<0)
//            return false;
//        return (n&n-1)==0;
    }


    /**
     * 234. 回文链表
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        List list = new ArrayList();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        for (int i = 0; i < list.size() / 2; i++) {
            if (!list.get(i).equals(list.get(list.size() - 1 - i)))
                return false;
        }
        return true;
    }
}



//
//
//
//
//    public int addDigits(int num) {
//        if (num<=9)
//            return num;
//        int sum=0;
//        while (num!=0){
//            sum=sum+(num%10);
//            num/=10;
//        }
//        return addDigits(sum);
//    }
//
//    public boolean isPowerOfThree(int n) {
////        while (n!=0){
////            if (n==1)
////                return true;
////            if (n%3==0){
////                n=n/3;
////            }else {
////                return false;
////            }
////        }
//        int x=3;
//        while (x<=n){
//            if (x==n)
//                return true;
//            x=x*3;
//        }
//        return false;
//    }
//
//    public boolean isPowerOfFour(int num) {
//        int zoer=0;
//        int one=0;
//        while (num!=0){
//            int x= num & 1;
//            if (x==1)
//                one++;
//            else
//                zoer++;
//            num>>=1;
//        }
//        return zoer%2==0&&one==1;
//    }
//
//    public String reverseVowels(String s) {
//        //1.缓存元音字符串的下标
//        List<Integer> indexArray=new ArrayList<>();
//
//        char[] chars = s.toCharArray();
//        for (int i = 0; i < chars.length; i++) {
//            if (chars[i]=='a'||chars[i]=='e'||chars[i]=='i'||chars[i]=='o'||chars[i]=='u'){
//                indexArray.add(i);
//            }
//        }
//        //2.交换下标位置
//        for (int i = 0; i < indexArray.size()/2; i++) {
//            char temp=chars[indexArray.get(i)];
//            chars[indexArray.get(i)]=chars[indexArray.get(indexArray.size()-1-i)];
//            chars[indexArray.get(indexArray.size()-1-i)]=temp;
//        }
//        //3.char数组转字符串
//        return String.valueOf(chars);
//    }
//
//    public  static int[] intersect(int[] nums1, int[] nums2) {
//        List<Integer> resultArray=new ArrayList();
//        List<Integer> array=new ArrayList<>();
//        for (int i : nums1) {
//            array.add(i);
//        }
//        for (int i : nums2) {
//            int of = array.indexOf(i);
//            if (of!=-1){
//                resultArray.add(i);
//                array.remove(of);
//            }
//        }
//        int[] ints = new int[resultArray.size()];
//        for (int i = 0; i < resultArray.size(); i++) {
//            ints[i]=resultArray.get(i);
//        }
//        return ints;
//    }
//
//    public void merge(int[] nums1, int m, int[] nums2, int n) {
//        int j=0;
//        for (int i = m; i< m+n; i++) {
//            nums1[i]=nums2[j++];
//        }
//        Arrays.sort(nums1);
//    }
//
//
//
//
//
//    public String convertToTitle(int n) {
//        if (n<=0)
//            return "";
//            StringBuilder builder=new StringBuilder();
//            while (n>0){
//                int i = n % 26;
//                if (i==0){
//                    i=26;
//                    n--;
//                }
//                builder.append((char)('A'+i-1));
//                n=n/26;
//            }
//            return builder.reverse().toString();
//    }
//
//
//
//    public boolean isPerfectSquare(int num) {
//        double sqrt = Math.sqrt(num);
//        int s= (int) sqrt;
//        BigDecimal bigDecimal = BigDecimal.valueOf(sqrt);
//        BigDecimal valueOf