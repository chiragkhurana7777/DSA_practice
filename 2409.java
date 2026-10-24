class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice,
                                 String arriveBob, String leaveBob) {

        int arriveA = dayInYear(arriveAlice);
        int leaveA = dayInYear(leaveAlice);
        int arriveB = dayInYear(arriveBob);
        int leaveB = dayInYear(leaveBob);

        int start = Math.max(arriveA, arriveB);
        int end = Math.min(leaveA, leaveB);

        return Math.max(0, end - start + 1);
    }

    private int dayInYear(String date) {
        int[] days = {
            31, 28, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31
        };

        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));

        int total = day;

        for (int i = 0; i < month - 1; i++) {
            total += days[i];
        }

        return total;
    }
}