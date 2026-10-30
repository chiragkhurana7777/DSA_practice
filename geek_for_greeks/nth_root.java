class Solution {
    public int nthRoot(int n, int m) {
        if(m == 1 || n == 1) return m;

        int left = 1;
        int right = m;

        while(left <= right) {
            int mid = left + (right-left)/2;

            long pow = 1;

            for(int i = 0; i < n; i++) {
                pow *= mid;

                if(pow > m) break;
            }

            if(pow == m) {
                return mid;
            }
            else if(pow < m) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        return -1;
    }
}