package GUIExample;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Model.*;

public class GameTablePanel extends JPanel implements ActionListener{
	
	private static boolean login = false;
	private static Player player;
	private static Dealer dealer;
	private static ImageIcon cardBackImage, chipImage;
	private static GameTableFrame app;
	private static JButton button;
	private static JLabel usernameLabel,passwordLabel, errorLabel;
	private static JTextField usernameTextField;
	private static JPasswordField pswTextField;
	private static JFrame loginframe;
	
	public JLabel labelTitle = new JLabel("");
	public JLabel playerChips = new JLabel("");
	public JLabel gameStart = new JLabel("");
	public JLabel round = new JLabel("");
	public JLabel exit = new JLabel("");
	public JLabel betsOnTable = new JLabel("Bet on table : 0");
	public JLabel playerTotal = new JLabel("");
	public JLabel dealerTotal = new JLabel("");
	public JLabel dealerName = new JLabel("dealer");
	public JLabel playerName = new JLabel("player");
	
	public JLabel playerLabel1 = new JLabel("");
	public JLabel playerLabel2 = new JLabel("");
	public JLabel playerLabel3 = new JLabel("");
	public JLabel playerLabel4 = new JLabel("");
	public JLabel playerLabel5 = new JLabel("");
	
	public JLabel dealerLabel1 = new JLabel("");
	public JLabel dealerLabel2 = new JLabel("");
	public JLabel dealerLabel3 = new JLabel("");
	public JLabel dealerLabel4 = new JLabel("");
	public JLabel dealerLabel5 = new JLabel("");
	
	public GameTablePanel(Dealer dealer, Player player) {
		
		setBackground(Color.green);
		setPreferredSize(new Dimension(1500, 900));
		setLayout(null);
		
		dealerName.setBounds(10, 30,100,100);
		add(dealerName);
		
		playerName.setBounds(10, 420,100,100);
		add(playerName);
		
		labelTitle.setBounds(650, -30,100,100);
		add(labelTitle);
		
		playerChips.setBounds(650, 800, 500, 100);
		add(playerChips);
		
		round.setBounds(10, 10, 500, 100);
		add(round);
		
		betsOnTable.setBounds(70, 370, 500, 100);
		add(betsOnTable);
		
		playerTotal.setBounds(10, 710, 500, 100);
		add(playerTotal);
		
		dealerTotal.setBounds(10, 300, 500, 100);
		add(dealerTotal);
		
		
		
		chipImage = new ImageIcon("images/Chip.svg.png");
		java.awt.Image chipOriginal = chipImage.getImage();
		int newWidth = 50;
		int newHeight = 50;
		
		java.awt.Image resizeImage = chipOriginal.getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_SMOOTH);
		
		ImageIcon resizeIcon = new ImageIcon(resizeImage);
		JLabel chip = new JLabel(resizeIcon);
		chip.setBounds(10, 390, 50, 50);
		add(chip);
		
		cardBackImage = new ImageIcon("images/back.png");
		
		playerLabel1.setBounds(10,500,200,200);
		playerLabel2.setBounds(210,500,200,200);
		playerLabel3.setBounds(410,500,200,200);
		playerLabel4.setBounds(610,500,200,200);
		playerLabel5.setBounds(810,500,200,200);
		
		add(playerLabel1);
		add(playerLabel2);
		add(playerLabel3);
		add(playerLabel4);
		add(playerLabel5);
		
		dealerLabel1.setBounds(10,120,200,200);
		dealerLabel2.setBounds(210,120,200,200);
		dealerLabel3.setBounds(410,120,200,200);
		dealerLabel4.setBounds(610,120,200,200);
		dealerLabel5.setBounds(810,120,200,200);
		
		add(dealerLabel1);
		add(dealerLabel2);
		add(dealerLabel3);
		add(dealerLabel4);
		add(dealerLabel5);
		
		JLabel deck1 = new JLabel(cardBackImage);
		deck1.setBounds(1150,300,200,200);
		
		JLabel deck2 = new JLabel(cardBackImage);
		deck2.setBounds(1180,310,200,200);
		
		JLabel deckLabel = new JLabel("DECK");
		deckLabel.setBounds(1250,480,100,100);
		
		add(deck2);
		add(deck1);
		add(deckLabel);
		
		this.dealer = dealer;
		this.player = player;
	}
	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int x = 50;
		int y = 100;

		int i = 0;
		for (Card c : dealer.getCardsOnHand()) {
			// display dealer cards
			if (i == 0d) {
				cardBackImage.paintIcon(this, g, x, y);
				i++;

			} else {
				c.paintIcon(this, g, x, y);
			}

			x += 200;
		}

		// display player cards
		x = 50;
		y = 600;

		for (Card c : player.getCardsOnHand()) {
			// display dealer cards
			c.paintIcon(this, g, x, y);
			x += 200;
		}
	}
	
	
	public static void Login() {
		loginframe = new JFrame("Game Login");
		loginframe.setSize(400,250);
		loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		loginframe.add(panel);
		
		panel.setLayout(null);
		
		usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(10,20,80,25);
		panel.add(usernameLabel);
		
		usernameTextField = new JTextField(10);
		usernameTextField.setBounds(100,20,165,25);
		panel.add(usernameTextField);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10,50,80,25);
		panel.add(passwordLabel);
		
		pswTextField = new JPasswordField(10);
		pswTextField.setBounds(100,50,165,25);
		panel.add(pswTextField);
		
		button = new JButton("Login");
		button.setBounds(100, 80, 80, 25);
		button.addActionListener(new GameTablePanel(dealer, player));
		panel.add(button);
		
		errorLabel = new JLabel("");
		errorLabel.setBounds(10,110,300,25);
		panel.add(errorLabel);
		
		loginframe.setVisible(true);
	}
	
	public static boolean getLogin() {
		return login;
	}
	
	public void actionPerformed(ActionEvent event) {
		String usernametext = usernameTextField.getText();
		String passwordtext = pswTextField.getText();
		if(usernametext.equals("IcePeak") && passwordtext.equals("password")) {
			loginframe.dispose();
			login = true;
		}else {
			errorLabel.setText("Incorrect username or password");
		}
	}
	
	
	public void run() {
		dealer.shuffleCards();
		app = new GameTableFrame(dealer,player);
	    app.run();
	}
	
	
	public static void main(String[] args) {
		Player player = new Player("tester1","",10000);
		Dealer dealer = new Dealer();
		Login();
		
		
		
	}

	
}
