class Solution {
    public String maxSumOfSquares(int num, int sum) {
        
        if(!(sum>=0 && sum<= num*9)) return "";

        StringBuilder ans = new StringBuilder();

        int n=sum/9;
        while(n-- != 0){
            ans.append('9');
        }
        if(ans.length()<num){
        int r=sum%9;
        ans.append(Integer.toString(r));

        while(ans.length()<num){
            ans.append("0");
        }
}

        return ans.toString();
    }
}