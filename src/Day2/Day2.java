package Day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Day2 {
    /**
    --- Day 2: Cube Conundrum ---

    You're launched high into the atmosphere! The apex of your trajectory just barely reaches the surface of a large island floating in the sky. You gently land in a fluffy pile of leaves. It's quite cold, but you don't see much snow. An Elf runs over to greet you.

    The Elf explains that you've arrived at Snow Island and apologizes for the lack of snow. He'll be happy to explain the situation, but it's a bit of a walk, so you have some time. They don't get many visitors up here; would you like to play a game in the meantime?

    As you walk, the Elf shows you a small bag and some cubes which are either red, green, or blue. Each time you play this game, he will hide a secret number of cubes of each color in the bag, and your goal is to figure out information about the number of cubes.

    To get information, once a bag has been loaded with cubes, the Elf will reach into the bag, grab a handful of random cubes, show them to you, and then put them back in the bag. He'll do this a few times per game.

    You play several games and record the information from each game (your puzzle input). Each game is listed with its ID number (like the 11 in Game 11: ...) followed by a semicolon-separated list of subsets of cubes that were revealed from the bag (like 3 red, 5 green, 4 blue).

    For example, the record of a few games might look like this:

    Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
    Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
    Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
    Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
    Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green

    In game 1, three sets of cubes are revealed from the bag (and then put back again). The first set is 3 blue cubes and 4 red cubes; the second set is 1 red cube, 2 green cubes, and 6 blue cubes; the third set is only 2 green cubes.

    The Elf would first like to know which games would have been possible if the bag contained only 12 red cubes, 13 green cubes, and 14 blue cubes?

    In the example above, games 1, 2, and 5 would have been possible if the bag had been loaded with that configuration. However, game 3 would have been impossible because at one point the Elf showed you 20 red cubes at once; similarly, game 4 would also have been impossible because the Elf showed you 15 blue cubes at once. If you add up the IDs of the games that would have been possible, you get 8.

    Determine which games would have been possible if the bag had been loaded with only 12 red cubes, 13 green cubes, and 14 blue cubes. What is the sum of the IDs of those games?
    */

    public static String run(String puzzleInputPath) {
        /**
         * 12 reds, 13 green, 14 blue
         * Look at each game, store ID, check if the colours less than max, if so keep id
         * sum ids
         * remove all pucntion, the split on space
         *Game 1: 3 blue, 4 red;
         * 1 red, 2 green, 6 blue;
         * 2 green
         *
         * int idSum
         * for each line:
         *      id = 6 char in (or 5 if we doing 0 index)
         *      get the string after the colon
         *      remove comma
         *      split on ;
         *      bool badId
         *      for each of comma split
         *          split on space
         *          for loop, jumping 2 indexes at a time
         *              if color is red -> add to red (repeat for all 3 colours)
         *          check if each colour is less than max
         *              if not then badId =false, and break
         *      if !badId, idSum+=sum
         */

        try {
            BufferedReader bf = new BufferedReader(new FileReader(puzzleInputPath));
            String currentGame = bf.readLine();

            int idSum = 0;
            while (currentGame!=null) {
                String[] splitLine = currentGame.split(":");
                //for text before the first colon, keep only the digits
                int id = Integer.parseInt(splitLine[0].replaceAll("[^0-9]", ""));
                String[] rounds = splitLine[1].replaceAll(",", "").split(";");
                boolean goodId = true;
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
                    if (!(red <= 12 && green <=13 && blue <= 14)) {
                        goodId = false;
                        break;
                    }
                }
                if (goodId) idSum += id;

                currentGame = bf.readLine();
            }
            bf.close();
            return "" + idSum;
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        System.out.println("Test Result = " + Day2.run("/Users/habiblawal/Documents/GitHub/Advent-Calendar-of-Code-2023/src/Day2/TestInput"));
        System.out.println("Puzzle Result = " + Day2.run("/Users/habiblawal/Documents/GitHub/Advent-Calendar-of-Code-2023/src/Day2/PuzzleInput"));
    }
}
