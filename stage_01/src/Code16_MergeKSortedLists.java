import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 23. 合并K个升序链表
 * 
 * https://leetcode.cn/problems/merge-k-sorted-lists/
 * 
 * @author haifuns
 * @date 2022-06-08 21:00
 */
public class Code16_MergeKSortedLists {

    public static class ListNode {
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

    public static class ListNodeComparator implements Comparator<ListNode> {

        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }

    }

    public ListNode mergeKLists(ListNode[] lists) {

        if (lists == null) {
            return null;
        }

        // 把所有链表放到小根堆里, 弹出第一个, 放回弹出的下一个元素, 以此循环直到所有元素弹出
        PriorityQueue<ListNode> heap = new PriorityQueue<>(new ListNodeComparator());
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                heap.add(lists[i]);
            }
        }

        if (heap.isEmpty()) {
            return null;
        }

        ListNode head = heap.poll();
        ListNode pre = head;
        if (pre.next != null) {
            heap.add(pre.next);
        }

        while(!heap.isEmpty()) {
            ListNode cur = heap.poll();
            pre.next = cur;
            pre = cur;
            if(cur.next != null) {
                heap.add(cur.next);
            }
        }

        return head;
    }

}
