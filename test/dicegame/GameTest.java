package dicegame;

import org.junit.Before;
import org.junit.Test;

import dicegame.Game;
import java.io.File;
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

        test.createPlayer("");
        test.setNumberOfHoles(1);
        test.createCourse();
        test.createGameStats();

        assertEquals(0, test.getStrokes(0, 0));
        assertEquals(test.getHoleLength(), test.getDistanceFromHole(0));
    }

    /**
     * Test of setNumberOfPlayers method, of class Game Test method to show that
     * number of players created matches number of players in game
     */
    @Test
    public void testSetNumberOfPlayers() {
        test.setNumberOfPlayers(3);
        int numberOfPlayers1 = test.getNumberOfPlayers();
        assertEquals(numberOfPlayers1, 3);
    }

    /**
     * Test of getNumberOfPlayers method, of class Game Test method to show that
     * set number of players value can be extracted from game
     */
    @Test
    public void testGetNumberOfPlayers() {
        test.setNumberOfPlayers(3);
        int numberOfPlayers = test.getNumberOfPlayers();
        assertEquals(numberOfPlayers, 3);
    }

    /**
     * Test of createPlayer method, of class Game.
     */
    @Test
    public void testCreatePlayer() {

        assertEquals(0, test.getNumberOfPlayers());

        test.createPlayer("Sean");

        assertEquals("Sean", test.getPlayer()[0]);
        assertEquals(1, test.getNumberOfPlayers());

        test.createPlayer("Bob");
        test.createPlayer("Joe");

        String[] players = {"Sean", "Bob", "Joe", null};
        assertArrayEquals(players, test.getPlayer());
        assertEquals(3, test.getNumberOfPlayers());
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
            int[] distance = test.hitTheBall(rollValue, 0, 0);

            //prints out distance of each stroke 'rollValue' number of times
            for (int index = 0; index < rollValue; index++) {
                //checks that each distance[i] value is within the range of 0 through 6
                assertTrue("Error, random is too high", high >= distance[index]);
                assertTrue("Error, random is too low", low <= distance[index]);
//			System.out.println("Distance: " + distance[i]);
            }
        }
    }
//
//

    /**
     * Test of getHoleLength method, of class Game.
     */
    @Test
    public void testGetHoleLength() { //Tested more exhaustively in other tests
        int length = test.getHoleLength();
        test.roll();
        for (int number = 1; number <= 6; number++) {
            test.hitTheBall(number, 0, 0);
            test.roll();

            assertEquals(length, test.getHoleLength());
        }
    }
//

    /**
     * Test of getDistanceFromHole method, of class Game. Ensure distance is
     * calculated correctly
     */
    @Test
    public void testGetDistanceFromHole() {
        test.createPlayer("t");
        test.createCourse();
        test.createGameStats();

        //Do this test 100 times due to the nature of the game (randomness), this should cover most edge cases
        for (int loop = 0; loop < 100; loop++) {
            int ydsLeft = test.getDistanceFromHole(0);
            assertEquals(test.getHoleLength(), test.getDistanceFromHole(0));

            while (ydsLeft > 0) {
                int rollValue = test.roll();

                //runs number of strokes to add to total yards hit
                int[] distance = test.hitTheBall(rollValue, 0, 0);

                //add total yards from first hitTheBall() call
                for (int diceNum = 0; diceNum < distance.length; diceNum++) {
                    ydsLeft -= distance[diceNum];
                }
                //count number of strokes

            }

            assertEquals(ydsLeft, test.getDistanceFromHole(0)); //Verify distance matches up

            test = new Game(); //Reset for next iteration
            test.createPlayer("t");
            test.createCourse();
            test.createGameStats();
        }
    }
//
//}

    /**
     * Test getLosses()
     */
    @Test
    public void testGetLosses() { //Losses are calculated from wins so also tests that
        test.createCourse();
        test.createGameStats();

        assertEquals(0, test.getLosses(0));

        test.createPlayer("");
        test.createPlayer("f");
        test.setNumberOfHoles(1);
        test.createCourse();
        test.createGameStats();

        for (int count = 0; count < 10; count++) {
            test.addStroke(0, 0);
            if (count < 2) {
                test.addStroke(1, 0);
            }
        }

        assertEquals(1, test.getLosses(0));
        assertEquals(0, test.getLosses(1));
    }

    /**
     * Test that setting the number of players matches the number of players in
     * the game.
     */
    @Test
    public void testSetNumberOfPlayers1() {
        Game testing = new Game();
        testing.setNumberOfPlayers(3);
        int numberOfPlayers = testing.getNumberOfPlayers();
        assertEquals(numberOfPlayers, 3);
    }

    @Test
    public void testCreatePlayer1() {
        Game test = new Game();

        test.createPlayer("Tom");

        System.out.println("Names of players: " + test.getPlayer());

    }
