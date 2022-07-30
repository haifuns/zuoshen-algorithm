package class11;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树，将 N 叉树编码为二叉树
 * 
 * https://leetcode.cn/problems/encode-n-ary-tree-to-binary-tree/
 * 
 * @author haifuns
 * @date 2022-07-30 11:27
 */
public class Code03_EncodeNaryTreeToBinaryTree {

    // N叉树
    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    // 二叉树
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Codec {
        // N叉树编码为二叉树
        public TreeNode encode(Node root) {
            if (root == null) {
                return null;
            }
            TreeNode head = new TreeNode(root.val);
            head.left = en(root.children);
            return head;
        }

        private TreeNode en(List<Node> children) {
            TreeNode head = null;
            TreeNode cur = null;
            for (Node child : children) {
                TreeNode tNode = new TreeNode(child.val);
                if (head == null) {
                    head = tNode; // 第一个子节点
                } else {
                    cur.right = tNode; // 同级节点在右边界串联
                }
                cur = tNode;
                cur.left = en(child.children); // 深度优先，同级节点经过右边界串联后挂在父节点左子节点上
            }
            return head;
        }

        // 二叉树解码为N叉树
        public Node decode(TreeNode root) {
            if (root == null) {
                return null;
            }
            return new Node(root.val, de(root.left));
        }

        public List<Node> de(TreeNode root) {
            List<Node> children = new ArrayList<>();
            while (root != null) {
                Node cur = new Node(root.val, de(root.left)); // 深度优先
                children.add(cur); // 汇总所有右边界上的同级子节点
                root = root.right;
            }
            return children;
        }
    }
}
