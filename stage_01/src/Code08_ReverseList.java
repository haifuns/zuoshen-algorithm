import java.util.ArrayList;
import java.util.List;

/**
 * 反转链表
 * 
 * @author haifuns
 * @date 2022-04-28 10:06
 */
public class Code08_ReverseList {

    public static class Node {

        private int value;

        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class DoubleNode {

        private int value;

        private DoubleNode last;

        private DoubleNode next;

        public DoubleNode(int value) {
            this.value = value;
        }
    }

    /**
     * 反转单链表
     * 
     * @param node 链表头部
     * @return 反转后的链表头部
     */
    public static Node reverseLinkedList(Node head) {

        // 1 -> 2 -> 3 -> 4
        Node pre = null;
        Node next = null;
        while (head != null) {
            // null <- 1 2 -> 3 -> 4
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 反转双链表
     * 
     * @param head 链表头
     * @return 反转后的链表头
     */
    public static DoubleNode reverseDoubleLinkedList(DoubleNode head) {

        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static Node generateRandomLinkedList(int len, int value) {
        int size = (int) (Math.random() * (len + 1));
        if (size == 0) {
            return null;
        }
        size--;
        Node head = new Node((int) (Math.random() * (value + 1)));
        Node pre = head;
        while (size != 0) {
            Node cur = new Node((int) (Math.random() * (value + 1)));
            pre.next = cur;
            pre = cur;
            size--;
        }
        return head;
    }

    public static DoubleNode generateRandomDoubleList(int len, int value) {
        int size = (int) (Math.random() * (len + 1));
        if (size == 0) {
            return null;
        }
        size--;
        DoubleNode head = new DoubleNode((int) (Math.random() * (value + 1)));
        DoubleNode pre = head;
        while (size != 0) {
            DoubleNode cur = new DoubleNode((int) (Math.random() * (value + 1)));
            pre.next = cur;
            cur.last = pre;
            pre = cur;
            size--;
        }
        return head;
    }

    public static List<Integer> getLinkedListOriginOrder(Node head) {
        List<Integer> ans = new ArrayList<>();
        while (head != null) {
            ans.add(head.value);
            head = head.next;
        }
        return ans;
    }

    public static List<Integer> getDoubleListOriginOrder(DoubleNode head) {
        List<Integer> ans = new ArrayList<>();
        while (head != null) {
            ans.add(head.value);
            head = head.next;
        }
        return ans;
    }

    public static boolean checkLinkedListReverse(List<Integer> origin, Node head) {
        for (int i = origin.size() - 1; i >= 0; i--) {
            if (!origin.get(i).equals(head.value)) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public static boolean checkDoubleListReverse(List<Integer> origin, DoubleNode head) {
        DoubleNode end = null;
        for (int i = origin.size() - 1; i >= 0; i--) {
            if (!origin.get(i).equals(head.value)) {
                return false;
            }
            end = head;
            head = head.next;
        }
        for (int i = 0; i < origin.size(); i++) {
            if (!origin.get(i).equals(end.value)) {
                return false;
            }
            end = end.last;
        }
        return true;
    }

    public static void main(String[] args) {
        int maxLen = 50;
        int maxValue = 100;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            Node node1 = generateRandomLinkedList(maxLen, maxValue);
            List<Integer> list1 = getLinkedListOriginOrder(node1);
            node1 = reverseLinkedList(node1);
            if (!checkLinkedListReverse(list1, node1)) {
                System.out.println("reverse linklist failed!");
            }
        }

        System.out.println("== reverse linklist succeed ==");

        for (int i = 0; i < testTime; i++) {
            DoubleNode node3 = generateRandomDoubleList(maxLen, maxValue);
            List<Integer> list3 = getDoubleListOriginOrder(node3);
            node3 = reverseDoubleLinkedList(node3);
            if (!checkDoubleListReverse(list3, node3)) {
                System.out.println("reverse double linkedlist failed!");
            }
        }

        System.out.println("== reverse double linkedlist succeed ==");
    }
}
