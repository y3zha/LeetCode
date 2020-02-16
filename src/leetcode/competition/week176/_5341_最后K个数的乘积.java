package leetcode.competition.week176;

import java.util.ArrayList;
import java.util.List;

public class _5341_最后K个数的乘积 {

    class ProductOfNumbers {
        List<Integer> list;
        public ProductOfNumbers() {
            list = new ArrayList<>();
        }

        public void add(int num) {
            list.add(num);
        }

        public int getProduct(int k) {
            int res = 1;
            for (int i = list.size() - 1; i >= list.size() - k; i--) {
                res = res * list.get(i);
            }
            return res;
        }
    }
}