package Day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day2Pt2 {
    /**
     * --- Part Two ---
     *
     * The Elf says they've stopped producing snow because they aren't getting any water! He isn't sure why the water stopped; however, he can show you how to get to the water source to check it out for yourself. It's just up ahead!
     *
     * As you continue your walk, the Elf poses a second question: in each game you played, what is the fewest number of cubes of each color that could have been in the bag to make the game possible?
     *
     * Again consider the example games from earlier:
     *
     * Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
     * Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
     * Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
     * Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
     * Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
     *
     *     In game 1, the game could have been played with as few as 4 red, 2 green, and 6 blue cubes. If any color had even one fewer cube, the game would have been impossible.
     *     Game 2 could have been played with a minimum of 1 red, 3 green, and 4 blue cubes.
     *     Game 3 must have been played with at least 20 red, 13 green, and 6 blue cubes.
     *     Game 4 required at least 14 red, 3 green, and 15 blue cubes.
     *     Game 5 needed no fewer than 6 red, 3 green, and 2 blue cubes in the bag.
     *
     * The power of a set of cubes is equal to the numbers of red, green, and blue cubes multiplied together. The power of the minimum set of cubes in game 1 is 48. In games 2-5 it was 12, 1560, 630, and 36, respectively. Adding up these five powers produces the sum 2286.
     *
     * For each game, find the minimum set of cubes that must have been present. What is the sum of the power of these sets?
     */

    public static String run(String puzzleInputPath) {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(puzzleInputPath));
            String currentGame = bf.readLine();

            int powerSum = 0;
            while (currentGame!=null) {
                String[] rounds = currentGame.split(":")[1].replaceAll(",", "").split(";");
                int maxRed = 0;
                int maxGreen = 0;
                int maxBlue = 0;
                for (String round : rounds) {
                    int red = 0;
                    int green = 0;
                    int blue = 0;
                    //we start from index 1 instead of 0 as there is an extra space after each comma
                    String[] colours = round.substring(1).split(" ");
                    for (int i=0; i<colours.length; i+=2) {
                        switch (colours[i + 1]) {
                            case "red" -> red += Integer.parseInt(colours[i]);
                            case "green" -> green += Integer.parseInt(colours[i]);
                            case "blue" -> blue += Integer.parseInt(colours[i]);
                        }
                    }
                    if (red > maxRed) maxRed = red;
                    if (green > maxGreen) maxGreen = green;
                    if (blue > maxBlue) maxBlue = blue;
                }
                powerSum += maxRed*maxGreen*maxBlue;

                currentGame = bf.readLine();
            }
            bf.close();
            return "" + powerSum;
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        System.out.println("Test Result = " + Day2Pt2.run("/Users/habiblawal/Documents/GitHub/Advent-Calendar-of-Code-2023/src/Day2/TestInput"));
        System.out.println("Puzzle Result = " + Day2Pt2.run("/Users/habiblawal/Documents/GitHub/Advent-Calendar-of-Code-2023/src/Day2/PuzzleInput"));
    }
}
