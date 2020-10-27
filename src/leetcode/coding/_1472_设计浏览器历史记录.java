package leetcode.coding;

public class _1472_设计浏览器历史记录 {

    // 栈模拟即可
    static class BrowserHistory {
        String[] stack;
        int top = -1;
        int pos = -1;

        public BrowserHistory(String homepage) {
            stack = new String[5001];
            stack[++top] = homepage;
            pos = top;
        }

        //  从当前页跳转访问 url 对应的页面 。执行此操作会把浏览历史前进的记录全部删除。
        public void visit(String url) {
            stack[++pos] = url;
            top = pos;
        }

        // 在浏览历史中后退 steps 步。
        // 如果你只能在浏览历史中后退至多 x 步且 steps > x ，那么你只后退 x 步。请返回后退 至多 steps 步以后的 url 。
        public String back(int steps) {
            pos -= steps;
            if (pos < 0) {
                pos = 0;
                return stack[0];
            } else {
                return stack[pos];
            }

        }

        // 在浏览历史中前进 steps 步。
        // 如果你只能在浏览历史中前进至多 x 步且 steps > x ，那么你只前进 x 步。请返回前进 至多 steps步以后的 url 。
        public String forward(int steps) {
            pos += steps;
            if (pos > top) {
                pos = top;
                return stack[top];
            } else {
                return stack[pos];
            }
        }
    }

    public static void main(String[] args) {
        BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
        browserHistory.visit("google.com");
        browserHistory.visit("facebook.com");
        browserHistory.visit("youtube.com");
        browserHistory.back(1);
        browserHistory.back(1);
        browserHistory.forward(1);
        browserHistory.visit("linkedin.com");
        browserHistory.forward(2);
        browserHistory.back(2);
        browserHistory.back(7);

    }
}