package iliketrains;

import java.util.*;

import skeleton.Skeleton;

public class RailCenter {

	Collection<Engine> engines;
	private Boolean collided;
	private int numberOfArrived;
	private int entryPoints;

	/**
	 * konstruktor
	 */
	public RailCenter() {
		Skeleton.write("RailCenter konstruktor");
		
	}

	/**
	 * Az Engineket (mozdony), és ezáltal a vonatokat mozgató fv.
	 */
	public void moveEngines() {
		// TODO - implement RailCenter.moveEngines
	}

	/**
	 * Azt ellenõrzi, hogy minden vonat megérkezett-e (vagyis leszállították az összes utast).
	 * Amennyiben igen, úgy a játékos nyert.
	 */
	public void reportArrived() {
		Skeleton.addIndent();
		boolean allTrains=Skeleton.askIN("Minden vonat megérkezett?");
		if(allTrains){
			Skeleton.write("NYERT!");
		}
		Skeleton.write("RailCenter.reportArrived() returns");
		Skeleton.removeIndent();
	}

	/**
	 * Ütközés megtörténtének jelzésére szolgáló függvény
	 */
	public void reportCollided() {
		Skeleton.addIndent();
		Skeleton.write("RailCenter.reportCollided() returns");
		Skeleton.removeIndent();
	}

}