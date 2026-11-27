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
    public boolean isBalanced(TreeNode root) {
        return dfsheight(root) != -1;
    }
    public static int dfsheight(TreeNode root){ 
        if(root==null) return 0;    // we are here using the same concept of finding height but also checking the diff simultaneously if it exist more than 1 return -1 and then returning it everywhere.

        int lh = dfsheight(root.left);
        if (lh==-1) return -1;

        int rh = dfsheight(root.right);
        if (rh==-1) return -1;

        if(Math.abs(lh-rh)>1) return -1;
        return 1 + Math.max(lh,rh);
    }
}