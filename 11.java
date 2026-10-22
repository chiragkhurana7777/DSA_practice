class Solution {
    public int maxArea(int[] height) {
        
        int i=0,j=height.length-1;
        int ans=0;
        while(i<j){
            int area=( Math.min(height[i],height[j])* (j-i) );
            if(area>ans) ans=area;

            if(height[i]>height[j]){
                j--;
            }else{
                i++;
            }

        }
        return ans;

    }
}