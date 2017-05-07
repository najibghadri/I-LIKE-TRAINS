package iliketrains;

import java.util.*;

/**
 * TrackComponent
 * Álalános sín elem
 */
public class TrackComponent {

	/** Sínelem azonosítója */
	private int id;
	
	/** Rajta lévő állomás referenciája */
	private Station station;

    /** Rajta lévő vonatkocsik listája */
    protected List<Cart> carts;
	
	/** Hozzákötődő sínelemek listája */
	protected List<TrackComponent> adjacentTracks;

    /**
     * Konstructor
     * Létrehoz egy sínt azonosítóval.
     * @param id Sínelem azonosítója
     */
    public TrackComponent(int id){
		adjacentTracks = new ArrayList<TrackComponent>();
		carts=new ArrayList<Cart>();
		this.id=id;
	}

	/**
	 * Szomszédos pályaelem hozzákapcsolása ehhez a sínhez
	 * @param track A szomszédos pályaelem referenciája
	 */
	public void addAdjacentTrack(TrackComponent track) {
		adjacentTracks.add(track);
	}

    /**
     * Megszűnteti az a sínhez való csatlakozást
     * @param tunnelTrack A szomszédos pályaelem referenciája
     */
    public void removeAdjacentTrack(TrackComponent tunnelTrack) {
        adjacentTracks.remove(tunnelTrack);
    }

	/**
	 * A következõ pályaelem lekérdezésére szolgáló függvény 
	 * (vagyis amerre tovább fogunk haladni),
	 * A paraméterként megadott pályaelem irányából közelítünk.
	 * Ha a szomszédos sínek száma 1, akkor null-t ad vissza mert zsákutca,
	 * különben azt, amelyik szomszéddal nem azonos a paraméter
	 * @param previous Annak a pályaelemnek a referenciája amerrõl közelítünk
	 * @return TrackComponent A következõ szomszédos pályaelem referenciája
	 */
	public TrackComponent getNext(TrackComponent previous) {
	    if(adjacentTracks.size()==0)
          return null;
		if(adjacentTracks.size()==2){
            if(previous.equals(adjacentTracks.get(0)))
                return adjacentTracks.get(1);
            else
                return adjacentTracks.get(0);
        }
		else
            return null;
	}

	public int getNextDir(TrackComponent previous){
		if(previous==null)
			return 0;

		return adjacentTracks.indexOf(previous);
	}

	/**
	 * A paraméterként megadott kocsi ráhelyezése a pályaelemre
	 * @param cart Annak a kocsinak a referenciája amit erre a pályaelemre kívánunk helyezni
	 */
	public void putCart(Cart cart) {
		carts.add(cart);
	}

	/**
	 * A pályaelemen lévõ kocsi eltávolítása
	 */
	public void removeCart(Cart cart) {
		carts.remove(cart);
	}

	/**
	 * A pályaelemen lévõ kocsi referenciájának lekérdezése
	 * @return Cart A pályaelemen lévõ kocsi referenciája
	 */
	public List<Cart> getCarts() {
		return carts;
	}

	/**
	 * Annak a lekérdezése, hogy ehhez a pályaelemhez tartozik-e állomás
	 * @return Station Az állomás referenciája (ha van)
	 */
	public Station hasStation() {
		return station;
	}

	/**
	 * Állomás beállítása ehhez a pályaelemhez
	 * @param station A hozzárendelendõ állomás referenciája
	 */
	public void setStation(Station station) {
		this.station = station;
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
        TrackComponent other = (TrackComponent) obj;
        if (id != other.id)
            return false;
        return true;
    }

    /**
     * Saját típusát sztringben
     * @return "TrackComponent"
     */
    public String getType(){
        return "TrackComponent";
    }

    /**
     * @return Visszatér a sínelem azonsítójával
     */
    public int getId() {
        return id;
    }
}