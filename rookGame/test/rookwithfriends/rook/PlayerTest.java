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
		c1 = new Card(CardColor.red, CardRank.seven, 1);
		c2 = new Card(CardColor.red, CardRank.six, 1);
		c3 = new Card(CardColor.black, CardRank.five, 2);

		cs1.add(c1);
		cs1.add(c2);
		cs1.add(c3);
		
		p=new Player();
		p.combineHand(cs1);
	}

	
	@Test
	public void testSelectCard() {
		assertTrue(p.selectCard(1)==c2);
	}

	@Test
	public void testCombineHand() {
		assertTrue(cs1.get(0)==p.selectCard(0) && cs1.get(1)==p.selectCard(1)&&cs1.get(2)==p.selectCard(2));
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
		assertTrue(currentBid==5 && winner==p);
	}*/
	
	/*@Test
	public void testChooseCard() {
		Card card = new Card(CardColor.red, CardRank.one, 0);
		Player p2=new Player();
		p2.getPlayerHand().addCard(card);
		Card newcard = p2.chooseCard();
		System.out.println(newcard);
		assertTrue(card.equals(newcard));
	}*/
	
	@Test
	public void testSetTrump() {
		Player p2=new Player();
		CardColor newcard = p2.setTrump();
		System.out.println(newcard);
	}

	@Test
	public void toJsonTest(){
		String json = p.toJSON();
		String expected = "{\r\n   \"hand\" : [ {\r\n      \"color\" : \"red\",\r\n      \"id\" : 1,\r\n      \"rank\" : \"seven\"\r\n   }, {\r\n      \"color\" : \"red\",\r\n      \"id\" : 1,\r\n      \"rank\" : \"six\"\r\n   }, {\r\n      \"color\" : \"black\",\r\n      \"id\" : 2,\r\n      \"rank\" : \"five\"\r\n   } ],\r\n   \"cardsWon\" : [ ],\r\n   \"handID\" : 0,\r\n   \"hasPassed\" : false,\r\n   \"playerBid\" : 0,\r\n   \"playerID\" : 0\r\n}";
		
		assertTrue(json.equals(expected));
	}
}
