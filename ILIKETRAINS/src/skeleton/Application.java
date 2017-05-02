package skeleton;

import iliketrains.Cart;
import iliketrains.Controllable;
import iliketrains.Station;
import iliketrains.TrackComponent;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Application extends JFrame{
	private IliketrainsGUI gamegui;
	private Menu menu;
	private Controller controller;
	private JPanel gameOver;
	private JPanel win;
	private CardLayout cardLayout;

	
	public Application(){
		super();
		cardLayout = new CardLayout();
		setLayout(cardLayout);
		setVisible(true);
		controller=new Controller(this);
		gamegui=new IliketrainsGUI(this, controller);
		menu=new Menu(this);
		
		initGameOver();
		initWin();
		setSize(600,600);
		add(menu);
		add(gamegui);
		add(gameOver);
		add(win);
		gamegui.setVisible(false);
		gameOver.setVisible(false);
		win.setVisible(false);
		setTitle("ILIKETRAINS");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Betöltteti a soron következő pályát, lekéri a grafikához szükséges elemeket
	 * és a grafikai elemeket is létrehozatja a gamegui-val
	 */
	public void newGame(){
		menu.setVisible(false);
		win.setVisible(false);
		gameOver.setVisible(false);
		gamegui.validate();
		gamegui.repaint();
		gamegui.setVisible(true);

		controller.startAutomataGame();
		gamegui.loadGraphicsMap();
		gamegui.start();
	}

	public void stop() {
		menu.setVisible(true);
		gamegui.setVisible(false);
		gamegui.stop();
		controller.stop();
	}

	public void exit() {
		setVisible(false);
		dispose();
		System.exit(0);
	}

	/**
	 * Vesztett a játékos, ehhez tartozó jpanelt jeleníti meg
	 */
	public void gameOver() {
		gameOver.setVisible(true);
		gamegui.setVisible(false);
		gamegui.stop();
		controller.stop();
	}
	
	/**
	 * Nyert a játékos, ehhez tartozó jpanel jelenik meg
	 */
	public void win(){
		win.setVisible(true);
		gamegui.setVisible(false);
		gamegui.stop();
		controller.stop();
	}
	
	/**
	 * Elkészíti a játék végét jelző JPanelt, szöveg, plusz egy "Újra" gomb
	 */
	private void initGameOver(){
		gameOver=new JPanel(new FlowLayout());
		JLabel label1 = new JLabel();
		label1.setText("<html><h1>GAME OVER, YOU LOST!</h1></html>");	
		label1.setBounds(0, 0, 200, 50);
		label1.setHorizontalAlignment(JLabel.CENTER);
		gameOver.add(label1);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setBounds(10,10, 100, 20);
		btnNewGame.setHorizontalAlignment(JLabel.CENTER);
		gameOver.add(btnNewGame);
		btnNewGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				newGame();	
			}
		});
	}
	
	/**
	 * A nyerésnél megjelenített panelt készíti el
	 */
	private void initWin() {
		win=new JPanel();
		JLabel label1 = new JLabel();
		label1.setText("<html><h1>SUCCESS, YOU WON</h1></html>");	
		label1.setBounds(0, 0, 200, 50);
		label1.setHorizontalAlignment(JLabel.CENTER);
		win.add(label1);
		
		JButton btnNewGame = new JButton("Start Next Map");
		btnNewGame.setBounds(10,10, 100, 20);
		btnNewGame.setHorizontalAlignment(JLabel.CENTER);
		btnNewGame.setVerticalAlignment(JLabel.CENTER);
		win.add(btnNewGame);
		btnNewGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				newGame();	
			}
		});
	}

}
