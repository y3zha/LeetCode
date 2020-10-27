package leetcode.coding;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 拓扑排序模版
 *
 * 即是否是有向无环图，即课程间规定了前置条件，但不能构成任何环路，否则课程前置条件将不成立。
 *
 * 思路：通过课程前置条件列表 prerequisites 可以得到课程安排图的 邻接矩阵
 *
 * 拓扑排序有两种写法
 *      1、bfs 利用入度表
 *      2、dfs
 */
public class _207_课程表 {

    /**
     * BFS解决拓扑排序流程
     * 1、统计每个节点的入度，生成入度表 indegrees，用一个数组记录即可
     * 2、借助一个队列，将所有入度为0的节点入队
     * 3、当队列不空，出队元素，并删除它在图中对应的节点(pre)。并不是真的删除，而是将该节点的邻接节点(cur)的入度-1，如果邻接节点入度变为0则入队
     * 4、每次出队元素，节点总数-1，如果减为0，则是有向无环图。
     * 时间复杂度 O(N + M)O(N+M)，遍历一个图需要访问所有节点和所有临边，NN 和 MM 分别为节点数量和临边数量；
     * 空间复杂度 O(N)O(N)，为建立邻接矩阵所需额外空间。
     */

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        //入度表
        int[] indegrees = new int[numCourses];
        for (int[] p : prerequisites) {
            //把入节点加入
            indegrees[p[0]]++;
        }
        //把入度为0的节点加入队列,用for each就犯错了
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegrees.length; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            Integer pre = queue.poll();
            numCourses--;
            //把它邻接节点的入度-1，prerequisites数组中是[课程，先修课程]
            for (int[] p : prerequisites) {
                //如果不是先修课程就继续
                if (p[1] != pre) {
                    continue;
                }
                //如果是先修课程，就把它对应的课程的入度-1
                if (--indegrees[p[0]] == 0) {
                    queue.add(p[0]);
                }
            }
        }
        return numCourses == 0;
    }

    /**
     * DFS解决拓扑排序算法流程（通过 DFS 判断图中是否有环）
     * 1、借助一个标志列表 flags，用于判断每个节点 i （课程）的状态：
     *      未被 DFS 访问：i == 0，
     *      已被其他节点启动的DFS访问：i == -1
     *      已被当前节点启动的DFS访问：i == 1
     * 2、对 n 个节点依次执行 DFS，判断每个节点起步 DFS 是否存在环，若存在环直接返回 False。DFS 流程；
     *      1、递归终止：flags[i] = -1,说明当前访问节点已被其他节点启动的 DFS 访问，无需再重复搜索，return true
     *                  flag[i] = 1,说明在本轮 DFS 搜索中节点 i 被第 22 次访问，即 课程安排图有环，return false。
     *      2、访问当前节点,flags[i] = 1
     *      3、递归访问当前节点i的所有邻接节点j，发现有环直接return false
     *      4、当前节点所有邻接节点已被遍历，并没有发现环，则将当前节点 flag 置为 −1 并返回 true。
     * 3、若整个图 DFS 结束并未发现环，返回 true
     *
     * 时间、空间复杂度同BFS
     */

    public static boolean canFinish2(int numCourses, int[][] prerequisites) {
        int[][] edges = new int[numCourses][numCourses];
        int[] flags = new int[numCourses];
        for (int[] p : prerequisites) {
            //有向边
            edges[p[1]][p[0]] = 1;
    }
        //从每个节点出发去dfs
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(edges, flags, i)) {
                return false;
            }
        }
        return true;
    }

    private static boolean dfs(int[][] edges, int[] flags, int i) {
        if (flags[i] == -1) {
            return false;
        }
        if (flags[i] == 1) {
            return true;
        }
        flags[i] = 1;
        //遍历这个节点的邻居
        for (int j = 0; j < edges.length; j++) {
            if (edges[i][j] == 1 && !dfs(edges, flags, j)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 如果是单纯的遍历图的话，那么对于这个节点就是两种状态，访问过和没访问过，用个vis数组记录就可以
     * 现在这个拓扑排序，就是判断图中有没有环，我们可以设定三个状态
     * 1、 状态为-1 表示没访问过
     * 2、 状态为0，表示正在访问
     * 3、 状态为1，表示已经访问完毕
     */
    int[] vis;
    ArrayList<Integer>[] edges;

    public boolean canFinish3(int n, int[][] prerequisites) {
        vis = new int[n];
        //初始化状态
        for (int i = 0; i < n; i++) {
            vis[i] = -1;
        }
        edges = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int[] arr : prerequisites) {
            edges[arr[1]].add(arr[0]);
        }
        boolean res = true;
        //从每个节点开始dfs
        for (int i = 0; i < n; i++) {
            if (vis[i] == -1) {
                res = res && dfs2(i);
            }
        }
        return res;
    }

    private boolean dfs2(int v) {
        //设置状态为正在遍历中
        vis[v] = 0;
        boolean res = true;
        //遍历它的边
        for (Integer next : edges[v]) {
            if (vis[next] == 0) {
                return false;
            }
            if (vis[next] == -1) {
                res = res && dfs2(next);
            }
        }
        //搞完之后，确定没有环，可以设置为1，表示遍历过了
        vis[v] = 1;
        return res;
    }


    public static void main(String[] args) {
        int[][] a = {{0, 1}};
        canFinish(2, a);
    }
}