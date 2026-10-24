void main() {
        String name = "Chirag";
        int key = 100;

        StringBuilder ans = new StringBuilder();
        key = ((key % 26) + 26) % 26;

        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);

            if (ch >= 'a' && ch <= 'z') {

                char newchar = (char) ('a' + (ch - 'a' + key) % 26);
                ans.append(newchar);
            } else if (ch >= 'A' && ch <= 'Z') {
                char newchar = (char) ('A' + (ch - 'A' + key) % 26);
                ans.append(newchar);
            } else {

                ans.append(ch);
            }

        }
        System.out.println(ans);
    }
