package leetcode.coding;

import java.util.ArrayList;
import java.util.List;

/**
 * 做这个题之前可以先看一下169题，投票算法，
 *
 *
 *
 * 169题，找一个众数，那就只要一个候选，他是保证一定有一个是众数的，直接投票就好
 * 但是这个题没有保证有一个元素一定出现 n/3以上
 *
 * 思路是这样的，弄个通用模板，N/K的众数最多只有k-1个。
 * 用摩尔投票法的思想，就是“凑够”K个不同的对象，然后把他们“移除”！投完票之后的候选者“可能”是众数。
 *
 * 那么对于这个题，超过n/3的数最多只能有3-1 = 2 个。先选出两个候选人A,B。 遍历数组，分三种情况：
 *  候选1：> n/3
 *  候选2：> n/3
 *  其他的： < n/3
 * 1、如果投A（当前元素等于A），则A的票数++;
 * 2、如果投B（当前元素等于B），B的票数++；
 * 3、如果A,B都不投（即当前与A，B都不相等）,那么检查此时A或B的票数是否减为0：
 *      如果为0,则当前元素成为新的候选人；如果A,B两个人的票数都不为0，那么A,B两个候选人的票数均减一；
 *
 * 会有几种可能：有2个大于n/3，有1个大于n/3，有0个大于n/3
 * 遍历结束后选出了两个候选人，但是这两个候选人是否满足>n/3，还需要再遍历一遍数组，找出两个候选人的具体票数。
 */
public class _229_求众数II {

    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int cand1 = 0,cand2 = 0;
        int cnt1 = 0, cnt2 = 0;
        //投票过程
        for (int num : nums) {
            if (num == cand1) {
                cnt1++;
                continue;
            }
            if (num == cand2) {
                cnt2++;
                continue;
            }
            //数字既不等于cand1也不等于cand2，如果cnt1为0，那它就去做cand1
            if (cnt1 == 0) {
                cand1 = num;
                cnt1++;
                continue;
            }
            //如果cand1的数量不为0但是cand2的数量为0，那他就去做cand2
            if (cnt2 == 0) {
                cand2 = num;
                cnt2++;
                continue;
            }
            //如果cand1和cand2的数量都不为0，那就都-1
            cnt1--;
            cnt2--;
        }
        //检查两个票数符不符合
        cnt1 = cnt2 = 0;
        for (int num : nums) {
            if (num == cand1) {
                cnt1++;
            } else if (num == cand2) {  //这里一定要用else if..因为可能出现[0,0,0]这种，会导致两个cand是一样的，写两个if结果就变为[0,0]了
                cnt2++;
            }
        }
        int n = nums.length;
        if (cnt1 > n / 3) {
            res.add(cand1);
        }
        if (cnt2 > n / 3) {
            res.add(cand2);
        }
        return res;
    }
}