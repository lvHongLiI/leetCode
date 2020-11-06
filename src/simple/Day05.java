package simple;

import java.util.*;

/**
 * 每个类 30题
 */
public class Day05 {

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

}
