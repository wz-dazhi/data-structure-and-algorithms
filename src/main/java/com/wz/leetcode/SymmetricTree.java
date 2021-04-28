package com.wz.leetcode;

/**
 * @projectName: data-structure-and-algorithms
 * @package: com.wz.leetcode
 * @className: SymmetricTree
 * @description: 判断一个树是否为镜面树(对称二叉树). 力扣: https://leetcode-cn.com/problems/symmetric-tree/
 * <p>
 * 给定一个二叉树，检查它是否是镜像对称的。
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 * <pre>
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * </pre>
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 * <pre>
 *    1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 * </pre>
 * </p>
 * @author: zhi
 * @date: 2021/4/21
 * @version: 1.0
 */
public class SymmetricTree {

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

    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    private boolean isMirror(TreeNode head1, TreeNode head2) {
        if (head1 == null || head2 == null) {
            return head1 == head2;
        }
        return head1.val == head2.val && isMirror(head1.left, head2.right) && isMirror(head1.right, head2.left);
    }
}
