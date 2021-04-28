package com.wz.leetcode;

/**
 * @projectName: data-structure-and-algorithms
 * @package: com.wz.leetcode
 * @className: BalancedBinaryTree
 * @description: 平衡二叉树. 力扣: https://leetcode-cn.com/problems/balanced-binary-tree/
 * <p>
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 * <pre>
 * 示例1:
 *      3
 *     / \
 *    9  20
 *       / \
 *      15  7
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：true
 * </pre>
 * <pre>
 * 示例2:
 *          1
 *         / \
 *        2   2
 *       / \
 *      3   3
 *     / \
 *    4   4
 * 输入：root = [1,2,2,3,3,null,null,4,4]
 * 输出：false
 * </pre>
 * <pre>
 * 示例3:
 * 输入：root = []
 * 输出：true
 * </pre>
 * </p>
 * @author: zhi
 * @date: 2021/4/28
 * @version: 1.0
 */
public class BalancedBinaryTree {
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

    private static class Info {
        boolean isBalanced;
        int height;

        public Info(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public boolean isBalanced(TreeNode root) {
        return processTree(root).isBalanced;
    }

    private Info processTree(TreeNode x) {
        if (x == null) {
            return new Info(true, 0);
        }
        // 处理左边
        final Info leftInfo = processTree(x.left);
        // 处理右边
        final Info rightInfo = processTree(x.right);
        // 获取最大的高度然后自身的高度
        final int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        // 左边是平衡的, 右边是平衡的. 并且左右两边差的绝对值小于等于1
        final boolean isBalanced = leftInfo.isBalanced && rightInfo.isBalanced && Math.abs(leftInfo.height - rightInfo.height) < 2;
        return new Info(isBalanced, height);
    }
}
