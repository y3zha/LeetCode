package leetcode.coding;

import java.util.*;

/**
 * 并查集
 *
 * 有相同的邮件地址则属于同一个人
 *
 * 思路：
 *      第一步，利用map统计每个邮箱出现的账户有哪几个
 *      第二步，合并
 *
 */
public class _721_账户合并 {

     class Node{
        Node parent;
        String name;
        String mail;

         public Node() {
             parent = null;
             name = null;
             mail = null;
         }
     }
     class UnionFind{
         private Node node;
         //路径压缩
         public Node find(Node x) {
             if (x.parent == x) {
                 return x;
             }
             return x.parent = find(x.parent);
         }

         public void union(Node a, Node b) {
             Node root_a = find(a);
             Node root_b = find(b);
             root_a.parent = root_b;
         }
     }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Node> map = new HashMap<>();
        UnionFind uf = new UnionFind();
        for (List<String> account : accounts) {
            String name = account.get(0);
            String m = account.get(1);
            for (int i = 1; i < account.size(); i++) {
                String mail = account.get(i);
                if (!map.containsKey(mail)) {
                    Node node = new Node();
                    node.parent = node; //把自己设置为father
                    node.name = name;
                    node.mail = mail;
                    map.put(mail, node);
                }       //把其他账户合并过去，让account集合中的email有了相同的parent
                uf.union(map.get(m), map.get(account.get(i)));

            }
        }

        Map<String, Set<String>> res = new HashMap<>();
        for (Map.Entry<String, Node> entry : map.entrySet()) {
            String mail = entry.getKey();
            Node node = uf.find(map.get(mail));
            res.putIfAbsent(node.mail, new TreeSet<>());        //用treemap存这些相同parent的email
            res.get(node.mail).add(entry.getKey());
        }

        List<List<String>> result = new ArrayList<>();
        for (Set<String> set : res.values()) {
            ArrayList<String> list = new ArrayList<>(set);
            list.add(0, map.get(list.get(0)).name);     //头部插上名字
            result.add(list);
        }
        return result;
    }


}