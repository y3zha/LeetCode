package leetcode.coding;

public class _334_递增的三元子序列 {

    // 保存最小值和次小值，如果比次小值还大就找到了
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;
        int min1 = 0x7fffffff, min2 = 0x7fffffff;
        for (int i = 0; i < n; i++){
            if(nums[i] <= min1){
                // min2 = min1; 不需要更新次小值，如果次小值在最小值前面，就不是上升的了
                min1 = nums[i];
            }else if (nums[i] <= min2){
                min2 = nums[i];
            }else{
                return true;
            }
        }
        return false;
    }

}