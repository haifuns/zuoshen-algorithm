/**
 * 112. 路径总和
 * 
 * https://leetcode.cn/problems/path-sum/
 * 
 * @author haifuns
 * @date 2022-06-12 18:43
 */
public class Code25_PathSum {

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

    public boolean hasPathSum(TreeNode root, int targetSum) {

        return process(root, targetSum);
    }

    public boolean process(TreeNode node, int num) {

        if (node == null) {
            return false;
        }

        // 递归
        if (node.left == null && node.right == null) {
            return num == node.val;
        }

        return process(node.left, num - node.val) || process(node.right, num - node.val);
    }
}
