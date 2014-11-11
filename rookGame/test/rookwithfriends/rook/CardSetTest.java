package rookwithfriends.rook;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.rookwithfriends.game.*;

public class CardSetTest {

	/**
	 * Fixture initialization (common initialization for all tests).
	 **/
	public CardSet cs1;
	public Card c1, c2, c3;

	@Before
	public void setUp() {
		
		cs1 = new CardSet();
		c1 = new Card(CardColor.red, CardRank.six, 1);
		c2 = new Card(CardColor.red, CardRank.seven, 1);
		c3 = new Card(CardColor.black, CardRank.five, 2);

		cs1.add(c1);
		cs1.add(c2);
		cs1.add(c3);
	}

	@Test
	public void testAddCard() {
		assertTrue(cs1.get(0) == c1);
	}

	@Test
	public void testShuffle() {
		cs1.Shuffle();
		assertFalse(cs1.get(0) == c1 && cs1.get(1) == c2 && cs1.get(2) == c3);
	}

	/*
	@Test
	public void testSort() {
		cs1.Sort();
		assertTrue(cs1.get(0) == c2 && cs1.get(2) == c3);
	}

	@Test
	public void getScore() {
		assertTrue( cs1.getScore() == 20 );
	}

	@Test
	public void testFindWinningCard() {
		assertTrue(cs1.findWinningCard(CardColor.red) == c1);
	}
	*/
	
	@Test
	public void toJsonTest(){
		String json = cs1.toJSON();
		String expected = "[ {\r\n   \"color\" : \"red\",\r\n   \"id\" : 1,\r\n   \"rank\" : \"six\"\r\n}, {\r\n   \"color\" : \"red\",\r\n   \"id\" : 1,\r\n   \"rank\" : \"seven\"\r\n}, {\r\n   \"color\" : \"black\",\r\n   \"id\" : 2,\r\n   \"rank\" : \"five\"\r\n} ]";
		
		assertTrue(json.equals(expected));
	}
}
