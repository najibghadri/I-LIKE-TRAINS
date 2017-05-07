package skeleton;

import sound.Sound;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Az alkalmazás konstruktora, létrehozza, beállítja a paneleket, és
 * Controllert példányosít, átadja a gamegui-nak
 * Végül csak a menüt teszi láthatóvá
 */
public class Application{
	private IliketrainsGUI gamegui;
	private Controller controller;

	private Sound sound;

    /** Időzítő objektum */
    private Timer timer;

    private ApplicationPanel panel;

    /**
     * Alkalmazás panelezéséért felelős. Egy cardLayout segítségével rendezi a paneleket
     * Az Application alkalmazás kezelőnek könnyít az ablakkezelésben úgy hogy nézeteket hoz létre.
     * Az ablakot 600x600-asra állítja
     */
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

        /**
         * Játék nézet
         */
        public void newGameView(){
            menuPanel.setVisible(false);
            win.setVisible(false);
            gameOver.setVisible(false);
        }

        /**
         * Menünézet/Stop nézet
         */
        public void stopView() {
            menuPanel.setVisible(true);
        }

        /**
         * Nyerésnél előjövő nézet
         */
        public void winView(){
            win.setVisible(true);
        }

        /**
         * Vesztés nézet
         */
        public void gameOverView(){
            gameOver.setVisible(true);
        }

        /**
         * Elkészíti a játék végét jelző JPanelt,ami egy szöveg, plusz egy "New Game" gomb
         * ami elindítja újra a pályát
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
         * A nyerésnél megjelenített panelt készíti el, egy szöveg és egy "Start Next Map" gomb
         * ami indítja a következő pályát
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
     * Játékot indít:
     * Előkészíti a jéték nézetet (gamegui) majd
     * A controller-el új játékot inicializál
     * Előkészíti a hang effekteket
     * És Elkezdi a játékot egy szinkronizált swing timer-el.
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
     * Leállítja a contorllert és a gamegui-t is, egyik sem rajzol/mozgat tovább
     * A menü lesz látható
     */
    public void stop() {
	    timer.stop();
        sound.stop();
		panel.stopView();
		gamegui.panel().setVisible(false);

	}

    /**
     * Bezárja az alkalmazást
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
