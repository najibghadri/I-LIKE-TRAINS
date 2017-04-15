package iliketrains;

import skeleton.Skeleton;

/**
 * A vonatkocsi ősosztály
 * Nem absztrakt, mert a származtatott osztályok közös része itt kerül megvalósításra
 */
public class Cart {

	/** A sín elem, amin aktuálisan tartózkodik */
	protected TrackComponent currentTrack;
	
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
	 * A vonatkocsit mozgató függvény, a paraméteréül kapott objektumra mozgat
	 * @param whereTo Pályaelem amire a kocsit mozgatni kívánjuk
	 */
	public void moveCart(TrackComponent whereTo) {
		Skeleton.addIndent();
		Skeleton.write("Cart.moveCart calls currentTrack.removeCart()");
		currentTrack.removeCart(this);
		Skeleton.write("Cart.moveCart calls whereTo.putCart()");
		whereTo.putCart(this);
		Skeleton.write("Cart.moveCart returns");
		Skeleton.removeIndent();
	}
	

	/**
	 * Beállítja a következő kocsi referenciáját a kapott paraméterre
	 * @param cart A következő kocsi referenciája
	 */
	public void setNextCart(Cart cart){

	}

}