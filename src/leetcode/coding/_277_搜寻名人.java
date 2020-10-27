package leetcode.coding;

public class _277_搜寻名人 {

    // 如果 knows(i,j) 为true，说明 i 不可能是名人
    // 如果 knows(i,j) 为false， 说明 j 不可能是名人
    // 两个人总能淘汰一人
    public int findCelebrity(int n) {
        int ans = 0;
        for (int i = 1; i < n; i++) {
            if (knows(ans, i)) {
                ans = i;
            }
        }
        // 可能不存在名人，需要check，名人不认识任何人，且任何人都认识名人
        for (int i = 0; i < n; i++) {
            if (ans == i) continue;
            if (knows(ans,i) || !knows(i,ans)) return -1;
        }
        return ans;
    }



    boolean knows(int a, int b){
        return true;
    }
}