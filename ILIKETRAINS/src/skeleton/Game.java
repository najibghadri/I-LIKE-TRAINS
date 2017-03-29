package skeleton;

import iliketrains.Color;
import iliketrains.RailCenter;
import iliketrains.Station;
import iliketrains.TrackComponent;

public class Game {
	
	public static void main(String[] args){
		TrackComponent t=new TrackComponent(1);
		Station s=new Station(new Color(),true);
		t.setStation(s);
		t.print();
	}
	
	public static void log(String s){
		System.out.println(s);
	}
}
