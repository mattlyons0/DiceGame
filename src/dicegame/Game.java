package dicegame;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

/**
 * Handle the game play logic and statistics
 *
 * @authors David McClure, Daniel Kercheski, Matt Lyons
 */
public class Game {

    private int diceSize = 7; //roll method will return random whole number between 1 and diceSize - 1
    private int holeDistance = 36;
    private int distanceRemaining; //place holder for the distance remaining, initialized in the roll() method
    private int numberOfStrokes; //place holder to keep track of strokes
    private int randomHoleDistance;

    private static int playerCount = 0; //does this need to be initialized to 0?
    private static int holeCount;
    private static int[] course = new int[holeCount]; //holds the course distances
    public static int[][] gameStats = new int[playerCount][holeCount]; //holds the stroke counts for each player and hole
    public static int[][] distancesRemaining = new int[playerCount][holeCount]; //holds the distances remaining for each player and hole
    private String[] playerName = new String[4]; //holds player names
    private int[] player = null;

    private int[] currentLocation = new int[2];
    private int[] nextHole = new int[18];

    private int[] currentDistance = new int[4];
    private static int playerOneDistance;
    private static int playerTwoDistance;
    private static int playerThreeDistance;
    private static int playerFourDistance;

    private static int[] currentGameLocation = new int[2];//index 0 holds player index, index 1 holds hole index

    private static int holeIndex;
    private static int playerIndex;

    /**
     * Constructor to ensure that all values are initialized when starting a new
     * game.
     */
    public Game() {
        distanceRemaining = 36;
        numberOfStrokes = 0;
        playerCount = 0;
        holeCount = 1;
        holeIndex = 0;
//		initializeGameStats(playerCount, 0, gameStats);
    }

    /**
     * Constructor to auto load the previous game.
     *
     * @param loadGame determines if the current game file will be loaded upon startup
     */
    public Game(boolean loadGame) {
        //This will load the game stats, player count, and hole count
        if (loadGame) {
            loadGameStats();
            numberOfStrokes = 0;
            holeIndex = 0;
        }

    }

    /**
     * method that returns distance of specified player at current hole
     *
     * @param player index of specified player
     * @param hole index of specified hole
     * @return current distance of specified player at current hole
     */
    public int getCurrentPlayerDistance(int player, int hole) {
        int current = distancesRemaining[player][hole];

        return current;
    }

    /**
     * Calculate the number of times a certain player has one (1 win = least
     * strokes on a hole)
     *
     * @param playerIndex index of player to check wins on
     * @return the number of times said player has won
     */
    public int getWins(int playerIndex) {
        int wins = 0;

        for (int holeIndex = 0; holeIndex < getNumberOfHoles(); holeIndex++) {
            int lowestScore = Integer.MAX_VALUE;
            ArrayList<Integer> lowestIndex = new ArrayList();
            for (int pIndex = 0; pIndex < getNumberOfPlayers(); pIndex++) {
                if (getStrokes(pIndex, holeIndex) < lowestScore) {
                    lowestScore = getStrokes(pIndex, holeIndex);
                    lowestIndex.clear();
                    lowestIndex.add(0, pIndex);
                } else if(getStrokes(pIndex,holeIndex) == lowestScore){
                    lowestIndex.add(pIndex);
                }
            }
            if (lowestIndex.contains(playerIndex) && lowestScore != 0) {
                wins++;
            }
        }

        return wins;
    }

    /**
     * Calculate the number of games the player has not won
     *
     * @param playerIndex index of the player to calculate from
     * @return the number of holes this player lost
     */
    public int getLosses(int playerIndex) {
        if (strokeSum(playerIndex) == 0) {
            return 0;
        }
        return getNumberOfHoles() - getWins(playerIndex);
    }

    /**
     * method to add up all strokes for each player
     *
     * @param playerNum index of the player to add up strokes for
     * @return sum of specified row
     */
    public int strokeSum(int playerNum) //works
    {
        int sum = 0;
        for (int holeIndex = 0; holeIndex < holeCount; holeIndex++) {
            sum += getStrokes(playerNum, holeIndex);
        }
        return sum;
    }

    /**
     * Move the hole to the next hole in the game. Returns true if moved or
     * false if the last hole.
     *
     * @return boolean move successful
     */
    public boolean nextHole() {
        int currentHole = holeIndex;
        int maxHole = holeCount;
        boolean moveSuccessful = false;

        //increment only if not on the last hole.
        if (currentHole < maxHole) {
            currentHole++;
            moveSuccessful = true;
        }

        saveGameStats();

        holeIndex = currentHole;
        return moveSuccessful;
    }

    /**
     * returns which hole the game is on.
     *
     * @return hole index
     */
    public int getHoleIndex() {
        int holeIndexLocation = holeIndex;
        return holeIndexLocation;
    }

