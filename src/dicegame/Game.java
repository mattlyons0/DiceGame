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
    private static int holeCount;
    private static int[] course = new int[holeCount]; //holds the course distances
    public int[][] gameStats = new int[playerCount][holeCount]; //holds the stroke counts for each player and hole
    public int[][] distancesRemaining = new int[playerCount][holeCount]; //holds the distances remaining for each player and hole    private String[] playerName = new String[4]; //holds player names
    private String[] playerName = new String[4]; //holds player names
    private int[] player = null;

    private int[] currentLocation = new int[2];
    private int[] nextHole = new int[18];
    private static int playerOneWins;
    private static int playerTwoWins;
    private static int playerThreeWins;
    private static int playerFourWins;
    
    private int[] currentDistance = new int[4];
    private static int playerOneDistance;
    private static int playerTwoDistance;
    private static int playerThreeDistance;
    private static int playerFourDistance;
    
    private static int[] currentGameLocation = new int[2];//index 0 holds player index, index 1 holds hole index
    
   
    private int holeIndex;
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
        holeIndex = 1; 
//		initializeGameStats(playerCount, 0, gameStats);
    }
    
    /**
     * method to store players' shot sums per hole
     */
    public void playerShotSum()
    {
    	int sum = 0;
    	int value = roll();
    	int player = getCurrentPlayer(); //gets current player
    	
    	if (player == 0)
    	{
    		int[] shotDistances = hitTheBall(value, 0 , holeIndex);
    		for(int i = 0; i < value; i++)
    		{
    			sum += shotDistances[i]; //sum adds shotDistances total
    		}
    		playerOneDistance += sum; //playerOneDistance adds sum to total for hole
    	}
    	if (player == 1)
    	{
    		int[] shotDistances = hitTheBall(value, 1 , holeIndex);
    		for(int i = 0; i < value; i++)
    		{
    			sum += shotDistances[i];
    		}
    		playerTwoDistance += sum;
    	}
    	if (player == 2)
    	{
    		int[] shotDistances = hitTheBall(value, 2, holeIndex);
    		for(int i = 0; i < value; i++)
    		{
    			sum += shotDistances[i];
    		}
    		playerThreeDistance += sum;
    	}
    	if (player == 3)
    	{
    		int[] shotDistances = hitTheBall(value, 3, holeIndex);
    		for(int i = 0; i < value; i++)
    		{
    			sum += shotDistances[i];
    		}
    		playerFourDistance += sum;
    	}
    }
    
    /**
     * method that returns distance of specified player at current hole
     * 
     * @param player
     * @return current distance of specified player at current hole
     */
    public int getCurrentPlayerDistance(int player)
    {
    	int hole = getHoleIndex();
    	
    	int current = gameStats[player][hole];
    	
    	return current;
    }
    
    /**
     * gets player one win count
     * @return playeronewin count
     */
    public int getPlayerOneWins() //works
    {
    	return playerOneWins;
    }
    
    /**
     * gets player two win count
     * @return playertwowin count
     */
    public int getPlayerTwoWins() //works
    {
    	return playerTwoWins;
    }
    
    /**
     * gets player three win count
     * @return playerthreewin count
     */
    public int getPlayerThreeWins() //works
    {
    	return playerThreeWins;
    }
    
    /**
     * gets player four win count
     * @return playerfourwin count
     */
    public int getPlayerFourWins() //works
    {
    	return playerFourWins;
    }
    
    /**
     * method to add up all strokes for each player
     * @return sum of specified row
     */
    public int strokeSum(int playerNum) //works
    {
    	int sum = 0;
    	for(int holeIndex = 0; holeIndex < holeCount; holeIndex++)
    	{
    		sum += gameStats[playerNum][holeIndex];
    	}
    	return sum;
    }
    
    /**
     * increments win count for player(s) that win based on final game score
     */
    public void countWin() //works
    {
    	if (strokeSum(0) < strokeSum(1) & strokeSum(0) < strokeSum(2) & strokeSum(0) < strokeSum(3))
    	{
    		playerOneWins++;
    	}
    	if (strokeSum(1) < strokeSum(0) & strokeSum(1) < strokeSum(2) & strokeSum(1) < strokeSum(3))
    	{
    		playerTwoWins++;
    	}
    	if (strokeSum(2) < strokeSum(0) & strokeSum(2) < strokeSum(1) & strokeSum(2) < strokeSum(3))
    	{
    		playerThreeWins++;
    	}
    	if (strokeSum(3) < strokeSum(1) & strokeSum(3) < strokeSum(0) & strokeSum(3) < strokeSum(2))
    	{
    		playerFourWins++;
    	}
    	
    	if (strokeSum(0) == strokeSum(1) & strokeSum(0) < strokeSum(3) & strokeSum(2) < strokeSum(0))
    	{
    		playerThreeWins++;
    	}
    	if (strokeSum(0) == strokeSum(1) & strokeSum(2) < strokeSum(0) & strokeSum(2) > strokeSum(3))
    	{
    		playerFourWins++;
    	}
    	if (strokeSum(0) == strokeSum(1) & strokeSum(0) < strokeSum(2) & strokeSum(0) < strokeSum(3))
    	{
    		playerOneWins++;
    		playerTwoWins++;
    	}
    	
    	if (strokeSum(0) == strokeSum(2) & strokeSum(0) < strokeSum(1) & strokeSum(0) < strokeSum(3))
    	{
    		playerOneWins++;
    		playerThreeWins++;
    	}
    	if (strokeSum(0) == strokeSum(2) & strokeSum(0) < strokeSum(3) & strokeSum(1) < strokeSum(0))
    	{
    		playerTwoWins++;
    	}
    	if (strokeSum(0) == strokeSum(2) & strokeSum(0) < strokeSum(1) & strokeSum(3) < strokeSum(0))
    	{
    		playerFourWins++;
    	}
    	
    	if (strokeSum(1) == strokeSum(2) & strokeSum(1) < strokeSum(3) & strokeSum(1) < strokeSum(0))
    	{
    		playerTwoWins++;
    		playerThreeWins++;
    	}
    	if (strokeSum(1) == strokeSum(2) & strokeSum(1) < strokeSum(3) & strokeSum(1) > strokeSum(0))
    	{
    		playerOneWins++;
    	}
    	if (strokeSum(1) == strokeSum(2) & strokeSum(3) < strokeSum(1) & strokeSum(3) < strokeSum(0))
    	{
    		playerThreeWins++;
    	}
    	
    	if (strokeSum(1) == strokeSum(3) & strokeSum(1) < strokeSum(2) & strokeSum(1) < strokeSum(0))
    	{
    		playerTwoWins++;
    		playerFourWins++;
    	}
    	if (strokeSum(1) == strokeSum(3) & strokeSum(1) < strokeSum(2) & strokeSum(0) < strokeSum(1))
    	{
    		playerOneWins++;
    	}
    	if (strokeSum(1) == strokeSum(3) & strokeSum(0) < strokeSum(1) & strokeSum(2) < strokeSum(0))
    	{
    		playerThreeWins++;
    	}
    	
    	if (strokeSum(3) == strokeSum(2) & strokeSum(2) < strokeSum(0) & strokeSum(2)  < strokeSum(1))
    	{
    		playerThreeWins++;
    		playerFourWins++;
    	}
    	if (strokeSum(3) == strokeSum(2) & strokeSum(2) < strokeSum(0) & strokeSum(1) < strokeSum(2))
    	{
    		playerTwoWins++;
    	}
    	if (strokeSum(3) == strokeSum(2) & strokeSum(0) < strokeSum(2) & strokeSum(0) < strokeSum(1))
		{
			playerOneWins++;
		}
    	
    	if (strokeSum(0) == strokeSum(1) & strokeSum(1) == strokeSum(2) & strokeSum(3) < strokeSum(2))
    	{
    		playerFourWins++;
    	}
    	if (strokeSum(0) == strokeSum(1) & strokeSum(1) == strokeSum(2) & strokeSum(3) > strokeSum(2))
    	{
    		playerOneWins++;
    		playerTwoWins++;
    		playerThreeWins++;
    	}
    	
    	if (strokeSum(0) == strokeSum(1) & strokeSum(1) == strokeSum(3) & strokeSum(2) < strokeSum(3))
    	{
    		playerThreeWins++;
    	}
    	if (strokeSum(0) == strokeSum(1) & strokeSum(1) == strokeSum(3) & strokeSum(2) > strokeSum(3))
    	{
    		playerOneWins++;
    		playerTwoWins++;
    		playerFourWins++;
    	}
    	
    	if (strokeSum(0) == strokeSum(2) & strokeSum(2) == strokeSum(3) & strokeSum(1) < strokeSum(3))
    	{
    		playerTwoWins++;
    	}
    	if (strokeSum(0) == strokeSum(2) & strokeSum(2) == strokeSum(3) & strokeSum(1) > strokeSum(3))
    	{
    		playerOneWins++;
    		playerThreeWins++;
    		playerFourWins++;
    	}
    	
    	if (strokeSum(1) == strokeSum(2) & strokeSum(2) == strokeSum(3) & strokeSum(0) < strokeSum(3))
    	{
    		playerOneWins++;
    	}
    	if (strokeSum(1) == strokeSum(2) & strokeSum(2) == strokeSum(3) & strokeSum(0) > strokeSum(3))
    	{
    		playerTwoWins++;
    		playerThreeWins++;
    		playerFourWins++;
    	}
    	
    	if (strokeSum(0) == strokeSum(1) & strokeSum(1) == strokeSum(2) & strokeSum(2) == strokeSum(3))
    	{
    		playerOneWins++;
    		playerTwoWins++;
    		playerThreeWins++;
    		playerFourWins++;
    	}
    }
    
    /**
     * Move the hole to the next hole in the game. Returns true if moved or false if the last hole.
     * @return boolean move successful
     */
    public boolean nextHole() //works
    {
    	int currentHole = holeIndex;
    	int maxHole = holeCount;
    	boolean moveSuccessful = false;
    	
    	//increment only if not on the last hole.
    	if (currentHole <  maxHole)
    	{
    		currentHole++;
    		moveSuccessful = true;
    	}
    	
    	holeIndex = currentHole;
    	return moveSuccessful;
    }
    
    /**
     * Move to the next player in game. Returns true if moved or false if not on same hole
     * @return boolean move successful
     */
    public boolean nextPlayer()
    {
    	int currentPlayer = playerIndex;
    	int maxPlayers = playerCount;
    	boolean moveSuccessful = false;
    	
    	//increment to next player until reset to 0
    	if (currentPlayer < maxPlayers)
    	{
    		currentPlayer++;
    		moveSuccessful = true;
    	}
    	playerIndex = currentPlayer;
    	return moveSuccessful;
    }
    
    /**
     * returns which hole the game is on.
     * @return hole index
     */
    public int getHoleIndex()//works
    {
    	int holeIndexLocation = holeIndex;
    	return holeIndexLocation;
    }
    
    /**
     * method that determines current player
     */
    public void currentPlayer() //works
    {
    	int[][] temp = gameStats;//pull in the gameStats array
    	boolean found = false;

    	for(int holeIndex = 0; holeIndex < holeCount; holeIndex++)
        {
    		for (int playerIndex = 0; playerIndex < playerCount; playerIndex++)
    		{
    			
                if (temp[playerIndex][holeIndex] == 0 && !found)
                {
                	currentGameLocation[0] = playerIndex; //mark the player index
                	currentGameLocation[1] = holeIndex; //mark the hole index
                	found = true;
                }
            }
    	}	
    }
    
    /**
     * returns current player
     * 
     * @return player number
     */
    public int getCurrentPlayer()//works
    {
    	currentPlayer(); //this way GUI team does not have to call two methods instead of 1
    	return currentGameLocation[0];
    }
    
    /**
     * Give the game stats as a 2d array.
     * @return game stats
     */
    public int[][] getGameStats() //works
    {
    	return gameStats;
    }
    
    /**
     * Save the game data to a file named savedGameStats.sav
     */
    public void saveGameStats()
    {
    	int oldStats[][] = gameStats;
    	
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
    public int getNumberOfHoles() //works
    { 
        return holeCount;
    }
    
    /**
     * Inputs the amount of holes
     */
    public void setNumberOfHoles(int numberOfHoles) //works
    {
        holeCount = numberOfHoles;
    }
    
    /**
     * function that returns randomHoleDistance that ranges between
     * a given high and low value
     * 
     * @return
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
     * Creates the course by putting a random number created by the randomHoleDistancer method into 
     * a global array.
     */
    public void createCourse()//works
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
    public int[] getCourse()//works
    {
    	createCourse();
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
     * @param name
     */
    public void createPlayer(String newPlayer) 
    {
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
    public int roll() 
    {
        int rolled = 0;
        Random rollTheDice = new Random();

        //rollTheDice has the possibility of returning a 0, this prevents returning a 0 roll
        while (rolled == 0) 
        {
            rolled = rollTheDice.nextInt(diceSize);
        }

        //when the first dice is rolled it signals the start of new hole
        //distanceRemaining = holeDistance; //THIS BREAKS OVERSHOOTING
        //increment the number of strokes every time the dice is rolled
        numberOfStrokes++;
        return rolled;
    }

    /**
     * Hits the ball for a total of values that were rolled by the multiplier. Automatically 
     * 	updates the array of distance remaining values.
     * @param roll
     * @param playerIndex
     * @param holeIndex
     * @return
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
     * @return number of strokes
     */
    public int getStrokes() 
    {
        return numberOfStrokes;
    }

    /**
     * Returns distance of the hole from where the ball starts
     * 
     * @return the distance of the hole from where the ball starts
     */
    public int getHoleLength() //works
    { 
    	int holeLocation = currentGameLocation[1];
    	return course[holeLocation];
    }

    /**
     * Returns the distance the player is currently from the hole
     *
     * @return the distance the player is currently from the hole
     */
    public int getDistanceFromHole() 
    {
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
    	
    	for(int playerIndex = 0; playerIndex < playerCount; playerIndex++)
    	{
    		for (int holeIndex = 0; holeIndex < holeCount; holeIndex++)
    		{
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
    public void addStroke(int player, int hole) //works
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
    private void printStats() //works
    {
    	int temp[][] = gameStats;
    	for (int playerIndex = 0; playerIndex < temp.length; playerIndex++)
    	{
    		for (int holeIndex = 0; holeIndex < temp[playerIndex].length; holeIndex++)
    		{
    			System.out.print(temp[playerIndex][holeIndex] + "\t");;
    		}
    		System.out.println();
    	}
    }
    
    /**
     * Print the hole distances remaining for testing
     */
    
    private void printDistanceRemaining()
    {
    	int temp[][] = distancesRemaining;
    	for (int playerIndex = 0; playerIndex < temp.length; playerIndex++)
    	{
    		for (int holeIndex = 0; holeIndex < temp[playerIndex].length; holeIndex++)
    		{
    			System.out.print(temp[playerIndex][holeIndex] + "\t");;
    		}
    		System.out.println();
    	}
    }
    
	public static void main(String[] args) 
	{
Game test = new Game();
 		
 		//creates 100 different random hole values
//		for (int i = 0; i < 100; i++)
//		{
// 			int distance = test.randomHoleDistancer();
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

		
		//tests getNumberOfPlayers()
		System.out.println("\n" + "Number of players: " + (test.getNumberOfPlayers()));
		
		int value = 0;
		
		//testing roll value
		value = test.roll();
		System.out.println("\n" + "Roll value: " + value + "\n");
		
		System.out.println("Print out of number of distances equal to roll value: " + "\n");
		//output number of random distances based on 'value' number
		//int[] shotDistances = test.hitTheBall(value);
		for(int i = 0; i < value; i++)
		{
		//System.out.println("Distance: " + shotDistances[i]);
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
//		test.addStroke(0, 0);
//		test.addStroke(0, 1);
//		test.addStroke(0, 2);
//		test.addStroke(0, 3);
//		test.addStroke(0, 4);
//		test.addStroke(0, 5);
//		test.addStroke(0, 6);
//		test.addStroke(0, 7);
//		test.addStroke(0, 8);
//		test.addStroke(1, 0);
		
//		test.printStats();	
		System.out.println();
		
		//testing storeWins()
		test.gameStats[0][0] = 3;
		test.gameStats[1][0] = 3;
		test.gameStats[2][0] = 5;
		test.gameStats[3][0] = 4;		
		test.gameStats[0][1] = 4;		
		test.gameStats[1][1] = 2;
		test.gameStats[2][1] = 6;
		test.gameStats[3][1] = 7;
		test.gameStats[0][2] = 1;
		test.gameStats[1][2] = 1;
		test.gameStats[2][2] = 1;
		test.gameStats[3][2] = 1;
		test.gameStats[0][3] = 4;
		
		test.printStats();
		
		//testing currentPlayer() to get current player
		//based upon above 2D set index values, current
		//player should be player number 1 (0 to 3)

		int currentPlayer = test.getCurrentPlayer();
		
		System.out.println("\n" + "Current player should be player 1 (from players 0 to 3): ");
		System.out.println("\n" + "Current player number: " + currentPlayer + "\n");
		
		//testing getHoleIndex
		System.out.println("Current hole should be 3.");
		//System.out.println("Current hole being played: " + getHoleIndex());
		
		//testing strokeSum()
		int totalOne = test.strokeSum(0);
		int totalTwo = test.strokeSum(1);
		int totalThree = test.strokeSum(2);
		int totalFour = test.strokeSum(3);
				
		System.out.println("\n" + "Total for player 1: " + totalOne);
		System.out.println("Total for player 2: " + totalTwo);
		System.out.println("Total for player 3: " + totalThree);
		System.out.println("Total for player 4: " + totalFour);
		
		
		test.countWin();
		int oneWins = playerOneWins;
		int twoWins = playerTwoWins;
		int threeWins = playerThreeWins;
		int fourWins = playerFourWins;
		
		System.out.println("\n" + "Total for player 1: " + oneWins);
		System.out.println("Total for player 2: " + twoWins);
		System.out.println("Total for player 3: " + threeWins);
		System.out.println("Total for player 4: " + fourWins + "\n");
		
		System.out.println("Current hole length: " + test.getHoleLength() + "\n");
		
		
		test.resetStats();
		test.printStats();
		System.out.println();
		
		test.gameStats[0][0] = 5;
		test.gameStats[1][0] = 4;		
		test.gameStats[2][0] = 4;		
		test.gameStats[3][0] = 2;
		test.gameStats[0][1] = 6;
		test.gameStats[1][1] = 7;
		test.gameStats[2][1] = 1;
		test.gameStats[3][1] = 5;
		test.gameStats[0][2] = 7;
		
		test.printStats();
		
		//testing currentPlayer() to get current player
		//based upon above 2D set index values, current
		//player should be player number 1 (0 to 3)

		int currentPlayer2 = test.getCurrentPlayer();
		
		System.out.println("\n" + "Current player should be player 1 (from players 0 to 3): ");
		System.out.println("\n" + "Current player number: " + currentPlayer2 + "\n");
		
		System.out.println("Current hole length: " + test.getHoleLength());
		System.out.println();
		
		//System.out.println("Current hole being played: " + getHoleIndex());
		
		//line 284
		test.nextPlayer();
		System.out.println("\n" + "Current player number: " + currentPlayer2 + "\n");
		
		int someDistance = test.getCurrentPlayerDistance(0);
		System.out.println("\n" + "Current distance shot: " + someDistance);
		
		//testing playerShotSum() to see if playerTwoDistance counter
		//increases total distance
		//
		// ISSUE: DOES NOT APPEAR TO CORRECTLY ADD SUM AS DESIRED
		System.out.println();
		test.playerShotSum();
		System.out.println("Current distance shot by Player " + test.getCurrentPlayer()
		+ " and their current distance: " + playerTwoDistance);
		
		System.out.println();
		test.playerShotSum();
		System.out.println("Current distance shot by Player " + test.getCurrentPlayer()
		+ " and their current distance: " + playerTwoDistance);
		
		System.out.println();
		test.playerShotSum();
		System.out.println("Current distance shot by Player " + test.getCurrentPlayer()
		+ " and their current distance: " + playerTwoDistance);	
	}
}

