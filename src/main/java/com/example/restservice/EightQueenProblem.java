package com.example.restservice;

public class EightQueenProblem {
	public int row;
	public int column;
	public int N = 8;
	
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

		
		for (i = 0; i < col; i++)
			if (board[row][i] == 1)
				return false;

		
		for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
			if (board[i][j] == 1)
				return false;

		
		for (i = row, j = col; j >= 0 && i < N; i++, j--)
			if (board[i][j] == 1)
				return false;

		return true;
	}
	
	boolean solveNEUtil(int board[][], int col)
	{
		
		if (col >= N)
			return true;

		for (int i = 0; i < N; i++) {
		
			if (isSafe(board, i, col)) {
				board[i][col] = 1;

				if (solveNEUtil(board, col + 1) == true)
					return true;

				board[i][col] = 0; 
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
		
		if (solveNEUtil(board, 1) == false) {
// 			Solution does not exist
			return result;
		}
		
		result[0][0] = row+1;
		result[0][1] = column+1;
		int count = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1 && i != row && j != column) {
                    result[count][0] = i+1;
                    result[count][1] = j+1;
                    count++;
                }
            }
        }
        
		return result;
	}
	
}
