package com.example.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChessController {
	
	@GetMapping("/eightQueensProblem")
	public int[][] eightQueensProblem(@RequestParam(value = "row", defaultValue = "a") String row,
			@RequestParam(value = "column", defaultValue = "1") String column) {
		EightQueenProblem eightQueenProblem = new EightQueenProblem(row, column);
		int[][] result = eightQueenProblem.solveEQ();
		return result;
	}
}