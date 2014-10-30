package rookwithfriends.rook;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.rookwithfriends.game.Card;
import com.rookwithfriends.game.CardColor;
import com.rookwithfriends.game.CardRank;
import com.rookwithfriends.game.CardSet;
import com.rookwithfriends.game.Player;

public class PlayerTest {

	public CardSet cs1;
	public Card c1, c2, c3;
	public Player p;
	
	@Before
	public void setUp() throws Exception {
		cs1 = new CardSet();
		c1 = new Card(CardColor.red, CardRank.two, 1);
		c2 = new Card(CardColor.red, CardRank.one, 1);
		c3 = new Card(CardColor.black, CardRank.five, 2);

		cs1.addCard(c1);
		cs1.addCard(c2);
		cs1.addCard(c3);
		
		p=new Player();
		p.combineHand(cs1);
	}

	
	@Test
	public void testSelectCard() {
		assertTrue(p.selectCard(1)==c2);
	}

	@Test
	public void testCombineHand() {
		assertTrue(cs1.getCard(0)==p.selectCard(0) && cs1.getCard(1)==p.selectCard(1)&&cs1.getCard(2)==p.selectCard(2));
	}

	/*@Test
	public void testSetBid() {
		int currentBid=0;
		Player p2=new Player();
		Player winner=p2;
		currentBid=p.setBid(currentBid);
		if(currentBid>p2.getPlayerBid())
		{
			winner=p;
		}
		assertTrue(currentBid==5&&winner==p);
	}*/


}
