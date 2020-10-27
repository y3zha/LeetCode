package leetcode.coding;

/**
 * 本质是 10进制转26进制
 * A = 1 * 26^0
 * AB = 1*26^1 + 2^26^0
 *
 * 十进制转二进制，比如6转二进制，那就是
 *  2 |_ 6 _
 *     2|_ 3 _  ······ 0    |
 *      2|_ 1 _ ·······1    |
 *        |_ 0_ ·······1    |
 *  然后得到二进制110
 *
 *  十进制转26进制，其实也是一样的，A就是1除以26，余1，。。。Z就是26除以26，余0，但是0不表示字母，我们可以认为是得0余26。
 *  修改的话，当我们发现当前位是 26 的时候，我们应该在等式两边减去一个 1
 *  测试下就知道了，比如ZZ是702（26*26），一开始c = 0，然后n--，变为701，701 /= 26 = 26
 *  如果n不减1，那就是702/= 26 = 27。这就不对了。
 */
public class _168_Excel表列名称 {

    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int c = n % 26;
            //如果是z
            if (c == 0) {
                c = 26;
                n--;
            }
            sb.append((char) (c - 1 + 'A'));
            n /= 26;
        }
        return sb.reverse().toString();
    }
}