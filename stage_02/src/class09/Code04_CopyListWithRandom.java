package class09;

import java.util.HashMap;
import java.util.Map;

/**
 * 链表，138. 复制带随机指针的链表
 * 
 * https://leetcode.cn/problems/copy-list-with-random-pointer/
 * 
 * @author haifuns
 * @date 2022-07-25 10:04
 */
public class Code04_CopyListWithRandom {
    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    // 利用额外容器
    public static Node copyRandomList1(Node head) {
        // key 老节点 -> value 新节点
        Map<Node, Node> map = new HashMap<Node, Node>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            // cur cur.next cur.random 老
            // get(cur) get(cur.next) get(cur.random) 新
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    // 不利用额外空间
    public static Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next = null;
        // 在链表基础上复制
        // 1 -> 2 -> 3 -> null
        // 1 -> 1' -> 2 -> 2' -> 3 -> 3'
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        Node copy = null;
        // 1 1' 2 2' 3 3'
        // 依次设置 1' 2' 3' random指针
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            copy.random = cur.random != null ? cur.random.next : null;
            cur = next;
        }
        Node res = head.next;
        cur = head;
        // next方向上老新混在一起，random正确
        // next方向上，把新老链表分离
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            cur.next = next;
            copy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }
}