//

    @Test
    public void testStrokeSum() {

        test.createPlayer("");
        test.createPlayer("f");
        test.createCourse();
        test.createGameStats();

        assertEquals(0, test.strokeSum(0));
        assertEquals(0, test.strokeSum(1));

        test.addStroke(0, 0);
        assertEquals(1, test.strokeSum(0));
        assertEquals(0, test.strokeSum(1));

        test.addStroke(1, 0);
        assertEquals(1, test.strokeSum(0));
        assertEquals(1, test.strokeSum(1));

        test.addStroke(0, 14);
        assertEquals(1, test.strokeSum(0));
        assertEquals(1, test.strokeSum(1));

    }

    @Test
    public void testNextHole() {
        test.setNumberOfHoles(3);

        assertEquals(0, test.getHoleIndex()); //tests to show hole is at 0
        test.nextHole(); //shifts hole up 1 increment holeIndex should be 1
        assertEquals(1, test.getHoleIndex());
        test.nextHole();
        assertEquals(2, test.getHoleIndex());
        test.nextHole();
        assertEquals(3, test.getHoleIndex());
        test.nextHole();
        assertEquals(3, test.getHoleIndex());

    }

    @Test
    public void testGetHoleIndex() {

        Game test = new Game();

        test.setNumberOfHoles(2);
        test.createCourse();
        test.createGameStats();

        assertEquals(0, test.getHoleIndex());//current hole should be at index 0
        test.nextHole();
        assertEquals(1, test.getHoleIndex());
        test.nextHole();
        assertEquals(2, test.getHoleIndex());
        test.nextHole();
        assertEquals(2, test.getHoleIndex());

    }

    @Test
    public void testGetCurrentPlayer() {
        test.createGameStats();
        test.setNumberOfHoles(5);
        test.createCourse();
        test.createGameStats();

        assertEquals(-1, test.getCurrentPlayer()); //current player doesnt exist

        test = new Game();
        test.createPlayer("Sean");
        test.setNumberOfHoles(2);
        test.createCourse();
        test.createGameStats();
        assertEquals(0, test.getCurrentPlayer());

        test.hitTheBall(8, 0, 0);
        assertEquals(0, test.getCurrentPlayer()); //Always returns first player 
        test.nextHole();
        assertEquals(0, test.getCurrentPlayer()); //Even on different holes

        test = new Game();
        test.createPlayer("a");
        test.createPlayer("b");
        test.setNumberOfHoles(2);
        test.createCourse();
        test.createGameStats();

        while (test.getCurrentPlayerDistance(0, 0) != 0 && test.getCurrentPlayerDistance(1, 0) != 0) {
            int[] hit1 = test.hitTheBall(1, 0, 0);
            if (test.getCurrentPlayerDistance(0, 0) >= test.getCurrentPlayerDistance(1, 0)) {
                assertEquals(0, test.getCurrentPlayer());
            } else {
                assertEquals(1, test.getCurrentPlayer()); //Furthest player should go next
            }
            int[] hit2 = test.hitTheBall(5, 1, 0);
            int p1Distance = hit1[0];
            int p2Distance = hit2[0] + hit2[1] + hit2[2] + hit2[3] + hit2[4];
            if (p1Distance > p2Distance) {
                assertEquals(0, test.getCurrentPlayer());
            }
        }
    }

    @Test
    public void testGetGameStats() {
        assertNotNull(test.getGameStats()); //Tested with almost every other helper function
    }

    @Test
    public void testGetNumberOfHoles() {
        test.createCourse();
        assertEquals(1, test.getNumberOfHoles());
        assertEquals(1, test.getCourse().length);

        test.setNumberOfHoles(12);
        assertEquals(12, test.getNumberOfHoles());
        assertEquals(12, test.getCourse().length);

        test.setNumberOfHoles(10);
        assertEquals(10, test.getNumberOfHoles());
        assertEquals(10, test.getCourse().length);
    }

    @Test
    public void testSetNumberOfHoles() {
        assertEquals(1, test.getNumberOfHoles());

        test.setNumberOfHoles(5);
        assertEquals(5, test.getNumberOfHoles());
    }

    @Test
    public void testRandomHoleDistancer() {
        int high = 100;
        int low = 15;

        //Runs 100 attempts of random value assignment to ensure value is between 0 and 6
        for (int index = 0; index < 100; index++) {
            int value = test.randomHoleDistancer();
            assertTrue("Error, random is too high", high >= value);
            assertTrue("Error, random is too low", low <= value);
            //System.out.println("Test passed: " + value + " is within " + high + " and " + low);
        }
    }

    @Test
    public void testCreateCourse() {
        //Create Course just creates 1 (by default) or the set ammount of holes with random distances
        //Random distances are tested in testRandomHoleDistancer
        test.createCourse();
        assertEquals(1, test.getNumberOfHoles()); //Creates 1 hole by default
        assertEquals(1, test.getCourse().length); //Creates array of length 1 by default

        test.setNumberOfHoles(0);
        assertEquals(0, test.getNumberOfHoles()); //Works for 0
        assertEquals(0, test.getCourse().length);

        test.setNumberOfHoles(5);
        assertEquals(5, test.getNumberOfHoles());
        assertEquals(5, test.getCourse().length); //Works for an arbitrary number
    }

    @Test
    public void testGetNumberOfPlayers1() {
        assertEquals(0, test.getNumberOfPlayers()); //Tested more thoroughly in other tests

        test.setNumberOfPlayers(3);
        assertEquals(3, test.getNumberOfPlayers());
    }

    @Test
    public void testGetPlayer() {

        Game test = new Game();

        test.createPlayer("Sean");
        String[] playerNames = test.getPlayer(); //gets players and stores them into String array

        assertEquals("Sean", playerNames[0]);
        test.createPlayer("Freddy");
        assertEquals("Freddy", playerNames[1]);
    }

    @Test
    public void testCreateGameStats() {
        //Creates gameStats and sets distancesRemaining to default values
        test.createPlayer("");
        test.createPlayer("");

        assertEquals(1, test.getStrokes(0, 0));
        assertEquals(0, test.getStrokes(1, 0));

        try {
            test.getDistanceFromHole(0);
            fail("An exception should be thrown");
        } catch (Exception exception) {
        }

        test.createGameStats();

        assertEquals(0, test.getStrokes(0, 0));
        assertEquals(0, test.getStrokes(1, 0));
        assertTrue(test.getDistanceFromHole(0) > 0);

        test.setNumberOfHoles(2);
        test.createGameStats();

        assertTrue(test.getDistanceFromHole(0) > 0);
        assertTrue(test.getDistanceFromHole(1) > 0);
    }
