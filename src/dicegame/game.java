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
	
	
	/**
	 * Constructor to ensure that all values are initialized when starting a 
	 * new game.
	 */
	game()
	{
		rolledValue = 0;
		distanceRemaining = 36;
		numberOfStrokes = 0;
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
	 * Rolls the dice a number of times determined by roll(). This roll does include 0 in
	 * the possible returns as it is possible to completely miss the ball.
	 * 
	 * Updates: Distance Remaining
	 */
	
	public static void hitTheBall()
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
			numberOfStrokes++;
		}
		
		
		if(seeValues)
		{
			System.out.println("size of stroke:\t\t" + sumOfStroke);
			System.out.println("distance remaining:\t" + distanceRemaining);
		}
		
		numberOfStrokes++;
	}
	
	/**
	 * Calls the method to roll the dice, then repeatedly hits the ball while counting the strokes 
	 * until less than a dice roll is left. It then adds one stroke that counts for the putt and ends 
	 * the turn.
	 * 
	 * Initializes: number of strokes
	 */
	public static void takeATurn()
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
	}
	
	
	public static void main(String[] args) 
	{
		
		
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
		hitTheBall();
		if(distanceRemaining < holeDistance)
		{
			System.out.println("hitTheBall() : distance remaining test  : Passed");
		}
		else
		{
			System.out.println("hitTheBall() : distance remaining test  : Failed");
		}
		
		//Testing that hit the ball increase the number of strokes
		roll();
		
		numberOfStrokes = 0;
		
		hitTheBall();
		if(numberOfStrokes == 1)
		{
			System.out.println("hitTheBall() : number of strokes test   : Passed");
		}
		else
		{
			System.out.println("hitTheBall() : number of strokes test   : Failed");
		}
			
		//Testing that hit the ball increase the number of strokes after the initial hit
		roll();
		hitTheBall();
		if(numberOfStrokes == 2)
		{
			System.out.println("hitTheBall() : number of strokes test   : Passed");
		}
		else
		{
			System.out.println("hitTheBall() : number of strokes test   : Failed");
		}
					
	}

}
