package leetcode.coding;

public class _604_迭代压缩字符串 {

    /**
     * 数字很大就超出内存限制了！
     */
    class StringIterator {
        StringBuilder sb;
        int p;

        public StringIterator(String s) {
            sb = new StringBuilder();
            p = 0;
            int i = 0;
            while (i < s.length()) {
                char c = s.charAt(i++);
                int cnt = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    cnt = cnt * 10 + s.charAt(i) - '0';
                }
                for (int j = 0; j < cnt; j++) {
                    sb.append(c);
                }
            }
        }

        public char next() {
            if (!hasNext()) {
                return ' ';
            } else {
                return sb.charAt(p++);
            }

        }

        public boolean hasNext() {
            return p != sb.length();
        }
    }

    /**
     * 其实不需要去把真正的字符串给生成出来
     * 用p代表当前字符是啥，用num代表这个字符还剩多少个就可以了
     */

    class StringIterator2 {
        int p, num;
        String s;
        char ch;

        public StringIterator2(String s) {
            p = num = 0;
            ch = ' ';
            this.s = s;
        }

        public char next() {
            if (!hasNext()) {
                return ' ';
            }
            if (num == 0) {
                ch = s.charAt(p++);

                while (p < s.length() && Character.isDigit(s.charAt(p))) {
                    num = num * 10 + s.charAt(p++) - '0';
                }
            }
            num--;
            return ch;
        }

        public boolean hasNext() {
            // 如果p==s.length，就要看num是否为0，为0，则空，不为0，则还有
            return !(p == s.length() && num == 0);
        }
    }
}