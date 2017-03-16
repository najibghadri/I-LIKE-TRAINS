package skeleton;

import java.util.Scanner;

import iliketrains.RailCenter;

public class Skeleton {
	private static int tabs=0;
	private static Scanner reader = new Scanner(System.in);
	private static boolean running=true;

	
	public static void main(String[] args){
		while(running){
			write("Üss be egy számot!");
			String command=reader.next();
			switch(command){
			case "6":
				testCase6();
				break;
			default:
				break;
			}
		}
	}

	private static void testCase6() {
		RailCenter center=new RailCenter();
		center.startTest6();
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
