package dicegame;

import static org.junit.Assert.*;

import org.junit.Test;

public class LogicTesting {

	@Test
	public void testGame() {
		Game test = new Game();
		assertNotNull(test);
		assertEquals(1, test.getHoleIndex());
	}

	@Test
	public void testPlayerShotSum() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCurrentPlayerDistance() {
		Game test = new Game();
		
		test.setNumberOfHoles(3);
		test.setNumberOfPlayers(3);
		
		test.createGameStats();
		
		int holeDistance[] = test.getCourse();
		
		
		
		int distance = test.getCurrentPlayerDistance(0,0);
		
		
		assertEquals(distance, holeDistance[0]);
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
