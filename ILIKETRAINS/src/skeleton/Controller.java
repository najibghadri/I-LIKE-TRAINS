package skeleton;

import java.util.List;

import iliketrains.Cart;
import iliketrains.Controllable;
import iliketrains.RailCenter;
import iliketrains.Station;

/**
 * A Kontroller osztály valósítja meg az kapcsolható elemek kapcsolhatóságát
 */
public class Controller {
	
	/** Az állítható elemek listája */
	private List<Controllable> controllables;
	
	/** A RailCenter referenciája. */
	private RailCenter railCenter;

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
	 * Vesztés vagy nyerés esetén hívja az Application megfelelő metódusát
	 * Leállítja a timer objektumot, már nem kell lépni többet
	 * Ha nyer, akkor a következő pályát állítja be
	 */
	public void gameTick() {
		railCenter.moveEngines();
		
	      if(railCenter.getAnyCollided()){
			  System.out.println("GAME OVER, YOU LOST!");
			  app.gameOver();

		  }
		  if(railCenter.getAllEmptyStatus()){
			  System.out.println("SUCCESS, YOU WON!");
			  app.win();

			  //Proceed on to the next maps
			  numberOfMap++;
			  if(numberOfMap>3){
				  System.out.println("Nincs több pálya");
				  numberOfMap=1;
			  }
		  }
	}
    
	/**
	 * Real-Time működő játékot inicializáló függvény
	 * Új railCentert hoz létre, mert minden elem új
	 */
	public void initializeGame() {
		railCenter=new RailCenter();
		railCenter.loadMap("game"+numberOfMap);
		controllables=railCenter.getControllables();
		railCenter.loadTrain("train"+numberOfMap);
	}

	/**
	 * A paraméterben megkapott id-jú elem change függvényét hívja meg
	 * Megkeresi a listában a megfelelő id-jú elemet
	 * @param id Szövegként megkapott sorszám
	 */
	public void change(Integer id) {
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
