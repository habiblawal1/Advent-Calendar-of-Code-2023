package Day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day1Part2 {
    /**
    --- Part Two ---

    Your calculation isn't quite right. It looks like some of the digits are actually spelled out with letters: one, two, three, four, five, six, seven, eight, and nine also count as valid "digits".

    Equipped with this new information, you now need to find the real first and last digit on each line. For example:
     */

    private static int findSpeltDigit(char[] line, int index) {
        int output = 0;
        switch (line[index]) {
            case 'o':
                if (line.length-index >= 3) {
                    if (new String(line, index, 3).equals("one")) {
                        output = 1;
                    }
                }
                break;
            case 't':
                //two, three
                if (line.length-index >= 5) {
                    if (new String(line, index, 5).equals("three")) {
                        output = 3;
                        break;
                    }
                }
                if (line.length-index >= 3) {
                    if (new String(line, index, 3).equals("two")) {
                        output = 2;
                    }
                }
                break;
            case 'f':
                //four, five
                if (line.length-index >= 4) {
                    if (new String(line, index, 4).equals("four")) {
                        output = 4;
                    } else if (new String(line, index, 4).equals("five")) {
                        output = 5;
                    }
                }
                break;
            case 's':
                //six,seven
                if (line.length-index >= 5) {
                    if (new String(line, index, 5).equals("seven")) {
                        output = 7;
                        break;
                    }
                }
                if (line.length-index >= 3) {
                    if (new String(line, index, 3).equals("six")) {
                        output = 6;
                    }
                }
                break;
            case 'e':
                if (line.length-index >= 5) {
                    if (new String(line, index, 5).equals("eight")) {
                        output = 8;
                    }
                }
                break;
            case 'n':
                if (line.length-index >= 4) {
                    if (new String(line, index, 4).equals("nine")) {
                        output = 9;
                    }
                }
                break;
        }
        return output;
    }

    public void test() {
        System.out.println(findSpeltDigit("ighteightninre".toCharArray(), 0));
    }
    public static String run(String puzzleInputPath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(puzzleInputPath));
            String currentLine = reader.readLine();
            int sum = 0;

            while (currentLine != null) {
                char[] lineArray = currentLine.toCharArray();
                int firstDigit = 0;
                int secondDigit = 0;

                for (int i = 0; i < lineArray.length; i++) {
                    if (Character.isDigit(lineArray[i])) {
                        firstDigit = lineArray[i]-'0';
                        break;
                    } else {
                        firstDigit = findSpeltDigit(lineArray,i);
                        if (firstDigit!=0) break;
                    }
                }

                for (int i = lineArray.length-1; i >= 0; i--) {
                    if (Character.isDigit(lineArray[i])) {
                        secondDigit = lineArray[i]-'0';
                        break;
                    } else {
                        secondDigit = findSpeltDigit(lineArray,i);
                        if (secondDigit!=0) break;
                    }
                }
                sum += firstDigit*10 + secondDigit;
                currentLine = reader.readLine();
            }
            reader.close();

            return ""+sum;

        } catch (IOException e) {
            return e.getMessage();
        }

    }

    public static void main(String[] args) {
        System.out.println("Test Result = " + run("/Users/habiblawal/Documents/GitHub/Advent-Calendar-of-Code-2023/src/Day1/TestInputPt2"));
        System.out.println("Puzzle Result = " + run("/Users/habiblawal/Documents/GitHub/Advent-Calendar-of-Code-2023/src/Day1/PuzzleInput"));
//        Day1Part2 obj = new Day1Part2();
//        obj.test();
    }
}
