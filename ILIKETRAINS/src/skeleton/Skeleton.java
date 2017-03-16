package skeleton;

import java.util.Scanner;

import iliketrains.Engine;
import iliketrains.RailCenter;
import iliketrains.TrackComponent;
import iliketrains.TunnelGate;

public class Skeleton {
	private static int tabs=0;
	private static Scanner reader;
	private static boolean running=true;

	public static void main(String[] args){
		reader = new Scanner(System.in);
		while(running){
			write("Üss be egy számot!");
			String command=reader.next();
			switch(command){
			case "6":
				test6();
				break;
			default:
				break;
			}
		}
		reader.close();
	}

	private static void test6() {
		write("Vonat tunnelGate-hez ér");
		RailCenter center = new RailCenter();
		TrackComponent trackObject = new TrackComponent();
		Engine engineObject = new Engine(center,trackObject,trackObject);		
		
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

	public static void addIndent() {
		tabs++;
	}
	
	public static void removeIndent() {
		tabs--;
	}
}
