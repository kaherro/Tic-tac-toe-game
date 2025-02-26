package game;
import java.util.Random;

public class Game {
    private final boolean log;
    private final Player player1, player2;
    private final int n, m, k, maxi; 

    public Game(final boolean log, final Player player1, final Player player2, final int m, final int n, final int k, int maxi) {
        this.log = log;
        this.player1 = player1;
        this.player2 = player2;
        this.n = n;
        this.m = m; 
        this.k = k; 
        this.maxi = maxi; 
    }

    public int play(Board board, Position position) {
        int steps = 0; 
        System.out.println("New round started."); 
        while (true) {
            if(steps == 0) {
                System.out.println(board);
            }
            int answer = player1.question(player2); 
            if(answer == 1) {
                return 2; 
            } 
            if(answer == 2) {
                return 0; 
            } 
            final int result1 = move(board, position, player1, 1);
            if(log) System.out.println(board);
            steps++; 
            if(steps == maxi) {
                return 0; 
            } 
            if (result1 != -1) {
                return result1;
            }
            answer = player2.question(player1); 
            if(answer == 1) {
                return 1; 
            } 
            if(answer == 2) {
                return 0; 
            }
            final int result2 = move(board, position, player2, 2);
            if(log) System.out.println(board); 
            steps++; 
            if(steps == maxi) {
                return 0; 
            }
            if (result2 != -1) {
                return result2;
            }
        }
    }

    private int move(final Board board, final Position position, final Player player, final int no) {
        Move move = player.move(position, board.getCell(), m, n);
        while(!position.isValid(move)) {
            move = player.move(position, board.getCell(), m, n);
        }
        final Result result = board.makeMove(move);
        log("Player " + no + " move: " + move);
        if (result == Result.WIN) {
            log("Player " + no + " won");
            return no;
        } else if (result == Result.LOSE) {
            log("Player " + no + " lose");
            return 3 - no;
        } else if (result == Result.DRAW) {
            log("Draw");
            return 0;
        } else {
            return -1;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
