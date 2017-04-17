package iliketrains;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import skeleton.Game;

/**
 * A RailCentert megvalósító osztály.
 */
public class RailCenter {

	/**  A mozdonyok listája. */
	private List<Engine> engines;
	
	/**  A kapcsolható pályaelemek listája. */
	private List<Controllable> controllableList;
	
	/**  Az állomások listája. */
	private List<Station> stations;
	
	/**  A belépési pontok listája. */
	private List<EntryPoint> entryPoints;
	
	/**  Az ütközés tároló attributum. */
	private boolean collided=false;
	
	/** A legnagyobb pályaelem azonosító sorszáma */
	private static int highestTrackId=1;
	
	/** A legnagyobb vonatkocsi azonosító sorszáma */
	private int highestCartId=1;

	/**
	 * Konstruktor
	 * Memóriaterületet foglal a listáknak.
	 */
	public RailCenter() {
		controllableList=new ArrayList<Controllable>();
		engines=new ArrayList<Engine>();
		entryPoints=new ArrayList<EntryPoint>();
		stations=new ArrayList<Station>();
	}
	
	
	/**
	 * Visszaadja a állítható elemek listáját
	 *
	 * @return List<Controllable> visszatér a listával
	 */
	public List<Controllable> getControllables(){
		return controllableList;		
	}

	/**
	 * Az Engineket (mozdony), és ezáltal a vonatokat mozgató fv.
	 */
	public void moveEngines() {
		for(Engine e : engines){
			e.move();
		}
		for(Engine e : engines){
            e.checkCollison();
        }
	}
	
	/**
	 * Ellenőrzi, hogy minden vonat és állomás kiürült-e.
	 * @return the all empty status
	 */
	public boolean getAllEmptyStatus(){
		boolean emptyness = true;
		//állomások ellenőrzése
		for(Station s : stations){
			if(s.getPassengers() || !emptyness){
				emptyness = false;
				break;
			}
		}
		//vonatok ellenőrzése
		for(int i=0;i<engines.size();i++){
			if(!engines.get(i).checkEmpty())
				emptyness=false;
		}
//		for(Engine e : engines){
//			if(e.checkEmpty()){
//				emptyness = false;
//				}
//		}
		return emptyness;
	}
	
	/**
	 * Ellenőrzi, hogy volt-e ütközés.
	 * @return collided attribútummal tér vissza
	 */
	public boolean getAnyCollided(){
		return collided;
	}
	
	/**
	 * A vonatkoktól kéri hogy ellenőrizzék ütköztek-e.
	 */
	public void checkAllCollision(){
		for(Engine e : engines){
			e.checkCollison();
		}
	}

	/**
	 * Ütközés megtörténtének jelzésére szolgáló függvény.
	 */
	public void reportCollided() {
		collided = true;
	}
	
