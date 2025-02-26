package game;

import java.util.Arrays;
import java.util.Map;


public class TicTacToeBoard implements Board {
    private static final Map<Cell, Character> SYMBOLS = Map.of( 
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.',
            Cell.C, ' '           
    );

    private final Cell[][] cells;
    private Cell turn;
    private int n, m, k; 

    public TicTacToeBoard(int m, int n, int k, int rhombus) {
        this.n = n; 
        this.m = m; 
        this.k = k; 
        if(rhombus == 0) {
            this.cells = new Cell[n][m];
            for (Cell[] row : cells) {
                Arrays.fill(row, Cell.E);
            }
        } else {
            this.cells = new Cell[n][n];
            for (Cell[] row : cells) {
                Arrays.fill(row, Cell.C);
            }
            for(int u = 0; u < (n + 1) / 2; u++) {
                for(int v = (n + 1) / 2 - u - 1; v < (n + 1) / 2 + u; v++) {
                    cells[u][v] = Cell.E; 
                    cells[(n + 1) / 2 * 2 - u - 2][v] = Cell.E;   
                }
            }
        }
        this.turn = Cell.X;
    }
    
    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Cell getCell(int r, int c) {
        return cells[r][c];
    }


    @Override
    public Result makeMove(final Move move) {
        
        cells[move.getRow()][move.getColumn()] = move.getValue();

        int inDiag1 = 0;
        int inDiag2 = 0;
        
        int inRow = 0; 
        int inColumn = 0; 
        int x = move.getRow(), y = move.getColumn();  
        int u = x, v = y; 
        while(u >= 0 && cells[u][v] == turn) {
            inColumn++; 
            u--; 
        }
        u = x + 1; 
        while(u < n && cells[u][v] == turn) {
            inColumn++; 
            u++; 
        }
        u = x; 
        while(v < m && cells[u][v] == turn) {
            inRow++; 
            v++; 
        }
        v = y - 1; 
        while(v >= 0 && cells[u][v] == turn) {
            inRow++; 
            v--; 
        }
        v = y;
        while(u >= 0 && v >= 0 && cells[u][v] == turn) {
            inDiag1++; 
            v--;
            u--; 
        } 
        u = x + 1;
        v = y + 1; 
        while(u < n && v < m && cells[u][v] == turn) {
            inDiag1++; 
            v++;
            u++; 
        }
        u = x;
        v = y;
        while(u < n && v >= 0 && cells[u][v] == turn) {
            inDiag2++; 
            u++; 
            v--; 
        } 
        u = x - 1; 
        v = y + 1; 
        while(u >= 0 && v < m && cells[u][v] == turn) {
            inDiag2++; 
            u--; 
            v++; 
        }
        if(inRow >= k || inColumn >= k || inDiag1 >= k || inDiag2 >= k) {
            return Result.WIN; 
        }
        

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("    "); 
        for (int r = 0; r < m; r++) {
            sb.append(r); 
            int len = (int) (Math.log10(r)) + 1;
            if(r == 0) {
                len = 1;
            }
            for(int i = 0; i < 4 - len; i++) {
                sb.append(" "); 
            }
        }
        sb.append('\n');
        for (int r = 0; r < n; r++) {
            sb.append("\n");
            sb.append(r);
            int len = (int) (Math.log10(r)) + 1;
            if(r == 0) {
                len = 1;
            }
            for(int i = 0; i < 4 - len; i++) {
                sb.append(" "); 
            }
            for (int c = 0; c < m; c++) {
                sb.append(SYMBOLS.get(cells[r][c]) + "   ");
            }
            if(r != n - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
