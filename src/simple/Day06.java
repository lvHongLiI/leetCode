package simple;

import java.math.BigDecimal;
import java.util.*;


/**
 * 每个类 30题
 */
public class Day06 {


    /**
     * 1431. 拥有最多糖果的孩子
     * @param candies
     * @param extraCandies
     * @return
     */
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        //1.获取最多的糖果
        int[] arr=candies.clone();
        Arrays.sort(arr);
        int max=arr[arr.length-1];
        List<Boolean> list=new LinkedList<>();
        for (int candy : candies) {
            list.add(candy+extraCandies>=max);
        }
        return list;
    }

    /**
     * 1394. 找出数组中的幸运数
     * @param arr
     * @return
     */
    public int findLucky(int[] arr) {
        int[] ints=new int[501];
        for (int i : arr) {
            ints[i]++;
        }
        for (int i = ints.length-1; i >0 ; i--) {
            if (ints[i]==i)
                return i;
        }
        return -1;
    }


    /**
     * 1446. 连续字符
     * @param s
     * @return
     */
    public int maxPower(String s) {
        int max = 0;
        int slow = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[slow] != chars[i]) {
                max = Math.max(max, i - slow);
                slow = i;
            } else if (i == chars.length - 1) {
                max = Math.max(max, chars.length - slow);
            }
        }
        return max;
    }
    /**
     * 925. 长按键入
     * @param name
     * @param typed
     * @return
     */
    public boolean isLongPressedName(String name, String typed) {
        int nameIndex=0;
        int typedIndex=0;
        char last='\u0000';
        while (nameIndex<name.length()||typedIndex<typed.length()){
            if (nameIndex<name.length()&&typedIndex<typed.length()&&name.charAt(nameIndex)==typed.charAt(typedIndex)){
                last=name.charAt(nameIndex);
                nameIndex++;
                typedIndex++;
                if (nameIndex==name.length()&&typedIndex==typed.length())
                    return true;
            }else {
                if (typed.length()==typedIndex)
                    return false;
                if (typed.charAt(typedIndex)==last){
                    typedIndex++;
                    if (nameIndex==name.length()&&typedIndex==typed.length())
                        return true;
                }else {
                    return false;
                }
            }
        }
        return false;
    }


    /**
     * 941. 有效的山脉数组
     * @param arr
     * @return
     */
    public boolean validMountainArray(int[] arr) {
        if (arr.length<3)
            return false;
        int leftPrint=0;
        int rightPrint=arr.length-1;
        while (leftPrint<arr.length-2){
            if (arr[leftPrint]<=arr[leftPrint+1])
                leftPrint++;
            else
                break;
        }

        while (rightPrint>1){
            if (arr[rightPrint]<=arr[rightPrint-1]){
                rightPrint--;
            }else
                break;
        }
        return leftPrint==rightPrint&&arr[rightPrint]>arr[arr.length-1]&&arr[leftPrint]>arr[0];
    }


    /**
     * 1450. 在既定时间做作业的学生人数
     * @param startTime
     * @param endTime
     * @param queryTime
     * @return
     */
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
            int sum=0;
            for (int i = 0; i < startTime.length; i++) {
                if (startTime[i]<=queryTime&&endTime[i]>=queryTime)
                    sum++;
            }
            return sum;
    }


    /**
     * 1455. 检查单词是否为句中其他单词的前缀
     * @param sentence
     * @param searchWord
     * @return
     */
    public int isPrefixOfWord(String sentence, String searchWord) {
        char[] chars = sentence.toCharArray();
        int space=0;
        int wordIndex=0;
        boolean flag=true;
        for (int i = 0; i < chars.length; i++) {
            char c=chars[i];
            if (c==' '){
                space++;
                wordIndex=0;
                flag=true;
                continue;
            }else {
             if (flag&&c==searchWord.charAt(wordIndex)){
                 if (wordIndex==searchWord.length()-1)
                     return space+1;
                 else
                     wordIndex++;
             }else {
                 flag=false;
             }
            }
        }
        return -1;
//        String[] split = sentence.split("\\s+");
//        for (int i = 0; i < split.length; i++) {
//            if (isPrefix(split[i],searchWord))
//                return i+1;
//        }
//        return -1;
    }

    /**
     * 1460. 通过翻转子数组使两个数组相等
     * @param target
     * @param arr
     * @return
     */
    public boolean canBeEqual(int[] target, int[] arr) {
        int[] nums=new int[1000];
        for (int i = 0; i < target.length; i++) {
            nums[target[i]]++;
            nums[arr[i]]++;
        }
        for (int num : nums) {
            if (num%2==1)
                return false;
        }
        return true;
    }



     /** 1160. 拼写单词
     * @param words
     * @param chars
     * @return
     */
    public int countCharacters(String[] words, String chars) {
        int[] arr=new int[26];
        int sum=0;
        for (char c : chars.toCharArray()) {
            arr[c-'a']++;
        }
        for (String word : words) {
            if (isChars(word,arr.clone())){
                sum+=word.length();
            }
        }
        return sum;
    }
    private boolean isChars(String s,int[] arr){
        for (char c : s.toCharArray()) {
            if (arr[c-'a']--<1)
                return false;

        }
        return true;
    }


    /**
     * 1295. 统计位数为偶数的数字
     * @param nums
     * @return
     */
    public int findNumbers(int[] nums) {
        int sum=0;
        for (int num : nums) {
            while (num>99){
                num/=100;
            }
            if (num>9&&num<100){
                sum++;
            }
        }
        return sum;
    }


    /**
     * 1470. 重新排列数组
     * @param nums
     * @param n
     * @return
     */
    public int[] shuffle(int[] nums, int n) {
        int[] arr=nums.clone();
        for (int i = 0; i < nums.length; i++) {
            if (i<n){
                nums[i*2]=arr[i];
            }else {
                nums[(i%n)*2+1]=arr[i];
            }
        }
        return nums;
    }


    /**
     * 1475. 商品折扣后的最终价格
     * @param prices
     * @return
     */
    public int[] finalPrices(int[] prices) {
        int[] arr=new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            arr[i]=prices[i]-prices(prices,i);
        }
        return arr;
    }
    private int prices(int[] prices,int priceIndex){
        for (int i = priceIndex+1; i < prices.length; i++) {
            if (prices[i]<=prices[priceIndex])
                return prices[i];
        }
        return 0;
    }


    /**
     * 1480. 一维数组的动态和
     * @param nums
     * @return
     */
    public int[] runningSum(int[] nums) {
        int[] arr=new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i]=nums[i];
            if (i!=0){
                arr[i]+=arr[i-1];
            }
        }
        return arr;
    }


    /**
     * 1486. 数组异或操作
     * @param n
     * @param start
     * @return
     */
    public int xorOperation(int n, int start) {
        int sum=0;
        for (int i = 0; i < n; i++) {
            sum^=start+i*2;
        }
        return sum;
    }

    /**
     * 1491. 去掉最低工资和最高工资后的工资平均值
     * @param salary
     * @return
     */
    public double average(int[] salary) {
        int max=salary[0];
        int min=salary[salary.length-1];
        int sum=0;
        for (int sa : salary) {
            if (max<sa){
                max=sa;
            }
            if (min>sa){
                min=sa;
            }
            sum+=sa;
        }
        return (sum-max-min)/((salary.length-2)*1.0);
    }


    /**
     * 1502. 判断能否形成等差数列
     * @param arr
     * @return
     */
    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        int x=arr[1]-arr[0];
        for (int i = 2; i < arr.length; i++) {
                if(arr[i]-arr[i-1]!=x){
                    return false;
                }
        }
        return true;
    }


    /**
     * 1507. 转变日期格式
     * @param date
     * @return
     */
    public String reformatDate(String date) {
        StringBuilder sb=new StringBuilder();
        String[] month={"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String[] split = date.split("\\s+");
        sb.append(split[2]).append("-");
        switch (split[1]){
            case "Jan":
                sb.append("01-");
                break;
            case "Feb":
                sb.append("02-");
                break;
            case "Mar":
                sb.append("03-");
                break;
            case "Apr":
                sb.append("04-");
                break;
            case "May":
                sb.append("05-");
                break;
            case "Jun":
                sb.append("06-");
                break;
            case "Jul":
                sb.append("07-");
                break;
            case "Aug":
                sb.append("08-");
                break;
            case "Sep":
                sb.append("09-");
                break;
            case "Oct":
                sb.append("10-");
                break;
            case "Nov":
                sb.append("11-");
                break;
            case "Dec":
                sb.append("12-");
                break;
        }
        if (split[0].length()==4){
         sb.append(split[0].substring(0,2));
        }else {
            sb.append(0).append(split[0].substring(0,1));
        }
        return sb.toString();
    }


    /**
     * 1512. 好数对的数目
     * @param nums
     * @return
     */
    public int numIdenticalPairs(int[] nums) {
        int sum=0;

        for (int i = 0; i < nums.length; i++) {
            int last=nums[i];
            for (int j = i+1; j < nums.length; j++) {
                if (last==nums[j]){
                    sum++;
                }
            }
        }
        return sum;
    }

    /**
     * 1518. 换酒问题
     * @param numBottles
     * @param numExchange
     * @return
     */
    public int numWaterBottles(int numBottles, int numExchange) {
        int sum=numBottles;
        while (numBottles>0){
            sum+=numBottles/numExchange;
            int n=0;
            if (numBottles>numExchange){
                n=  numBottles%numExchange;
            }
            numBottles=numBottles/numExchange+n;
        }
        return sum;
    }


    /**
     * 1523. 在区间范围内统计奇数数目
     * @param low
     * @param high
     * @return
     */
    public int countOdds(int low, int high) {
        int num=(high-low)/2;
        if (low%2==1||high%2==1){
            num+=1;
        }
        return num;
    }

    /**
     * 1528. 重新排列字符串
     * @param s
     * @param indices
     * @return
     */
    public String restoreString(String s, int[] indices) {
        char[]  chars=new char[indices.length];
        for (int i = 0; i < indices.length; i++) {
            chars[indices[i]]=s.charAt(i);
        }

        return new String(chars);
    }

    /**
     * 1539. 第 k 个缺失的正整数
     * @param arr
     * @param k
     * @return
     */
    public int findKthPositive(int[] arr, int k) {
        int sum=0;
        int[] nums=new int[1001];
        for (int a : arr) {
            nums[a]=1;
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i]==0)
                sum++;
            if (sum==k)
                return i;
        }
        return 1000+k-sum;
    }

    /**
     * 1544. 整理字符串
     * @param s
     * @return
     */
    public String makeGood(String s) {
        StringBuilder builder=new StringBuilder(s);
        for (int i = 0; i < builder.length()-1;) {
            if (Math.abs(builder.charAt(i)-builder.charAt(i+1))==32){
                builder.deleteCharAt(i);
                builder.deleteCharAt(i);
                i=0;
            }else {
                i++;
            }
        }
        return builder.toString();
    }

    /**
     * 1550. 存在连续三个奇数的数组
     * @param arr
     * @return
     */
    public boolean threeConsecutiveOdds(int[] arr) {
        int low=0;
        int fast=0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]%2==1){
                fast++;
            }else {
                low=fast;
            }
            if (fast-low==3)
                return true;
        }
        return false;
    }

    /**
     * 1556. 千位分隔数
     * @param n
     * @return
     */
    public String thousandSeparator(int n) {
        StringBuilder sb=new StringBuilder();
        int sum=0;
        while (n>0){
            if (sum==3){
                sb.append(".");
                sum=0;
            }
            sum++;
            sb.append(n%10);
            n/=10;
        }
        return sb.length()==0?"0":sb.reverse().toString();
    }


    /**
     * 1576. 替换所有的问号
     * @param s
     * @return
     */
    public String modifyString(String s) {
        StringBuilder builder=new StringBuilder(s);
        for (int i = 0; i < builder.length(); i++) {
            if (builder.charAt(i)=='?'){
                    if (i==0){
                        if (i==builder.length()-1){
                            builder.deleteCharAt(i);
                            builder.append('a');
                        }else {
                            char aChar = getChar(builder.charAt(i + 1));
                            builder.deleteCharAt(i);
                            builder.insert(i,aChar);
                        }
                    }else {
                        if (i==builder.length()-1){
                            char aChar = getChar(builder.charAt(i - 1));
                            builder.deleteCharAt(i);
                            builder.insert(i,aChar);
                        }else {
                            char aChar = getChar(builder.charAt(i - 1), builder.charAt(i + 1));
                            builder.deleteCharAt(i);
                            builder.insert(i,aChar);
                        }
                    }
            }
        }
        return builder.toString();
    }


    private static char getChar(char... chars){
        a:for (int i = 0; i < 26; i++) {
            for (char aChar : chars) {
                if (aChar-'a'==i){
                    continue a;
                }
            }
            return (char) (i+'a');
        }
        return 'a';
    }

    /**
     * 1588. 所有奇数长度子数组的和
     * @param arr
     * @return
     */
    public int sumOddLengthSubarrays(int[] arr) {
        int sum=0;
        SegmentTree tree = new SegmentTree(arr);
        for (int i = 1; i <=arr.length; i+=2) {
            for (int j = 0; j <=arr.length-i; j++) {
                sum+=tree.query(j,j+i-1);
            }
        }
        return sum;
    }

    /**
     * 1592. 重新排列单词间的空格
     * @param text
     * @return
     */
    public String reorderSpaces(String text) {
        int sum=0;
        for (char c : text.toCharArray()) {
            if (c==' '){
                sum++;
            }
        }
        if (sum==0)
            return text;
        String[] split = text.trim().split("\\s+");
        int avg=0;
        if (split.length>1){
             avg=sum/(split.length-1);
        }
        StringBuilder builder=new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            builder.append(split[i]);
            if (i!=split.length-1){
                for (int j = 0; j <avg ; j++) {
                    builder.append(' ');
                }
                sum-=avg;
            }

        }

        for (int i = 0; i < sum; i++) {
            builder.append(' ');
        }
        return builder.toString();
    }

    /**
     * 1598. 文件夹操作日志搜集器
     * @param logs
     * @return
     */
    public int minOperations(String[] logs) {
    int sum=0;
        for (String log : logs) {
            if (log.equals("../")){
                if (sum>1){
                    sum--;
                }
            }else if (log.equals("./")){

            }else {
                sum++;
            }
        }
        return sum;
    }


    /**
     * 1603. 设计停车系统
     */
    class ParkingSystem {
        private int[] arr=new int[3];

        public ParkingSystem(int big, int medium, int small) {
            this.arr[0] = big;
            this.arr[1] = medium;
            this.arr[2]  = small;
        }

        public boolean addCar(int carType) {
            if (arr[carType]>0){
                arr[carType]--;
                return true;
            }
            return false;
        }
    }


    /**
     * 1614. 括号的最大嵌套深度
     * @param s
     * @return
     */
    public int maxDepth(String s) {
        int max=0;
        int sum=0;
        for (char c : s.toCharArray()) {
            if (c=='('){
                sum++;
                if (max<sum){
                    max=sum;
                }
            }else if (c==')'){
                sum--;
            }
        }
        return max;
    }

    public static void main(String[] args) {

        System.out.println(new Day06().maxDepth("()(1)+((2))+(((3)))"));

    }

}
