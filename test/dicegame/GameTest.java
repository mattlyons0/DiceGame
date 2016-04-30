package dicegame;

import java.util.Random;
import org.junit.Before;
import org.junit.Test;

import dicegame.Game;
import static org.junit.Assert.*;

/**
 * Test the Game class which contains all the game logic.
 *
 * @authors Matt Lyons, David Lukacs, David McClure, Daniel Kercheski
 */
public class GameTest {

    private Game test;

    @Before
    public void setUp() {
        test = new Game();
    }

    /**
     * Test the constructor and default values of class Game.
     */
    @Test
    public void testConstructor() {
        assertNotNull(test);
        assertEquals(36, test.getHoleLength());
        assertEquals(0, test.getStrokes());
        assertEquals(36, test.getDistanceFromHole());
    }

    /**
     * Test of setNumberOfPlayers method, of class Game.
     */
    @Test
    public void testSetNumberOfPlayers() {
        //TODO in next sprint
    }

    /**
     * Test of getNumberOfPlayers method, of class Game.
     */
    @Test
    public void testGetNumberOfPlayers() {
        //TODO in next sprint
    }

    /**
     * Test of createPlayer method, of class Game.
     */
    @Test
    public void testCreatePlayer() {
        //TODO in next sprint
    }

    /**
     * Test of getPlayer method, of class Game.
     */
    @Test
    public void testGetPlayer() {
        //TODO in next sprint
    }

    /**
     * Test of roll method, of class Game.
     */
    @Test
    public void testRoll() {
        int high = 6;
        int low = 1;

        //Runs 100 attempts of random value assignment to ensure value is between 0 and 6
        for (int index = 0; index < 100; index++) {
            int roll = test.roll();
            assertTrue("Error, random is too high", high >= roll);
            assertTrue("Error, random is too low", low <= roll);
            //System.out.println("Test passed: " + roll + " is within " + high + " and " + low);
        }
    }

    /**
     * Test of hitTheBall method, of class Game.
     */
    @Test
    public void testHitTheBall() {
        int rollValue = 0;
        int high = 6;
        int low = 0;

        //Roll 100x to ensure none of the values in hitTheBall are out of bounds
        for (int count = 0; count < 100; count++) {
            //rolls random value between 0 and 6
            rollValue = test.roll();

            //rolls 'rollValue' of dice and stores each of their values
            int[] distance = test.hitTheBall(rollValue);

            //prints out distance of each stroke 'rollValue' number of times
            for (int index = 0; index < rollValue; index++) {
                //checks that each distance[i] value is within the range of 0 through 6
                assertTrue("Error, random is too high", high >= distance[index]);
                assertTrue("Error, random is too low", low <= distance[index]);
//			System.out.println("Distance: " + distance[i]);
            }
        }

        test = new Game();

        //The hit should never pass by the hole, to simulate a putt.
        int distanceLeft = test.getHoleLength();
        Random random = new Random();

        while (distanceLeft > 0) {
            int[] hitDistance = test.hitTheBall(random.nextInt(6) + 1);
            for (int dice = 0; dice < hitDistance.length; dice++) {
                distanceLeft -= hitDistance[dice];
            }
            test.roll();
        }

        test.hitTheBall(6);

        assertEquals(0, distanceLeft);
    }

