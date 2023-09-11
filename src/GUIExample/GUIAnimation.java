package GUIExample;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Model.*;

public class GUIAnimation extends JPanel implements ActionListener{
	

	private static Image cardBackImage;

	private static Timer timer;
	private static GUIAnimation panel;
	private int x = 450;
	private int y = 200;
	private String shuffling = "Shuffling";
	
	public GUIAnimation() {
		this.setPreferredSize(new Dimension(1500, 900));
		this.setBackground(Color.green);
		
		cardBackImage = new ImageIcon("images/back.png").getImage();
		timer = new Timer(200, this);
		
		timer.restart();
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics graphic = (Graphics2D) g;
		Graphics graphic1 = (Graphics2D) g;
		Graphics graphic2 = (Graphics2D) g;
		Graphics shuffle = (Graphics2D) g;
		
		graphic.drawImage(cardBackImage, x, y, null);
		graphic1.drawImage(cardBackImage, 450, 200, null);
		graphic2.drawImage(cardBackImage, 750, 200, null);
		shuffle.drawString(shuffling, 650, 500);
	}
	
	public void actionPerformed(ActionEvent event) {
		shuffling = shuffling + ".";
		
		if(shuffling.length() == 15) {
			shuffling = "Shuffling";
		}
		
		
		x += 30;
		
		if(x == 750) {
			x = 450;
		}
		
		repaint();
	}
	
	public static void run() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new GUIAnimation();
		frame.add(panel);
		
		frame.pack();
		frame.setVisible(true);
		pause5s();
		frame.dispose();
	}
	
	private static void pause5s() {
	   	 try{
	            Thread.sleep(5000);
	            
	         }catch(Exception e){}
	   }

	public static void main(String[] args) {
		new GUIAnimation().run();
//		pause5s();
		
	}
}
