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

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Day07().replaceElements(new int[]{6,1})));
    }
}
