class Solution {
    public boolean isValidSudoku(char[][] board) {
        
        HashSet<Character>[] row = new HashSet[9];
        HashSet<Character>[] col = new HashSet[9];
        HashSet<Character>[] box = new HashSet[9];

        for(int i=0;i<9;i++){
            row[i]=new HashSet<>();
            col[i]=new HashSet<>();
            box[i]=new HashSet<>();
        }
        
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){

                char num=board[i][j];
                if(num=='.') continue;

                int boxindex=(i/3)*3+(j/3);

                if(!row[i].add(num) || !col[j].add(num) || !box[boxindex].add(num)){
                    return false;
                }

            }
        }

        return true;
    }
}