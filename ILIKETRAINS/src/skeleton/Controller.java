package skeleton;

import java.util.List;
import java.util.Scanner;

import javax.swing.text.ChangedCharSetException;

import iliketrains.Color;
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
	
	/** A kontroll szál, bemenetet olvassa, amíg meg nem szakítják. */
	final Thread controlThread = new Thread(new Runnable() {
		  public void run() {
		    while (!Thread.interrupted()) {
		      readInput();
		    }
		    System.out.println("ControlThread 1 stopped!");
		  }
		});
	
	/**
	 * A kontroll szálat indítja el.
	 *
	 * @throws InterruptedException the interrupted exception
	 */
	public void startGame() throws InterruptedException{
		railCenter=new RailCenter();
		controlThread.start();
	}
	
	
	/**
	 * Beolvassa a bemenetet és a megfelelő parancsra megfelelő függvényt hív.
	 */
	private void readInput() {
			String command=reader.nextLine();
			String[] commandpart=command.split(" ");			
			
			switch (commandpart[0]) {
			case "exit":
				controlThread.interrupt();				
				break;
			case "change":
				change(commandpart[1]);
				break;
			case "start":
				//Useless :D
				//na jó, Timer indítás, de loadmap után kéne, ez rossz volt eddig szerintem
				break;
			case "loadmap":
				railCenter.loadMap(commandpart[1]);
				controllables=railCenter.getControllables();
				break;
			case "loadtrain":
				railCenter.loadTrain(commandpart[1]);
				break;
			default:
				break;
			}
	}

	/**
	 * A paraméterben megkapott id-jú elem change függvényét hívja meg
	 * Megkeresi a listában a megfelelő id-jú elemet
	 * @param string Szövegként megkapott sorszám
	 */
	private void change(String string) {
		int id = Integer.parseInt(string);
		for(int i=0;i<controllables.size();i++){
			if(controllables.get(i).getId()==id){
				controllables.get(i).change();
			}
		}
	}
}
