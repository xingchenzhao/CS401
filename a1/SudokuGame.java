package SudokuGame;
/*
Welcome to the Suduko Solver
v1.0 2/11/18
Authors: Jack Ullery, Hunter Basso, Xingchen Zhao, Ryan Matukaitis

This program reads a text file into 2D array and interprets it as a suduko puzzle.
The program then uses 3 rules to solve the puzzle and displays each step until it is
solved.
*/

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class SudokuGame implements SudokuInterface {

//First action of the Program that calls the file reader and reads in the file data
    int[][] game = retrieveSudokuGameFromFile("sudokuGame.sud");
    int currentStep = 0;

    public static void main(String[] args) {
        SudokuGame SudGame = new SudokuGame();
        SudGame.printBoard();

        //Essential loop that applies the Suduko Rules and replaces integer values within.
        boolean continueLoop;
        do {
            continueLoop = false;
            for (int i = 0; i < SudokuGame.BOARD_SIZE; i++) {
                for (int k = 0; k < SudokuGame.BOARD_SIZE; k++) {

                    //Replaces empty value position with an plausible solution value.
                    if (SudGame.isCellEmpty(i, k)) {
                        int allowedValue = SudGame.getUniqueValue(i, k);
                        SudGame.updateGame(allowedValue, i, k);

                        //Prints the step number and the updated gameboard
                        if (!SudGame.isCellEmpty(i, k)) {
                            System.out.println("\nStep " + SudGame.currentStep
                                    + ": Place " + SudGame.game[i][k]
                                    + " at (" + (i + 1) + "," + (k + 1) + ")");
                            SudGame.printBoard();
                            SudGame.currentStep += 1;
                            continueLoop = true;
                        }
                    }
                }
            }

        //Checks to see if the gameboard has been solved
        } while (continueLoop);

        if (SudGame.hasPuzzleBeenSolved()) {
            SudGame.showGameSolvedMessage();
        }

    }

    //Opens and reads the text file's 2D Array returning it as a int 2D Array
    public int[][] retrieveSudokuGameFromFile(java.lang.String sudokuFileDirectory) {

        int[][] gameBoardArray = new int[BOARD_SIZE][BOARD_SIZE];

        Scanner sudokuFileData = initializeFileScanner(sudokuFileDirectory);
        
        //Loop for reading in the text file into a 2D int array
        try {
            for (int i = 0; i < BOARD_SIZE; i++) {
                String sudokuLine = sudokuFileData.nextLine();
                String[] sudokuLineValues = sudokuLine.split(",");

                for (int k = 0; k < BOARD_SIZE; k++) {
                    sudokuLineValues[k] = sudokuLineValues[k].trim();
                    gameBoardArray[k][i] = Integer.parseInt(sudokuLineValues[k]);

                    //Checks for inapproriate number for  board size
                    if ((gameBoardArray[k][i] < 0) || (gameBoardArray[k][i] > BOARD_SIZE)) {
                        System.out.println("ERROR: Found a number outside acceptable bounds at (" 
                                + k + "," + i + ")");
                        System.exit(0);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("ERROR: File not formatted properly...");
            System.exit(0);
        }

        return gameBoardArray;
    }

    //Initializes the file reader and checks to see if the file exists, returns file data
    public Scanner initializeFileScanner(String sudokuFileDirectory) {

        boolean continueLoop = true;
        Scanner sudokuFileData = new Scanner("");

        do {
            File sudokuFile = new File(sudokuFileDirectory);
            try {
                sudokuFileData = new Scanner(sudokuFile);
                continueLoop = false;

            //If file doesn't exist, asks the user for new file directory
            } catch (FileNotFoundException e) {
                System.out.println("ERROR: File Not Found. \n"
                        + " Please Input The Directory Of A Valid File:");
                Scanner userInput = new Scanner(System.in);
                sudokuFileDirectory = userInput.nextLine().trim();

            }
        } while (continueLoop);
        return sudokuFileData;
    }

   //Prints the gameboard in an eye-pleasing manner
    public void printBoard() {

        for (int i = 0; i < BOARD_SIZE; i++) {

            if (((i) % GROUP_SIZE == 0) && !((i + 1) == 1)) {

                for (int k = 0; k < BOARD_SIZE * 2; k++) {
                    System.out.print("-");
                }
                System.out.println("-");
            }

            System.out.print("|");
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(game[j][i]);

                if (((j + 1) % GROUP_SIZE == 0) && !((j + 1) == 1)) {
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    //First rule, checks for approriate values in gameboard columns
    public boolean[] getAllowedValuesInCurrentCol(int col) {

        boolean[] allowedValues = new boolean[BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++) {

            allowedValues[i] = true;

            for (int j = 0; j < BOARD_SIZE; j++) {
                if (game[col][j] == (i + 1)) {
                    allowedValues[i] = false;
                }
            }
        }

        return allowedValues;
    }
    
    //Second rule checks for allowed values for each row. Returns allowed value array
    public boolean[] getAllowedValuesInCurrentRow(int row) {

        boolean[] allowedValues = new boolean[BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++) {

            allowedValues[i] = true;

            for (int j = 0; j < BOARD_SIZE; j++) {
                if (game[j][row] == (i + 1)) {
                    allowedValues[i] = false;
                }
            }
        }

        return allowedValues;
    }
  
    //Third rule: Group rule creates an array of allowed values per cell based on the values
    //in the cell's group.
    public boolean[] getAllowedValuesInCurrentGroup(int col, int row) {

        row -= (row % GROUP_SIZE);
        col -= (col % GROUP_SIZE);
        
        boolean[] allowedValues = new boolean[(GROUP_SIZE * GROUP_SIZE)];

        for (int i = 0; i < BOARD_SIZE; i++) {

            allowedValues[i] = true;

            for (int j = 0; j < GROUP_SIZE; j++) {

                for (int k = 0; k < GROUP_SIZE; k++) {
                    if (game[col + j][row + k] == (i + 1)) {
                        allowedValues[i] = false;
                    }
                }
            }
        }

        return allowedValues;
    }

    //Cross references all three rules and consolidates the values into one array.
    public boolean[] getAllowedValuesBasedOnTheThreeRules(boolean[] allowedValuesInCurrentRow, boolean[] allowedValuesInCurrentCol, boolean[] allowedValuesInCurrentGroup) {

        boolean[] allowedValues = new boolean[BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++) {

            if (allowedValuesInCurrentRow[i] && allowedValuesInCurrentCol[i] && allowedValuesInCurrentGroup[i]) {
                allowedValues[i] = true;
            }
        }

        return allowedValues;
    }

    //Returns a unique (allowed) value for empty cell
    public int getUniqueValue(int col, int row) {

        boolean[] allowedValuesInCurrentRow = getAllowedValuesInCurrentRow(row);
        boolean[] allowedValuesInCurrentCol = getAllowedValuesInCurrentCol(col);
        boolean[] allowedValuesInCurrentGroup = getAllowedValuesInCurrentGroup(col, row);

        boolean[] allowedValues = getAllowedValuesBasedOnTheThreeRules(allowedValuesInCurrentRow, allowedValuesInCurrentCol, allowedValuesInCurrentGroup);

        int numAllowedValues = 0;
        int value = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (allowedValues[i]) {
                numAllowedValues++;
                value = i + 1;
            }

            if (numAllowedValues > 1) {
                return 0;
            }
        }

        return value;
    }

    //Updates the gameboard with unique (allowed) value
    public void updateGame(int allowedValue, int row, int col) {
        game[row][col] = allowedValue;
    }

    //Checks if the puzzle is solved
    public boolean hasPuzzleBeenSolved() {
        boolean isSolved = true;

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (isCellEmpty(i, j)) {
                    isSolved = false;
                }
            }
        }

        return isSolved;
    }

    //Solved Message
    public void showGameSolvedMessage() {
        System.out.println("\nThe Puzzle Has Been Solved!");
    }

    //Checks if the selected cell is empty
    public boolean isCellEmpty(int col, int row) {

        return game[col][row] == 0;
    }
}
