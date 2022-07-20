package class09;

/**
 * 链表，将单向链表按某个值划分成左边小、中间相等、右边大的形式
 * 
 * @author haifuns
 * @date 2022-07-20 22:44
 */
public class Code03_SmallerEqualBigger {

    public static class Node {
        public int value;
        public Node next;

        public Node(int val) {
            this.value = val;
        }
    }

    // 利用容器实现，放到数组做partition
    public static Node listPartition1(Node head, int pivot) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        int i = 0;
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        Node[] nodeArr = new Node[i];
        i = 0;
        cur = head;
        for (i = 0; i != nodeArr.length; i++) { // 放到数组里
            nodeArr[i] = cur;
            cur = cur.next;
        }
        arrPartition(nodeArr, pivot); // 分区
        for (i = 1; i != nodeArr.length; i++) { // 恢复链表
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[i - 1].next = null;
        return nodeArr[0];
    }

    public static void arrPartition(Node[] nodeArr, int pivot) {
        int small = -1; // 小于区域右边界
        int big = nodeArr.length; // 大于区域左边界
        int index = 0;
        while (index != big) {
            if (nodeArr[index].value < pivot) {
                swap(nodeArr, ++small, index++); // 小于交换当前节点与小于区域最右+1交换，交换过来的值不会大于pivot，直接判断下一个
            } else if (nodeArr[index].value == pivot) {
                index++; // 等于直接判断下一个
            } else {
                swap(nodeArr, --big, index); // 大于与大于区域左-1交换，交换完不需要移动，接着判断交换过来的节点
            }
        }
    }

    public static void swap(Node[] nodeArr, int a, int b) {
        Node tmp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = tmp;
    }

    // 不用额外空间实现
    public static Node listPartition2(Node head, int pivot) {
        Node sH = null; // small head，小于区域头节点
        Node sT = null; // small tail，小于区域尾节点
        Node eH = null; // equal head，等于区域头节点
        Node eT = null; // equal tail，等于区域尾节点
        Node mH = null; // big head，大于区域头节点
        Node mT = null; // big tail，大于区域尾节点
        Node next = null; // 下一个要处理的节点
        // 遍历链表，将所有节点分别放到小于等于大于三个区域
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < pivot) { // 小于
                if (sH == null) {
                    sH = head; // 如果头为空，头尾都指向当前节点
                    sT = head;
                } else {
                    sT.next = head; // 头不是空，当前节点链到尾部
                    sT = head;
                }
            } else if (head.value == pivot) { // 等于
                if (eH == null) {
                    eH = head; // 如果头为空，头尾都指向当前节点
                    eT = head;
                } else {
                    eT.next = head; // 头不是空，当前节点链到尾部
                    eT = head;
                }
            } else { // 大于
                if (mH == null) {
                    mH = head; // 如果头为空，头尾都指向当前节点
                    mT = head;
                } else {
                    mT.next = head; // 头不是空，当前节点链到尾部
                    mT = head;
                }
            }
            head = next;
        }

        // 开始串联三个部分，小于区域的尾巴，连等于区域的头，等于区域的尾巴连大于区域的头
        if (sT != null) { // 如果有小于区域
            sT.next = eH;
            eT = eT == null ? sT : eT; // 下一步，谁去连大于区域的头，谁就变成eT
        }
        // 下一步，一定是需要用eT 去接 大于区域的头
        // 有等于区域，eT -> 等于区域的尾结点
        // 无等于区域，eT -> 小于区域的尾结点
        // eT 尽量不为空的尾巴节点
        if (eT != null) { // 如果小于区域和等于区域，不是都没有
            eT.next = mH;
        }
        return sH != null ? sH : (eH != null ? eH : mH);
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
        // head1 = listPartition1(head1, 4);
        head1 = listPartition2(head1, 5);
        printLinkedList(head1);

    }
}