	/**
	 * A paraméter egy fájl neve, a megfelelő térképet tölti be.
	 * Létrehozza az összes objektumot és a listákba elhelyezi.
	 * Az előző pálya elemeit (ha volt) törli
	 * @param name the name
	 */
	public void loadMap(String name){
		highestCartId=1;
		highestTrackId=1;
		controllableList.clear();
		engines.clear();
		entryPoints.clear();
		stations.clear();		
		
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

			//Set the highest track ID to the TC generating tunnel
			Tunnel.setFirstID(highestTrackId);
			Game.log("loadmap "+name+" success");
		} catch (IOException e) {			
			e.printStackTrace();
			Game.log("loadmap "+name+" failed");
		} finally {			
			try {				
				if (br != null)
					br.close();				
				if (fr != null)
					fr.close();
			} catch (IOException ex) {				
				ex.printStackTrace();				
			}
		}
	}

	/**
	 * Létrehozza a kapcsolatokat az egyes elemek között
	 *
	 * @param list A pályaelem lista
	 * @param currentLine Az aktuálisan beolvasott kapcsolatot tartalmazó input string
	 */
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


	/**
	 * Létrehozza a váltókat
	 *
	 * @param list Pályaelem lista
	 * @param currentLine Az aktuálisan beolvasott váltó adatait tartalmazó input string
	 */
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


	/**
	 * Létrehozza a TrackComponenteket
	 *
	 * @param list Pályaelem lista 
	 * @param currentLine Az aktuálisan beolvasott pályaelem adatait tartalmazó input string
	 */
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
	
	/**
	 * Létrehozza a kereszteződéseket
	 *
	 * @param list Pályaelem lista 
	 * @param currentLine Az aktuálisan beolvasott kereszteződés adatait tartalmazó input string
	 */
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
	
	/**
	 * Létrehozza a belépési pontokat
	 *
	 * @param list Pályaelem lista 
	 * @param currentLine Az aktuálisan beolvasott belépési pont adatait tartalmazó input string
	 */
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
	
	/**
	 * Létrehozza a alagútszájakat
	 *
	 * @param list Pályaelem lista 
	 * @param currentLine Az aktuálisan beolvasott alagútszáj adatait tartalmazó input string
	 */
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

	/**
	 * Generate filename.
	 *
	 * @param name the name
	 * @return the string
	 */
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
	 * majd felhelyezi a kezdősínekre, és az Engines listába.
	 *
	 * @param name the name
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
					if(entry.getId()==Integer.parseInt(trainPart[0])){
						e=entry;
					}
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
			Game.log("loadtrain "+name+" success");
		} catch (IOException e) {			
			e.printStackTrace();
			Game.log("loadmap "+name+" failed");
		} finally {			
			try {				
				if (br != null)
					br.close();				
				if (fr != null)
					fr.close();
				
			} catch (IOException ex) {				
				ex.printStackTrace();				
			}
		}
	}
	
	/**
	 * Kiírja a vonatok állapotát a log outputjaira
	 */
	public void printStatus(){
        for(Engine e : engines){
        	if(e.currentTrack.getNext(e.getPrevious()) == null)
        		Game.log("next-> "+e.currentTrack.getNext(e.getPrevious()));
        	else
        		Game.log("next-> "+e.currentTrack.getNext(e.getPrevious()).getType()+
        				" ("+e.currentTrack.getNext(e.getPrevious()).getId()+")");
            Cart temp = e;
            do{
                if(temp.currentTrack == null)
                    Game.log("| "+getPrintStyleName(temp)+" |@ null");
                else
                    Game.log("| "+getPrintStyleName(temp)+" |@ "+temp.currentTrack.getType()+ " ("+temp.currentTrack.getId()+")");
                temp = temp.getNext();
            }while(temp != null);
        }
    }
	
	/**
	 * A printStatus függvényhez szükséges formátumú kocsi nevekkel tér vissza
	 * @param c Kívánt kocsi referneciája
	 * @return Jól formázott név
	 */
	private String getPrintStyleName(Cart c){
		String str = "";
		if(c.getType() == "Engine" ){
			str += "ENGI:"+c.getId();
		}
		else if(c.getType() == "CoalCart"){
			str += "COAL  ";
		}
		else if(c.getType() == "Cart"){
			PassengerCart pc = (PassengerCart) c;
			str += getPrintStyleColor(pc.getColor())+":"+getPrintStyleBool(pc.isNotEmpty());
		}
		//TODO: van asszem erre valami beépített java fgv, most nem találtam
		//mindenestere, ha nincs meg hat karakter, akkor adunk még hozz whitespace-eket
		for(int i = str.length(); i<=6;i++){
			str += " ";
		}
		
		return str;
	}
	
	/**
	 * A printStatus függvényhez szükséges színszüvegformátummal tér vissza
	 * @param c Kívánt szín
	 * @return Jól formázott szín string
	 */
	private String getPrintStyleColor(Color c){
		if(c.equals(Color.Red))
			return "RED";
		else if(c.equals(Color.Blue))
			return "BLUE";
		else if(c.equals(Color.Green))
			return "GREN";
		else if(c.equals(Color.Yellow))
			return "YLLW";
		else /*if(c.equals(Color.Brown))*/
			return "BRWN";
	}
	
	/**
	 * A print függvényhez szükséges formátumra alakítja a boolt
	 * @param c Kívánt bool
	 * @return Átalakított formátum
	 */
	private int getPrintStyleBool(boolean c){
		if(c)
			return 1;
		else
			return 0;
	}

}