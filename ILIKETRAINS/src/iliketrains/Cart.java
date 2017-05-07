package iliketrains;

/**
 * A vonatkocsi ősosztály
 * Nem absztrakt, mert a származtatott osztályok közös része itt kerül megvalósításra
 */
public class Cart {

	/** A sín elem, amin aktuálisan tartózkodik */
	protected TrackComponent currentTrack;

	/**
	 * Visszaadja az előző sín referenciáját
	 * @return Előző sín pozíció
	 */
	public TrackComponent getPrevious() {
		return previous;
	}

	/**  A korábbi sínelem referneciáját tárolja az előrehaladás miatt. */
	protected TrackComponent previous;
	
	/** Vonatkocsi azonosítója */
	private int id;
	
	/** A következő kocsi referenciája */
	private Cart next;
	
	/**
	 * Konstruktor, eltárolja a paraméterben kapott azonosítót
	 * @param id Vonatkocsi azonosítója
	 */
	protected Cart(int id){
		this.id=id;
	}
	
	/**
	 * Azonosító szám lekérdezésére szolgáló függvény
	 * @return id
	 */
	public final int getId(){
		return id;
	}
	
	/**
     * Saját típusát sztringben
     * @return "string"
     */
    public String getType(){
        return "Cart";
    }
    
    /**
     * Visszaadja a következő kocsi referenciáját
     * @return Következő kocsi referenciája
     */
    public Cart getNextCart(){
    	return next;
    }



	/**
	 * A vonatkocsit mozgató függvény, a paraméteréül kapott objektumra mozgat
	 * @param whereTo Pályaelem amire a kocsit mozgatni kívánjuk
	 */
	public void moveCart(TrackComponent whereTo) {
		if(whereTo==null){
			return;
		}

		//Előző beállítása
		TrackComponent newPrev=currentTrack;

		//Ha ez még pályán kívül volt akkor a következőt nem próbálja üres helyre rakni
		if(currentTrack==null){
			whereTo.putCart(this);		
			currentTrack=whereTo;
			previous = newPrev;
			return;
		}

		//mozgás maga
		currentTrack.removeCart(this);
		whereTo.putCart(this);

		if(next!=null)	//ha nem utolsó kocsiról van szó
			next.moveCart(currentTrack);	//következő kocsi mozgatása

		//mozgás véglegesítésa
		currentTrack=whereTo;
		previous = newPrev;
	}
	
	/**
	 * Beállítja a következő kocsi referenciáját a kapott paraméterre
	 * @param cart A következő kocsi referenciája
	 */
	public void setNextCart(Cart cart){
		if(cart.equals(this)) //ha önmagát akarjuk beállítani következőnek
			throw new RuntimeException("Can't set a cart as next to itself");
		else
			this.next=cart;
	}

	/**
	 * @return the currentTrack
	 */
	public TrackComponent getCurrentTrack() {
		return currentTrack;
	}
}