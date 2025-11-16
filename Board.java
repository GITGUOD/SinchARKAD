public class Board {
    private int row;
    private int col;
    private Position[][] board;
 
    public Board(int row, int col) {
        this.row = row;
        this.col = col;
        this.board = new Position[row][col];
    }

    public Position getPosition(int row, int col) {
        return board[row][col];
    }

    public void initializeBoard(String[][] colors) {
    
        Colour colour = null;
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    if(colors[r][c].equals("R")) {
                        colour = Colour.RED;
                    } else if(colors[r][c].equals("B")){
                        colour = Colour.BLUE;
                    };
                    board[r][c] = new Position(r, c, colour);
                }
            }
    }

    public Colour getColourAt(int row, int col) {
        return getPosition(row, col).getColour();
    }

}