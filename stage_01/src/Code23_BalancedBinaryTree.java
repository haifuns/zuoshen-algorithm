import javax.xml.ws.Holder;

/**
 * 110. 平衡二叉树
 * 
 * 一棵高度平衡二叉树定义为：一个二叉树每个节点的左右两个子树的高度差的绝对值不超过 1 。
 * 
 * https://leetcode.cn/problems/balanced-binary-tree/
 * 
 * @author haifuns
 * @date 2022-06-12 15:56
 */
public class Code23_BalancedBinaryTree {

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

    public boolean isBalanced(TreeNode root) {

        return process(root).isBalanced;
    }

    public static class TreeInfo {
        // 当前树是否平衡
        private boolean isBalanced;
        // 当前树高度
        private int height;

        public TreeInfo(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public static TreeInfo process(TreeNode x) {
        if (x == null) {
            return new TreeInfo(true, 0);
        }

        // 递归
        TreeInfo leftInfo = process(x.left);
        TreeInfo rightInfo = process(x.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalanced = leftInfo.isBalanced && rightInfo.isBalanced &&
                Math.abs(leftInfo.height - rightInfo.height) < 2;

        return new TreeInfo(isBalanced, height);
    }
}
