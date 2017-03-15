package skeleton;

import iliketrains.RailCenter;

import java.io.IOException;
import java.util.Scanner;

public class Skeleton {
	private static int tabs=0;
	
	public static void main(String[] args){
		testCase6();
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
		write(string);
		Scanner reader = new Scanner(System.in);
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
