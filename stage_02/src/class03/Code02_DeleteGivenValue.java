package class03;

/**
 * 链表， 给定值删除
 * 
 * @author haifuns
 * @date 2022-06-22 22:51
 */
public class Code02_DeleteGivenValue {

    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node removeValue(Node head, int num) {
        // 移除头部为指定值的节点
        // 移动head到第一个不需要删除的位置
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }

        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next; // 将前一个node.next设置为当前这个node的下一个node
            } else {
                pre = cur;
            }
            cur = cur.next;
        }

        return head;
    }
}
