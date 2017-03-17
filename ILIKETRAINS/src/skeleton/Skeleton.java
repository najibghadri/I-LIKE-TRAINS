package skeleton;

import java.util.Scanner;

import iliketrains.Cart;
import iliketrains.Engine;
import iliketrains.PassengerCart;
import iliketrains.RailCenter;
import iliketrains.Station;
import iliketrains.Switch;
import iliketrains.TrackComponent;
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
			case "5":
				test5();
				break;
			case "6":
				test6();
				break;
			case "3":
				write("Kilépés a játékból");
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

	private static void test5() {
		currentTest=5;
		write("Vonat váltóhoz ér");	
		RailCenter center = new RailCenter();
		Switch switchObject=new Switch();
		Engine engineObject = new Engine(center,switchObject,switchObject);	
		engineObject.move();
	}

	private static void test6() {
		currentTest=6;
		write("Vonat tunnelGate-hez ér");
		RailCenter center = new RailCenter();
		TunnelGate tunnelGateObject = new TunnelGate();
		Engine engineObject = new Engine(center,tunnelGateObject,tunnelGateObject);				
		engineObject.move();
	}
	
	private static void test7(){
		currentTest=7;
		write("Vonat állomáshoz ér");
		RailCenter center = new RailCenter();
		TrackComponent trackObject= new TrackComponent();
		Station stationObject=new Station();
		trackObject.setStation(stationObject);
		Engine engineObject = new Engine(center,trackObject,trackObject);
		PassengerCart passengerCartObject=new PassengerCart();
		engineObject.addNext(passengerCartObject);
		engineObject.move();
	}

	public static void write(String string) {
		writeIdent();
		System.out.println(string);
	}

	private static void writeIdent() {
		for(int i=0;i<tabs;i++){
			System.out.print("\t");
		}
	}

	public static boolean askIN(String string) {
		System.out.print(">");
		write(string);
		writeIdent();
		String a=reader.next();
		if(a.equals("I"))
		return true;
		else return false;
	}
	
	

	public static int getCurrentTest() {
		return currentTest;
	}

	public static void addIndent() {
		tabs++;
	}
	
	public static void removeIndent() {
		tabs--;
	}
}
