package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Random;

public class Main {
    public static int playPair(int m, int n, int k, int rhombus, Player player1, Player player2, int maxi) {
        final Game game = new Game(true, player1, player2, m, n, k, maxi);
        int result = 0;
        Board board = new TicTacToeBoard(m, n, k, rhombus); 
        result = game.play(board, new TicTacToePosition(m, n, k, rhombus, board));
        return result; 
    }

    public static void main(String[] args) {
        int n, m, k;
        int maxi = 0; 
        int rhombus = 1;
        boolean tournament = true;
        int players_num = 11; // must be >= 3
        Scanner in = new Scanner(System.in);
        while(true) {
            try {
                System.out.println("Enter m, n, k:");
                m = in.nextInt(); 
                n = in.nextInt(); 
                k = in.nextInt(); 
                break; 
            } catch (InputMismatchException e){
                System.err.println("Input is incorrect. Try again.");
                in.next();
            }
        }

        if(rhombus == 1) {
            n = n * 2 - 1; 
            m = n; 
            for(int i = 1; i < n; i += 2) {
                maxi += i * 2; 
            }
            maxi += n; 
        } else {
            maxi = m * n; 
        }

        if(tournament) {
            ArrayList<ArrayList<Integer>> results = new ArrayList<>();
            ArrayList<Integer> high = new ArrayList<>();
            ArrayList<Integer> low = new ArrayList<>();
            
            ArrayList<Player> playersTypes = new ArrayList<>(Arrays.asList(new RandomPlayer(), new RandomPlayer()));
            for(int i = 0; i < players_num - 2; i++) {
                playersTypes.add(new RandomPlayer()); 
            }

            for(int i = 1; i <= players_num; i++) {
                high.add(i); 
            }

            while(high.size() > 1 || low.size() > 2) {
                Collections.shuffle(high);
                Collections.shuffle(low);
                ArrayList<Integer> high_next = new ArrayList<>();
                ArrayList<Integer> low_next = new ArrayList<>();
                ArrayList<Integer> left = new ArrayList<>(); 
                int num = 1; 
                while(num * 2 <= high.size()) {
                    num *= 2; 
                }
                for(int i = 0; i + 1 < num; i += 2) {
                    int result = playPair(m, n, k, rhombus, playersTypes.get(high.get(i) - 1), 
                        playersTypes.get(high.get(i + 1) - 1), maxi); 
                    while(result == 0) {
                        result = playPair(m, n, k, rhombus, playersTypes.get(high.get(i) - 1), 
                        playersTypes.get(high.get(i + 1) - 1), maxi); 
                    }
                    System.out.println("Game result: " + high.get(i + result - 1));
                    if(result == 2) {
                        high_next.add(high.get(i + 1)); 
                        low_next.add(high.get(i)); 
                    }
                    else {
                        high_next.add(high.get(i)); 
                        low_next.add(high.get(i + 1)); 
                    }
                }
                for(int i = num; i < high.size(); i++) {
                    high_next.add(high.get(i)); 
                }
                
                num = 1; 
                while(num * 2 <= low.size()) {
                    num *= 2; 
                }
                for(int i = 0; i + 1 < num; i += 2) {
                    int result = playPair(m, n, k, rhombus, playersTypes.get(low.get(i) - 1), 
                        playersTypes.get(low.get(i + 1) - 1), maxi); 
                    while(result == 0) {
                        result = playPair(m, n, k, rhombus, playersTypes.get(low.get(i) - 1), 
                        playersTypes.get(low.get(i + 1) - 1), maxi); 
                    }
                    System.out.println("Game result: " + low.get(i + result - 1));
                    if(result == 2) {
                        low_next.add(low.get(i + 1));  
                        left.add(low.get(i)); 
                    }
                    else {
                        low_next.add(low.get(i)); 
                        left.add(low.get(i + 1)); 
                    }
                }
                for(int i = num; i < low.size(); i++) {
                    low_next.add(low.get(i)); 
                }
                if(high_next.size() >= 1) high = new ArrayList<>(high_next); 
                if(low_next.size() >= 2) low = new ArrayList<>(low_next); 
                results.add(left); 
            }
            int result = 0;
            while(result == 0) {
                result = playPair(m, n, k, rhombus, playersTypes.get(low.get(0) - 1), 
                    playersTypes.get(low.get(1) - 1), maxi); 
            }
            if(result == 2) {
                high.add(low.get(1)); 
                results.add(new ArrayList<>(Arrays.asList(low.get(0)))); 
            }
            else {
                high.add(low.get(0));
                results.add(new ArrayList<>(Arrays.asList(low.get(1)))); 
            }

            result = 0;
            while(result == 0) {
                result = playPair(m, n, k, rhombus, playersTypes.get(low.get(0) - 1), 
                    playersTypes.get(low.get(1) - 1), maxi); 
            }
            if(result == 2) {
                results.add(new ArrayList<>(Arrays.asList(high.get(0))));  
                results.add(new ArrayList<>(Arrays.asList(high.get(1)))); 
            }
            else {
                results.add(new ArrayList<>(Arrays.asList(high.get(1)))); 
                results.add(new ArrayList<>(Arrays.asList(high.get(0)))); 
            }
            
            for(int i = 1; i < results.size(); i++) {
                System.out.print("Place #" + (results.size() - i) + ": ");
                for(int j = 0; j < results.get(i).size(); j++) {
                    System.out.print(results.get(i).get(j) + " ");
                }
                System.out.println();
            }
        }
        else {
            int result;
            do {
                result = playPair(m, n, k, rhombus, new RandomPlayer(), new RandomPlayer(), maxi);
            } while (result != 0); 
            System.out.println("Game result: " + result);
        }
        in.close();         
    }
}
