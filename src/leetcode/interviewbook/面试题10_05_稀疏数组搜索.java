package leetcode.interviewbook;

import java.util.Arrays;
import java.util.stream.Collectors;

public class 面试题10_05_稀疏数组搜索 {

    /**
     * 二分 logN，但是稀疏矩阵需要处理
     * 遇到空值，两边指针向中间移动即可，
     * 如果是每次二分取得的中间数为空，那就让他向右移动，但是不能大于右指针
     * (向左移动也可以，但是不能小于左指针，此处的目的同样也是跳过空值)
     */


}