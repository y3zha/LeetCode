package leetcode.coding;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.*;

/**
 * 克隆图,bfs
 *      1、第一步：需要从一个点出发，找到所有点 node -> nodes
 *      2、第二步：copy nodes 把所有的点进行copy，node1 -> node1'  node2 -> node2'。。。。
 *      3、第三步：copy edges 对于有关系的边,原图 node1 与 node2有关系，那么就把这个关系加到node1' 与 node2'上
 *
 * BFS写了两版，第二版好一点，
 * 还可以用dfs写，非常简洁
 */
public class _133_克隆图 {
    //节点定义
    class Node{
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int val,List<Node> neighbors) {
            this.val = val;
            this.neighbors = neighbors;
        }
    }



    // dfs写法
    public Node cloneGraph(Node node) {
        //node->node的克隆
        Map<Node, Node> map = new HashMap<>();
        return dfs(node, map);
    }

    private Node dfs(Node node, Map<Node, Node> map) {
        if (node == null) {
            return null;
        }
        //如果克隆过了，就返回克隆的
        if (map.containsKey(node)) {
            return map.get(node);
        }
        //克隆当前节点
        Node clone = new Node(node.val, new ArrayList<>());
        //把当前节点放进map
        map.put(node, clone);
        //对node的邻居们进行克隆
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(dfs(neighbor, map));
        }
        return clone;
    }


    //bfs简洁的写法
    public Node cloneGraph_bfs1(Node node) {
        if (node == null) {
            return null;
        }
        //node -> node的clone
        Map<Node, Node> map = new HashMap<>();

        //把起点的clone放进map
        Node clone = new Node(node.val, new ArrayList<>());
        map.put(node, clone);

        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            //对每个节点的邻居进行拷贝
            for (Node neighbor : poll.neighbors) {
                //如果之前没有放进map
                if (!map.containsKey(neighbor)) {
                    map.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
                    queue.offer(neighbor);
                }
                //当前node的clone 也要添加当前node的neighbor,但是注意！！！这里必须添加克隆过的neighbor
                //总结：克隆过的clone 添加原node邻居的clone
                map.get(poll).neighbors.add(map.get(neighbor));
            }
        }
        return clone;
    }


    //比较繁琐的bfs写法，但是运行只要 4 ms，上面两种写法都要30ms以上
    public Node cloneGraph_bfs2(Node node) {
        if (node == null) {
            return null;
        }
        // 得到所有节点
        ArrayList<Node> nodes = getNodes(node);

        //复制所有节点并创建映射 node1->node1'.....
        HashMap<Node, Node> map = new HashMap<>();
        for (Node n : nodes) {
            //副本必须是deep clone
            Node nCopy = new Node();
            nCopy.val = n.val;
            map.put(n, nCopy);
        }

        //为边创建映射
        for (Node n : nodes) {
            //获取副本,初始化它的list
            Node nCopy = map.get(n);
            nCopy.neighbors = new ArrayList<>();
            //遍历n的所有邻居
            for (Node neighbor : n.neighbors) {
                //获取邻居副本
                Node neighborCopy = map.get(neighbor);
                nCopy.neighbors.add(neighborCopy);
            }
        }
        return map.get(node);   //返回的是克隆后的
    }

    //bfs得到所有的节点
    private ArrayList<Node> getNodes(Node node) {
        ArrayList<Node> list = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        list.add(node);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node poll = queue.poll();
                // 遍历它的邻居
                for (Node neighbor : poll.neighbors) {
                    if (list.contains(neighbor)) {
                        continue;
                    }
                    list.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        return list;
    }


}