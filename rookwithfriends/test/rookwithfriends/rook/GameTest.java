package rookwithfriends.rook;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

import com.rookwithfriends.game.*;

public class GameTest {
	Game game;
	
	@Before
	public void setUp() throws Exception {
		game = new Game(0, 1, 2, 3);
		Scanner read = new Scanner(System.in);
		// System.out.println("Starting Game\n");
		// Step 1 -- Shuffle Cards
		// create Deck that holds all cards
		while (game.getTotalTeam1Score() < 500 && game.getTotalTeam2Score() < 500) {
			game.createDeck(); // Moved the code that instantiates allDeck to
								// separate
								// method

			// instantiate hand with all cards
			game.getAllDeck().Shuffle();

			// Step 2 -- Deal & Sort Cards
			game.dealHands(); // Moved the code that deals hands into separate
								// method

			// Step 3 -- Print all the hands out
			System.out.println("Here are the hands\n");
			game.printHands(); // Moved the print hands into a different method

			game.setNumPasses(0);
			Player currentPlayer;
			for (int i = 0; !game.isBettingDone(); i = (i + 1) % 4) {
				currentPlayer = game.getPlayer(i);
				game.setBid(currentPlayer);
			}

			// Step 4 -- Find winner of bid - Pass control unto them?
			// Player bidWinner is a public variable -- Create Gameboard, add
			// needed
			// methods.

			System.out.println("The winner of the bid was: "
					+ game.bidWinner.getPlayerID());
			game.bidWinner.combineHand(game.kitty);
			game.bidWinner.getPlayerHand().Sort();
			System.out.println("\nThe winners hand is: ");
			game.bidWinner.printHand();
			game.setTrump(game.bidWinner.setTrump());
			System.out.println("The trump is " + game.getTrump());
			game.kitty.clear();
			for (int i = 0; i < 5; i++) {
				System.out.println("Choose a card to remove");
				Card temp = game.bidWinner.chooseCard();
				game.kitty.add(temp);
				game.bidWinner.getPlayerHand().remove(
						game.bidWinner.getPlayerHand().indexOf(temp));
				game.bidWinner.printHand();
			}
			game.currentPlayer = game.bidWinner;
			for (int i = 0; i < 10; i++) {
				game.centerDeck.clear();
				for(int tea = 0; tea<4;tea++)
				{
					game.playRound(game.currentPlayer,game.currentPlayer.chooseCard());
				}
				game.trickColorSet=false;
				Card winningCard = game.centerDeck.findWinningCard(game
						.getTrump());
				System.out.println("Winning card is: " + winningCard);
				for (Player p : game.players) {
					if (winningCard.getId() == p.getPlayerID()) {
						p.getCardsWon().addAll(game.centerDeck);
						game.setCurrentPlayer(p);
						game.centerDeck.clear();
						break;
					}
				}
				System.out.println("Winning player was:"+ game.currentPlayer.getPlayerID());
				game.scoreGame();
				System.out.println("Team 1 Score: " + game.getTeam1Score()+ " Team 2 Score: " + game.getTeam2Score());
			}
			game.currentPlayer.getCardsWon().addAll(game.kitty);
			game.scoreGame();
			System.out.println("Round Team 1 Score: " + game.getTeam1Score() + " Round Team 2 Score: " + game.getTeam2Score());
			game.setTotalTeam1Score(game.getTotalTeam1Score()+game.getTeam1Score());
			game.setTotalTeam2Score(game.getTotalTeam2Score()+game.getTeam2Score());
			System.out.println("Overall Team 1 Score: "+game.getTotalTeam1Score()+" Overall Team 2 Score: "+game.getTotalTeam2Score());
		}
		if(game.getTotalTeam1Score()>game.getTotalTeam2Score())
		{
			System.out.println("Team 1 Wins");
		}
		else if(game.getTotalTeam1Score()==game.getTotalTeam2Score())
		{
			System.out.println("It's a Tie!");
		}
		else
		{
			System.out.println("Team 2 Wins");
		}
	}
	

	@Test
	public void testDeckCreation() {
		/*CardSet allDeck = new CardSet();

		for (CardColor color : CardColor.values()) {
			for (CardRank rank : CardRank.values()) {
				if (rank != CardRank.rook) {
					allDeck.add(new Card(color, rank, 0));
				}
			}
		}

		allDeck.add(new Card(CardColor.black, CardRank.rook, 0));

		ArrayList<Card> actualSet = game.getAllDeck();
		ArrayList<Card> kitty = game.kitty;

		for (Card card : allDeck) {
			// Check if card is in the deck
			if (!(actualSet.contains(card) || kitty.contains(card))) {
				boolean doesPlayerHaveCard = false;

				// check if the card has been delt
				for (Player player : game.players) {
					if (player.getPlayerHand().contains(card)) {
						doesPlayerHaveCard = true;
						break;
					}
				}

				if (!doesPlayerHaveCard) {
					fail("Missing Card in card set");
				}
			}
		}

		assertTrue(actualSet.size() == 12);*/
	}

	/*@Test
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
	}*/
}
