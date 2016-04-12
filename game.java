	package dicegame;

	import java.util.*;
	/**Handle the game play logic and statistics
 	 * @param args
 	 * @authors Matt Lyons, David Lukacs, David McClure, Daniel Kercheski
 	 * CSE360 Team Project
 	 */
    public class game {
 	
 	static int rolledValue; //place holder for the first rolled value
 	static int diceSize = 7; //roll method will return random whole number between 1 and diceSize - 1
	static int holeDistance = 36;
 	static int distanceRemaining; //place holder for the distance remaining, initialized in the roll() method
 	static int numberOfStrokes; //place holder to keep track of strokes
 
 	static int playerName;
 	static int playerCount;
 	static int holeCount = 18;
 	static int[][] gameStates = new int[playerCount][holeCount];
 	static String[] playerList = new String[playerName];

 	/**
 	* Constructor to ensure that all values are initialized when starting a 
 	* new game.
 	*/
 	 	game()
 	 	{
 	 		rolledValue = 0;
 	 		distanceRemaining = 36;
 	 		numberOfStrokes = 0;
 	 		playerCount = 1;
 	 		playerName = 4;
 	 	}
 	
 	/**
 	 * Rolls the dice and returns the value between 1 and (diceSize -1) 
 	 * @return int value rolled by dice
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
 	 * Rolls the dice a number of times determined by roll(). This roll does include 0 in
 	 * the possible returns as it is possible to completely miss the ball.
 	 * @return 
 	 */
 	
 	public static int hitTheBall()
 	{
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
 		 	numberOfStrokes++;
 		}
 		
 		
 		if(seeValues)
 		{
 			System.out.println("size of stroke:\t\t" + sumOfStroke);
 			System.out.println("distance remaining:\t" + distanceRemaining);
 		}
 		
 		numberOfStrokes++;
 		
 		return numberOfStrokes;
 		
 	}
 	
 	/**
 	 * Calls the method to roll the dice, then repeatedly hits the ball while counting the strokes 
 	 * until less than a dice roll is left. It then adds one stroke that counts for the putt and ends 
 	 * the turn.
 	 * 
 	 * Initializes: number of strokes
 	 */
 	
 	public static int takeATurn()
 	{
 		//reset the number of strokes for the turn to 0
 		int strokes = 0;
		
 		//find the initial roll value
 		roll();
		
 		//keep hitting the ball until it goes in the hole
 		while(distanceRemaining > 1 && distanceRemaining >= diceSize - 1)
		{
			hitTheBall();
 			strokes++;
 		}
		
 		//Once the distanceRemaining is less than a dice roll add one for the putt and end the turn
 		strokes++;
 		
 		
 		numberOfStrokes = strokes;
 		
 		return numberOfStrokes;
 	}
 	
 	public static void createPlayer()
 	 {
 		playerCount++;
 	 }
 	
 	/**
 	 * A method that is passed an index value and a String 'name' and stores
 	 * that name in the corresponding index location
 	 * 
 	 * @param nameOrder
 	 * @param name
 	 */
 	
 	public static void playerArray(int nameOrder, String name)
	{
		if(nameOrder == 0)
		{
			playerList[nameOrder] = name;
		}
		if(nameOrder == 1)
		{
			playerList[nameOrder] = name;
		}
		if(nameOrder == 2)
		{
			playerList[nameOrder] = name;
		}
		if(nameOrder == 3)
		{
			playerList[nameOrder] = name;
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
			      System.out.print(gameStates[i][j] + "\t");
			   }
			   System.out.println();
		}
 	}
 	
 	
 	public static void main(String[] args) 
 	{
 		String name;
 		boolean quit = false;
 		
 		Scanner in = new Scanner(System.in);
 		
 		do
 		{
 			System.out.println("Welcome to Dice Golf!");
 			//user determines number of players playing
 			System.out.println("\n\n" + "Enter a value between 2 and 4 to set"
 					+ "how many players you want to play this match." + "\n\n");
 			playerCount = in.nextInt();
 			
 			if (playerCount != 2 && playerCount != 3 && playerCount != 4)
 			{
 				do
 				{
 					System.out.println("Please enter a value between 2 and 4: ");
 					playerCount = in.nextInt();
 	 				
 				}while (playerCount != 2 && playerCount != 3 && playerCount != 4);
 			}
 			
 			System.out.println("There are " + playerCount + " players now playing." + "\n\n");

 			
 			//Store player names as they're entered
 			//using playerArray function - ArrayIndexOutOfBoundsException occurs : why?
 			for(int i = 0; i < playerCount + 1 ; i++)
 			{
 				System.out.println("Enter the name of player " + (i + 1));
 				name = in.next();
 				playerArray(i, name);
			} 			
 			
 			//prints out all players
 			for (String s: playerList)
 	        {
 	        	System.out.println(s);
 	        }
 			
 			//loads a stroke value into every index value of each player automatically
 			//just to test the takeATurn() method
 			for (int i=0; i < playerCount; i++)
 			{
 				for (int j=0; j < holeCount; j++)
 				{
 					gameStates[i][j] = takeATurn();
 				}
 			}
 			
 			//prints out the 2D array
 			statsUpdate();
 			
 			System.out.println("\n\n"+ "Would you like to play again? 1 - yes : 2 - no ");
 			int answer = in.nextInt();
 			
 			if (answer == 1)
 			{
 				quit = false;
 			}
 			else if (answer < 1 && answer > 2)
 			{
 				System.out.println("Not a valid input.");
 			}
 			else
 			{
 				quit = true;
 			}
 			
 		}while(quit != true);
 		
 		System.out.println("\n" + "Thanks for playing!");
	}
 
 }