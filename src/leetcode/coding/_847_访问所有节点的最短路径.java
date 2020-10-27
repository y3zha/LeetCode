package leetcode.coding;

import java.util.*;

/**
 * 记录当前已经访问的节点（最多只有12个节点，用12bit记录）。
 * 用队列实现广度优先搜索，判断当前的状态是否曾经访问过，如果曾经访问过就跳过。
 * 用一个list存放状态，大小为N*2^N。逐层访问。
 * https://www.cnblogs.com/grandyang/p/11410007.html
 * 这道题说是给了一个无向图，里面有N个结点，让我们找到一条可以经过所有结点的路径，该路径的起点和终点任意选，只要能经过所有结点即可
 * 这里的每个结点和边都可以重复经过，问这样一条路径的最短长度是多少，注意这里的长度不是路径结点的个数，而是结点中的边的个数。
 *
 * 多起点的BFS 扩散题真的是，很经典。
 */
public class _847_访问所有节点的最短路径 {

    //bfs + 状态标记
    public int shortestPathLength(int[][] g) {
        int n = g.length;
        boolean[][] vis = new boolean[n][1 << n];   //开状态
        //这样每个结点都可以被分别编码进对应位，则遍历过n个结点的十进制数就是 2^n-1 了
        // 那么终止状态就是所有的都遍历过了，11111，也即是2^n-1
        int fs = (1 << n) - 1;  //final state
        Queue<int[]> queue = new LinkedList<>();

        //n个节点放进队列中去 起始状态，由于最短路径的起点不定，那么这里的 BFS 的起点就应该是所有的结点，将每个结点都当作起始结点，并将结点编号编码到十进制数中
        for (int i = 0; i < n; i++) {
            queue.offer(new int[]{i, 1 << i});
        }
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] poll = queue.poll();
                if (poll[1] == fs) {    //如果到了final state 就return
                    return step;
                }
                //否则就遍历他的邻居的编号
                for (int nei : g[poll[0]]) {
                    //对于每个相邻的结点 next，由于在之前的基础上又加上了结点 next，这也要编码进去
                    //或运算，只要有一个位上是1就是1那就算它遍历过了。所以要 ‘或’ 上1 << nei
                    int ns = poll[1] | (1 << nei);
                    // System.out.println("poll[1]: " + poll[1] + "  " + " 1<<nei: " + (1 << nei) + "  " + " ns: " + ns);
                    // System.out.println();
                    // 然后在 visted 集合中查找该新状态是否存在，不存在的话加入 visited 集合，并把编码成的十进制数 path 和当前结点编号 next 组成新的 pair 对儿加入队列进行下次遍历。
                    if (vis[nei][ns]) {
                        continue;
                    }
                    vis[nei][ns] = true;
                    queue.offer(new int[]{nei, ns});
                }
            }
            step++;
        }
        return step;
    }


    /**
     * 写法2，状压dp
     * dp[i][j] 表示的某个状态时经过的结点编码成的十进制数i，且当前位置为结点j时的路径长度
     * 这样的话只要当i到达 2^n-1 的时候，此时所有的 dp[2^n-1][j] 中的最小值即为所求
     * 和bfs一样 将n个结点都当作起始点
     *
     * 新建一个 boolean 型变量 repeat，进行 repeat 为 true 的循环。在循环中，先将 repeat 赋值为 false，然后遍历所有结点
     * 对于遍历到的结点i，取出其编码值为 cur 的 dp 值 dist，然后遍历与其相连的所有结点，对于每个遍历到的结点 next，
     * 还是先用 cur ‘或’ 上 1 << next 得到新的编码值，然后进行松弛操作 Relaxation，即若 dp[path][next] 值大于 dist+1，则用 dist+1 来更新 dp[path][next]。
     *
     * 同时判断若 path 和 cur 相等，将 repeat 赋值为 true，从而需要再次进行循环。
     * 因为若 path 等于 cur，说明该相邻结点 next 在之前已经被编码进 cur 了，但依然能进行松弛操作的话，就需要再次遍历一遍所有结点
     * 以保证在当前编码 cur 的情况下，将所有的松弛操作都进行完。最后用 dp 数组的最后一行来更新结果 res，其中的最小值即为所求
     */
    public int shortestPathLength2(int[][] g) {
        int n = g.length;
        int[][] dp = new int[1 << n][n];
        for (int[] ints : dp) {
            Arrays.fill(ints, 0x3f3f3f3f);
        }
        //init 起始节点
        for (int i = 0; i < n; i++) {
            dp[1 << i][i] = 0;
        }
        for (int i = 0; i < 1 << n; i++) {
            boolean repeat = true;
            while (repeat) {
                repeat = false;
                for (int j = 0; j < n; j++) {
                    int dist = dp[i][j];
                    //next
                    for (int next : g[j]) {
                        int path = i | (1 << next);
                        if (dist + 1 < dp[path][next]) {
                            dp[path][next] = dist + 1;
                            if (path == i) {
                                repeat = true;
                            }
                        }
                    }
                }
            }
        }

        int res = 0x3f3f3f3f;
        for (int i = 0; i < n; i++) {
            res = Math.min(res, dp[(1 << n) - 1][i]);
        }
        return res;
    }


}