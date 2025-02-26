package game;

public interface Player {
    Move move(Position position, Cell cell, final int m, final int n);
    int question(Player player);
    int askForDraw();  
}
