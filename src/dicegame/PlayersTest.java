package dicegame;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayersTest {
	/**
	 * Testing null
	 */
	@Test
	public void testNull(){
		Players p = new Players();
		assert (p) != null;
	}
	
	/**
	 * Testing createPlayer adding elements to end of array.
	 */
	@Test
	public void createPlayerTest(){
		Players p = new Players();
		p.createPlayer("Test");
		assertEquals(p.currentPlayer.length, 4);
		assertEquals(p.currentPlayer[0].name, "Test");
		Players p2 = new Players();
		p.createPlayer("Test2");
		assertEquals(p.currentPlayer[1].name, "Test2");
		Players p3 = new Players();
		p.createPlayer("Test3");
		assertEquals(p.currentPlayer[2].name, "Test3");
		Players p4 = new Players();
		p.createPlayer("Test4");
		assertEquals(p.currentPlayer[3].name, "Test4");
	}
	
	/**
	 * testing getNames not 
	 */
	@Test
	public void getNameTest(){
		Players p = new Players();
		p.createPlayer("Test");
		assertEquals(p.currentPlayer.length, 4);
		Players p2 = new Players();
		p.createPlayer("Test2");
		Players p3 = new Players();
		p.createPlayer("Test3");
		Players p4 = new Players();
		p.createPlayer("Test4");
	}
	
	@Test
	public void setNameTest(){
		Players p = new Players();
		}
	
	@Test
	public void getScoreTest(){
		Players p = new Players();
		assertEquals(p.getScore(), 0);
	}
	
	@Test
	public void aScoreTest(){
		Players p = new Players();
		p.addScore(5);
		assertEquals(p.getScore(), 5);
	}

}
