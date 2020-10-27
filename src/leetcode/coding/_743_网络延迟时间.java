package leetcode.coding;
/*
  经典图论题，那dijkstra、floyd、spfa写一下

  单源最短路径
       非负权边 dijkstra O(N^2)，可以用pq优化到(mlogN) 无论图有没有环，都可以用
       带负权边 BellmanFord O(mn)，升级版SPFA（适合于稀疏图）
  多源最短路径
       floyd O(N^3)
  https://blog.csdn.net/qq_36932169/article/details/78806863

  spfa的复杂度略高于dij，所以适合稀疏图（边少），而dij更适合稠密图（边多），spfa适用范围更广一点，可以处理负权边
  floyd有个神奇的特性，这个是其他算法没有的，floyd第k轮算的结果，是每个源点到每个汇点经过前k个点的最短路
 */


import java.util.*;

public class _743_网络延迟时间 {

    /*
        堆优化dijkstra
        times中的数组 a a[0]代表源，a[1]代表目标，a[2]代表权值，单向的
        一般堆优化的都用map< Integer,List< int[]>>来存放源到目的节点及权值
        朴素dijkstra直接用数组就可以了

        dijkstra 需要 dis数组和vis数组，都开N+1
     */

    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        //存放边信息
        for (int[] arr : times) {
            List<int[]> list = map.getOrDefault(arr[0], new ArrayList<>());
            list.add(new int[]{arr[1], arr[2]});
            map.put(arr[0], list);
        }

        //初始化dis数组和vis数组
        int[] dis = new int[N + 1];
        Arrays.fill(dis, 0x3f3f3f3f);
        boolean[] vis = new boolean[N + 1];

        //起点的dis为0，但是别忘记0也要搞一下，因为它是不参与的，但是我计算结果的时候直接用了stream，所以这个0也就要初始化下了
        dis[K] = 0;
        dis[0] = 0;