//

    @Test
    public void testResetStats() {
        test.createPlayer("test");
        test.createGameStats();
        test.createCourse();
        test.addStroke(0, 0);
        assertEquals(1, test.getStrokes(0, 0));
        test.resetStats();
        assertEquals(0, test.getStrokes(0, 0));
    }

    @Test
    public void testAddStroke() {

        test.createPlayer("");
        test.createPlayer("");
        test.setNumberOfHoles(9);
        test.createCourse();
        test.createGameStats();

        for (int index = 0; index < 9; index++) {
            assertEquals(0, test.getStrokes(0, index)); //Defaults to 0 for first player
        }
        for (int index = 0; index < 9; index++) {
            assertEquals(0, test.getStrokes(1, index)); //Defaults to 0 for other player
        }
        test.addStroke(0, 0);
        test.addStroke(0, 1);
        test.addStroke(0, 2);
        test.addStroke(0, 3);
        test.addStroke(0, 4);
        test.addStroke(0, 5);
        test.addStroke(0, 6);
        test.addStroke(0, 7);
        test.addStroke(0, 8);

        for (int index = 0; index < 9; index++) {
            assertEquals(1, test.getStrokes(0, index)); //Mutates all holes for first player
        }
        for (int index = 0; index < 9; index++) {
            assertEquals(0, test.getStrokes(1, index)); //Doesn't mutate holes for other player
        }
        test.addStroke(0, 0);
        assertEquals(2, test.getStrokes(0, 0)); //Mutating past 1 works too
        assertEquals(0, test.getStrokes(1, 0)); //Mutating past 1 doesn't alter others

        test.addStroke(1, 0);
        assertEquals(1, test.getStrokes(1, 0)); //Mutating other players works too
        assertEquals(2, test.getStrokes(0, 0)); //Mutating other players doesn't affect first player

        test.addStroke(3, 0);
        assertEquals(0, test.getStrokes(3, 0)); //Doesn't add for non existing players

        test.addStroke(0, 10);
        assertEquals(0, test.getStrokes(0, 10)); //Doesn't add for non existing holes
    }

    /**
     * Testing resetPlayerCount(): Creating 2 players and checking that 2
     * players have been created. Then resetting the player count and checking
     * that the player count is 0.
     */
    @Test
    public void testResetPlayerCount() {
        test.createPlayer("test");
        test.createPlayer("test2");
        assertEquals(test.getNumberOfPlayers(), 2);
        test.resetPlayerCount();
        assertEquals(test.getNumberOfPlayers(), 0);
    }

    /**
     * Test resetPlayers() to see if the players get reset
     */
    @Test
    public void testResetPlayers() {
        assertEquals(0, test.getNumberOfPlayers());
        assertArrayEquals(new String[]{null, null, null, null}, test.getPlayer());

        test.createPlayer("t1");
        assertArrayEquals(new String[]{"t1", null, null, null}, test.getPlayer());
        assertEquals(1, test.getNumberOfPlayers());

        test.resetPlayers();
        assertEquals(0, test.getNumberOfPlayers());
        assertArrayEquals(new String[]{null, null, null, null}, test.getPlayer());

        test.createPlayer("t2");
        test.createPlayer("t3");
        test.createPlayer("t4");
        assertArrayEquals(new String[]{"t2", "t3", "t4", null}, test.getPlayer());
        assertEquals(3, test.getNumberOfPlayers());

        test.resetPlayers();
        assertArrayEquals(new String[]{null, null, null, null}, test.getPlayer());
        assertEquals(0, test.getNumberOfPlayers());
    }

    /**
     * Testing resetHoleIndex(): Setting amount of holes to four. Playing 2
     * holes before resetting them.
     */
    @Test
    public void testResetHoleIndex() {
        test.setNumberOfHoles(4);
        test.nextHole();
        test.nextHole();
        assertEquals(2, test.getHoleIndex());
        test.resetHoleIndex();
        assertEquals(0, 0);
    }

    @Test
    public void testGetCurrentPlayerDistance() {
        test.setNumberOfHoles(3);
        test.setNumberOfPlayers(3);
        test.createGameStats();

        int holeDistance = test.getHoleLength();
        int distance = test.getCurrentPlayerDistance(0, 0);

        assertEquals(distance, holeDistance);

        int val = distance;
        while (val > 0) {
            int currentVal = test.hitTheBall(1, 0, 0)[0];
            assertEquals(val - currentVal, test.getCurrentPlayerDistance(0, 0));
            val -= currentVal;
        }

        test.hitTheBall(5, 0, 0);
        assertEquals(0, test.getCurrentPlayerDistance(0, 0)); //No overshooting
    }

    /**
     * Test saveGameStats()
     */
    @Test
    public void testsaveGameStats() {
        test = new Game(true);

        test.saveGameStats();
        assertTrue(new File("savedGameStats.sav").exists());
    }

    /**
     * Test loadGameStats()
     */
    @Test
    public void testloadGameStats() {
        File f = new File("savedGameStats.sav");
        f.delete();

        test = new Game(true);
        test.setNumberOfHoles(5);
        test.createPlayer("Matt");
        test.createCourse();
        test.createGameStats();
        test.addStroke(0, 0);
        test.saveGameStats();

        test.resetPlayers();
        test.setNumberOfHoles(1);
        test.resetStats();
        test.createCourse();
        test.createGameStats();

        assertEquals(0, test.getStrokes(0, 0));
        assertEquals(0, test.getNumberOfPlayers());
        assertArrayEquals(new String[]{null, null, null, null}, test.getPlayer());
        assertEquals(1, test.getNumberOfHoles());

        test.loadGameStats();

        assertEquals(1, test.getStrokes(0, 0));
        assertEquals(1, test.getNumberOfPlayers());
        assertArrayEquals(new String[]{"Matt", null, null, null}, test.getPlayer());
        assertEquals(5, test.getNumberOfHoles());

    }
}
