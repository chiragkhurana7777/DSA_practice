class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int max=nums[0];
        for(int num:nums){
            if(num>max) max=num;
        }
        int ans =0;
        int l=1,h=max;
        while(l<=h){
            int mid = l + (h-l)/2;
            double sum=0;
            for(int num:nums){
                sum+= (num+mid-1)/mid;
            }
            if(sum<=threshold){
                ans=mid;
                h=mid-1;
            }
            else{
                l=mid+1;
            }
            
        }
        return ans;

    }
}