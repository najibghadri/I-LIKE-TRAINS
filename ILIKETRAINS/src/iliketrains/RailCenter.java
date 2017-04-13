package iliketrains;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import skeleton.Game;
import skeleton.Skeleton;

public class RailCenter {

	private List<Engine> engines;
	private List<Controllable> controllableList;
	private List<Station> stations;
	private List<EntryPoint> entryPoints;
	private boolean collided=false;
	private static int highestTrackId=1;
	private int highestCartId=1;

	/**
	 * konstruktor
	 */
	public RailCenter() {
		controllableList=new ArrayList<Controllable>();
		engines=new ArrayList<Engine>();
		entryPoints=new ArrayList<EntryPoint>();
		stations=new ArrayList<Station>();
	}
	
	
	/**
	 * @return List<Controllable> visszatér a listával
	 */
	public List<Controllable> getControllables(){
		return controllableList;		
	}

	/**
	 * Az Engineket (mozdony), és ezáltal a vonatokat mozgató fv.
	 */
	public void moveEngines() {
		// TODO - implement RailCenter.moveEngines
	}
	
	/**
	 * Ellenőrzi, hogy minden vonat és állomás kiürült-e
	 */
	public boolean getAllEmptyStatus(){
		//TODO - implement 
		return false;
	}
	
	/**
	 * Ellenőrzi, hogy volt-e ütközés
	 * @return collided attribútummal tér vissza
	 */
	public boolean getAnyCollided(){
		return collided;
	}
	
	/**
	 * A vonatkoktól kéri hogy ellenőrizzék ütköztek-e
	 */
	public void checkAllCollision(){
		//TODO - implement
	}

	/**
	 * Ütközés megtörténtének jelzésére szolgáló függvény
	 */
	public void reportCollided() {
		Skeleton.addIndent();
		Skeleton.write("RailCenter.reportCollided() returns");
		Skeleton.removeIndent();
	}
	