        //new一个小堆出来，按照dis升序排，一定要让它从小到大排，省去了松弛工作
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> dis[o1] - dis[o2]);
        //把起点放进去
        queue.offer(K);

        while (!queue.isEmpty()) {
            //当队列不空，拿出一个源出来
            Integer poll = queue.poll();
            //把它标记为访问过
            vis[poll] = true;
            //遍历它的邻居们，当然可能没邻居，这里用getOrDefault处理就很方便
            List<int[]> list = map.getOrDefault(poll, Collections.emptyList());
            for (int[] arr : list) {
                int next = arr[0];
                //如果这个邻居访问过了，继续
                if (vis[next]) continue;
                //更新到这个邻居的最短距离，看看是不是当前poll出来的节点到它更近一点
                dis[next] = Math.min(dis[next], dis[poll] + arr[1]);
                //如果堆中有这个节点，就要先把它剔除，再放进去
                queue.remove(next);
                queue.offer(next);
            }
        }
        //拿到数组中的最大值比较下，返回结果
        int res = Arrays.stream(dis).max().getAsInt();
        return res == 0x3f3f3f3f ? -1 : res;
    }

    /*
    【介绍】
    SPFA是一种用队列优化的B-F算法，在稀疏图中，采用类似邻接链表储存比较节省空间。
    dis数组：储存起点到各个点的距离，dis[i]就是起点到i点的距离。 初始自己到自己为0，其他都是INF   ，dis的初始化可以用-1也可以INF，看情况
    vis数组：标记点是否访问过

    【算法思想】
    1、初始时，只有把起点放入队列中。
    2、遍历与起点相连的边，如果可以松弛就更新距离dis[],然后判断如果这个点没有在队列中就入队标记。
    3、出队队首，取消标记，循环2-3步，直至队为空。
    3、所有能更新的点都更新完毕，dis[]数组中的距离就是，起点到其他点的最短距离。

    【为什么SPFA可以处理负边】
    因为在SPFA中每一个点松弛过后说明这个点距离更近了，所以有可能通过这个点会再次优化其他点
    所以将这个点入队再判断一次，而Dijkstra中是贪心的策略，每个点选择之后就不再更新，如果碰到了负边的存在就会破坏这个贪心的策略就无法处理了。

    【如何判断成环】
    在储存边时，记录下每个点的入度，每个点入队的时候记录一次，如果入队的次数大于这个点的入度，说明从某一条路进入了两次，即该点处成环。

     */

    //SPFA：用邻接表写
    public int networkDelayTime2(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        //构建临街表
        for (int[] arr : times) {
            List<int[]> list = map.getOrDefault(arr[0], new ArrayList<>());
            list.add(new int[]{arr[1], arr[2]});
            map.put(arr[0], list);
        }
        //初始化dis数组和vis数组
        int[] dis = new int[N + 1];
        int INF = 0x3f3f3f3f;
        Arrays.fill(dis, INF);  //dis的初始化可以-1，也可以INF，看情况
        boolean[] vis = new boolean[N + 1];
        dis[K] = dis[0] = 0;

        //new一个队列，而不是堆。放入起点
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(K);

        while (!queue.isEmpty()) {
            //取出队首节点
            Integer poll = queue.poll();
            //遍历起点的邻居,更新距离
            List<int[]> list = map.getOrDefault(poll, Collections.emptyList());
            for (int[] arr : list) {
                int next = arr[0];
                //如果没更新过，或者需要更新距离()
                if (dis[next] == INF || dis[next] > dis[poll] + arr[1]) {
                    //更新距离
                    dis[next] = dis[poll] + arr[1];
                    //如果没有访问过，才入队 （那么判断入度可以在这里做文章）
                    if (!vis[next]) {
                        vis[next] = true;
                        queue.offer(next);
                    }
                }
            }
            //当遍历完 当前节点的邻居后，就可以重置为false了！！！这就是可以重复入队，可以再更新，而不是像dijkstra那样贪心
            vis[poll] = false;
        }
        int res = Arrays.stream(dis).max().getAsInt();
        return res == INF ? -1 : res;
    }

    //SPFA：用邻接矩阵写，写的时候一直死循环
    //[[1,2,1],[2,1,3]] 环的情况
    public int networkDelayTime3(int[][] times, int N, int K) {
        int[][] edge = new int[N + 1][N + 1];
        //初始化edge
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                edge[i][j] = i == j ? 0 : -1;
            }
        }
        for (int[] arr : times) {
            edge[arr[0]][arr[1]] = arr[2];
        }

        //初始化
        int[] dis = new int[N + 1];
        boolean[] vis = new boolean[N + 1];
        int INF = 0x3f3f3f3f;
        Arrays.fill(dis, INF);
        dis[K] = dis[0] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(K);

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            //这里就和邻接链表不太一样，要遍历所有的节点，看是不是邻居关系，所以用邻接链表更适合
            for (int i = 1; i <= N; i++) {
                /*
                如果是邻居，，一定要在这一步全部写完，如果能更新才能判断能不能入队，
                不要写成下面这样，血的教训，这样是判断邻居，但是不更新，也能入队！
                if (edge[poll][i] != -1) {
                    //更新
                    if (dis[i] == INF || dis[i] > dis[poll] + edge[poll][i]) {
                        dis[i] = dis[poll] + edge[poll][i];
                    }
                    if (!vis[i]) {
                        vis[i] = true;
                        queue.offer(i);
                    }
                }
                 */

                if (edge[poll][i] != -1 && dis[i] == INF || dis[i] > dis[poll] + edge[poll][i]) {
                    //更新
                    dis[i] = dis[poll] + edge[poll][i];
                    if (!vis[i]) {
                        vis[i] = true;
                        queue.offer(i);
                    }
                }
            }
            vis[poll] = false;
        }
        int res = Arrays.stream(dis).max().getAsInt();
        return res == INF ? -1 : res;
    }

    /**
     * floyd
     * 一般用邻接矩阵
     * 并且它不用dis和vis
     */
    public int networkDelayTime4(int[][] times, int N, int K) {
        int[][] edge = new int[N + 1][N + 1];
        int INF = 0x3f3f3f3f;
        //初始化
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                edge[i][j] = i == j ? 0 : INF;  //注意，我一开始距离是初始化为INF的，而不是-1，因为要取min的啊
            }
        }
        for (int[] arr : times) {
            edge[arr[0]][arr[1]] = arr[2];
        }


        //使用K节点来松弛i->j的最短路径（大白话就是利用k作为中间节点）
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i != k && j != k && edge[i][k] != -1 && edge[k][j] != -1) {
                        edge[i][j] = Math.min(edge[i][j], edge[i][k] + edge[k][j]);
                    }
                }
            }
        }
        //拿结果
        int res = 0;
        for (int distance : edge[K]) {
            res = Math.max(res, distance);
        }
        return res == INF ? -1 : res;
    }

    /**
     * dijkstra和spfa的dis都可以设置为INF，或者-1，看情况
     * 但是floyd只能设置为INF！floyd是不用dis和vis的
     */



}