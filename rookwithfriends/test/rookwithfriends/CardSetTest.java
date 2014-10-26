package rookwithfriends;

import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
public class CardSetTest {

	CardSet cs1;
	Card c1, c2, c3;
	
	@Before public void setUp()
	{
		cs1=new CardSet();
		c1=new Card(red,two,1);
		c2=new Card(red,one,1);
		c3=new Card(black,five,2);
	}
	
	@Test
	public void testAddCard() {
		cs1.addCard(c1);
		cs1.addCard(c2);
		cs1.addCard(c3);
		Assert.assertEquals(true,cs1.getCard(0)==c1);
	}

}