package class10;

import java.util.Stack;

/**
 * 二叉树，非递归实现遍历
 * 
 * @author haifuns
 * @date 2022-07-26 10:04
 */
public class Code03_UnRecursiveTraversalBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    // 栈实现先序
    public static void pre(Node head) {
        System.out.print("pre-order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<Node>();
            stack.add(head);
            while (!stack.isEmpty()) {
                head = stack.pop(); // 弹出最左
                System.out.print(head.value + " ");
                if (head.right != null) { // 先压入右子节点，后压入左子节点
                    stack.push(head.right);
                }
                if (head.left != null) {
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }

    // 栈实现中序
    public static void in(Node cur) {
        System.out.print("in-order: ");
        if (cur != null) {
            Stack<Node> stack = new Stack<Node>();
            while (!stack.isEmpty() || cur != null) {
                if (cur != null) {
                    stack.push(cur); // 以cur为头的数整条左边界入栈，直到遇到空
                    cur = cur.left;
                } else {
                    cur = stack.pop(); // 弹出节点打印，弹出节点右孩子设置为cur
                    System.out.print(cur.value + " ");
                    cur = cur.right;
                }
            }
        }
        System.out.println();
    }

    // 两个栈实现后序
    public static void pos1(Node head) {
        System.out.print("pos-order: ");
        if (head != null) {
            Stack<Node> s1 = new Stack<Node>(); // 先类似先序实现头右左
            Stack<Node> s2 = new Stack<Node>(); // 不打印，栈1压入栈2，得到左右头
            s1.push(head);
            while (!s1.isEmpty()) {
                head = s1.pop(); // 头 右 左
                s2.push(head);
                if (head.left != null) {
                    s1.push(head.left);
                }
                if (head.right != null) {
                    s1.push(head.right);
                }
            }
            // 左 右 头
            while (!s2.isEmpty()) {
                System.out.print(s2.pop().value + " ");
            }
        }
        System.out.println();
    }

    // 一个栈实现后序，hard，了解即可
    public static void pos2(Node h) {
        System.out.print("pos-order: ");
        if (h != null) {
            Stack<Node> stack = new Stack<Node>();
            stack.push(h);
            Node c = null;
            while (!stack.isEmpty()) {
                c = stack.peek();
                if (c.left != null && h != c.left && h != c.right) {
                    stack.push(c.left);
                } else if (c.right != null && h != c.right) {
                    stack.push(c.right);
                } else {
                    System.out.print(stack.pop().value + " ");
                    h = c;
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        pre(head);
        System.out.println("========");
        in(head);
        System.out.println("========");
        pos1(head);
        System.out.println("========");
        pos2(head);
        System.out.println("========");
    }
}
