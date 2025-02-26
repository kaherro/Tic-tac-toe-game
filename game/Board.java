package game;

public interface Board {
    Cell getCell();
    Result makeMove(Move move);
    Cell getCell(int r, int c); 
}
