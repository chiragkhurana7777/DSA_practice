import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for(int i=0;i<nums.length-2;i++){

            if(i>0 && nums[i]==nums[i-1]) {
                continue;
            }
            int left=i+1;
            int right=nums.length-1;
            while(left<right){

                int sum=nums[left]+nums[right];

                if(sum==-nums[i]){

                List<Integer> list = new ArrayList<>();

                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                
                    ans.add(list);
                    left++;
                    right--;

                    while(left<right && nums[left]==nums[left-1]) left++;
                    while(right>left && nums[right]==nums[right+1]) right--;
                }    
                else if(sum+nums[i]<0){
                     left++;

                }
                else{
                    right--;

                }
            }
        }
        return ans;
    }
}