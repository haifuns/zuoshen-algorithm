package class10;

/**
 * 链表，链表相交
 * 
 * 给定两个可能有环也可能无环的单链表的头结点head1和head2，请找出两个链表相交的第一个节点，如果不存在返回null。
 * 要求时间复杂度O(N)，空间复杂度O(1)。
 * 
 * @author haifuns
 * @date 2022-07-25 19:12
 */
public class Code01_FindFirstIntersectNode {

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        // 链表1入环节点
        Node loop1 = getLoopNode(head1);
        // 链表2入环节点
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2); // 都不为环
        }
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2); // 都是环
        }
        return null; // 一个是环一个不是环一定不相交
    }

    // 给定一个链表的头节点，返回第一个入环节点，如果没有环，则返回null
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        // 快慢指针
        Node slow = head.next;
        Node fast = head.next.next;
        // 移动快慢指针到相遇
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                // 无环，直接退出
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        // 当相遇时，快指针重新从头节点开始一步一步移动，慢指针继续移动，最终会在入环位置相遇
        // 推导过程可参考leetcode142题解
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    // 如果两个链表都无环，返回第一个相交节点，如果不相交返回null
    // 丢弃长链表更长的部分，遍历长度相同的两个链表，相同的节点就是相交位置
    public static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        Node cur1 = head1;
        Node cur2 = head2;
        int sum = 0;
        while (cur1.next != null) {
            sum++; // 累计cur1的长度
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            sum--; // 减掉cur2的长度
            cur2 = cur2.next;
        }

        // sum的绝对值为长链表-短链表的长度

        // 遍历到结尾都不相等，则表示不相交
        // 两个链表相交末尾节点一定是同一个
        if (cur1 != cur2) {
            return null;
        }

        // 长链表
        cur1 = sum > 0 ? head1 : head2;
        // 短链表
        cur2 = cur1 == head1 ? head2 : head1;
        sum = Math.abs(sum);
        while (sum != 0) {
            sum--;
            cur1 = cur1.next; // 移动长链表，放弃更长的部分
        }
        while (cur1 != cur2) { // 同时遍历两个链表找到相同的位置
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    // 两个链表都有环
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        if (loop1 == loop2) { // 入环位置已经相交，第一个相交位置在入环前或者入环位置
            cur1 = head1; // 从头节点到入环位置判断相交，处理方式同无环
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else { // 入环位置没有相交，要么链表没有相交，要么在环其他位置相交
            cur1 = loop1.next;
            while (cur1 != loop1) { // 两个链表在环其他位置相交
                if (cur1 == loop2) {
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null; // 两个链表有环但是不相交
        }

    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

    }

}
