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
		c1 = new Card(CardColor.red, CardRank.two, 1);
		c2 = new Card(CardColor.red, CardRank.one, 1);
		c3 = new Card(CardColor.black, CardRank.five, 2);

		cs1.addCard(c1);
		cs1.addCard(c2);
		cs1.addCard(c3);
	}

	@Test
	public void testAddCard() {
		assertTrue(cs1.getCard(0) == c1);
	}

	@Test
	public void testShuffle() {
		cs1.Shuffle();
		assertFalse(cs1.getCard(0) == c1 && cs1.getCard(1) == c2 && cs1.getCard(2) == c3);
	}

	@Test
	public void testSort() {
		cs1.Sort();
		assertTrue(cs1.getCard(0) == c2 && cs1.getCard(2) == c3);
	}

	@Test
	public void getScore() {
		assertTrue( cs1.getScore() == 20 );
	}

	@Test
	public void testFindWinningCard() {
		assertTrue(cs1.findWinningCard(CardColor.red) == c1);
	}

}
