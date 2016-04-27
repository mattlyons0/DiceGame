package dicegame;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    private int playerCount = 0; //does this need to be initialized to 0?
    private int holeCount;
    private int[] course = new int[holeCount];
    private int[][] gameStats = new int[playerCount][holeCount];
    private String[] playerName = new String[4];
    private int[] player = null;

    /**
     * Constructor to ensure that all values are initialized when starting a new
     * game.
     */
    public Game() {
        distanceRemaining = 36;
        numberOfStrokes = 0;
        playerCount = 1;
        holeCount = 1;
//		initializeGameStats(playerCount, 0, gameStats);

    }

    /**
     * method that determines current player
     */
    public int[] currentPlayer()
    {
    	for (int playerIndex = 0; playerIndex < playerCount; playerIndex++) {
            for (int holeIndex = 0; holeIndex < holeCount; holeIndex++) {
                if (gameStats[playerIndex][holeIndex] == 0) {
                	player = gameStats[playerIndex];
                }
            }
    	}	
    	return player;
    }
    
    
    /**
     * returns current player
     * 
     * @return player number
     */
    public int[] getCurrentPlayer()
    {
    	return player;
    }
    
    /**
     * Give the game stats as a 2d array.
     * @return game stats
     */
    
    public int[][] getGameStats()
    {
    	return gameStats;
    }
    
    /**
     * Save the game data to a file named savedGameStats.sav
     */
    
    public void saveGameStats()
    {
    	int oldStats[][] = gameStats;
 //   	int number = playerCount;		//this is not used
    	try
    	{
    		//create a file
    		FileOutputStream savedFile = new FileOutputStream("savedGameStats.sav");
    		ObjectOutputStream saved = new ObjectOutputStream(savedFile);
    		
    		//put the array in the file
    		saved.writeObject(oldStats);
    		
    		
    		//close the file
    		saved.close();
    	}
    	catch(Exception e)
    	{
    		System.out.println("Error saving file");
    	}
    }
    
    
    /**
     * Load the game stats from the file savedGameStats.sav
     */
    
    public void loadGameStats()
    {
    	try
    	{
    		//load the file
    		FileInputStream savedFile = new FileInputStream("savedGameStats.sav");
    		ObjectInputStream saved = new ObjectInputStream(savedFile);
    		
    		//put the array back into the game
    		gameStats = (int[][]) saved.readObject();
    		
    		
    		//close the file
    		saved.close();
    	}
    	catch(Exception e)
    	{
    		System.out.println("Error loading the file");
    	}
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
     * Creates the course by putting a random number created by the randomHoleDistancer method into 
     * a global array.
     */
    public void createCourse()
    {
    	int tempCourse[] = new int[holeCount]; //temporary storage for the course creation
    	for (int holeIndex = 0; holeIndex < holeCount; holeIndex++)
    	{
    		tempCourse[holeIndex] = randomHoleDistancer();//place each distance into the array
    	}
    	
    	course = tempCourse;//put all distances into the global array
    }
    
    /**
     * Returns the hole distance for each hole
     * @return course 
     */
    public int[] getCourse()
    {
    	return course;
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
    public void createPlayer(String newPlayer) {
    	
    	playerName[playerCount - 1] = newPlayer; 	// Put new player in next
        									//     available spot.
    	playerCount++;  						// And increment playerCt to count the new player.
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
    
    
    /**
     * create a new gameStats   
     */
    public void createGameStats()
    {
    	int players = playerCount;
    	int holes = holeCount;
    	
    	int temp[][] = new int[players][holes];
    	gameStats = temp;
    	
    }
    
    /**
     * Reset the game stats
     */
    
    public void resetStats()
    {
    	int temp[][] = gameStats;
    	
    	for (int playerIndex = 0; playerIndex < temp.length; playerIndex++)
    	{
    		for (int holeIndex = 0; holeIndex < temp[playerIndex].length; holeIndex++)
    		{
    			temp[playerIndex][holeIndex] = 0;
    		}
    	}
    	
    	gameStats = temp;
    }
    
    /**
     * Add a stroke for the specified player and hole
     * @param player
     * @param hole
     */
    public void addStroke(int player, int hole)
    {
    	int temp[][] = gameStats;
    	try
    	{
    		temp[player][hole]++;
    	}
    	catch(Exception e)
    	{
    		System.out.println("Error: incorrect player number or hole number");
    	}
    }
    
    /**
     * Print the gameStats array for testing
     */
    
    private void printStats()
    {
    	int temp[][] = gameStats;
    	for (int playerIndex = 0; playerIndex < temp.length - 1; playerIndex++)
    	{
    		for (int holeIndex = 0; holeIndex < temp[playerIndex].length; holeIndex++)
    		{
    			System.out.print(temp[playerIndex][holeIndex]);;
    		}
    		System.out.println();
    	}
    }


//}

	public static void main(String[] args) 
	{
 		Game test = new Game();
 		
 		//creates 100 different random hole values
 //		for (int i = 0; i < 100; i++)
//		{
 //			int distance = test.randomHoleDistancer();
 //	 		System.out.println("Value: " + distance);
 //		}
		
		//set number of holes and print the number out
 		test.setNumberOfHoles(9);
 		System.out.println("Number of holes: " + test.getNumberOfHoles() + "\n");
 		
		//test setting player names
		test.createPlayer("George");
		test.createPlayer("John");
		test.createPlayer("Stephen");
		test.createPlayer("Sean");
		
		//stores address of values into new array
		String[] playerName = test.getPlayer();
		
		//test printing out stored player names
		System.out.println("Names of players: " + "\n");
		
		for (int i = 0; i < 4; i++)
		{
			System.out.println("Player name: " + playerName[i]);
		}
		
		//Initialize all of matrix indices to 0
		//print out current score card
		System.out.println("\n" + "Testing 2D array output and manipulations: " + "\n");
		test.createGameStats();
		test.printStats();
		System.out.println();
		
		//change matrix values with [0][0] to 1
		System.out.println("Changed values at [0][0] and [1][8]: " + "\n");
		test.gameStats[0][0] = 1;
		test.addStroke(1, 8);
		test.printStats();
		
		System.out.println("\n" + "Resetting values back to 0: " + "\n");
		//test reset method to set all values back to 0
		test.resetStats();
		System.out.println();
		test.printStats();

		
		//playerCount starts at 0; each time createPlayer() is used
		//playerCount increases by 1; when 4th player is created
		//there would be 5 players, because of counter, thus the inclusion of "- 1"
		System.out.println("\n" + "Number of players: " + (test.getNumberOfPlayers() - 1));
		
		int value = 0;
		
		//testing roll value
		value = test.roll();
		System.out.println("\n" + "Roll value: " + value + "\n");
		
		System.out.println("Print out of number of distances equal to roll value: " + "\n");
		//output number of random distances based on 'value' number
		int[] shotDistances = test.hitTheBall(value);
		for(int i = 0; i < value; i++)
		{
		System.out.println("Distance: " + shotDistances[i]);
		}
		
		test.createCourse();
		System.out.println("\n" + "Hole distances: " + "\n");
		int courseList [] = test.getCourse();
		
		for (int i = 0; i < test.getNumberOfHoles(); i++)
		{
			System.out.println("Hole distance: " + courseList[i] + "\n");
		}
		
		//creates 2D array that fills up all of first row
		//fills up first column of second row
		test.addStroke(0, 0);
		test.addStroke(0, 1);
		test.addStroke(0, 2);
		test.addStroke(0, 3);
		test.addStroke(0, 4);
		test.addStroke(0, 5);
		test.addStroke(0, 6);
		test.addStroke(0, 7);
		test.addStroke(0, 8);
		test.addStroke(1, 0);
		
		test.printStats();
		
		//testing currentPlayer() to get current player
		//based upon above 2D set index values, current
		//player should be player number 1 (0 to 3)
		test.currentPlayer();
		int[] currentPlayer = test.getCurrentPlayer();
		
		System.out.println("\n" + "Current player should be player 1 (from players 0 to 3): ");
		
		for(int i = 0; i < 1; i++)
		{
			System.out.print("\n" + "Current player number: " + currentPlayer[i]);
		}
		
	}
}

