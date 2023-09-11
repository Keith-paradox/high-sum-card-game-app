package Controller;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import GUIExample.GameTableFrame;
import GUIExample.GameTablePanel;
import Model.Dealer;
import Model.Player;
import View.ViewController;

public class GameController {

	private static Dealer dealer;
	private static Player player;
	private static ViewController view;
	private int chipsOnTable;
	private boolean allIn = false;
	private ImageIcon originalimage = new ImageIcon("images/stickman.png");
	java.awt.Image imageBefore = originalimage.getImage();
	java.awt.Image resizeImage = imageBefore.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
	private ImageIcon image = new ImageIcon(resizeImage);
	
	public GameController(Dealer dealer,Player player,ViewController view) {
		this.dealer = dealer;
		this.player = player;
		this.view = view;
		this.chipsOnTable = 0;
	}
	
	public void run() {
		
		boolean carryOn= true;
		
		while(carryOn) {
			runOneRound();
			char r = this.view.getPlayerNextGame();
			if(r=='n') {
				carryOn = false;
			}
		}
		this.view.displayPlayerNameAndLeftOverChips(this.player);
		this.view.displayExitGame();
	}
	
	public static void main(String[] args) {
		
		new GameController(dealer, player, view).run();
	}
	
	public void runOneRound() {
		allIn = false;
		this.dealer.shuffleCards();
		
		if(this.player.getChips() <= 0) {
			JOptionPane.showMessageDialog(null, "You have no chips left to play", "Not Enough Chips", JOptionPane.PLAIN_MESSAGE, image);
			return;
		}
		this.view.emptyCards();
		this.view.displayGameTitle();
		this.view.displayDoubleLine();
		this.view.displayPlayerNameAndChips(this.player);
		this.view.displaySingleLine();
		
		this.view.displaySingleLine();
		
		this.chipsOnTable = 0;
		this.view.displayBetOntable(this.chipsOnTable);
		boolean playerQuit = false;
		this.view.displayDealerDealCardsAndGameRound(1);
		this.view.displayPlayerTotalCardValue(player);
		this.view.emptyDealertotal();
		this.view.displayGameStart();
		
		for(int round = 1;round<=4;round++) {
			this.view.displaySingleLine();
			this.view.displayDealerDealCardsAndGameRound(round);
			this.view.displaySingleLine();

			if(round==1) {//round 1 deal extra card
				this.dealer.dealCardTo(this.player);
				this.dealer.dealCardTo(this.dealer);
			}
			this.dealer.dealCardTo(this.player);
			this.dealer.dealCardTo(this.dealer);
			
			this.view.displayPlayerCardsOnHand(this.dealer);
			this.view.displayBlankLine();
			this.view.displayPlayerCardsOnHand(player);
			this.view.displayPlayerTotalCardValue(player);
			
			int whoCanCall = this.dealer.determineWhichCardRankHigher(dealer.getLastCard(), player.getLastCard());
			if(allIn == false) {
				if(whoCanCall==1) {//dealer call
					if(this.player.getChips()<10) {
						JOptionPane.showMessageDialog(null, "You don't have enough chips to follow the dealer.", "Not Enough Chips to Follow", JOptionPane.PLAIN_MESSAGE, image);
						return;
					}
					else {
						int chipsToBet = this.view.getDealerCallBetChips();
						//ask player want to follow?
						char r = this.view.getPlayerFollowOrNot(this.player,chipsToBet);
						if(r=='y') {
							this.player.deductChips(chipsToBet);
							this.chipsOnTable+=2*chipsToBet;	
							this.view.displayPlayerNameAndLeftOverChips(this.player);
							this.view.displayBetOntable(this.chipsOnTable);
						}else {
							playerQuit = true;
							break;
						}
					}
					
				}else {//player call
					if(round==1) {//round 1 player cannot quit
						int chipsToBet = view.getPlayerCallBetChip(this.player);
						
						if(chipsToBet == player.getChips()) {
							//all in
							JOptionPane.showMessageDialog(null, "You bet all of your chips", "All in", JOptionPane.PLAIN_MESSAGE, image);
							allIn = true;
						}
						
						this.player.deductChips(chipsToBet);
						this.chipsOnTable+=2*chipsToBet;
						this.view.displayBetOntable(this.chipsOnTable);
					}else {
						char r = this.view.getPlayerCallOrQuit();
						if(r=='c') {
							int chipsToBet = view.getPlayerCallBetChip(this.player);
							
							if(chipsToBet == player.getChips()) {
								//all in
								JOptionPane.showMessageDialog(null, "You bet all of your chips", "All in", JOptionPane.PLAIN_MESSAGE, image);
								allIn = true;
							}
							
							this.player.deductChips(chipsToBet);
							this.chipsOnTable+=2*chipsToBet;
							this.view.displayPlayerNameAndLeftOverChips(this.player);
							this.view.displayBetOntable(this.chipsOnTable);
						}else {
							playerQuit = true;
							break;
						}
					}
				}
			}
		}
		this.view.revealDealerFirstCard(dealer);
		
		//check who win
		if(playerQuit) {
			this.view.displayPlayerNameAndLeftOverChips(this.player);
			this.view.displayPlayerQuit();
		}
		else if(this.player.getTotalCardsValue()>this.dealer.getTotalCardsValue()) {
			this.view.displayDealerTotalCardValue(dealer);
			this.view.displayPlayerWin(this.player);
			this.player.addChips(chipsOnTable);
			this.view.displayPlayerNameAndLeftOverChips(this.player);
		}else if(this.player.getTotalCardsValue()<this.dealer.getTotalCardsValue()) {
			this.view.displayDealerTotalCardValue(dealer);
			this.view.displayDealerWin();
			this.view.displayPlayerNameAndLeftOverChips(this.player);
		}else {
			this.view.displayTie();
			this.view.displayDealerTotalCardValue(dealer);
			this.player.addChips(chipsOnTable/2);
			this.view.displayPlayerNameAndLeftOverChips(this.player);
		}
		
		//put all the cards back to the deck
		dealer.addCardsBackToDeck(dealer.getCardsOnHand());
		dealer.addCardsBackToDeck(player.getCardsOnHand());
		dealer.clearCardsOnHand();
		player.clearCardsOnHand();	
	}
	
	
}
