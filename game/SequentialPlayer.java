package game;

public class SequentialPlayer implements Player {
    @Override
    public Move move(final Position position, final Cell cell, final int m, final int n) {
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                final Move move = new Move(r, c, cell);
                if (position.isValid(move)) { 
                    return move;
                }
            }
        }
        throw new IllegalStateException("No valid moves");
    }
    
    @Override
    public int question(Player player) {
        System.out.println("Continue(0)/Surrender(1)/Draw(2)?\n0");
        return 0; 
    }
    @Override
    public int askForDraw() {
        System.out.println("Accept(0)/Reject(1)?\n0"); 
        return 0; 
    }
}
