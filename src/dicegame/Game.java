package dicegame;

import java.util.Random;

/**
 * Handle the game play logic and statistics
 *
 * @authors Matt Lyons, David Lukacs, David McClure, Daniel Kercheski Team
 * Project
 */
public class Game {

    private int diceSize = 7; //roll method will return random whole number between 1 and diceSize - 1
    private int holeDistance = 36;
    private int distanceRemaining; //place holder for the distance remaining, initialized in the roll() method
    private int numberOfStrokes; //place holder to keep track of strokes
    private int randomHoleDistance;

    private int playerCount;
    private int holeCount;
    private int[][] gameStats = new int[playerCount][holeCount];
    private String[] playerName = new String[playerCount];

    /**
     * Constructor to ensure that all values are initialized when starting a new
     * game.
     */
    public Game() {
        distanceRemaining = 36;
        numberOfStrokes = 0;
        playerCount = 1;
//		initializeGameStats(playerCount, 0, gameStats);

    }
    
    /**
     * Returns the amount of holes.
     *
     * @return hole count
     */
    public int getNumberOfHoles() {
        return holeCount;
    }
    
    /**
     * Inputs the amount of holes
     */
    public void setNumberOfHoles(int numberOfHoles) {
        holeCount = numberOfHoles;
    }
    
    /**
     * function that returns randomHoleDistance that ranges between
     * a given high and low value
     * 
     * @return
     */
    public int randomHoleDistancer(){
    	
    	int high = 100;
    	int low = 15;
    	
    	Random rollTheDice = new Random();
    	
    	randomHoleDistance = rollTheDice.nextInt(high - low) + low; //result is between 15 and 100
    	
    	return randomHoleDistance;
    }
    
    /**
     * Once a putt is completed the distance remaining will reset to the full
     * distance
     */
    private void resetDistance() {
        distanceRemaining = holeDistance;
    }

    /**
     * Input the amount of players.
     *
     * @param numberOfPlayers
     */
    public void setNumberOfPlayers(int numberOfPlayers) {
        playerCount = numberOfPlayers;
    }

    /**
     * Returns the amount of players.
     *
     * @return player count
     */
    public int getNumberOfPlayers() {
        return playerCount;
    }

    /**
     * Create a player and put their name in the string array.
     *
     * @param name
     */
    public void createPlayer(String name) {
//        playerName[0] = name; //Doesn't work
    }

    /**
     * Returns a string array of player names
     *
     * @return players names
     */
    public String[] getPlayer() {
        return playerName;
    }

    /**
     * Initialize the array for testing to values of 0
     *
     * @param numberOfPlayers
     * @param hole
     * @param gameStats
     */
    private static void initializeGameStats(int numberOfPlayers, int hole, int gameStats[][]) {
        for (int playerIndex = 0; playerIndex < numberOfPlayers; playerIndex++) {
            gameStats[playerIndex][0] = 0;
        }
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
     *
     * @param roll
     * @return array of values rolled
     */
    public int[] hitTheBall(int roll) {
        //boolean to tell if putt is required
        boolean putt = true;

        //array to hold each value added to the stroke distance
        int distances[] = new int[roll];

        int sumOfStroke = 0; //value to store how far the ball is going to go
        int addToStroke = 0;
        Random rollTheDice = new Random();

        //(distanceRemaining >= diceSize - 1) stops sumOfStroke from going over the hole
        for (int rollIndex = 0; rollIndex < roll && distanceRemaining >= diceSize - 1; rollIndex++) {
            //calculates how far the ball goes during this stroke
            addToStroke = rollTheDice.nextInt(diceSize);

            //store each value rolled
            distances[rollIndex] = addToStroke;

            //keep track of the sum of the rolls
            sumOfStroke = sumOfStroke + addToStroke;

            //calculates how many yards remain, remaining yards is reset in the roll() method
            distanceRemaining = distanceRemaining - addToStroke;

            //prevent the putt from occurring if part of the original stroke;
            putt = false;

        }

        //if the distance remaining is less than the dice size just add once stroke for the putt 
        //and zero out the distance remaining to prevent endless game play
        if (distanceRemaining < diceSize - 1 && putt) {
            //index 0 will contain the remaining distance, everything else will be set to 0
            distances[0] = distanceRemaining;
            for (int rollIndex = 1; rollIndex < roll; rollIndex++) {
                distances[rollIndex] = 0;
            }

            distanceRemaining = 0;
//            resetDistance(); //This also breaks tests since it resets before it can even be detected it was 0.
        }

        return distances;
    }

    /**
     * Returns number of strokes from each roll() function
     *
     * @return number of strokes
     */
    public int getStrokes() {
        return numberOfStrokes;
    }

    /**
     * Returns the distance of the hole from where the ball starts
     *
     * @return the distance of the hole from where the ball starts
     */
    public int getHoleLength() {
        return holeDistance;
    }

    /**
     * Returns the distance the player is currently from the hole
     *
     * @return the distance the player is currently from the hole
     */
    public int getDistanceFromHole() {
        return distanceRemaining;
    }

}
//
//	public static void main(String[] args) 
//	{
// 		Game test = new Game();
// 		
// 		for (int i = 0; i < 100; i++)
// 		{
// 			int distance = test.randomHoleDistancer();
// 	 		System.out.println("Value: " + distance);
// 		}
// 		
 //		
//		test.setNumberOfPlayers(4);
//		System.out.println("Number of players: " + test.getNumberOfPlayers());
//		test.createPlayer("George");
//		test.createPlayer("John");
//		test.createPlayer("Stephen");
//		test.createPlayer("Sean");
//		System.out.println("Names of players: " + test.getPlayer());
//		int value = 0;
//		System.out.println("value: " + value);
//		test.roll();
//		System.out.println("new value: " + value);
//		value = 0;
//		value = test.roll();
//		System.out.println("new value: " + value + "\n");
//		int[] shotDistances = test.hitTheBall(value);
//		for(int i = 0; i < value; i++)
//		{
//		System.out.println("Distance: " + shotDistances[i]);
//	}
//	}
//}
//}
