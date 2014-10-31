package rookwithfriends.rook;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.rookwithfriends.game.*;

public class GameTest {
	Game game;

	@Before
	public void setUp() throws Exception {
		game = new Game(0,1,2,3);
		game.startGame();
	}
	
	@Test
	public void testDeckCreation() {
		CardSet allDeck = new CardSet();
		
		for (CardColor color : CardColor.values()) {
			for (CardRank rank : CardRank.values()) {
				if (rank != CardRank.rook) {
					allDeck.addCard(new Card(color, rank, 0));
				}
			}
		}
	
		allDeck.addCard(new Card(CardColor.black, CardRank.rook, 0));
		
		ArrayList<Card> actualSet = game.getAllDeck().getArrayList();
		for(Card card : allDeck.getArrayList()){
			if(!actualSet.contains(card)){
				fail("Missing Card in card set");
			}
		}
	}

	@Test
	public void testGame() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetGameBid() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetGameBid() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTrump() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetTrump() {
		fail("Not yet implemented");
	}
}
