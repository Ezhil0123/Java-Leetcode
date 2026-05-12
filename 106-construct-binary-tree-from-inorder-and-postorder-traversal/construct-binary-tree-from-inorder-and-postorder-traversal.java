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
    int postIndex;
    HashMap<Integer, Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postIndex = postorder.length - 1;
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return construct(postorder, inorder, 0, inorder.length - 1);
    }
    private TreeNode construct(int[] postorder, int[] inorder, int inStart, int inEnd) {
        if (inStart > inEnd) {
            return null;
        }
        int value = postorder[postIndex--];
        TreeNode root = new TreeNode(value);
        int inIndex = map.get(value);
        root.right = construct(postorder, inorder, inIndex + 1, inEnd);
        root.left = construct(postorder, inorder, inStart, inIndex - 1);
        return root;
    }
}