    /**
     * Determines and returns current player based on the player who is furthest
     * away Algorithm is similar to real golf, based on the furthest player
     * away.
     *
     * @return the index of the player who should be
     */
    public int getCurrentPlayer()//works
    {
        int highestValue = -1;
        int highestIndex = -1;
        for (int playerIndex = 0; playerIndex < getNumberOfPlayers(); playerIndex++) {
            int distance = getCurrentPlayerDistance(playerIndex, getHoleIndex());
            if (distance > highestValue) {
                highestValue = distance;
                highestIndex = playerIndex;
            }
        }

        currentGameLocation[0] = highestIndex; //mark the player index
        currentGameLocation[1] = holeIndex; //mark the hole index

        return highestIndex;
    }

    /**
     * Give the game stats as a 2d array.
     *
     * @return game stats
     */
    public int[][] getGameStats() //works
    {
        return gameStats;
    }

    /**
     * Save the game data to a file named savedGameStats.sav
     */
    public void saveGameStats() {
        int oldStats[][] = gameStats;
        int numberOfPlayers = playerCount;
        int numberOfHoles = holeCount;
        String[] players = playerName;

        try {
            //create a file
            FileOutputStream savedFile = new FileOutputStream("savedGameStats.sav");
            ObjectOutputStream saved = new ObjectOutputStream(savedFile);

            //put the array in the file
            saved.writeObject(oldStats);
            saved.writeObject(numberOfPlayers);
            saved.writeObject(numberOfHoles);
            saved.writeObject(players);

            //close the file
            saved.close();
        } catch (Exception e) {
            System.err.println("Error saving file");
        }
    }

    /**
     * Load the game stats from the file savedGameStats.sav
     */
    public void loadGameStats() {
        try {

            //load the file
            FileInputStream savedFile = new FileInputStream("savedGameStats.sav");

            ObjectInputStream saved = new ObjectInputStream(savedFile);

            //put the array back into the game
            gameStats = (int[][]) saved.readObject();
            playerCount = (int) saved.readObject();
            holeCount = (int) saved.readObject();
            playerName = (String[]) saved.readObject();

            //close the file
            saved.close();
        } catch (Exception e) {
            System.err.println("Error: file does not exist yet");
        }
    }

    /**
     * Returns the amount of holes.
     *
     * @return hole count
     */
    public int getNumberOfHoles() //works
    {
        return holeCount;
    }

    /**
     * Inputs the amount of holes
     *
     * @param numberOfHoles number of holes to set the logic to
     */
    public void setNumberOfHoles(int numberOfHoles) //works
    {
        holeCount = numberOfHoles;
    }

    /**
     * function that returns randomHoleDistance that ranges between a given high
     * and low value
     *
     * @return a random value 15-100
     */
    public int randomHoleDistancer() //works
    {
        int high = 100;
        int low = 15;

        Random rollTheDice = new Random();

        randomHoleDistance = rollTheDice.nextInt(high - low) + low; //result is between 15 and 100

        return randomHoleDistance;
    }

    /**
     * Creates the course by putting a random number created by the
     * randomHoleDistancer method into a global array.
     */
    public void createCourse()//works
    {
        int tempCourse[] = new int[holeCount]; //temporary storage for the course creation
        for (int holeIndex = 0; holeIndex < holeCount; holeIndex++) {
            tempCourse[holeIndex] = randomHoleDistancer();//place each distance into the array
        }

        course = tempCourse;//put all distances into the global array
    }

    /**
     * Returns the hole distance for each hole
     *
     * @return course
     */
    public int[] getCourse()//works
    {
        createCourse();
        return course;
    }

    /**
     * Input the amount of players.
     *
     * @param numberOfPlayers
     */
    public void setNumberOfPlayers(int numberOfPlayers) //works
    {
        playerCount = numberOfPlayers;
    }

    /**
     * Returns the amount of players.
     *
     * @return player count
     */
    public int getNumberOfPlayers() //works
    {
        return playerCount;
    }

    /**
     * Create a player and put their name in the string array.
     *
     * @param newPlayer name of the new player to add
     */
    public void createPlayer(String newPlayer) {
        playerName[playerCount] = newPlayer; 	// Put new player in next available spot.
        playerCount++;  						// And increment playerCt to count the new player.
    }

    /**
     * Returns a string array of player names
     *
     * @return players names
     */
    public String[] getPlayer() //works
    {
        return playerName;
    }

    /**
     * Rolls the dice and returns and number between 1 and 6
     *
     * @return rolled value
     */
    public int roll() {
        int rolled = 0;
        Random rollTheDice = new Random();

        //rollTheDice has the possibility of returning a 0, this prevents returning a 0 roll
        while (rolled == 0) {
            rolled = rollTheDice.nextInt(diceSize);
        }

        //when the first dice is rolled it signals the start of new hole
        //distanceRemaining = holeDistance; //THIS BREAKS OVERSHOOTING
        //increment the number of strokes every time the dice is rolled
        numberOfStrokes++;
        return rolled;
    }

