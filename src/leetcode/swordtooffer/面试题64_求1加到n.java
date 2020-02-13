package leetcode.swordtooffer;

public class 面试题64_求1加到n {

    // 要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
    //递归咯

    public int sumNums(int n) {
        int res = n;
        boolean flag = (res != 0) && ((res += sumNums(n - 1)) != 0); //短路特性，&& 前面若假，后面不计算
        return res;
    }

}