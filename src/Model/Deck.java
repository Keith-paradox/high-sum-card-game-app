package Model;
import java.util.*;

public class Deck {

	private ArrayList<Card> cards;

	public Deck() {
		cards = new ArrayList<Card>();
		String[] suits = { "Diamond", "Club","Heart","Spade",  };
		for (int i = 0; i < suits.length; i++) {
			String suit = suits[i];
			Card card = new Card(suit, "Ace", 1,1+i);
			cards.add(card);
			
			int c = 5;
			for (int n = 2; n <= 10; n++) {
				Card oCard = new Card(suit, "" + n, n,c+i);
				cards.add(oCard);
				c+=4;
			}

			Card jackCard = new Card(suit, "Jack", 10,41+i);
			cards.add(jackCard);

			Card queenCard = new Card(suit, "Queen", 10,45+i);
			cards.add(queenCard);

			Card kingCard = new Card(suit, "King", 10,49+i);
			cards.add(kingCard);
		}
	}
	
	public Card dealCard() {
		return cards.remove(0);
	}
	
	//add back one card
	public void appendCard(Card card) {
		cards.add(card);
	}
	//add back arraylist of cards
	public void appendCard(ArrayList<Card> cards) {
		for(Card card: cards) {
			this.cards.add(card);
		}
	}

	public void shuffle() {
		Random random = new Random();
		for(int i=0;i<10000;i++) {
			int indexA = random.nextInt(cards.size());
			int indexB = random.nextInt(cards.size());
			Card cardA = cards.get(indexA);
			Card cardB = cards.get(indexB);
			cards.set(indexA, cardB);
			cards.set(indexB, cardA);	
		}
	}

	//for internal use only
	private void showCards() {
		for (Card card : cards) {
			System.out.println(card);
		}
	}
	
	//for internal use only
	private void displayCards() {
		for (Card card : cards) {
			System.out.println(card.display());
		}
	}

	public static void main(String[] args) {
		Deck deck = new Deck();
		//deck.shuffle();
		/*Card card1 = deck.dealCard();
		Card card2 = deck.dealCard();
		Card card3 = deck.dealCard();
		deck.showCards();
		
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(card1);
		cards.add(card2);
		cards.add(card3);
		deck.appendCard(cards);
		System.out.println();*/
		deck.displayCards();
	}
}

//card rank
//D  C  H  S
//1   1  2  3  4
//2   5  6  7  8
//3   9 10 11 12
//4  13 14 15 16
//5  17 18 19 20
//6  21 22 23 24
//7  25 26 27 28
//8  29 30 31 32
//9  33 34 35 36 
//10 37 38 39 40
//J  41 42 43 44
//Q  45 46 47 48
//K  49 50 51 52
