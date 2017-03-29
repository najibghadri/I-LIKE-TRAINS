package iliketrains;

import java.util.*;

import skeleton.Game;
import skeleton.Skeleton;

public class TrackComponent {

	private int id;
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
	
	public TrackComponent(int id){
		adjacentTracks = new ArrayList<TrackComponent>();
		this.id=id;
	}

	/**
	 * Szomszédos pályaelem hozzákapcsolása ehhez
	 * @param track A szomszédos pályaelem referenciája
	 */
	public void addAdjacentTrack(TrackComponent track) {
		adjacentTracks.add(track);
	}

	/**
	 * A következõ pályaelem lekérdezésére szolgáló függvény 
	 * (vagyis amerre tovább fogunk haladni),
	 * A paraméterként megadott pályaelem irányából közelítünk.
	 * Ha a szomszédos sínek száma 1, akkor mindenképpen azt adja vissza,
	 * különben azt, amelyik szomszéddal nem azonos a paraméter
	 * @param previous Annak a pályaelemnek a referenciája amerrõl közelítünk
	 * @return TrackComponent A következõ pályaelem referenciája
	 */
	public TrackComponent getNext(TrackComponent previous) {
		if(adjacentTracks.size()==1)
			return adjacentTracks.get(0);
		if(previous.equals(adjacentTracks.get(0)))
			return adjacentTracks.get(1);
		else
			return adjacentTracks.get(0);
	}

	/**
	 * A paraméterként megadott kocsi ráhelyezése a pályaelemre
	 * @param cart Annak a kocsinak a referenciája amit erre a pályaelemre kívánunk helyezni
	 */
	public void putCart(Cart cart) {
		this.cart=cart;
	}

	/**
	 * A pályaelemen lévõ kocsi eltávolítása
	 */
	public void removeCart() {
		this.cart=null;
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
		return station;
	}

	/**
	 * Állomás hozzárendelése ehhez a pályaelemhez
	 * @param station A hozzárendelendõ állomás referenciája
	 */
	public void setStation(Station station) {
		this.station = station;
	}
	
	/**
	 * Visszaadja a típusát a konzolra íratáshoz
	 * @return
	 */
	public String getType(){
		return "TrackComponent";
	}
	
	/**
	 * A konzolra a pálya kiíráshoz szükséges függvény 
	 */
	public void print(){
		if(station!=null)
			Game.log(getType()+" "+id+": "+"\n"+"    "+station.print());
		else 
			Game.log(getType()+" "+id);
		
		//TODO - szomszédok printeltetése
	}

	/**
	 * A paraméterül kapott sínnel hasonlítja magát. Ha az id, vagy a referencia egyezik, akkor 
	 * igaz értékkel tér vissza, különben hamissal
	 * @param obj A másik sín referenciája
	 * &return boolean visszatérés igazzal ha azonosak
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrackComponent other = (TrackComponent) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}