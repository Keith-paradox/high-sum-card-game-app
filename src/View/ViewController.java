package View;

import GUIExample.GameTableFrame;
import GUIExample.GameTablePanel;
import Helper.Keyboard;
import Model.*;

import java.awt.Color;

import javax.swing.*;


//all input and output should be done view ViewController
//so that it is easier to implement GUI later
public class ViewController  {
	private GameTableFrame gameFrame;
	private Dealer dealer;
	private Player player;
	private GameTablePanel gamePanel;
	private ImageIcon originalimage = new ImageIcon("images/stickman.png");
	java.awt.Image imageBefore = originalimage.getImage();
	java.awt.Image resizeImage = imageBefore.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
	private ImageIcon image = new ImageIcon(resizeImage);
	
	public ViewController() {
		Player player = new Player("tester1","",10000);
		Dealer dealer = new Dealer();
		gameFrame = new GameTableFrame(dealer, player);
		gamePanel = new GameTablePanel(dealer, player);
	}
	
	public void displayExitGame() {
		System.out.println("Thank you for playing HighSum game");
		gameFrame.dispose();
		JOptionPane.showMessageDialog(null, "Thank you for playing HighSum game", "Program Ended", JOptionPane.PLAIN_MESSAGE, image);
	}
	
	public void emptyDealertotal() {
		gameFrame.getGamePanel().dealerTotal.setText("");
	}
	
	public void displayBetOntable(int bet) {
		System.out.println("Bet on table : "+bet);
		gameFrame.getGamePanel().betsOnTable.setText("Bet on table : "+bet);
	}
	
	
	public void displayPlayerWin(Player player) {
		System.out.println(player.getLoginName()+" Wins!");
		JOptionPane.showMessageDialog(null, player.getLoginName()+" Wins!", "Player Wins", JOptionPane.PLAIN_MESSAGE, image);
	}
	
	public void displayDealerWin() {
		System.out.println("Dealer Wins!");
		JOptionPane.showMessageDialog(null, "Dealer Wins!", "Dealer Wins", JOptionPane.PLAIN_MESSAGE, image);

	}
	
	public void displayTie() {
		System.out.println("It is a tie!.");
		JOptionPane.showMessageDialog(null, "It is a tie!.", "Tie", JOptionPane.PLAIN_MESSAGE, image);

	}
	
	public void displayPlayerQuit() {
		JOptionPane.showMessageDialog(null, "You have quit the current game.", "Game Quit", JOptionPane.PLAIN_MESSAGE, image);
		System.out.println("You have quit the current game.");
	}
	
	public void displayPlayerCardsOnHand(Player player) {
		
		System.out.println(player.getLoginName());

		if(player instanceof Dealer) {
			gameFrame.getGamePanel().dealerName.setText(player.getLoginName());
			for(int i=0;i<player.getCardsOnHand().size();i++) {
				if(i==0) {
					System.out.print("<HIDDEN CARD> ");
					gameFrame.getGamePanel().dealerLabel1.setIcon(new ImageIcon("images/back.png"));
				}else {
					System.out.print(player.getCardsOnHand().get(i).toString()+" ");
					switch(i) {
					case 1 -> gameFrame.getGamePanel().dealerLabel2.setIcon(new ImageIcon(player.getCardsOnHand().get(i).getImage()));
					case 2 -> gameFrame.getGamePanel().dealerLabel3.setIcon(new ImageIcon(player.getCardsOnHand().get(i).getImage()));
					case 3 -> gameFrame.getGamePanel().dealerLabel4.setIcon(new ImageIcon(player.getCardsOnHand().get(i).getImage()));
					case 4 -> gameFrame.getGamePanel().dealerLabel5.setIcon(new ImageIcon(player.getCardsOnHand().get(i).getImage()));
					}
				}
			}
		}else {
			gameFrame.getGamePanel().playerName.setText(player.getLoginName());
			for(int i = 0; i<player.getCardsOnHand().size();i++){
				switch(i) {
				case 0 -> gameFrame.getGamePanel().playerLabel1.setIcon(new ImageIcon(player.getCardsOnHand().get(i).getImage()));
				case 1 -> gameFrame.getGamePanel().playerLabel2.setIcon(new ImageIcon(player.getCardsOnHand().get(i).getImage()));
				case 2 -> gameFrame.getGamePanel().playerLabel3.setIcon(new ImageIcon(player.getCardsOnHand().get(i).getImage()));
				case 3 -> gameFrame.getGamePanel().playerLabel4.setIcon(new ImageIcon(player.getCardsOnHand().get(i).getImage()));
				case 4 -> gameFrame.getGamePanel().playerLabel5.setIcon(new ImageIcon(player.getCardsOnHand().get(i).getImage()));
				}
				
			}
		}
		System.out.println();
		
	}
	
	public void revealDealerFirstCard(Player player) {
		gameFrame.getGamePanel().dealerLabel1.setIcon(new ImageIcon(player.getCardsOnHand().get(0).getImage()));
	}
	
