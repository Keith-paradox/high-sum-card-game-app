import Model.*;
import Controller.*;
import GUIExample.GUIAnimation;
import GUIExample.GameTablePanel;
import View.*;

public class HighSum {

	private Dealer dealer;
	private Player player;
	private ViewController view;
    private GameController gc;
    
    public HighSum() {
    	//create all the required objects
    	this.dealer = new Dealer();
        this.player = new Player("IcePeak","password",50);
        this.view = new ViewController();
        //bring them together
    	this.gc = new GameController(this.dealer,this.player,this.view);
    }
	
    public void run() {
    	//starts the game!
    	gc.run();
    }
    
	public static void main(String[] args) {
		boolean login = false;
		boolean start = true;
		GameTablePanel.Login();
		while(start) {
			login = GameTablePanel.getLogin();
			System.out.println(login);
			if(login) {
//				System.out.println("This is runing");
				new HighSum().run();
				start = false;
			}
		}
		
	}

}
