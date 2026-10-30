class Solution {
    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        long low =-10000000000L;
        long high=10000000000L;

        while(low<high){
            long mid = low + (high-low)/2;

            if(countPairs(nums1,nums2,mid)>=k){
                high=mid;
            }
            else{
                low=mid+1;
            }
        }
        return low;
    }

    long ceilDiv(long a,long b){
        return -Math.floorDiv(-a,b);
    }

    long countPairs(int[] nums1 , int[] nums2 , long mid){
        long count=0;

        for(int x:nums1){
            if(x>0){
                count+=bound(nums2,Math.floorDiv(mid,x),true);
            }
            else if(x<0){
                count+=nums2.length-bound(nums2,ceilDiv(mid,x),false);

            }
            else{
                if(mid>=0){
                    count+=nums2.length;
                }
            }
        }
        return count;
    }

    int bound(int[] arr , long target,boolean isUpper){
        int low=0;
        int high=arr.length;

        while(low<high){
            int mid = low+(high-low)/2;

            if(isUpper){

                if(arr[mid]<=target){
                    low=mid+1;
                }
                else {
                    high=mid;
                }
            }
            else{
                if(arr[mid]<target){
                    low=mid+1;
                }
                else {
                    high=mid;
                }

            }
        }

        return low;

    }

}