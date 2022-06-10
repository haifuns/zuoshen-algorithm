import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 107. 二叉树的层序遍历 II
 * 
 * https://leetcode.cn/problems/binary-tree-level-order-traversal-ii/
 * 
 * @author haifuns
 * @date 2022-06-10 19:25
 */
public class Code22_BinaryTreeLevelOrderTravelsalII {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        // 利用一个queue, 从根节点开始:
        // 1. 将根节点压入queue里
        // 2. 记录当前queue中元素个数n, 依次弹出直到n个, 每次弹出时将其左右子节点压入queue
        // 3. 循环2

        List<List<Integer>> ans = new ArrayList<>();

        if (root == null) {
            return ans;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> curAns = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                curAns.add(curNode.val);
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
            }
            ans.add(0, curAns);
        }

        return ans;
    }
}
