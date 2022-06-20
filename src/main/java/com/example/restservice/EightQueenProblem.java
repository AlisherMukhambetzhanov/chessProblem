package com.example.restservice;
import java.util.HashSet;

public class EightQueenProblem {
	public int row;
	public int column;
	public int N = 8;

	public boolean checkSolution(int board[][]) {
		HashSet<Integer> row = new HashSet<Integer>();
		HashSet<Integer> col = new HashSet<Integer>();
		HashSet<Integer> mainDiag = new HashSet<Integer>();
		HashSet<Integer> revDiag = new HashSet<Integer>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 1) {
					row.add(i);
					col.add(j);
					mainDiag.add(i-j);
					revDiag.add(i+j);
				}
			}
		}

		System.out.println("ROW SIZE: " + row.size());
		System.out.println("COLUMN SIZE: " + col.size());
		System.out.println("MAIN DIAGONAL SIZE: " + mainDiag.size());
		System.out.println("REVERSE DIAGONAL SIZE: " + revDiag.size());
		return 
			row.size() == col.size() && 
			col.size() == mainDiag.size() && 
			mainDiag.size() == revDiag.size();
	}
	
	public int getChessNum(String symbol) {
		if (symbol.equals("1") || symbol.equals("a")) {
			return 0;
		} else if (symbol.equals("2") || symbol.equals("b")) {
			return 1;
		} else if (symbol.equals("3") || symbol.equals("c")) {
			return 2;
		} else if (symbol.equals("4") || symbol.equals("d")) {
			return 3;
		} else if (symbol.equals("5") || symbol.equals("e")) {
			return 4;
		} else if (symbol.equals("6") || symbol.equals("f")) {
			return 5;
		} else if (symbol.equals("7") || symbol.equals("g")) {
			return 6;
		} else {
			return 7;
		}
	}
	
	public EightQueenProblem(String row, String column) {
		this.row = getChessNum(row);
		this.column = getChessNum(column);
	}
	
	boolean isSafe(int board[][], int row, int col)
	{
		int i, j;
		
		for (i = 0; i < N; i++)
			if (board[row][i] == 1 || board[i][col] == 1)
				return false;

		
		for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
			if (board[i][j] == 1)
				return false;

		for (i = row+1, j = col+1; i < N && j < N; i++, j++)
			if (board[i][j] == 1)
				return false;
		

		for (i = row, j = col; i < N && j >= 0; i++, j--)
			if (board[i][j] == 1)
				return false;

		for (i = row-1, j = col+1; i >= 0 && j < N; i--, j++)
			if (board[i][j] == 1)
				return false;

		return true;
	}
	
	boolean solveNEUtil(int board[][], int r)
	{

		if (r == this.row) 
			r++;

		if (r >= N)
			return true;

		for (int i = 0; i < N; i++) {
			if (isSafe(board, r, i)) {
				board[r][i] = 1;

				if (solveNEUtil(board, r + 1)) {
					return true;
				}

				board[r][i] = 0; 
			}
		}

		return false;
	}
	
	int[][] solveEQ()
	{
		int board[][] = { { 0, 0, 0, 0, 0, 0, 0 ,0 },
						  { 0, 0, 0, 0, 0, 0, 0 ,0 },
						  { 0, 0, 0, 0, 0, 0, 0 ,0 },
						  { 0, 0, 0, 0, 0, 0, 0 ,0 },
						  { 0, 0, 0, 0, 0, 0, 0 ,0 },
						  { 0, 0, 0, 0, 0, 0, 0 ,0 },
						  { 0, 0, 0, 0, 0, 0, 0 ,0 },
						  { 0, 0, 0, 0, 0, 0, 0 ,0 },
						};
		board[row][column] = 1;
		int result[][] = new int[8][2];
		
		for (int i = 0; i < N; i++) {
			result[i][0] = 0;
			result[i][1] = 0;
		}
		
		if (solveNEUtil(board, 0) == false) {
// 			Solution does not exist
			return result;
		}
		
		result[0][0] = row+1;
		result[0][1] = column+1;
		int count = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1 && (i != row || j != column)) {
                    result[count][0] = i+1;
                    result[count][1] = j+1;
                    count++;
                }
            }
        }
        
        System.out.println("--------------------------------------");
        System.out.println("START POSITION");
        System.out.println("ROW: " + (this.row+1));
        System.out.println("COLUMN: " + (this.column+1) + "\n");
        if (checkSolution(board)) {
        	System.out.println("\nSolution is right\n");
        } else {
        	System.out.println("\nSolution is NOT right. Something is definitely wrong...\n");
        }
        System.out.println("--------------------------------------");

		return result;
	}
	
}
