/**
 * 2. 两数相加
 * 
 * https://leetcode.cn/problems/add-two-numbers/
 * 
 * @author haifuns
 * @date 2022-05-13 11:49
 */
public class Code12_AddTwoNumbers {

    public class ListNode {

        int val;

        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode addTwoNumbers(ListNode head1, ListNode head2) {

        // 先遍历两个链表，把长链表找出来
        int len1 = listLength(head1);
        int len2 = listLength(head2);

        // 长链表
        ListNode l = len1 > len2 ? head1 : head2;
        // 短链表
        ListNode s = l == head1 ? head2 : head1;

        // 长链表当前节点
        ListNode curL = l;
        // 短链表当前节点
        ListNode curS = s;
        //
        ListNode last = curL;

        // 进位
        int carry = 0;
        // 当前和
        int curNum = 0;

        while (curS != null) {
            curNum = curS.val + curL.val + carry;
            curL.val = curNum % 10;
            carry = curNum / 10;
            last = curL;
            curL = curL.next;
            curS = curS.next;
        }

        while (curL != null) {
            curNum = curL.val + carry;
            curL.val = curNum % 10;
            carry = curNum / 10;
            last = curL;
            curL = curL.next;
        }

        if (carry != 0) {
            last.next = new ListNode(carry);
        }

        return l;
    }

    /**
     * 求链表长度
     */
    public static int listLength(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }
}
