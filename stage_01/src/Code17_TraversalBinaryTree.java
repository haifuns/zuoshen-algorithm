/**
 * 二叉树遍历
 * 
 * @author haifuns
 * @date 2022-06-08 21:44
 */
public class Code17_TraversalBinaryTree {

    public static class Node {
        
        private int value;

        private Node left;

        private Node right;

        public Node(int value) {
            this.value = value;
        }

    }

    /**
     * 先序遍历
     */
    public static void pre(Node head) {
		if (head == null) {
			return;
		}
		System.out.println(head.value);
		pre(head.left);
		pre(head.right);
	}

    /**
     * 中序遍历
     */
    public static void in(Node head) {
        if(head == null) {
            return;
        }

        in(head.left);
        System.out.println(head.value);
        in(head.right);
    }

    /**
     * 后序遍历
     */
    public static void pos(Node head) {
        if(head == null) {
            return;
        }

        pos(head.left);
        pos(head.right);
        System.out.println(head.value);
    }

    public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);

        //    1
        //  2   3
        // 4 5 6 7
        
		pre(head);
		System.out.println("========");
		in(head);
		System.out.println("========");
		pos(head);
		System.out.println("========");

	}
}
