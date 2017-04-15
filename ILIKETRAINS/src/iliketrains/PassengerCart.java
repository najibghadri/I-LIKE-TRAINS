package iliketrains;

import skeleton.Skeleton;

/**
 * Utaskocsi osztály
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
	public PassengerCart(int id,String passengers,String color){
		super(id);
		Skeleton.write("PassengerCart konstruktor");
	}
	
	/**
	 * Az utaskocsi színét visszaadó függvény
	 * @return color Az utaskocsi színe
	 */
	public Color getColor() {
		return this.color;
	}


	/**
	 * Az utasok leszállítása.
	 * @param color Az adott szín, amilyen utasokat leszállítani kívánunk
	 * @return Az kocsi referenciája
	 */
	public Cart popPassengers(Color color) {
		Skeleton.addIndent();
		boolean empty=Skeleton.askIN("Üres már a kocsi?");
		if(empty){
			boolean allEmpty=Skeleton.askIN("Ez az utolsó kocsi?");
			if(allEmpty){
				
			}else{
				Skeleton.write("Cart.popPassenger calls nextCart.popPassengers(color)");
				this.popPassengers(color);
			}
		}
		else{
			Skeleton.askIN("Egyezik a szín?");
		}
		Skeleton.removeIndent();
		return this;
	}


	/**
	 * Utaskocsi hozzákapcsolása ehhez a kocsihoz
	 * @param pCart A kapcsolandó utaskocsi referenciája
	 */
	public void addNext(PassengerCart pCart) {
	}

}