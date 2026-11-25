class Solution {
    public int splitArray(int[] nums, int k) {
        int n=nums.length;
        int low=nums[0];
        int high=0;
        
        for(int i=0;i<n;i++){
            if(nums[i]>low) low=nums[i];
            high+=nums[i];
        }
        

        if(n==k) return low;
        if(k==1) return high;

        while(low<high){
            int mid = low+(high-low)/2;

            int sum=0;
            int count=1;
            
            for(int num:nums){
                if(num+sum>mid){
                    count++;
                    sum=num;
                }else{
                    sum+=num;
                }
            }

            if(count<=k){
                high=mid;
            }
            else{
                low=mid+1;
            }


        }

        return low;

    }
}