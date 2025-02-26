package game;

public class TicTacToePosition implements Position {
    private int n, m, k;
    Board board;

    public TicTacToePosition(int m, int n, int k, int rhombus, Board board) {
        this.n = n;
        this.m = m;
        this.k = k;
        this.board = board;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < n
                && 0 <= move.getColumn() && move.getColumn() < m
                && board.getCell(move.getRow(), move.getColumn()) == Cell.E;
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return board.getCell(r, c);
    }
}
