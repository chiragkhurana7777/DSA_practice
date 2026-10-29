class Solution {
    public int minDays(int[] BloomDay, int m, int k) {
        int n=BloomDay.length;
        if(n<(long)m*k) return -1;

        int low=BloomDay[0];
        int high=BloomDay[0];
        for(int i=1;i<n;i++){
            if(BloomDay[i]>high) high=BloomDay[i];
            else if(BloomDay[i]<low) low=BloomDay[i];
        }

        while(low<high){
            int mid = low +(high-low)/2;
            int count=0;
            int bouque=0;
            for(int i=0;i<n;i++){
                if(BloomDay[i]<=mid){
                    count++;
                    if(count==k){
                        bouque++;
                        count=0;
                    }
                }else{
                    count=0;
                }
            }
            if(bouque<m){
                low=mid+1;
            }else{
                high=mid;
            }
        }

        return low;
    }
}