// the window must contains starting or ending elements but not middle alone without them so window should be in that range and intitially takes the k starting elements then reduce one from it and add from end of array and so on.

class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int n=cardPoints.length;
        
        int winleft=k;
        int ans=0;
        int sum=0;
        
        for(int i=0;i<winleft;i++){
            sum+=cardPoints[i];
        }
        ans=sum;
        winleft--;
        int winright=n-1;

        while(winleft>=0){
            sum-=cardPoints[winleft--];
            sum+=cardPoints[winright];
            winright--;
            if(sum>ans) ans=sum;
        } 

        return ans;
    }
}