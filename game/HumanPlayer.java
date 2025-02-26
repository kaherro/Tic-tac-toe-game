package game;

import java.util.InputMismatchException;
import java.io.PrintStream;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    } 

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell, int m, int n) {
        while (true) {
            out.println(cell + "'s move");
                Move move;
                while(true) {
                    try {
                        out.println("Enter row and column"); 
                        move = new Move(in.nextInt(), in.nextInt(), cell); 
                        break; 
                    } catch (InputMismatchException e){
                        System.err.println("Input is incorrect. Try again."); 
                        in.next();
                    }
                }
                if (position.isValid(move)) {
                    return move;
                }
                final int row = move.getRow();
                final int column = move.getColumn();
                out.println("Move " + move + " is invalid");
                out.println("Input error. Try again:");
        }
    }
    
    @Override
    public int question(Player player) {
        int answer1; 
        while(true) {
            try {
                System.out.println("Continue(0)/Surrender(1)/Draw(2)?");
                answer1 = in.nextInt(); 
                break; 
            } catch (InputMismatchException e){
                System.err.println("Input is incorrect. Try again."); 
                in.next();
            }
        }
        if(answer1 == 2) {
            int answer2 = player.askForDraw(); 
            if(answer2 == 0) {
                return 2; 
            }
            else {
                return 0; 
            }
        }
        return answer1; 
    }
    @Override
    public int askForDraw() {
        int answer = 0; 
        try {
            System.out.println("Accept(0)/Reject(1)?"); 
            answer = in.nextInt(); 
        } catch (InputMismatchException e){
            System.err.println("Input is incorrect. Try again."); 
            in.next();
        }
        return answer; 
    }
}
