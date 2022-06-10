import java.util.HashMap;
import java.util.Map;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 
 * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * 
 * @author haifuns
 * @date 2022-06-09 13:25
 */
public class Code21_ConstructBinaryTreeFromPreorderAndInorderTraversal {
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

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }

        return f(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    /**
     * 根据二叉树先序和中序结果构建整棵树返回头结点
     * 
     * @param pre 先序结果
     * @param l1  先序数组左位置
     * @param r1  先序数组右位置
     * @param in  中序结果
     * @param l2  中序数组左位置
     * @param r2  中序数组右位置
     * @return
     */
    public static TreeNode f(int[] pre, int l1, int r1, int[] in, int l2, int r2) {

        if (l1 > r1) {
            return null;
        }

        TreeNode head = new TreeNode(pre[l1]);

        if (l1 == r1) {
            return head;
        }

        // 找到中序数组中头结点位置, 头结点即先序第一个
        // 中序数组中从0到头结点位置, 即为左树所有元素
        int find = l2;
        while (in[find] != pre[l1]) {
            find++;
        }

        // e.g.:
        // pre: 1245367
        // int: 4251637

        // l1, find 为根结点
        // pre[l1+1,l1+find-l2], in[l2,find-1] 为左树
        // pre[l1+find-l2+1,r1], in[find+1,r2] 为右树
        head.left = f(pre, l1 + 1, l1 + find - l2, in, l2, find - 1);
        head.right = f(pre, l1 + find - l2 + 1, r1, in, find + 1, r2);

        return head;
    }

    public TreeNode buildTreev2(int[] preorder, int[] inorder) {

        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }

        Map<Integer, Integer> valueIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            valueIndexMap.put(inorder[i], i);
        }

        return g(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, valueIndexMap);
    }

    public static TreeNode g(int[] pre, int l1, int r1, int[] in, int l2, int r2, Map<Integer, Integer> valueIndexMap) {

        if (l1 > r1) {
            return null;
        }

        TreeNode head = new TreeNode(pre[l1]);

        if (l1 == r1) {
            return head;
        }

        // hash表优化, 不需要遍历
        int find = valueIndexMap.get(pre[l1]);

        head.left = g(pre, l1 + 1, l1 + find - l2, in, l2, find - 1, valueIndexMap);
        head.right = g(pre, l1 + find - l2 + 1, r1, in, find + 1, r2, valueIndexMap);

        return head;
    }
}
