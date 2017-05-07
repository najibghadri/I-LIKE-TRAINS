package skeleton;

import java.util.List;
import java.util.Map;

import iliketrains.*;

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
	 * Konstruktor
	 */
	public Controller() {

    }

	/**
	 * Játék alatt működő időzítőt valósítja meg (bizonyos időközönként lép)
	 * Vesztés vagy nyerés esetén hívja az Application megfelelő metódusát
	 * Leállítja a timer objektumot, már nem kell lépni többet
	 * Ha nyer, akkor a következő pályát állítja be
	 */
	public EventResult gameTick() {
		railCenter.moveEngines();
		
	      if(railCenter.getAnyCollided()){
			  System.out.println("GAME OVER, YOU LOST!");
			  return EventResult.LOSE;

		  }
		  if(railCenter.getAllEmptyStatus()){
			  System.out.println("SUCCESS, YOU WON!");

			  //Proceed on to the next maps
			  numberOfMap++;
			  if(numberOfMap>3){
				  System.out.println("Nincs több pálya");
				  numberOfMap=1;
			  }

			  return EventResult.WIN;
		  }
        return EventResult.CONTINUE;
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

	/**
	 * Visszaadja az állítható elemek listáját
	 *
	 * @return kapcsolható elemek listája
	 */
	public List<Controllable> getControllables() {
		return controllables;
	}


	/**
	 * Visszaadja a vonatkocsik listáját
	 *
	 * @return vonatkocsi lista
	 */
	public List<Cart> getCarts() {
		return railCenter.getCarts();
	}


	/**
	 * Visszaadja az állomások listáját
	 *
	 * @return állomások listája
	 */
	public List<Station> getStations() {
		return railCenter.getStations();
	}


    /**
     * Visszaadj a grafikus sínelem listát
     *
     * @return sínelem lista
     */
    public Map<Integer,TrackComponent> getTrackComponents() {
        return railCenter.getTrackComponents();
    }
	
}
