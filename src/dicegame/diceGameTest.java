package dicegame;

import static org.junit.Assert.*;

import org.junit.Test;

import dicegame.Game;

public class diceGameTest {

	
	@Test
	public void constructorTest()
	{

		Game test = new Game();
		
		assertNotNull(test);
//		assertEquals(1, playerCount);
//		assertEquals(0, numberOfStrokes);
//		assertEquals(36, distanceRemaining);

	}
	
//	@Test
//	public void setNumberOfPlayers(int numberOfPlayers)
	
//	@Test
//	public void getNumberOfPlayers()
	
//	@Test
//	public void getPlayer()
	
	
	
//	@Test
//	public void createPlayer(String name)
//	{
//		Game test = new Game();
//		
//		test.createPlayer("Tom");
//		
//		System.out.println("Names of players: " + test.getPlayer());
//		
//	}
	

	
	@Test
	public void roll()
	{
		Game test = new Game();
		
		  int high = 6;
		  int low = 0;
		  
		  //Runs 100 attempts of random value assignment to ensure value is between 0 and 6
		  for(int i = 0; i < 100; i++)
		  {
			  int roll = test.roll();
			  assertTrue("Error, random is too high", high >= roll);
			  assertTrue("Error, random is too low",  low  <= roll);
			 //System.out.println("Test passed: " + roll + " is within " + high + " and " + low);
		  }
		  
	}

	@Test
	public void hitTheBall()
	{
		
		int rollValue = 0;
		
		Game test = new Game();
		
		//rolls random value between 0 and 6
		rollValue = test.roll();
		
		//rolls 'rollValue' of dice and stores each of their values
		int[] distance = test.hitTheBall(rollValue);
		
		
	 	//prints out distance of each stroke
		for(int i = 0; i < rollValue; i++)
 		{
			System.out.println("Distance: " + distance[i]);
 		}
		
	}
}
