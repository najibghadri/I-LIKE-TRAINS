package skeleton;

import java.util.Scanner;

import iliketrains.Cart;
import iliketrains.Engine;
import iliketrains.PassengerCart;
import iliketrains.RailCenter;
import iliketrains.Station;
import iliketrains.Switch;
import iliketrains.TrackComponent;
import iliketrains.Tunnel;
import iliketrains.TunnelGate;



public class Skeleton {
	private static int tabs=0;
	private static Scanner reader;
	private static boolean running=true;
	private static int currentTest;

	public static void main(String[] args){
		reader = new Scanner(System.in);
		while(running){
			write("Üss be egy számot!");
			String command=reader.next();
			switch(command){
			case "1":
				test1();
				break;
			case "2":
				test2();
				break;
			case "3":	/*Ez az egyetlen teszteset ami egyszerûségébõl fakadóan nincs külön függvényben implementálva*/
				write("Kilépés a játékból");
				break;
			case "4":
				test4();
				break;
			case "5":
				test5();
				break;
			case "6":
				test6();
				break;
			case "7":
				test7();
				break;
			default:
				break;
			}
		}
		reader.close();
	}
	
	/**
	 * 1. tesztesetet megvalósító függvény
	 * Ez a teszteset a Switch (váltó) átállításának folyamatát szemlélteti
	 */
	private static void test1(){
		currentTest=1;
		write("Váltót állít");
		RailCenter center = new RailCenter();
		Switch switchObject = new Switch(0,"0");
		TrackComponent trackObj2= new TrackComponent();
		TrackComponent trackObj3= new TrackComponent();
		switchObject.change();
	}
	
	/**
	 * 2. tesztesetet megvalósító függvény
	 * Ez a teszteset a TunnelGate (alagútszáj) aktiválásának folyamatát szemlélteti
	 */
	private static void test2(){
		currentTest=2;
		write("TunnelGate-t állít");
		RailCenter center = new RailCenter();
		Tunnel tunnelObject = Tunnel.getInstance();
		TunnelGate tunnelGateObject = new TunnelGate(0);
		tunnelGateObject.change();
	}
	
	/**
	 * 4. tesztesetet megvalósító függvény
	 * Ez a teszteset a vonat a következõ pályaelemre való lépésének folyamatát szemlélteti
	 */
	private static void test4(){
		currentTest=4;
		write("Vonat következõ pályaelemre lép");
		RailCenter center = new RailCenter();
		TrackComponent trackObject= new TrackComponent();
		Engine engineObject = new Engine(1,center, trackObject, trackObject);
		engineObject.move();
	}


	/**
	 * 5. tesztesetet megvalósító függvény
	 * Ez a teszteset azt szemlélteti, ahogy a vonat egy Switch-hez (váltó) ér, és azon áthalad
	 */
	private static void test5() {
		currentTest=5;
		write("Vonat váltóhoz ér");	
		RailCenter center = new RailCenter();
		Switch switchObject=new Switch(0,"0");
		Engine engineObject = new Engine(1,center,switchObject,switchObject);	
		engineObject.move();
	}

	/**
	 * 6. tesztesetet megvalósító függvény
	 * Ez a teszteset azt szemlélteti amint a vonat egy TunnelGate-hez (alagútszáj) ér, 
	 * és azon áthalad (nem feltétlenül megy be!)
	 */
	private static void test6() {
		currentTest=6;
		write("Vonat tunnelGate-hez ér");
		RailCenter center = new RailCenter();
		TunnelGate tunnelGateObject = new TunnelGate();
		Engine engineObject = new Engine(1,center,tunnelGateObject,tunnelGateObject);				
		engineObject.move();
	}
	
	/**
	 * 7. tesztesetet megvalósító függvény
	 * Ez a teszteset szemlélteti azoknak a játéklogikai lépéseknek a végrehajtását, 
	 * amikor a vonat egy állomáshoz ér
	 */
	private static void test7(){
		currentTest=7;
		write("Vonat állomáshoz ér");
		RailCenter center = new RailCenter();
		TrackComponent trackObject= new TrackComponent();
		Station stationObject=new Station();
		trackObject.setStation(stationObject);
		Engine engineObject = new Engine(1,center,trackObject,trackObject);
		PassengerCart passengerCartObject=new PassengerCart(1,"","");
		engineObject.addNext(passengerCartObject);
		engineObject.move();
	}

	/**
	 * Kiírófüggvény ami a jobb áttekinthetõség érdekében a bekezdés számnak megfelelõ 
	 * tabulátor után írja ki a paraméteréül kapott stringet
	 * @param string Kiírandó szöveg
	 */
	public static void write(String string) {
		writeIdent();
		System.out.println(string);
	}

	/**
	 * A bekezdés számának megfelelõ tabulátor kiíratása az áttekinthetõség érdekében
	 */
	private static void writeIdent() {
		for(int i=0;i<tabs;i++){
			System.out.print("\t");
		}
	}

	/**
	 * A tesztelõ felhasználóval való kommunikációt megvalósító függvény,
	 * a paraméterként kapott eldöntendõ kérdésekre vár választ
	 * @param string Ez a string egy kérdés a tesztelõ felhasználó felé, a standard kimenetre íródik ki
	 * @return boolean A tesztelõ felhasználó válasza a feltett kérdésre
	 */
	public static boolean askIN(String string) {
		System.out.print(">");
		write(string);
		writeIdent();
		while(true){
			String a=reader.next();
			if(a.equals("I") || a.equals("i"))
				return true;
			else if(a.equals("N") || a.equals("n"))
				return false;
			//EXIT feature
			else if(a.equals("e"))
				System.exit(-1);
			else{
				System.out.print(">");
				write("Nem megfelelõ bemenet! (I/N) Újra: ");
				writeIdent();
				}
		}
	}
	
	

	/**
	 * @return int Az éppen futó teszteset száma
	 */
	public static int getCurrentTest() {
		return currentTest;
	}

	/**
	 * A bekezdés számot növeli egyel
	 */
	public static void addIndent() {
		tabs++;
	}
	
	/**
	 * A bekezdés számot csökkenti egyel
	 */
	public static void removeIndent() {
		tabs--;
	}
}
