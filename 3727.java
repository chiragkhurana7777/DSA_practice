import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {

        int n=nums.length;
        long[] nums1=new long[n];
        for(int i=0;i<n;i++){
            nums1[i]=(long)nums[i]*nums[i];
        }

        Arrays.sort(nums1);
        long ans=0;
        int l=0,h=n-1;

        while(l<=h){
            ans+=nums1[h];
            if(l!=h)
            ans-=nums1[l];
            h--;
            l++;
        }
        return ans;
    }
}