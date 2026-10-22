class Solution {

    public int value(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            default: return 1000; // M
        }
    }

    public int romanToInt(String s) {
        int num = 0;

        for (int i = 0; i < s.length() - 1; i++) {

            int x = value(s.charAt(i));
            int y = value(s.charAt(i + 1));

            if (x < y)
                num -= x;
            else
                num += x;
        }

        num += value(s.charAt(s.length() - 1));

        return num;
    }
}