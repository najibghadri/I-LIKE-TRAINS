package skeleton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import iliketrains.Controllable;
import iliketrains.RailCenter;

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
				testOrPlay();
			}
			String command=reader.nextLine();
			String[] commandParts=command.split(" ");
			
			switch (commandParts[0]) {
			case "stop":
				controlThread.interrupt();	
				timer.cancel();
				break;
			case "change":
				change(commandParts[1]);
				break;
			case "print":
				railCenter.printStatus();
				break;
			default:
				break;
			}
	}
	
	/**
	 * Játékban működő időzítőt valósítja meg
	 */
	private void gameManualTick() {
		railCenter.moveEngines();
		
	      if(railCenter.getAnyCollided()){
			  System.out.println("GAME OVER, YOU LOST!");
		  }
		  if(railCenter.getAllEmptyStatus()){
			  System.out.println("SUCCESS, YOU WON!");
		  }
	}
	
	private void gameAutoTick() {
		railCenter.moveEngines();
		
	      if(railCenter.getAnyCollided()){
			  System.out.println("GAME OVER, YOU LOST!");
			  if(timer!=null){
			  timer.cancel();
			  running=false;
			  System.out.println("Nyomj ENTER-t!");
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
	
	/**
	 * Főmenü függvénye
	 */
	private void testOrPlay(){
		System.out.println("Játék vagy teszt? (1|2)");
		String line = reader.nextLine();

		//töröljük a meglévő teszt fileOutputját
		Game.clearOutput();
		
		//Elindítja a játékot
		if(line.equals("1")){
			startAutomataGame();
		}

		//Ha nem indítunk, akkor tesztfájlt választunk
		else{			
			System.out.println("Válassz 1-33-ig:");
			
			line = reader.nextLine();
			int testNum = Integer.parseInt(line);
			
			File file = null;
			String filename="";
			filename=Game.generateFilename("test_input_"+line+".txt");
			System.out.println(filename);
			file = new File(filename);

			//Beolvassa a tesztparancsokat
			BufferedReader reader = null;
			ArrayList<String> commands = new ArrayList<String>();
			try {
				reader = new BufferedReader(new FileReader(file));
				String text = null;
				while ((text = reader.readLine()) != null) {
					commands.add(text);
				}
			} catch (Exception e) {
	
			} finally {
				if (reader != null) {
						try {
							reader.close();
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
						railCenter = new RailCenter();
						railCenter.loadMap(commandpart[1]);
						controllables=railCenter.getControllables();
						break;
					case "loadtrain":
						railCenter.loadTrain(commandpart[1]);
						break;
					case "moveengines":
						gameManualTick();
					default:
						break;
				}
			}
			Game.outputCompare(testNum);
		}
	}


	/**
	 * Real-Time működő játékot megvalósítő függvény
	 */
	private void startAutomataGame() {
		railCenter.loadMap("1");
		controllables=railCenter.getControllables();
		railCenter.loadTrain("1-1");
		timer = new Timer();
		timer=new Timer();
		running=true;
		timer.schedule(new TimerTask() {
			  @Override
			  public void run() {
				  gameAutoTick();
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
