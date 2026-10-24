class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int row = 0, col = n - 1;
        while (row < m && col >= 0) {
            int num = matrix[row][col];
            if (num == target)
                return true;
            else if (num > target)
                col--;
            else
                row++;
        }
        return false;
    }
}