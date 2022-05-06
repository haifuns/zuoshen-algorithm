import java.util.List;

/**
 * 
 * 25. K 个一组翻转链表
 * 
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 * 
 * @author haifuns
 * @date 2022-05-06 19:34
 */
public class Code11_ReverseNodesInKGroup {

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

    public ListNode getKGroupEnd(ListNode start, int k) {
        while (--k != 0 && start != null) {
            start = start.next;
        }
        return start;
    }

    public static void reverse(ListNode start, ListNode end) {
        end = end.next;
        ListNode pre = null;
        ListNode cur = start;
        ListNode next = null;
        while (cur != end) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        start.next = end;
    }

    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode start = head;
        ListNode end = getKGroupEnd(start, k);
        if (end == null) {
            return head;
        }

        head = end;
        // 先反转第一组
        reverse(start, end);
        // 上一组结尾
        ListNode lastEnd = start;
        while (lastEnd.next != null) {
            start = lastEnd.next;
            end = getKGroupEnd(start, k);
            if (end == null) {
                return head;
            }
            reverse(start, end);
            lastEnd.next = end;
            lastEnd = start;
        }

        return head;
    }
}
