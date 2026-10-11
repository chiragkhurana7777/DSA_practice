class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        int m = intervals.length;
        int end = intervals[0][1];

        int ans = 0;
        for (int i = 1; i < m; i++) {
            if (intervals[i][0] < end) {
                ans++;
                end = Math.min(intervals[i][1], end);
            } else {
                end = intervals[i][1];
            }
        }
        return ans;

    }
}