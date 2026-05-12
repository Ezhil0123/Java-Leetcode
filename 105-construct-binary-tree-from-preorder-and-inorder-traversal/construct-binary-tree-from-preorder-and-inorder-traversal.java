/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int preIndex = 0;
    HashMap<Integer, Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return construct(preorder, 0, inorder.length-1);
    }
    private TreeNode construct(int[] preorder, int inStart, int inEnd) {
        if (inStart > inEnd) {
            return null;
        }
        int value = preorder[preIndex++];
        TreeNode root = new TreeNode(value);
        int inIndex = map.get(value);
        root.left = construct(preorder, inStart, inIndex - 1);
        root.right = construct(preorder, inIndex + 1, inEnd);
        return root;
    }
}