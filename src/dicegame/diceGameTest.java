package dicegame;

import static org.junit.Assert.*;

import org.junit.Test;

public class diceGameTest {

	
	@Test
	public void constructorTest()
	{
		@SuppressWarnings("unused")
		Game test = new Game();
	}
	
	

	
	
	@Test
	public void createPlayer(String name)
	{
		Game test = new Game();
		
		test.createPlayer("Tom");
		
		System.out.println("Names of players: " + test.getPlayer());
		
	}
	

	
	@Test
	public void roll()
	{
		Game test = new Game();
		
		int roll = test.roll();
		System.out.println("Roll value should be between 1 and 6: " );
		System.out.println("Roll value: " + roll);
	}

	@Test
	public void hitTheBall(int roll)
	{
		Game test = new Game();
		
		int rollValue = test.roll();
		int[] distance = test.hitTheBall(rollValue);
		
		System.out.print("Distance of first stroke: " + distance);
	}
}
