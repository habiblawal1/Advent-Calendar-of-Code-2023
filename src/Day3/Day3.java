package Day3;

import Day2.Day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

public class Day3 {
    /**
     * --- Day 3: Gear Ratios ---
     *
     * You and the Elf eventually reach a gondola lift station; he says the gondola lift will take you up to the water source, but this is as far as he can bring you. You go inside.
     *
     * It doesn't take long to find the gondolas, but there seems to be a problem: they're not moving.
     *
     * "Aaah!"
     *
     * You turn around to see a slightly-greasy Elf with a wrench and a look of surprise. "Sorry, I wasn't expecting anyone! The gondola lift isn't working right now; it'll still be a while before I can fix it." You offer to help.
     *
     * The engineer explains that an engine part seems to be missing from the engine, but nobody can figure out which one. If you can add up all the part numbers in the engine schematic, it should be easy to work out which part is missing.
     *
     * The engine schematic (your puzzle input) consists of a visual representation of the engine. There are lots of numbers and symbols you don't really understand, but apparently any number adjacent to a symbol, even diagonally, is a "part number" and should be included in your sum. (Periods (.) do not count as a symbol.)
     *
     * Here is an example engine schematic:
     *
     * 467..114..
     * ...*......
     * ..35..633.
     * ......#...
     * 617*......
     * .....+.58.
     * ..592.....
     * ......755.
     * ...$.*....
     * .664.598..
     *
     * In this schematic, two numbers are not part numbers because they are not adjacent to a symbol: 114 (top right) and 58 (middle right). Every other number is adjacent to a symbol and so is a part number; their sum is 4361.
     *
     * Of course, the actual engine schematic is much larger. What is the sum of all of the part numbers in the engine schematic?
     */
    private static final String NON_SYMBOLS = "0123456789.";
    private static boolean isSymbol(char c) { return NON_SYMBOLS.indexOf(c) == -1; }
    private static boolean hasAdjacent(int r, int s, int e, char[][] matrix) {
        //left
        if (s>0)
            if (isSymbol(matrix[r][s-1])) return true;
        //right
        if (e+1 < matrix[0].length)
            if (isSymbol(matrix[r][e+1])) return true;

        if (r+1 < matrix.length) {
            //down-left
            if (s>0)
                if (isSymbol(matrix[r+1][s-1])) return true;
            //down-right
            if (e+1 < matrix[0].length)
                if (isSymbol(matrix[r+1][e+1])) return true;
            //down
            for (int i=s; i<=e; i++)
                if (isSymbol(matrix[r+1][i])) return true;
        }

        if (r>0) {
            //up-left
            if (s>0)
                if (isSymbol(matrix[r-1][s-1])) return true;
            //up-right
            if (e+1 < matrix[0].length)
                if (isSymbol(matrix[r-1][e+1])) return true;
            //up
            for (int i=s; i<=e; i++)
                if (isSymbol(matrix[r-1][i])) return true;
        }
        return false;
    }

    public static String run(String puzzleInputPath) {
        /**
         * Need to look at to left, right, up, down, diaganol (up-left, up-right, down-left, down-right)
         * check for anything character that is not a .
         * if number has an adjacent then add to a sum
         *
         * loop through each line, and get a count of number of lines and the line length
         *  new character[numOfLines][lineLength]
         * loop through each line
         *      add the charArray to the array
         *
         *  int sum
         *  int num
         *  int start
         *  loop through each row
         *      loop through each column
         *          if its a digit
         *              if num ==0 -> store index in start variable
         *               add digit to num
         *              if next item is NOT a digit or is end of row
         *                  check for adjacent
         *                  if adjacent -> add num to sum
         *                  num = 0
         *  return sum
         *
         *  checkAdjacent
         *  //left
         *  if (s-1 >= 0 && matrix[r][s-1] != '.') return true
         *
         *  //right
         *  else if (e+1 < row length && matrix[r][e+1] != '.')
         *
         *  //down
         *  if (r+1 < num of rows)
         *      //down-left
         *      if (s-1 >= 0 && matrix[r+1][s-1] != '.') return true
         *      //down-right
         *      else if (e+1 < row length && matrix[r+1][e+1] != '.')
         *      //down
         *      for (int i=s; i<=e; i++)
         *          if (matrix[r+1][i] != '.') return true
         *
         *
         *   //up
         *   if (r-1 >= 0)
         *         //up-left
         *         if (s-1 >= 0 && matrix[r-1][s-1] != '.') return true
         *         //up-right
         *         else if (e+1 < row length && matrix[r-1][e+1] != '.')
         *        //up
         *       for (int i=s; i<=e; i++)
         *          if (matrix[r-1][i] != '.') return true
         *  return false
         */
        try {
            BufferedReader bf = new BufferedReader(new FileReader(puzzleInputPath));
            int rows = 1;
            int cols = bf.readLine().length();
            while (bf.readLine() != null)  rows++;

            bf = new BufferedReader(new FileReader(puzzleInputPath));
            String currentLine = bf.readLine();
            char[][] matrix = new char[rows][cols];
            int index=0;
            while (currentLine != null) {
                matrix[index] = currentLine.toCharArray();
                index++;
                currentLine = bf.readLine();
            }

            int num = 0;
            int sum = 0;
            int start = 0;
            for (int r=0; r<rows; r++) {
                for (int c=0; c<cols; c++) {
                    if (Character.isDigit(matrix[r][c])) {
                        if (num == 0) start=c;
                        num = num*10 + (matrix[r][c]-'0');
                        if (c+1>=cols || !Character.isDigit(matrix[r][c+1])) {
                            if (hasAdjacent(r,start,c,matrix)) sum += num;
                            num = 0;
                        }
                    }
                }
            }
            bf.close();
            return ""+sum;
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        System.out.println("Test Result = " + Day3.run("/Users/habiblawal/Documents/GitHub/Advent-Calendar-of-Code-2023/src/Day3/TestInput"));
        System.out.println("Puzzle Result = " + Day3.run("/Users/habiblawal/Documents/GitHub/Advent-Calendar-of-Code-2023/src/Day3/PuzzleInput"));
    }
}
