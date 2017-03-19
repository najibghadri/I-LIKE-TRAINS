package iliketrains;

import java.util.*;

import skeleton.Skeleton;

public class TrackComponent {

	Cart cart;
	Station station;
	protected List<TrackComponent> adjacentTracks;

	/**
	 * konstruktor
	 */
	public TrackComponent() {
		adjacentTracks = new ArrayList<TrackComponent>();
		Skeleton.write("TrackComponent constructor");
	}

	/**
	 * Szomszédos pályaelem hozzákapcsolása ehhez
	 * @param track A szomszédos pályaelem referenciája
	 */
	public void addAdjacentTrack(TrackComponent track) {
		// TODO - implement TrackComponent.addAdjacentTrack
	}

	/**
	 * A következõ pályaelem lekérdezésére szolgáló függvény 
	 * (vagyis amerre tovább fogunk haladni),
	 * amennyiben a paraméterként megadott pályaelem irányából közelítünk
	 * @param previous Annak a pályaelemnek a referenciája amerrõl közelítünk
	 * @return TrackComponent A következõ pályaelem referenciája
	 */
	public TrackComponent getNext(TrackComponent previous) {
		return this;
	}

	/**
	 * A paraméterként megadott kocsi ráhelyezése a pályaelemre
	 * @param cart Annak a kocsinak a referenciája amit erre a pályaelemre kívánunk helyezni
	 */
	public void putCart(Cart cart) {
		Skeleton.addIndent();
		Skeleton.write("TrackComponent.putCart returns");
		Skeleton.removeIndent();
	}

	/**
	 * A pályaelemen lévõ kocsi eltávolítása
	 */
	public void removeCart() {
		Skeleton.addIndent();
		Skeleton.write("TrackComponent.removeCart returns");
		Skeleton.removeIndent();
	}

	/**
	 * A pályaelemen lévõ kocsi referenciájának lekérdezése
	 * @return Cart A pályaelemen lévõ kocsi referenciája
	 */
	public Cart getCart() {
		return this.cart;
	}

	/**
	 * Annak a lekérdezése, hogy ehhez a pályaelemhez tartozik-e állomás
	 * @return Station Az állomás referenciája (ha van)
	 */
	public Station hasStation() {
		Skeleton.addIndent();
		boolean stationExist = Skeleton.askIN("Van állomás?");
		if (stationExist){
			Skeleton.write("TrackComponent.hasStation returns with station");
			Skeleton.removeIndent();
			return station;
		}
		else{
			Skeleton.write("TrackComponent.hasStation returns with null");	
			Skeleton.removeIndent();
			return null;
		}

	}

	/**
	 * Állomás hozzárendelése ehhez a pályaelemhez
	 * @param station A hozzárendelendõ állomás referenciája
	 */
	public void setStation(Station station) {
		this.station = station;
	}

}