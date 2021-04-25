package com.wz.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @projectName: data-structure-and-algorithms
 * @package: com.wz.leetcode
 * @className: ConstructBinaryTreeFromPreorderAndInorderTraversal
 * @description: 从前序与中序遍历序列构造二叉树. 力扣: https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * <p>
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * 注意:
 * 你可以假设树中没有重复的元素。
 * 例如，给出
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 * <pre>
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * </pre>
 * <pre>
 * 前序遍历[3, 9, 20, 15, 7]
 *         0  1  2   3  4
 *         L1           R1
 * 中序遍历[9, 3, 15, 20, 7]
 *         0  1  2   3  4
 *         L2           R2
 *           find
 *
 * </pre>
 * </p>
 * @author: zhi
 * @date: 2021/4/25
 * @version: 1.0
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
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

    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }

        return doBuilderTree1(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode doBuilderTree1(int[] pre, int l1, int r1, int[] in, int l2, int r2) {
        // 边界检查, 避免l1超过r1
        if (l1 > r1) {
            return null;
        }
        // 只有一个节点, 直接返回
        TreeNode head = new TreeNode(pre[l1]);
        if (l1 == r1) {
            return head;
        }
        // 从中序in中找出pre的头节点
        int find = l2;
        while (in[find] != pre[l1]) {
            find++;
        }
        // 构建left
        head.left = doBuilderTree1(pre, l1 + 1, l1 + find - l2, in, l2, find - 1);
        // 构建right
        head.right = doBuilderTree1(pre, l1 + find - l2 + 1, r1, in, find + 1, r2);
        return head;
    }

    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        // 将中序节点value和索引放入map
        Map<Integer, Integer> valueIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            valueIndexMap.put(inorder[i], i);
        }

        return doBuilderTree2(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, valueIndexMap);
    }

    private TreeNode doBuilderTree2(int[] pre, int l1, int r1, int[] in, int l2, int r2, Map<Integer, Integer> valueIndexMap) {
        if (l1 > r1) {
            return null;
        }
        TreeNode head = new TreeNode(pre[l1]);
        if (l1 == r1) {
            return head;
        }
        // 从pre[l1]中找到in 头的索引
        int find = valueIndexMap.get(pre[l1]);
        head.left = doBuilderTree2(pre, l1 + 1, l1 + find - l2, in, l2, find - 1, valueIndexMap);
        head.right = doBuilderTree2(pre, l1 + find - l2 + 1, r1, in, find + 1, r2, valueIndexMap);
        return head;
    }
}
