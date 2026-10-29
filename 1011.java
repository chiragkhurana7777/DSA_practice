    class Solution {
        public int shipWithinDays(int[] weights, int days) {
            int low=weights[0];
            int high=0;
            for(int num:weights){
                if(num>low) low=num;
                high+=num;
            }
            
            while(low<high){
                int mid=low+(high-low)/2;
                int day=1;
                int currentweight=0;
                for(int num:weights){
                    if(currentweight+num>mid){
                        day++;
                        currentweight=0;
                    }
                    currentweight+=num;
                }

                if(day>days){
                    low=mid+1;
                }else{
                    high=mid;
                }
            }
            return high;
        }
    }