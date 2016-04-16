package dicegame;

import static org.junit.Assert.*;

import org.junit.Test;

public class diceGameTest {

    @Test
    public void constructorTest() {

        Game test = new Game();

        assertNotNull(test);
        assertEquals(36,test.getHoleLength());
        assertEquals(0,test.getStrokes());
        assertEquals(36,test.getDistanceFromHole());
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
    public void rollTest() {
        Game test = new Game();

        int high = 6;
        int low = 0;

        //Runs 100 attempts of random value assignment to ensure value is between 0 and 6
        for (int i = 0; i < 100; i++) {
            int roll = test.roll();
            assertTrue("Error, random is too high", high >= roll);
            assertTrue("Error, random is too low", low <= roll);
            //System.out.println("Test passed: " + roll + " is within " + high + " and " + low);
        }

    }

    @Test
    public void hitTheBallTest() {

        int rollValue = 0;
        int high = 6;
        int low = 0;

        Game test = new Game();

        //rolls random value between 0 and 6
        rollValue = test.roll();

        //rolls 'rollValue' of dice and stores each of their values
        int[] distance = test.hitTheBall(rollValue);

        //prints out distance of each stroke 'rollValue' number of times
        for (int i = 0; i < rollValue; i++) {
            //checks that each distance[i] value is within the range of 0 through 6
            assertTrue("Error, random is too high", high >= distance[i]);
            assertTrue("Error, random is too low", low <= distance[i]);
//			System.out.println("Distance: " + distance[i]);
        }

    }

    @Test
    public void strokeCountTest() {
        int rollValue = 0;
        int strokes = 0;

        Game test = new Game();

        //rolls random value between 0 and 6
        rollValue = test.roll();

        //rolls 'rollValue' of dice and stores each of their values
//		int[] distance = test.hitTheBall(rollValue);
        //collects the number of roll() occurrences (strokes) from hitTheBall()
        //by multiplying number of roll() occurrences by instance of numberOfStrokes count
        strokes = (test.getStrokes() * rollValue);
//		System.out.println("\n" + "Number of value: " + rollValue);
//		System.out.println("Number of strokes: " + strokes);

        assertEquals(rollValue, strokes);
    }

    @Test
    public void puttTest() {
        int distanceRemaining = 6;
        int rollValue;
        int stroke = 0;
        int total = 0;

        Game test = new Game();

        rollValue = test.roll();

        //runs number of strokes to add to total yards hit
        test.hitTheBall(rollValue);
        int[] distance = test.hitTheBall(rollValue);

        //add total yards from first hitTheBall() call
        //use hitTheBall() again until total yardage reaches 36
        //count number of strokes
        //print out distance values to show distance left was between 0 and 6
        //and only 1 stroke was added
        for (int i = 0; i < rollValue; i++) {
            System.out.println("Distance: " + distance[i]);
            stroke++;

            //add up total yards
        }

        System.out.println("Number of strokes: " + stroke);
        System.out.println("total yards: " + total);

    }

}
