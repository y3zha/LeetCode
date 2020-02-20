package leetcode.coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和。写一个通用解法，K数之和，实际上是一个背包问题，给定一个数组，选出K个数，使其和为0，找出满足条件的有多少种情况，那就用dp
 * 这个题要返回结果集，使用组合的思想
 */
public class _015_三数之和 {

    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        //target是0，就不传了
        dfs(nums, 0, 0,visited,new ArrayList<Integer>(), res, 0);
        return res;
    }

    //使用dfs超出了时间限制，但是思路是正确的
    private void dfs(int[] nums, int startIndex, int crtSum, boolean[] visited, ArrayList<Integer> list, List<List<Integer>> res, int count) {
        if (count == 3 && crtSum == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            //去重,如果后面的数和前面一样，但是前面没选，那后面的这个就不能选
            if (!visited[i] && i > startIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            if (!visited[i]) {
                list.add(nums[i]);
                visited[i] = true;
                crtSum += nums[i];
                count++;
                dfs(nums, i + 1, crtSum, visited, list, res, count);
                count--;
                crtSum -= nums[i];
                visited[i] = false;
                list.remove(list.size() - 1);
            }
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        if (n < 3) {
            return res;
        }
        Arrays.sort(nums);
        //abc，固定a，固定小的！！！！！不要固定大的（c），减4Sum  固定小的在target那一端
        for (int i = 0; i < n - 2; i++) {
            //避免结果集中重复
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            //i的右面区间
            int left = i + 1;
            int right = nums.length - 1;
            int target = -nums[i];  //b + c = 0 - nums[i]

            //就是twoSum
            while (left < right) {
                if (nums[left] + nums[right] > target) {
                    right--;
                } else if (nums[left] + nums[right] < target) {
                    left++;
                } else {    //相等的情况
                    ArrayList<Integer> list = new ArrayList<>();
                    //添加三个元素
                    list.add(-target);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    res.add(list);
                    left++;
                    right--;

                    //如果left++后，这个数字和前面数字是一样的，则继续加
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }

                    //如果right--后，这个数字和前面数字是一样的，则继续减
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                }
            }
        }
        return res;
    }
}