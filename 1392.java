//not working self but O(n^2)

class Sol {
    public String longestPrefix(String s) {
        int n=s.length();
        int l=0,r=n-1;
        String ans="";
        while(l<n-1 && r>0){
            String s1=s.substring(0,l+1);
            String s2=s.substring(r,n);

            if(s1.equals(s2)) ans=s1;    

            l++;
            r--;
        }
        return ans;
    }
}   

// KMP Algo (KMP stands for Knuth–Morris–Pratt.)

class Solution {
    public String longestPrefix(String s) {
        int n=s.length();
        int[] lps = new int[n];

        int len=0;
        int i=1;

        while(i<n){
            if(s.charAt(i)==s.charAt(len)){
                len++;
                lps[i]=len;
                i++;
            }
            else{
                if(len>0){
                    len = lps[len-1];
                }
                else{
                    lps[i]=0;
                    i++;
                }
            }
        }
        return s.substring(0,lps[n-1]);
    }
}