class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        
        int max=piles[0];
        for(int num:piles){
            if(num>max) max=num;
        }

        if(h==piles.length) return max;

        int l=1,high=max;
        while(l<=high){
            int mid = l+ (high-l)/2;
            long hours=0;
            for(int num:piles){
                hours += num / mid + (num % mid == 0 ? 0 : 1);
            }
            if(hours>h){
                l=mid+1;
            }
            else if(hours<=h){
                high=mid-1;
            }

        }
        return l;

    }
}