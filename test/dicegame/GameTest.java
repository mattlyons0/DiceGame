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
     * Test of setNumberOfPlayers method, of class Game
     * Test method to show that number of players created
     * matches number of players in game
     */
    @Test
    public void testSetNumberOfPlayers(int numberOfPlayers)
    {
    	test.setNumberOfPlayers(3);
    	int numberOfPlayers = test.getNumberOfPlayers();
    	assertEquals(numberOfPlayers, 3);
    }

    /**
     * Test of getNumberOfPlayers method, of class Game
     * Test method to show that set number of players value
     * can be extracted from game
     */
    @Test
    public void testGetNumberOfPlayers()
    {
    	test.setNumberOfPlayers(3);
    	int numberOfPlayers = test.getNumberOfPlayers();
    	assertEquals(numberOfPlayers, 3);
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
public void testGetPlayerOneWins()
{
	Game test = new Game();
	
			//inputs values into 2D array
										//EXAMPLE ARRAY:
	                                    //______________
			test.gameStats[0][0] = 3;	//3	4 1  P1 = 8  -- P1 should win
			test.gameStats[1][0] = 3;	//3	2 6  P2 = 11
			test.gameStats[2][0] = 5;	//5	6 2  P3 = 13
			test.gameStats[3][0] = 4;	//4	7 1  P4 = 12
			test.gameStats[0][1] = 4;					
			test.gameStats[1][1] = 2;
			test.gameStats[2][1] = 6;
			test.gameStats[3][1] = 7;
			test.gameStats[0][2] = 1;
			test.gameStats[1][2] = 6;
			test.gameStats[2][2] = 2;
			test.gameStats[3][2] = 1;
	
			//strokeSum() totals all of each players' stroke counts
			int totalOne = test.strokeSum(0);
			int totalTwo = test.strokeSum(1);
			int totalThree = test.strokeSum(2);
			int totalFour = test.strokeSum(3);
					
			test.countWin();//compares the strokeSum() totals
							//increments player[number]Wins by 1 if they have lowest score
			
			int oneWins = playerOneWins;
			int twoWins = playerTwoWins;
			int threeWins = playerThreeWins;
			int fourWins = playerFourWins;
			
			assertEquals(1, playerOneWins); //winner increments total by 1; P1 should have 1
			assertEquals(0, playerTwoWins); //losers do not increment; P2 - P4 should have 0
			assertEquals(0, playerThreeWins);
			assertEquals(0, playerFourWins);
}

@Test
public void testGetPlayerTwoWins() {
	Game test = new Game();
	
	//inputs values into 2D array
								//EXAMPLE ARRAY:
                                //______________
	test.gameStats[0][0] = 9;	//9	4 1  P1 = 14 
	test.gameStats[1][0] = 3;	//3	2 6  P2 = 11
	test.gameStats[2][0] = 5;	//5	6 2  P3 = 13
	test.gameStats[3][0] = 4;	//4	7 1  P4 = 12
	test.gameStats[0][1] = 4;					
	test.gameStats[1][1] = 2;
	test.gameStats[2][1] = 6;
	test.gameStats[3][1] = 7;
	test.gameStats[0][2] = 1;
	test.gameStats[1][2] = 6;
	test.gameStats[2][2] = 2;
	test.gameStats[3][2] = 1;

	//strokeSum() totals all of each players' stroke counts
	int totalOne = test.strokeSum(0);
	int totalTwo = test.strokeSum(1);
	int totalThree = test.strokeSum(2);
	int totalFour = test.strokeSum(3);
			
	test.countWin();//compares the strokeSum() totals
					//increments player[number]Wins by 1 if they have lowest score
	
	int oneWins = playerOneWins;
	int twoWins = playerTwoWins;
	int threeWins = playerThreeWins;
	int fourWins = playerFourWins;
	
	assertEquals(0, playerOneWins); //winner increments total by 1; P2 should have 1
	assertEquals(1, playerTwoWins); 
	assertEquals(0, playerThreeWins);
	assertEquals(0, playerFourWins);
}

@Test
public void testGetPlayerThreeWins() {
	Game test = new Game();
	
	//inputs values into 2D array
								//EXAMPLE ARRAY:
                                //______________
	test.gameStats[0][0] = 7;	//7	4 1  P1 = 12
	test.gameStats[1][0] = 3;	//3	2 6  P2 = 11
	test.gameStats[2][0] = 1;	//1	6 2  P3 = 9
	test.gameStats[3][0] = 4;	//4	7 1  P4 = 12
	test.gameStats[0][1] = 4;					
	test.gameStats[1][1] = 2;
	test.gameStats[2][1] = 6;
	test.gameStats[3][1] = 7;
	test.gameStats[0][2] = 1;
	test.gameStats[1][2] = 6;
	test.gameStats[2][2] = 2;
	test.gameStats[3][2] = 1;

	//strokeSum() totals all of each players' stroke counts
	int totalOne = test.strokeSum(0);
	int totalTwo = test.strokeSum(1);
	int totalThree = test.strokeSum(2);
	int totalFour = test.strokeSum(3);
			
	test.countWin();//compares the strokeSum() totals
					//increments player[number]Wins by 1 if they have lowest score
	
	int oneWins = playerOneWins;
	int twoWins = playerTwoWins;
	int threeWins = playerThreeWins;
	int fourWins = playerFourWins;
	
	assertEquals(0, playerOneWins); //winner increments total by 1
	assertEquals(0, playerTwoWins); 
	assertEquals(1, playerThreeWins);
	assertEquals(0, playerFourWins);
}

@Test
public void testGetPlayerFourWins() {
	Game test = new Game();
	
	//inputs values into 2D array
								//EXAMPLE ARRAY:
                                //______________
	test.gameStats[0][0] = 3;	//3	4 1  P1 = 8 
	test.gameStats[1][0] = 3;	//3	2 6  P2 = 11
	test.gameStats[2][0] = 5;	//5	6 2  P3 = 13
	test.gameStats[3][0] = 4;	//4	1 1  P4 = 6
	test.gameStats[0][1] = 4;					
	test.gameStats[1][1] = 2;
	test.gameStats[2][1] = 6;
	test.gameStats[3][1] = 1;
	test.gameStats[0][2] = 1;
	test.gameStats[1][2] = 6;
	test.gameStats[2][2] = 2;
	test.gameStats[3][2] = 1;

	//strokeSum() totals all of each players' stroke counts
	int totalOne = test.strokeSum(0);
	int totalTwo = test.strokeSum(1);
	int totalThree = test.strokeSum(2);
	int totalFour = test.strokeSum(3);
			
	test.countWin();//compares the strokeSum() totals
					//increments player[number]Wins by 1 if they have lowest score
	
	int oneWins = playerOneWins;
	int twoWins = playerTwoWins;
	int threeWins = playerThreeWins;
	int fourWins = playerFourWins;
	
	assertEquals(0, playerOneWins); //winner increments total by 1
	assertEquals(0, playerTwoWins); //losers do not increment
	assertEquals(0, playerThreeWins);
	assertEquals(1, playerFourWins);
}

@Test
public void testStrokeSum() {
	
	Game test = new Game();
	
	//inputs values into 2D array
								//EXAMPLE ARRAY:
                                //______________
	test.gameStats[0][0] = 3;	//3	4 1  P1 = 8 
	test.gameStats[1][0] = 3;	//3	2 6  P2 = 11
	test.gameStats[2][0] = 5;	//5	6 2  P3 = 13
	test.gameStats[3][0] = 4;	//4	1 1  P4 = 6
	test.gameStats[0][1] = 4;					
	test.gameStats[1][1] = 2;
	test.gameStats[2][1] = 6;
	test.gameStats[3][1] = 1;
	test.gameStats[0][2] = 1;
	test.gameStats[1][2] = 6;
	test.gameStats[2][2] = 2;
	test.gameStats[3][2] = 1;

	//strokeSum() totals all of each players' stroke counts
	int totalOne = test.strokeSum(0);
	int totalTwo = test.strokeSum(1);
	int totalThree = test.strokeSum(2);
	int totalFour = test.strokeSum(3);
	
	assertEquals(8, totalOne);
	assertEquals(11, totalTwo);
	assertEquals(13, totalThree);
	assertEquals(6, totalFour);
}

@Test
public void testCountWin() {
Game test = new Game();
	
	//inputs values into 2D array
								//EXAMPLE ARRAY:
                                //______________
	test.gameStats[0][0] = 3;	//3	4 1  P1 = 8 
	test.gameStats[1][0] = 3;	//3	2 6  P2 = 11
	test.gameStats[2][0] = 5;	//5	6 2  P3 = 13
	test.gameStats[3][0] = 4;	//4	1 1  P4 = 6
	test.gameStats[0][1] = 4;					
	test.gameStats[1][1] = 2;
	test.gameStats[2][1] = 6;
	test.gameStats[3][1] = 1;
	test.gameStats[0][2] = 1;
	test.gameStats[1][2] = 6;
	test.gameStats[2][2] = 2;
	test.gameStats[3][2] = 1;

	//strokeSum() totals all of each players' stroke counts
	int totalOne = test.strokeSum(0);
	int totalTwo = test.strokeSum(1);
	int totalThree = test.strokeSum(2);
	int totalFour = test.strokeSum(3);
			
	test.countWin();//compares the strokeSum() totals
					//increments player[number]Wins by 1 if they have lowest score
	
	int oneWins = playerOneWins; //player[Number]Wins are global variables
	int twoWins = playerTwoWins; //that store the increment values
	int threeWins = playerThreeWins;
	int fourWins = playerFourWins;
	
	//if values added correctly, then player 1 should have lowest score and win 1 increment
	assertEquals(1, playerOneWins);
}

@Test
public void testNextHole() {
	
	Game test = new Game();
	
	int currentHole = holeIndex;
	
	assertEquals(0, holeIndex); //tests to show hole is at 0
	test.nextHole(); //shifts hole up 1 increment holeIndex should be 1
	assertEquals(1, holeIndex);
	test.nextHole();
	assertEquals(2, holeIndex);
	holeIndex = 5;
	test.nextHole();
	assertEquals(6, holeIndex);
	
	
}

@Test
public void testNextPlayer() {	//I dont believe that we use this method at all
	fail("Not yet implemented");//might need to delete
}

@Test
public void testGetHoleIndex() {
	
	Game test = new Game();
	
	test.createGameStats();
	test.setNumberOfHoles(5);
	test.createCourse();
	
	assertEquals(0, getHoleIndex());//current hole should be at index 0
	
			//player 1 hits
			test.playerShotSum();
			gameStats[0][0] = playerOneDistance;
			
			//player 2 hits
			test.playerShotSum();
			gameStats[1][0] = playerTwoDistance;

			//player 3 hits
			test.playerShotSum();
			gameStats[2][0] = playerThreeDistance;

			//player 4 hits
			test.playerShotSum();
			gameStats[3][0] = playerFourDistance;

			//Player 1 hits again and adds to the current distance total
			test.playerShotSum();
			gameStats[0][0] = playerOneDistance;
			
			//Should be on second hole now : Index 1
			assertEquals(1, test.getHoleIndex());

}

@Test
public void testCurrentPlayer() { //Appears at though method was deleted from Game.java
	fail("Not yet implemented");
}

@Test
public void testGetCurrentPlayer() {
	
	Game test = new Game();
	
	test.createGameStats();
	test.setNumberOfHoles(5);
	test.createCourse();
	
	assertEquals(0, getCurrentPlayer());//current player should be at index 0
	
	//player 1 hits
	test.playerShotSum();
	gameStats[0][0] = playerOneDistance;
	
	//player 2 hits
	test.playerShotSum();
	gameStats[1][0] = playerTwoDistance;

	//player 3 hits
	test.playerShotSum();
	gameStats[2][0] = playerThreeDistance;

	//player 4 hits
	test.playerShotSum();
	gameStats[3][0] = playerFourDistance;

	//Player 1 hits again and adds to the current distance total
	test.playerShotSum();
	gameStats[0][0] = playerOneDistance;
	
	//Player 2 (index 1) should be up to play
	assertEquals(1, getCurrentPlayer());
	
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
	
	Game test = new Game();
	
	test.setNumberOfHoles(12);
	
	assertEquals(12, test.getNumberOfHoles());
	holeCount = 0; //this might not be needed or might not work
				   //using this to basically reset current number of holes to 0
	test.setNumberOfHoles(10);
	assertEquals(10, test.getNumberOfHoles());
}

@Test
public void testSetNumberOfHoles() {
	
	Game test = new Game();
	
	test.setNumberOfHoles(5);
	assertEquals(5, getNumberOfHoles);
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
	
	Game test = new Game();
	
	playerCount = 0; //global variable value
	
	assertEquals(0, test.getNumberOfPlayers());
	test.setNumberOfPlayers(3);
	assertEquals(3, test.getNumberOfPlayers());
}

@Test
public void testGetNumberOfPlayers() {
	
	Game test = new Game();
	
	playerCount = 0; //global variable value
	
	assertEquals(0, test.getNumberOfPlayers());
	test.setNumberOfPlayers(3);
	assertEquals(3, test.getNumberOfPlayers());
}

@Test
public void testCreatePlayer() {
	
	Game test = new Game();
	
	test.createPlayer("Sean"); //creates a player named Sean
	String[] playerNames = test.getPlayer();
	
	assertEquals("Sean", playerNames[0]);
	test.createPlayer("Freddy"); //creates a player named Freddy
	assertEquals("Freddy", playerNames[1]);
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