	/**
	 * A paraméter egy fájl neve, a megfelelő térképet tölti be.
	 * Létrehozza az összes objektumot és a listákba elhelyezi.
	 * @param name
	 */
	public void loadMap(String name){
		String FILENAME = generateFilename(name);		
		BufferedReader br = null;
		FileReader fr = null;
		List<TrackComponent> list=new ArrayList<TrackComponent>();
		
		try {
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);
			
			String currentLine;
			String currentPhase="";
			
			br = new BufferedReader(new FileReader(FILENAME));
			
			while ((currentLine = br.readLine()) != null) {
				if(currentLine.startsWith("ch")){
					currentPhase=currentLine.split(" ")[1];
				}else{
				switch (currentPhase) {
				case "trackcomponent":
					createTrackComponent(list, currentLine);
					break;
				case "cross":
					createCross(list, currentLine);
					break;
				case "switch":
					createSwitch(list, currentLine);
					break;
				case "entrypoint":
					createEntryPoint(list,currentLine);
					break;
				case "tunnelgate":
					createTunnelGate(list,currentLine);
					break;
				case "connections":
					createConnections(list,currentLine);
					break;
				default:
					break;
				}
				}
			}			
		} catch (IOException e) {			
			e.printStackTrace();			
		} finally {			
			try {				
				if (br != null)
					br.close();				
				if (fr != null)
					fr.close();
				Game.log("loadmap "+name+" success");
			} catch (IOException ex) {				
				ex.printStackTrace();				
			}
		}
	}

	private void createConnections(List<TrackComponent> list, String currentLine) {
		String[] connectionParts=currentLine.split(",");
		int[] connectionPartNumbers = null;
		
		//Kiválogatja, hogy a null-okból ne próbáljon majd integert csinálni, létrehozza az int tömböt
		for(int i=0;i<connectionParts.length;i++){
			if(connectionParts[i].equals("null")){
				connectionPartNumbers=new int[i];
				break;
			}
			if(i==connectionParts.length-1)
				connectionPartNumbers=new int[i+1];			//Utolsó sem null, minden érték szám
		}	
		
		//Az int tömbbe átmásolja és beparszolja az értékeket
		for(int i=0;i<connectionPartNumbers.length;i++){
			connectionPartNumbers[i]=Integer.parseInt(connectionParts[i]);
		}
		
		//Hozzáadja a sínhez a szomszédait
		for(int i=1;i<connectionPartNumbers.length;i++){
			list.get(connectionPartNumbers[0]-1).addAdjacentTrack(list.get(connectionPartNumbers[i]-1));
		}
	}


	private void createSwitch(List<TrackComponent> list, String currentLine) {
		String[] commandParts=currentLine.split(",");
		Switch sw=new Switch(highestTrackId);
		if(commandParts[0].equals("1")){
			Station s=new Station(highestTrackId,commandParts[1],commandParts[2]);
			sw.setStation(s);
		}
		list.add(sw);
		controllableList.add(sw);
		highestTrackId++;
	}


	private void createTrackComponent(List<TrackComponent> list,
			String currentLine) {
		TrackComponent t=new TrackComponent(highestTrackId);
		String[] station=currentLine.split(",");
		if(station[0].equals("1")){
			Station s=new Station(highestTrackId,station[1],station[2]);
			t.setStation(s);
		}
		list.add(t);
		highestTrackId++;
	}
	
	private void createCross(List<TrackComponent> list,
			String currentLine) {
		Cross t=new Cross(highestTrackId);
		String[] station=currentLine.split(",");
		if(station[0].equals("1")){
			Station s=new Station(highestTrackId,station[1],station[2]);
			t.setStation(s);
		}
		list.add(t);
		highestTrackId++;
	}
	
	private void createEntryPoint(List<TrackComponent> list,
			String currentLine) {
		EntryPoint t=new EntryPoint(highestTrackId);
		String[] station=currentLine.split(",");
		if(station[0].equals("1")){
			Station s=new Station(highestTrackId,station[1],station[2]);
			t.setStation(s);
		}
		list.add(t);
		entryPoints.add(t);
		highestTrackId++;
	}
	
	private void createTunnelGate(List<TrackComponent> list,
			String currentLine) {
		TunnelGate t=new TunnelGate(highestTrackId);
		String[] station=currentLine.split(",");
		if(station[0].equals("1")){
			Station s=new Station(highestTrackId,station[1],station[2]);
			t.setStation(s);
		}
		list.add(t);
		controllableList.add(t);
		highestTrackId++;
	}

	//Fájlnévhez hozzáfűzi a jelenlegi elérési utat és a mappa nevét
	private String generateFilename(String name) {
		String FILENAME =System.getProperty("user.dir");
		if(name.contains(".txt")){
			FILENAME=FILENAME+"\\res\\"+name;
		}
		else
			FILENAME=FILENAME+"\\res\\"+name+".txt";
		return FILENAME;
	}
	
	/**
	 * A vonatfájlt tölti be, és hozza létre őket, 
	 * majd felhelyezi a kezdősínekre, és az Engines listába
	 * @param name
	 */
	public void loadTrain(String name){
		String FILENAME = generateFilename(name);		
		BufferedReader br = null;
		FileReader fr = null;
		
		try {
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);
			
			String currentLine;
			
			br = new BufferedReader(new FileReader(FILENAME));
			
			while ((currentLine = br.readLine()) != null) {
				List<Cart> carts=new ArrayList<Cart>();
				List<PassengerCart> passenger=new ArrayList<PassengerCart>();
				
				//Szétdarabolni a kocsilistát
				String[] trainPart=currentLine.split(",");
				EntryPoint e = null;
				
				//Megkeresi azt az EntryPointot ahova a mozdony kerül
				for(EntryPoint entry : entryPoints ){
					if(entry.getId()==Integer.parseInt(trainPart[0]))
						e=entry;
				}
				
				//Létrehoz mozdonyt, listákhoz adja
				Engine engine=new Engine(highestCartId,this,e, null);
				carts.add(engine);
				engines.add(engine);
				highestCartId++;
				
				//Minden kocsidarabon végigmegy, létrehozza, listába helyezi
				for(int i=1;i<trainPart.length;i++){
					//Ha szeneskocsi
					if(trainPart[i].equals("0")){
						CoalCart c=new CoalCart(highestCartId);
						carts.add(c);
						carts.get(i-1).setNextCart(c);
					}
					//Ha nem szenes, akkor utas
					else{
						String details[]=trainPart[i].split(":");	//Ennek további adatai vannak
						PassengerCart p;
							p=new PassengerCart(highestCartId,details[0],details[1]);	//Szín és utasszám paraméter
						if(passenger.size()==0)							//Ha üres lista->első utaskocsi,
							engine.addNext(p);							//ezért mozdonyhoz adom
						else
							passenger.get(passenger.size()-1).addNext(p);				//Különben az előző utaskocsihoz
						
						passenger.add(p);
						carts.add(p);
						carts.get(i-1).setNextCart(p);
					}
					highestCartId++;
				}
			}			
		} catch (IOException e) {			
			e.printStackTrace();			
		} finally {			
			try {				
				if (br != null)
					br.close();				
				if (fr != null)
					fr.close();
				Game.log("loadtrain "+name+" success");
			} catch (IOException ex) {				
				ex.printStackTrace();				
			}
		}
	}

	public static int getHighestTrackId() {
		return highestTrackId;
	}

}