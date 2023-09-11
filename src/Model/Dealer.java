package Model;
import java.util.*;

import GUIExample.GUIAnimation;

public class Dealer extends Player{

	private Deck deck;
	
	public Dealer() {
		super("Dealer","",0);
		deck = new Deck();
	}
	
	public void shuffleCards() {
		GUIAnimation.run();
		System.out.println("Dealer shuffle deck");
		deck.shuffle();
	}
	
	public void dealCardTo(Player player) {
		Card card = deck.dealCard();//take a card out from deck
		player.addCard(card);//pass the card into player
	}
	
	public void addCardsBackToDeck(ArrayList<Card> cards) {
		deck.appendCard(cards);
	}
	
	//return 1 if card1 rank higher, else return 2
	public int determineWhichCardRankHigher(Card card1, Card card2) {
		if(card1.getRank()>card2.getRank()) {
			return 1;
		}else {
			return 2;
		}
	}
	
	
	
}
