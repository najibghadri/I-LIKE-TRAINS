package skeleton;

import java.util.List;
import java.util.Scanner;

import javax.swing.text.ChangedCharSetException;

import iliketrains.Color;
import iliketrains.Controllable;
import iliketrains.RailCenter;
import iliketrains.Station;
import iliketrains.TrackComponent;

public class Controller {
	private static List<Controllable> controllables;
	private static RailCenter railCenter;
	private static Scanner reader = new Scanner(System.in);
	
	/**
	 * A kontroll szál, bemenetet olvassa, amíg meg nem szakítják
	 */
	final Thread controlThread = new Thread(new Runnable() {
		  public void run() {
		    while (!Thread.interrupted()) {
		      readInput();
		    }
		    System.out.println("ControlThread 1 stopped!");
		  }
		});
	
	/**
	 * A kontroll szálat indítja el
	 * @throws InterruptedException
	 */
	public void startGame() throws InterruptedException{
		railCenter=new RailCenter();
		controlThread.start();
	}
	
	
	/**
	 * Beolvassa a bemenetet és a megfelelő parancsra megfelelő függvényt hív
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
				break;
			case "loadtrain":
				railCenter.loadTrain(commandpart[1]);
				break;
			default:
				break;
			}
	}

	private void change(String string) {
		// TODO Auto-generated method stub
		
	}
}
