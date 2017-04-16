package iliketrains;

/**
 * Utaskocsi osztály
 * @author Imi
 */
public class PassengerCart extends Cart {

	/** A következő kocsi referenciája */
	 PassengerCart nextCart;
	
	/** Az utaskocsi színe */
	private Color color;
	
	/** Utaznak-e utasok a kocsin */
	private boolean passengers;

	/**
	 * Konstruktor.
	 * A kapott paramétereket inicializálja
	 * @param id Utaskocsi azonosítója
	 * @param passengers Utasok meghatározásához szükséges input string
	 * @param color Az utaskocsi színe
	 */
	public PassengerCart(int id, String passengers, String color){
		super(id);
		if(passengers.equals("0"))
			this.passengers=false;
		else if (passengers.equals("1"))
			this.passengers=true;

		this.color=Color.values()[Integer.parseInt(color)];
		}
	
	/**
	 * Az utaskocsi színét visszaadó függvény
	 * @return color Az utaskocsi színe
	 */
	public final Color getColor() {
		return this.color;
	}

	/**
	 * Az utasok felszállítása
	 */
	public void addPassengers(){
		passengers=true;
	}
	
	/**
	 * Az utasok leszállítása
	 */
	public void popPassengers() {
		passengers=false;
	}
	
	/**
	 * Annak a lekérdezése, hogy vannak-e utasok a kocsiban
	 * @return a leírás szerinti logikai érték
	 */
	public final boolean isNotEmpty(){
		return passengers;
	}

	/**
	 * Utaskocsi hozzákapcsolása ehhez a kocsihoz
	 * @param pCart A kapcsolandó utaskocsi referenciája
	 */
	public void addNext(PassengerCart pCart) {
		if(pCart.equals(this))	//ha önmagát akarnánk megnevezni, mint a következő utaskocsi
			throw new RuntimeException("Can't link next PassangerCart to self");
		else
			nextCart=pCart;
	}

}