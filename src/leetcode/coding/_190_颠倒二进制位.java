package leetcode.coding;

public class _190_颠倒二进制位 {

    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i <= 31; i++) {
            //1、给定二进制位，从低到高逐位取出
            int temp = n >> i;
            //2、取有效位
            temp = temp & 1;
            //3、把它变到对应的位置上
            temp = temp << (31 - i);
            //4、通过异或运算翻转，因为和0进行异或运算都是其本身，这里用 | 或运算也可以
            res = res ^ temp;
        }
        return res;
    }


}