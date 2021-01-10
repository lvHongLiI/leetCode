package difficulty;
/**
 * 每个类 30题
 */
public class Day01 {

    /**
     * 154. 寻找旋转排序数组中的最小值 II
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i]<nums[i-1]){
                return nums[i];
            }
        }
        return nums[0];
    }
}
