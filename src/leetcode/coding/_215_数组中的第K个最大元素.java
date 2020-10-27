package leetcode.coding;

public class _215_数组中的第K个最大元素 {

    /**
     * 第一种写法，有很多点需要注意
     *
     * quick select
     */
    public static int findKthLargest(int[] nums,int k) {
        if (nums == null || nums.length == 0) return -1;
        return quickselect1(nums, 0, nums.length - 1, k - 1);
        // return quickselect2(nums, 0, nums.length - 1, k);
    }



    private static int quickselect1(int[] nums, int start, int end, int k) {
        if (start == end) return nums[start];
        int pivot = nums[start];
        int i = start, j = end;
        //一定是要小于等于！遍历整个数组
        while (i <= j) {
            while (i <= j && nums[i] > pivot) i++;  //这边不能加等号
            while (i <= j && nums[j] < pivot) j--;  //这边不能加等号
            if (i <= j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            }
        }
        if (start <= k && k <= j) {
            return quickselect1(nums, start, j, k);
        } else if (i <= k && k <= end) {
            return quickselect1(nums, i, end, k);
        }
        return nums[k];
    }

    /**
     * 写法二
     * 迭代 写起来很舒服
     */
    private static int quickselect2(int[] nums, int start, int end, int k) {
        //第k大，也就是第k小
        int targetIdx = nums.length - k;
        while (start < end) {
            int pivot = nums[start];
            int i = start, j = end;
            while (i <= j) {
                //按照升序来的
                while (i <= j && nums[i] < pivot) {
                    i++;
                }
                while (i <= j && nums[j] > pivot) {
                    j--;
                }
                if (i <= j) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    i++;
                    j--;
                }
            }
            //如果i太后面了，需要往前走，否则往后走
            if (i >= targetIdx) {
                end = i - 1;
            } else {
                start = i;
            }
        }
        return nums[targetIdx];
    }

    /**
     * 手写堆排序，最小堆
     */






}