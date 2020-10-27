package leetcode.coding;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首先我得厘清题目的意思
 *
 * LFU cache，就是 Least Frequently Used，最近最少未使用
 * 假设容量是 2，我们现在有两个 key， k1和k2，k1访问过两次，k2访问过一次
 * 此时要添加一个key，那么就要把访问次数少的那个给删除，也就是把k2删除
 * 如果两个key，k1和k2的访问频率freq都是两次，那我们就要把最近没访问的那个给删除
 *
 * 所以对于每一个key，要记录它被访问的次数
 * 那么不仅要记录次数，还要记录相同访问次数下，每个节点的访问的先后顺序，因为如果频率都一样，如果要删除，我们要把最近没访问的删除
 *
 * 时间限制O(1)，如果没这个就可以用TreeMap这玩意自动排序 时间复杂度是 log(capacity)
 *
 * 对于LRU来说，我们手写的话，利用的是 Map + 双向链表
 * 那么对于这个题来说，可以这样设计
 * Map< Integer, DoublyLinkedList> 放的是 当前freq下对应的一条双向链表
 * 那么对于这个双向列表，我们要定义一个 head 与 tail ，以及记录它的长度是多少，当我要删掉一个节点的时候
 * 双向链表需要提供这些操作：add（添加到头部）、remove（删除的是尾部，最近没用的那个给删了）
 *
 *
 * 【get操作】
 * 1、如果不存在，直接return -1
 * 2、如果存在，对应Node的freq++，从原来频率中移除节点，再放到其对应频率的双向链表中去（比如从原来freq为2的双向链表中删除，放在freq为3的双向链表中去）
 *      并且是放到头的，表示最近用过
 * 【put操作】
 * 1、如果key存在，就是更新下频率，更新value，再调用get函数即可
 * 2、如果key不存在，首先 new 一个 Node出来
 *      1、如果此时 没有到达 capacity，可以直接放
 *      2、如果已经到了 capacity，去掉一个 LFU的，也就是使用频率最低的那个，如果使用频率一样低的有多个，去掉最久没用的那个
 */
public class _460_LFU缓存 {

    class LFUCache {
        Map<Integer, ListNode> map;
        Map<Integer, DoublyLinkedList> freqMap;
        int capacity;
        int maxFreq;

        public LFUCache(int capacity) {
            map = new HashMap<>();
            freqMap = new HashMap<>();
            this.capacity = capacity;
            maxFreq = 0;
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            // 如果有这个 key，首先拿到频率
            ListNode node = map.get(key);
            int freq = node.freq;
            // 根据这个频率拿到其对应的双向链表
            DoublyLinkedList dll1 = freqMap.get(freq);
            // 从这个双向链表中删除该节点,然后在map中也需要删除
            ListNode rmv = dll1.remove(node);
            map.remove(rmv.key);
            // 把访问频率+1,拿到对应的双向链表
            DoublyLinkedList dll2 = freqMap.getOrDefault(freq + 1, new DoublyLinkedList());
            node.freq = freq + 1;
            // 更新下maxFreq
            maxFreq = Math.max(maxFreq, freq + 1);
            dll2.add(node);
            // 更新两个map，对于map可以直接覆盖，对于freqMap 尤其要注意 freqMap 要更新两个双向链表
            map.put(key, node);
            freqMap.put(freq, dll1);
            freqMap.put(freq + 1, dll2);
            return node.val;
        }

        public void put(int key, int value) {
            // 有个恶心的uc是 capacity 为 0 的情况，那就直接返回了
            if (capacity == 0) return;
            // 如果 map 中有这个key，只需要更细这个节点的val即可，再调用 get()
            if (map.containsKey(key)) {
                ListNode node = map.get(key);
                node.val = value;
                get(key);
                return;
            }
            // 如果没有这个 key，新建一个 Node，它对应的频率为 1
            ListNode node = new ListNode(key, value);
            // 拿到频率为 1 的双向链表，并调用 add 添加到头部
            DoublyLinkedList dll = freqMap.getOrDefault(1, new DoublyLinkedList());
            dll.add(node);
            map.put(key, node);
            // 如果需要删除
            if (map.size() > capacity) {
                // 如果 freq 为 1 的双向链表的长度大于 1，说明有东西可以删，否则只能从其他双向链表中删除
                if (dll.len > 1) {
                    ListNode rmv = dll.removeTail();
                    map.remove(rmv.key);
                } else {
                    // 否则就要开始找
                    for (int i = 2; i <= maxFreq; i++) {
                        if (freqMap.containsKey(i) && freqMap.get(i).len > 0) {
                            ListNode rmv = freqMap.get(i).removeTail();
                            map.remove(rmv.key);
                            break;
                        }
                    }
                }
            }
            freqMap.put(1, dll);
        }

    }

    class DoublyLinkedList{
        int len;
        ListNode head;
        ListNode tail;

        public DoublyLinkedList() {
            head = new ListNode(0, 0);
            tail = new ListNode(0, 0);
            head.next = tail;
            tail.next = head;
            len = 0;
        }

        // 添加到头部
        public void add(ListNode node) {
            ListNode temp = head.next;
            head.next = node;
            node.pre = head;
            node.next = temp;
            temp.pre = node;
            len++;
        }

        // 删除该条双向链表中指定的节点，返回删除的节点
        public ListNode remove(ListNode node) {
            ListNode pre = node.pre;
            ListNode next = node.next;
            pre.next = next;
            next.pre = pre;
            len--;
            return node;
        }

        // 删除该条双向链表的最后一个节点（tail的前一个），返回删除的节点
        public ListNode removeTail() {
            ListNode rmv = tail.pre;
            remove(rmv);
            return rmv;
        }
    }

    class ListNode{
        int key;
        int val;
        // 访问次数
        int freq;
        ListNode pre;
        ListNode next;

        public ListNode(int key, int val) {
            this.key = key;
            this.val = val;
            freq = 1;
        }
    }




}
