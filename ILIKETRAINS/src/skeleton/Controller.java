package skeleton;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import iliketrains.Cart;
import iliketrains.Controllable;
import iliketrains.RailCenter;
import iliketrains.Station;
import iliketrains.TrackComponent;

/**
 * A Kontroller osztály valósítja meg az kapcsolható elemek kapcsolhatóságát
 */
public class Controller {
	
	/** Az állítható elemek listája */
	private List<Controllable> controllables;
	
	/** A RailCenter referenciája. */
	private RailCenter railCenter;
	
	/** A beolvasáshoz szükséges objektum */
	private Scanner reader = new Scanner(System.in);
	
	/** Időzítő objektum */
	Timer timer;
	
	/** Futást jelző flag */
	private boolean running=false;

    /** Jelenlegi térkép fájl*/
	private int numberOfMap=1;

	/**
	 * Applicationra referencia, arra kell hogy szóljunk,
	 *  ha nyer vagy veszít a játékos
	 */
	private Application app;
	
	public Controller(Application application) {
		app=application;
	}


	/**
	 * Játék alatt működő időzítőt valósítja meg (bizonyos időközönként lép)
	 */
	private void gameAutoTick() {
		railCenter.moveEngines();
		
	      if(railCenter.getAnyCollided()){
			  System.out.println("GAME OVER, YOU LOST!");
			  app.gameOver();
			  if(timer!=null){
			  timer.cancel();
			  running=false;
			  }

		  }
		  if(railCenter.getAllEmptyStatus()){
			  System.out.println("SUCCESS, YOU WON!");
			  app.win();
			  if(timer!=null){
			  timer.cancel();
			  running=false;
			  }
			  
			  numberOfMap++;
			  if(numberOfMap>2){
				  System.out.println("Nincs több pálya");
				  numberOfMap=1;
			  }
		  }
	}


	/**
	 * Real-Time működő játékot elindító függvény
	 * Új railCentert hoz létre, mert minden elem új
	 */
	public void startAutomataGame() {
		railCenter=new RailCenter();
		railCenter.loadMap("game"+numberOfMap);
		controllables=railCenter.getControllables();
		railCenter.loadTrain("train"+numberOfMap);
		timer = new Timer();
		timer=new Timer();
		running=true;
		timer.schedule(new TimerTask() {
			  @Override
			  public void run() {
				  gameAutoTick();
			  }
			}, 0,2000);
	}


	/**
	 * A paraméterben megkapott id-jú elem change függvényét hívja meg
	 * Megkeresi a listában a megfelelő id-jú elemet
	 * @param string Szövegként megkapott sorszám
	 */
	public void change(String string) {
		int id = Integer.parseInt(string);
		for(int i=0;i<controllables.size();i++){
			if(controllables.get(i).getId()==id){
				controllables.get(i).change();
			}
		}
	}

	/**
	 * @return the numberOfMap
	 */
	public int getNumberOfMap() {
		return numberOfMap;
	}

	/**
	 * @param numberOfMap the numberOfMap to set
	 */
	public void setNumberOfMap(int numberOfMap) {
		this.numberOfMap = numberOfMap;
	}


	/**
	 * Megállítja a játékot
	 */
	public void stop() {
		timer.cancel();
	}


	public List<Controllable> getControllables() {
		return controllables;
	}


	public List<Cart> getCarts() {
		return railCenter.getCarts();
	}


	public List<Station> getStations() {
		return railCenter.getStations();
	}
	
	
}
