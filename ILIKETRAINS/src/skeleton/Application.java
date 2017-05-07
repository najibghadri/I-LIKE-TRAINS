package skeleton;

import sound.Sound;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Application{
	private IliketrainsGUI gamegui;
	private Controller controller;

	private Sound sound;

    /** Időzítő objektum */
    private Timer timer;

    private ApplicationPanel panel;

    private class ApplicationPanel extends JFrame {
        private CardLayout cardLayout;
        private MenuPanel menuPanel;
        private JPanel gameOver;
        private JPanel win;

        public ApplicationPanel (){
            super();
            setTitle("I LIKE TRAINS");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(600,600);

            //Layout
            cardLayout = new CardLayout();
            setLayout(cardLayout);

            //MenuPanel
            menuPanel =new MenuPanel(Application.this);
            add(menuPanel);

            //Game over interface
            initGameOver();
            add(gameOver);
            gameOver.setVisible(false);

            //Win interface
            initWin();
            add(win);
            win.setVisible(false);
        }

        public void newGameView(){
            menuPanel.setVisible(false);
            win.setVisible(false);
            gameOver.setVisible(false);
        }

        public void stopView() {
            menuPanel.setVisible(true);
        }

        public void winView(){
            win.setVisible(true);
        }

        public void gameOverView(){
            gameOver.setVisible(true);
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
                    startNewGame();
                }
            });
        }

        /**
         * A nyerésnél megjelenített panelt készíti el
         */
        private void initWin() {
            win=new JPanel();
            JLabel label1 = new JLabel();
            label1.setText("<html><h1>SUCCESS, YOU WON!</h1></html>");
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
                    startNewGame();
                }
            });
        }
    }

    /**
     * Panel manager
     */
    public Application(){
        panel = new ApplicationPanel();

        //Sound effects
        sound = new Sound();

        //Game logic controller
		controller=new Controller(this);

		//Game canvas
        gamegui=new IliketrainsGUI(this, controller);
        panel.add(gamegui.panel());
        gamegui.panel().setVisible(false);

	    panel.setVisible(true);
	}

	/**
	 * Betöltteti a soron következő pályát, lekéri a grafikához szükséges elemeket
	 * és a grafikai elemeket is létrehozatja a gamegui-val
	 */
	public void startNewGame(){
        panel.newGameView();
		gamegui.panel().setVisible(true);

		controller.initializeGame();
		gamegui.loadGraphicsMap();

		//play background music
		sound.start();

		//Start the timer
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controller.gameTick();
                gamegui.panel().repaint();
            }
        };
        timer = new Timer(750, taskPerformer);
        timer.start();
	}

    /**
     * Stops the game
     */
    public void stop() {
	    timer.stop();
        sound.stop();
		panel.stopView();
		gamegui.panel().setVisible(false);

	}

    /**
     * Exit from the game
     */
	public void exit() {
		panel.setVisible(false);
        if (panel.isDisplayable()) {
            panel.dispose();
        }
		System.exit(0);
	}

	/**
	 * Vesztett a játékos, ehhez tartozó jpanelt jeleníti meg
	 */
	public void gameOver() {
        timer.stop();
		panel.gameOverView();
	}
	
	/**
	 * Nyert a játékos, ehhez tartozó jpanel jelenik meg
	 */
	public void win(){
        timer.stop();
		panel.winView();
	}

}
