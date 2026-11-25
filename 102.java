import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<List<Integer>>(); // final ans to return
        Queue<TreeNode> queue = new LinkedList<TreeNode>(); // make a queue to add node and then add their left and right after that add the node in the sublist.
        if(root==null){
            return ans;
        }
        queue.offer(root); // add first element
        while(!queue.isEmpty()){
            int size=queue.size(); // so that the loop only check the element inside the queue.
            List<Integer> sublist= new LinkedList<Integer>();
            for(int i=0;i<size;i++){
                if(queue.peek().left!=null) queue.offer(queue.peek().left);
                if(queue.peek().right!=null) queue.offer(queue.peek().right);

                sublist.add(queue.poll().val);
            } 
            ans.add(sublist);
        }
        return ans;

    }
}