import java.util.Arrays;

class Solution {
    public String[] createGrid(int m, int n) {
        StringBuilder s = new StringBuilder();
        for(int i=0;i<n;i++){
            s.append('#');
        }
        String[] ans = new String[m];
        Arrays.fill(ans,s.toString());
        for(int i=0;i<m;i++){
            if(i==0){
                ans[0] = ans[0].replace('#','.');
            }else{
                StringBuilder row = new StringBuilder(ans[i]);
                row.setCharAt(n - 1, '.');
                ans[i] = row.toString();
            }
        }
        return ans;
    }
}

class Solution1 {
    public String[] createGrid(int m, int n) {
        char[][] grid = new char[m][n];

        // Fill the entire grid with obstacles
        for (int i = 0; i < m; i++) {
            Arrays.fill(grid[i], '#');
        }

        // Open the first row
        Arrays.fill(grid[0], '.');

        // Open the last column
        for (int i = 0; i < m; i++) {
            grid[i][n - 1] = '.';
        }

        // Convert char[][] to String[]
        String[] ans = new String[m];

        for (int i = 0; i < m; i++) {
            ans[i] = new String(grid[i]);
        }

        return ans;
    }
}