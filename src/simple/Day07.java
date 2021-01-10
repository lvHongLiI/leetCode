package simple;

import java.util.Arrays;

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




    /**
     * 1619. 删除某些元素后的数组均值
     * @param arr
     * @return
     */
    public double trimMean(int[] arr) {
        int len=arr.length/20;
        Arrays.sort(arr);
        double sum=0;
        for (int i = len; i < arr.length-len; i++) {
            sum+=arr[i];
        }
        return sum/(arr.length-2*len);
    }


    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        TreeMap<Integer,List<List<Integer>>> treeMap=new TreeMap();
        Arrays.sort(arr);
        for (int i = 1; i < arr.length; i++) {
            int sum=Math.abs(arr[i]-arr[i-1]);
            List<List<Integer>> lists = treeMap.get(sum);
            if (lists==null){
                lists=new ArrayList<>();
                treeMap.put(sum,lists);
            }
            lists.add(Arrays.asList(arr[i-1],arr[i]));
        }
        return treeMap.pollFirstEntry().getValue();
    }

    /**
     * 1154. 一年中的第几天
     * @param date
     * @return
     */
    public int dayOfYear(String date) {
        int[] months=new int[]{0,31,28,31,30,31,30,31,31,30,31,30,31};
        String[] split = date.split("-");
        int year=Integer.valueOf(split[0]);
        int month=Integer.valueOf(split[1]);
        int day=Integer.valueOf(split[2]);
        boolean isYear=false;
        if (year%4==0){
            if (year%400==0){
                isYear=true;
            }else if (year%100!=0){
                isYear=true;
            }
        }
        int sum=0;
        for (int i = 1; i < month; i++) {
            sum+=months[i];
        }
        sum+=day;
        if (isYear&&month>2)
            sum++;
        return sum;
    }


    /**
     * 1185. 一周中的第几天
     * @param day
     * @param month
     * @param year
     * @return
     */
    public String dayOfTheWeek(int day, int month, int year) {
        String[] s={ "Wednesday", "Thursday", "Friday", "Saturday","Sunday", "Monday", "Tuesday"};
        int sum=0;
        for (int i = 1970; i < year; i++) {
            if (i%4==0){
                if (i%400==0){
                    sum+=366;
                    continue;
                }else if (i%100!=0){
                    sum+=366;
                    continue;
                }
            }
            sum+=365;
        }
        int[] months=new int[]{0,31,28,31,30,31,30,31,31,30,31,30,31};
        boolean isYear=false;
        if (year%4==0){
            if (year%400==0){
                isYear=true;
            }else if (year%100!=0){
                isYear=true;
            }
        }
        for (int i = 1; i < month; i++) {
            sum+=months[i];
        }
        if (isYear&&month>2)
            sum++;
        sum+=day;
        int x=sum%7;
        return s[x];
    }

    /**
     * 1189. “气球” 的最大数量
     * @param text
     * @return
     */
    public int maxNumberOfBalloons(String text) {
        int[] arr=new int[15];
        for (char c : text.toCharArray()) {
            if (c<='o')
                arr[c-'a']++;

        }
       return Math.min(Math.min(  Math.min( Math.min(arr[0],arr[1]),arr[11]/2),arr[14]/2),arr[13]);
    }

    /**
     * 1252. 奇数值单元格的数目
     * @param n
     * @param m
     * @param indices
     * @return
     */
    public int oddCells(int n, int m, int[][] indices) {
        //1.初始化一个n*m大小的数组
        int[] arr=new int[n*m];
        //2.遍历数组indices
        for (int[] indice : indices) {
            //2.1将行加1
            for (int i = indice[0]*m; i <(indice[0]+1)*m; i++) {
                arr[i]++;
            }
            print(arr,m);
            //2.2将列加1
            for (int i = indice[1]; i <arr.length; i+=m) {
                arr[i]++;
            }
            print(arr,m);
        }
        //3统计奇数值单元格的个数
        int sum=0;
        for (int i : arr) {
            if (i%2!=0){
                sum++;
            }
        }
        return sum;
    }

    private void  print(int[] arr,int  m){
        int count=0;
        for (int i = 0; i < arr.length; i++) {
            if (count%m==0){
                System.out.println();
            }
            System.out.print(arr[i]+"\t");
            count++;
        }
        System.out.println();
    }


    /**
     * 1624. 两个相同字符之间的最长子字符串
     * @param s
     * @return
     */
    public int maxLengthBetweenEqualCharacters(String s) {
        int[] start=new int[26];
        int[] end=new int[26];
        Arrays.fill(start,-1);
        Arrays.fill(end,-1);
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (start[chars[i]-'a']==-1){
                start[chars[i]-'a']=i;
            }else {

                end[chars[i]-'a']=i;
            }
        }
        int len=-1;
        for (int i = 0; i < 26; i++) {
            int  a= end[i] - start[i]-1;
                if (a>len)
                    len=a;
        }
        return len;
    }


    /**
     * 1652. 拆炸弹
     * @param code
     * @param k
     * @return
     */
    public int[] decrypt(int[] code, int k) {
        int[] arr=code.clone();
        for (int i = 0; i < code.length; i++) {
            int count=0;
            if (k>0){
                 count=0;
                for (int j = 1; j <=k; j++) {
                    count+=arr[(i+j)%code.length];
                }
                code[i]=count;
            }else if (k<0){
                for (int j = 1; j <=Math.abs(k); j++) {
                    count+=arr[(code.length+i-j)%code.length];
                }
                code[i]=count;
            }else {
                code[i]=count;
            }

        }
        return code;
    }


    /**
     * 1656. 设计有序流
     */
    class OrderedStream {

        String[] strings;
        private int prt;
        public OrderedStream(int n) {
            strings=new String[n];
            prt=1;
        }

        public List<String> insert(int id, String value) {
            strings[id-1]=value;
            LinkedList<String> list = new LinkedList<>();
            if (prt==id){
                for (int i = id-1; i <strings.length; i++) {
                    if (strings[i]!=null){
                        list.add(strings[i]);
                    }else {
                        prt=i+1;
                        return list;
                    }
                }
            }
            return list;
        }
    }

    /**
     * 1662. 检查两个字符串数组是否相等
     * @param word1
     * @param word2
     * @return
     */
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
            StringBuilder a=new StringBuilder();
            StringBuilder b=new StringBuilder();
            for (String s : word1) {
                a.append(s);
            }
            for (String s : word2) {
                b.append(s);
            }
            return a.toString().equals(b.toString());
    }


    /**
     * 1672. 最富有客户的资产总量
     * @param accounts
     * @return
     */
    public int maximumWealth(int[][] accounts) {
        int max=0;
        for (int[] account : accounts) {
            int sum=0;
            for (int i : account) {
                sum+=i;
            }
            if (max<sum)
                max=sum;
        }
        return max;
    }

    /**
     * 1684. 统计一致字符串的数目
     * @param allowed
     * @param words
     * @return
     */
    public int countConsistentStrings(String allowed, String[] words) {
        int[] chars=new int[26];
        for (char c : allowed.toCharArray()) {
            chars[c-'a']++;
        }
        int sum=0;
        a:for (String word : words) {
            for (char c : word.toCharArray()) {
                if (chars[c-'a']==0){
                    continue a;
                }
            }
            sum++;
        }
        return sum;
    }

    /**
     * 1688. 比赛中的配对次数
     * @param n
     * @return
     */
    public int numberOfMatches(int n) {
        int sum=0;
        while (n>1){
            int a=n&1;
            sum+=n=n/2;
            sum+=a;
        }
        return sum;
    }


    /**
     * 1694. 重新格式化电话号码
     * @param number
     * @return
     */
    public String reformatNumber(String number) {
        StringBuilder sb=new StringBuilder(number);
        for (int i = 0; i < sb.length();) {
            if (sb.charAt(i)==' '||sb.charAt(i)=='-'){
                sb.deleteCharAt(i);
                continue;
            }
            i++;
        }
        int index=0;
        while (sb.length()-index>4){
            sb.insert(index+=3,'-');
            index++;
        }
        if (sb.length()-index==4){
            sb.insert(sb.length()-2,'-');
        }
        return sb.toString();
    }


    /**
     * 1704. 判断字符串的两半是否相似
     * @param s
     * @return
     */
    public boolean halvesAreAlike(String s) {
        int[] arr=new int[123];
        char[] chars={ 'a','e','i','o','u','A','E','I','O','U'};
        for (char c : chars) {
            arr[c]=1;
        }
        int left=0;
        int right=0;
        for (int i = 0; i < s.length()/2; i++) {
                if (arr[s.charAt(i)]!=0){
                    left++;
                }
                if (arr[s.charAt(s.length()-1-i)]!=0){
                    right ++;
                }
        }
        return left==right;
    }


    /**
     * LCP 01. 猜数字
     * @param guess
     * @param answer
     * @return
     */
    public int game(int[] guess, int[] answer) {
        int sum=0;
        for (int i = 0; i < guess.length; i++) {
            if (guess[i]==answer[i]){
                sum++;
            }
        }
        return sum;
    }


    /**
     * LCP 06. 拿硬币
     * @param coins
     * @return
     */
    public int minCount(int[] coins) {
        int sum=0;
        for (int coin : coins) {
            sum+=(coin+1)/2;
        }
        return sum;
    }


    /**
     * LCP 17. 速算机器人
     * @param s
     * @return
     */
    public int calculate(String s) {
        int x=0,y=0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)=='A'){
               getX(x,y);
            }else if (s.charAt(i)=='B'){
                getY(x,y);
            }
        }
        return x+y;
    }
    private int getX(int x,int y){
        return x*2+y;
    }
    private int getY(int x,int y){
        return y*2+x;
    }

    /**
     * LCP 18. 早餐组合
     * @param staple
     * @param drinks
     * @param x
     * @return
     */
    public int breakfastNumber1(int[] staple, int[] drinks, int x) {
        long sum=0;
        if (staple.length>drinks.length){
            int[] tmp=drinks;
            drinks=staple;
            staple=tmp;
        }
        Arrays.sort(drinks);
        a:for (int s : staple) {
            int num=x-s;
            int left=0;
            int right=drinks.length;
            while (left<right){
                int mid= left+(right-left)/2;
                if (drinks[mid]>num){//错误版本
                    right=mid;
                }else {//正确版本
                    left=mid+1;
                }
            }
            sum+=left;
        }
        return (int) (sum%1000000007);
    }



    public int breakfastNumber(int[] staple, int[] drinks, int x) {
        long res = 0;
        int [] xs = new int[x+1];
        //将低于x的每一个价位的 staple 都存起来
        for (int i = 0; i < staple.length; i++) {
            int a = staple[i];
            if (a < x){
                xs[a] = xs[a] +1;
            }
        }
        int sum = 0;
        for (int i = 1; i < xs.length; i++) {
            sum += xs[i];
            xs[i] = sum;
        }
        //计算
        for (int i = 0; i < drinks.length; i++) {
            int diff = x-drinks[i];

            if(diff>0 && diff < x){
                res+=xs[diff];
            }
        }
        return (int)(res % 1000000007);
    }

    /**
     * 剑指 Offer 03. 数组中重复的数字
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        int[] arr=new int[nums.length];
        for (int num : nums) {
            if (arr[num]==1){
                return num;
            }else {
                arr[num]=1;
            }
        }
        return -1;
    }

    /**
     * 剑指 Offer 05. 替换空格
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        StringBuilder sb=new StringBuilder(s);
        for (int i = 0; i < sb.length();) {
            if (sb.charAt(i)==' '){
                sb.insert(i,"%20");
                sb.deleteCharAt(i+3);
                i+=3;
            }else {
                i++;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Day07().replaceElements(new int[]{6,1})));
    }
}
