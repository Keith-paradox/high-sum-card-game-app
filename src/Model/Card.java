package Model;
import javax.swing.*;

public class Card extends ImageIcon{

	private String suit;
	private String name;
	private int value;
	//used for card ranking - see below 
	//to determine which card has higher power to determine who can call.
	private int rank;
	
	public Card(String suit, String name, int value,int rank) {
		super("images/"+suit+name+".png");
		this.suit = suit;
		this.name = name;
		this.value = value;
		this.rank = rank;
	}

	public String getSuit() {
		return this.suit;
	}

	public String getName() {
		return this.name;
	}

	public int getValue() {
		return this.value;
	}
	
	public int getRank() {
		return this.rank;
	}
	
	
	public String toString() {
		return "<"+this.suit+" "+this.name+">";
	}
	
	public String display() {
		return "<"+this.suit+" "+this.name+" "+this.rank+">";
	}
}
//card rank
//    D  C  H  S
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