    /**
     * Hits the ball for a total of values that were rolled by the multiplier.
     * Automatically updates the array of distance remaining values.
     *
     * @param roll the number of dice to roll
     * @param playerIndex the index of the player rolling
     * @param holeIndex the index of the hole the player is rolling for
     * @return an array of the values rolled with a length of roll
     */
    public int[] hitTheBall(int roll, int playerIndex, int holeIndex) {
        //boolean to tell if putt is required
        boolean putt = true;

        //array to hold each value added to the stroke distance
        int distances[] = new int[roll];

        int sumOfStroke = 0; //value to store how far the ball is going to go
        int addToStroke = 0;
        Random rollTheDice = new Random();

        int remainingDistance = distancesRemaining[playerIndex][holeIndex];

        //(distanceRemaining >= diceSize - 1) stops sumOfStroke from going over the hole
        for (int rollIndex = 0; rollIndex < roll && remainingDistance >= diceSize - 1; rollIndex++) {
            //calculates how far the ball goes during this stroke
            addToStroke = rollTheDice.nextInt(diceSize);

            //store each value rolled
            distances[rollIndex] = addToStroke;

            //keep track of the sum of the rolls
            sumOfStroke = sumOfStroke + addToStroke;

            //calculates how many yards remain, remaining yards is reset in the roll() method
            remainingDistance = remainingDistance - addToStroke;

            //prevent the putt from occurring if part of the original stroke;
            putt = false;

        }

        //if the distance remaining is less than the dice size just add once stroke for the putt 
        //and zero out the distance remaining to prevent endless game play
        if (remainingDistance < diceSize - 1 && putt) {
            //index 0 will contain the remaining distance, everything else will be set to 0
            distances[0] = remainingDistance;
            for (int rollIndex = 1; rollIndex < roll; rollIndex++) {
                distances[rollIndex] = 0;
            }

            remainingDistance = 0;
//            resetDistance(); //This also breaks tests since it resets before it can even be detected it was 0.
        }

        distancesRemaining[playerIndex][holeIndex] = remainingDistance;
        return distances;
    }

    /**
     * Returns number of strokes from each roll() function
     *
     * @param player index of player to roll for
     * @param hole index of hole to roll for
     * @return number of strokes
     */
    public int getStrokes(int player, int hole) {
        if (player < gameStats.length && hole < gameStats[player].length) {
            numberOfStrokes = gameStats[player][hole];
            return numberOfStrokes;
        } else {
            return 0;
        }
    }

    /**
     * Returns distance of the hole from where the ball starts
     *
     * @return the distance of the hole from where the ball starts
     */
    public int getHoleLength() //works
    {
        int holeLocation = getHoleIndex();
        return course[holeLocation];
    }

    /**
     * Returns the distance the player is currently from the hole
     *
     * @param hole index of hole to get distance from
     * @return the distance the player is currently from the hole
     */
    public int getDistanceFromHole(int hole) {
        distanceRemaining = distancesRemaining[getCurrentPlayer()][hole];
        return distanceRemaining;
    }

    /**
     * create a new gameStats and initialize the course
     */
    public void createGameStats() //works
    {
        int players = playerCount;
        int holes = holeCount;

        int temp[][] = new int[players][holes];
        gameStats = temp;

        int tempDistances[][] = new int[players][holes]; //creates a template to store distances remaining for each hole and player
        createCourse();

        for (int playerIndex = 0; playerIndex < playerCount; playerIndex++) {
            for (int holeIndex = 0; holeIndex < holeCount; holeIndex++) {
                tempDistances[playerIndex][holeIndex] = course[holeIndex];
            }
        }

        distancesRemaining = tempDistances;
    }

    /**
     * Reset the game stats
     */
    public void resetStats() //works
    {
        for (int playerIndex = 0; playerIndex < gameStats.length; playerIndex++) {
            for (int holeIndex = 0; holeIndex < gameStats[playerIndex].length; holeIndex++) {
                gameStats[playerIndex][holeIndex] = 0;
            }
        }
    }

    /**
     * Add a stroke for the specified player and hole
     *
     * @param player index of player to add stroke to
     * @param hole index of hole to add stroke to
     */
    public void addStroke(int player, int hole) //works
    {
        try {
            gameStats[player][hole]++;
        } catch (Exception e) {
            System.err.println("Error: incorrect player number or hole number");
        }

    }

    /**
     * Resets the player count to zero
     *
     * @return zero
     */
    public int resetPlayerCount() {
        playerCount = 0;
        return playerCount;
    }

    /**
     * Resets the names of the players in the game logic to null
     */
    public void resetPlayers() {
        resetPlayerCount();
        playerName = new String[4];
    }

    /**
     * Resets the hole index in the game logic
     *
     * @return zero
     */
    public int resetHoleIndex() {
        holeIndex = 0;
        return holeIndex;
    }
}
