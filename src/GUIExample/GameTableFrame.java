package GUIExample;
import java.awt.*;
import javax.swing.*;

import Model.*;

public class GameTableFrame extends JFrame{
	 
    private GameTablePanel gameTablePanel;
    private Dealer dealer;
    private Player player;
    private int count=0;
    
    public GameTableFrame(Dealer dealer, Player player)
    {
        this.dealer = dealer;
        this.player = player;
        gameTablePanel = new GameTablePanel(dealer,player);
        this.count=0;
        
        add(gameTablePanel);
        pack();
        setVisible(true);
    }
    
    public void updateGameTable()
    {
        gameTablePanel.repaint();
    }
    
    public GameTablePanel getGamePanel() {
		return this.gameTablePanel;
	}

    public void run() {
    	
        for(int i=0;i<5;i++) {
        	dealer.dealCardTo(dealer);
        	dealer.dealCardTo(player);
        	
        	pause();
        	updateGameTable();
        }
    }
    
    public void dealCards() {
    	dealer.dealCardTo(dealer);
    	dealer.dealCardTo(player);
    	pause();
    	updateGameTable();
    }
    
    //pause for 500msec
    private void pause() {
    	 try{
             Thread.sleep(500);
             
          }catch(Exception e){}
    }
    
}
