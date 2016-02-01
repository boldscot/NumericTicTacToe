/**
 * @file        TicTacToe.java
 * @author      Stephen Collins 20061696
 * @assignment  NumericTicTacToe
 * @brief       Attempted solution
 * @date		01/02/2016
 *
 * @notes		 
 */
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import edu.princeton.cs.introcs.StdDraw;

public class NumericTicTacToe {

	private static int board [][], move, EMPTY;
	private static ArrayList<Integer> playerArray, cpuArray;
	private static boolean winner, DEBUG; 

	public static void main(String[] args) throws Exception{

		//debug flag, set to false to stop printouts 
		DEBUG = false;

		// Allocate identifiers to represent game state
		// Using an array of int so that summing along a row, a column or a
		// diagonal is a easy test for a win
		winner = false;
		move = 0;
		board = new int[3][3];

		//set up arraylist containing numbers the human uses
		playerArray = new ArrayList<Integer>();
		for(int i = 2; i <= 9 ; i+=2){
			playerArray.add(i);
		}
		if (DEBUG)System.out.println("Player" + playerArray);

		//set up arraylist containing numbers the cpu uses
		cpuArray = new ArrayList<Integer>();
		for(int i = 1; i <= 9 ; i+=2){
			cpuArray.add(i);
		}
		if (DEBUG)System.out.println("CPU " + cpuArray);

		// Setup graphics and draw empty board
		StdDraw.setPenRadius(0.04);								// draw thicker lines
		StdDraw.line(0, 0.33, 1, 0.33);
		StdDraw.line(0, 0.66, 1, 0.66);
		StdDraw.line(0.33, 0, 0.33, 1.0);
		StdDraw.line(0.66, 0, 0.66, 1.0);
		StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 64)); // Font SIZE!

		//set up a grid jframe
		JFrame frame = new JFrame("");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		int col, row;
		row = 0;
		col = 0;

		while(winner || move < 10) {

			//cpu move code
			if (move % 2 == 0){

				int index = 0;
				int number = 0;
				int turnValues [] = new int [3];

				if (move == 0){
					col = (int) (Math.random() * 3);
					row = (int) (Math.random() * 3);
				}

				if (checkPotentialWinRow(cpuArray) != null) {
					turnValues = checkPotentialWinRow(cpuArray);
					row = turnValues[0];
					col = turnValues[1];
					number = turnValues[2];
					index = cpuArray.indexOf(number);
				}
				else if (checkPotentialWinColumn(cpuArray) != null) {
					turnValues = checkPotentialWinColumn(cpuArray);
					row = turnValues[0];
					col = turnValues[1];
					number = turnValues[2];
					index = cpuArray.indexOf(number);
				}
				else if (checkPotentialWinDiag_1(cpuArray) != null) {
					turnValues = checkPotentialWinDiag_1(cpuArray);
					row = turnValues[0];
					col = turnValues[1];
					number = turnValues[2];
					index = cpuArray.indexOf(number);
				}
				else if (checkPotentialWinDiag_2(cpuArray) != null) {
					turnValues = checkPotentialWinDiag_2(cpuArray);
					row = turnValues[0];
					col = turnValues[1];
					number = turnValues[2];
					index = cpuArray.indexOf(number);
				}
				else if (checkPotentialWinRow(playerArray) != null) {
					turnValues = checkPotentialWinRow(playerArray);
					row = turnValues[0];
					col = turnValues[1];

					//generate random number within arraylist and get the element at that index
					index = (int) (Math.random() * cpuArray.size() );
					number = cpuArray.get(index);
				}
				else if (checkPotentialWinColumn(playerArray) != null) {
					turnValues = checkPotentialWinColumn(playerArray);
					row = turnValues[0];
					col = turnValues[1];

					//generate random number within arraylist and get the element at that index
					index = (int) (Math.random() * cpuArray.size() );
					number = cpuArray.get(index);
				}
				else if (checkPotentialWinDiag_1(playerArray) != null) {
					turnValues = checkPotentialWinDiag_1(playerArray);
					row = turnValues[0];
					col = turnValues[1];
					
					//generate random number within arraylist and get the element at that index
					index = (int) (Math.random() * cpuArray.size() );
					number = cpuArray.get(index);
				}
				else if (checkPotentialWinDiag_2(playerArray) != null) {
					turnValues = checkPotentialWinDiag_2(playerArray);
					row = turnValues[0];
					col = turnValues[1];
					
					//generate random number within arraylist and get the element at that index
					index = (int) (Math.random() * cpuArray.size() );
					number = cpuArray.get(index);
				}
				else {
					while(board[row][col]!= EMPTY) {
						col = (int) (Math.random() * 3);
						row = (int) (Math.random() * 3);
					}

					//generate random number within arraylist and get the element at that index
					index = (int) (Math.random() * cpuArray.size() );
					number = cpuArray.get(index);
				}
				String stringNumber = Integer.toString(number);

				double x = col * .33 + 0.15;
				double y = row * .33 + 0.15;
				StdDraw.text(x, y, stringNumber );
				move++;
				board[row][col] = number;

				//remove the used number 
				cpuArray.remove(index);

				if (checkWin()) break;

				//check current values in the arraylist
				if (DEBUG)System.out.println("\nCPU MOVE: " + move + cpuArray);

				//end game on 9th move
				if (move >=9) break;
			}

			//human move code
			if (move % 2 ==1) {

				//take in a value from the player then make sure its valid
				int numberToPlay = Integer.parseInt (JOptionPane.showInputDialog(frame, "Input unused even number from :"+ playerArray) );

				//check the variable to see what's stored
				if (DEBUG)System.out.println("\nThe number stored in the numberToPlay var is : " + numberToPlay);

				while( (!playerArray.contains(numberToPlay) ) || ( numberToPlay > 8) 
						|| (numberToPlay < 1) || (numberToPlay % 2 == 1) ) {
					numberToPlay = Integer.parseInt (JOptionPane.showInputDialog(frame, "Please enter a valid number from :"+ playerArray) );

					//check the variable to see what's stored
					if (DEBUG)System.out.println("\nThe number stored in the numberToPlay var is : " + numberToPlay);
				}

				//convert int into string to draw
				String stringNumber = Integer.toString(numberToPlay);

				double x = 0;
				double y = 0;
				do {
					if (StdDraw.mousePressed()) {
						col = (int) (StdDraw.mouseX() * 3);	
						row = (int) (StdDraw.mouseY() * 3);
						x = col * .33 + 0.15;
						y = row * .33 + 0.15;
					}
				}
				while(StdDraw.mousePressed() || board[row][col] != EMPTY);
				StdDraw.text(x, y, stringNumber);
				move++;
				board[row][col] = numberToPlay;   // valid move (empty slot)

				//remove the used number 
				playerArray.remove(playerArray.indexOf(numberToPlay));

				if (checkWin()) break;

				//check current values in the arraylist
				if (DEBUG)System.out.println("\nPlayer MOVE:" + move + playerArray);
			}
		}

		//print out board to check if values match expected
		if (DEBUG) {
			System.out.println("Board State ");
			for(int x = 2; x >= 0; x --) {
				for(int y = 0; y <= board.length-1; y ++) {
					System.out.print(board[x][y]);
				}
				System.out.print("\n");
			}
			System.out.print("\n");
		}
	}

	//check for win on row
	private static int[] checkPotentialWinRow(ArrayList<Integer> numberList){

		int rowColNum [] = new int[3];
		int chosenNumber = 0;
		int emptyCells = 0;

		for (int x = 0; x < 3 ; x++) {

			emptyCells = 0;
			int rowValue = board [x][0] + board [x][1] + board [x][2];

			if (DEBUG)System.out.println("\nThe row sums to :" + rowValue);

			//find out how many empty cells are on the row
			if ( board [x][0] == EMPTY )
				emptyCells++;
			if ( board [x][1] == EMPTY )
				emptyCells++;
			if ( board [x][2] == EMPTY )
				emptyCells++;

			if (DEBUG) System.out.println("\nThe number of empty cells is :" + emptyCells);

			//check if a number can be played to make 15
			if ( numberList.contains(15 - rowValue) ) {
				chosenNumber = 15 - rowValue;

				if (DEBUG) System.out.println("\nThe number being played :" + chosenNumber);

				//only play the number if there is one empty cell
				if (emptyCells == 1 ) {
					if ( board [x][0] == EMPTY ) {
						rowColNum[0] = x;
						rowColNum[1] = 0;
						rowColNum[2] = chosenNumber;

						if (DEBUG) System.out.println("\nValues stored in rowcolnum "+Arrays.toString(rowColNum) );
						return rowColNum;
					}
					else if ( board [x][1] == EMPTY ) {
						rowColNum[0] = x;
						rowColNum[1] = 1;
						rowColNum[2] = chosenNumber;

						if (DEBUG) System.out.println("\nValues stored in rowcolnum "+Arrays.toString(rowColNum) );
						return rowColNum;
					}
					else if ( board [x][2] == EMPTY ){
						rowColNum[0] = x;
						rowColNum[1] = 2;
						rowColNum[2] = chosenNumber;

						if (DEBUG) System.out.println("\nValues stored in rowcolnum "+Arrays.toString(rowColNum) );
						return rowColNum;
					}
				}
			}
		}
		return null;
	}

	//check for win on row
	private static int[] checkPotentialWinColumn(ArrayList<Integer> numberList){

		int rowColNum [] = new int[3];
		int chosenNumber = 0;
		int emptyCells = 0;

		for (int y = 0; y < 3 ; y++) {
			emptyCells = 0;
			int columnValue = board [0][y] + board [1][y] + board [2][y];

			if (DEBUG)System.out.println("\nThe row sums to :" + columnValue);

			//find out how many empty cells are on the column
			if ( board [0][y] == EMPTY )
				emptyCells++;
			if ( board [1][y] == EMPTY )
				emptyCells++;
			if ( board [2][y] == EMPTY )
				emptyCells++;

			if (DEBUG) System.out.println("\nThe number of empty cells is :" + emptyCells);

			//check if a number can be played to make 15
			if ( numberList.contains(15 - columnValue) ) {
				chosenNumber = 15 - columnValue;

				if (DEBUG) System.out.println("\nThe number being played :" + chosenNumber);

				//only play the number if there is one empty cell
				if (emptyCells == 1 ) {
					if ( board [0][y] == EMPTY ) {
						rowColNum[0] = 0;
						rowColNum[1] = y;
						rowColNum[2] = chosenNumber;

						if (DEBUG) System.out.println("\nValues stored in rowcolnum "+Arrays.toString(rowColNum) );
						return rowColNum;
					}
					else if ( board [1][y] == EMPTY ) {
						rowColNum[0] = 1;
						rowColNum[1] = y;
						rowColNum[2] = chosenNumber;

						if (DEBUG) System.out.println("\nValues stored in rowcolnum "+Arrays.toString(rowColNum) );
						return rowColNum;
					}
					else if ( board [2][y] == EMPTY ){
						rowColNum[0] = 2;
						rowColNum[1] = y;
						rowColNum[2] = chosenNumber;

						if (DEBUG) System.out.println("\nValues stored in rowcolnum "+Arrays.toString(rowColNum) );
						return rowColNum;
					}
				}
			}
		}
		return null;
	}

	private static int[] checkPotentialWinDiag_1(ArrayList<Integer> numberList){

		int rowColNum [] = new int[3];
		int chosenNumber = 0;
		int emptyCells = 0;
		int diagValue = board [0][0] + board [1][1] + board [2][2];

		if (DEBUG)System.out.println("\nThe row sums to :" + diagValue);

		//find out how many empty cells are on the diagonal
		if ( board [0][0] == EMPTY )
			emptyCells++;
		if ( board [1][1] == EMPTY )
			emptyCells++;
		if ( board [2][2] == EMPTY )
			emptyCells++;

		if (DEBUG) System.out.println("\nThe number of empty cells is :" + emptyCells);

		//check if a number can be played to make 15
		if ( numberList.contains(15 - diagValue) ) {
			chosenNumber = 15 - diagValue;

			if (DEBUG) System.out.println("\nThe number being played :" + chosenNumber);

			//only play the number if there is one empty cell
			if (emptyCells == 1 ) {
				if ( board [0][0] == EMPTY ) {
					rowColNum[0] = 0;
					rowColNum[1] = 0;
					rowColNum[2] = chosenNumber;

					if (DEBUG) System.out.println("\nValues stored in rowcolnum "+Arrays.toString(rowColNum) );
					return rowColNum;
				}
				else if ( board [1][1] == EMPTY ) {
					rowColNum[0] = 1;
					rowColNum[1] = 1;
					rowColNum[2] = chosenNumber;

					if (DEBUG) System.out.println("\nValues stored in rowcolnum "+Arrays.toString(rowColNum) );
					return rowColNum;
				}
				else if ( board [2][2] == EMPTY ){
					rowColNum[0] = 2;
					rowColNum[1] = 2;
					rowColNum[2] = chosenNumber;

					if (DEBUG) System.out.println("\nValues stored in rowcolnum "+Arrays.toString(rowColNum) );
					return rowColNum;
				}
			}
		}
		return null;
	}
	
	private static int[] checkPotentialWinDiag_2(ArrayList<Integer> numberList){

		int rowColNum [] = new int[3];
		int chosenNumber = 0;
		int emptyCells = 0;
		int diagValue = board [0][2] + board [1][1] + board [2][0];

		if (DEBUG)System.out.println("\nThe row sums to :" + diagValue);

		//find out how many empty cells are on the column
		if ( board [0][2] == EMPTY )
			emptyCells++;
		if ( board [1][1] == EMPTY )
			emptyCells++;
		if ( board [2][0] == EMPTY )
			emptyCells++;

		if (DEBUG) System.out.println("\nThe number of empty cells is :" + emptyCells);

		//check if a number can be played to make 15
		if ( numberList.contains(15 - diagValue) ) {
			chosenNumber = 15 - diagValue;

			if (DEBUG) System.out.println("\nThe number being played :" + chosenNumber);

			//only play the number if there is one empty cell
			if (emptyCells == 1 ) {
				if ( board [0][2] == EMPTY ) {
					rowColNum[0] = 0;
					rowColNum[1] = 2;
					rowColNum[2] = chosenNumber;

					if (DEBUG) System.out.println("\nValues stored in rowcolnum "+Arrays.toString(rowColNum) );
					return rowColNum;
				}
				else if ( board [1][1] == EMPTY ) {
					rowColNum[0] = 1;
					rowColNum[1] = 1;
					rowColNum[2] = chosenNumber;

					if (DEBUG) System.out.println("\nValues stored in rowcolnum "+Arrays.toString(rowColNum) );
					return rowColNum;
				}
				else if ( board [2][0] == EMPTY ){
					rowColNum[0] = 2;
					rowColNum[1] = 0;
					rowColNum[2] = chosenNumber;

					if (DEBUG) System.out.println("\nValues stored in rowcolnum "+Arrays.toString(rowColNum) );
					return rowColNum;
				}
			}
		}
		return null;
	}

	//check for a win
	private static boolean checkWin() {
		if ( (board [0][0] + board [1][1] + board [2][2] == 15) 
				&&( board [0][0]!= EMPTY) && ( board [1][1]!= EMPTY) && ( board [2][2]!= EMPTY) ) {
			winner = true;
			if (DEBUG)System.out.println("Game over1");
			return true;
		}
		if ( (board [0][2] + board [1][1] + board [2][0] == 15) 
				&& ( board [0][2]!= EMPTY) && ( board [1][1]!= EMPTY) && ( board [2][0]!= EMPTY) ) {
			winner = true;
			if (DEBUG)System.out.println("Game over1");
			return true;
		}
		for (int rc = 0; rc < 3; rc ++) { 
			if ( (board [0][rc] + board [1][rc] + board [2][rc] == 15 ) && ( board [0][rc]!= EMPTY) 
					&&( board [1][rc]!= EMPTY) && ( board [2][rc]!= EMPTY) ) {
				winner  = true;
				if (DEBUG)System.out.println("Game over");
				return true;
			}
			if ( (board [rc][0] + board [rc][1] + board [rc][2] == 15)  && ( board [rc][0]!= EMPTY) 
					&& ( board [rc][1]!= EMPTY) && ( board [rc][2]!= EMPTY) ) {
				winner = true;
				if (DEBUG)System.out.println("Game over");
				return true;
			}
		}
		return false;
	}
}