    /**
     * Test of getStrokes method, of class Game.
     */
    @Test
    public void testGetStrokes() {
        int rollValue = 0;
        int strokes = 0;

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

    /**
     * Test of getHoleLength method, of class Game.
     */
    @Test
    public void testGetHoleLength() {
        int length = test.getHoleLength();
        test.roll();
        for (int number = 1; number <= 6; number++) {
            test.hitTheBall(number);
            test.roll();

            assertEquals(length, test.getHoleLength());
        }
    }

    /**
     * Test of getDistanceFromHole method, of class Game. Ensure distance is
     * calculated correctly
     */
    @Test
    public void testGetDistanceFromHole() {
        //Do this test 100 times due to the nature of the game (randomness), this should cover most edge cases
        for (int loop = 0; loop < 100; loop++) {
            int stroke = 0;
            int ydsLeft = test.getDistanceFromHole();
        	System.out.println(test.getDistanceFromHole());
            assertEquals(test.getHoleLength(), test.getDistanceFromHole());

            while (ydsLeft > 0) {
                int rollValue = test.roll();

                //runs number of strokes to add to total yards hit
                int[] distance = test.hitTheBall(rollValue);

                //add total yards from first hitTheBall() call
                for (int diceNum = 0; diceNum < distance.length; diceNum++) {
                    ydsLeft -= distance[diceNum];
                }
                //count number of strokes
                stroke++;

//            System.out.println("Number of strokes: " + stroke);
//            System.out.println("total yards: " + total);
            }

            assertEquals(stroke, test.getStrokes()); //Verify strokes match up

            assertEquals(ydsLeft, test.getDistanceFromHole()); //Verify distance matches up
            
            test = new Game(); //Reset for next iteration
        }
    }

}
	/**
	 * Test that setting the number of players matches the number of players
	 * in the game.
	 */
	@Test
	public void testSetNumberOfPlayers(int numberOfPlayers)
	{
		Game testing = new Game();
		testing.setNumberOfPlayers(3);
		int numberOfPlayers = testing.getNumberOfPlayers();
		assertEquals(numberOfPlayers, 3);
	}

@Test
public void testCreatePlayer(String name)
{
	Game test = new Game();
	
	test.createPlayer("Tom");
	
	System.out.println("Names of players: " + test.getPlayer());
	
}

@Test
public void testGetPlayerOneWins() {
	fail("Not yet implemented");
}

@Test
public void testGetPlayerTwoWins() {
	fail("Not yet implemented");
}

@Test
public void testGetPlayerThreeWins() {
	fail("Not yet implemented");
}

@Test
public void testGetPlayerFourWins() {
	fail("Not yet implemented");
}

@Test
public void testStrokeSum() {
	fail("Not yet implemented");
}

@Test
public void testCountWin() {
	fail("Not yet implemented");
}

@Test
public void testNextHole() {
	fail("Not yet implemented");
}

@Test
public void testNextPlayer() {
	fail("Not yet implemented");
}

@Test
public void testGetHoleIndex() {
	fail("Not yet implemented");
}

@Test
public void testCurrentPlayer() {
	fail("Not yet implemented");
}

@Test
public void testGetCurrentPlayer() {
	fail("Not yet implemented");
}

@Test
public void testGetGameStats() {
	fail("Not yet implemented");
}

@Test
public void testSaveGameStats() {
	fail("Not yet implemented");
}

@Test
public void testLoadGameStats() {
	fail("Not yet implemented");
}

@Test
public void testGetNumberOfHoles() {
	fail("Not yet implemented");
}

@Test
public void testSetNumberOfHoles() {
	fail("Not yet implemented");
}

@Test
public void testRandomHoleDistancer() {
	fail("Not yet implemented");
}

@Test
public void testCreateCourse() {
	fail("Not yet implemented");
}

@Test
public void testGetCourse() {
	fail("Not yet implemented");
}

@Test
public void testSetNumberOfPlayers() {
	fail("Not yet implemented");
}

@Test
public void testGetNumberOfPlayers() {
	fail("Not yet implemented");
}

@Test
public void testCreatePlayer() {
	fail("Not yet implemented");
}

@Test
public void testGetPlayer() {
	fail("Not yet implemented");
}

@Test
public void testRoll() {
	fail("Not yet implemented");
}

@Test
public void testHitTheBall() {
	fail("Not yet implemented");
}

@Test
public void testGetStrokes() {
	fail("Not yet implemented");
}

@Test
public void testGetHoleLength() {
	fail("Not yet implemented");
}

@Test
public void testGetDistanceFromHole() {
	fail("Not yet implemented");
}

@Test
public void testCreateGameStats() {
	fail("Not yet implemented");
}

@Test
public void testResetStats() {
	fail("Not yet implemented");
}

@Test
public void testAddStroke() {
	fail("Not yet implemented");
}

@Test
public void testResetHoleCount() {
	fail("Not yet implemented");
}

@Test
public void testResetPlayerCount() {
	fail("Not yet implemented");
}

@Test
public void testResetHoleIndex() {
	fail("Not yet implemented");
}

@Test
public void testResetDistancesRemainder() {
	fail("Not yet implemented");
}
}
