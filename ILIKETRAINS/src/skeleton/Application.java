package skeleton;

import javax.swing.JFrame;

public class Application extends JFrame{
	private IliketrainsGUI gamegui;
	private Menu menu;
	private Controller controller;
	
	public Application(Controller controller){
		super();
		this.controller=controller;
		gamegui=new IliketrainsGUI(this);
		menu=new Menu(this);
		
		setSize(600,600);
		add(menu);
		add(gamegui);
		gamegui.setVisible(false);
		setTitle("ILIKETRAINS");
		setVisible(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void newGame(){
		menu.setVisible(false);
		gamegui.setVisible(true);
		controller.startAutomataGame();
		gamegui.loadGraphicsMap(controller.getNumberOfMap());
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

}
