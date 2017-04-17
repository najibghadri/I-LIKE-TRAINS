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
	
	Timer timer;
	private boolean running=false;
	
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
	 * A kezdő kérdést veti fel
	 *
	 * @throws InterruptedException the interrupted exception
	 */
	public void startGame() throws InterruptedException{
		if(railCenter==null)
			railCenter=new RailCenter();
		controlThread.start();
	}
	
	
	/**
	 * Beolvassa a bemenetet és a megfelelő parancsra megfelelő függvényt hív.
	 */
	private void readInput() {
			while(!running){
			testOrGame();
			}
			String command=reader.nextLine();
			String[] commandpart=command.split(" ");			
			
			switch (commandpart[0]) {
			case "stop":
				controlThread.interrupt();	
				timer.cancel();
				break;
			case "change":
				change(commandpart[1]);
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
			  if(timer!=null){
			  timer.cancel();
			  running=false;
			  }
			  //TODO pálya/train neve
			  //railCenter.loadMap("NextMap");
			  //railCenter.loadTrain("NewTrain");
		  }
		  if(railCenter.getAllEmptyStatus()){
			  System.out.println("SUCCESS, YOU WON!");
			  if(timer!=null){
			  timer.cancel();
			  running=false;
			  }
			  //TODO pálya/train neve
			  //railCenter.loadMap("NextMap");
			  //railCenter.loadTrain("NewTrain");
		  }
	}
	
	private void testOrGame(){
		System.out.println("Játék vagy teszt? (1|2)");
		String line = null;
	
		line = reader.nextLine();
		
		//töröljük a meglévő teszt fileOutputját
		Game.clearOutput();
		
		//Elindítja a játékot
		if(line.equals("1")){
			startAutomataGame();
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
			int testNum = Integer.parseInt(line);
			
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
			Game.outputCompare(testNum);
		}
		
	}


	private void startAutomataGame() {
		railCenter.loadMap("2");
		controllables=railCenter.getControllables();
		railCenter.loadTrain("2-1");
		change("5");
		change("6");
		timer = new Timer();
		timer=new Timer();
		running=true;
		timer.schedule(new TimerTask() {
			  @Override
			  public void run() {
				  gameTick();
			  }
			}, 0,1000);
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
