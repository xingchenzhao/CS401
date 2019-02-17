package SudokuGame;

import java.util.Scanner;

public interface SudokuInterface {
    final int BOARD_SIZE=9;
    final int GROUP_SIZE=3;
    
    int[][] retrieveSudokuGameFromFile(String sudokuFile);
    Scanner initializeFileScanner(String sudokuFileDirectory);
    void printBoard();
    boolean isCellEmpty(int row, int col);
    boolean[] getAllowedValuesInCurrentRow(int row);
    boolean[] getAllowedValuesInCurrentCol(int col);
    boolean[] getAllowedValuesInCurrentGroup(int row, int col);
    boolean[] getAllowedValuesBasedOnTheThreeRules(boolean[] allowedValuesInCurrentRow, boolean[] allowedValuesInCurrentCol, boolean[] allowedValuesInCurrentGroup);
    int getUniqueValue(int row, int col);
    void updateGame(int allowedValue, int row, int col);
    boolean hasPuzzleBeenSolved();
    void showGameSolvedMessage();
}