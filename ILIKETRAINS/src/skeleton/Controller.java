package skeleton;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;
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
		testOrGame();
	}
	
	
	/**
	 * Beolvassa a bemenetet és a megfelelő parancsra megfelelő függvényt hív.
	 */
	private void readInput() {
			String command=reader.nextLine();
			String[] commandpart=command.split(" ");			
			
			switch (commandpart[0]) {
			case "stop":
				controlThread.interrupt();	
				//TODO: timer leállítása
				break;
			case "change":
				change(commandpart[1]);
				break;
			case "loadmap":
				railCenter.loadMap(commandpart[1]);
				controllables=railCenter.getControllables();
				break;
			case "loadtrain":
				railCenter.loadTrain(commandpart[1]);
				break;
			case "moveengines":
				gameTick();
				break;
			case "print":
				railCenter.printStatus();
				break;
			default:
				break;
			}
	}
	
	private void gameTick() {
		railCenter.moveEngines();
	      if(railCenter.getAnyCollided()){
			  System.out.println("GAME OVER, YOU LOST!");
			  //TODO pálya/train neve
			  railCenter.loadMap("NextMap");
			  railCenter.loadTrain("NewTrain");
		  }
		  if(railCenter.getAllEmptyStatus()){
			  System.out.println("SUCCESS, YOU WON!");
			  //TODO pálya/train neve
			  railCenter.loadMap("NextMap");
			  railCenter.loadTrain("NewTrain");
		  }
	}
	
	private void testOrGame(){
	System.out.println("Játék vagy teszt? (1|2)");
	String line = null;

	line = reader.nextLine();
	
	//Elindítja a játékot
	if(line.equals("1")){
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			  @Override
			  public void run() {
				  gameTick();
			  }
			}, 3000);
	}
	//Ha nem indítunk, akkor tesztfájlt választunk
	else{
		System.out.println("1. teszt)");
		System.out.println("2. teszt)");
		System.out.println("3. teszt)");
		System.out.println("4. teszt)");
		System.out.println("5. teszt)");
		System.out.println("6. teszt)");
		System.out.println("7. teszt)");
		System.out.println("8. teszt)");
		line = reader.nextLine();
		File file = null;
		String filename="";
			switch (line){
				case "1":
					filename=Game.generateFilename("elso.txt");
					file = new File(filename);
					break;
				case "2":
					filename=Game.generateFilename("masodik.txt");
					file = new File(filename);
					break;
				case "3":
					file = new File("file.txt");
					break;
				case "4":
					file = new File("file.txt");
					break;
				case "5":
					file = new File("file.txt");
					break;
				case "6":
					file = new File("file.txt");
					break;
				case "7":
					file = new File("file.txt");
					break;
				case "8":
					file = new File("file.txt");
					break;
				default:
					break;
			}
		//Beolvassa a tesztparancsokat
		BufferedReader br = null;
		ArrayList<String> commands = new ArrayList<String>();
		try {
			br = new BufferedReader(new FileReader(file));
			String text = null;
			while ((text = br.readLine()) != null) {
				commands.add(text);
			}
		} catch (Exception e) {

		} finally {
			if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
		}
		
		//A kontroll szálat elindítja
		controlThread.start();
		//Beadja a tesztbemeneteket
		//Nem rossz ötlet, de nem sikerült működésre bírnom ezt a módszerts
//		InputStream stdin = System.in;
//		for (String in: commands) {
//			try {
//			  System.setIn(new ByteArrayInputStream(in.getBytes()));
//			  reader=new Scanner(System.in);
//			} finally {
//			  System.setIn(stdin);
//			}
//		}
		for(String in:commands){
			String[] commandpart=in.split(" ");
			switch (commandpart[0]) {
			case "print":
				railCenter.printStatus();
				break;
			case "change":
				change(commandpart[1]);
				break;
			case "loadmap":
				railCenter.loadMap(commandpart[1]);
				controllables=railCenter.getControllables();
				break;
			case "loadtrain":
				railCenter.loadTrain(commandpart[1]);
				break;
			case "moveengines":
				gameTick();
			default:
				break;
			}
		}
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
