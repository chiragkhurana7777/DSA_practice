//bit manipulation:

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int n=nums.length;

        for(int mask=0 ; mask<(1<<n) ; mask++){
            List<Integer> temp = new ArrayList<>();

            for(int j=0;j<n;j++){
                if((mask & (1<<j)) !=0){
                    temp.add(nums[j]);
                }
            }
            ans.add(temp);
        }
        return ans;
    }
}

// back-tracking

class Solution1 {
    
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        sets(nums,0,new ArrayList<>());
        
        return ans;
    }

    public void sets(int[] nums , int start ,List<Integer> path){
        
        ans.add(new ArrayList<>(path));

        for(int i=start;i<nums.length;i++){
            path.add(nums[i]);
            sets(nums,i+1,path);
            path.remove(path.size()-1);
        }
        

    }

}