package dicegame;

import java.util.*;
/**Handle the game play logic and statistics
	 * @param args
	 * @authors Matt Lyons, David Lukacs, David McClure, Daniel Kercheski
	 * CSE360 Team Project
	 */
public class Game {
	
	static int rolledValue; //place holder for the first rolled value
	static int diceSize = 7; //roll method will return random whole number between 1 and diceSize - 1
	static int holeDistance = 36;
	static int distanceRemaining; //place holder for the distance remaining, initialized in the roll() method
	static int numberOfStrokes; //place holder to keep track of strokes
	
	static int playerCount = 10;
	static int holeCount = 18;
	static int[][]	gameStats = new int[playerCount][holeCount];
	static String[] playerName = new String[playerCount];
	
	/**
	 * Constructor to ensure that all values are initialized when starting a 
	 * new game.
	 */
	Game()
	{
		rolledValue = 0;
		distanceRemaining = 36;
		numberOfStrokes = 0;
		playerCount = 1;
		
	}
	
	
	/**
	 * Rolls the dice and returns the value between 1 and (diceSize -1) 
	 *
	 * Initializes: distance remaining, rolled value
	 */
	
	public static void roll()
	{
		int rolled = 0;
		Random rollTheDice = new Random();
		
		//rollTheDice has the possibility of returning a 0, this prevents returning a 0 roll
		while(rolled == 0)
		{	
			rolled = rollTheDice.nextInt(diceSize);
		}
		
		rolledValue = rolled;
		
		
		//when the first dice is rolled it signals the start of new hole.
		distanceRemaining = holeDistance;
	}
	
	
	/**
	 * Finds the sum of a number of values that determine how far the ball is hit.
	 * The number of values is determined by rolling the dice.
	 * @param playerIndex
	 * @param holeNumber
	 */
	
	public static void hitTheBall(int playerIndex, int holeNumber)
	{
		//Troubleshooting key
		boolean seeValues = false;
		
		int sumOfStroke = 0; //value to store how far the ball is going to go
		int addToStroke = 0;
		Random rollTheDice = new Random();
		
		//(distanceRemaining >= diceSize - 1) stops sumOfStroke from going over the hole
		for(int i = 0; i < rolledValue && distanceRemaining >= diceSize - 1; i++ )
		{
			//calculates how far the ball goes during this stroke
			addToStroke = rollTheDice.nextInt(diceSize);
			sumOfStroke = sumOfStroke + addToStroke;
			
			//calculates how many yards remain, remaining yards is reset in the roll() method
			distanceRemaining = distanceRemaining - addToStroke;
		}
		
		//if the distance remaining is less than the dice size just add once stroke for the putt 
		//and zero out the distance remaining to prevent endless game play
		if (distanceRemaining < diceSize - 1)
		{
			distanceRemaining = 0;
			gameStats[playerIndex][holeNumber] = gameStats[playerIndex][holeNumber] + 1;
		}
		
		else
		{
			gameStats[playerIndex][holeNumber] = gameStats[playerIndex][holeNumber] + 1;
		}
		
		
		if(seeValues)
		{
			System.out.println("size of stroke:\t\t" + sumOfStroke);
			System.out.println("distance remaining:\t" + distanceRemaining);
		}
		
		
	}
	
	
	
	/**
	 * Creates a new player in the game by adding 1 to the player count in the stats array
	 * 
	 * @param String players name to be added
	 */
	public static void createPlayer(String name)
	{
		playerCount++;
		playerName[(playerCount - 1)] = name;
		
	}
	
	
	/**
	 * Initialize the game statistics array to all 0s so that additions can be performed.
	 * 
	 */
	public static void initializeGameStats()
	{
		for (int playerIndex = 0; playerIndex < playerCount; playerIndex++)
		{
			for (int holeIndex = 0; holeIndex < holeCount; holeIndex++)
			{
				gameStats[playerIndex][holeIndex] = 0;
			}
		}
	}
	
	/**
 	 * Prints out current stats of players and hole-stroke counts for each respective hole
 	 * in a 2D-array format
 	 */
 	public static void statsUpdate()
 	{
 		for (int i=0; i < playerCount; i++)
		{
			   for (int j=0; j < holeCount; j++)
			   {
			      System.out.print(gameStats[i][j] + "\t");
			   }
			   System.out.println();
		}
 	}
 	
	public static void main(String[] args) 
	{
		
		initializeGameStats();
		//Testing that dice values are in the correct range.
		boolean diceValue = true;
		for (int i = 0; i < 100 && diceValue; i++) //100 is just an arbitrary amount
		{
			roll();
			if(rolledValue > diceSize - 1 || rolledValue < 1)//ensures that values should be between 1 and the dice value -1
			{
				diceValue = false;
			}
			
		}
		
		if(diceValue)
		{
			System.out.println("roll()       : testing 100 dice values  : Passed");
		}
		else
		{
			System.out.println("roll()       : testing 100 dice values  : Failed");
		}
		
		
		
		//Testing that hit the ball decreases the distance remaining
		roll();
		hitTheBall(0,0);
		if(distanceRemaining < holeDistance)
		{
			System.out.println("hitTheBall() : distance remaining test  : Passed");
		}
		else
		{
			System.out.println("hitTheBall() : distance remaining test  : Failed");
		}
		
		
		
		//Test that hit the ball adds one hit to the score card for the appropriate person.
		System.out.println("Score card: should show player 1 has 2 strokes, player 2 has 1, and player 3 has 1.");
		roll();
		hitTheBall(0,0);
		
		roll();
		hitTheBall(1,0);
		
		roll();
		hitTheBall(2,0);
		
		statsUpdate();
		
		//Test that hit the ball adds one hit to the score card for different holes.
		System.out.println("\nScore card: should show player 1 has 1 strokes, player 2 has 1, and player 3 has 1, for hole 2.");
		roll();
		hitTheBall(0,1);
		
		roll();
		hitTheBall(1,1);
		
		roll();
		hitTheBall(2,1);
		
		statsUpdate();
		
		
		
					
	}

}
