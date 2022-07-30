package class11;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树，按层遍历
 * 
 * @author haifuns
 * @date 2022-07-29 19:55
 */
public class Code01_LevelTraversalBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static void level(Node head) {
        if (head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head); // 压入头节点
        while (!queue.isEmpty()) {
            Node cur = queue.poll(); // 弹出一个节点
            System.out.println(cur.value);
            if (cur.left != null) {
                queue.add(cur.left); // 有左节点压入
            }
            if (cur.right != null) {
                queue.add(cur.right); // 有右节点压入
            }
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        level(head);
        System.out.println("========");
    }
}
