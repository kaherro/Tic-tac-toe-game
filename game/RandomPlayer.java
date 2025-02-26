package game;

import java.util.Random;

public class RandomPlayer implements Player {
    private final Random random;

    public RandomPlayer(final Random random) {
        this.random = random;
    }

    public RandomPlayer() {
        this(new Random());
    } 

    @Override
    public Move move(final Position position, final Cell cell, final int m, final int n) {
        while (true) {
            int r = random.nextInt(n);
            int c = random.nextInt(m);
            final Move move = new Move(r, c, cell);
            if (position.isValid(move)) {
                return move;
            }
        }
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
