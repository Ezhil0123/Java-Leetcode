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
    public TreeNode bstFromPreorder(int[] preorder) {
        int[] i = {0};
        return bstPre(preorder, i, Integer.MAX_VALUE);
    }
    TreeNode bstPre(int[] pre, int[] i, int ub) {
        if (i[0] == pre.length || pre[i[0]] > ub) {
            return null;
        }
        TreeNode root = new TreeNode(pre[i[0]]);
        i[0]++;
        root.left = bstPre(pre, i, root.val);
        root.right = bstPre(pre, i, ub);
        return root;
    }
}