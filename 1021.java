class Solution {
    public String removeOuterParentheses(String s) {
    
        StringBuilder ans = new StringBuilder();
        int count = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                if (count > 0) ans.append(c);
                count++;
            } else {
                count--;
                if (count > 0) ans.append(c);
            }
        }

        return ans.toString();
    }


}

class Solution1 {
    public String removeOuterParentheses(String s) {
        int n=s.length();
        StringBuilder ans = new StringBuilder();
        boolean flag=false;
        int count=0;

        for(int i=0;i<n;i++){
            if(s.charAt(i)=='(' && !flag){
                flag=true;
                count++;
                continue;
            }
            if(s.charAt(i)=='('){
                count++;
                ans.append(s.charAt(i));
            }else{
                count--;
                if(count==0){
                    flag=false;
                    continue;
                }
                ans.append(s.charAt(i));
            }
        }
        return ans.toString();
        
        
    }
}