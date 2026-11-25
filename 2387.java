class Solution {
    public int median(int[][] mat) {
        
        int m = mat.length;    
        int n = mat[0].length;    
        int low=mat[0][0];
        int high=mat[0][n-1];
    
        for(int i=1;i<m;i++){
            if(mat[i][0]<low) low=mat[i][0];
            if(mat[i][n-1]>high) high=mat[i][n-1];
        }
        int required=(n*m+1)/2;
        while(low<high){
            int mid=low+ (high-low)/2;
            int count=0;
            
            for(int i=0;i<m;i++){
                count+=upperBound(mat[i],mid);
            }
            
            
            if(count<required){
                low=mid+1;
            }
            else{
                high=mid;
            }
            
            
            
        }
        return low;
        
    }
    
    int upperBound(int[] arr , int target){
        int low=0;
        int high=arr.length;
        
        while(low<high){
            int mid = low+(high-low)/2;
            
            if(arr[mid]<=target){
                low=mid+1;
            }else{
                high=mid;
            }
        }
        return low;
    }
}