	public void emptyCards() {
	gameFrame.getGamePanel().playerLabel1.setIcon(new ImageIcon(""));
	gameFrame.getGamePanel().playerLabel2.setIcon(new ImageIcon(""));
	gameFrame.getGamePanel().playerLabel3.setIcon(new ImageIcon(""));
	gameFrame.getGamePanel().playerLabel4.setIcon(new ImageIcon(""));
	gameFrame.getGamePanel().playerLabel5.setIcon(new ImageIcon(""));
	gameFrame.getGamePanel().dealerLabel1.setIcon(new ImageIcon(""));
	gameFrame.getGamePanel().dealerLabel2.setIcon(new ImageIcon(""));
	gameFrame.getGamePanel().dealerLabel3.setIcon(new ImageIcon(""));
	gameFrame.getGamePanel().dealerLabel4.setIcon(new ImageIcon(""));
	gameFrame.getGamePanel().dealerLabel5.setIcon(new ImageIcon(""));
	}
	
	public void displayBlankLine() {
		System.out.println();
	}
	
	public void displayPlayerTotalCardValue(Player player) {
		System.out.println("Value:"+player.getTotalCardsValue());
		gameFrame.getGamePanel().playerTotal.setText("Value:"+player.getTotalCardsValue());
	}
	
	public void displayDealerTotalCardValue(Dealer dealer) {
		System.out.println("Value:"+dealer.getTotalCardsValue());
		gameFrame.getGamePanel().dealerTotal.setText("Value:"+dealer.getTotalCardsValue());
	}
	
	public void displayDealerDealCardsAndGameRound(int round) {
		System.out.println("Dealer dealing cards - ROUND "+round);
		gameFrame.getGamePanel().round.setText("ROUND "+round);
	}
	
	public void displayGameStart() {
		System.out.println("Game starts - Dealer shuffle deck");
		JOptionPane.showMessageDialog(null, "Game starts", "Game Starts", JOptionPane.PLAIN_MESSAGE, image);
		
	}
	
	
	public void displayPlayerNameAndChips(Player player) {
		System.out.println(player.getLoginName()+", You have "+player.getChips()+" chips");
		gameFrame.getGamePanel().playerChips.setText(player.getLoginName()+", You have "+player.getChips()+" chips.");
	}
	
	public void displayPlayerNameAndLeftOverChips(Player player) {
		System.out.println(player.getLoginName()+", You are left with "+player.getChips()+" chips");
		gameFrame.getGamePanel().playerChips.setText(player.getLoginName()+", You have "+player.getChips()+" chips.");
	}
	
	public void displayGameTitle() {
		gameFrame.getGamePanel().labelTitle.setText("HighSum Game");
	}
	
	public void displaySingleLine() {
		for(int i=0;i<30;i++) {
			System.out.print("-");
		}
		System.out.println();
	}
	
	public void displayDoubleLine() {
		for(int i=0;i<30;i++) {
			System.out.print("=");
		}
		System.out.println();
	}
	
	
	public char getPlayerCallOrQuit() {
		char r = 'q';
		
		String[] options = {"Call","Quit"};
		int callOrQuit = JOptionPane.showOptionDialog(null, "Do you want to Call or Quit?","Player Call", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, image, options, 0);

		
		if(callOrQuit == JOptionPane.NO_OPTION) {
			r = 'q';
		}else if (callOrQuit == JOptionPane.YES_OPTION) {
			r = 'c';
		}
		
		
		return r;
	}
	
	public char getPlayerFollowOrNot(Player player, int dealerBet) {
		char r = 'n';
		String[] options = {"Yes", "No"};
		
		int followOrNot = JOptionPane.showOptionDialog(null, "Dealer call, state bet: 10\nDo you want to follow?", "Dealer Call", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, image, options, 0);
//		int followOrNot = JOptionPane.showConfirmDialog(null, "Dealer call, state bet: 10\nDo you want to follow?");
		
		if(followOrNot == JOptionPane.NO_OPTION) {
			r = 'n';
		}else if (followOrNot == JOptionPane.YES_OPTION) {
			r = 'y';
		}
		return r;
	}
	
	public char getPlayerNextGame() {
		char r = 'y';
		
		String[] options = {"NextGame","Quit"};
		int nextGame = JOptionPane.showOptionDialog(null, "Game Ended! Next Game?","Next Game", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, image, options, 0);

		if(nextGame == JOptionPane.NO_OPTION) {
			r = 'n';
		}else if (nextGame == JOptionPane.YES_OPTION) {
			r = 'y';
		}
		return r;
	}
	
	public int getPlayerCallBetChip(Player player) {
		String[] options = {"Enter"};
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Player call, state bet:");
		JTextField txtField = new JTextField(10);
		panel.add(label);
		panel.add(txtField);

		boolean validBetAmount = false;
		int chipsToBet = 0;
		while(!validBetAmount) {
			int optionDialog = JOptionPane.showOptionDialog(null, panel, "State Bet", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, image, options , options[0]);

			try{
				chipsToBet = Integer.parseInt(txtField.getText());
				if(chipsToBet<=0) {
					JOptionPane.showMessageDialog(null, "Chips cannot be negative or zero", "State Bet Error", JOptionPane.PLAIN_MESSAGE, image);
					System.out.println("Chips cannot be negative");
				}else if(chipsToBet>player.getChips()) {
					JOptionPane.showMessageDialog(null, "You do not have enough chips", "State Bet Error", JOptionPane.PLAIN_MESSAGE, image);
					System.out.println("You do not have enough chips");
				}else {
					validBetAmount = true;
				}
			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Invalid input", "State Bet Error", JOptionPane.PLAIN_MESSAGE, image);
			}
			
		}
		return chipsToBet;
	}
	
	public int getDealerCallBetChips() {
		int defaultBet = 10;
		System.out.println("Dealer call, state bet: 10");
//		}
		return defaultBet;
	}
	
	
}
