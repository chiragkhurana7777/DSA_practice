import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int element1 =nums[0],element2=nums[0];
        int count1=0,count2=0;

        for(int num:nums){
            if(num==element1){
                count1++;
            }
            else if(num==element2){
                count2++;
            }
            else if(count1==0){
                element1=num;
                count1++;
            }
            else if(count2==0){
                element2=num;
                count2++;
            }
            else{
                count1--;
                count2--;
            }
        }

        count1=0;
        count2=0;
        for(int num:nums){
            if(num==element1){
                count1++;
            }
            else if(num==element2){
                count2++;
            }
        }
        List<Integer> ans = new ArrayList<>();
        int n=nums.length;

        if(count1>n/3) ans.add(element1);
        if(count2>n/3) ans.add(element2);

        return ans;
    }
}