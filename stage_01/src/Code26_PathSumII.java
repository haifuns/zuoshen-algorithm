import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 113. 路径总和 II
 * 
 * https://leetcode.cn/problems/path-sum-ii/
 * 
 * @author haifuns
 * @date 2022-06-12 19:33
 */
public class Code26_PathSumII {

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

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {

        List<List<Integer>> ans = new ArrayList<>();
        process(root, new LinkedList<Integer>(), targetSum, ans);
        return ans;
    }

    // 递归实现
    public void process(TreeNode node, List<Integer> path, int num, List<List<Integer>> ans) {

        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            if (num == node.val) {
                path.add(node.val);
                ans.add(copy(path));
                path.remove(path.size() - 1);
            }
            return;
        }

        path.add(node.val);

        if (node.left != null) {
            process(node.left, path, num - node.val, ans);
        }

        if (node.right != null) {
            process(node.right, path, num - node.val, ans);
        }

        path.remove(path.size() - 1);
    }

    public List<Integer> copy(List<Integer> list) {
        List<Integer> newList = new LinkedList<Integer>();
        newList.addAll(list);
        return newList;
    }
}
