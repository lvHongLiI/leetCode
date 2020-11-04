package simple;

import java.util.*;

public class Day05 {

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

    public static void main(String[] args) {
        new Day05().lastStoneWeight(new int[]{1,3});
    }
}
